package com.bestsoft.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bestsoft.R;
import com.bestsoft.base.BaseActivity;
import com.bestsoft.base.BaseMvpActivity;
import com.bestsoft.bean.UpgradeModel;
import com.bestsoft.common.mvp_senior.annotaions.CreatePresenterAnnotation;
import com.bestsoft.mvp.contract.TeamDataContract;
import com.bestsoft.mvp.presenter.TeamDataPresenter;
import com.bestsoft.ui.adapter.BasePagerAdapter;
import com.bestsoft.ui.fragment.TeamDataHomeFragment;
import com.bestsoft.utils.IntentUtils;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 团队数据
 */
@CreatePresenterAnnotation(TeamDataPresenter.class)
public class TeamDataActivity extends BaseMvpActivity<TeamDataContract.View, TeamDataPresenter> implements TeamDataContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    SlidingTabLayout tabs;
    @BindView(R.id.appbar_layout)
    AppBarLayout appbarLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.btn_myteam)
    Button btnMyteam;
    @BindView(R.id.btn_invite_fans)
    Button btnInviteFans;
    @BindView(R.id.txt_title)
    TextView txtTitle;

    @Override
    protected int getLayout() {
        return R.layout.activity_team_data;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        toolbar.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_toolbar));
        txtTitle.setText(mContext.getString(R.string.title_team_data));
        getMvpPresenter().getUserTeamProfit(userModel.getId(), userModel.getUser_channel_id());
        List<String> mTitleList = new ArrayList<>();
        List<Fragment> mFragments = new ArrayList<>();
        mTitleList.add(mContext.getString(R.string.tab_all));//全部
        mTitleList.add(mContext.getString(R.string.tab_promotion_order));//推广订单
        mTitleList.add(mContext.getString(R.string.tab_natural_order));//自然订单
        mFragments.add(new TeamDataHomeFragment().newInstance(0));
        mFragments.add(new TeamDataHomeFragment().newInstance(1));
        mFragments.add(new TeamDataHomeFragment().newInstance(2));
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


    @OnClick({R.id.img_back, R.id.btn_myteam, R.id.btn_invite_fans})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_myteam:
                IntentUtils.get().goActivity(mContext, MyTeamActivity.class);
                break;
            case R.id.btn_invite_fans:
                IntentUtils.get().goActivity(mContext, InviteActivity.class);
                break;
        }
    }

    @Override
    public void setTeamData(UpgradeModel models) {

    }

    @Override
    public void showError(Throwable throwable) {

    }
}
