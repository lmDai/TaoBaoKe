package com.bestsoft.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.baichuan.android.trade.AlibcTrade;
import com.alibaba.baichuan.android.trade.adapter.login.AlibcLogin;
import com.alibaba.baichuan.android.trade.callback.AlibcLoginCallback;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeCallback;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import com.alibaba.baichuan.android.trade.model.AlibcShowParams;
import com.alibaba.baichuan.android.trade.model.AlibcTaokeParams;
import com.alibaba.baichuan.android.trade.model.OpenType;
import com.alibaba.baichuan.android.trade.model.TradeResult;
import com.alibaba.baichuan.android.trade.page.AlibcBasePage;
import com.alibaba.baichuan.android.trade.page.AlibcDetailPage;
import com.alibaba.baichuan.android.trade.page.AlibcPage;
import com.bestsoft.R;
import com.bestsoft.base.BaseMvpActivity;
import com.bestsoft.bean.OrderConfirmModel;
import com.bestsoft.bean.ProductModel;
import com.bestsoft.common.mvp_senior.annotaions.CreatePresenterAnnotation;
import com.bestsoft.mvp.contract.ProductDetailsContract;
import com.bestsoft.mvp.presenter.ProductDetailsPresenter;
import com.bestsoft.ui.widget.GlideImageLoader;
import com.bestsoft.utils.GlideUtil;
import com.youth.banner.Banner;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.bestsoft.utils.IntentUtils.OPEN_ACTIVITY_KEY;

/**
 * 商品详情
 */
@CreatePresenterAnnotation(ProductDetailsPresenter.class)
public class ProductDetailsActivity extends BaseMvpActivity<ProductDetailsContract.View, ProductDetailsPresenter> implements ProductDetailsContract.View {
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.txt_confirm)
    TextView txtConfirm;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_right)
    TextView txtRight;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.txt_end_price)
    TextView txtEndPrice;
    @BindView(R.id.txt_upgrade)
    TextView txtUpgrade;
    @BindView(R.id.txt_sale)
    TextView txtSale;
    @BindView(R.id.txt_money)
    TextView txtMoney;
    @BindView(R.id.txt_tkmoney)
    TextView txtTkmoney;
    @BindView(R.id.txt_couponmoney)
    TextView txtCouponmoney;
    @BindView(R.id.txt_time)
    TextView txtTime;
    @BindView(R.id.txt_seller_nick)
    TextView txtSellerNick;
    @BindView(R.id.img_pic)
    ImageView imgPic;
    @BindView(R.id.txt_title_product)
    TextView txtTitleProduct;
    private String itemId;
    private ProductModel result;

    @Override
    protected int getLayout() {
        return R.layout.activity_product_details;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        txtTitle.setText("商品详情");

    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.toolbar)
                .statusBarColor(R.color.colorWhite)
                .statusBarDarkFont(true, 0.2f)
                .init();
    }

    @Override
    protected void getIntentData() {
        super.getIntentData();
        ProductModel productModel = getIntent().getParcelableExtra(OPEN_ACTIVITY_KEY);
        itemId = productModel.getItem_id();
        getMvpPresenter().getHaoDetail(productModel.getItem_id(), userModel.getId(), userModel.getUser_channel_id());
        if (userModel.getLevel() == 1) {
            txtUpgrade.setVisibility(View.GONE);
        } else {
            txtUpgrade.setVisibility(View.VISIBLE);
            txtUpgrade.setText("赚¥" + productModel.getUpgrade());
        }
    }

    @Override
    public void setResult(ProductModel result) {
        this.result = result;
        banner.setImages(result.getItem_pic())
                .setImageLoader(new GlideImageLoader())
                .start();
        txtEndPrice.setText("¥" + result.getItem_end_price());//商品券后价格
        txtUpgrade.setText("赚¥" + result.getUpgrade());
        txtSale.setText("已售" + result.getItem_sale() + "件");
        txtMoney.setText("原价：" + result.getItem_price());
        txtTkmoney.setText("现在升级VIP最高可得佣金¥" + result.getTkmoney());
        txtTitleProduct.setText(result.getItem_title());
        txtCouponmoney.setText(result.getCouponmoney() + "元优惠券");
        txtTime.setText("使用时间:" + result.getCouponstarttime() + "-" + result.getCouponendtime());
        txtSellerNick.setText(result.getSellernick());
        GlideUtil.into(mContext, result.getItem_pic_copy(), imgPic);
    }

    @Override
    public void orderConfirm(OrderConfirmModel orderConfirmModel) {
//        login();
//提供给三方传递配置参数
        Map<String, String> exParams = new HashMap<>();
        exParams.put(AlibcConstants.ISV_CODE, "appisvcode");

        //商品详情page
        AlibcBasePage detailPage = new AlibcDetailPage(itemId);

//        //实例化店铺打开page
//        AlibcBasePage shopPage = new AlibcShopPage(shopId);
//
//        //实例化添加购物车打开page
//        AlibcBasePage addCardPage = new AlibcAddCartPage(itemId);
//
//        //实例化我的订单打开page
//        AlibcBasePage ordersPage = new AlibcMyOrdersPage(status, allOrder);
//
//        //实例化我的购物车打开page
//        AlibcBasePage myCartsPage = new AlibcMyCartsPage();
//
        //实例化URL打开page
//        AlibcBasePage page = new AlibcPage(result.getCouponurl());

        //设置页面打开方式
        AlibcShowParams showParams = new AlibcShowParams(OpenType.H5, false);
        AlibcTaokeParams taokeParams = new AlibcTaokeParams(orderConfirmModel.getTaobao_pid(), orderConfirmModel.getTaobao_pid(), null);

        //使用百川sdk提供默认的Activity打开detail
        AlibcTrade.show(mContext, detailPage, showParams, taokeParams, exParams,
                new AlibcTradeCallback() {
                    @Override
                    public void onTradeSuccess(TradeResult tradeResult) {
                        //打开电商组件，用户操作中成功信息回调。tradeResult：成功信息（结果类型：加购，支付；支付结果）
                        Log.i("single", "onTradeSuccess");
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        //打开电商组件，用户操作中错误信息回调。code：错误码；msg：错误信息
                        Log.i("single", "onFailure" + msg + code);
                    }
                });
    }


    @OnClick(R.id.txt_confirm)
    public void onViewClicked() {
        getMvpPresenter().orderConfirm(result.getItem_id(), result.getItem_title(), result.getItem_price()
                , result.getItem_end_price(), result.getTkrates(), result.getTkmoney() + "", userModel.getId(), userModel.getUser_channel_id()
                , result.getCouponmoney());
    }

    public void login() {
        final AlibcLogin alibcLogin = AlibcLogin.getInstance();
        alibcLogin.showLogin(mContext, new AlibcLoginCallback() {
            @Override
            public void onSuccess() {
                Log.i("single", "onSuccessdengludfasdfds");
            }

            @Override
            public void onFailure(int i, String s) {
                Log.i("single", "onFailure" + s);
            }
        });
    }
}
