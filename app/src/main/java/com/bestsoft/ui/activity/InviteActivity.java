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
import com.bestsoft.utils.RecyclerViewUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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
        List<String> imageViews = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            imageViews.add("https://img.alicdn.com/imgextra/i2/1634291101/O1CN011K0IXB8UKZjVRqG_!!1634291101.jpg");
        }

        ruleAdapter = new RuleAdapter(R.layout.item_rule);
        RecyclerViewUtils.initLinerLayoutRecyclerView(recyclerView, mContext);
        recyclerView.setAdapter(ruleAdapter);
        initData();
        getMvpPresenter().shareInviteTemp(userModel.getId(), userModel.getUser_channel_id());
    }

    private void initData() {
        List<RuleModel> modelList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            modelList.add(new RuleModel());
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
                txtPosition.setText("选择主题" + (i + 1) + "/");
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @OnClick({R.id.img_me, R.id.img_message})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_me:
                AppManager.getAppManager().removeActivity(this);
                finish();
                break;
            case R.id.img_message:
                refreshLayout.autoRefresh();
                break;
        }
    }

    @Override
    public void shareInviteTemp(List<ShareInviteTempModel> settingResult) {
        txtTotal.setText(String.valueOf(settingResult.size()));
        refreshLayout.finishRefresh();
        viewPagerTheme.setAdapter(new ImgPagerAdapter(settingResult, this));
    }
}
