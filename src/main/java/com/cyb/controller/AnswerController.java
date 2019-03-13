package com.cyb.controller;

import com.cyb.codemsg.CodeMsg;
import com.cyb.pojo.Answer;
import com.cyb.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    private Integer code;

    @ResponseBody
    @RequestMapping("/addanswer")
    public Map<String,Object> addanswer(@RequestBody Map map){
        Map<String,Object> back = new HashMap<>();
        Answer answer  = new Answer();
        answer.setProblemId((Integer) map.get("problemid"));
        answer.setAnswerText((String) map.get("text"));
        Integer flag = answerService.addanswer(answer);
        if (flag > 0){
            code = CodeMsg.Code_SUCCESS;
        }else {
            code = CodeMsg.Code_ERROR;
        }
        back.put("code",code);
        return back;
    }
}
