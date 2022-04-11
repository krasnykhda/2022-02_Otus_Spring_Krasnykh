package spring.domain;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private final String nameQuestion;
    private final List<Answer> answers = new ArrayList<>();

    public Question(String nameQuestion) {
        this.nameQuestion = nameQuestion;
    }


    public void addAnswer(String answer, boolean isCorrect) {
        answers.add(new Answer(answer, isCorrect));
    }


    public List<Answer> getAnswers() {
        return answers;
    }

    public String getNameQuestion() {
        return nameQuestion;
    }


}




