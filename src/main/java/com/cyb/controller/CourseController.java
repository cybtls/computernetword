package com.cyb.controller;

import com.cyb.codemsg.CodeMsg;
import com.cyb.pojo.Course;
import com.cyb.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    private Integer code;

    @ResponseBody
    @RequestMapping("/getcourse")
    public Map<String,Object> getcourse(){
        Map<String,Object> back = new HashMap<>();
        List<Course> courses = courseService.getcourse();
        if (courses ==  null){
            code = CodeMsg.Code_ERROR;
        }else {
            code = CodeMsg.Code_SUCCESS;
            back.put("courses",courses);
        }
        back.put("code",code);
        return back;
    }

}
