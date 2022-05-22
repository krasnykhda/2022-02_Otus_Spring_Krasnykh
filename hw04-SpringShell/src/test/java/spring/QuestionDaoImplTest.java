package spring;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import spring.config.AppSettings;
import spring.dao.QuestionDao;
import spring.dao.QuestionDaoImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.assertj.core.api.Assertions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class QuestionDaoImplTest {

    @Autowired
    private QuestionDao questionDao;

    @Test
    @DisplayName("Метод получения вопросов возвращает корректный список вопросов")
    void questionsNameTest() {
        var questions = questionDao.getQuestions();
        Assertions.assertThat(questions.get(0).getNameQuestion()).isEqualTo("What is the number pi equal to?");
        Assertions.assertThat(questions.get(1).getNameQuestion()).isEqualTo("The speed of light in a vacuum?");
        Assertions.assertThat(questions.get(2).getNameQuestion()).isEqualTo("The first cosmonaut of the earth?");

    }

    @Test
    @DisplayName("Метод получения вопросов корректно определяет правильный ответ")
    void answerTest() {
        var questions = questionDao.getQuestions();

        Assertions.assertThat(questions.get(0).getAnswers().get(0).isCorrect()).isEqualTo(true);
        Assertions.assertThat(questions.get(1).getAnswers().get(0).isCorrect()).isEqualTo(true);
        Assertions.assertThat(questions.get(2).getAnswers().get(0).isCorrect()).isEqualTo(true);

    }


}
