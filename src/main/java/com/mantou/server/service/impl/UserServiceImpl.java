package com.mantou.server.service.impl;

import com.mantou.server.entity.Result;
import com.mantou.server.entity.user.User;
import com.mantou.server.mapper.UserMapper;
import com.mantou.server.service.BaseService;
import com.mantou.server.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wuweiliang on 2017/4/18.
 */
@Service("userService")
public class UserServiceImpl extends BaseService implements IUserService{
    @Autowired
    private UserMapper userMapper;
    @Override
    public Result show(String id) {
        String message = "ok";
        User user = userMapper.show(id);
        return success(message,user);
    }
}
