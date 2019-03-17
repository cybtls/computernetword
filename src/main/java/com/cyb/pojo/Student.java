package com.cyb.pojo;

import java.util.List;

/**
 * 学生表
 */
public class Student {

    private Integer stuId;

    private Integer stuAccount;

    private String stuPassword;

    private String stuName;

    private Integer stuClassid;

    private String stuQuestion;

    private String stuAnswer;

    private Integer stuStatus;

    private Class stuClass;
    
    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public Integer getStuAccount() {
        return stuAccount;
    }

    public void setStuAccount(Integer stuAccount) {
        this.stuAccount = stuAccount;
    }

    public String getStuPassword() {
        return stuPassword;
    }

    public void setStuPassword(String stuPassword) {
        this.stuPassword = stuPassword;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Integer getStuClassid() {
        return stuClassid;
    }

    public void setStuClassid(Integer stuClassid) {
        this.stuClassid = stuClassid;
    }

    public String getStuQuestion() {
        return stuQuestion;
    }

    public void setStuQuestion(String stuQuestion) {
        this.stuQuestion = stuQuestion;
    }

    public String getStuAnswer() {
        return stuAnswer;
    }

    public void setStuAnswer(String stuAnswer) {
        this.stuAnswer = stuAnswer;
    }

    public Integer getStuStatus() {
        return stuStatus;
    }

    public void setStuStatus(Integer stuStatus) {
        this.stuStatus = stuStatus;
    }

    public Class getStuClass() {
        return stuClass;
    }

    public void setStuClass(Class stuClass) {
        this.stuClass = stuClass;
    }

}
