package com.bestsoft.ui.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.bestsoft.R;
import com.bestsoft.base.BaseFragment;
import com.bestsoft.bean.ProductModel;
import com.bestsoft.ui.adapter.ProductAdapter;
import com.bestsoft.utils.RecyclerViewUtils;
import com.bestsoft.utils.SpacesItemDecoration;
import com.blankj.utilcode.utils.SizeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.util.DesignUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @package: com.bestsoft.ui.fragment
 * @user:xhkj
 * @date:2018/11/2
 * @description: 商品列表
 **/
public class ProductListFragment extends BaseFragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    private ProductAdapter productAdapter;


    @Override
    protected int getLayout() {
        return R.layout.fragment_product_list;
    }


    @Override
    protected void initView(LayoutInflater inflater) {
        super.initView(inflater);
        productAdapter = new ProductAdapter(R.layout.item_product);
        RecyclerViewUtils.initLinerLayoutRecyclerView(recyclerView, mContext);
        recyclerView.setAdapter(productAdapter);
        initData();
    }

    private void initData() {
        List<ProductModel> modelList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            modelList.add(new ProductModel());
        }
        productAdapter.setNewData(modelList);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        productAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                //todo 加载更多
                List<ProductModel> modelList = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    modelList.add(new ProductModel());
                }
                RecyclerViewUtils.handleNormalAdapter(productAdapter, modelList, false);
            }
        }, recyclerView);
    }
}
