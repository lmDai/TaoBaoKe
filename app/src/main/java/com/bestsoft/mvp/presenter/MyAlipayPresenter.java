package com.bestsoft.mvp.presenter;

import com.bestsoft.common.https.BaseNoDataResponse;
import com.bestsoft.common.https.ProgressObserver;
import com.bestsoft.common.https.rxUtils.RxUtil;
import com.bestsoft.common.utils.Utils;
import com.bestsoft.mvp.contract.MyAlipayContract;
import com.bestsoft.mvp.model.PersonModule;

/**
 * @package: com.bestsoft.mvp.presenter
 * @user:xhkj
 * @date:2018/10/29
 * @description:
 **/
public class MyAlipayPresenter extends MyAlipayContract.Presenter {

    @Override
    public void userSettingAliPay(String account,String name,String user_id, String user_channel_id) {
        PersonModule.getInstance(Utils.getContext()).userSettingAlipay(account,name,user_id, user_channel_id)
                .compose(RxUtil.observableIO2Main(getView()))
                .compose(RxUtil.handNoResponseResult())
                .subscribe(new ProgressObserver<BaseNoDataResponse>(this, true, "设置中...") {
                    @Override
                    public void onSuccess(BaseNoDataResponse result) {
                        getView().userSettingAlipay(result);
                    }
                });
    }
}
