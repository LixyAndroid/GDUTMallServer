package com.xuyang.gdutmallserver.service;

import com.xuyang.gdutmallserver.model.UserInfo;

public interface UserService {
    UserInfo getUserById(int paramInt);

    UserInfo getUserByMobile(String paramString);

    void addUser(UserInfo paramUserInfo);

    int modifyUser(UserInfo paramUserInfo);
}
