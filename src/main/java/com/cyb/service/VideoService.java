package com.cyb.service;

import com.cyb.pojo.Video;

import java.util.List;

public interface VideoService {
    Integer addvideo(Video video);

    Integer getvideonum(Integer categoryid, String videoname);

    List<Video> getvideo(Integer categoryid, String videoname);

    Integer getvideobyname(String videoname);

    Integer delvideo(Integer videoid);

    Integer addviewingtimes(Integer videoid);
}
