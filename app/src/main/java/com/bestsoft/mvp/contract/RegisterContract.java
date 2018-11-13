package com.bestsoft.mvp.contract;

import com.bestsoft.common.https.BaseNoDataResponse;
import com.bestsoft.common.https.BasePresenter;
import com.bestsoft.common.https.IBaseView;

/**
 * @package: com.bestsoft.mvp.contract
 * @user:xhkj
 * @date:2018/10/29
 * @description:注册
 **/
public interface RegisterContract {
    interface View extends IBaseView {
        void registerSuccess(BaseNoDataResponse result);
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void userRegister(String phone, String smscode, String user_chanel_id, String pid);
    }
}
