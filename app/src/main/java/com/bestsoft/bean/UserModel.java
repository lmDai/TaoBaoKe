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
    private double total_income;
    private double balance;
    private double discount;
    private String invite_code;
    private String user_channel_id;
    /**
     * id : 3
     * headimgurl :
     * user_channel_id : 2
     */
    private String headimgurl;
    private int settingtaobao;//淘宝授权1.设置授权，2.不授权
    private String alipay_account;//淘宝账号
    private String real_name;//真实姓名

    public void setSettingtaobao(int settingtaobao) {
        this.settingtaobao = settingtaobao;
    }

    public void setAlipay_account(String alipay_account) {
        this.alipay_account = alipay_account;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public int getSettingtaobao() {
        return settingtaobao;
    }

    public String getAlipay_account() {
        return alipay_account;
    }

    public String getReal_name() {
        return real_name;
    }

    public String getUser_channel_id() {
        return user_channel_id;
    }

    public void setUser_channel_id(String user_channel_id) {
        this.user_channel_id = user_channel_id;
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

    public double getTotal_income() {
        return total_income;
    }

    public void setTotal_income(int total_income) {
        this.total_income = total_income;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public double getDiscount() {
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

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }
}
