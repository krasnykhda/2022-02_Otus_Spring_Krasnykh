package spring.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.stereotype.Service;
import spring.config.AppSettings;
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

    public TestService(AppSettings appSettings, IOService ioService, QuestionService questionService, MessageSourceService messageSourceService) {
        this.numberOfCorrectAnswersForTest = appSettings.getNumberOfCorrectAnswers();
        this.ioService = ioService;
        this.questionService = questionService;
        this.messageSourceService = messageSourceService;
    }

    public void run(String userName) {
        ioService.out(messageSourceService.getMessage("goodLuck") + " "+ userName);
        try {
            var questions = questionService.getQuestions();
            int numberCorrectAnswers = showQuestionsAndGetUserAnswers(questions);
            checkResultTest(numberCorrectAnswers, numberOfCorrectAnswersForTest);
        } catch (QuestionStructureException e) {
            ioService.out(messageSourceService.getMessage("questionStructureException"));
        } catch (RuntimeException e) {
            ioService.out(messageSourceService.getMessage("readFileError"));
        }


    }

    private void checkResultTest(int numberCorrectAnswers, int numberOfCorrectAnswersForTest) {
        if (numberCorrectAnswers >= numberOfCorrectAnswersForTest) {
            ioService.out(messageSourceService.getMessage("passed"));
        } else {
            ioService.out(messageSourceService.getMessage("missed"));
        }
    }

    private int showQuestionsAndGetUserAnswers(List<Question> questions) {
        int numberCorrectAnswers = 0;
        for (Question question : questions) {
            int count = 0;
            ioService.out(question.getNameQuestion());
            ioService.out(messageSourceService.getMessage("responseOption"));
            for (Answer answer : question.getAnswers()) {
                ioService.out(++count + ". " + answer.getNameAnswer());
            }
            var userAnswer = ioService.readLn(messageSourceService.getMessage("chooseAnswerOption"));
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
