package com.bestsoft.mvp.contract;

import com.bestsoft.bean.ArticleModel;
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
public interface ShareAticleContract {
    interface View extends IBaseView {
        void showAticleList(List<ArticleModel> models, boolean isRefresh);

        void showError(Throwable throwable, boolean isRefresh);
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void shareAticle(int type, String user_id, String user_channel_id,
                                         boolean isRefresh);
    }
}
