package com.bestsoft.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bestsoft.R;
import com.bestsoft.base.BaseActivity;
import com.bestsoft.ui.adapter.BasePagerAdapter;
import com.bestsoft.ui.fragment.CircleCenterFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 发圈中心
 */
public class HairCircleCenterActivity extends BaseActivity {

    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.img_me)
    ImageView imgMe;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.img_message)
    ImageView imgMessage;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    private BasePagerAdapter myAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_hair_circle_center;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        txtTitle.setText(mContext.getString(R.string.title_invite));
        List<String> mTitleList = new ArrayList<>();
        List<Fragment> mFragments = new ArrayList<>();
        mTitleList.add("营销素材");
        mTitleList.add("新手必发");
        mFragments.add(new CircleCenterFragment().newInstance(2));
        mFragments.add(new CircleCenterFragment().newInstance(1));
        initTabViewPager(mFragments, mTitleList);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                ((CircleCenterFragment) myAdapter.getItem(viewpager.getCurrentItem())).lazyFetchData();
                refreshLayout.finishRefresh();
            }
        });
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.toolbar)
                .init();
    }

    private void initTabViewPager(List<Fragment> mFragments, List<String> mTitleList) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        myAdapter = new BasePagerAdapter(supportFragmentManager, mFragments, mTitleList);
        viewpager.setAdapter(myAdapter);
        // 左右预加载页面的个数
        viewpager.setOffscreenPageLimit(4);
        tabs.setTabMode(TabLayout.MODE_FIXED);
        tabs.setupWithViewPager(viewpager);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @OnClick({R.id.img_me, R.id.img_message})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_me:
                finish();
                break;
            case R.id.img_message:
                refreshLayout.autoRefresh();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}