package com.xuyang.gdutmallserver.service.impl;

import com.xuyang.gdutmallserver.mapper.UserInfoMapper;
import com.xuyang.gdutmallserver.model.UserInfo;
import com.xuyang.gdutmallserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    public UserInfo getUserById(int id) {
        return this.userInfoMapper.selectByPrimaryKey(Integer.valueOf(id));
    }

    public UserInfo getUserByMobile(String mobile) {
        return this.userInfoMapper.selectByMobile(mobile);
    }

    public void addUser(UserInfo userInfo) {
        this.userInfoMapper.insert(userInfo);
    }

    public int modifyUser(UserInfo userInfo) {
        return this.userInfoMapper.updateByPrimaryKeySelective(userInfo);
    }
}