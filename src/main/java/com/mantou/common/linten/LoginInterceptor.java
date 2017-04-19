package com.mantou.common.linten;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wuweiliang on 2017/4/19.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.warn("=====================开始拦截====================");
        String uri = request.getRequestURL().toString();
        logger.warn("=====================:"+uri);
        logger.warn("=====================拦截结束====================");
        return true;
    }
}
