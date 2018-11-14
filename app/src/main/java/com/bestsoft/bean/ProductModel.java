package com.bestsoft.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @package: com.bestsoft.bean
 * @user:xhkj
 * @date:2018/11/2
 * @description:
 **/
public class ProductModel implements Parcelable {

    /**
     * item_id : 557654804770
     * item_title : 婴儿背带腰凳四季通用多功能前横抱式小孩儿童抱带宝宝抱娃神器单
     * item_short_title :
     * item_desc :
     * item_sale :
     * item_sale2 :
     * today_sale :
     * item_pic : ["http://img04.taobaocdn.com:80/tfscom/TB1s7JUpmzqK1RjSZPcXXbTepXa","https://img.alicdn.com/imgextra/i4/2413134683/O1CN01BJzeh91kSrFb0eViu_!!2413134683.jpg","https://img.alicdn.com/imgextra/i4/2413134683/TB2boQ.qhGYBuNjy0FnXXX5lpXa_!!2413134683.jpg","http://img01.taobaocdn.com:80/tfscom/TB1y4X5pkvoK1RjSZFwXXciCFXa"]
     * item_pic_copy :
     * fqcat :
     * item_price : 79
     * item_end_price : 59.00
     * couponmoney : 20
     * couponsurplus :
     * couponnum :
     * source : 淘宝
     * guide_article :
     * tkrates : 30.00
     * tkmoney :
     */

    private String item_id;
    private String item_title;
    private String item_short_title;
    private String item_desc;
    private String item_sale;
    private String item_sale2;
    private String today_sale;
    private String item_pic_copy;
    private String fqcat;
    private String item_price;
    private String item_end_price;
    private String couponmoney;
    private String couponsurplus;
    private String couponnum;
    private String source;
    private String guide_article;
    private String tkrates;
    private String tkmoney;
    private List<String> item_pic;
    private String estimate;
    private String upgrade;
    /**
     * guide_article : null
     * tkmoney : 2.37
     * sellernick : 以康旗舰店
     * couponstarttime : 2018-11-11
     * couponendtime : 2018-11-18
     * couponurl : http://uland.taobao.com/quan/detail?sellerId=2832220970&activityId=7c787063ffc3419f9449fddc23ae0f1d
     * core_commission : 0.95985
     */

    private String sellernick;
    private String couponstarttime;
    private String couponendtime;
    private String couponurl;
    private double core_commission;

    protected ProductModel(Parcel in) {
        item_id = in.readString();
        item_title = in.readString();
        item_short_title = in.readString();
        item_desc = in.readString();
        item_sale = in.readString();
        item_sale2 = in.readString();
        today_sale = in.readString();
        item_pic_copy = in.readString();
        fqcat = in.readString();
        item_price = in.readString();
        item_end_price = in.readString();
        couponmoney = in.readString();
        couponsurplus = in.readString();
        couponnum = in.readString();
        source = in.readString();
        guide_article = in.readString();
        tkrates = in.readString();
        tkmoney = in.readString();
        item_pic = in.createStringArrayList();
        estimate = in.readString();
        upgrade = in.readString();
        sellernick = in.readString();
        couponstarttime = in.readString();
        couponendtime = in.readString();
        couponurl = in.readString();
        core_commission = in.readDouble();
    }

    public static final Creator<ProductModel> CREATOR = new Creator<ProductModel>() {
        @Override
        public ProductModel createFromParcel(Parcel in) {
            return new ProductModel(in);
        }

        @Override
        public ProductModel[] newArray(int size) {
            return new ProductModel[size];
        }
    };

    public String getEstimate() {
        return estimate;
    }

    public void setEstimate(String estimate) {
        this.estimate = estimate;
    }

    public String getUpgrade() {
        return upgrade;
    }

    public void setUpgrade(String upgrade) {
        this.upgrade = upgrade;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getItem_title() {
        return item_title;
    }

    public void setItem_title(String item_title) {
        this.item_title = item_title;
    }

    public String getItem_short_title() {
        return item_short_title;
    }

    public void setItem_short_title(String item_short_title) {
        this.item_short_title = item_short_title;
    }

    public String getItem_desc() {
        return item_desc;
    }

    public void setItem_desc(String item_desc) {
        this.item_desc = item_desc;
    }

    public String getItem_sale() {
        return item_sale;
    }

    public void setItem_sale(String item_sale) {
        this.item_sale = item_sale;
    }

    public String getItem_sale2() {
        return item_sale2;
    }

    public void setItem_sale2(String item_sale2) {
        this.item_sale2 = item_sale2;
    }

    public String getToday_sale() {
        return today_sale;
    }

    public void setToday_sale(String today_sale) {
        this.today_sale = today_sale;
    }

    public String getItem_pic_copy() {
        return item_pic_copy;
    }

    public void setItem_pic_copy(String item_pic_copy) {
        this.item_pic_copy = item_pic_copy;
    }

    public String getFqcat() {
        return fqcat;
    }

    public void setFqcat(String fqcat) {
        this.fqcat = fqcat;
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

    public String getCouponsurplus() {
        return couponsurplus;
    }

    public void setCouponsurplus(String couponsurplus) {
        this.couponsurplus = couponsurplus;
    }

    public String getCouponnum() {
        return couponnum;
    }

    public void setCouponnum(String couponnum) {
        this.couponnum = couponnum;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getGuide_article() {
        return guide_article;
    }

    public void setGuide_article(String guide_article) {
        this.guide_article = guide_article;
    }

    public String getTkrates() {
        return tkrates;
    }

    public void setTkrates(String tkrates) {
        this.tkrates = tkrates;
    }

    public String getTkmoney() {
        return tkmoney;
    }

    public void setTkmoney(String tkmoney) {
        this.tkmoney = tkmoney;
    }

    public List<String> getItem_pic() {
        return item_pic;
    }

    public void setItem_pic(List<String> item_pic) {
        this.item_pic = item_pic;
    }

    public String getSellernick() {
        return sellernick;
    }

    public void setSellernick(String sellernick) {
        this.sellernick = sellernick;
    }

    public String getCouponstarttime() {
        return couponstarttime;
    }

    public void setCouponstarttime(String couponstarttime) {
        this.couponstarttime = couponstarttime;
    }

    public String getCouponendtime() {
        return couponendtime;
    }

    public void setCouponendtime(String couponendtime) {
        this.couponendtime = couponendtime;
    }

    public String getCouponurl() {
        return couponurl;
    }

    public void setCouponurl(String couponurl) {
        this.couponurl = couponurl;
    }

    public double getCore_commission() {
        return core_commission;
    }

    public void setCore_commission(double core_commission) {
        this.core_commission = core_commission;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(item_id);
        dest.writeString(item_title);
        dest.writeString(item_short_title);
        dest.writeString(item_desc);
        dest.writeString(item_sale);
        dest.writeString(item_sale2);
        dest.writeString(today_sale);
        dest.writeString(item_pic_copy);
        dest.writeString(fqcat);
        dest.writeString(item_price);
        dest.writeString(item_end_price);
        dest.writeString(couponmoney);
        dest.writeString(couponsurplus);
        dest.writeString(couponnum);
        dest.writeString(source);
        dest.writeString(guide_article);
        dest.writeString(tkrates);
        dest.writeString(tkmoney);
        dest.writeStringList(item_pic);
        dest.writeString(estimate);
        dest.writeString(upgrade);
        dest.writeString(sellernick);
        dest.writeString(couponstarttime);
        dest.writeString(couponendtime);
        dest.writeString(couponurl);
        dest.writeDouble(core_commission);
    }
}
