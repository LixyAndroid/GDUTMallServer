package com.xuyang.gdutmallserver.model;

import java.util.List;

public class GoodsSku {
    private Integer id;
    private Integer goodsId;
    private String goodsSkuTitle;
    private String goodsSkuContent;
    private String skuTitle;
    private List<String> skuContent;

    public GoodsSku() {
    }

    public GoodsSku(Integer goodsId, String goodsSkuTitle, String goodsSkuContent) {
        this.goodsId = goodsId;
        this.goodsSkuTitle = goodsSkuTitle;
        this.goodsSkuContent = goodsSkuContent;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return this.goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsSkuContent() {
        return this.goodsSkuContent;
    }

    public void setGoodsSkuContent(String goodsSkuContent) {
        this.goodsSkuContent = (goodsSkuContent == null ? null : goodsSkuContent.trim());
    }

    public String getSkuTitle() {
        return this.skuTitle;
    }

    public void setSkuTitle(String skuTitle) {
        this.skuTitle = skuTitle;
    }

    public List<String> getSkuContent() {
        return this.skuContent;
    }

    public void setSkuContent(List<String> skuContent) {
        this.skuContent = skuContent;
    }

    public String getGoodsSkuTitle() {
        return this.goodsSkuTitle;
    }

    public void setGoodsSkuTitle(String goodsSkuTitle) {
        this.goodsSkuTitle = goodsSkuTitle;
    }
}

