package com.bestsoft.mvp.presenter;

import com.bestsoft.bean.ProfitModel;
import com.bestsoft.common.https.ProgressObserver;
import com.bestsoft.common.https.rxUtils.RxUtil;
import com.bestsoft.common.utils.Utils;
import com.bestsoft.mvp.contract.OrderContract;
import com.bestsoft.mvp.model.OrderModule;

/**
 * @package: com.bestsoft.mvp.presenter
 * @user:xhkj
 * @date:2018/10/29
 * @description: 会员收益数据
 **/
public class OrderPresenter extends OrderContract.Presenter {

    @Override
    public void userProfit(String user_id, String user_channel_id) {
        OrderModule.getInstance(Utils.getContext()).userProfit(user_id, user_channel_id)
                .compose(RxUtil.observableIO2Main(getView()))
                .compose(RxUtil.hanResult())
                .subscribe(new ProgressObserver<ProfitModel>(this, true, "请稍后...") {
                    @Override
                    public void onSuccess(ProfitModel result) {
                        getView().setUserProfit(result);//会员收益数据
                    }
                });
    }
}
