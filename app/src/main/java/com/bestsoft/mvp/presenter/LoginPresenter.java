package com.bestsoft.mvp.presenter;

import com.bestsoft.bean.ThirdLoginModel;
import com.bestsoft.bean.UserModel;
import com.bestsoft.common.https.BaseNoDataResponse;
import com.bestsoft.common.https.ProgressObserver;
import com.bestsoft.common.https.rxUtils.RxUtil;
import com.bestsoft.common.utils.Utils;
import com.bestsoft.mvp.contract.LoginContract;
import com.bestsoft.mvp.contract.PhoneLoginContract;
import com.bestsoft.mvp.model.LoginModel;

/**
 * @package: com.bestsoft.mvp.presenter
 * @user:xhkj
 * @date:2018/10/29
 * @description:
 **/
public class LoginPresenter extends LoginContract.Presenter {

    @Override
    public void thirdLogin(String type, String openid, String user_id, String user_channel_id) {
        LoginModel.getInstance(Utils.getContext()).thirdLogin(type, openid, user_id, user_channel_id)
                .compose(RxUtil.observableIO2Main(getView()))
                .compose(RxUtil.handNoResponseResult())
                .subscribe(new ProgressObserver<ThirdLoginModel>(this, true, "登录中...") {
                    @Override
                    public void onSuccess(ThirdLoginModel result) {
                        getView().loginSuccess(result);
                    }
                });
    }
}
