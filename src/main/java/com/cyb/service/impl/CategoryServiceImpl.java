package com.cyb.service.impl;

import com.cyb.mapper.CategoryMapper;
import com.cyb.pojo.Category;
import com.cyb.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;


    @Override
    public List<Category> getallcategory() {
        return categoryMapper.getallcategory();
    }

    @Override
    public Integer queryone(String categoryname) {
        return categoryMapper.queryone(categoryname);
    }

    @Override
    public Integer addcategory(String categoryname) {
        return categoryMapper.addcategory(categoryname);
    }

    @Override
    public Integer delcategory(Integer categoryid) {
        return categoryMapper.delcategory(categoryid);
    }
}
