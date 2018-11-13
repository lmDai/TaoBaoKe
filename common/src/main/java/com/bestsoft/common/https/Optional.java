package com.bestsoft.common.https;

import java.util.NoSuchElementException;

import io.reactivex.annotations.Nullable;

/**
 * @package: com.bestsoft.common.https
 * @user:xhkj
 * @date:2018/11/12
 * @description:包装返回结果为空
 **/
public class Optional<M> {
    private final M optional;//接收到的返回结果

    public Optional(@Nullable M optional) {
        this.optional = optional;
    }
    public boolean isEmpty(){
        return this.optional==null;
    }
    public M get(){
        if (optional==null){
            throw  new NoSuchElementException("No value present");
        }
        return optional;
    }

    public M getIncludeNull() {
        return optional;
    }
}
