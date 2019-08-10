package com.xuyang.gdutmallserver.service;

import com.xuyang.gdutmallserver.model.CartGoods;

import java.util.List;

public interface CartGoodsService {
    int addCartGoods(CartGoods paramCartGoods);

    List<CartGoods> getCartGoodsList(Integer paramInteger);

    void deleteCartGoods(List<Integer> paramList);
}
