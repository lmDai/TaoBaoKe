package com.bestsoft.mvp.contract;

import com.bestsoft.bean.UserModel;
import com.bestsoft.bean.VersionModel;
import com.bestsoft.bean.WithDrawModel;
import com.bestsoft.common.https.BaseNoDataResponse;
import com.bestsoft.common.https.BasePresenter;
import com.bestsoft.common.https.IBaseView;

/**
 * @package: com.bestsoft.mvp.contract
 * @user:xhkj
 * @date:2018/10/29
 * @description:登陆
 **/
public interface WithDrawContract {
    interface View extends IBaseView {
        void setUserModel(UserModel userModel);

        void withDrawApply(WithDrawModel settingResult);

    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getUserInfo(String user_id, String user_channel_id);
        public abstract void withDrawApply(String amount,String type,String user_id,String user_channel_id);
    }
}
