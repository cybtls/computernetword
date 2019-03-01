package com.cyb.controller;


import com.cyb.pojo.Class;
import com.cyb.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/class")
public class ClassController {

    @Autowired
    private ClassService classService;

    @ResponseBody
    @RequestMapping("/queryall")
    public List<Class> queryall(){
        List<Class> list = classService.queryall();
        return list;
    }

}
