package com.bestsoft.mvp.contract;

import com.bestsoft.common.https.BasePresenter;
import com.bestsoft.common.https.IBaseView;

/**
 * @package: com.bestsoft.mvp.contract
 * @user:xhkj
 * @date:2018/10/29
 * @description:获取邀请人信息
 **/
public interface InputInvateInfoContract {
    interface View extends IBaseView {
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getInvateInfo();
    }
}
