package ru.otus.spring;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.dao.QuestionDaoImpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class QuestionDaoImplTest {
    private QuestionDao questionDao;

    @Test
    @DisplayName("должен бросать верное исключение при неправильном имени файла")
    void fileNameTest() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> questionDao.getQuestions());

    }

    @BeforeEach
    void setUp() {
        questionDao = new QuestionDaoImpl("badFile");
    }
}
