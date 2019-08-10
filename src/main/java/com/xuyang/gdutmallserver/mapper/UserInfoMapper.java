package com.xuyang.gdutmallserver.mapper;

import com.xuyang.gdutmallserver.model.UserInfo;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer paramInteger);

    int insert(UserInfo paramUserInfo);

    int insertSelective(UserInfo paramUserInfo);

    UserInfo selectByPrimaryKey(Integer paramInteger);

    UserInfo selectByMobile(String paramString);

    int updateByPrimaryKeySelective(UserInfo paramUserInfo);

    int updateByPrimaryKey(UserInfo paramUserInfo);
}
