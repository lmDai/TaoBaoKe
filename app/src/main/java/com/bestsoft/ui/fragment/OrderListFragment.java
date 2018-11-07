package com.bestsoft.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.bestsoft.R;
import com.bestsoft.base.BaseFragment;
import com.bestsoft.bean.OrderModel;
import com.bestsoft.ui.adapter.OrderListAdapter;
import com.bestsoft.utils.RecyclerViewUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @package: com.bestsoft.ui.fragment
 * @user:xhkj
 * @date:2018/11/2
 * @description: 订单列表
 **/
public class OrderListFragment extends BaseFragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    private OrderListAdapter orderAdapter;
    private static final String TYPE = "type";
    public int tag;//0 全部订单，1 已付款，2 已结算，3 已失效

    public OrderListFragment newInstance(int tag) {
        OrderListFragment orderListFragment = new OrderListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TYPE, tag);
        orderListFragment.setArguments(bundle);
        return orderListFragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_order_list;
    }


    @Override
    protected void initView(LayoutInflater inflater) {
        super.initView(inflater);
        Bundle bundle = getArguments();
        if (bundle != null) {
            tag = bundle.getInt(TYPE);
        }
        orderAdapter = new OrderListAdapter(R.layout.item_order, tag);
        addHeader();//添加头部
        RecyclerViewUtils.initLinerLayoutRecyclerView(recyclerView, mContext);
        recyclerView.setAdapter(orderAdapter);
        initData();
    }

    private void initData() {
        List<OrderModel> modelList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            modelList.add(new OrderModel());
        }
        orderAdapter.setNewData(modelList);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        orderAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                //todo 加载更多
                List<OrderModel> modelList = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    modelList.add(new OrderModel());
                }
                RecyclerViewUtils.handleNormalAdapter(orderAdapter, modelList, false);
            }
        }, recyclerView);
    }

    private void addHeader() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.order_list_head, null, false);
        orderAdapter.addHeaderView(view);
    }
}
