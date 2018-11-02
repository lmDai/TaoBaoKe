package com.bestsoft.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.bestsoft.Constant;
import com.bestsoft.R;
import com.bestsoft.base.BaseMvpFragment;
import com.bestsoft.common.mvp_senior.annotaions.CreatePresenterAnnotation;
import com.bestsoft.mvp.contract.HomeFragmentContract;
import com.bestsoft.mvp.presenter.HomeFragmentPresenter;
import com.bestsoft.ui.activity.MessageActivity;
import com.bestsoft.ui.activity.PersonalActivity;
import com.bestsoft.ui.adapter.BaseDelegateAdapter;
import com.bestsoft.ui.adapter.BasePagerAdapter;
import com.bestsoft.ui.widget.WrapContentHeightViewPager;
import com.bestsoft.utils.IntentUtils;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @package: com.bestsoft.ui.fragment
 * @user:xhkj
 * @date:2018/10/31
 * @description:首页
 **/
@CreatePresenterAnnotation(HomeFragmentPresenter.class)
public class HomeFragment extends BaseMvpFragment<HomeFragmentContract.View, HomeFragmentPresenter> implements HomeFragmentContract.View {
    @BindView(R.id.img_me)
    ImageView imgMe;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.img_message)
    ImageView imgMessage;
    @BindView(R.id.recycler_home)
    RecyclerView recyclerHome;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.appbar_layout)
    AppBarLayout appBarLayout;

    private List<DelegateAdapter.Adapter> mAdapters;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        super.initView(inflater);
        txtTitle.setText("¥200000");
        mAdapters = new LinkedList<>();
        initRecyclerView();
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.toolbar)
                .init();
    }

    @OnClick({R.id.img_me, R.id.img_message})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_me://跳转个人中心
                IntentUtils.get().goActivity(mContext, PersonalActivity.class);
                break;
            case R.id.img_message:
                IntentUtils.get().goActivity(mContext, MessageActivity.class);
                break;
        }
    }

    private void initRecyclerView() {

        DelegateAdapter delegateAdapter = getMvpPresenter().initRecyclerView(recyclerHome);
        //标题
        BaseDelegateAdapter titleAdapter = getMvpPresenter().initTitle();
        mAdapters.add(titleAdapter);
        //搜索框
        BaseDelegateAdapter searchAdapter = getMvpPresenter().initSearch();
        mAdapters.add(searchAdapter);
        //初始化九宫格
        BaseDelegateAdapter menuAdapter = getMvpPresenter().initGvMenu();
        mAdapters.add(menuAdapter);
        //初始化banner
        BaseDelegateAdapter bannerAdapter = getMvpPresenter().initBanner();
        mAdapters.add(bannerAdapter);
        //初始化快速入口标题
        BaseDelegateAdapter fastEntrceTitleAdapter = getMvpPresenter().initFastEntrceTitle();
        mAdapters.add(fastEntrceTitleAdapter);
        //初始化快速入口标题
        BaseDelegateAdapter fastEntraceAdapter = getMvpPresenter().initFastEntrace();
        mAdapters.add(fastEntraceAdapter);

//        StickyLayoutHelper stickyLayoutHelper = new StickyLayoutHelper();
//        stickyLayoutHelper.setStickyStart(true);

//        BaseDelegateAdapter stickyTab = new BaseDelegateAdapter(mContext, stickyLayoutHelper, R.layout.layout_home_sticky, 1, Constant.viewType.typeSticky) {
//            @Override
//            public void onBindViewHolder(BaseViewHolder holder, int position) {
//                super.onBindViewHolder(holder, position);
//                TabLayout tabLayout = holder.getView(R.id.tab_layout);
//                WrapContentHeightViewPager viewPager = holder.getView(R.id.view_pager);
//                List<String> mTitleList = new ArrayList<>();
//                List<Fragment> mFragments = new ArrayList<>();
//                mTitleList.add("干货定制");
//                mTitleList.add("Android");
//                mTitleList.add("生活福利");
//                mTitleList.add("休息视频");
//                mFragments.add(new SkillFragment());
//                mFragments.add(new SkillFragment());
//                mFragments.add(new SkillFragment());
//                mFragments.add(new SkillFragment());
//                initTabViewPager(mFragments, mTitleList, tabLayout, viewPager);
//            }
//        };
//        mAdapters.add(stickyTab);
//        BaseDelegateAdapter foot = getMvpPresenter().initFragment();
//        mAdapters.add(foot);
        //设置适配器
        delegateAdapter.setAdapters(mAdapters);

        recyclerHome.requestLayout();
        List<String> mTitleList = new ArrayList<>();
        List<Fragment> mFragments = new ArrayList<>();
        mTitleList.add("干货定制");
        mTitleList.add("Android");
        mTitleList.add("生活福利");
        mTitleList.add("休息视频");
        mFragments.add(new ProductListFragment());
        mFragments.add(new ProductListFragment());
        mFragments.add(new ProductListFragment());
        mFragments.add(new ProductListFragment());
        initTabViewPager(mFragments, mTitleList);

    }

    private void initTabViewPager(List<Fragment> mFragments, List<String> mTitleList) {
        FragmentManager supportFragmentManager = getChildFragmentManager();
        BasePagerAdapter myAdapter = new BasePagerAdapter(supportFragmentManager, mFragments, mTitleList);
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

    @Override
    public void setOnclick(int position) {

    }

}
