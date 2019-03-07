package com.cyb.service;

import com.cyb.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface StudentService {
    Student stulogin(Integer stuaccount, String stupassword);

    Integer updatemyinfo(Student student);

    Integer updatestatus(Integer stuid, Integer status);

    Integer delstudent(Integer stuid);

    Integer queryone(Integer classId);

    List<Student> getallstudent();

    Integer getallstudentnum();

    List<Student> getstudentbyclassid(Integer classid);
}
