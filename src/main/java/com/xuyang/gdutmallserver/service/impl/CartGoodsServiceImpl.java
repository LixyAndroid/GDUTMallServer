package com.xuyang.gdutmallserver.service.impl;

import com.xuyang.gdutmallserver.mapper.CartGoodsMapper;
import com.xuyang.gdutmallserver.model.CartGoods;
import com.xuyang.gdutmallserver.service.CartGoodsService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cartGoodsService")
public class CartGoodsServiceImpl implements CartGoodsService {

    @Autowired
    private CartGoodsMapper cartGoodsMapper;

    public int addCartGoods(CartGoods model) {
        return this.cartGoodsMapper.insert(model);
    }

    public List<CartGoods> getCartGoodsList(Integer userId) {
        return this.cartGoodsMapper.selectCartGoodsList(userId.intValue());
    }

    public void deleteCartGoods(List<Integer> list) {
        this.cartGoodsMapper.deleteCartGoods(list);
    }
}

