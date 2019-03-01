package com.cyb.service.impl;

import com.cyb.mapper.ScoreMapper;
import com.cyb.pojo.Score;
import com.cyb.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("scoreService")
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;


    @Override
    public List<Score> getmyscore(Integer stuId) {
        List<Score> score = scoreMapper.getmyscore(stuId);
        return score;
    }

    @Override
    public Integer getcount(Integer stuId,String coursename) {
        return scoreMapper.getcount(stuId,coursename);
    }

    @Override
    public List<Score> getmyscorebyname(Integer stuId, String coursename) {
        return scoreMapper.getmyscorebyname(stuId,coursename);
    }
}
