package com.bestsoft.mvp.contract;

import com.bestsoft.bean.ExtractModel;
import com.bestsoft.common.https.BasePresenter;
import com.bestsoft.common.https.IBaseView;

/**
 * @package: com.bestsoft.mvp.contract
 * @user:xhkj
 * @date:2018/10/29
 * @description:
 **/
public interface ExtractContract {
    interface View extends IBaseView {
        void setUserExtract(ExtractModel models);

        void showError(Throwable throwable);
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getUserExtract(String user_id, String user_channel_id);
    }
}
