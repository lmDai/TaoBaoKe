package com.bestsoft.mvp.presenter;

import android.support.annotation.NonNull;

import com.bestsoft.bean.ProductModel;
import com.bestsoft.common.https.ProgressObserver;
import com.bestsoft.common.https.rxUtils.RxUtil;
import com.bestsoft.common.utils.Utils;
import com.bestsoft.mvp.contract.ProductListContract;
import com.bestsoft.mvp.contract.RegisterContract;
import com.bestsoft.mvp.model.LoginModel;
import com.bestsoft.mvp.model.MainModel;

import java.util.List;

/**
 * @package: com.bestsoft.mvp.presenter
 * @user:xhkj
 * @date:2018/10/29
 * @description: 注册
 **/
public class ProductListPresenter extends ProductListContract.Presenter {
    private int currentPage = 1;

    @Override
    public void getGoodHaoList(String key, String sort, String user_id, String user_channel_id, boolean isRefresh) {
        if (isRefresh) {
            currentPage = 1;
        } else {
            ++currentPage;
        }
        MainModel.getInstance(Utils.getContext()).getGoodHaoList(key, sort, currentPage + "", user_id, user_channel_id)
                .compose(RxUtil.observableIO2Main(getView()))
                .compose(RxUtil.hanResult())
                .subscribe(new ProgressObserver<List<ProductModel>>(this, true, "请稍后...") {
                    @Override
                    public void onSuccess(List<ProductModel> result) {
                        getView().showProductList(result, isRefresh);//注册成功
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        super.onError(e);
                    }
                });
    }
}
