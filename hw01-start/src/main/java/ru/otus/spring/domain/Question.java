package ru.otus.spring.domain;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private  String question;
    private List<String> answers=new ArrayList<>();

    public void setQuestion(String question) {
        this.question = question;
    }

    public void addAnswer(String answer) {
       answers.add(answer);
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", answers=" + answers +
                ", trueAnswer='" + trueAnswer + '\'' +
                '}';
    }

    public void setTrueAnswer(String trueAnswer) {
        this.trueAnswer = trueAnswer;
    }

    private String trueAnswer;

}




