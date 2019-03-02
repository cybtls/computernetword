package com.cyb.mapper;

import com.cyb.pojo.Test;

import java.util.List;

public interface TestMapper {
    List<Test> get();

    void add();
}
