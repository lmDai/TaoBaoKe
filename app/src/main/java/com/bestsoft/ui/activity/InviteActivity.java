package com.bestsoft.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bestsoft.R;
import com.bestsoft.base.BaseActivity;
import com.bestsoft.base.BaseMvpActivity;
import com.bestsoft.bean.RuleModel;
import com.bestsoft.common.https.BaseNoDataResponse;
import com.bestsoft.common.mvp_senior.annotaions.CreatePresenterAnnotation;
import com.bestsoft.mvp.contract.ShareInviteContract;
import com.bestsoft.mvp.model.ShareInviteTempModel;
import com.bestsoft.mvp.presenter.ShareInvitePresenter;
import com.bestsoft.ui.adapter.ImgPagerAdapter;
import com.bestsoft.ui.adapter.RuleAdapter;
import com.bestsoft.ui.widget.GallyPageTransformer;
import com.bestsoft.utils.AppManager;
import com.bestsoft.utils.DialogUtils;
import com.bestsoft.utils.RecyclerViewUtils;
import com.bestsoft.utils.ShareDialogListener;
import com.blankj.utilcode.util.LogUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * 邀请粉丝
 */
@CreatePresenterAnnotation(ShareInvitePresenter.class)
public class InviteActivity extends BaseMvpActivity<ShareInviteContract.View, ShareInvitePresenter> implements ShareInviteContract.View {

    @BindView(R.id.img_me)
    ImageView imgMe;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.img_message)
    ImageView imgMessage;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.txt_position)
    TextView txtPosition;
    @BindView(R.id.txt_total)
    TextView txtTotal;
    @BindView(R.id.view_pager_theme)
    ViewPager viewPagerTheme;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.txt_invite_code)
    TextView txtInviteCode;
    private RuleAdapter ruleAdapter;
    private int pagerWidth;
    private int i = 0;
    private List<ShareInviteTempModel> settingResult;

    @Override
    protected int getLayout() {
        return R.layout.activity_invite;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        txtTitle.setText(mContext.getString(R.string.title_invite));
        txtInviteCode.setText("我的邀请码:" + userModel.getInvite_code());
        viewPagerTheme.setOffscreenPageLimit(4);
        pagerWidth = (int) (getResources().getDisplayMetrics().widthPixels * 3.0f / 6.0f);
        ViewGroup.LayoutParams lp = viewPagerTheme.getLayoutParams();
        if (lp == null) {
            lp = new ViewGroup.LayoutParams(pagerWidth, ViewGroup.LayoutParams.MATCH_PARENT);
        } else {
            lp.width = pagerWidth;
        }
        viewPagerTheme.setLayoutParams(lp);
        viewPagerTheme.setPageMargin(-20);
        viewPagerTheme.setPageTransformer(true, new GallyPageTransformer());
        ruleAdapter = new RuleAdapter(R.layout.item_rule);
        RecyclerViewUtils.initLinerLayoutRecyclerView(recyclerView, mContext);
        recyclerView.setAdapter(ruleAdapter);
        initData();
        getMvpPresenter().shareInviteTemp(userModel.getId(), userModel.getUser_channel_id());
    }

    private void initData() {
        List<RuleModel> modelList = new ArrayList<>();
        final String[] proName = mContext.getResources().getStringArray(R.array.rule);
        for (int a = 0; a < proName.length; a++) {
            modelList.add(new RuleModel(proName[a]));
        }
        ruleAdapter.setNewData(modelList);
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.toolbar)
                .init();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getMvpPresenter().shareInviteTemp(userModel.getId(), userModel.getUser_channel_id());
            }
        });
        viewPagerTheme.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                InviteActivity.this.i = i;
                txtPosition.setText("选择主题" + (i + 1) + "/");
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @OnClick({R.id.img_me, R.id.img_message, R.id.btn_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_me:
                AppManager.getAppManager().removeActivity(this);
                finish();
                break;
            case R.id.img_message:
                refreshLayout.autoRefresh();
                break;
            case R.id.btn_share:
                DialogUtils.showDialogShare(mContext, new ShareDialogListener() {
                    @Override
                    public void onClick(boolean confirm, int type) {
                        if (confirm) {
                            Platform.ShareParams sp = new Platform.ShareParams();
                            sp.setImageUrl(settingResult.get(i).getUrl());
                            sp.setShareType(Platform.SHARE_IMAGE);
                            Platform platform = null;
                            switch (type) {
                                case 1:
                                    platform = ShareSDK.getPlatform(WechatMoments.NAME);
                                    break;
                                case 2:
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
                });
                break;
        }
    }

    @Override
    public void shareInviteTemp(List<ShareInviteTempModel> settingResult) {
        this.settingResult = settingResult;
        txtTotal.setText(String.valueOf(settingResult.size()));
        refreshLayout.finishRefresh();
        viewPagerTheme.setAdapter(new ImgPagerAdapter(settingResult, this));
    }
}
