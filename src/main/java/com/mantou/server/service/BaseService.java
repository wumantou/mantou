package com.mantou.server.service;

import com.mantou.server.entity.Result;

import java.util.UUID;

/**
 * Created by wuweiliang on 2017/4/18.
 */
public abstract class BaseService {

    public final String RESULT_MESSAGE_SUCCESS="200";  //成功
    public final String RESULT_MESSAGE_ERROR="0"; //失败

    public Result result(String errcode, String errmsg, Object data, int total, int per_page, int current_page, int last_page) {
        return new Result(errcode, errmsg, data, total, per_page, current_page, last_page);
    }

    public Result error(String errmsg){
        return result(RESULT_MESSAGE_ERROR,errmsg,null,0,0,0,0);
    }

    public Result success(String errcode, String errmsg, Object data, int total, int per_page, int current_page, int last_page){
        return result(RESULT_MESSAGE_SUCCESS,errmsg, data, total, per_page, current_page, last_page);
    }

    public Result success(String errmsg, Object data){
        return result(RESULT_MESSAGE_SUCCESS,errmsg, data,0,0,0,0);
    }

    public Result success(String errmsg){
        return result(RESULT_MESSAGE_SUCCESS,errmsg, null,0,0,0,0);
    }

    public static String getUUID() {
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid;
    }

}
