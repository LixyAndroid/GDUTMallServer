package com.xuyang.gdutmallserver.mapper;

import com.xuyang.gdutmallserver.model.OrderGoods;

import java.util.List;

public interface OrderGoodsMapper {
    int deleteByPrimaryKey(Integer paramInteger);

    int insert(OrderGoods paramOrderGoods);

    int insertSelective(OrderGoods paramOrderGoods);

    OrderGoods selectByPrimaryKey(Integer paramInteger);

    int updateByPrimaryKeySelective(OrderGoods paramOrderGoods);

    int updateByPrimaryKey(OrderGoods paramOrderGoods);

    List<OrderGoods> selectGoodsList(Integer paramInteger);
}
