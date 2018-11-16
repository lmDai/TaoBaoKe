package com.bestsoft.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @package: com.bestsoft.bean
 * @user:xhkj
 * @date:2018/11/14
 * @description:
 **/

@Entity
public class KeyWordModel {

    private String keyword;

    @Generated(hash = 861867040)
    public KeyWordModel(String keyword) {
        this.keyword = keyword;
    }

    @Generated(hash = 1188290633)
    public KeyWordModel() {
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean equals(Object o) {
        KeyWordModel inItem = (KeyWordModel) o;
        return keyword.equals(inItem.getKeyword());
    }

}
