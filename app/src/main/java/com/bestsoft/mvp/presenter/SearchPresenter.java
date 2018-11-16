package com.bestsoft.mvp.presenter;

import android.support.annotation.NonNull;

import com.bestsoft.bean.KeyWordModel;
import com.bestsoft.common.https.ProgressObserver;
import com.bestsoft.common.https.rxUtils.RxUtil;
import com.bestsoft.common.utils.Utils;
import com.bestsoft.mvp.contract.SearchContract;
import com.bestsoft.mvp.model.MainModel;

import java.util.List;

/**
 * @package: com.bestsoft.mvp.presenter
 * @user:xhkj
 * @date:2018/10/29
 * @description: 会员升级
 **/
public class SearchPresenter extends SearchContract.Presenter {
    @Override
    public void getHotKeyWord(String user_id, String user_channel_id) {
        MainModel.getInstance(Utils.getContext()).getHotKeyWord(user_id, user_channel_id)
                .compose(RxUtil.observableIO2Main(getView()))
                .compose(RxUtil.hanResult())
                .subscribe(new ProgressObserver<List<KeyWordModel>>(this, true, "请稍后...") {
                    @Override
                    public void onSuccess(List<KeyWordModel> result) {
                        getView().setHotKeyWords(result);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        super.onError(e);
                        getView().showError(e);
                    }
                });
    }
}
