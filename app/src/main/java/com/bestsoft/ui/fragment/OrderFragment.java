package com.bestsoft.ui.fragment;

import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
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
import com.bestsoft.base.BaseMvpFragment;
import com.bestsoft.bean.ProfitModel;
import com.bestsoft.common.mvp_senior.annotaions.CreatePresenterAnnotation;
import com.bestsoft.mvp.contract.OrderContract;
import com.bestsoft.mvp.presenter.OrderPresenter;
import com.bestsoft.ui.activity.MessageActivity;
import com.bestsoft.ui.activity.PersonalActivity;
import com.bestsoft.ui.activity.TeamDataActivity;
import com.bestsoft.ui.adapter.BasePagerAdapter;
import com.bestsoft.utils.IntentUtils;
import com.flyco.tablayout.SlidingTabLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @package: com.bestsoft.ui.fragment
 * @user:xhkj
 * @date:2018/10/31
 * @description: 订单
 **/
@CreatePresenterAnnotation(OrderPresenter.class)
public class OrderFragment extends BaseMvpFragment<OrderContract.View, OrderPresenter> implements OrderContract.View {
    @BindView(R.id.img_me)
    ImageView imgMe;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.img_message)
    ImageView imgMessage;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    SlidingTabLayout tabs;
    @BindView(R.id.appbar_layout)
    AppBarLayout appbarLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.btn_team_data)
    Button btnTeamData;
    @BindView(R.id.txt_couponmoney)
    TextView txtCouponmoney;
    @BindView(R.id.txt_commission)
    TextView txtCommission;
    @BindView(R.id.btn_own_commission)
    Button btnOwnCommission;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    private int position = 0;
    private BasePagerAdapter myAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_order;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        super.initView(inflater);
        txtTitle.setText(mContext.getText(R.string.order));
        List<String> mTitleList = new ArrayList<>();
        List<Fragment> mFragments = new ArrayList<>();
        mTitleList.add(mContext.getString(R.string.tab_all_orders));//全部订单
        mTitleList.add(mContext.getString(R.string.tab_paid));//已付款
        mTitleList.add(mContext.getString(R.string.tab_settled));//已结算
        mTitleList.add(mContext.getString(R.string.tab_expired));//已失效
        //1.待支付订单，2.已支付订单，3.退款订单，4.完成订单，5.失效订单 0.全部
        mFragments.add(new OrderListFragment().newInstance(0));
        mFragments.add(new OrderListFragment().newInstance(2));
        mFragments.add(new OrderListFragment().newInstance(4));
        mFragments.add(new OrderListFragment().newInstance(5));
        initTabViewPager(mFragments, mTitleList);

    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.toolbar)
                .init();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                ((OrderListFragment) myAdapter.getItem(position)).lazyFetchData();
                getMvpPresenter().userProfit(userModel.getId(), userModel.getUser_channel_id());
            }
        });
    }

    @Override
    protected void lazyFetchData() {
        super.lazyFetchData();
        getMvpPresenter().userProfit(userModel.getId(), userModel.getUser_channel_id());
    }

    private void initTabViewPager(List<Fragment> mFragments, List<String> mTitleList) {
        FragmentManager supportFragmentManager = getChildFragmentManager();
        myAdapter = new BasePagerAdapter(supportFragmentManager, mFragments, mTitleList);
        viewpager.setAdapter(myAdapter);
        // 左右预加载页面的个数
        viewpager.setOffscreenPageLimit(mFragments.size());
        tabs.setViewPager(viewpager);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                OrderFragment.this.position = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @OnClick({R.id.img_me, R.id.img_message, R.id.btn_team_data})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_me://个人中心
                IntentUtils.get().goActivity(mContext, PersonalActivity.class);
                break;
            case R.id.img_message://消息中心
                IntentUtils.get().goActivity(mContext, MessageActivity.class);
                break;
            case R.id.btn_team_data://团队数据
                IntentUtils.get().goActivity(mContext, TeamDataActivity.class);
                break;
        }
    }

    @Override
    public void setUserProfit(ProfitModel result) {
        txtCommission.setText("待到账订单" + result.getCommission() + "元");
        txtCouponmoney.setText(result.getCouponmoney());
        btnOwnCommission.setText("含自购" + result.getOwn_commission());
        refreshLayout.finishRefresh();
    }

}
