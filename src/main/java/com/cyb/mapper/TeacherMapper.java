package com.cyb.mapper;

import com.cyb.pojo.Class;
import com.cyb.pojo.Student;
import com.cyb.pojo.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherMapper {
    Teacher teacherlogin(@Param("teacheraccount") String teacheraccount, @Param("teacherpassword") String teacherpassword);

    Integer updatemyinfo(Teacher teacher);


    List<Student> getmyclassbyid(@Param("classid") Integer classid);

    List<Student> getmyclassbyname(@Param("teacherid") Integer teacherid, @Param("classname") String classname);

    Integer getcountbyid(@Param("classid") Integer classid);

    Integer getcountbyname(@Param("teacherid") Integer teacherid, @Param("classname") String classname);
}
