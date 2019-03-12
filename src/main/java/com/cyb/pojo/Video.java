package com.cyb.pojo;

public class Video {

    private Integer videoId;

    private String videoName;

    private Integer videoCategoryid;

    private String videoPath;

    private Integer videoViewingtimes;

    private String videoLastuploader;

    private String videoLastmodification;

    private Category category;

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public Integer getVideoCategoryid() {
        return videoCategoryid;
    }

    public void setVideoCategoryid(Integer videoCategoryid) {
        this.videoCategoryid = videoCategoryid;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public Integer getVideoViewingtimes() {
        return videoViewingtimes;
    }

    public void setVideoViewingtimes(Integer videoViewingtimes) {
        this.videoViewingtimes = videoViewingtimes;
    }

    public String getVideoLastuploader() {
        return videoLastuploader;
    }

    public void setVideoLastuploader(String videoLastuploader) {
        this.videoLastuploader = videoLastuploader;
    }

    public String getVideoLastmodification() {
        return videoLastmodification;
    }

    public void setVideoLastmodification(String videoLastmodification) {
        this.videoLastmodification = videoLastmodification;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
