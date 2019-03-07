package com.cyb.service;

import com.cyb.pojo.Class;

import java.util.List;

public interface ClassService {
    List<Class> queryall();

    List<Class> getclassinfobyteacherId(Integer teacherId);

    List<Class> getclasslist(String classname);

    Integer getclassnum(String classname);

    Integer updateclassname(Integer classId, String classname);

    Integer queryone(String classname);

    Integer delclass(Integer classId);

    Integer addclass(String classname);

    List<Class> getallclass();
}
