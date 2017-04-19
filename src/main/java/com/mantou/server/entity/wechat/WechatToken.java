package com.mantou.server.entity.wechat;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by wuweiliang on 2017/4/19.
 */
@Getter
@Setter
public class WechatToken {
    private String access_token;
    private long expires_in;

}

