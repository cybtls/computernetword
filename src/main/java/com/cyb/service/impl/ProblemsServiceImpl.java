package com.cyb.service.impl;

import com.cyb.mapper.ProblemsMapper;
import com.cyb.pojo.Problems;
import com.cyb.service.ProblemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("problemsService")
public class ProblemsServiceImpl implements ProblemsService {

    @Autowired
    private ProblemsMapper problemsMapper;

    @Override
    public List<Problems> getmyproblems(Integer stuid) {
        return problemsMapper.getmyproblems(stuid);
    }

    @Override
    public Integer delproblem(Integer problemid) {
        return problemsMapper.delproblem(problemid);
    }

    @Override
    public Integer addproblem(Problems problems) {
        return problemsMapper.addproblem(problems);
    }

    @Override
    public Integer getproblemsnumbyteacherid(Integer teacherid) {
        return problemsMapper.getproblemsnumbyteacherid(teacherid);
    }

    @Override
    public List<Problems> getmyproblemsbyteacherid(Integer teacherid) {
        return problemsMapper.getmyproblemsbyteacherid(teacherid);
    }
}
