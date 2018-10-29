package com.bestsoft.mvp.contract;

import com.bestsoft.common.https.BasePresenter;
import com.bestsoft.common.https.IBaseView;

/**
 * @package: com.bestsoft.mvp.contract
 * @user:xhkj
 * @date:2018/10/29
 * @description:登陆
 **/
public interface LoginContract {
    interface View extends IBaseView {
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getTag();
    }
}
