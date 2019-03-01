package com.cyb.service;

import com.cyb.pojo.Score;

import java.util.List;

public interface ScoreService {
    List<Score> getmyscore(Integer stuId);

    Integer getcount(Integer stuId,String coursename);

    List<Score> getmyscorebyname(Integer stuId, String coursename);
}
