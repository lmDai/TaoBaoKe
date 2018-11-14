package com.bestsoft.mvp.presenter;

import android.support.annotation.NonNull;

import com.bestsoft.bean.OrderConfirmModel;
import com.bestsoft.bean.ProductModel;
import com.bestsoft.common.https.ProgressObserver;
import com.bestsoft.common.https.rxUtils.RxUtil;
import com.bestsoft.common.utils.Utils;
import com.bestsoft.mvp.contract.ProductDetailsContract;
import com.bestsoft.mvp.model.MainModel;


/**
 * @package: com.bestsoft.mvp.presenter
 * @user:xhkj
 * @date:2018/10/29
 * @description: 注册
 **/
public class ProductDetailsPresenter extends ProductDetailsContract.Presenter {
    @Override
    public void getHaoDetail(String item_id, String user_id, String user_chanel_id) {
        MainModel.getInstance(Utils.getContext()).getHaoDetail(item_id, user_id, user_chanel_id)
                .compose(RxUtil.observableIO2Main(getView()))
                .compose(RxUtil.hanResult())
                .subscribe(new ProgressObserver<ProductModel>(this, true, "请稍后...") {
                    @Override
                    public void onSuccess(ProductModel result) {
                        getView().setResult(result);//返回数据
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        super.onError(e);
                    }
                });
    }

    @Override
    public void orderConfirm(String item_id, String item_title, String item_price,
                             String item_end_price, String tkrates, String tkmoney,
                             String user_id, String user_channel_id,String couponmoney) {
        MainModel.getInstance(Utils.getContext()).orderConfirm(item_id, item_title, item_price,
                item_end_price, tkrates, tkmoney, user_id, user_channel_id,couponmoney)
                .compose(RxUtil.observableIO2Main(getView()))
                .compose(RxUtil.hanResult())
                .subscribe(new ProgressObserver<OrderConfirmModel>(this, true, "请稍后...") {
                    @Override
                    public void onSuccess(OrderConfirmModel result) {
                        getView().orderConfirm(result);//返回数据
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        super.onError(e);
                    }
                });
    }
}
