package com.cyb.controller;

import com.cyb.codemsg.CodeMsg;
import com.cyb.pojo.Score;
import com.cyb.pojo.Student;
import com.cyb.service.ScoreService;
import com.cyb.service.StudentService;
import com.github.pagehelper.PageHelper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    Integer code;

//    学生登录
    @ResponseBody
    @RequestMapping("/stulogin")
    public Map<String,Object> stulogin(@RequestBody Map map){
        Integer stuaccount = (Integer) map.get("stuaccount");
        String stupassword = (String) map.get("stupassword");
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

    //设置每页数量
    private Integer pageSize = 13;


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

}
