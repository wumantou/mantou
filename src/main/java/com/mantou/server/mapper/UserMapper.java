package com.mantou.server.mapper;

import com.mantou.server.entity.user.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by wuweiliang on 2017/4/18.
 */
@Mapper
public interface UserMapper {
    User show(String id);
}
