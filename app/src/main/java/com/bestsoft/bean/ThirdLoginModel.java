package com.bestsoft.bean;

/**
 * @package: com.bestsoft.bean
 * @user:xhkj
 * @date:2018/11/21
 * @description:
 **/
public class ThirdLoginModel {

    /**
     * errorcode : 0
     * msg : 登录成功！
     * data : {"id":34,"headimgurl":"http://qzapp.qlogo.cn/qzapp/101520389/444445AD79D26C7566D752E70DC5CEF4/30","nickname":"25663","level":1,"taobao_pid":"mm_116147303_36984478_29770200275","total_income":0,"balance":0,"discount":0,"invite_code":"EACAE0A1","user_channel_id":2,"settingtaobao":2,"alipay_account":"","real_name":""}
     */

    private int errorcode;
    private String msg;
    private DataBean data;

    public int getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(int errorcode) {
        this.errorcode = errorcode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 34
         * headimgurl : http://qzapp.qlogo.cn/qzapp/101520389/444445AD79D26C7566D752E70DC5CEF4/30
         * nickname : 25663
         * level : 1
         * taobao_pid : mm_116147303_36984478_29770200275
         * total_income : 0
         * balance : 0
         * discount : 0
         * invite_code : EACAE0A1
         * user_channel_id : 2
         * settingtaobao : 2
         * alipay_account :
         * real_name :
         */

        private int id;
        private String headimgurl;
        private String nickname;
        private int level;
        private String taobao_pid;
        private int total_income;
        private int balance;
        private int discount;
        private String invite_code;
        private int user_channel_id;
        private int settingtaobao;
        private String alipay_account;
        private String real_name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(String headimgurl) {
            this.headimgurl = headimgurl;
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

        public int getUser_channel_id() {
            return user_channel_id;
        }

        public void setUser_channel_id(int user_channel_id) {
            this.user_channel_id = user_channel_id;
        }

        public int getSettingtaobao() {
            return settingtaobao;
        }

        public void setSettingtaobao(int settingtaobao) {
            this.settingtaobao = settingtaobao;
        }

        public String getAlipay_account() {
            return alipay_account;
        }

        public void setAlipay_account(String alipay_account) {
            this.alipay_account = alipay_account;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }
    }
}
