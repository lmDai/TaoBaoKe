package com.bestsoft.mvp.presenter;

import com.bestsoft.bean.CodeModel;
import com.bestsoft.common.https.ProgressObserver;
import com.bestsoft.common.https.rxUtils.RxUtil;
import com.bestsoft.common.utils.Utils;
import com.bestsoft.mvp.contract.InputInvateInfoContract;
import com.bestsoft.mvp.model.LoginModel;

/**
 * @package: com.bestsoft.mvp.presenter
 * @user:xhkj
 * @date:2018/10/29
 * @description: 邀请码验证
 **/
public class InputInvateInfoPresenter extends InputInvateInfoContract.Presenter {
    @Override
    public void getInvateInfo(String invite_code) {
        LoginModel.getInstance(Utils.getContext()).validateInviteCode(invite_code)
                .compose(RxUtil.observableIO2Main(getView()))
                .compose(RxUtil.hanResult())
                .subscribe(new ProgressObserver<CodeModel>(this, true, "加载中...") {
                    @Override
                    public void onSuccess(CodeModel result) {
                        getView().setCodeInfo(result);
                    }
                });
    }

    @Override
    public void sendSmsCode(String phone, int type,String user_channel_id) {
        LoginModel.getInstance(Utils.getContext()).sendSmsCode(phone, type,user_channel_id)
                .compose(RxUtil.observableIO2Main(getView()))
                .compose(RxUtil.hanResult())
                .subscribe(new ProgressObserver<String>(this, true, "发送中...") {
                    @Override
                    public void onSuccess(String result) {
                        getView().sendCodeSuccess();//发送验证码成功
                    }
                });
    }

    @Override
    public void userRegister(String phone, String smscode, String user_chanel_id, String pid) {
        LoginModel.getInstance(Utils.getContext()).userRegister(phone, smscode, user_chanel_id, pid)
                .compose(RxUtil.observableIO2Main(getView()))
                .compose(RxUtil.hanResult())
                .subscribe(new ProgressObserver<String>(this, true, "请稍后...") {
                    @Override
                    public void onSuccess(String result) {
                        getView().registerSuccess();//注册成功
                    }
                });
    }
}
