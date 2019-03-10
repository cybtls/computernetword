package com.cyb.mapper;

import com.cyb.pojo.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryMapper {
    List<Category> getallcategory();

    Integer queryone(@Param("categoryname") String categoryname);

    Integer addcategory(@Param("categoryname") String categoryname);

    Integer delcategory(@Param("categoryid") Integer categoryid);
}
