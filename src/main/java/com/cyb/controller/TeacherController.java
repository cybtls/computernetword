package com.cyb.controller;


import com.cyb.codemsg.CodeMsg;
import com.cyb.pojo.Teacher;
import com.cyb.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import net.sf.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    Integer code;

    @ResponseBody
    @RequestMapping("/teacherlogin")
    public Map<String,Object> teacherlogin(@RequestBody Map map){
        String teacheraccount = (String) map.get("teacheraccount");
        String teacherpassword = (String) map.get("teacherpassword");
        Teacher teacher = teacherService.teacherlogin(teacheraccount,teacherpassword);
        Map<String,Object> back  = new HashMap<>();
        if (teacher == null){
//            账号不存在
            code = CodeMsg.Code_NOTEXIST;
        }else {
            if(teacher.getTeacherStatus() == 0){
//            状态为封禁，不可登录
                code = CodeMsg.Code_CLOSURE;
            }else {
//            正常登录
                code = CodeMsg.Code_EXIST;
                back.put("teacher",teacher);
                back.put("sign","teacher");
            }
        }
        back.put("code",code);
        return back;
    }


    @ResponseBody
    @RequestMapping("/updatemyinfo")
    public Map<String,Object> updatemyinfo(@RequestBody Map map){
        System.out.println(map.get("stuform"));
        JSONObject jsonobject = JSONObject.fromObject(map.get("stuform").toString());
        Teacher teacher = (Teacher)JSONObject.toBean(jsonobject,Teacher.class);
//        System.out.println(teacher.getTeacherCreatedate());
//        System.out.println(teacher.getTeacherId());
//        System.out.println(new Date());
        teacher.setTeacherCreatedate("169836800000");
        Integer flag =  teacherService.updatemyinfo(teacher);
        if (flag <= 0){
            //更新失败
            code = CodeMsg.Code_ERROR;
            System.out.println("更新失败");
        }else {
            code = CodeMsg.Code_SUCCESS;
        }
        Map<String,Object> back  = new HashMap<>();
        back.put("code",code);
        return back;
    }
}
