package com.cyb.service;

import com.cyb.pojo.Teacher;

public interface TeacherService {
    Teacher teacherlogin(String teacheraccount, String teacherpassword);

    Integer updatemyinfo(Teacher teacher);
}
