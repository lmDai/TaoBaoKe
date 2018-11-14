package com.bestsoft.bean;

/**
 * @package: com.bestsoft.bean
 * @user:xhkj
 * @date:2018/11/2
 * @description:
 **/
public class OrderModel {
    private String order_id;
    private String order_number;
    private String third_number;
    private String item_pic;
    private String item_title;
    private String item_end_price;
    private String item_num;
    private String create_at;
    private String commission;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public String getThird_number() {
        return third_number;
    }

    public void setThird_number(String third_number) {
        this.third_number = third_number;
    }

    public String getItem_pic() {
        return item_pic;
    }

    public void setItem_pic(String item_pic) {
        this.item_pic = item_pic;
    }

    public String getItem_title() {
        return item_title;
    }

    public void setItem_title(String item_title) {
        this.item_title = item_title;
    }

    public String getItem_end_price() {
        return item_end_price;
    }

    public void setItem_end_price(String item_end_price) {
        this.item_end_price = item_end_price;
    }

    public String getItem_num() {
        return item_num;
    }

    public void setItem_num(String item_num) {
        this.item_num = item_num;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }
}
