package com.cyb.pojo;

import java.util.Date;

public class Answer {

    private Integer answerId;

    private Integer problemId;

    private String answerText;

    private Date answerCreatedate;

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

    public Date getAnswerCreatedate() {
        return answerCreatedate;
    }

    public void setAnswerCreatedate(Date answerCreatedate) {
        this.answerCreatedate = answerCreatedate;
    }
}
