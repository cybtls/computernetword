package com.cyb.service.impl;


import com.cyb.mapper.StudentMapper;
import com.cyb.pojo.Student;
import com.cyb.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;


    @Override
    public Student stulogin(Integer stuaccount, String stupassword) {
        Student stu = studentMapper.stulogin(stuaccount,stupassword);
        return stu;
    }

    @Override
    public Integer updatemyinfo(Student student) {
        Integer flag = studentMapper.updatemyinfo(student);
        return  flag;
    }

    @Override
    public Integer updatestatus(Integer stuid, Integer status) {
        Integer flag = studentMapper.updatestatus(stuid,status);
        return  flag;
    }

    @Override
    public Integer delstudent(Integer stuid) {
        Integer flag = studentMapper.delstudent(stuid);
        return  flag;
    }

    @Override
    public Integer queryone(Integer classId) {
        Integer flag = studentMapper.queryone(classId);
        return  flag;
    }

    @Override
    public List<Student> getallstudent() {
        List<Student> students = studentMapper.getallstudent();
        return students;
    }

    @Override
    public Integer getallstudentnum() {
        Integer count = studentMapper.getallstudentnum();
        return count;
    }

    @Override
    public List<Student> getstudentbyclassid(Integer classid) {
        return null;
    }

    @Override
    public Integer getstudentnumbyclassname(String classname) {
        Integer count = studentMapper.getstudentnumbyclassname(classname);
        return count;
    }

    @Override
    public List<Student> getstudentbyclassname(String classname) {
        List<Student> students = studentMapper.getstudentbyclassname(classname);
        return students;
    }

    @Override
    public Integer getstudentnumbystudentname(String studentname) {
        Integer count = studentMapper.getstudentnumbystudentname(studentname);
        return count;
    }

    @Override
    public List<Student> getstudentbystudentname(String studentname) {
        List<Student> students = studentMapper.getstudentbystudentname(studentname);
        return students;
    }

    @Override
    public Integer addstudent(Student student) {
        Integer flag = studentMapper.addstudent(student);
        return flag;
    }

    @Override
    public Integer getstudentnumbyclassid(Integer classId) {
        Integer flag = studentMapper.getstudentnumbyclassid(classId);
        return flag;
    }


}
