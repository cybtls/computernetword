package com.cyb.controller;

import com.cyb.codemsg.CodeMsg;
import com.cyb.pojo.Admin;
import com.cyb.pojo.Class;
import com.cyb.pojo.Student;
import com.cyb.service.AdminService;
import com.cyb.service.ClassService;
import com.cyb.service.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private ClassService classService;

    @Autowired
    private StudentService studentService;

    private Integer pageSize = 10;

    Integer code;

//    管理员登录检查
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


//    管理员更新个人信息
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


//    管理员获取所有班级名与id与人数
    @ResponseBody
    @RequestMapping("/getclasslist")
    public Map<String,Object> getclasslist(@RequestBody Map map){
        String classname = (String) map.get("classname");
        Integer CurrentpageNum = (Integer) map.get("CurrentpageNum");
        List<Class> classinfo;
        Integer count;
        if (classname != null && classname.trim().length() >0 ){
            //有传入班级名，按班级名查询
//            System.out.println(classname);
            count = classService.getclassnum(classname);
            PageHelper.startPage(CurrentpageNum, pageSize);
            classinfo =  classService.getclasslist(classname);
        }else {
            //没有传入班级名，查询全部
            count = classService.getclassnum(null);
            PageHelper.startPage(CurrentpageNum, pageSize);
            classinfo =  classService.getclasslist(null);
        }
        Map<String,Object> back = new HashMap<>();
        if (classinfo == null){
            code = CodeMsg.Code_ERROR;
        }else {
            code = CodeMsg.Code_SUCCESS;
            back.put("classinfo",classinfo);
        }
        back.put("code",code);
        back.put("count",count);
        return back;
    }


    @ResponseBody
    @RequestMapping("/getallclass")
    public Map<String,Object> getallclass(){
        List<Class> classList = classService.getallclass();
        Map<String,Object> back = new HashMap<>();
        if (classList == null){
            code = CodeMsg.Code_ERROR;
        }else {
            code = CodeMsg.Code_SUCCESS;
            back.put("classList",classList);
        }
        return back;
    }


//    修改班级名称
    @ResponseBody
    @RequestMapping("/updateclassname")
    public Map<String,Object> updateclassname(@RequestParam("classid")Integer classId,@RequestParam("classname")String classname){
        Integer count = classService.queryone(classname);
        Map<String,Object> back = new HashMap<>();
        if(count > 0){
            code = CodeMsg.Code_EXIST;
        }else {
            Integer flag = classService.updateclassname(classId,classname);
            if (flag <= 0){
                code = CodeMsg.Code_ERROR;
            }else {
                code = CodeMsg.Code_SUCCESS;
            }
        }
        back.put("code",code);
        return back;
    }


//    删除班级
    @ResponseBody
    @RequestMapping("/delclass")
    public Map<String,Object> delclass(@RequestParam("classid")Integer classId){
        Integer delflag = studentService.queryone(classId);
        Map<String,Object> back = new HashMap<>();
        if(delflag > 0){
            code = CodeMsg.Code_EXIST;
        }else {
            Integer flag = classService.delclass(classId);
            if(flag > 0){
                code = CodeMsg.Code_SUCCESS;
            }else {
                code = CodeMsg.Code_ERROR;
            }
        }
        back.put("code",code);
        return back;
    }


//    添加班级
    @ResponseBody
    @RequestMapping("/addclass")
    public Map<String,Object> addclass(@RequestParam("classname")String classname){
        Integer count = classService.queryone(classname);
        if(count > 0){
            code = CodeMsg.Code_EXIST;
        }else {
            Integer flag = classService.addclass(classname);
            if(flag > 0){
                code = CodeMsg.Code_SUCCESS;
            }else {
                code = CodeMsg.Code_ERROR;
            }
        }
        Map<String,Object> back = new HashMap<>();
        back.put("code",code);
        return back;
    }

//    获取所有学生
    @ResponseBody
    @RequestMapping("/getallstudent")
    public Map<String,Object> getallstudent(@RequestParam("CurrentpageNum")Integer CurrentpageNum){
        Integer count = studentService.getallstudentnum();
        Map<String,Object> back = new HashMap<>();
        if(count > 0){
            PageHelper.startPage(CurrentpageNum, pageSize);
            List<Student> students = studentService.getallstudent();
            back.put("count",count);
            if(students != null){
                code = CodeMsg.Code_SUCCESS;
                back.put("students",students);
            }else {
                code = CodeMsg.Code_ERROR;
            }
        }else {
            code = CodeMsg.Code_NOTEXIST;
        }
        back.put("code",code);
        return back;
    }

    @ResponseBody
    @RequestMapping("getstudentbyclassid")
    public Map<String,Object> getstudentbyclassid(@RequestParam("classId")Integer classid){
        List<Student> students = studentService.getstudentbyclassid(classid);
        Map<String,Object> back = new HashMap<>();
        return back;
    }
}
