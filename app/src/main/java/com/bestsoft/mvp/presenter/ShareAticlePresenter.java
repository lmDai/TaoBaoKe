package com.bestsoft.mvp.presenter;

import android.support.annotation.NonNull;

import com.bestsoft.bean.ArticleModel;
import com.bestsoft.bean.ProductModel;
import com.bestsoft.common.https.BasePageResponse;
import com.bestsoft.common.https.ProgressObserver;
import com.bestsoft.common.https.rxUtils.RxUtil;
import com.bestsoft.common.mvp_senior.annotaions.CreatePresenterAnnotation;
import com.bestsoft.common.utils.Utils;
import com.bestsoft.mvp.contract.ProductListContract;
import com.bestsoft.mvp.contract.ShareAticleContract;
import com.bestsoft.mvp.model.MainModel;
import com.bestsoft.mvp.model.SkillModule;

import java.util.List;

/**
 * @package: com.bestsoft.mvp.presenter
 * @user:xhkj
 * @date:2018/10/29
 * @description: 注册
 **/

public class ShareAticlePresenter extends ShareAticleContract.Presenter {
    private int currentPage = 1;

    @Override
    public void shareAticle(int type, String user_id, String user_channel_id, boolean isRefresh) {
        if (isRefresh) {
            currentPage = 1;
        } else {
            ++currentPage;
        }
        SkillModule.getInstance(Utils.getContext()).shareAticle(type, currentPage,
                user_id, user_channel_id)
                .compose(RxUtil.observableIO2Main(getView()))
                .compose(RxUtil.hanResult())
                .subscribe(new ProgressObserver<List<ArticleModel>>(this, true, "请稍后...") {
                    @Override
                    public void onSuccess(List<ArticleModel> result) {
                            getView().showAticleList(result, isRefresh);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        super.onError(e);
                        getView().showError(e, isRefresh);
                    }
                });
    }
}
