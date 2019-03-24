package com.cyb.service.impl;

import com.cyb.mapper.CommentMapper;
import com.cyb.pojo.Comment;
import com.cyb.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Integer getcommentnum(Integer postid) {
        return commentMapper.getcommentnum(postid);
    }

    @Override
    public List<Comment> getcomment(Integer postid) {
        return commentMapper.getcomment(postid);
    }

    @Override
    public Integer addcomment(Comment comment) {
        return commentMapper.addcomment(comment);
    }

    @Override
    public Integer delcomment(Integer commentid) {
        return commentMapper.delcomment(commentid);
    }

}
