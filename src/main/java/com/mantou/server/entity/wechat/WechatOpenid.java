package com.mantou.server.entity.wechat;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by wuweiliang on 2017/4/19.
 */
@Getter
@Setter
public class WechatOpenid {
    private String access_token;
    private String refresh_token;
    private String openid;
    private String scope;
    private long expires_in;
}
