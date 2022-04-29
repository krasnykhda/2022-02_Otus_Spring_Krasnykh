package spring.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import spring.domain.Answer;
import spring.domain.Question;
import spring.exceptions.QuestionStructureException;
import spring.service.QuestionService;

import java.util.List;

@Service
public class TestService {

    private final int numberOfCorrectAnswersForTest;
    private final IOService ioService;
    private final QuestionService questionService;

    public TestService(@Value("${numberOfCorrectAnswers}") int numberOfCorrectAnswersForTest, IOService ioService, QuestionService questionService) {
        this.numberOfCorrectAnswersForTest = numberOfCorrectAnswersForTest;
        this.ioService = ioService;
        this.questionService = questionService;
    }

    public void run() {
        var name = ioService.readLn("Введите ваше имя");
        try {
            var questions = questionService.getQuestions();
            int numberCorrectAnswers = showQuestionsAndGetUserAnswers(questions);
            checkResultTest(numberCorrectAnswers, numberOfCorrectAnswersForTest);
        } catch (NullPointerException e) {
            ioService.out("Файл с вопросами не найден.");
        }catch (QuestionStructureException e){
            ioService.out(e.getMessage());
        }
        catch (RuntimeException e) {
            ioService.out("Ошибка чтения файла с вопросами");
        }


    }

    private void checkResultTest(int numberCorrectAnswers, int numberOfCorrectAnswersForTest) {
        if (numberCorrectAnswers >= numberOfCorrectAnswersForTest) {
            ioService.out("Тест сдан");
        } else {
            ioService.out("Тест не сдан");
        }
    }

    private int showQuestionsAndGetUserAnswers(List<Question> questions) {
        int numberCorrectAnswers = 0;
        for (Question question : questions) {
            ioService.out(question.getNameQuestion());
            var userAnswer = ioService.readLn("Введите ваш ответ");
            for (Answer answer : question.getAnswers()) {
                if (answer.isCorrect() && answer.getNameAnswer().equalsIgnoreCase(userAnswer)) {
                    numberCorrectAnswers++;
                }
            }
        }
        return numberCorrectAnswers;
    }

}
