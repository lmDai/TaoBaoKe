package com.bestsoft.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.bestsoft.R;
import com.bestsoft.base.BaseMvpFragment;
import com.bestsoft.bean.ArticleModel;
import com.bestsoft.common.mvp_senior.annotaions.CreatePresenterAnnotation;
import com.bestsoft.mvp.contract.ShareAticleContract;
import com.bestsoft.mvp.presenter.ShareAticlePresenter;
import com.bestsoft.ui.adapter.CircleCenterAdapter;
import com.bestsoft.utils.DialogListener;
import com.bestsoft.utils.DialogUtils;
import com.bestsoft.utils.RecyclerViewUtils;
import com.bestsoft.utils.ShareDialogListener;
import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

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
                if (view.getId() == R.id.ll_share) {
                    DialogUtils.showDialogShare(mContext, new ShareDialogListener() {
                        @Override
                        public void onClick(boolean confirm, int type) {
                            if (confirm) {
                                Platform.ShareParams sp = new Platform.ShareParams();
                                sp.setTitle(null);
                                sp.setTitleUrl(circleAdapter.getData().get(position).getShare_img()); // 标题的超链接
                                sp.setText(null);
                                sp.setImageUrl(circleAdapter.getData().get(position).getShare_img());
                                sp.setSite(null);
                                sp.setSiteUrl(null);
                                Platform platform = null;
                                switch (type) {
                                    case 1:
                                        platform = ShareSDK.getPlatform(WechatMoments.NAME);
                                        break;
                                    case 2:
                                        platform = ShareSDK.getPlatform(Wechat.NAME);
                                        break;
                                    case 3:
                                        platform = ShareSDK.getPlatform(QQ.NAME);
                                        break;
                                    case 4:
                                        platform = ShareSDK.getPlatform(QZone.NAME);
                                        break;
                                }
                                // 设置分享事件回调（注：回调放在不能保证在主线程调用，不可以在里面直接处理UI操作）
                                platform.setPlatformActionListener(new PlatformActionListener() {
                                    public void onError(Platform arg0, int arg1, Throwable arg2) {
                                        //失败的回调，arg:平台对象，arg1:表示当前的动作，arg2:异常信息
                                        LogUtils.i("onError");
                                    }
                                    public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
                                        //分享成功的回调
                                        LogUtils.i("onComplete",JSON.toJSONString(arg2));
                                    }
                                    public void onCancel(Platform arg0, int arg1) {
                                        //取消分享的回调
                                        LogUtils.i("onCancel");
                                    }
                                });
                                // 执行图文分享
                                platform.share(sp);
                            }
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
