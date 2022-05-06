package spring.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.stereotype.Service;
import spring.domain.Answer;
import spring.domain.Question;
import spring.exceptions.QuestionStructureException;

import java.util.List;


@Service
public class TestService {

    private final int numberOfCorrectAnswersForTest;
    private final IOService ioService;
    private final QuestionService questionService;
    private final MessageSourceService messageSourceService;

    public TestService(@Value("${numberOfCorrectAnswers}") int numberOfCorrectAnswersForTest, IOService ioService, QuestionService questionService, MessageSourceService messageSourceService) {
        this.numberOfCorrectAnswersForTest = numberOfCorrectAnswersForTest;
        this.ioService = ioService;
        this.questionService = questionService;
        this.messageSourceService = messageSourceService;
    }

    public void run() {
        var name = ioService.readLn(messageSourceService.getMessage("enterName", null));
        try {
            var questions = questionService.getQuestions();
            int numberCorrectAnswers = showQuestionsAndGetUserAnswers(questions);
            checkResultTest(numberCorrectAnswers, numberOfCorrectAnswersForTest);
        } catch (QuestionStructureException e) {
            ioService.out(e.getMessage());
        } catch (RuntimeException e) {
            ioService.out("Ошибка чтения файла с вопросами");
        }


    }

    private void checkResultTest(int numberCorrectAnswers, int numberOfCorrectAnswersForTest) {
        if (numberCorrectAnswers >= numberOfCorrectAnswersForTest) {
            ioService.out(messageSourceService.getMessage("passed", null));
        } else {
            ioService.out(messageSourceService.getMessage("missed", null));
        }
    }

    private int showQuestionsAndGetUserAnswers(List<Question> questions) {
        int numberCorrectAnswers = 0;
        for (Question question : questions) {
            int count = 0;
            ioService.out(question.getNameQuestion());
            ioService.out(messageSourceService.getMessage("responseOption", null));
            for (Answer answer : question.getAnswers()) {
                ioService.out(++count + ". " + answer.getNameAnswer());
            }
            var userAnswer = ioService.readLn(messageSourceService.getMessage("chooseAnswerOption", null));
            int countAnswer = 0;
            for (Answer answer : question.getAnswers()) {
                countAnswer++;
                if (answer.isCorrect() && userAnswer.equals(Integer.toString(countAnswer))) {
                    numberCorrectAnswers++;
                }
            }
        }
        return numberCorrectAnswers;
    }

}
