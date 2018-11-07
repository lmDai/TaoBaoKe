package com.bestsoft.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.bestsoft.R;
import com.bestsoft.base.BaseMvpFragment;
import com.bestsoft.common.mvp_senior.annotaions.CreatePresenterAnnotation;
import com.bestsoft.mvp.contract.HomeFragmentContract;
import com.bestsoft.mvp.presenter.HomeFragmentPresenter;
import com.bestsoft.ui.adapter.BaseDelegateAdapter;

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

    private List<DelegateAdapter.Adapter> mAdapters;

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
        mImmersionBar.statusBarView(R.id.top_view)
                .statusBarColor(R.color.colorPrimary)
                .fitsSystemWindows(true)
                .init();
    }
    private void initRecyclerView() {

        DelegateAdapter delegateAdapter = getMvpPresenter().initRecyclerView(recyclerHome);
        //标题
        BaseDelegateAdapter stickyTab = getMvpPresenter().initStickyTab();
        mAdapters.add(stickyTab);
        //标题
        BaseDelegateAdapter titleAdapter = getMvpPresenter().initTitle();
        mAdapters.add(titleAdapter);
        //搜索框
        BaseDelegateAdapter searchAdapter = getMvpPresenter().initSearch();
        mAdapters.add(searchAdapter);
        BaseDelegateAdapter menuAdapter1 = getMvpPresenter().initGvMenu();
        mAdapters.add(menuAdapter1);
        BaseDelegateAdapter menuAdapter2 = getMvpPresenter().initGvMenu();
        mAdapters.add(menuAdapter2);
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
        List<String> mTitleList = new ArrayList<>();
        List<Fragment> mFragments = new ArrayList<>();
        mTitleList.add("他们都在买");
        mTitleList.add("猜你喜欢");
        mTitleList.add("女装");
        mTitleList.add("母婴");
        mTitleList.add("居家");
        mFragments.add(new PopularProductFragment());
        mFragments.add(new ProductListFragment());
        mFragments.add(new ProductListFragment());
        mFragments.add(new ProductListFragment());
        mFragments.add(new ProductListFragment());
    }

    @Override
    public void setOnclick(int position) {

    }

}
