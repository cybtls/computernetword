package com.cyb.service;

import com.cyb.pojo.Comment;

import java.util.List;

public interface CommentService {
    Integer getcommentnum(Integer postid);

    List<Comment> getcomment(Integer postid);

    Integer addcomment(Comment comment);

    Integer delcomment(Integer commentid);
}
