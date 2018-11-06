package com.bestsoft.mvp.presenter;

import com.bestsoft.common.https.ProgressObserver;
import com.bestsoft.common.https.rxUtils.RxUtil;
import com.bestsoft.common.utils.Utils;
import com.bestsoft.mvp.contract.InputInvateInfoContract;
import com.bestsoft.mvp.model.LoginModel;

import java.util.List;

/**
 * @package: com.bestsoft.mvp.presenter
 * @user:xhkj
 * @date:2018/10/29
 * @description:
 **/
public class InputInvateInfoPresenter extends InputInvateInfoContract.Presenter {
    @Override
    public void getInvateInfo() {
        LoginModel.getInstance(Utils.getContext()).getTag()
        .compose(RxUtil.observableIO2Main(getView()))
        .compose(RxUtil.hanResult())
        .subscribe(new ProgressObserver<List<String>>(this,true) {
            @Override
            public void onSuccess(List<String> result) {

            }
        });
    }
}
