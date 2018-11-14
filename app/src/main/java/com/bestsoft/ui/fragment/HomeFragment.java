package com.bestsoft.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
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
import com.bestsoft.ui.activity.SearchActivity;
import com.bestsoft.ui.adapter.BasePagerAdapter;
import com.bestsoft.ui.adapter.FastEntranceAdapter;
import com.bestsoft.ui.adapter.MenuAdapter;
import com.bestsoft.ui.adapter.OnePlusAdapter;
import com.bestsoft.ui.widget.FiltPopuWindow;
import com.bestsoft.ui.widget.GlideImageLoader;
import com.bestsoft.utils.IntentUtils;
import com.bestsoft.utils.SpacesItemDecoration;
import com.flyco.tablayout.SlidingTabLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.Arrays;
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
    //    @BindView(R.id.recycler_home)
//    RecyclerView recyclerHome;
    @BindView(R.id.tabs)
    SlidingTabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.appbar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.btn_select)
    ImageButton btnSelect;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recycler_entrance)
    RecyclerView recyclerEntrance;


    private List<DelegateAdapter.Adapter> mAdapters;
    private int position;
    private BasePagerAdapter myAdapter;
    private List<ClassfyModel> classfiy;


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
        String[] urls = mContext.getResources().getStringArray(R.array.url);
        List list = Arrays.asList(urls);
        List<?> images = new ArrayList(list);
        banner.setImages(images)
                .setImageLoader(new GlideImageLoader())
                .start();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerEntrance.setLayoutManager(linearLayoutManager);
        recyclerEntrance.addItemDecoration(new SpacesItemDecoration(0));
        List<String> fruitList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            fruitList.add(i + "");
        }
        FastEntranceAdapter adapter = new FastEntranceAdapter(fruitList);
        recyclerEntrance.setAdapter(adapter);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                ((ProductListFragment) myAdapter.getItem(position)).lazyFetchData();
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

    @OnClick({R.id.img_me, R.id.img_message, R.id.btn_select, R.id.txt_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_me://跳转个人中心
                IntentUtils.get().goActivity(mContext, PersonalActivity.class);
                break;
            case R.id.img_message:
                IntentUtils.get().goActivity(mContext, MessageActivity.class);
                break;
            case R.id.btn_select:
                btnSelect.setImageResource(R.drawable.ic_close);
                showFilterPopu();
                break;
            case R.id.txt_search:
                IntentUtils.get().goActivity(mContext, SearchActivity.class);
                break;
        }
    }

    /**
     * 显示筛选框
     */
    public void showFilterPopu() {
        FiltPopuWindow mFilter = new FiltPopuWindow.Builder(mContext, new FiltPopuWindow.Builder.OnCloseListener() {
            @Override
            public void onClick(FiltPopuWindow popupWindow, ClassfyModel confirm) {
                if (popupWindow.isShowing()) popupWindow.dismiss();
                if (classfiy.contains(confirm))
                    viewpager.setCurrentItem(classfiy.indexOf(confirm));
            }
        }).setColumnCount(4)//设置列数，测试2.3.4.5没问题
                .setDataSource(classfiy)
                .setColorBg(R.color.color_f8f8f8)
                //所有的属性设置必须在build之前，不然无效
                .build()
                .createPop();
        mFilter.showAsDropDown(tabs);
        mFilter.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                btnSelect.setImageResource(R.drawable.ic_more_classify);
            }
        });

    }


    private void initRecyclerView() {
//        recyclerHome.setNestedScrollingEnabled(false);
//        DelegateAdapter delegateAdapter = getMvpPresenter().initRecyclerView(recyclerHome);
//        BaseDelegateAdapter bannerAdapter = getMvpPresenter().initBanner();
//        mAdapters.add(bannerAdapter);
//        BaseDelegateAdapter searchAdapter = getMvpPresenter().initSearch();
//        mAdapters.add(searchAdapter);
//        mAdapters.add(onePlusAdapter(3));
//        mAdapters.add(menuAdapter(2));
//        //初始化快速入口标题
//        BaseDelegateAdapter fastEntrceTitleAdapter = getMvpPresenter().initFastEntrceTitle();
//        mAdapters.add(fastEntrceTitleAdapter);
//        //初始化快速入口标题
//        BaseDelegateAdapter fastEntraceAdapter = getMvpPresenter().initFastEntrace();
//        mAdapters.add(fastEntraceAdapter);
//        delegateAdapter.setAdapters(mAdapters);
    }

    private void initTabViewPager(List<Fragment> mFragments, List<String> mTitleList) {
        FragmentManager supportFragmentManager = getChildFragmentManager();
        myAdapter = new BasePagerAdapter(supportFragmentManager, mFragments, mTitleList);
        viewpager.setAdapter(myAdapter);
        viewpager.setOffscreenPageLimit(mFragments.size());
        tabs.setViewPager(viewpager);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                HomeFragment.this.position = position;
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
        this.classfiy = classfiy;
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

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
