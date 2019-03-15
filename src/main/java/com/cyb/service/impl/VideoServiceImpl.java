package com.cyb.service.impl;

import com.cyb.mapper.VideoMapper;
import com.cyb.pojo.Video;
import com.cyb.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("videoService")
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public Integer addvideo(Video video) {
        return videoMapper.addvideo(video);
    }

    @Override
    public Integer getvideonum(Integer categoryid, String videoname) {
        return videoMapper.getvideonum(categoryid,videoname);
    }

    @Override
    public List<Video> getvideo(Integer categoryid, String videoname) {
        return videoMapper.getvideo(categoryid,videoname);
    }

    @Override
    public Integer getvideobyname(String videoname) {
        return videoMapper.getvideobyname(videoname);
    }

    @Override
    public Integer delvideo(Integer videoid) {
        return videoMapper.delvideo(videoid);
    }

    @Override
    public Integer addviewingtimes(Integer videoid) {
        return videoMapper.addviewingtimes(videoid);
    }
}
