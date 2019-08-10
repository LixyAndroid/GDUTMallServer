package com.xuyang.gdutmallserver.service;

import com.xuyang.gdutmallserver.model.VerifyCodeModel;

public interface SmsService {
    boolean sendVerifyCode(VerifyCodeModel paramVerifyCodeModel);

    String getVerifyCode(String paramString);
}