package com.bestsoft.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.bestsoft.R;
import com.bestsoft.base.BaseMvpFragment;
import com.bestsoft.bean.ProductModel;
import com.bestsoft.bean.SelectModel;
import com.bestsoft.common.mvp_senior.annotaions.CreatePresenterAnnotation;
import com.bestsoft.mvp.contract.ProductListContract;
import com.bestsoft.mvp.presenter.ProductListPresenter;
import com.bestsoft.ui.activity.ProductDetailsActivity;
import com.bestsoft.ui.adapter.ProductAdapter;
import com.bestsoft.ui.widget.DropdownButton;
import com.bestsoft.ui.widget.ItemClickListener;
import com.bestsoft.ui.widget.ListPopu;
import com.bestsoft.utils.IntentUtils;
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
    private TextView chooseComprehensive;
    private DropdownButton choosePostCoupon, chooseSalesVolume, chooseVoucherDenomination;
    private String sort = "";
    private String Comprehensive, PostCoupon, SalesVolume, VoucherDenomination;
    private ListPopu listPopu;

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
        productAdapter = new ProductAdapter(R.layout.item_product, userModel.getLevel());
        RecyclerViewUtils.initLinerLayoutRecyclerView(recyclerView, mContext);
        recyclerView.setAdapter(productAdapter);
        View headerView = LayoutInflater.from(mContext).inflate(R.layout.header_product_list, null, false);
        productAdapter.addHeaderView(headerView);
        chooseComprehensive = headerView.findViewById(R.id.choose_comprehensive);
        choosePostCoupon = headerView.findViewById(R.id.choose_post_coupon);
        chooseSalesVolume = headerView.findViewById(R.id.choose_sales_volume);
        chooseVoucherDenomination = headerView.findViewById(R.id.choose_voucher_denomination);
        chooseComprehensive.setText("最新");
        choosePostCoupon.setText("销量");
        chooseSalesVolume.setText("佣金");
        chooseVoucherDenomination.setText("筛选");
        chooseComprehensive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sort = "0";
                lazyFetchData();
            }
        });
        choosePostCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePostCoupon.setChecked(!choosePostCoupon.getChecked());
                List<SelectModel> classfiy = new ArrayList<>();
                classfiy.add(new SelectModel("7", "月销量(从低到高)", false));
                classfiy.add(new SelectModel("4", "月销量(从高到低)", false));
                classfiy.add(new SelectModel("10", "全天销量(从低到高)", false));
                classfiy.add(new SelectModel("9", "全天销量(从高到低)", false));
                classfiy.add(new SelectModel("12", "近2小时销量(从低到高)", false));
                classfiy.add(new SelectModel("11", "近2小时销量(从高到低)", false));
                listPopu = new ListPopu(mContext, classfiy, R.layout.item_filter);
                listPopu.showPopupWindow(headerView);
                listPopu.setOnItemClickListener(new ItemClickListener() {
                    @Override
                    public void onItemClick(Object obj, int position) {
                        listPopu.dismiss();
                        listPopu = null;
                        sort = classfiy.get(position).getId();
                        lazyFetchData();
                    }
                });
            }
        });
        chooseSalesVolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseSalesVolume.setChecked(!chooseSalesVolume.getChecked());
                List<SelectModel> classfiy = new ArrayList<>();
                classfiy.add(new SelectModel("8", "佣金比例(从低到高)", false));
                classfiy.add(new SelectModel("5", "佣金比例(从高到低)", false));
                listPopu = new ListPopu(mContext, classfiy, R.layout.item_filter);
                listPopu.showPopupWindow(headerView);
                listPopu.setOnItemClickListener(new ItemClickListener() {
                    @Override
                    public void onItemClick(Object obj, int position) {

                        listPopu.dismiss();
                        listPopu = null;
                        sort = classfiy.get(position).getId();
                        lazyFetchData();
                    }
                });
            }
        });
        chooseVoucherDenomination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseVoucherDenomination.setChecked(!chooseVoucherDenomination.getChecked());
                List<SelectModel> classfiy = new ArrayList<>();
                classfiy.add(new SelectModel("1", "券后价格(从低到高)", false));
                classfiy.add(new SelectModel("2", "券后价格(从高到低)", false));
                classfiy.add(new SelectModel("8", "优惠券领取量(从低到高)", false));
                classfiy.add(new SelectModel("13", "优惠券领取量(从高到低)", false));
                listPopu = new ListPopu(mContext, classfiy, R.layout.item_filter);
                listPopu.showPopupWindow(headerView);
                listPopu.setOnItemClickListener(new ItemClickListener() {
                    @Override
                    public void onItemClick(Object obj, int position) {
                        listPopu.dismiss();
                        listPopu = null;
                        sort = classfiy.get(position).getId();
                        lazyFetchData();
                    }
                });
            }
        });
    }

    @Override
    protected void lazyFetchData() {
        super.lazyFetchData();
        getMvpPresenter().getGoodHaoList(key, sort, userModel.getId(), userModel.getUser_channel_id(), userModel.getLevel(), true);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        productAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getMvpPresenter().getGoodHaoList(key, sort, userModel.getId(), userModel.getUser_channel_id(), userModel.getLevel(), false);
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

    @Override
    public void showError(Throwable throwable, boolean isRefresh) {
        RecyclerViewUtils.handError(productAdapter, isRefresh);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
