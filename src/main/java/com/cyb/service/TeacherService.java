package com.cyb.service;


import com.cyb.pojo.Student;
import com.cyb.pojo.Teacher;

import java.util.List;

public interface TeacherService {
    Teacher teacherlogin(String teacheraccount, String teacherpassword);

    Integer updatemyinfo(Teacher teacher);

    List<Student> getmyclassbyid(Integer classid);

    List<Student> getmyclassbyname(Integer teacherid,String classname);

    Integer getcountbyid(Integer classid);

    Integer getcountbyname(Integer teacherid, String classname);

    List<Teacher> getteacher(String teachername);

    Integer getteachernum(String teachername);

    Integer changeteacherstatus(Integer teacherId, Integer teacherStatus);

    Integer addteacher(Teacher teacher);

    Integer queryone(String teacherAccount);

    List<Teacher> getteacherlist();

    Teacher getteachernamebyid(Integer teacherid);
}
