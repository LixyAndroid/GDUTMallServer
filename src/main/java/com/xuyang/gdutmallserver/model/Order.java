package com.xuyang.gdutmallserver.model;

import java.util.List;

public class Order {
    private Integer id;
    private Integer payType;
    private ShipAddress shipAddress;
    private String totalPrice;
    private Integer orderStatus;
    private List<OrderGoods> orderGoodsList;

    public Integer getId() {

        return this.id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public Integer getPayType() {

        return this.payType;
    }

    public void setPayType(Integer payType) {
        /* 31 */
        this.payType = payType;
    }

    public ShipAddress getShipAddress() {
        /* 35 */
        return this.shipAddress;
    }

    public void setShipAddress(ShipAddress shipAddress) {

        this.shipAddress = shipAddress;
    }

    public String getTotalPrice() {

        return this.totalPrice;
    }

    public void setTotalPrice(String totalPrice) {

        this.totalPrice = totalPrice;
    }

    public Integer getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderGoods> getOrderGoodsList() {
        return this.orderGoodsList;
    }

    public void setOrderGoodsList(List<OrderGoods> orderGoodsList) {

        this.orderGoodsList = orderGoodsList;
    }
}
