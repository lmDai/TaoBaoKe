package com.bestsoft.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.bestsoft.R;
import com.bestsoft.base.BaseFragment;
import com.bestsoft.bean.CircleCenterModel;
import com.bestsoft.ui.adapter.CircleCenterAdapter;
import com.bestsoft.utils.DialogListener;
import com.bestsoft.utils.DialogUtils;
import com.bestsoft.utils.RecyclerViewUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @package: com.bestsoft.ui.fragment
 * @user:xhkj
 * @date:2018/11/6
 * @description: 发圈中心碎片
 **/
public class CircleCenterFragment extends BaseFragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    private CircleCenterAdapter orderAdapter;


    @Override
    protected int getLayout() {
        return R.layout.fragment_circle_center;
    }


    @Override
    protected void initView(LayoutInflater inflater) {
        super.initView(inflater);
        orderAdapter = new CircleCenterAdapter(R.layout.item_circle_center);
        RecyclerViewUtils.initLinerLayoutRecyclerView(recyclerView, mContext);
        recyclerView.setAdapter(orderAdapter);
        initData();
    }

    private void initData() {
        List<CircleCenterModel> modelList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            modelList.add(new CircleCenterModel());
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
                List<CircleCenterModel> modelList = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    modelList.add(new CircleCenterModel());
                }
                RecyclerViewUtils.handleNormalAdapter(orderAdapter, modelList, false);
            }
        }, recyclerView);
        orderAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.txt_share) {
                    DialogUtils.showDialogShare(mContext, new DialogListener() {
                        @Override
                        public void onClick(boolean confirm) {
                            //todo
                        }
                    });
                }
            }
        });
    }
}
