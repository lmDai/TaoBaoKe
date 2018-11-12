package com.bestsoft.mvp.contract;

import com.bestsoft.bean.CodeModel;
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
        void setCodeInfo(CodeModel codeInfo);

        void sendCodeSuccess();//验证码发送成功

        void registerSuccess();//注册成功
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getInvateInfo(String invite_code);

        public abstract void sendSmsCode(String phone, int type,String user_channel_id);

        public abstract void userRegister(String phone, String smscode, String user_chanel_id, String pid);
    }
}
