package com.bestsoft.mvp.presenter;

import android.support.annotation.NonNull;

import com.bestsoft.bean.UpgradeModel;
import com.bestsoft.common.https.ProgressObserver;
import com.bestsoft.common.https.rxUtils.RxUtil;
import com.bestsoft.common.utils.Utils;
import com.bestsoft.mvp.contract.UpgradeContract;
import com.bestsoft.mvp.model.MemberModule;

/**
 * @package: com.bestsoft.mvp.presenter
 * @user:xhkj
 * @date:2018/10/29
 * @description: 会员升级
 **/
public class UpgradePresenter extends UpgradeContract.Presenter {
    @Override
    public void getUserUpgrade(String user_id, String user_channel_id) {
        MemberModule.getInstance(Utils.getContext()).userUpgrade(user_id, user_channel_id)
                .compose(RxUtil.observableIO2Main(getView()))
                .compose(RxUtil.hanResult())
                .subscribe(new ProgressObserver<UpgradeModel>(this, true, "请稍后...") {
                    @Override
                    public void onSuccess(UpgradeModel result) {
                        getView().setUpgrade(result);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        super.onError(e);
                        getView().showError(e);
                    }
                });
    }
}
