package com.cyb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/tt")
public class testController {

    @ResponseBody
    @RequestMapping("/yy")
    public String test(@RequestBody Map map)
    {
        System.out.println(map.get("stuaccount"));
        return "ok1111";
    }
}
