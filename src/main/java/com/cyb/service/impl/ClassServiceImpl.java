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
}
