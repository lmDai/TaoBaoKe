package com.bestsoft.common.https;

/**
 * @package: com.bestsoft.common.https
 * @user:xhkj
 * @date:2018/10/29
 * @description:返回的基础类型数据
 **/
public class BaseResponse<T> {


    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
