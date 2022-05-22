package spring.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.stereotype.Component;
import spring.config.AppSettings;
import spring.domain.Question;
import spring.exceptions.QuestionStructureException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component

public class QuestionDaoImpl implements QuestionDao {
    private final String fileName;


    public QuestionDaoImpl(AppSettings appSettings) {
        this.fileName = appSettings.getFileNameWithLanguage();

    }

    public List<Question> getQuestions() {
        try {
            return getDataFromStream(getInputStreamFromResources(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Question> getDataFromStream(InputStream is) throws IOException {
        List<Question> dataFromFile = new ArrayList<>();

        try (InputStreamReader streamReader =
                     new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                dataFromFile.add(stringToQuestion(line));
            }

        }
        return dataFromFile;
    }

    private Question stringToQuestion(String str) {
        List<String> questionList = Arrays.asList(str.split(","));
        if (str.equals("") || questionList.size() == 1) {
            throw new QuestionStructureException();
        }
        Question question = new Question(questionList.get(0));
        addAnswer(question, questionList);
        return question;

    }

    private void addAnswer(Question question, List<String> questionList) {
        if (questionList.size() == 2) {
            question.addAnswer(questionList.get(1), true);
        } else if (questionList.size() > 2) {
            for (int i = 1; i < questionList.size() - 1; i++) {
                question.addAnswer(questionList.get(i), (Integer.toString(i)).equals(questionList.get(questionList.size() - 1)));
            }
        }

    }

    private InputStream getInputStreamFromResources(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }
}



