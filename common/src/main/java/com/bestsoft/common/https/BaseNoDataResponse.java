package com.bestsoft.common.https;

/**
 * @package: com.bestsoft.common.https
 * @user:xhkj
 * @date:2018/11/12
 * @description:
 **/
public class BaseNoDataResponse {

    /**
     * errorcode : 0
     * msg : 注册成功！
     */

    private int errorcode;
    private String msg;

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
}
