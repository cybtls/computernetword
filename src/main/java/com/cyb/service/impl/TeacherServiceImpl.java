package com.cyb.service.impl;


import com.cyb.mapper.TeacherMapper;
import com.cyb.pojo.Teacher;
import com.cyb.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;


    @Override
    public Teacher teacherlogin(String teacheraccount, String teacherpassword) {
        Teacher teacher =  teacherMapper.teacherlogin(teacheraccount,teacherpassword);
        return teacher;
    }
}
