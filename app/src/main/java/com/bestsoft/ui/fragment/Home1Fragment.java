package com.bestsoft.ui.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.bestsoft.R;
import com.bestsoft.base.BaseMvpFragment;
import com.bestsoft.common.mvp_senior.annotaions.CreatePresenterAnnotation;
import com.bestsoft.mvp.contract.HomeFragmentContract;
import com.bestsoft.mvp.presenter.HomeFragmentPresenter;
import com.bestsoft.ui.adapter.BaseDelegateAdapter;
import com.bestsoft.ui.adapter.BasePagerAdapter;
import com.bestsoft.ui.widget.JudgeNestedScrollView;
import com.bestsoft.utils.ScreenUtil;
import com.bestsoft.utils.StatusBarUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
import com.scwang.smartrefresh.layout.util.DensityUtil;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

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

    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.magic_indicator_title)
    MagicIndicator magicIndicatorTitle;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.scrollView)
    JudgeNestedScrollView scrollView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    int toolBarPositionY = 0;
    private int mOffset = 0;
    private int mScrollY = 0;

    private List<DelegateAdapter.Adapter> mAdapters;
    private ArrayList<String> mTitleList = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.fragment_home1;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        super.initView(inflater);
        mAdapters = new LinkedList<>();
        initRecyclerView();
        mTitleList = new ArrayList<>();
        mTitleList.add("他们都在买");
        mTitleList.add("猜你喜欢");
        mTitleList.add("女装");
        mTitleList.add("母婴");
        mTitleList.add("居家");
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(true, 0.2f)
                .statusBarView(toolbar)
                .init();
    }

    private void initRecyclerView() {
        recyclerHome.setNestedScrollingEnabled(false);
        DelegateAdapter delegateAdapter = getMvpPresenter().initRecyclerView(recyclerHome);
        //标题
        BaseDelegateAdapter titleAdapter = getMvpPresenter().initTitle();
        mAdapters.add(titleAdapter);
//        //搜索框
//        BaseDelegateAdapter searchAdapter = getMvpPresenter().initSearch();
//        mAdapters.add(searchAdapter);
        //初始化九宫格
        BaseDelegateAdapter menuAdapter1 = getMvpPresenter().initGvMenu();
        mAdapters.add(menuAdapter1);
        //初始化九宫格
        BaseDelegateAdapter menuAdapter2 = getMvpPresenter().initGvMenu();
        mAdapters.add(menuAdapter2);
        //初始化九宫格
        BaseDelegateAdapter menuAdapter3 = getMvpPresenter().initGvMenu();
        mAdapters.add(menuAdapter3);
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

        //设置适配器
        delegateAdapter.setAdapters(mAdapters);
        recyclerHome.requestLayout();
    }

    @Override
    public void setOnclick(int position) {

    }

    @Override
    protected void initEvent() {
        super.initEvent();
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
                magicIndicator.getLocationOnScreen(location);
                int yPosition = location[1];
                if (yPosition < toolBarPositionY) {
                    magicIndicatorTitle.setVisibility(View.VISIBLE);
                    scrollView.setNeedScroll(false);
                } else {
                    magicIndicatorTitle.setVisibility(View.GONE);
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
        viewPager.setAdapter(new BasePagerAdapter(getChildFragmentManager(), getFragments()));
        viewPager.setOffscreenPageLimit(10);
        initMagicIndicator();
        initMagicIndicatorTitle();
    }

    private void dealWithViewPager() {
        toolBarPositionY = toolbar.getHeight();
        ViewGroup.LayoutParams params = viewPager.getLayoutParams();
        params.height = ScreenUtil.getScreenHeightPx(mContext) - toolBarPositionY - magicIndicator.getHeight() + 1;
        viewPager.setLayoutParams(params);
    }

    private List<Fragment> getFragments() {
        List<Fragment> mFragments = new ArrayList<>();
        mFragments.add(new PopularProductFragment());
        mFragments.add(new ProductListFragment());
        mFragments.add(new ProductListFragment());
        mFragments.add(new ProductListFragment());
        mFragments.add(new ProductListFragment());
        return mFragments;
    }

    private void initMagicIndicator() {
        CommonNavigator commonNavigator = new CommonNavigator(mContext);
        commonNavigator.setScrollPivotX(0.65f);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mTitleList == null ? 0 : mTitleList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
                simplePagerTitleView.setText(mTitleList.get(index));
                simplePagerTitleView.setNormalColor(ContextCompat.getColor(mContext, R.color.colorBlack));
                simplePagerTitleView.setSelectedColor(ContextCompat.getColor(mContext, R.color.colorBlack));
                simplePagerTitleView.setTextSize(16);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(index, false);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 2));
                indicator.setLineWidth(UIUtil.dip2px(context, 20));
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(ContextCompat.getColor(mContext, R.color.colorBlack));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPager);
    }

    private void initMagicIndicatorTitle() {
        CommonNavigator commonNavigator = new CommonNavigator(mContext);
        commonNavigator.setScrollPivotX(0.65f);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mTitleList == null ? 0 : mTitleList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
                simplePagerTitleView.setText(mTitleList.get(index));
                simplePagerTitleView.setNormalColor(ContextCompat.getColor(mContext, R.color.colorBlack));
                simplePagerTitleView.setSelectedColor(ContextCompat.getColor(mContext, R.color.colorBlack));
                simplePagerTitleView.setTextSize(16);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(index, false);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 2));
                indicator.setLineWidth(UIUtil.dip2px(context, 20));
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(ContextCompat.getColor(mContext, R.color.colorBlack));
                return indicator;
            }
        });
        magicIndicatorTitle.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicatorTitle, viewPager);

    }
}
