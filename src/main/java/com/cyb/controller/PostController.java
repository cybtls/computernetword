package com.cyb.controller;

import com.cyb.codemsg.CodeMsg;
import com.cyb.pojo.Post;
import com.cyb.service.PostService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    private Integer pageSize = 10;

    private Integer code;

    @ResponseBody
    @RequestMapping("/getpost")
    public Map<String,Object> getpost(@RequestParam("currentpagenum")Integer currentpagenum,@RequestParam(value = "posttitleorauthor",required = false)String posttitleorauthor){
        Map<String,Object> back = new HashMap<>();
        Integer count = postService.getpostnum(posttitleorauthor);
        if (count > 0){
            PageHelper.startPage(currentpagenum, pageSize);
            List<Post> posts = postService.getpost(posttitleorauthor);
            if(posts == null){
                code = CodeMsg.Code_ERROR;
            }else {
                back.put("posts",posts);
                code = CodeMsg.Code_SUCCESS;
            }
        }else {
            code = CodeMsg.Code_NOTEXIST;
        }
        back.put("code",code);
        return back;
    }


    @ResponseBody
    @RequestMapping("/addpostfire")
    public Map<String,Object> addpostfire(@RequestParam("postid")Integer postid){
        Map<String,Object> back = new HashMap<>();
        Integer flag = postService.addpostfire(postid);
        return back;
    }

}
