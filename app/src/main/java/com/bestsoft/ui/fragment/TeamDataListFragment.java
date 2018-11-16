package com.bestsoft.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bestsoft.R;
import com.bestsoft.base.BaseMvpFragment;
import com.bestsoft.bean.TeamOrderModel;
import com.bestsoft.common.mvp_senior.annotaions.CreatePresenterAnnotation;
import com.bestsoft.mvp.contract.TeamOrderContract;
import com.bestsoft.mvp.presenter.TeamOrderPresenter;
import com.bestsoft.ui.adapter.TeamDataListAdapter;
import com.bestsoft.utils.RecyclerViewUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.BindView;

/**
 * @package: com.bestsoft.ui.fragment
 * @user:xhkj
 * @date:2018/11/2
 * @description: 订单列表
 **/
@CreatePresenterAnnotation(TeamOrderPresenter.class)
public class TeamDataListFragment extends BaseMvpFragment<TeamOrderContract.View, TeamOrderPresenter> implements TeamOrderContract.View {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rb_all)
    RadioButton rbAll;
    @BindView(R.id.radio)
    RadioGroup radio;
    private TeamDataListAdapter teamDataAdapter;
    private static final String TYPE = "type";
    public int order_type;//0 全部订单，1 推广，2 自然订单
    private int order_status = 0;//1.待支付订单，2.已支付订单，3.退款订单，4.完成订单，5.失效订单 0.全部

    public TeamDataListFragment newInstance(int order_type) {
        TeamDataListFragment orderListFragment = new TeamDataListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TYPE, order_type);
        orderListFragment.setArguments(bundle);
        return orderListFragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_team_data_list;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        super.initView(inflater);
        Bundle bundle = getArguments();
        if (bundle != null) {
            order_type = bundle.getInt(TYPE);
        }
        rbAll.setChecked(true);
        teamDataAdapter = new TeamDataListAdapter(R.layout.item_team_data);
        if (order_type == 1)
            addHeader();//添加头部
        RecyclerViewUtils.initLinerLayoutRecyclerViewPadding(recyclerView, mContext);
        recyclerView.setAdapter(teamDataAdapter);
    }

    @Override
    protected void lazyFetchData() {
        super.lazyFetchData();
        getMvpPresenter().getTeamOrders(order_status, order_type, userModel.getId(), userModel.getUser_channel_id(), true);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        teamDataAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getMvpPresenter().getTeamOrders(order_status, order_type, userModel.getId(), userModel.getUser_channel_id(), false);
            }
        }, recyclerView);
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_all:
                        order_status = 0;
                        break;
                    case R.id.rb_payed:
                        order_status = 2;
                        break;
                    case R.id.rb_checked:
                        order_status = 4;
                        break;
                    case R.id.rb_invalid:
                        order_status = 5;
                        break;
                }
                getMvpPresenter().getTeamOrders(order_status, order_type, userModel.getId(), userModel.getUser_channel_id(), true);
            }
        });
    }

    private void addHeader() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.team_data_list_head, null, false);
        teamDataAdapter.addHeaderView(view);
    }

    @Override
    public void showTeamOrders(List<TeamOrderModel> models, boolean isRefresh) {
        RecyclerViewUtils.handleNormalAdapter(teamDataAdapter, models, isRefresh);
    }

    @Override
    public void showError(Throwable throwable, boolean isRefresh) {
        RecyclerViewUtils.handError(teamDataAdapter, isRefresh);
    }
}
