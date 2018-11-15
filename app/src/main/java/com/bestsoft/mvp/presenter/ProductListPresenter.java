package com.bestsoft.mvp.presenter;

import android.support.annotation.NonNull;

import com.bestsoft.bean.ProductModel;
import com.bestsoft.common.https.BaseNoDataResponse;
import com.bestsoft.common.https.BasePageResponse;
import com.bestsoft.common.https.ProgressObserver;
import com.bestsoft.common.https.rxUtils.RxUtil;
import com.bestsoft.common.mvp_senior.annotaions.CreatePresenterAnnotation;
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
@CreatePresenterAnnotation(ProductListPresenter.class)
public class ProductListPresenter extends ProductListContract.Presenter {
    private String currentPage = "1";

    @Override
    public void getGoodHaoList(String key, String sort, String user_id, String user_channel_id, int user_level, boolean isRefresh) {
        if (isRefresh) {
            currentPage = "1";
        }
        MainModel.getInstance(Utils.getContext()).getGoodHaoList(key, sort, currentPage,
                user_id, user_channel_id, user_level)
                .compose(RxUtil.observableIO2Main(getView()))
                .compose(RxUtil.handNoResponseResult())
                .subscribe(new ProgressObserver<BasePageResponse<List<ProductModel>>>(this, true, "请稍后...") {
                    @Override
                    public void onSuccess(BasePageResponse<List<ProductModel>> result) {
                        if (result.getErrorcode() == 0) {
                            currentPage = result.getNext();
                            getView().showProductList(result.getData(), isRefresh);//注册成功
                        } else {
                            getView().showError(new Throwable(), isRefresh);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        super.onError(e);
                        getView().showError(e, isRefresh);
                    }
                });
    }
}
