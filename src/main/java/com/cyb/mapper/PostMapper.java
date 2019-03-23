package com.cyb.mapper;

import com.cyb.pojo.Post;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PostMapper {
    Integer getpostnum(@Param("posttitleorauthor") String posttitleorauthor);

    List<Post> getpost(@Param("posttitleorauthor") String posttitleorauthor);

    Integer addpostfire(@Param("postid") Integer postid);
}
