package com.bestsoft.bean;

/**
 * @package: com.bestsoft.bean
 * @user:xhkj
 * @date:2018/11/12
 * @description:
 **/
public class OrderConfirmModel {
    private String taobao_pid;
    private String order_id;

    public String getTaobao_pid() {
        return taobao_pid;
    }

    public void setTaobao_pid(String taobao_pid) {
        this.taobao_pid = taobao_pid;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }
}
