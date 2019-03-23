package com.cyb.mapper;

import com.cyb.pojo.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {
    Integer getcommentnum(@Param("postid") Integer postid);

    List<Comment> getcomment(@Param("postid") Integer postid);

    Integer addcomment(Comment comment);
}
