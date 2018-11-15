package com.bestsoft.bean;

/**
 * @package: com.bestsoft.bean
 * @user:xhkj
 * @date:2018/11/14
 * @description:
 **/
public class TeamProfitModel {
    private String today_commission;//今日收益金额
    private String stay_commission;//待到账订单佣金金额

    private String today_orders_num;//今日付款订单数
    private String today_estimate_commission;//	今日付款订单预估佣金
    private String today_other;//今日其他

    private String yesterday_orders_num;//昨天付款订单数
    private String yesterday_estimate_commission;//	昨天付款订单预估佣金
    private String yesterday_other;//	昨天其他

    public String getToday_commission() {
        return today_commission;
    }

    public void setToday_commission(String today_commission) {
        this.today_commission = today_commission;
    }

    public String getStay_commission() {
        return stay_commission;
    }

    public void setStay_commission(String stay_commission) {
        this.stay_commission = stay_commission;
    }

    public String getToday_orders_num() {
        return today_orders_num;
    }

    public void setToday_orders_num(String today_orders_num) {
        this.today_orders_num = today_orders_num;
    }

    public String getToday_estimate_commission() {
        return today_estimate_commission;
    }

    public void setToday_estimate_commission(String today_estimate_commission) {
        this.today_estimate_commission = today_estimate_commission;
    }

    public String getToday_other() {
        return today_other;
    }

    public void setToday_other(String today_other) {
        this.today_other = today_other;
    }

    public String getYesterday_orders_num() {
        return yesterday_orders_num;
    }

    public void setYesterday_orders_num(String yesterday_orders_num) {
        this.yesterday_orders_num = yesterday_orders_num;
    }

    public String getYesterday_estimate_commission() {
        return yesterday_estimate_commission;
    }

    public void setYesterday_estimate_commission(String yesterday_estimate_commission) {
        this.yesterday_estimate_commission = yesterday_estimate_commission;
    }

    public String getYesterday_other() {
        return yesterday_other;
    }

    public void setYesterday_other(String yesterday_other) {
        this.yesterday_other = yesterday_other;
    }
}
