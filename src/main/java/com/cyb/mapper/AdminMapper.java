package com.cyb.mapper;

import com.cyb.pojo.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper {
    Admin adminlogin(@Param("adminaccount") String adminaccount, @Param("adminpassword") String adminpassword);

    Integer updatemyinfo(Admin admin);
}
