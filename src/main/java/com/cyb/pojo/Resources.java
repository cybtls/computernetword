package com.cyb.pojo;


import java.sql.Timestamp;

/**
 * 教学资源表
 */
public class Resources {

    private Integer resId;

    private String resName;

    private Integer resCategoryid;

    private String resPath;

    private Integer resDownnum;

    private String resUploader;

    private Timestamp resCreatedate;

    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public Integer getResCategoryid() {
        return resCategoryid;
    }

    public void setResCategoryid(Integer resCategoryid) {
        this.resCategoryid = resCategoryid;
    }

    public String getResPath() {
        return resPath;
    }

    public void setResPath(String resPath) {
        this.resPath = resPath;
    }

    public Integer getResDownnum() {
        return resDownnum;
    }

    public void setResDownnum(Integer resDownnum) {
        this.resDownnum = resDownnum;
    }

    public String getResUploader() {
        return resUploader;
    }

    public void setResUploader(String resUploader) {
        this.resUploader = resUploader;
    }

    public Timestamp getResCreatedate() {
        return resCreatedate;
    }

    public void setResCreatedate(Timestamp resCreatedate) {
        this.resCreatedate = resCreatedate;
    }
}
