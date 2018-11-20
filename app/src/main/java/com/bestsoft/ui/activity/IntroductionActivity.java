package com.bestsoft.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bestsoft.R;
import com.bestsoft.base.BaseActivity;
import com.bestsoft.bean.IntroductionModel;
import com.bestsoft.ui.adapter.IntroductionAdapter;
import com.bestsoft.utils.RecyclerViewUtils;
import com.bestsoft.utils.TextFontUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 介绍
 */
public class IntroductionActivity extends BaseActivity {


    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.txt_right)
    TextView txtRight;
//    @BindView(R.id.txt_about)
//    TextView txtAbout;
//    @BindView(R.id.txt_know)
//    TextView txtKnow;
    private IntroductionAdapter introductionAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_introduction;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        txtTitle.setText(mContext.getString(R.string.app_name));
        introductionAdapter = new IntroductionAdapter(R.layout.item_introduction);
        RecyclerViewUtils.initLinerLayoutRecyclerView(recyclerView, mContext);
        recyclerView.setAdapter(introductionAdapter);
        initData();
//        TextFontUtils.setTextTypeHaiPai(mContext, txtAbout);
//        TextFontUtils.setTextTypeHaiPai(mContext, txtKnow);
    }

    private void initData() {
        List<IntroductionModel> modelList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            modelList.add(new IntroductionModel());
        }
        introductionAdapter.setNewData(modelList);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        introductionAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                //todo 加载更多
                List<IntroductionModel> modelList = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    modelList.add(new IntroductionModel());
                }
                RecyclerViewUtils.handleAdapter(introductionAdapter, refreshLayout, modelList, false);
            }
        }, recyclerView);
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.toolbar)
                .statusBarColor(R.color.colorWhite)
                .statusBarDarkFont(true, 0.2f)
                .init();
    }



    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
