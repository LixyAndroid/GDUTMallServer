package com.xuyang.gdutmallserver.controller;

import com.xuyang.gdutmallserver.domain.BaseResp;
import com.xuyang.gdutmallserver.domain.GetSignReq;
import com.xuyang.gdutmallserver.utils.YuanFenConverter;
import com.xuyang.gdutmallserver.utils.pay.PaySignUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.xuyang.gdutmallserver.controller.BaseController.DEFAULT_JSON_CONTENT_TYPE;

@Controller
@RequestMapping(produces = {DEFAULT_JSON_CONTENT_TYPE}, value = {"/pay"})
public class PayController extends BaseController {
    @RequestMapping(value = {"/getPaySign"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public BaseResp<String> getPaySign(@RequestBody GetSignReq req) {
        BaseResp resp = new BaseResp();
        resp.setStatus(0);
        resp.setMessage("");
        try {
            resp.setData(PaySignUtils.sign(YuanFenConverter.changeF2Y(String.valueOf(req.getTotalPrice())), req.getOrderId() + ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        return resp;
    }
}
