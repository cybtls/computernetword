package com.cyb.controller;

import com.cyb.codemsg.CodeMsg;
import com.cyb.pojo.Score;
import com.cyb.service.ScoreService;
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
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    private Integer code;

    @ResponseBody
    @RequestMapping("/getstuscore")
    public Map<String,Object> getstuscore(@RequestParam("stuid")Integer stuid){
        Map<String,Object> back = new HashMap<>();
        List<Score> scores = scoreService.getstuscore(stuid);
        back.put("scores",scores);
        return back;
    }

    @ResponseBody
    @RequestMapping("/addscore")
    public Map<String,Object> addscore(@RequestBody Map map ){
        Integer stuid = (Integer) map.get("stuid");
        Integer courseid = (Integer) map.get("courseid");
        Integer score = (Integer) map.get("score");
        Map<String,Object> back = new HashMap<>();
        Integer flag = scoreService.queryscore(stuid,courseid);
        if (flag > 0){
            code = CodeMsg.Code_EXIST;
        }else {
            Integer sign = scoreService.addscore(stuid,courseid,score);
            if (sign > 0){
                code = CodeMsg.Code_SUCCESS;
            }else {
                code = CodeMsg.Code_ERROR;
            }
        }
        back.put("code",code);
        return back;
    }
}
