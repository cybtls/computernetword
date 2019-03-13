package com.cyb.mapper;

import com.cyb.pojo.Problems;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProblemsMapper {
    List<Problems> getmyproblems(@Param("stuid") Integer stuid);

    Integer delproblem(@Param("problemid") Integer problemid);

    Integer addproblem(Problems problems);

    Integer getproblemsnumbyteacherid(@Param("teacherid") Integer teacherid);

    List<Problems> getmyproblemsbyteacherid(@Param("teacherid") Integer teacherid);
}
