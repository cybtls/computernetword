package com.cyb.mapper;

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

    List<Teacher> getteacher(@Param("teachername") String teachername);

    Integer getteachernum(@Param("teachername") String teachername);

    Integer changeteacherstatus(@Param("teacherId") Integer teacherId, @Param("teacherStatus") Integer teacherStatus);

    Integer addteacher(Teacher teacher);

    Integer queryone(@Param("teacherAccount") String teacherAccount);

    List<Teacher> getteacherlist();
}
