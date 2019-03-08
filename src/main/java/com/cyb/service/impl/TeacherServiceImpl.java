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

    @Override
    public List<Teacher> getteacher(String teachername) {
        List<Teacher> teachers = teacherMapper.getteacher(teachername);
        return teachers;
    }

    @Override
    public Integer getteachernum(String teachername) {
        Integer count = teacherMapper.getteachernum(teachername);
        return count;
    }

    @Override
    public Integer changeteacherstatus(Integer teacherId, Integer teacherStatus) {
        Integer flag = teacherMapper.changeteacherstatus(teacherId,teacherStatus);
        return flag;
    }

    @Override
    public Integer addteacher(Teacher teacher) {
        Integer flag = teacherMapper.addteacher(teacher);
        return flag;
    }

    @Override
    public Integer queryone(String teacherAccount) {
        Integer flag = teacherMapper.queryone(teacherAccount);
        return flag;
    }

    @Override
    public List<Teacher> getteacherlist() {
        List<Teacher> teachers = teacherMapper.getteacherlist();
        return teachers;
    }


}
