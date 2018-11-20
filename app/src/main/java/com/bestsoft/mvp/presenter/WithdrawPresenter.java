package com.bestsoft.mvp.presenter;

import com.bestsoft.bean.UserModel;
import com.bestsoft.bean.WithDrawModel;
import com.bestsoft.common.https.BaseNoDataResponse;
import com.bestsoft.common.https.ProgressObserver;
import com.bestsoft.common.https.rxUtils.RxUtil;
import com.bestsoft.common.utils.Utils;
import com.bestsoft.mvp.contract.WithDrawContract;
import com.bestsoft.mvp.model.PersonModule;

/**
 * @package: com.bestsoft.mvp.presenter
 * @user:xhkj
 * @date:2018/10/29
 * @description:
 **/
public class WithdrawPresenter extends WithDrawContract.Presenter {


    @Override
    public void withDrawApply(String amount, String type, String user_id, String user_channel_id) {
        PersonModule.getInstance(Utils.getContext()).withdrawApply(amount,type,user_id, user_channel_id)
                .compose(RxUtil.observableIO2Main(getView()))
                .compose(RxUtil.hanResult())
                .subscribe(new ProgressObserver<WithDrawModel>(this, true, "检查更新...") {
                    @Override
                    public void onSuccess(WithDrawModel result) {
                        getView().withDrawApply(result);
                    }
                });
    }
    @Override
    public void getUserInfo(String user_id, String user_channel_id) {
        PersonModule.getInstance(Utils.getContext()).getUserInfo(user_id, user_channel_id)
                .compose(RxUtil.observableIO2Main(getView()))
                .compose(RxUtil.hanResult())
                .subscribe(new ProgressObserver<UserModel>(this, true, "加载中...") {
                    @Override
                    public void onSuccess(UserModel result) {
                        getView().setUserModel(result);
                    }

                });
    }
}
