package com.bestsoft.bean;

import java.util.List;

/**
 * @package: com.bestsoft.bean
 * @user:xhkj
 * @date:2018/11/21
 * @description:
 **/
public class GoodsShareModel {

    private String item_title;//商品标题
    private String fqcat;//商品类目
    private String source;//来源
    private List<String> item_pic;//图片（数组）
    private String item_price;//商品价格
    private String item_end_price;//商品卷后价格
    private String couponmoney;//商品优惠卷金额
    private String original_article;//文案
    private String sellernick;//店名
    private String tapkoul;//淘口令
    private String commission;//预估佣金
    private String share_img;

    public String getShare_img() {
        return share_img;
    }

    public void setShare_img(String share_img) {
        this.share_img = share_img;
    }

    public String getItem_title() {
        return item_title;
    }

    public void setItem_title(String item_title) {
        this.item_title = item_title;
    }

    public String getFqcat() {
        return fqcat;
    }

    public void setFqcat(String fqcat) {
        this.fqcat = fqcat;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<String> getItem_pic() {
        return item_pic;
    }

    public void setItem_pic(List<String> item_pic) {
        this.item_pic = item_pic;
    }

    public String getItem_price() {
        return item_price;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }

    public String getItem_end_price() {
        return item_end_price;
    }

    public void setItem_end_price(String item_end_price) {
        this.item_end_price = item_end_price;
    }

    public String getCouponmoney() {
        return couponmoney;
    }

    public void setCouponmoney(String couponmoney) {
        this.couponmoney = couponmoney;
    }

    public String getOriginal_article() {
        return original_article;
    }

    public void setOriginal_article(String original_article) {
        this.original_article = original_article;
    }

    public String getSellernick() {
        return sellernick;
    }

    public void setSellernick(String sellernick) {
        this.sellernick = sellernick;
    }

    public String getTapkoul() {
        return tapkoul;
    }

    public void setTapkoul(String tapkoul) {
        this.tapkoul = tapkoul;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }
}
