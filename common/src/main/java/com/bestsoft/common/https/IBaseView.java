package com.bestsoft.common.https;

import io.reactivex.ObservableTransformer;

/**
 * @package: com.bestsoft.common.https
 * @user:xhkj
 * @date:2018/10/29
 * @description:
 **/
public interface IBaseView {
    /**
     * 用来 绑定view 生命周期，解决rxjava内存泄露
     *
     * @param
     * @return
     */
    <T> ObservableTransformer<T, T> bindLifeycle();

    /**
     * 显示正在加载
     */
    void showProgress();

    /**
     * 隐藏正在加载
     */
    void hideProgress();

}
