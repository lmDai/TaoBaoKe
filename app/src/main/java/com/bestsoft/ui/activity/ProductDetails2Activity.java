package com.bestsoft.ui.activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bestsoft.R;
import com.bestsoft.base.BaseMvpActivity;
import com.bestsoft.bean.GoodsShareModel;
import com.bestsoft.common.mvp_senior.annotaions.CreatePresenterAnnotation;
import com.bestsoft.mvp.contract.GoodsShareContract;
import com.bestsoft.mvp.presenter.GoodsSharePresenter;
import com.bestsoft.utils.GlideUtil;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * 商品详情
 */
@CreatePresenterAnnotation(GoodsSharePresenter.class)
public class ProductDetails2Activity extends BaseMvpActivity<GoodsShareContract.View, GoodsSharePresenter> implements GoodsShareContract.View {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_right)
    TextView txtRight;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.item_title)
    TextView itemTitle;
    @BindView(R.id.item_end_price)
    TextView itemEndPrice;
    @BindView(R.id.commission)
    TextView commission;
    @BindView(R.id.original_article)
    TextView originalArticle;
    @BindView(R.id.item_pic)
    ImageView itemPic;
    @BindView(R.id.item_end_price1)
    TextView itemEndPrice1;
    @BindView(R.id.commission1)
    TextView commission1;
    @BindView(R.id.tapkoul)
    TextView tapkoul;
    @BindView(R.id.txt_copy)
    TextView txtCopy;
    @BindView(R.id.txt_share_wechat)
    TextView txtShareWechat;
    @BindView(R.id.txt_share_wechat_circle)
    TextView txtShareWechatCircle;
    @BindView(R.id.txt_share_qq)
    TextView txtShareQq;
    @BindView(R.id.txt_share_qzone)
    TextView txtShareQzone;
    @BindView(R.id.dialog_zhifubao)
    LinearLayout dialogZhifubao;
    private String itemId;
    private GoodsShareModel result;

    @Override
    protected int getLayout() {
        return R.layout.activity_product_details2;
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
        Bundle bundle = getIntent().getBundleExtra("bundle");
        if (bundle != null) {
            itemId = bundle.getString("item_id");
        }
        getMvpPresenter().goodsShare(itemId, userModel.getId(), userModel.getUser_channel_id());
    }

    @Override
    public void setResult(GoodsShareModel result) {
        this.result = result;
        itemTitle.setText(result.getItem_title());
        itemEndPrice.setText("¥" + result.getItem_end_price());
        itemEndPrice1.setText("¥" + result.getItem_end_price());
        commission.setText("¥" + result.getCommission());
        commission1.setText("¥" + result.getCommission());
        tapkoul.setText("复制这条评论信息，" + result.getTapkoul() + "，打开【手机淘宝】即可查看");
        originalArticle.setText(result.getOriginal_article());
        if (result.getItem_pic() != null && result.getItem_pic().size() > 0)
            GlideUtil.into(mContext, result.getItem_pic().get(0), itemPic);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.img_back, R.id.txt_copy, R.id.txt_share_wechat, R.id.txt_share_wechat_circle, R.id.txt_share_qq, R.id.txt_share_qzone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                break;
            case R.id.txt_copy:
                ClipboardManager cm = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                cm.setText(result.getTapkoul());
                ToastUtils.showShort("复制成功");
                break;
            case R.id.txt_share_wechat:
                share(1);
                break;
            case R.id.txt_share_wechat_circle:
                share(2);
                break;
            case R.id.txt_share_qq:
                share(3);
                break;
            case R.id.txt_share_qzone:
                share(4);
                break;
        }
    }

    private void share(int type) {
        Platform.ShareParams sp = new Platform.ShareParams();
        sp.setImageUrl(result.getShare_img());
        sp.setShareType(Platform.SHARE_IMAGE);
        Platform platform = null;
        switch (type) {
            case 2:
                platform = ShareSDK.getPlatform(WechatMoments.NAME);
                break;
            case 1:
                platform = ShareSDK.getPlatform(Wechat.NAME);
                break;
            case 3:
                platform = ShareSDK.getPlatform(QQ.NAME);
                break;
            case 4:
                platform = ShareSDK.getPlatform(QZone.NAME);
                break;
        }
        // 设置分享事件回调（注：回调放在不能保证在主线程调用，不可以在里面直接处理UI操作）
        platform.setPlatformActionListener(new PlatformActionListener() {
            public void onError(Platform arg0, int arg1, Throwable arg2) {
                //失败的回调，arg:平台对象，arg1:表示当前的动作，arg2:异常信息
                LogUtils.i("onError" + arg2.getMessage());
            }

            public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
                //分享成功的回调
                LogUtils.i("onComplete", JSON.toJSONString(arg2));
            }

            public void onCancel(Platform arg0, int arg1) {
                //取消分享的回调
                LogUtils.i("onCancel");
            }
        });
        // 执行图文分享
        platform.share(sp);
    }
}
