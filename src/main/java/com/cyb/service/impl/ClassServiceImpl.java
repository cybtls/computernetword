package com.cyb.service.impl;


import com.cyb.mapper.ClassMapper;
import com.cyb.pojo.Class;
import com.cyb.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("classService")
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassMapper classMapper;

    @Override
    public List<Class> queryall() {
        return classMapper.queryall();
    }

    @Override
    public List<Class> getclassinfobyteacherId(Integer teacherId) {
        return classMapper.getclassinfobyteacherId(teacherId);
    }

    @Override
    public List<Class> getclasslist(String classname) {
        return classMapper.getclasslist(classname);
    }

    @Override
    public Integer getclassnum(String classname) {
        return classMapper.getclassnum(classname);
    }

    @Override
    public Integer updateclassname(Integer classId, String classname) {
        return classMapper.updateclassname(classId,classname);
    }

    @Override
    public Integer queryone(String classname) {
        return classMapper.queryone(classname);
    }

    @Override
    public Integer delclass(Integer classId) {
        return classMapper.delclass(classId);
    }

    @Override
    public Integer addclass(String classname) {
        return classMapper.addclass(classname);
    }

    @Override
    public List<Class> getallclass() {
        return classMapper.getallclass();
    }

    @Override
    public Class getone(String classname) {
        return classMapper.getone(classname);
    }
}
