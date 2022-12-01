package ru.yescoding.app.model.dto;

import java.sql.Date;

public class SuccessDto {
    private String testName;
    private int done;
    private int max;
    private int donePercent;
    private String subject;
    private Date finishDate;

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public int getDone() {
        return done;
    }

    public void setDone(int done) {
        this.done = done;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getDonePercent() {
        return donePercent;
    }

    public void setDonePercent(int donePercent) {
        this.donePercent = donePercent;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }
}
