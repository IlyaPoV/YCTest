package ru.yescoding.app.model.dto;

import ru.yescoding.app.model.dto.Test.Questions;

import java.util.List;

public class TestDto {
    private String testName;
    private List<Questions> questionsList;

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public List<Questions> getQuestionsList() {
        return questionsList;
    }

    public void setQuestionsList(List<Questions> questionsList) {
        this.questionsList = questionsList;
    }
}
