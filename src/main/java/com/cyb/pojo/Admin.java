package com.cyb.pojo;

import java.util.Date;

/**
 * 管理员表
 */
public class Admin {


    private Integer adminId;

    private String adminAccount;

    private String adminPassword;

    private String adminName;

    private Date adminCreatedate;

    private String adminEmail;

    //是否为超级管理员
    private Integer adminSuper;

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminAccount() {
        return adminAccount;
    }

    public void setAdminAccount(String adminAccount) {
        this.adminAccount = adminAccount;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public Date getAdminCreatedate() {
        return adminCreatedate;
    }

    public void setAdminCreatedate(Date adminCreatedate) {
        this.adminCreatedate = adminCreatedate;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public Integer getAdminSuper() {
        return adminSuper;
    }

    public void setAdminSuper(Integer adminSuper) {
        this.adminSuper = adminSuper;
    }
}
