package com.mantou.server.controller;

import com.mantou.server.entity.Result;

import java.util.UUID;

/**
 *  控制器基类
 *
 */
public abstract class BaseController {

    public final String RESULT_MESSAGE_ERROR="0"; //失败

    public Result error(String errmsg){
        return new Result(RESULT_MESSAGE_ERROR, errmsg, null, 0, 0, 0, 0);
    }
    /**
     * 生成32位编码
     *
     * @return string
     */
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid;
    }



}

