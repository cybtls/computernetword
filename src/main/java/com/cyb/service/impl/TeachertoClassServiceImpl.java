package com.cyb.service.impl;

import com.cyb.mapper.TeachertoClassMapper;
import com.cyb.pojo.TeachertoClass;
import com.cyb.service.TeachertoClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("teachertoClassService")
public class TeachertoClassServiceImpl implements TeachertoClassService {

    @Autowired
    private TeachertoClassMapper teachertoClassMapper;

    @Override
    public List<TeachertoClass> getall() {
        return teachertoClassMapper.getall();
    }

    @Override
    public Integer queryone(Integer teacherid, Integer classid) {
        return teachertoClassMapper.queryone(teacherid,classid);
    }

    @Override
    public Integer addttc(Integer teacherid, Integer classid) {
        return teachertoClassMapper.addttc(teacherid,classid);
    }

    @Override
    public Integer delttc(Integer ttcid) {
        return teachertoClassMapper.delttc(ttcid);
    }
}
