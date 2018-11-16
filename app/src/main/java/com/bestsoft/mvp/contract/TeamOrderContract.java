package com.bestsoft.mvp.contract;

import com.bestsoft.bean.TeamOrderModel;
import com.bestsoft.common.https.BasePresenter;
import com.bestsoft.common.https.IBaseView;

import java.util.List;

/**
 * @package: com.bestsoft.mvp.contract
 * @user:xhkj
 * @date:2018/10/29
 * @description:会员团队订单
 **/
public interface TeamOrderContract {
    interface View extends IBaseView {
        void showTeamOrders(List<TeamOrderModel> models, boolean isRefresh);
        void showError(Throwable throwable, boolean isRefresh);
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getTeamOrders(int order_status, int order_type,String user_id, String user_channel_id,
                                            boolean isRefresh);
    }
}
