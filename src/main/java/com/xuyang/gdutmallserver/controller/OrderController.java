package com.xuyang.gdutmallserver.controller;

import com.xuyang.gdutmallserver.common.InitAction;
import com.xuyang.gdutmallserver.domain.*;
import com.xuyang.gdutmallserver.model.MessageInfo;
import com.xuyang.gdutmallserver.model.Order;
import com.xuyang.gdutmallserver.model.OrderGoods;
import com.xuyang.gdutmallserver.model.OrderInfo;
import com.xuyang.gdutmallserver.service.*;
import com.xuyang.gdutmallserver.utils.DateUtil;
import com.xuyang.gdutmallserver.utils.YuanFenConverter;
import com.xuyang.gdutmallserver.utils.push.PushSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.xuyang.gdutmallserver.controller.BaseController.DEFAULT_JSON_CONTENT_TYPE;

@Controller
@RequestMapping(produces = {DEFAULT_JSON_CONTENT_TYPE}, value = {"/order"})
public class OrderController extends BaseController {


    @Autowired
    private OrderService orderService;

    @Autowired
    private ShipAddressService shipAddressService;

    @Autowired
    private CartGoodsService cartGoodsService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/getOrderList"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public BaseResp<List<Order>> getOrderList(@RequestBody GetOrderListReq req) {
        BaseResp resp = new BaseResp();

        List<OrderInfo> list = this.orderService.getOrderList(Integer.valueOf(this.request.getHeader("token")), req.getOrderStatus());
        if ((list == null) || (list.size() == 0)) {
            resp.setStatus(0);
            resp.setMessage("列表为空");
            return resp;
        }

        List orderList = new ArrayList();
        for (OrderInfo info : list) {
            Order order = new Order();
            order.setId(info.getId());
            order.setOrderStatus(info.getOrderStatus());
            order.setPayType(info.getPayType());
            order.setTotalPrice(YuanFenConverter.changeY2F(Long.valueOf(info.getTotalPrice())));
            order.setShipAddress(this.shipAddressService.getShipAddressById(info.getShipId()));

            List<OrderGoods> goodsList = this.orderService.getOrderGoodsList(info.getId());
            for (OrderGoods orderGoods : goodsList) {
                orderGoods.setGoodsPrice(YuanFenConverter.changeY2F(orderGoods.getGoodsPrice()));
            }

            order.setOrderGoodsList(goodsList);

            orderList.add(order);
        }

        resp.setStatus(0);
        resp.setMessage("列表获取成功");
        resp.setData(orderList);
        return resp;
    }

    @RequestMapping(value = {"/getOrderById"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public BaseResp<Order> getOrderById(@RequestBody GetOrderByIdReq req) {
        BaseResp resp = new BaseResp();

        Order order = new Order();

        OrderInfo orderInfo = this.orderService.getOrderById(req.getOrderId());
        order.setId(orderInfo.getId());
        order.setOrderStatus(orderInfo.getOrderStatus());
        order.setPayType(orderInfo.getPayType());
        order.setTotalPrice(YuanFenConverter.changeY2F(Long.valueOf(orderInfo.getTotalPrice())));

        order.setShipAddress(this.shipAddressService.getShipAddressById(orderInfo.getShipId()));

        List<OrderGoods> orderGoodsList = this.orderService.getOrderGoodsList(orderInfo.getId());
        for (OrderGoods orderGoods : orderGoodsList) {
            orderGoods.setGoodsPrice(YuanFenConverter.changeY2F(orderGoods.getGoodsPrice()));
        }

        order.setOrderGoodsList(orderGoodsList);

        resp.setStatus(0);
        resp.setMessage("获取订单成功");
        resp.setData(order);
        return resp;
    }

    @RequestMapping(value = {"/submitOrder"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public BaseResp submitOrder(@RequestBody SubmitOrderReq req) {
        BaseResp resp = new BaseResp();

        OrderInfo orderInfo = this.orderService.getOrderById(req.getOrder().getId());
        orderInfo.setOrderStatus(Integer.valueOf(1));
        orderInfo.setShipId(req.getOrder().getShipAddress().getId());

        this.orderService.modifyOrder(orderInfo);

        if (InitAction.cartIdMap != null) {
            List cartIdList = InitAction.cartIdMap.get(req.getOrder().getId());
            this.cartGoodsService.deleteCartGoods(cartIdList);
            InitAction.cartIdMap.remove(req.getOrder().getId());
        }


        int userId = Integer.valueOf(this.request.getHeader("token")).intValue();
        String pushId = this.userService.getUserById(userId).getPushId();
        sendMessage(Integer.valueOf(userId), pushId, req.getOrder().getId() + "");

        resp.setStatus(0);
        resp.setMessage("订单提交成功");
        return resp;
    }

    private void sendMessage(Integer userId, String pushId, String orderId) {
        String curTime = DateUtil.format(new Date(), DateUtil.FORMAT_LONG_NEW);
        MessageInfo msg = new MessageInfo();
        msg.setMsgIcon("http://pw7t5h9o4.bkt.clouddn.com/icon_default_user.png");
        msg.setMsgTitle("下单成功");
        msg.setMsgContent("恭喜您下单成功，有一笔订单等待支付");
        msg.setMsgTime(curTime);
        msg.setUserId(userId);
        this.messageService.addMessage(msg);

        PushSender.sendOrderEvent(pushId, orderId);
    }

    @RequestMapping(value = {"/cancel"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public BaseResp cancelOrder(@RequestBody CancelOrderReq req) {
        BaseResp resp = new BaseResp();

        OrderInfo orderInfo = this.orderService.getOrderById(req.getOrderId());
        orderInfo.setOrderStatus(Integer.valueOf(4));
        this.orderService.modifyOrder(orderInfo);

        resp.setStatus(0);
        resp.setMessage("订单取消成功");
        return resp;
    }

    @RequestMapping(value = {"/confirm"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public BaseResp confirmOrder(@RequestBody ConfirmOrderReq req) {
        BaseResp resp = new BaseResp();

        OrderInfo orderInfo = this.orderService.getOrderById(req.getOrderId());
        orderInfo.setOrderStatus(Integer.valueOf(3));
        this.orderService.modifyOrder(orderInfo);

        resp.setStatus(0);
        resp.setMessage("订单确认收货成功");
        return resp;
    }

    @RequestMapping(value = {"/pay"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public BaseResp payOrder(@RequestBody PayOrderReq req) {
        BaseResp resp = new BaseResp();

        OrderInfo orderInfo = this.orderService.getOrderById(req.getOrderId());
        orderInfo.setOrderStatus(Integer.valueOf(2));
        this.orderService.modifyOrder(orderInfo);

        resp.setStatus(0);
        resp.setMessage("订单支付成功");
        return resp;
    }
}
