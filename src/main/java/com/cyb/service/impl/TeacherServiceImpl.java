package com.cyb.service.impl;


import com.cyb.mapper.TeacherMapper;
import com.cyb.pojo.Class;
import com.cyb.pojo.Student;
import com.cyb.pojo.Teacher;
import com.cyb.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;


    @Override
    public Teacher teacherlogin(String teacheraccount, String teacherpassword) {
        Teacher teacher =  teacherMapper.teacherlogin(teacheraccount,teacherpassword);
        return teacher;
    }

    @Override
    public Integer updatemyinfo(Teacher teacher) {
        Integer flag = teacherMapper.updatemyinfo(teacher);
        return flag;
    }

    @Override
    public List<Student> getmyclassbyid(Integer classid) {
        List<Student> students = teacherMapper.getmyclassbyid(classid);
        return students;
    }

    @Override
    public List<Student> getmyclassbyname(Integer teacherid,String classname) {
        List<Student> students = teacherMapper.getmyclassbyname(teacherid,classname);
        return students;
    }

    @Override
    public Integer getcountbyid(Integer classid) {
        Integer count = teacherMapper.getcountbyid(classid);
        return count;
    }

    @Override
    public Integer getcountbyname(Integer teacherid,String classname) {
        Integer count = teacherMapper.getcountbyname(teacherid,classname);
        return count;
    }
}
