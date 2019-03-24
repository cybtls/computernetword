package com.cyb.controller;

import com.cyb.codemsg.CodeMsg;
import com.cyb.pojo.Comment;
import com.cyb.service.CommentService;
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
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    private Integer pageSize = 7;

    private Integer code;

    @ResponseBody
    @RequestMapping("/getcomment")
    public Map<String,Object> getcomment(@RequestParam("commentCurrentpageNum")Integer commentcurrentpagenum,
                                         @RequestParam("postid")Integer postid){
        Map<String,Object> back = new HashMap<>();
        Integer count = commentService.getcommentnum(postid);
        if (count == 0){
            code = CodeMsg.Code_NOTEXIST;
        }else {
            PageHelper.startPage(commentcurrentpagenum,pageSize);
            List<Comment> comments = commentService.getcomment(postid);
            if (comments != null){
                code = CodeMsg.Code_SUCCESS;
                back.put("comments",comments);
                back.put("count",count);
            }else {
                code = CodeMsg.Code_ERROR;
            }
        }
        back.put("code",code);
        return back;
    }


    @ResponseBody
    @RequestMapping("/addcomment")
    public Map<String,Object> addcomment(@RequestBody Map map){
        Map<String,Object> back = new HashMap<>();
        Comment comment = new Comment();
        comment.setCommentPresenterid((Integer) map.get("id"));
        comment.setCommentPresentername((String) map.get("name"));
        comment.setCommentPresentertype((Integer) map.get("type"));
        comment.setPostId((Integer) map.get("postid"));
        comment.setCommentText((String) map.get("comment"));
        Integer flag = commentService.addcomment(comment);
        if (flag > 0){
            code = CodeMsg.Code_SUCCESS;
        }else {
            code = CodeMsg.Code_ERROR;
        }
        back.put("code",code);
        return back;
    }


    @ResponseBody
    @RequestMapping("/delcomment")
    public Map<String,Object> delcomment(@RequestBody Map map){
        Map<String,Object> back = new HashMap<>();
        Integer commentid = (Integer)map.get("commentid");
        Integer flag = commentService.delcomment(commentid);
        if(flag > 0){
            code = CodeMsg.Code_SUCCESS;
        }else {
            code = CodeMsg.Code_ERROR;
        }
        back.put("code",code);
        return back;
    }
}
