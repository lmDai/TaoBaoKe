package com.bestsoft.utils;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bestsoft.Constant;
import com.blankj.utilcode.utils.SizeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;


/**
 * @package: com.bestsoft.utils
 * @user:xhkj
 * @date:2018/11/2
 * @description:
 **/
public class RecyclerViewUtils {
    public static void initLinerLayoutRecyclerView(RecyclerView recyclerView, Context mContext) {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.addItemDecoration(new SpacesItemDecoration(SizeUtils.dp2px(mContext, 10)));
    }
    public static void initLinerLayoutRecyclerViewPadding(RecyclerView recyclerView, Context mContext) {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }
    public static void handleNormalAdapter(BaseQuickAdapter adapter, List<?> data, boolean isRefresh) {
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            adapter.setNewData(data);
            adapter.setEnableLoadMore(true);
        } else {
            adapter.addData(data);
        }
        if (size < Constant.PAGE_SIZE) {
            adapter.loadMoreEnd(false);
        } else {
            adapter.loadMoreComplete();
        }
    }
    public static void handleAdapter(BaseQuickAdapter adapter, SmartRefreshLayout refreshLayout, List<?> data, boolean isRefresh) {
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            adapter.setNewData(data);
            refreshLayout.setEnableRefresh(true);
            adapter.setEnableLoadMore(true);
            refreshLayout.setEnableLoadMore(false);
            refreshLayout.finishRefresh();
        } else {
            adapter.addData(data);
        }
        if (size < Constant.PAGE_SIZE) {
            adapter.loadMoreEnd(isRefresh);
        } else {
            adapter.loadMoreComplete();
        }
    }
}
