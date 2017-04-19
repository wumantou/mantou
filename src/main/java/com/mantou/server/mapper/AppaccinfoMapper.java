package com.mantou.server.mapper;

import com.mantou.server.entity.wechat.AppaccinfoVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by wuweiliang on 2017/4/19.
 */
@Mapper
public interface AppaccinfoMapper {
    AppaccinfoVO getAppaccinfo(String id);
}
