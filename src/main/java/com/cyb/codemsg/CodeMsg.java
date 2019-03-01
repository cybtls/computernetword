package com.cyb.codemsg;

public class CodeMsg {
    /**
     * 调用接口成功
     */
    public static Integer Code_SUCCESS = 200;
    public static String Code_SUCCESS_MSG = "调用接口成功";


    /**
     * 调用接口失败
     */
    public static Integer Code_ERROR = 400;
    public static String Code_ERROE_MSG = "调用接口失败";


    /**
     *  账号存在 登录成功
     */
    public static Integer Code_EXIST = 201;
    public static String Code_EXIST_MSG = "账号存在 登录成功";

    /**
     * 账号状态为封禁
     */
    public static Integer Code_CLOSURE = 202;
    public static String Code_CLOSURE_MSG = "账号状态为封禁";


    /**
     * 账号不存在，登录失败
     */
    public static Integer Code_NOTEXIST =  204;
    public static String Code_NOTEXIST_MSG = "账号不存在，登录失败";

}
