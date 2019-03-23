package com.cyb.service.impl;

import com.cyb.mapper.PostMapper;
import com.cyb.pojo.Post;
import com.cyb.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("postService")
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;

    @Override
    public Integer getpostnum(String posttitleorauthor) {
        return postMapper.getpostnum(posttitleorauthor);
    }

    @Override
    public List<Post> getpost(String posttitleorauthor) {
        return postMapper.getpost(posttitleorauthor);
    }

    @Override
    public Integer addpostfire(Integer postid) {
        return postMapper.addpostfire(postid);
    }
}
