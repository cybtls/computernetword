package com.cyb.service;

import com.cyb.pojo.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getallcategory();

    Integer queryone(String categoryname);

    Integer addcategory(String categoryname);

    Integer delcategory(Integer categoryid);
}
