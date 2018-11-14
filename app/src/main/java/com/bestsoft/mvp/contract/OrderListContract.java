package com.bestsoft.mvp.contract;

import com.bestsoft.bean.OrderModel;
import com.bestsoft.bean.ProductModel;
import com.bestsoft.common.https.BasePresenter;
import com.bestsoft.common.https.IBaseView;

import java.util.List;

/**
 * @package: com.bestsoft.mvp.contract
 * @user:xhkj
 * @date:2018/10/29
 * @description:会员订单数据
 **/
public interface OrderListContract {
    interface View extends IBaseView {
        void showOrderList(List<OrderModel> models, boolean isRefresh);

        void showError(Throwable throwable, boolean isRefresh);
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getUserOrder(String order_status, String user_id, String user_channel_id,
                                          boolean isRefresh);
    }
}
