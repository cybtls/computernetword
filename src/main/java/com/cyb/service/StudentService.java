package com.cyb.service;

import com.cyb.pojo.Student;


public interface StudentService {
    Student stulogin(Integer stuaccount, String stupassword);

    Integer updatemyinfo(Student student);

    Integer updatestatus(Integer stuid, Integer status);

    Integer delstudent(Integer stuid);
}
