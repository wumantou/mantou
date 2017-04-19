package com.mantou.server.controller;

import com.mantou.server.entity.Result;
import com.mantou.server.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wuweiliang on 2017/4/18.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/show/{id}",method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Result show(HttpServletRequest request,@PathVariable("id") String id){
        logger.warn("start!");
        Result result = null;
        result = userService.show(id);
        return result;
    }

}
