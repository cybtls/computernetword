package com.cyb.service.impl;

import com.cyb.mapper.AdminMapper;
import com.cyb.pojo.Admin;
import com.cyb.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin adminlogin(String adminaccount, String adminpassword) {
        Admin admin = adminMapper.adminlogin(adminaccount,adminpassword);
        return admin;
    }

    @Override
    public Integer updatemyinfo(Admin admin) {
        Integer flag = adminMapper.updatemyinfo(admin);
        return flag;
    }
}
