package com.bestsoft.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.bestsoft.R;
import com.bestsoft.base.BaseFragment;
import com.bestsoft.base.BaseMvpActivity;
import com.bestsoft.base.BaseMvpFragment;
import com.bestsoft.bean.ArticleModel;
import com.bestsoft.bean.CircleCenterModel;
import com.bestsoft.common.mvp_senior.annotaions.CreatePresenterAnnotation;
import com.bestsoft.mvp.contract.ShareAticleContract;
import com.bestsoft.mvp.presenter.ShareAticlePresenter;
import com.bestsoft.ui.adapter.CircleCenterAdapter;
import com.bestsoft.utils.DialogListener;
import com.bestsoft.utils.DialogUtils;
import com.bestsoft.utils.RecyclerViewUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static android.support.test.InstrumentationRegistry.getArguments;

/**
 * @package: com.bestsoft.ui.fragment
 * @user:xhkj
 * @date:2018/11/6
 * @description: 发圈中心碎片
 **/
@CreatePresenterAnnotation(ShareAticlePresenter.class)
public class CircleCenterFragment extends BaseMvpFragment<ShareAticleContract.View, ShareAticlePresenter> implements ShareAticleContract.View {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    private CircleCenterAdapter circleAdapter;
    private static final String TYPE = "type";
    public int tag;//分类 1.新手必发，2.营销素材

    public CircleCenterFragment newInstance(int tag) {
        CircleCenterFragment orderListFragment = new CircleCenterFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TYPE, tag);
        orderListFragment.setArguments(bundle);
        return orderListFragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_circle_center;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        super.initView(inflater);
        Bundle bundle = getArguments();
        if (bundle != null) {
            tag = bundle.getInt(TYPE);
        }
        circleAdapter = new CircleCenterAdapter(R.layout.item_circle_center);
        RecyclerViewUtils.initLinerLayoutRecyclerView(recyclerView, mContext);
        recyclerView.setAdapter(circleAdapter);
    }

    @Override
    public void lazyFetchData() {
        super.lazyFetchData();
        getMvpPresenter().shareAticle(tag, userModel.getId(), userModel.getUser_channel_id(), true);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        circleAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getMvpPresenter().shareAticle(tag, userModel.getId(), userModel.getUser_channel_id(), false);
            }
        }, recyclerView);
        circleAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
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

    @Override
    public void showAticleList(List<ArticleModel> models, boolean isRefresh) {
        RecyclerViewUtils.handleNormalAdapter(circleAdapter, models, isRefresh);
    }

    @Override
    public void showError(Throwable throwable, boolean isRefresh) {
        RecyclerViewUtils.handError(circleAdapter, isRefresh);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
