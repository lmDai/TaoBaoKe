package com.bestsoft.mvp.contract;

import com.bestsoft.bean.ProductModel;
import com.bestsoft.common.https.BasePresenter;
import com.bestsoft.common.https.IBaseView;

import java.util.List;

/**
 * @package: com.bestsoft.mvp.contract
 * @user:xhkj
 * @date:2018/10/29
 * @description:会员升级
 **/
public interface SearchDetailsContract {
    interface View extends IBaseView {
        void setProductList(List<ProductModel> models, boolean isRefresh);

        void showError(Throwable throwable, boolean isRefresh);
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getGoodsSearch(String keyword, String sort
                , String user_id, String user_channel_id, int user_level, boolean isRefresh);
    }
}
