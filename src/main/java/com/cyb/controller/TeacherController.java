package com.cyb.controller;


import com.cyb.codemsg.CodeMsg;
import com.cyb.pojo.Class;
import com.cyb.pojo.Student;
import com.cyb.pojo.Teacher;
import com.cyb.service.ClassService;
import com.cyb.service.StudentService;
import com.cyb.service.TeacherService;
import com.github.pagehelper.PageHelper;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("teacher")
public class TeacherController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ClassService classService;

    //    设置每页数量
    private Integer pageSize = 10;

    Integer code;

//    教师登录检查
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

//    教师更新个人信息
    @ResponseBody
    @RequestMapping("/updatemyinfo")
    public Map<String,Object> updatemyinfo(@RequestBody Map map){
        JSONObject jsonobject = JSONObject.fromObject(map.get("stuform").toString());
        Teacher teacher = (Teacher)JSONObject.toBean(jsonobject,Teacher.class);
//        System.out.println(teacher.getTeacherId());
        Integer flag =  teacherService.updatemyinfo(teacher);
        if (flag <= 0){
            //更新失败
            code = CodeMsg.Code_ERROR;
        }else {
            code = CodeMsg.Code_SUCCESS;
        }
        Map<String,Object> back  = new HashMap<>();
        back.put("code",code);
        return back;
    }


//    获取自己管理的班级
    @ResponseBody
    @RequestMapping("/getmyclassinfo")
    public Map<String,Object> getmyclassinfo(@RequestBody Map map){
        JSONObject jsonobject = JSONObject.fromObject(map.get("user").toString());
        Teacher teacher = (Teacher)JSONObject.toBean(jsonobject,Teacher.class);
//        System.out.println(teacher.getTeacherId());
        Integer teacherId = teacher.getTeacherId();
        List<Class> myclassinfo = classService.getclassinfobyteacherId(teacherId);
        Map<String,Object> back  = new HashMap<>();
        if (myclassinfo == null){
            //没有管理的班级
            code = CodeMsg.Code_NOTEXIST;
        }else {
            code = CodeMsg.Code_SUCCESS;
        }
        back.put("myclassinfo",myclassinfo);
        back.put("code",code);
        return back;
    }

//    通过班级id查询班级
    @ResponseBody
    @RequestMapping("/getmyclassbyid")
    public Map<String,Object> getmyclassbyid(@RequestBody Map map){
        Integer classid = (Integer) map.get("classid");
        Integer pageNo = (Integer) map.get("CurrentpageNum");
        Integer count = teacherService.getcountbyid(classid);
        PageHelper.startPage(pageNo, pageSize);
        List<Student> students = teacherService.getmyclassbyid(classid);
        Map<String,Object> back = new HashMap<>();
        if (students == null || students.isEmpty() || students.size() == 0){
            code = CodeMsg.Code_ERROR;
        }else {
            code = CodeMsg.Code_SUCCESS;
            back.put("students",students);
        }
        back.put("count",count);
        back.put("code",code);
        return back;
    }

//    通过班级名查询班级
    @ResponseBody
    @RequestMapping("/getmyclassbyname")
    public Map<String,Object> getmyclassbyname(@RequestBody Map map){
        JSONObject jsonobject = JSONObject.fromObject(map.get("user").toString());
        String classname = (String) map.get("classname");
        Integer pageNo = (Integer) map.get("CurrentpageNum");
        Teacher teacher = (Teacher)JSONObject.toBean(jsonobject,Teacher.class);
        Integer teacherid = teacher.getTeacherId();
        Integer count = teacherService.getcountbyname(teacherid,classname);
        PageHelper.startPage(pageNo, pageSize);
        List<Student> students = teacherService.getmyclassbyname(teacherid,classname);
        Map<String,Object> back = new HashMap<>();
        if (students == null || students.isEmpty() || students.size() == 0){
            code = CodeMsg.Code_ERROR;
        }else {
            code = CodeMsg.Code_SUCCESS;
            back.put("students",students);
            back.put("count",count);
        }
        back.put("code",code);
        return back;
    }

//    改变学生状态
    @ResponseBody
    @RequestMapping("/chagestatus")
    public  Map<String,Object> chagestatus(@RequestBody Map map){
        Integer stuid = (Integer) map.get("stuid");
        Integer status = (Integer) map.get("status");
//        System.out.println(stuid+"------------"+status);
        Integer flag  = studentService.updatestatus(stuid,status);
        if (flag != 0){
            code = CodeMsg.Code_SUCCESS;
        }else {
            code = CodeMsg.Code_ERROR;
        }
        Map<String,Object> back = new HashMap<>();
        back.put("code",code);
        return back;
    }

//    删除学生
    @ResponseBody
    @RequestMapping("/delstudent")
    public  Map<String,Object> delstudent(@RequestBody Map map){
        Integer stuid = (Integer) map.get("stuid");
        Integer flag  = studentService.delstudent(stuid);
        if (flag != 0){
            code = CodeMsg.Code_SUCCESS;
        }else {
            code = CodeMsg.Code_ERROR;
        }
        Map<String,Object> back = new HashMap<>();
        back.put("code",code);
        return back;
    }
}
