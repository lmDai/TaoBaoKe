package com.bestsoft.mvp.contract;

import com.bestsoft.bean.ChartModel;
import com.bestsoft.common.https.BasePresenter;
import com.bestsoft.common.https.IBaseView;

/**
 * @package: com.bestsoft.mvp.contract
 * @user:xhkj
 * @date:2018/10/29
 * @description:会员升级
 **/
public interface ChartDataContract {
    interface View extends IBaseView {
        void setChartData(ChartModel models);

        void showError(Throwable throwable);
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getUserChart(String user_id, String user_channel_id);
    }
}
