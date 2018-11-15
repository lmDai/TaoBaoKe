package com.bestsoft.bean;

/**
 * @package: com.bestsoft.bean
 * @user:xhkj
 * @date:2018/11/15
 * @description:
 **/
public class TeamOrderModel {
    private String order_id;//订单id
    private String order_number;//订单号
    private String third_number;//第三方订单号
    private String item_id;//宝贝id
    private String item_pic;//宝贝图片
    private String item_title;//宝贝标题
    private String item_price;//商品价格
    private String item_end_price;//商品卷后价格
    private String item_num;//商品数量
    private String create_at;//下单时间
    private String commission;//佣金
    private String payment_amount;//支付金额
    private String order_status;

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

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

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
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

    public String getPayment_amount() {
        return payment_amount;
    }

    public void setPayment_amount(String payment_amount) {
        this.payment_amount = payment_amount;
    }
}
