package com.bestsoft.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;

import com.bestsoft.R;
import com.bestsoft.base.BaseFragment;
import com.bestsoft.ui.adapter.BasePagerAdapter;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @package: com.bestsoft.ui.fragment
 * @user:xhkj
 * @date:2018/11/2
 * @description: 订单列表
 **/
public class TeamDataHomeFragment extends BaseFragment {

    @BindView(R.id.tabs)
    SlidingTabLayout tabs;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private static final String TYPE = "type";
    public int tag;//0 全部订单，1 已付款，2 已结算，3 已失效

    public TeamDataHomeFragment newInstance(int tag) {
        TeamDataHomeFragment orderListFragment = new TeamDataHomeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TYPE, tag);
        orderListFragment.setArguments(bundle);
        return orderListFragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_team_data_home;
    }


    @Override
    protected void initView(LayoutInflater inflater) {
        super.initView(inflater);
        Bundle bundle = getArguments();
        if (bundle != null) {
            tag = bundle.getInt(TYPE);
        }
        List<String> mTitleList = new ArrayList<>();
        List<Fragment> mFragments = new ArrayList<>();
        mTitleList.add(mContext.getString(R.string.tab_comprehensive));//综合
        mTitleList.add(mContext.getString(R.string.tab_paid));//已付款
        mTitleList.add(mContext.getString(R.string.tab_settled));//已结算
        mTitleList.add(mContext.getString(R.string.tab_expired));//已失效
        mFragments.add(new TeamDataListFragment().newInstance(0));
        mFragments.add(new TeamDataListFragment().newInstance(1));
        mFragments.add(new TeamDataListFragment().newInstance(2));
        mFragments.add(new TeamDataListFragment().newInstance(3));
        initTabViewPager(mFragments, mTitleList);
    }


    private void initTabViewPager(List<Fragment> mFragments, List<String> mTitleList) {
        FragmentManager supportFragmentManager = getChildFragmentManager();
        BasePagerAdapter myAdapter = new BasePagerAdapter(supportFragmentManager, mFragments, mTitleList);
        viewPager.setAdapter(myAdapter);
        // 左右预加载页面的个数
        viewPager.setOffscreenPageLimit(mFragments.size());
        tabs.setViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
    protected void initEvent() {
        super.initEvent();

    }

}
