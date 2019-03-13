package com.cyb.service.impl;

import com.cyb.mapper.AnswerMapper;
import com.cyb.pojo.Answer;
import com.cyb.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("answerService")
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerMapper answerMapper;

    @Override
    public Integer addanswer(Answer answer) {
        return answerMapper.addanswer(answer);
    }
}
