package com.bestsoft.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @package: com.bestsoft.bean
 * @user:xhkj
 * @date:2018/11/9
 * @description:
 **/
public class CodeModel implements Parcelable {
    private String pid;//父级会员id
    private String user_channel_id;//直属渠道id

    protected CodeModel(Parcel in) {
        pid = in.readString();
        user_channel_id = in.readString();
    }

    public static final Creator<CodeModel> CREATOR = new Creator<CodeModel>() {
        @Override
        public CodeModel createFromParcel(Parcel in) {
            return new CodeModel(in);
        }

        @Override
        public CodeModel[] newArray(int size) {
            return new CodeModel[size];
        }
    };

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getUser_channel_id() {
        return user_channel_id;
    }

    public void setUser_channel_id(String user_channel_id) {
        this.user_channel_id = user_channel_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(pid);
        dest.writeString(user_channel_id);
    }
}
