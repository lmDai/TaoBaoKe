package com.bestsoft.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @package: com.bestsoft.bean
 * @user:xhkj
 * @date:2018/11/19
 * @description:
 **/
public class WithDrawModel implements Parcelable {
    private String money;//当前提现金额
    private String type;//提现方式
    private String poundage;//当前提现收取的手续费

    protected WithDrawModel(Parcel in) {
        money = in.readString();
        type = in.readString();
        poundage = in.readString();
    }

    public static final Creator<WithDrawModel> CREATOR = new Creator<WithDrawModel>() {
        @Override
        public WithDrawModel createFromParcel(Parcel in) {
            return new WithDrawModel(in);
        }

        @Override
        public WithDrawModel[] newArray(int size) {
            return new WithDrawModel[size];
        }
    };

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPoundage() {
        return poundage;
    }

    public void setPoundage(String poundage) {
        this.poundage = poundage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(money);
        dest.writeString(type);
        dest.writeString(poundage);
    }
}
