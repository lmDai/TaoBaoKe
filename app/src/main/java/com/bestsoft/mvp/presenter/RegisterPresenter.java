package com.bestsoft.mvp.presenter;

import com.bestsoft.common.https.BaseNoDataResponse;
import com.bestsoft.common.https.ProgressObserver;
import com.bestsoft.common.https.rxUtils.RxUtil;
import com.bestsoft.common.utils.Utils;
import com.bestsoft.mvp.contract.RegisterContract;
import com.bestsoft.mvp.model.LoginModel;

/**
 * @package: com.bestsoft.mvp.presenter
 * @user:xhkj
 * @date:2018/10/29
 * @description: 注册
 **/
public class RegisterPresenter extends RegisterContract.Presenter {

    @Override
    public void userRegister(String nickName,String headimgurl,String openid,String type,String phone, String smscode, String user_chanel_id, String pid) {
        LoginModel.getInstance(Utils.getContext()).userRegister(nickName,headimgurl,openid,type,phone, smscode, user_chanel_id, pid)
                .compose(RxUtil.observableIO2Main(getView()))
                .compose(RxUtil.handNoResponseResult())
                .subscribe(new ProgressObserver<BaseNoDataResponse>(this, true, "请稍后...") {
                    @Override
                    public void onSuccess(BaseNoDataResponse result) {
                        getView().registerSuccess(result);//注册成功
                    }
                });
    }
}
