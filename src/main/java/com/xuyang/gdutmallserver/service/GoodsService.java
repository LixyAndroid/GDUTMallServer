package com.xuyang.gdutmallserver.service;

import com.xuyang.gdutmallserver.model.GoodsInfo;
import com.xuyang.gdutmallserver.model.GoodsSku;

import java.util.List;

public interface GoodsService {
    int addGoods(GoodsInfo paramGoodsInfo);

    List<GoodsInfo> getGoodsList(Integer paramInteger1, Integer paramInteger2);

    List<GoodsInfo> getAllGoodsList(Integer paramInteger);

    GoodsInfo getGoodsDetail(Integer paramInteger);

    int addGoodsSku(GoodsSku paramGoodsSku);

    List<GoodsSku> getGoodsSkuList(Integer paramInteger);

    void modifyGoodsInfo(GoodsInfo paramGoodsInfo);

    List<GoodsInfo> getGoodsListByKeyword(String paramString, Integer paramInteger);

    List<GoodsInfo> getAllByKeyword(String paramString);
}