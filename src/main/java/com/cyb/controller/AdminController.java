package com.cyb.controller;

import com.cyb.codemsg.CodeMsg;
import com.cyb.pojo.Admin;
import com.cyb.service.AdminService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    Integer code;

    @ResponseBody
    @RequestMapping("/adminlogin")
    public Map<String,Object> adminlogin(@RequestParam("adminaccount")String adminaccount,@RequestParam("adminpassword")String adminpassword){
        Admin admin = adminService.adminlogin(adminaccount,adminpassword);
        Map<String,Object> back = new HashMap<>();
        if (admin == null){
            code = CodeMsg.Code_NOTEXIST;
        }else {
            code = CodeMsg.Code_EXIST;
            back.put("admin",admin);
            back.put("sign","admin");
        }
        back.put("code",code);
        return back;
    }


    @ResponseBody
    @RequestMapping("/updatemyinfo")
    public Map<String,Object> updatemyinfo(@RequestBody Map map){
        JSONObject jsonobject = JSONObject.fromObject(map.get("adminform").toString());
        Admin admin = (Admin)JSONObject.toBean(jsonobject,Admin.class);
        Integer flag = adminService.updatemyinfo(admin);
        Map<String,Object> back = new HashMap<>();
        if (flag <= 0){
            //更新失败
            code = CodeMsg.Code_ERROR;
        }else {
            code = CodeMsg.Code_SUCCESS;
        }
        back.put("code",code);
        return back;
    }
}
