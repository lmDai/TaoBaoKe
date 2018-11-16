package com.bestsoft.mvp.contract;

import com.bestsoft.bean.IncomeDetailModel;
import com.bestsoft.bean.ProductModel;
import com.bestsoft.common.https.BasePresenter;
import com.bestsoft.common.https.IBaseView;

import java.util.List;

/**
 * @package: com.bestsoft.mvp.contract
 * @user:xhkj
 * @date:2018/10/29
 * @description:注册
 **/
public interface InComeListContract {
    interface View extends IBaseView {
        void showUserBillList(List<IncomeDetailModel> models, String total, boolean isRefresh);

        void showError(Throwable throwable, boolean isRefresh);
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getUserBills(String type, String user_id, String user_channel_id,
                                          boolean isRefresh);
    }
}
