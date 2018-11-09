package com.bestsoft.ui.fragment;

import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.bestsoft.R;
import com.bestsoft.base.BaseMvpFragment;
import com.bestsoft.bean.ClassfyModel;
import com.bestsoft.common.mvp_senior.annotaions.CreatePresenterAnnotation;
import com.bestsoft.mvp.contract.HomeFragmentContract;
import com.bestsoft.mvp.presenter.HomeFragmentPresenter;
import com.bestsoft.ui.adapter.BaseDelegateAdapter;
import com.bestsoft.ui.adapter.BasePagerAdapter;
import com.bestsoft.ui.adapter.MenuAdapter;
import com.bestsoft.ui.adapter.OnePlusAdapter;
import com.bestsoft.ui.widget.JudgeNestedScrollView;
import com.bestsoft.utils.ScreenUtil;
import com.flyco.tablayout.SlidingTabLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
import com.scwang.smartrefresh.layout.util.DensityUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;

/**
 * @package: com.bestsoft.ui.fragment
 * @user:xhkj
 * @date:2018/10/31
 * @description:首页
 **/
@CreatePresenterAnnotation(HomeFragmentPresenter.class)
public class Home1Fragment extends BaseMvpFragment<HomeFragmentContract.View, HomeFragmentPresenter> implements HomeFragmentContract.View {

    @BindView(R.id.recycler_home)
    RecyclerView recyclerHome;
    @BindView(R.id.tabs)
    SlidingTabLayout tabs;
    @BindView(R.id.tabs_title)
    SlidingTabLayout tabTitle;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.scrollView)
    JudgeNestedScrollView scrollView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ll_title)
    LinearLayout llTitle;
    private Runnable trigger;
    int toolBarPositionY = 0;
    private int mOffset = 0;
    private int mScrollY = 0;
    private List<DelegateAdapter.Adapter> mAdapters;
    private BasePagerAdapter mAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home1;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        super.initView(inflater);
        mAdapters = new LinkedList<>();
        initRecyclerView();
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar
                .statusBarDarkFont(true, 0.2f)
                .init();
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

        //设置适配器
        delegateAdapter.setAdapters(mAdapters);

        final Handler mainHandler = new Handler(Looper.getMainLooper());

        trigger = new Runnable() {
            @Override
            public void run() {
                recyclerHome.requestLayout();
            }
        };
        mainHandler.postDelayed(trigger, 1000);
    }

    @Override
    public void setOnclick(int position) {

    }

    /**
     * 分类列表
     *
     * @param classfiy
     */
    @Override
    public void setClassfiy(List<ClassfyModel> classfiy) {
        List<Fragment> mFragments = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        for (ClassfyModel classfyModel : classfiy) {
            titles.add(classfyModel.getName());
            mFragments.add(new ProductListFragment());
        }
        initViewPager(mFragments, titles);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        getMvpPresenter().getIconClassify();
        refreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            @Override
            public void onHeaderMoving(RefreshHeader header, boolean isDragging, float percent, int offset, int headerHeight, int maxDragHeight) {
                mOffset = offset / 2;
            }
        });
        toolbar.post(new Runnable() {
            @Override
            public void run() {
                dealWithViewPager();
            }
        });
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            int lastScrollY = 0;
            int h = DensityUtil.dp2px(170);
            int color = ContextCompat.getColor(mContext, R.color.colorWhite) & 0x00ffffff;

            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int[] location = new int[2];
                tabs.getLocationOnScreen(location);
                int yPosition = location[1];
                if (yPosition < toolBarPositionY) {
                    llTitle.setVisibility(View.VISIBLE);
                    scrollView.setNeedScroll(false);
                } else {
                    llTitle.setVisibility(View.GONE);
                    scrollView.setNeedScroll(true);
                }
                if (lastScrollY < h) {
                    scrollY = Math.min(h, scrollY);
                    mScrollY = scrollY > h ? h : scrollY;
                    toolbar.setBackgroundColor(((255 * mScrollY / h) << 24) | color);
                }
                lastScrollY = scrollY;
            }
        });
        toolbar.setBackgroundColor(0);
    }


    private void dealWithViewPager() {
        toolBarPositionY = toolbar.getHeight();
        ViewGroup.LayoutParams params = viewPager.getLayoutParams();
        params.height = ScreenUtil.getScreenHeightPx(mContext) - toolBarPositionY - tabs.getHeight() + 1;
        viewPager.setLayoutParams(params);
    }


    private void initViewPager(List<Fragment> mFragments, List<String> mTitles) {
        mAdapter = new BasePagerAdapter(getChildFragmentManager(), mFragments, mTitles);
        viewPager.setAdapter(mAdapter);
        tabs.setViewPager(viewPager);
        tabTitle.setViewPager(viewPager);
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
