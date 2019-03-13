package com.cyb.controller;

import com.cyb.codemsg.CodeMsg;
import com.cyb.pojo.Score;
import com.cyb.pojo.Student;
import com.cyb.pojo.Teacher;
import com.cyb.service.ScoreService;
import com.cyb.service.StudentService;
import com.cyb.service.TeacherService;
import com.github.pagehelper.PageHelper;
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
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private TeacherService teacherService;

//    状态码
    private Integer code;

//    设置每页数量
    private Integer pageSize = 11;


//    学生登录
    @ResponseBody
    @RequestMapping("/stulogin")
    public Map<String,Object> stulogin(@RequestParam("stuaccount")Integer stuaccount, @RequestParam("stupassword")String stupassword){
//        Integer stuaccount = (Integer) map.get("stuaccount");
//        String stupassword = (String) map.get("stupassword");
        Student stu =  studentService.stulogin(stuaccount,stupassword);
        Map<String,Object> back  = new HashMap<>();
        if (stu == null){
//            账号不存在
            code = CodeMsg.Code_NOTEXIST;
        }else {
            if(stu.getStuStatus() == 0){
//            状态为封禁，不可登录
                code = CodeMsg.Code_CLOSURE;
            }else {
//            正常登录
                code = CodeMsg.Code_EXIST;
                back.put("stu",stu);
                back.put("sign","stu");
            }
        }
        back.put("code",code);
        return back;
    }


//    查询成绩
    @ResponseBody
    @RequestMapping("/getmyscore")
    public Map<String,Object> getmyscore(@RequestBody Map map){
        JSONObject jsonobject = JSONObject.fromObject(map.get("User").toString());
        Student student = (Student)JSONObject.toBean(jsonobject,Student.class);
        Integer stuId = student.getStuId();
        Integer pageNo = (Integer)map.get("CurrentpageNum");
        Integer count = scoreService.getcount(stuId,null);
        PageHelper.startPage(pageNo, pageSize);
        List<Score> score =  scoreService.getmyscore(stuId);
        if(score == null){
            code = CodeMsg.Code_ERROR;
        }else {
            code = CodeMsg.Code_SUCCESS;
        }
        Map<String,Object> back = new HashMap<>();
        back.put("myscore",score);
        back.put("count",count);
        back.put("code",code);
        return back;
    }


//    按课程名查询成绩
    @ResponseBody
    @RequestMapping("/getmyscorebyname")
    public Map<String,Object> getmyscorebyname(@RequestBody Map map){
        JSONObject jsonobject = JSONObject.fromObject(map.get("User").toString());
        Student student = (Student)JSONObject.toBean(jsonobject,Student.class);
        Integer stuId = student.getStuId();
        Integer pageNo = (Integer)map.get("CurrentpageNum");
        String coursename = (String)map.get("coursename");
        Integer count = scoreService.getcount(stuId,coursename);

        PageHelper.startPage(pageNo, pageSize);
        List<Score> score =  scoreService.getmyscorebyname(stuId,coursename);
        Map<String,Object> back = new HashMap<>();
        if(score == null){
            code = CodeMsg.Code_ERROR;
        }else {
            code = CodeMsg.Code_SUCCESS;
        }
        back.put("myscore",score);
        back.put("count",count);
        back.put("code",code);
        return back;
    }

//    更新个人信息
    @ResponseBody
    @RequestMapping("/updatemyinfo")
    public Map<String,Object> updatemyinfo(@RequestBody Map map){
        JSONObject jsonobject = JSONObject.fromObject(map.get("stuform").toString());
        Student student = (Student)JSONObject.toBean(jsonobject,Student.class);
        Integer flag = studentService.updatemyinfo(student);
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

    @ResponseBody
    @RequestMapping("/getteacherlist")
    public Map<String,Object> getteacherlist(){
        List<Teacher> teachers = teacherService.getteacherlist();
        Map<String,Object> back = new HashMap<>();
        if (teachers == null){
            code = CodeMsg.Code_ERROR;
        }else {
            code = CodeMsg.Code_SUCCESS;
            back.put("teachers",teachers);
        }
        back.put("code",code);
        return back;
    }

}
