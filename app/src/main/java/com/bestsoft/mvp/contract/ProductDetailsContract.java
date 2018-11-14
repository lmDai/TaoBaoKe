package com.bestsoft.mvp.contract;

import com.bestsoft.bean.OrderConfirmModel;
import com.bestsoft.bean.ProductModel;
import com.bestsoft.common.https.BasePresenter;
import com.bestsoft.common.https.IBaseView;


/**
 * @package: com.bestsoft.mvp.contract
 * @user:xhkj
 * @date:2018/10/29
 * @description:注册
 **/
public interface ProductDetailsContract {
    interface View extends IBaseView {
        void setResult(ProductModel result);

        void orderConfirm(OrderConfirmModel orderConfirmModel);
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getHaoDetail(String item_id, String user_id, String user_chanel_id);

        public abstract void orderConfirm(String item_id, String item_title,
                                          String item_price, String item_end_price,
                                          String tkrates, String tkmoney,
                                          String user_id, String user_channel_id,String couponmoney);
    }
}
