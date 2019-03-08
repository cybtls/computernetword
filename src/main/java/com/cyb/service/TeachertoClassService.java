package com.cyb.service;

import com.cyb.pojo.TeachertoClass;

import java.util.List;

public interface TeachertoClassService {

    List<TeachertoClass> getall();

    Integer queryone(Integer teacherid, Integer classid);

    Integer addttc(Integer teacherid, Integer classid);

    Integer delttc(Integer ttcid);
}
