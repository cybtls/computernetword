package com.cyb.pojo;

import java.util.Date;

public class Problems {

    private Integer problemId;

    private String problemText;

    private Integer teacherId;

    private Integer stuId;

    private Date problemCreatedate;

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

    public Date getProblemCreatedate() {
        return problemCreatedate;
    }

    public void setProblemCreatedate(Date problemCreatedate) {
        this.problemCreatedate = problemCreatedate;
    }
}
