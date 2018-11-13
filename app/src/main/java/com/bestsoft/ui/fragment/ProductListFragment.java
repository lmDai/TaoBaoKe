package com.bestsoft.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.bestsoft.R;
import com.bestsoft.base.BaseFragment;
import com.bestsoft.base.BaseMvpFragment;
import com.bestsoft.bean.ProductModel;
import com.bestsoft.common.mvp_senior.annotaions.CreatePresenterAnnotation;
import com.bestsoft.mvp.contract.ProductListContract;
import com.bestsoft.mvp.presenter.ProductListPresenter;
import com.bestsoft.ui.activity.ProductDetailsActivity;
import com.bestsoft.ui.adapter.ProductAdapter;
import com.bestsoft.utils.IntentUtils;
import com.bestsoft.utils.RecyclerViewUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @package: com.bestsoft.ui.fragment
 * @user:xhkj
 * @date:2018/11/2
 * @description: 商品列表
 **/
@CreatePresenterAnnotation(ProductListPresenter.class)
public class ProductListFragment extends BaseMvpFragment<ProductListContract.View, ProductListPresenter> implements ProductListContract.View {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    private ProductAdapter productAdapter;
    private String KEY = "key";
    private String key;

    public ProductListFragment newInstance(String key) {
        ProductListFragment productListFragment = new ProductListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY, key);
        productListFragment.setArguments(bundle);
        return productListFragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_product_list;
    }


    @Override
    protected void initView(LayoutInflater inflater) {
        super.initView(inflater);
        Bundle bundle = getArguments();
        if (bundle != null) {
            key = bundle.getString(KEY);
        }
        productAdapter = new ProductAdapter(R.layout.item_product);
        RecyclerViewUtils.initLinerLayoutRecyclerView(recyclerView, mContext);
        recyclerView.setAdapter(productAdapter);
    }

    @Override
    protected void lazyFetchData() {
        super.lazyFetchData();
        getMvpPresenter().getGoodHaoList(key, "", userModel.getId(), userModel.getChannel_id(), true);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        productAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getMvpPresenter().getGoodHaoList(key, "", userModel.getId(), userModel.getChannel_id(), false);
            }
        }, recyclerView);
        productAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ProductModel productModel = productAdapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putString("item_id", productModel.getItem_id());
                IntentUtils.get().goActivity(mContext, ProductDetailsActivity.class, bundle);
            }
        });
    }

    @Override
    public void showProductList(List<ProductModel> models, boolean isRefresh) {
        RecyclerViewUtils.handleNormalAdapter(productAdapter, models, isRefresh);
    }
}
