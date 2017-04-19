package com.mantou.common.util;

import com.alibaba.fastjson.JSON;
import com.mantou.common.Constants;
import com.mantou.server.entity.wechat.AppaccinfoVO;
import com.mantou.server.entity.wechat.WechatOpenid;
import com.mantou.server.entity.wechat.WechatToken;
import com.mantou.server.entity.wechat.WechatUser;
import com.mantou.server.mapper.AppaccinfoMapper;
import me.hao0.wechat.core.Wechat;
import me.hao0.wechat.core.WechatBuilder;
import me.hao0.wechat.model.material.MaterialUploadType;
import me.hao0.wechat.model.material.TempMaterial;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by wuweiliang on 2017/4/19.
 */

@Component
public class WechatUtil {
    private Logger logger = LoggerFactory.getLogger(WechatUtil.class);
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    private AppaccinfoMapper appaccinfoMapper;
    private Wechat wechat;
    private AppaccinfoVO appaccinfoVO;

    //
    private AppaccinfoVO getAppaccinfoVO(){
        if (ObjectUtils.isEmpty(appaccinfoVO)){
            appaccinfoVO = appaccinfoMapper.getAppaccinfo("16");
        }
        return appaccinfoVO;
    }

    private Wechat getWechat(){
        if (ObjectUtils.isEmpty(wechat)){
            wechat = WechatBuilder.newBuilder(getAppaccinfoVO().getAppid(), getAppaccinfoVO().getSecret()).build();
        }
        return wechat;
    }


    public String getAppId(){
        return getAppaccinfoVO().getAppid();
    }
    public String getAccessToken(){
        String accessToken = null;
        Object o = redisUtil.get(Constants.ACCESS_TOKEN_KEY);
        if (ObjectUtils.isEmpty(o)){
            String url = getAppaccinfoVO().getUrlToken();
            String param = "grant_type=client_credential&appid="+getAppaccinfoVO().getAppid()+"&secret="+getAppaccinfoVO().getSecret();
            String result = HttpClientUtil.sendGet(url,param);
            WechatToken wechatToken = null;
            if (!StringUtils.isEmpty(result)){
                if (result.contains("access_token")){
                    wechatToken = JSON.parseObject(result,WechatToken.class);
                } else {
                    logger.warn("==============异常开始=============");
                    logger.error("获取accesstoken异常："+result);
                    return "";
                }
            }
            accessToken = wechatToken.getAccess_token();
            redisUtil.set(Constants.ACCESS_TOKEN_KEY,wechatToken.getAccess_token(),wechatToken.getExpires_in());
        }else{
            accessToken = o.toString();
        }
        return  accessToken;
    }

    public String getOpenid(String code){
        String url = getAppaccinfoVO().getUrlOauthToken();
        String param = "appid="+getAppaccinfoVO().getAppid()+"&secret="+getAppaccinfoVO().getSecret()+"&code="+code+"&grant_type=authorization_code";
        String result = HttpClientUtil.sendGet(url,param);
        WechatOpenid wechatOpenid = null;
        if (!StringUtils.isEmpty(result)){
            if (result.contains("openid")){
                wechatOpenid = JSON.parseObject(result,WechatOpenid.class);
            } else {
                logger.warn("==============异常开始=============");
                logger.error("获取用户openid异常："+result);
                return "";
            }
        }
        return wechatOpenid.getOpenid();
    }

    public WechatUser getUser(String openid){
        //String url = this.getAppaccinfoVO().getUrlUserInfo();
        String url = "";
        String param = "access_token=" + getAccessToken() + "&openid=" + openid + "&lang=zh_CN";
        String result = HttpClientUtil.sendGet(url,param);
        logger.warn("=================="+result + "======================================");
        WechatUser wechatUser = null;
        if (!StringUtils.isEmpty(result)){
            if (result.contains("subscribe")){
                wechatUser = JSON.parseObject(result,WechatUser.class);
            } else {
                logger.warn("==============异常开始=============");
                logger.error("获取用户openid异常："+result);
                return null;
            }
        }
        return wechatUser;
    }

    public void downloadImage(String pathname,String mediaId){
        File output = new File(pathname);
        String accessAoken = getAccessToken();
        byte[] data = getWechat().material().downloadTemp(accessAoken, mediaId);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(output);
            out.write(data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null)
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

    }

//    public void testPath(String pathname){
//        File output = new File(pathname);
//        String accessAoken = getAccessToken();
//        byte[] data = getWechat().material().downloadTemp(accessAoken, mediaId);
//        FileOutputStream out = null;
//        try {
//            out = new FileOutputStream(output);
//            out.write(data);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (out != null)
//                try {
//                    out.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//        }
//    }

    public TempMaterial uploadImage(){
        TempMaterial t= getWechat().material().uploadTemp(getAccessToken(), MaterialUploadType.IMAGE, new File("D:\\123123.jpg"));
        return t;
    }

    public String getAuthurl(String redireurl){
        try {
            String authurl = getAppaccinfoVO().getUrlOauth() + "?appid=" + getAppId() +
                    "&redirect_uri=" + URLEncoder.encode(redireurl, "utf-8") +
                    "&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
            return authurl;
        } catch (Exception e){
            logger.warn("==============异常开始=============");
            logger.error("获取授权链接失败:",e);
            e.printStackTrace();
            return "";
        }
        /*try{
            String authurl = getAppaccinfoVO().getUrlOauth();
            String param = "appid=" + getAppId() +
                    "&redirect_uri=" + URLEncoder.encode(redireurl, "utf-8") +
                    "&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
            String result = HttpClientUtil.sendGet(authurl,param);
            logger.warn(result);
        } catch (Exception e){
            logger.warn("==============异常开始=============");
            logger.error("获取授权链接失败:",e);
            e.printStackTrace();
            return "";
        }
        return "";*/
    }
}
