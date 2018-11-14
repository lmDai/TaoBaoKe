package com.bestsoft.bean;

/**
 * @package: com.bestsoft.bean
 * @user:xhkj
 * @date:2018/11/14
 * @description:会员收益数据
 **/
public class ProfitModel {

    private String couponmoney;
    private String commission;
    private String own_commission;

    public String getCouponmoney() {
        return couponmoney;
    }

    public void setCouponmoney(String couponmoney) {
        this.couponmoney = couponmoney;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public String getOwn_commission() {
        return own_commission;
    }

    public void setOwn_commission(String own_commission) {
        this.own_commission = own_commission;
    }
}
