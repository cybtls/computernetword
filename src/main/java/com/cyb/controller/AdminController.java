package com.cyb.controller;

import com.cyb.codemsg.CodeMsg;
import com.cyb.pojo.*;
import com.cyb.pojo.Class;
import com.cyb.service.*;
import com.github.pagehelper.PageHelper;
import net.sf.json.JSONObject;
import org.aspectj.apache.bcel.classfile.Code;
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

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TeachertoClassService teachertoClassService;

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

//    @ResponseBody
//    @RequestMapping("getstudentbyclassid")
//    public Map<String,Object> getstudentbyclassid(@RequestParam("classId")Integer classid){
//        List<Student> students = studentService.getstudentbyclassid(classid);
//        Map<String,Object> back = new HashMap<>();
//        return back;
//    }

//    按班级名称获取学生信息
    @ResponseBody
    @RequestMapping("/getstudentbyclassname")
    public Map<String,Object> getstudentbyclassname(@RequestParam("classname")String classname,@RequestParam("CurrentpageNum")Integer CurrentpageNum){
        Integer count = studentService.getstudentnumbyclassname(classname);
        Map<String,Object> back = new HashMap<>();
        if(count > 0){
            PageHelper.startPage(CurrentpageNum, pageSize);
            List<Student> students = studentService.getstudentbyclassname(classname);
            back.put("students",students);
            back.put("count",count);
            code = CodeMsg.Code_SUCCESS;
        }else {
            code = CodeMsg.Code_NOTEXIST;
        }
        back.put("code",code);
        return back;
    }

//    按学生名查找学生
    @ResponseBody
    @RequestMapping("/getstudentbystudentname")
    public Map<String,Object> getstudentbystudentname(@RequestParam("studentname")String studentname,@RequestParam("CurrentpageNum")Integer CurrentpageNum){
        Integer count = studentService.getstudentnumbystudentname(studentname);
        Map<String,Object> back = new HashMap<>();
        if(count > 0){
            PageHelper.startPage(CurrentpageNum, pageSize);
            List<Student> students = studentService.getstudentbystudentname(studentname);
            back.put("students",students);
            back.put("count",count);
            code = CodeMsg.Code_SUCCESS;
        }else {
            code = CodeMsg.Code_NOTEXIST;
        }
        back.put("code",code);
        return back;
    }


    //    删除学生
