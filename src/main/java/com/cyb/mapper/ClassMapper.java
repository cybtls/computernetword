package com.cyb.mapper;

import com.cyb.pojo.Class;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassMapper {
    List<Class> queryall();

    List<Class> getclassinfobyteacherId(@Param("teacherId") Integer teacherId);

    List<Class> getclasslist(@Param("classname") String classname);

    Integer getclassnum(@Param("classname") String classname);

    Integer updateclassname(@Param("classId") Integer classId, @Param("classname") String classname);

    Integer queryone(@Param("classname") String classname);

    Integer delclass(@Param("classId") Integer classId);

    Integer addclass(@Param("classname") String classname);

    List<Class> getallclass();

    Class getone(@Param("classname") String classname);
}
