package com.cyb.service;

import com.cyb.pojo.Post;

import java.util.List;

public interface PostService {
    Integer getpostnum(String posttitleorauthor);

    List<Post> getpost(String posttitleorauthor);

    Integer addpostfire(Integer postid);

    Integer addpost(Post post);

    Integer delpost(Integer postid);

    Integer getmypostnum(Integer id, Integer type);

    List<Post> getmypost(Integer id, Integer type);
}