//    @ResponseBody
//    @RequestMapping("/delstudent")
//    public  Map<String,Object> delstudent(@RequestParam("studentid")Integer studentid){
//        Integer flag  = studentService.delstudent(studentid);
//        if (flag != 0){
//            code = CodeMsg.Code_SUCCESS;
//        }else {
//            code = CodeMsg.Code_ERROR;
//        }
//        Map<String,Object> back = new HashMap<>();
//        back.put("code",code);
//        return back;
//    }

    @ResponseBody
    @RequestMapping("/chagestatus")
    public  Map<String,Object> chagestatus(@RequestBody Map map){
        Integer stuid = (Integer) map.get("stuid");
        Integer status = (Integer) map.get("status");
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

    //    更新学生
    @ResponseBody
    @RequestMapping("/editstudent")
    public Map<String,Object> editstudent(@RequestBody Map map){
        JSONObject jsonobject = JSONObject.fromObject(map.get("editstuinfo").toString());
        Student student = (Student)JSONObject.toBean(jsonobject,Student.class);
        Integer flag = studentService.updatemyinfo(student);
        Map<String,Object> back = new HashMap<>();
        if (flag <= 0){
//            更新失败
            code = CodeMsg.Code_ERROR;
        }else {
            code = CodeMsg.Code_SUCCESS;
        }
        back.put("code",code);
        return back;
    }

    //    添加学生
    @ResponseBody
    @RequestMapping("/addstudent")
    public Map<String,Object> addstudent(@RequestBody Map map){
        JSONObject jsonobject = JSONObject.fromObject(map.get("newstuinfo").toString());
        Student student = (Student)JSONObject.toBean(jsonobject,Student.class);
        Integer count = studentService.getstudentnumbyclassid(student.getStuClass().getClassId())+1;
        String num = null;
        if (count < 10){
            num = "0" + count;
        }
        Integer account = student.getStuClass().getClassId();
        String newaccount = null;
        if(account < 10 && account > 0){
            newaccount = "00" + account;
        }
        if (account >= 10 && account < 100){
            newaccount = "0" + account;
        }
        String year = (String) map.get("year");
        newaccount = year+newaccount+num;
        account =  Integer.parseInt(newaccount);
        student.setStuAccount(account);
        Integer flag = studentService.addstudent(student);
        Map<String,Object> back = new HashMap<>();
        if (flag <= 0){
            code = CodeMsg.Code_ERROR;
        }else {
            code = CodeMsg.Code_SUCCESS;
        }
        back.put("code",code);
        return back;
    }

//    获取教师信息
    @ResponseBody
    @RequestMapping("/getteacher")
    public Map<String,Object> getteacher(@RequestBody Map map){
        String teachername = (String) map.get("searchteachername");
        Integer CurrentpageNum = (Integer) map.get("CurrentpageNum");
        Integer count = teacherService.getteachernum(teachername);
        Map<String,Object> back = new HashMap<>();
        if (count > 0){
            PageHelper.startPage(CurrentpageNum, pageSize);
            List<Teacher> teachers = teacherService.getteacher(teachername);
            code = CodeMsg.Code_SUCCESS;
            back.put("teachers",teachers);
            back.put("count",count);
        }else {
            code = CodeMsg.Code_NOTEXIST;
        }
        back.put("code",code);
        return back;
    }


//    改变教师状态
    @ResponseBody
    @RequestMapping("/changeteacherstatus")
    public  Map<String,Object> changeteacherstatus(@RequestBody Map map){
        Integer teacherId = (Integer) map.get("teacherId");
        Integer teacherStatus = (Integer) map.get("teacherStatus");
        Integer flag  = teacherService.changeteacherstatus(teacherId,teacherStatus);
        if (flag != 0){
            code = CodeMsg.Code_SUCCESS;
        }else {
            code = CodeMsg.Code_ERROR;
        }
        Map<String,Object> back = new HashMap<>();
        back.put("code",code);
        return back;
    }

//    编辑教师信息
    @ResponseBody
    @RequestMapping("/editteacher")
    public Map<String,Object> editteacher(@RequestBody Map map){
        JSONObject jsonobject = JSONObject.fromObject(map.get("teacherinfo").toString());
        Teacher teacher = (Teacher)JSONObject.toBean(jsonobject,Teacher.class);
        Integer flag = teacherService.updatemyinfo(teacher);
        Map<String,Object> back = new HashMap<>();
        if (flag > 0){
            code = CodeMsg.Code_SUCCESS;
        }else {
            code = CodeMsg.Code_ERROR;
        }
        back.put("code",code);
        return back;

    }

//    增加老师
    @ResponseBody
    @RequestMapping("/addteacher")
    public Map<String,Object> addteacher(@RequestBody Map map){
        JSONObject jsonobject = JSONObject.fromObject(map.get("addteacherinfo").toString());
        Teacher teacher = (Teacher)JSONObject.toBean(jsonobject,Teacher.class);
        Map<String,Object> back = new HashMap<>();
        Integer count = teacherService.queryone(teacher.getTeacherAccount());
        if (count > 0){
            code = CodeMsg.Code_EXIST;
        }else {
            Integer flag = teacherService.addteacher(teacher);
            if (flag > 0){
                code = CodeMsg.Code_SUCCESS;
            }else {
                code = CodeMsg.Code_ERROR;
            }
        }
        back.put("code",code);
        return back;
    }


//    获取教师班级关系
    @ResponseBody
    @RequestMapping("/getttc")
    public Map<String,Object> getttc(){
        List<TeachertoClass> teachertoClass = teachertoClassService.getall();
        Map<String,Object> back = new HashMap<>();
        if (teachertoClass == null){
            code = CodeMsg.Code_ERROR;
        }else {
            code = CodeMsg.Code_SUCCESS;
            back.put("teachertoClass",teachertoClass);
        }
        back.put("code",code);
        return back;

    }

    @ResponseBody
    @RequestMapping("/getteacherlist")
    public Map<String,Object> getteacherlist(){
        List<Teacher> teachers = teacherService.getteacherlist();
        Map<String,Object> back = new HashMap<>();
        if(teachers == null){
            code = CodeMsg.Code_ERROR;
        }else {
            code = CodeMsg.Code_SUCCESS;
            back.put("teacherlist",teachers);
        }
        back.put("code",code);
        return back;
    }

    @ResponseBody
    @RequestMapping("/addttc")
    public Map<String,Object> addttc(@RequestBody Map map){
        Integer teacherid = (Integer) map.get("ttcteacherid");
        String classname = (String) map.get("ttcclassname");
        Map<String,Object> back = new HashMap<>();
        Class ttcclass = classService.getone(classname);
        if (ttcclass == null){
            //班级不存在
            code = CodeMsg.Code_NOTEXIST;
        }else {
            Integer classid = ttcclass.getClassId();
            Integer flag = teachertoClassService.queryone(teacherid,classid);
            if (flag > 0){
                //该关系已经存在
                code = CodeMsg.Code_EXIST;
            }else {
                //添加新关系
                Integer sign = teachertoClassService.addttc(teacherid,classid);
                if (sign > 0){
                    code = CodeMsg.Code_SUCCESS;
                }else {
                    code = CodeMsg.Code_ERROR;
                }
            }
        }
        back.put("code",code);
        return back;
    }


    @ResponseBody
    @RequestMapping("/delttc")
    public Map<String,Object> delttc(@RequestParam("ttcid")Integer ttcid){
        Integer flag = teachertoClassService.delttc(ttcid);
        if(flag > 0){
            code = CodeMsg.Code_SUCCESS;
        }else {
            code = CodeMsg.Code_ERROR;
        }
        Map<String,Object> back = new HashMap<>();
        back.put("code",code);
        return back;
    }
}
