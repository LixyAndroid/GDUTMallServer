package com.xuyang.gdutmallserver.controller;

import com.xuyang.gdutmallserver.domain.BaseResp;
import com.qiniu.util.Auth;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.xuyang.gdutmallserver.controller.BaseController.DEFAULT_JSON_CONTENT_TYPE;

@Controller
@RequestMapping(produces = {DEFAULT_JSON_CONTENT_TYPE}, value = {"/common"})
public class UploadController extends BaseController {
    @RequestMapping(value = {"/getUploadToken"}, method = {RequestMethod.POST})
    @ResponseBody
    public BaseResp<String> getUploadToken() {
        String accessKey = "ZTs1N6_S4JC2_F9PleDlNWyeo32fqBkJaKYi1Y9m";
        String secretKey = "aWgCom1HWsmVSXm7l4_N7hLdEl86_1gMyVD62AXz";
        String bucket = "mallicon";
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        System.out.println(upToken);

        BaseResp resp = new BaseResp();
        resp.setStatus(0);
        resp.setMessage("");
        resp.setData(upToken);

        return resp;
    }
}

