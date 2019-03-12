package com.cyb.service;

import com.cyb.pojo.Resources;

import java.util.List;

public interface ResourcesService {
    Integer addresource(Resources resources);

    List<Resources> getresources(Integer categoryid, String resourcename);

    Integer getresourcesnum(Integer categoryid, String resourcename);

    Resources getresourcebyid(Integer resid);

    Integer delresources(Integer resid);
}
