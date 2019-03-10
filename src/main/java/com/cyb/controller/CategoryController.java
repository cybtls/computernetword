package com.cyb.controller;


import com.cyb.codemsg.CodeMsg;
import com.cyb.pojo.Category;
import com.cyb.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    private Integer code;

    @ResponseBody
    @RequestMapping("/getallcategory")
    public Map<String,Object> getallcategory(){
        List<Category> categories = categoryService.getallcategory();
        Map<String,Object> back = new HashMap<>();
        if (categories == null){
            code = CodeMsg.Code_ERROR;
        }else {
            code = CodeMsg.Code_SUCCESS;
            back.put("categories",categories);
        }
        back.put("code",code);
        return back;
    }

    @ResponseBody
    @RequestMapping("/addcategory")
    public Map<String,Object> addcategory(@RequestParam("categoryname")String categoryname){
        Integer count = categoryService.queryone(categoryname);
        Map<String,Object> back = new HashMap<>();
        if (count > 0){
            code = CodeMsg.Code_EXIST;
        }else {
            Integer flag = categoryService.addcategory(categoryname);
            if (flag > 0){
                code = CodeMsg.Code_SUCCESS;
            }else {
                code = CodeMsg.Code_ERROR;
            }
        }
        back.put("code",code);
        return back;
    }

    @ResponseBody
    @RequestMapping("/delcategory")
    public Map<String,Object> delcategory(@RequestParam("categoryid")Integer categoryid){
        Map<String,Object> back = new HashMap<>();
        Integer flag = categoryService.delcategory(categoryid);
        if (flag > 0){
            code = CodeMsg.Code_SUCCESS;
        }else {
            code = CodeMsg.Code_ERROR;
        }
        back.put("code",code);
        return back;
    }
}
