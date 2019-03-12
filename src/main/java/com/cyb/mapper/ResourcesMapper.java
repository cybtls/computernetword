package com.cyb.mapper;

import com.cyb.pojo.Resources;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResourcesMapper {
    Integer addresource(Resources resources);

    List<Resources> getresources(@Param("categoryid") Integer categoryid, @Param("resourcename") String resourcename);

    Integer getresourcesnum(@Param("categoryid") Integer categoryid, @Param("resourcename") String resourcename);

    Resources getresourcebyid(@Param("resid") Integer resid);

    Integer delresources(@Param("resid") Integer resid);
}
