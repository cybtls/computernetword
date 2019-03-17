package com.cyb.mapper;

import com.cyb.pojo.Score;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScoreMapper {
    List<Score> getmyscore(@Param("stuId") Integer stuId);

    Integer getcount(@Param("stuId") Integer stuId, @Param("coursename") String coursename);

    List<Score> getmyscorebyname(@Param("stuId") Integer stuId, @Param("coursename") String coursename);

    List<Score> getstuscore(@Param("stuid") Integer stuid);

    Integer queryscore(@Param("stuid") Integer stuid, @Param("courseid") Integer courseid);

    Integer addscore(@Param("stuid") Integer stuid, @Param("courseid") Integer courseid, @Param("score") Integer score);
}
