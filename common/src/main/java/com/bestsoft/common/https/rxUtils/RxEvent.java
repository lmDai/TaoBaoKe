package com.bestsoft.common.https.rxUtils;

/**
 * @package: com.bestsoft.common.https.rxUtils
 * @user:xhkj
 * @date:2018/10/29
 * @description:
 **/
public class RxEvent {
    private int code;
    private Object object;

    public RxEvent() {

    }

    public RxEvent(int code, Object object) {
        this.code = code;
        this.object = object;
    }

    public int getCode() {
        return code;
    }


    public Object getObject() {
        return object;
    }
}
