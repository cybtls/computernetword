package com.cyb.mapper;

import com.cyb.pojo.Teacher;
import org.apache.ibatis.annotations.Param;

public interface TeacherMapper {
    Teacher teacherlogin(@Param("teacheraccount") String teacheraccount, @Param("teacherpassword") String teacherpassword);

    Integer updatemyinfo(Teacher teacher);
}
