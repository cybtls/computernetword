package com.cyb.pojo;

public class Comment {

    private Integer commentId;

    private String  commentText;

    private Integer postId;

    private Integer commentPresenterid;

    private Integer commentPresentertype;

    private String commentPresentername;

    private Integer commentStatus;

    private String commentTime;

    private Post post;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getCommentPresenterid() {
        return commentPresenterid;
    }

    public void setCommentPresenterid(Integer commentPresenterid) {
        this.commentPresenterid = commentPresenterid;
    }

    public Integer getCommentPresentertype() {
        return commentPresentertype;
    }

    public void setCommentPresentertype(Integer commentPresentertype) {
        this.commentPresentertype = commentPresentertype;
    }

    public String getCommentPresentername() {
        return commentPresentername;
    }

    public void setCommentPresentername(String commentPresentername) {
        this.commentPresentername = commentPresentername;
    }

    public Integer getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(Integer commentStatus) {
        this.commentStatus = commentStatus;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
