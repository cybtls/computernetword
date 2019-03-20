package com.cyb.pojo;

import java.util.List;

public class Post {

    private Integer postId;

    private String postTitle;

    private String postText;

    private Integer postPresenterid;

    private Integer postPresentertype;

    private String postPresentername;

    private String postTime;

    private Integer postStatus;

    private List<Comment> commentList;

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public Integer getPostPresenterid() {
        return postPresenterid;
    }

    public void setPostPresenterid(Integer postPresenterid) {
        this.postPresenterid = postPresenterid;
    }

    public Integer getPostPresentertype() {
        return postPresentertype;
    }

    public void setPostPresentertype(Integer postPresentertype) {
        this.postPresentertype = postPresentertype;
    }

    public String getPostPresentername() {
        return postPresentername;
    }

    public void setPostPresentername(String postPresentername) {
        this.postPresentername = postPresentername;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public Integer getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(Integer postStatus) {
        this.postStatus = postStatus;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}
