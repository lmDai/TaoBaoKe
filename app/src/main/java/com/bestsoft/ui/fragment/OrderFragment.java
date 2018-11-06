package com.bestsoft.ui.fragment;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bestsoft.R;
import com.bestsoft.base.BaseFragment;
import com.bestsoft.ui.activity.TeamDataActivity;
import com.bestsoft.ui.adapter.BasePagerAdapter;
import com.bestsoft.utils.IntentUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @package: com.bestsoft.ui.fragment
 * @user:xhkj
 * @date:2018/10/31
 * @description: 订单
 **/
public class OrderFragment extends BaseFragment {
    @BindView(R.id.img_me)
    ImageView imgMe;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.img_message)
    ImageView imgMessage;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.appbar_layout)
    AppBarLayout appbarLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.btn_team_data)
    Button btnTeamData;

    @Override
    protected int getLayout() {
        return R.layout.fragment_order;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        super.initView(inflater);
        List<String> mTitleList = new ArrayList<>();
        List<Fragment> mFragments = new ArrayList<>();
        mTitleList.add("干货定制");
        mTitleList.add("Android");
        mTitleList.add("生活福利");
        mTitleList.add("休息视频");
        mFragments.add(new OrderListFragment());
        mFragments.add(new OrderListFragment());
        mFragments.add(new OrderListFragment());
        mFragments.add(new OrderListFragment());
        initTabViewPager(mFragments, mTitleList);
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.toolbar)
                .init();
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


    @OnClick({R.id.img_me, R.id.img_message, R.id.btn_team_data})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_me:
                break;
            case R.id.img_message:
                break;
            case R.id.btn_team_data:
                IntentUtils.get().goActivity(mContext, TeamDataActivity.class);
                break;
        }
    }
}
