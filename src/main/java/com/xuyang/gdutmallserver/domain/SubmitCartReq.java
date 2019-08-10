package com.xuyang.gdutmallserver.domain;

import com.xuyang.gdutmallserver.model.OrderGoods;

import java.util.List;

public class SubmitCartReq {
    private List<OrderGoods> goodsList;
    private Long totalPrice;

    public List<OrderGoods> getGoodsList() {
        return this.goodsList;
    }

    public void setGoodsList(List<OrderGoods> goodsList) {
        this.goodsList = goodsList;
    }

    public Long getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }
}