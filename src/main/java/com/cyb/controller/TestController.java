package com.cyb.controller;

import com.cyb.pojo.Student;
import com.cyb.pojo.Test;
import com.cyb.service.StudentService;
import com.cyb.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/tt")
public class TestController {

    @Autowired
    private TestService testService;
    @Autowired
    private StudentService studentService;

    @ResponseBody
    @RequestMapping("/yy")
    public List<Test> test()
    {
        List<Test> test = testService.get();
        return test;
    }

    @ResponseBody
    @RequestMapping("/uu")
    public String testadd()
    {
        List<Test> test = testService.add();
        return "1";
    }



}
