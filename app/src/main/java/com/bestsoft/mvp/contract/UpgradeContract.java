package com.bestsoft.mvp.contract;

import com.bestsoft.bean.UpgradeModel;
import com.bestsoft.bean.UserModel;
import com.bestsoft.common.https.BasePresenter;
import com.bestsoft.common.https.IBaseView;

/**
 * @package: com.bestsoft.mvp.contract
 * @user:xhkj
 * @date:2018/10/29
 * @description:会员升级
 **/
public interface UpgradeContract {
    interface View extends IBaseView {
        void setUpgrade(UpgradeModel models);

        void showError(Throwable throwable);

        void showPayPage(String page);
        void setUserModel(UserModel userModel);
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getUserUpgrade(String user_id, String user_channel_id);
        public abstract void getUpgradeApply(String user_id,String user_channel_id);
        public abstract void getUpgradePay(String orderId);
        public abstract void getUserInfo(String user_id, String user_channel_id);
    }
}
