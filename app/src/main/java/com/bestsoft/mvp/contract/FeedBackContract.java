package com.bestsoft.mvp.contract;

import com.bestsoft.common.https.BaseNoDataResponse;
import com.bestsoft.common.https.BasePresenter;
import com.bestsoft.common.https.IBaseView;
import com.bestsoft.mvp.model.ShareInviteTempModel;

import java.util.List;

/**
 * @package: com.bestsoft.mvp.contract
 * @user:xhkj
 * @date:2018/10/29
 * @description:登陆
 **/
public interface FeedBackContract {
    interface View extends IBaseView {

        void setFeedBackStatus(BaseNoDataResponse settingResult);
    }

    abstract class Presenter extends BasePresenter<View> {


        public abstract void feedBack(String content, String contact, String user_id, String user_channel_id);

    }
}
