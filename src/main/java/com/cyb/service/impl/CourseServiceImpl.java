package com.cyb.service.impl;

import com.cyb.mapper.CourseMapper;
import com.cyb.pojo.Course;
import com.cyb.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("courseService")
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> getcourse() {
        return courseMapper.getcourse();
    }
}
