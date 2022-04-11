package spring.domain;

public class Answer {
    private final String nameAnswer;
    private  final boolean isCorrect;

    public Answer(String nameAnswer, boolean isCorrect) {
        this.nameAnswer = nameAnswer;
        this.isCorrect = isCorrect;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public String getNameAnswer() {
        return nameAnswer;
    }
}
