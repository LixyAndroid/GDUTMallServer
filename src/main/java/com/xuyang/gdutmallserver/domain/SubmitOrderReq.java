package com.xuyang.gdutmallserver.domain;

import com.xuyang.gdutmallserver.model.Order;

public class SubmitOrderReq {
    private Order order;

    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
