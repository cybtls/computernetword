package com.cyb.pojo;

/**
 * 教师班级关系表
 */
public class TeachertoClass {

    private Integer ttcId;

    private Integer teacherId;

    private Integer classID;

    public Integer getTtcId() {
        return ttcId;
    }

    public void setTtcId(Integer ttcId) {
        this.ttcId = ttcId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getClassID() {
        return classID;
    }

    public void setClassID(Integer classID) {
        this.classID = classID;
    }
}
