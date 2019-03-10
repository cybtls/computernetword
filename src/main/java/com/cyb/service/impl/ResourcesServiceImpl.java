package com.cyb.service.impl;

import com.cyb.mapper.ResourcesMapper;
import com.cyb.pojo.Resources;
import com.cyb.service.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("resourcesService")
public class ResourcesServiceImpl implements ResourcesService {

    @Autowired
    private ResourcesMapper resourcesMapper;

    @Override
    public Integer addresource(Resources resources) {
        return resourcesMapper.addresource(resources);
    }

    @Override
    public List<Resources> getresources(Integer categoryid, String resourcename) {
        return resourcesMapper.getresources(categoryid,resourcename);
    }
}
