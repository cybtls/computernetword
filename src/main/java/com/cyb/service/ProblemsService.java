package com.cyb.service;

import com.cyb.pojo.Problems;

import java.util.List;

public interface ProblemsService {
    List<Problems> getmyproblems(Integer stuid);

    Integer delproblem(Integer problemid);

    Integer addproblem(Problems problems);

    Integer getproblemsnumbyteacherid(Integer teacherid);

    List<Problems> getmyproblemsbyteacherid(Integer teacherid);
}
