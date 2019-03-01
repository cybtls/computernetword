package com.cyb.mapper;

import com.cyb.pojo.Student;
import org.apache.ibatis.annotations.Param;

public interface StudentMapper {
    Student stulogin(@Param("stuaccount") Integer stuaccount, @Param("stupassword") String stupassword);

    Integer updatemyinfo(Student student);
}
