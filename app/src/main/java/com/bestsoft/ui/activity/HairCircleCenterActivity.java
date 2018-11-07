package com.bestsoft.ui.activity;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bestsoft.R;
import com.bestsoft.base.BaseActivity;
import com.bestsoft.ui.adapter.BasePagerAdapter;
import com.bestsoft.ui.fragment.CircleCenterFragment;
import com.bestsoft.ui.fragment.OrderListFragment;
import com.bestsoft.utils.IntentUtils;

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


    @Override
    protected int getLayout() {
        return R.layout.activity_hair_circle_center;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        List<String> mTitleList = new ArrayList<>();
        List<Fragment> mFragments = new ArrayList<>();
        mTitleList.add("商品推荐");
        mTitleList.add("营销素材");
        mTitleList.add("新手必发");
        mFragments.add(new CircleCenterFragment());
        mFragments.add(new CircleCenterFragment());
        mFragments.add(new CircleCenterFragment());
        initTabViewPager(mFragments, mTitleList);
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.toolbar)
                .init();
    }

    private void initTabViewPager(List<Fragment> mFragments, List<String> mTitleList) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
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
}