package com.bestsoft.bean;

/**
 * @package: com.bestsoft.bean
 * @user:xhkj
 * @date:2018/11/15
 * @description:
 **/
public class ExtractModel {
    private String balance;//	会员余额
    private String own_commission;//会员已结算的自购佣金
    private String fans_commission;//会员已结算的粉丝提供佣金
    private String wait_commission;//会员等待到账的佣金

    public String getBalance() {
        return balance;
    }

    public String getWait_commission() {
        return wait_commission;
    }

    public void setWait_commission(String wait_commission) {
        this.wait_commission = wait_commission;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getOwn_commission() {
        return own_commission;
    }

    public void setOwn_commission(String own_commission) {
        this.own_commission = own_commission;
    }

    public String getFans_commission() {
        return fans_commission;
    }

    public void setFans_commission(String fans_commission) {
        this.fans_commission = fans_commission;
    }

}
