package com.cyb.mapper;

import com.cyb.pojo.Video;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoMapper {
    Integer addvideo(Video video);

    Integer getvideonum(@Param("categoryid") Integer categoryid, @Param("videoname") String videoname);

    List<Video> getvideo(@Param("categoryid") Integer categoryid, @Param("videoname") String videoname);

    Integer getvideobyname(@Param("videoname") String videoname);

    Integer delvideo(@Param("videoid") Integer videoid);

    Integer addviewingtimes(@Param("videoid") Integer videoid);
}
