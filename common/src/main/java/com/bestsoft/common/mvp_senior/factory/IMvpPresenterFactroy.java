package com.bestsoft.common.mvp_senior.factory;


import com.bestsoft.common.https.BasePresenter;
import com.bestsoft.common.https.IBaseView;

/**
 * @author Administrator
 * @date 2018/10/16  14:26
 * @description 创建Presenter工厂接口
 **/
public interface IMvpPresenterFactroy<V extends IBaseView,P extends BasePresenter<V>> {
    /**
     * 创建Presenter的接口方法
     * @return 需要创建的Presenter
     */
    P createMvpPresenter();
}
