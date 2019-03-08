package com.cyb.mapper;

import com.cyb.pojo.TeachertoClass;
import com.cyb.service.TeachertoClassService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeachertoClassMapper {
    List<TeachertoClass> getall();

    Integer queryone(@Param("teacherid") Integer teacherid, @Param("classid") Integer classid);

    Integer addttc(@Param("teacherid") Integer teacherid, @Param("classid") Integer classid);

    Integer delttc(@Param("ttcid") Integer ttcid);
}
