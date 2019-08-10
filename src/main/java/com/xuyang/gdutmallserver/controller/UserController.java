package com.xuyang.gdutmallserver.controller;

import com.xuyang.gdutmallserver.domain.*;
import com.xuyang.gdutmallserver.model.MessageInfo;
import com.xuyang.gdutmallserver.model.UserInfo;
import com.xuyang.gdutmallserver.service.MessageService;
import com.xuyang.gdutmallserver.service.UserService;
import com.xuyang.gdutmallserver.utils.DateUtil;
import com.xuyang.gdutmallserver.utils.push.PushSender;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

import static com.xuyang.gdutmallserver.controller.BaseController.DEFAULT_JSON_CONTENT_TYPE;

@Controller
@RequestMapping(produces = {DEFAULT_JSON_CONTENT_TYPE}, value = {"/userCenter"})
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;
    private static final String DEFAULT_VERIFYCODE = "123456";

    @RequestMapping(value = {"/login"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public BaseResp<UserInfo> loginByPwd(@RequestBody LoginReq req) {
        BaseResp resp = new BaseResp();

        String mobile = req.getMobile();
        String pwd = req.getPwd();

        if (StringUtils.isEmpty(mobile)) {
            resp.setStatus(-1);
            resp.setMessage("手机号码为空");
            return resp;
        }

        if (StringUtils.isEmpty(pwd)) {
            resp.setStatus(-1);
            resp.setMessage("密码为空");
            return resp;
        }

        UserInfo userInfo = this.userService.getUserByMobile(mobile);
        if (userInfo == null) {
            resp.setStatus(-1);
            resp.setMessage("用户不存在");
            return resp;
        }
        if (!pwd.equals(userInfo.getUserPwd())) {
            resp.setStatus(-1);
            resp.setMessage("密码错误");
            return resp;
        }

        userInfo.setPushId(req.getPushId());
        this.userService.modifyUser(userInfo);
        sendMessage(userInfo.getId(), req.getPushId());

        userInfo.setUserPwd(null);
        resp.setStatus(0);
        resp.setMessage("登录成功");
        resp.setData(userInfo);
        return resp;
    }

    private void sendMessage(Integer userId, String pushId) {
        String curTime = DateUtil.format(new Date(), DateUtil.FORMAT_LONG_NEW);
        MessageInfo msg = new MessageInfo();
        msg.setMsgIcon("http://osea2fxp7.bkt.clouddn.com/108x108.png");
        msg.setMsgTitle("登录成功");
        msg.setMsgContent("恭喜您登录成功");
        msg.setMsgTime(curTime);
        msg.setUserId(userId);
        this.messageService.addMessage(msg);

        PushSender.sendLoginEvent(pushId);
    }

    @RequestMapping(value = {"/forgetPwd"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public BaseResp forgetPwd(@RequestBody ForgetPwdReq req) {
        BaseResp resp = new BaseResp();

        String mobile = req.getMobile();
        String verifyCode = req.getVerifyCode();

        if (StringUtils.isEmpty(mobile)) {
            resp.setStatus(-1);
            resp.setMessage("手机号码为空");
            return resp;
        }

        if (StringUtils.isEmpty(verifyCode)) {
            resp.setStatus(-1);
            resp.setMessage("验证码为空");
            return resp;
        }

        UserInfo userInfo = this.userService.getUserByMobile(mobile);
        if (userInfo == null) {
            resp.setStatus(-1);
            resp.setMessage("用户不存在");
            return resp;
        }

        if (!DEFAULT_VERIFYCODE.equals(verifyCode)) {
            resp.setStatus(-1);
            resp.setMessage("验证码错误");
            return resp;
        }

        resp.setStatus(0);
        resp.setMessage("验证成功");
        return resp;
    }

    @RequestMapping(value = {"/resetPwd"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public BaseResp<String> resetPwd(@RequestBody ModifyPwdReq req) {
        BaseResp resp = new BaseResp();

        String mobile = req.getMobile();
        String pwd = req.getPwd();

        if (StringUtils.isEmpty(mobile)) {
            resp.setStatus(-1);
            resp.setMessage("手机号码为空");
            return resp;
        }

        if (StringUtils.isEmpty(pwd)) {
            resp.setStatus(-1);
            resp.setMessage("密码为空");
            return resp;
        }

        UserInfo userInfo = this.userService.getUserByMobile(mobile);
        if (userInfo == null) {
            resp.setStatus(-1);
            resp.setMessage("用户不存在");
            return resp;
        }

        userInfo.setUserPwd(pwd);
        int result = this.userService.modifyUser(userInfo);

        if (result > 0) {
            resp.setStatus(0);
            resp.setMessage("密码修改成功");
            return resp;
        }
        resp.setStatus(-1);
        resp.setMessage("密码修改失败");
        return resp;
    }

    @RequestMapping(value = {"/register"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public BaseResp register(@RequestBody RegisterReq req) {
        BaseResp resp = new BaseResp();

        String mobile = req.getMobile();
        String verifyCode = req.getVerifyCode();

        UserInfo userInfo = this.userService.getUserByMobile(mobile);
        if (userInfo != null) {
            resp.setStatus(-1);
            resp.setMessage("账号已被注册");
            return resp;
        }

        if (!DEFAULT_VERIFYCODE.equals(verifyCode)) {
            resp.setStatus(-1);
            resp.setMessage("验证码错误");
            return resp;
        }

        userInfo = new UserInfo();
        userInfo.setUserMobile(mobile);
        userInfo.setUserName(mobile);
        userInfo.setUserPwd(req.getPwd());
        this.userService.addUser(userInfo);

        resp.setStatus(0);

        return resp;
    }

    @RequestMapping(value = {"/editUser"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public BaseResp<UserInfo> editUser(@RequestBody EditUserReq req) {
        BaseResp resp = new BaseResp();

        UserInfo userInfo = this.userService.getUserById(Integer.valueOf(this.request.getHeader("token")).intValue());
        if (userInfo == null) {
            resp.setStatus(-1);
            resp.setMessage("用户不存在");
            return resp;
        }

        userInfo.setId(Integer.valueOf(this.request.getHeader("token")));
        userInfo.setUserName(req.getUserName());
        userInfo.setUserIcon(req.getUserIcon());
        userInfo.setUserGender(req.getGender());
        userInfo.setUserSign(req.getSign());
        userInfo.setUserPwd(null);

        this.userService.modifyUser(userInfo);

        resp.setStatus(0);
        resp.setData(userInfo);
        return resp;
    }
}
