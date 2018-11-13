package com.bestsoft.ui.fragment;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.bestsoft.R;
import com.bestsoft.base.BaseMvpFragment;
import com.bestsoft.bean.ClassfyModel;
import com.bestsoft.common.mvp_senior.annotaions.CreatePresenterAnnotation;
import com.bestsoft.mvp.contract.HomeFragmentContract;
import com.bestsoft.mvp.presenter.HomeFragmentPresenter;
import com.bestsoft.ui.activity.MessageActivity;
import com.bestsoft.ui.activity.PersonalActivity;
import com.bestsoft.ui.adapter.BaseDelegateAdapter;
import com.bestsoft.ui.adapter.BasePagerAdapter;
import com.bestsoft.ui.adapter.MenuAdapter;
import com.bestsoft.ui.adapter.OnePlusAdapter;
import com.bestsoft.utils.IntentUtils;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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
    SlidingTabLayout tabs;
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
        getMvpPresenter().getIconClassify();//获取分类列表
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
        recyclerHome.setNestedScrollingEnabled(false);
        DelegateAdapter delegateAdapter = getMvpPresenter().initRecyclerView(recyclerHome);
        BaseDelegateAdapter bannerAdapter = getMvpPresenter().initBanner();
        mAdapters.add(bannerAdapter);
        BaseDelegateAdapter searchAdapter = getMvpPresenter().initSearch();
        mAdapters.add(searchAdapter);
        mAdapters.add(onePlusAdapter(1));
        mAdapters.add(onePlusAdapter(2));
        mAdapters.add(onePlusAdapter(3));
        mAdapters.add(menuAdapter(1));
        mAdapters.add(menuAdapter(2));
        mAdapters.add(onePlusAdapter(1));
        mAdapters.add(onePlusAdapter(2));
        mAdapters.add(onePlusAdapter(3));
        mAdapters.add(menuAdapter(1));
        mAdapters.add(menuAdapter(2));
        //初始化快速入口标题
        BaseDelegateAdapter fastEntrceTitleAdapter = getMvpPresenter().initFastEntrceTitle();
        mAdapters.add(fastEntrceTitleAdapter);
        //初始化快速入口标题
        BaseDelegateAdapter fastEntraceAdapter = getMvpPresenter().initFastEntrace();
        mAdapters.add(fastEntraceAdapter);
        delegateAdapter.setAdapters(mAdapters);
    }

    private void initTabViewPager(List<Fragment> mFragments, List<String> mTitleList) {
        FragmentManager supportFragmentManager = getChildFragmentManager();
        BasePagerAdapter myAdapter = new BasePagerAdapter(supportFragmentManager, mFragments, mTitleList);
        viewpager.setAdapter(myAdapter);
        // 左右预加载页面的个数
        viewpager.setOffscreenPageLimit(4);
        tabs.setViewPager(viewpager);
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

    @Override
    public void setClassfiy(List<ClassfyModel> classfiy) {
        List<Fragment> mFragments = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        for (ClassfyModel classfyModel : classfiy) {
            titles.add(classfyModel.getName());
            mFragments.add(new ProductListFragment().newInstance(classfyModel.getKey()));
        }
        initTabViewPager(mFragments, titles);
    }


    private OnePlusAdapter onePlusAdapter(int type) {
        return new OnePlusAdapter(mContext, new LinearLayoutHelper(), 1, type);
    }

    /**
     * 菜单
     *
     * @param type
     * @return
     */
    private MenuAdapter menuAdapter(int type) {
        return new MenuAdapter(mContext, new LinearLayoutHelper(), 1, type);
    }

}
