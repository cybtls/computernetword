package com.cyb.pojo;

import java.sql.Timestamp;
import java.util.Date;

public class Problems {

    private Integer problemId;

    private String problemTitle;

    private String problemText;

    private Integer teacherId;

    private Integer stuId;

    private Answer answer;

    private String problemCreatedate;

    public Integer getProblemId() {
        return problemId;
    }

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }

    public String getProblemText() {
        return problemText;
    }

    public void setProblemText(String problemText) {
        this.problemText = problemText;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getProblemTitle() {
        return problemTitle;
    }

    public void setProblemTitle(String problemTitle) {
        this.problemTitle = problemTitle;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public String getProblemCreatedate() {
        return problemCreatedate;
    }

    public void setProblemCreatedate(String problemCreatedate) {
        this.problemCreatedate = problemCreatedate;
    }
}
