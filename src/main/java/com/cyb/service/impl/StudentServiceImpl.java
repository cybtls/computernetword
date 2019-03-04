package com.cyb.service.impl;


import com.cyb.mapper.StudentMapper;
import com.cyb.pojo.Student;
import com.cyb.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
