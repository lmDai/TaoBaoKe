package com.bestsoft.mvp.presenter;

import com.bestsoft.bean.UserModel;
import com.bestsoft.common.https.ProgressObserver;
import com.bestsoft.common.https.rxUtils.RxUtil;
import com.bestsoft.common.utils.Utils;
import com.bestsoft.mvp.contract.PersonalContract;
import com.bestsoft.mvp.model.PersonModel;

/**
 * @package: com.bestsoft.mvp.presenter
 * @user:xhkj
 * @date:2018/10/29
 * @description:
 **/
public class PersonalPresenter extends PersonalContract.Presenter {

    @Override
    public void getUserInfo(String user_id, String user_channel_id) {
        PersonModel.getInstance(Utils.getContext()).getUserInfo(user_id, user_channel_id)
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
