package com.cyb.pojo;


import java.util.Date;
import java.util.List;

/**
 * 教师表
 */
public class Teacher {

    private Integer teacherId;

    private String teacherAccount;

    private String teacherPassword;

    private String teacherName;

    private String teacherEmail;

    private String teacherQuestion;

    private String teacherAnswer;

    private Integer teacherStatus;

    private Date teacherCreatedate;

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherAccount() {
        return teacherAccount;
    }

    public void setTeacherAccount(String teacherAccount) {
        this.teacherAccount = teacherAccount;
    }

    public String getTeacherPassword() {
        return teacherPassword;
    }

    public void setTeacherPassword(String teacherPassword) {
        this.teacherPassword = teacherPassword;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public String getTeacherQuestion() {
        return teacherQuestion;
    }

    public void setTeacherQuestion(String teacherQuestion) {
        this.teacherQuestion = teacherQuestion;
    }

    public String getTeacherAnswer() {
        return teacherAnswer;
    }

    public void setTeacherAnswer(String teacherAnswer) {
        this.teacherAnswer = teacherAnswer;
    }

    public Integer getTeacherStatus() {
        return teacherStatus;
    }

    public void setTeacherStatus(Integer teacherStatus) {
        this.teacherStatus = teacherStatus;
    }

    public Date getTeacherCreatedate() {
        return teacherCreatedate;
    }

    public void setTeacherCreatedate(Date teacherCreatedate) {
        this.teacherCreatedate = teacherCreatedate;
    }
}
