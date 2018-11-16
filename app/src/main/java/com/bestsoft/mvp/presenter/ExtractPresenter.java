package com.bestsoft.mvp.presenter;

import android.support.annotation.NonNull;

import com.bestsoft.bean.ExtractModel;
import com.bestsoft.common.https.ProgressObserver;
import com.bestsoft.common.https.rxUtils.RxUtil;
import com.bestsoft.common.utils.Utils;
import com.bestsoft.mvp.contract.ExtractContract;
import com.bestsoft.mvp.model.PersonModule;

/**
 * @package: com.bestsoft.mvp.presenter
 * @user:xhkj
 * @date:2018/10/29
 * @description:
 **/
public class ExtractPresenter extends ExtractContract.Presenter {
    @Override
    public void getUserExtract(String user_id, String user_channel_id) {
        PersonModule.getInstance(Utils.getContext()).userExtract(user_id, user_channel_id)
                .compose(RxUtil.observableIO2Main(getView()))
                .compose(RxUtil.hanResult())
                .subscribe(new ProgressObserver<ExtractModel>(this, true, "请稍后...") {
                    @Override
                    public void onSuccess(ExtractModel result) {
                        getView().setUserExtract(result);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        super.onError(e);
                        getView().showError(e);
                    }
                });
    }
}
