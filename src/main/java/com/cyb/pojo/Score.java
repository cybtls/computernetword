package com.cyb.pojo;


/**
 * 成绩表
 */
public class Score {

    private Integer scoreId;

    private Integer stuId;

    private Integer courseId;

    private Integer courseScore;

    private Student student;

    private Course course;

    public Integer getScoreId() {
        return scoreId;
    }

    public void setScoreId(Integer scoreId) {
        this.scoreId = scoreId;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getCourseScore() {
        return courseScore;
    }

    public void setCourseScore(Integer courseScore) {
        this.courseScore = courseScore;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
