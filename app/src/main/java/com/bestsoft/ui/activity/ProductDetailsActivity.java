package com.bestsoft.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.baichuan.android.trade.AlibcTrade;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeCallback;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import com.alibaba.baichuan.android.trade.model.AlibcShowParams;
import com.alibaba.baichuan.android.trade.model.OpenType;
import com.alibaba.baichuan.android.trade.model.TradeResult;
import com.alibaba.baichuan.android.trade.page.AlibcAddCartPage;
import com.alibaba.baichuan.android.trade.page.AlibcBasePage;
import com.alibaba.baichuan.android.trade.page.AlibcDetailPage;
import com.alibaba.baichuan.android.trade.page.AlibcMyCartsPage;
import com.alibaba.baichuan.android.trade.page.AlibcMyOrdersPage;
import com.alibaba.baichuan.android.trade.page.AlibcPage;
import com.alibaba.baichuan.android.trade.page.AlibcShopPage;
import com.bestsoft.R;
import com.bestsoft.base.BaseMvpActivity;
import com.bestsoft.bean.OrderConfirmModel;
import com.bestsoft.bean.ProductModel;
import com.bestsoft.common.mvp_senior.annotaions.CreatePresenterAnnotation;
import com.bestsoft.mvp.contract.ProductDetailsContract;
import com.bestsoft.mvp.presenter.ProductDetailsPresenter;
import com.bestsoft.ui.widget.GlideImageLoader;
import com.youth.banner.Banner;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 商品详情
 */
@CreatePresenterAnnotation(ProductDetailsPresenter.class)
public class ProductDetailsActivity extends BaseMvpActivity<ProductDetailsContract.View, ProductDetailsPresenter> implements ProductDetailsContract.View {
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.txt_confirm)
    TextView txtConfirm;
    private String itemId;
    private ProductModel result;

    @Override
    protected int getLayout() {
        return R.layout.activity_product_details;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void getIntentData() {
        super.getIntentData();
        Bundle bundle = getIntent().getBundleExtra("bundle");
        itemId = bundle.getString("item_id");
        getMvpPresenter().getHaoDetail(itemId, userModel.getId(), userModel.getChannel_id());
    }

    @Override
    public void setResult(ProductModel result) {
        this.result = result;
        banner.setImages(result.getItem_pic())
                .setImageLoader(new GlideImageLoader())
                .start();
    }

    @Override
    public void orderConfirm(OrderConfirmModel orderConfirmModel) {
//提供给三方传递配置参数
//        Map<String, String> exParams = new HashMap<>();
//        exParams.put(AlibcConstants.ISV_CODE, "appisvcode");
//
//        //商品详情page
//        AlibcBasePage detailPage = new AlibcDetailPage(itemId);
//
//        //实例化店铺打开page
//        AlibcBasePage shopPage = new AlibcShopPage(shopId);
//
//        //实例化添加购物车打开page
//        AlibcBasePage addCardPage = new AlibcAddCartPage(itemId)
//
//        //实例化我的订单打开page
//        AlibcBasePage ordersPage = new AlibcMyOrdersPage(status, allOrder);
//
//        //实例化我的购物车打开page
//        AlibcBasePage myCartsPage = new AlibcMyCartsPage();
//
//        //实例化URL打开page
//        AlibcBasePage page = new AlibcPage(taokeUrl);
//
//        //设置页面打开方式
//        AlibcShowParams showParams = new AlibcShowParams(OpenType.Native, false);
//
//        //使用百川sdk提供默认的Activity打开detail
//        AlibcTrade.show(context, detailPage, showParams, null, exParams ,
//                new AlibcTradeCallback() {
//                    @Override
//                    public void onTradeSuccess(TradeResult tradeResult) {
//                        //打开电商组件，用户操作中成功信息回调。tradeResult：成功信息（结果类型：加购，支付；支付结果）
//                    }
//
//                    @Override
//                    public void onFailure(int code, String msg) {
//                        //打开电商组件，用户操作中错误信息回调。code：错误码；msg：错误信息
//                    }
//                });
    }


    @OnClick(R.id.txt_confirm)
    public void onViewClicked() {
        getMvpPresenter().orderConfirm(result.getItem_id(), result.getItemt_itle(), result.getItem_price()
                , result.getItem_end_price(), result.getTkrates(), result.getTkmoney(), userModel.getId(), userModel.getChannel_id());
    }
}
