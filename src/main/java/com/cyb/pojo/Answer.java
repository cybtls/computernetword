package com.cyb.pojo;

import java.sql.Timestamp;

public class Answer {

    private Integer answerId;

    private Integer problemId;

    private String answerText;

    private Timestamp answerCreatedate;

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Integer getProblemId() {
        return problemId;
    }

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public Timestamp getAnswerCreatedate() {
        return answerCreatedate;
    }

    public void setAnswerCreatedate(Timestamp answerCreatedate) {
        this.answerCreatedate = answerCreatedate;
    }
}
