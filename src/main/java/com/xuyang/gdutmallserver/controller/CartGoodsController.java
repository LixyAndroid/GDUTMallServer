package com.xuyang.gdutmallserver.controller;

import com.xuyang.gdutmallserver.common.InitAction;
import com.xuyang.gdutmallserver.domain.AddCartGoodsReq;
import com.xuyang.gdutmallserver.domain.BaseResp;
import com.xuyang.gdutmallserver.domain.DeleteCartGoodsReq;
import com.xuyang.gdutmallserver.domain.SubmitCartReq;
import com.xuyang.gdutmallserver.model.CartGoods;
import com.xuyang.gdutmallserver.model.OrderGoods;
import com.xuyang.gdutmallserver.model.OrderInfo;
import com.xuyang.gdutmallserver.model.ShipAddress;
import com.xuyang.gdutmallserver.service.CartGoodsService;
import com.xuyang.gdutmallserver.service.OrderService;
import com.xuyang.gdutmallserver.service.ShipAddressService;
import com.xuyang.gdutmallserver.utils.YuanFenConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.xuyang.gdutmallserver.controller.BaseController.DEFAULT_JSON_CONTENT_TYPE;

@Controller
@RequestMapping(produces = {DEFAULT_JSON_CONTENT_TYPE}, value = {"/cart"})
public class CartGoodsController extends BaseController {

    @Autowired
    private CartGoodsService cartGoodsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ShipAddressService shipAddressService;

    @RequestMapping(value = {"/getList"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public BaseResp<List<CartGoods>> getList() {
        BaseResp resp = new BaseResp();

        if ((this.request.getHeader("token") == null) || (this.request.getHeader("token").equals(""))) {
            resp.setStatus(0);
            resp.setMessage("未登录");
            return resp;
        }

        List list = this.cartGoodsService.getCartGoodsList(Integer.valueOf(this.request.getHeader("token")));
        if ((list == null) || (list.size() == 0)) {
            resp.setStatus(0);
            resp.setMessage("列表为空");
            return resp;
        }

        resp.setStatus(0);
        resp.setMessage("列表获取成功");
        resp.setData(list);
        return resp;
    }

    @RequestMapping(value = {"/add"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public BaseResp<Integer> addCartGoods(@RequestBody AddCartGoodsReq req) {
        BaseResp resp = new BaseResp();

        CartGoods cartGoods = new CartGoods();
        cartGoods.setGoodsId(req.getGoodsId());
        cartGoods.setUserId(Integer.valueOf(this.request.getHeader("token")));
        cartGoods.setGoodsIcon(req.getGoodsIcon());
        cartGoods.setGoodsDesc(req.getGoodsDesc());
        cartGoods.setGoodsPrice(req.getGoodsPrice());
        cartGoods.setGoodsCount(req.getGoodsCount());
        cartGoods.setGoodsSku(req.getGoodsSku());

        this.cartGoodsService.addCartGoods(cartGoods);
        resp.setMessage("添加购物车成功");
        resp.setData(Integer.valueOf(this.cartGoodsService.getCartGoodsList(Integer.valueOf(this.request.getHeader("token"))).size()));
        return resp;
    }

    @RequestMapping(value = {"/delete"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public BaseResp deleteCartGoods(@RequestBody DeleteCartGoodsReq req) {

        BaseResp resp = new BaseResp();

        this.cartGoodsService.deleteCartGoods(req.getCartIdList());
        resp.setStatus(0);
        resp.setMessage("添加购物车成功");

        return resp;
    }

    @RequestMapping(value = {"/submit"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public BaseResp<Integer> submitCart(@RequestBody SubmitCartReq req) {

        BaseResp resp = new BaseResp();

        int userId = Integer.valueOf(this.request.getHeader("token")).intValue();

        OrderInfo orderInfo = new OrderInfo();

        orderInfo.setUserId(Integer.valueOf(userId));
        try {

            orderInfo.setTotalPrice(YuanFenConverter.changeF2Y(req.getTotalPrice()));
        } catch (Exception e) {

            e.printStackTrace();
        }


        List<ShipAddress> shipList = this.shipAddressService.getShipAddress(Integer.valueOf(userId));

        if ((shipList == null) || (shipList.size() == 0))
            orderInfo.setShipId(Integer.valueOf(0));
        else {

            for (ShipAddress shipAddress : shipList) {

                if (shipAddress.getShipIsDefault().intValue() == 0) {

                    orderInfo.setShipId(shipAddress.getId());
                }
            }
        }

        orderInfo.setOrderStatus(Integer.valueOf(-1));

        orderInfo.setPayType(Integer.valueOf(0));

        int orderId = this.orderService.addOrder(orderInfo);

        List cartIdList = new ArrayList();

        for (OrderGoods orderGoods : req.getGoodsList()) {

            cartIdList.add(orderGoods.getId());

            orderGoods.setOrderId(Integer.valueOf(orderId));
            try {

                orderGoods.setGoodsPrice(YuanFenConverter.changeF2Y(orderGoods.getGoodsPrice()));
            } catch (Exception e) {

                e.printStackTrace();
            }

            this.orderService.addOrderGoods(orderGoods);
        }


        InitAction.cartIdMap.put(Integer.valueOf(orderId), cartIdList);

        resp.setStatus(0);
        resp.setMessage("购物车提交成功");
        resp.setData(Integer.valueOf(orderId));
        return resp;
    }
}