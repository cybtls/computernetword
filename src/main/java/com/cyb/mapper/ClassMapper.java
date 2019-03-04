package com.cyb.mapper;

import com.cyb.pojo.Class;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassMapper {
    List<Class> queryall();

    List<Class> getclassinfobyteacherId(@Param("teacherId") Integer teacherId);
}
