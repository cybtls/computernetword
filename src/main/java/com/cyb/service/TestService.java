package com.cyb.service;

import com.cyb.pojo.Test;

import java.util.List;

public interface TestService {
    List<Test> get();

    List<Test> add();
}
