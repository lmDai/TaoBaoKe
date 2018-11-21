package com.bestsoft.mvp.presenter;

import android.support.annotation.NonNull;

import com.bestsoft.bean.GoodsShareModel;
import com.bestsoft.bean.OrderConfirmModel;
import com.bestsoft.bean.ProductModel;
import com.bestsoft.common.https.BaseNoDataResponse;
import com.bestsoft.common.https.ProgressObserver;
import com.bestsoft.common.https.rxUtils.RxUtil;
import com.bestsoft.common.utils.Utils;
import com.bestsoft.mvp.contract.GoodsShareContract;
import com.bestsoft.mvp.contract.ProductDetailsContract;
import com.bestsoft.mvp.model.MainModel;


/**
 * @package: com.bestsoft.mvp.presenter
 * @user:xhkj
 * @date:2018/10/29
 * @description: 注册
 **/
public class GoodsSharePresenter extends GoodsShareContract.Presenter {

    @Override
    public void goodsShare(String item_id, String user_id, String user_chanel_id) {
        MainModel.getInstance(Utils.getContext()).goodsShare(item_id, user_id, user_chanel_id)
                .compose(RxUtil.observableIO2Main(getView()))
                .compose(RxUtil.hanResult())
                .subscribe(new ProgressObserver<GoodsShareModel>(this, true, "请稍后...") {
                    @Override
                    public void onSuccess(GoodsShareModel result) {
                        getView().setResult(result);//返回数据
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        super.onError(e);
                    }
                });
    }
}
