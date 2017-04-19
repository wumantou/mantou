package com.mantou.server.entity.wechat;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by wuweiliang on 2017/4/19.
 */
@Setter
@Getter
public class WechatUser {
    private String city;//
    private String country;//
    private int groupId;//
    private String language;//
    private String headimgurl;//
    private String nickname;//
    private String openid;//
    private String province;//
    private String remark;//
    private int sex;//
    private int subscribe;//
    private long subscribetime;//
    private String unionid;//

    private String userInfoId;
    private String userBindWechatId;
    private String appid;
    private String createIp;
}
