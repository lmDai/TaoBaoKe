package com.bestsoft.common.https;

/**
 * @package: com.bestsoft.common.https
 * @user:xhkj
 * @date:2018/10/29
 * @description:返回的基础类型数据
 **/
public class BaseResponse<T> {
    public static final int SUCCESS = 0;
    public static final int FAILURE = 1;
    public static final int NO_LOGIN = 2;

    private int errorcode;
    private String msg;
    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Optional<T> transform() {
        return new Optional<>(data);
    }
}
