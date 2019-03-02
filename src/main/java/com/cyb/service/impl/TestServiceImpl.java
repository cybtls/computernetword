package com.cyb.service.impl;

import com.cyb.mapper.TestMapper;
import com.cyb.pojo.Test;
import com.cyb.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("testService")
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;


    @Override
    public List<Test> get() {
        return testMapper.get();
    }

    @Override
    public List<Test> add() {
        testMapper.add();
        return null;
    }
}
