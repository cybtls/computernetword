package com.cyb.controller;

import com.cyb.codemsg.CodeMsg;
import com.cyb.pojo.Problems;
import com.cyb.pojo.Teacher;
import com.cyb.service.ProblemsService;
import com.cyb.service.TeacherService;
import com.github.pagehelper.PageHelper;
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
@RequestMapping("/problems")
public class ProblemsController {

    @Autowired
    private ProblemsService problemsService;

    @Autowired
    private TeacherService teacherService;

    private Integer code;

    private Integer pageSize = 10;

    @ResponseBody
    @RequestMapping("/getmyproblems")
    public Map<String,Object> getmyproblems(@RequestParam("stuid")Integer stuid){
        List<Problems> problems =  problemsService.getmyproblems(stuid);
        Map<String,Object> back = new HashMap<>();
        if (problems == null){
            code = CodeMsg.Code_NOTEXIST;
        }else {
            code = CodeMsg.Code_SUCCESS;
            back.put("problems",problems);
        }
        back.put("code",code);
        return back;
    }

    @ResponseBody
    @RequestMapping("/getteachernamebyid")
    public  Map<String,Object> getteachernamebyid(@RequestParam("teacherid")Integer teacherid){
        Teacher teacher = teacherService.getteachernamebyid(teacherid);
        Map<String,Object> back = new HashMap<>();
        back.put("teachername",teacher.getTeacherName());
        return back;
    }

    @ResponseBody
    @RequestMapping("/delproblem")
    public  Map<String,Object> delproblem(@RequestParam("problemid")Integer problemid){
        Map<String,Object> back = new HashMap<>();
        Integer flag = problemsService.delproblem(problemid);
        if (flag > 0){
            code = CodeMsg.Code_SUCCESS;
        }else {
            code = CodeMsg.Code_ERROR;
        }
        back.put("code",code);
        return back;
    }


    @ResponseBody
    @RequestMapping("/addproblem")
    public Map<String,Object> addproblem(@RequestBody Map map){
        Map<String,Object> back = new HashMap<>();
        Problems problems = new Problems();
        problems.setStuId((Integer) map.get("stuid"));
        problems.setTeacherId((Integer) map.get("teacherid"));
        problems.setProblemTitle((String) map.get("title"));
        problems.setProblemText((String) map.get("text"));
        Integer flag = problemsService.addproblem(problems);
        if (flag > 0){
            code = CodeMsg.Code_SUCCESS;
        }else {
            code = CodeMsg.Code_ERROR;
        }
        back.put("code",code);
        return back;
    }

    @ResponseBody
    @RequestMapping("/getmyproblemsbyteacherid")
    public Map<String,Object> getmyproblemsbyteacherid(@RequestParam("teacherid")Integer teacherid,@RequestParam("CurrentpageNum")Integer CurrentpageNum){
        Map<String,Object> back = new HashMap<>();
        Integer count = problemsService.getproblemsnumbyteacherid(teacherid);
        if (count > 0){
            PageHelper.startPage(CurrentpageNum,pageSize);
            List<Problems> problems =  problemsService.getmyproblemsbyteacherid(teacherid);
            if (problems == null){
                code = CodeMsg.Code_ERROR;
            }else {
                code = CodeMsg.Code_SUCCESS;
                back.put("count",count);
                back.put("problems",problems);
            }
        }else {
            code = CodeMsg.Code_NOTEXIST;
        }
        back.put("code",code);
        return back;
    }

}
