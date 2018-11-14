package com.bestsoft.mvp.presenter;

import android.support.annotation.NonNull;

import com.bestsoft.bean.ProductModel;
import com.bestsoft.common.https.ProgressObserver;
import com.bestsoft.common.https.rxUtils.RxUtil;
import com.bestsoft.common.utils.Utils;
import com.bestsoft.mvp.contract.SearchDetailsContract;
import com.bestsoft.mvp.model.MainModel;

import java.util.List;

/**
 * @package: com.bestsoft.mvp.presenter
 * @user:xhkj
 * @date:2018/10/29
 * @description: 会员升级
 **/
public class SearchDetailsPresenter extends SearchDetailsContract.Presenter {
    private int currentPage = 1;

    @Override
    public void getGoodsSearch(String keyword, String sort, String user_id, String user_channel_id, int user_level, boolean isRefresh) {
        if (isRefresh) {
            currentPage = 1;
        } else {
            ++currentPage;
        }
        MainModel.getInstance(Utils.getContext()).getGoodsSearch(keyword, sort, currentPage + "", user_id, user_channel_id, user_level)
                .compose(RxUtil.observableIO2Main(getView()))
                .compose(RxUtil.hanResult())
                .subscribe(new ProgressObserver<List<ProductModel>>(this, true, "请稍后...") {
                    @Override
                    public void onSuccess(List<ProductModel> result) {
                        getView().setProductList(result,isRefresh);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        super.onError(e);
                        getView().showError(e, isRefresh);
                    }
                });
    }
}
