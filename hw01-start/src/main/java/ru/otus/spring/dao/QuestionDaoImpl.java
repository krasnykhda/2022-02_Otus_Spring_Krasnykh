package ru.otus.spring.dao;

import ru.otus.spring.domain.Question;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionDaoImpl implements QuestionDao {
    private String fileName;

    public QuestionDaoImpl(String fileName) {
        this.fileName = fileName;
    }

    public List<Question> getQuestions() throws IOException {
        File file = getFileFromResources(fileName);
        return getDataFromFile(file);
    }

    private List<Question> getDataFromFile(File file) throws IOException {
        List<Question> dataFromFile = new ArrayList<>();
        try (FileReader reader = new FileReader(file);
             BufferedReader br = new BufferedReader(reader)) {
            String line;
            while ((line = br.readLine()) != null) {
                dataFromFile.add(stringToQuestion(line));
            }
        }
        return dataFromFile;
    }

    private Question stringToQuestion(String str) {
        List<String> questionList = Arrays.asList(str.split(","));
        Question question = new Question();
        question.setQuestion(questionList.get(0));
        question.setTrueAnswer(questionList.get(questionList.size() - 1));
        for (int i = 1; i < questionList.size() - 1; i++) {
            question.addAnswer(questionList.get(i));
        }
        return question;
    }

    private File getFileFromResources(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }

    }
}



