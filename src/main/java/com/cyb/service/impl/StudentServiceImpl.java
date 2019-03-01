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

}
