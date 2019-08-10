package com.xuyang.gdutmallserver.domain;

public class SendVerifyCodeReq extends BaseReq {
    private String mobile;

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}

