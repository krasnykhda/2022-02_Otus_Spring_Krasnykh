package spring;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.dao.QuestionDao;

import org.assertj.core.api.Assertions;

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
