package com.bestsoft.bean;

/**
 * @package: com.bestsoft.bean
 * @user:xhkj
 * @date:2018/11/12
 * @description:用户信息
 **/
public class UserModel {

    /**
     * id : 3
     * nickname :
     * level : 1
     * taobao_pid : ddd_dasd
     * total_income : 0
     * balance : 0
     * discount : 0
     * invite_code : F1569EEA
     */

    private String id;
    private String nickname;
    private int level;
    private String taobao_pid;
    private int total_income;
    private int balance;
    private int discount;
    private String invite_code;
    private String channel_id;

    public String getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getTaobao_pid() {
        return taobao_pid;
    }

    public void setTaobao_pid(String taobao_pid) {
        this.taobao_pid = taobao_pid;
    }

    public int getTotal_income() {
        return total_income;
    }

    public void setTotal_income(int total_income) {
        this.total_income = total_income;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getInvite_code() {
        return invite_code;
    }

    public void setInvite_code(String invite_code) {
        this.invite_code = invite_code;
    }
}
