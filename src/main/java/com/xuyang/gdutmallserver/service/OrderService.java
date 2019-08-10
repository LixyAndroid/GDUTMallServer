package com.xuyang.gdutmallserver.service;

import com.xuyang.gdutmallserver.model.OrderGoods;
import com.xuyang.gdutmallserver.model.OrderInfo;

import java.util.List;

public interface OrderService {
    int addOrder(OrderInfo paramOrderInfo);

    List<OrderInfo> getOrderList(Integer paramInteger1, Integer paramInteger2);

    int addOrderGoods(OrderGoods paramOrderGoods);

    OrderInfo getOrderById(Integer paramInteger);

    List<OrderGoods> getOrderGoodsList(Integer paramInteger);

    int modifyOrder(OrderInfo paramOrderInfo);
}
