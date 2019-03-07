package com.cyb.mapper;

import com.cyb.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
    Student stulogin(@Param("stuaccount") Integer stuaccount, @Param("stupassword") String stupassword);

    Integer updatemyinfo(Student student);

    Integer updatestatus(@Param("stuid") Integer stuid, @Param("status") Integer status);

    Integer delstudent(@Param("stuid") Integer stuid);

    Integer queryone(@Param("classId") Integer classId);

    List<Student> getallstudent();

    Integer getallstudentnum();
}
