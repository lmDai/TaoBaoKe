package com.bestsoft.mvp.contract;

import com.bestsoft.bean.UserModel;
import com.bestsoft.bean.VersionModel;
import com.bestsoft.common.https.BaseNoDataResponse;
import com.bestsoft.common.https.BasePresenter;
import com.bestsoft.common.https.IBaseView;

/**
 * @package: com.bestsoft.mvp.contract
 * @user:xhkj
 * @date:2018/10/29
 * @description:登陆
 **/
public interface PersonalContract {
    interface View extends IBaseView {
        void setUserModel(UserModel userModel);

        void userSettingTaobao(BaseNoDataResponse settingResult);

        void setUserVersion(VersionModel version);
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getUserInfo(String user_id, String user_channel_id);

        public abstract void userSettingTaobao(String user_id, String user_channel_id);

        public abstract void userVersion(String version,String user_id,String user_channel_id);

    }
}
