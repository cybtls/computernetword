package com.cyb.service;

import com.cyb.pojo.Admin;

public interface AdminService {
    Admin adminlogin(String adminaccount, String adminpassword);

    Integer updatemyinfo(Admin admin);
}
