package com.bestsoft.mvp.contract;

import com.bestsoft.bean.GoodsShareModel;
import com.bestsoft.bean.OrderConfirmModel;
import com.bestsoft.bean.ProductModel;
import com.bestsoft.common.https.BaseNoDataResponse;
import com.bestsoft.common.https.BasePresenter;
import com.bestsoft.common.https.IBaseView;


/**
 * @package: com.bestsoft.mvp.contract
 * @user:xhkj
 * @date:2018/10/29
 * @description:注册
 **/
public interface GoodsShareContract {
    interface View extends IBaseView {
        void setResult(GoodsShareModel result);

    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void goodsShare(String item_id, String user_id, String user_chanel_id);
    }
}
