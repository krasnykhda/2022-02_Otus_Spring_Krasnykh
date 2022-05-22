package spring;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito.*;
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

public class QuestionDaoImplTest {
    private AppSettings appSettings;

    @Test

    @DisplayName("Метод получения вопросов возвращает корректный список вопросов")
    void questionsNameTest() {
        QuestionDao questionDao = new QuestionDaoImpl(appSettings);
        var questions = questionDao.getQuestions();

        Assertions.assertThat(questions.get(0).getNameQuestion()).isEqualTo("What is the number pi equal to?");
        Assertions.assertThat(questions.get(1).getNameQuestion()).isEqualTo("The speed of light in a vacuum?");
        Assertions.assertThat(questions.get(2).getNameQuestion()).isEqualTo("The first cosmonaut of the earth?");

    }
    @Test
    @DisplayName("Метод получения вопросов корректно определяет правильный ответ")
    void answerTest() {
        QuestionDao questionDao = new QuestionDaoImpl(appSettings);
        var questions = questionDao.getQuestions();

        Assertions.assertThat(questions.get(0).getAnswers().get(0).isCorrect()).isEqualTo(true);
        Assertions.assertThat(questions.get(1).getAnswers().get(0).isCorrect()).isEqualTo(true);
        Assertions.assertThat(questions.get(2).getAnswers().get(0).isCorrect()).isEqualTo(true);

    }

    @BeforeEach
    void setUp() {
        appSettings = mock(AppSettings.class);
        when(this.appSettings.getFileNameWithLanguage()).thenReturn("testquestions.csv");
    }

}
