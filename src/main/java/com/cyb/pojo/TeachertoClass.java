package com.cyb.pojo;

/**
 * 教师班级关系表
 */
public class TeachertoClass {

    private Integer ttcId;

    private Integer teacherId;

    private Integer classID;

    private Teacher teacher;

    private Class aClass;

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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }
}
