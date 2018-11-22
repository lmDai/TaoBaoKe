package com.bestsoft.mvp.presenter;

import com.bestsoft.common.https.BaseNoDataResponse;
import com.bestsoft.common.https.ProgressObserver;
import com.bestsoft.common.https.rxUtils.RxUtil;
import com.bestsoft.common.utils.Utils;
import com.bestsoft.mvp.contract.ShareInviteContract;
import com.bestsoft.mvp.model.PersonModule;
import com.bestsoft.mvp.model.ShareInviteTempModel;

import java.util.List;

/**
 * @package: com.bestsoft.mvp.presenter
 * @user:xhkj
 * @date:2018/10/29
 * @description:
 **/
public class ShareInvitePresenter extends ShareInviteContract.Presenter {

    @Override
    public void shareInviteTemp(String user_id, String user_channel_id) {
        PersonModule.getInstance(Utils.getContext()).shareInviteTemp(user_id, user_channel_id)
                .compose(RxUtil.observableIO2Main(getView()))
                .compose(RxUtil.hanResult())
                .subscribe(new ProgressObserver<List<ShareInviteTempModel>>(this, false, "请稍候...") {
                    @Override
                    public void onSuccess(List<ShareInviteTempModel> result) {
                        getView().shareInviteTemp(result);
                    }
                });
    }
}
