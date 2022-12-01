package ru.yescoding.app.model.dto.Test;

import ru.yescoding.app.model.dto.Test.Answers;

import java.util.List;

public class Questions {
    private String question;
    private List<Answers> answers;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Answers> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answers> answers) {
        this.answers = answers;
    }
}
