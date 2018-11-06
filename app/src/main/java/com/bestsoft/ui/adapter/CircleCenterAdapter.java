package com.bestsoft.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bestsoft.R;
import com.bestsoft.bean.CircleCenterModel;
import com.bestsoft.utils.SpacesItemDecoration;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @package: com.bestsoft.ui.adapter
 * @user:xhkj
 * @date:2018/11/2
 * @description: 发圈中心
 **/
public class CircleCenterAdapter extends BaseQuickAdapter<CircleCenterModel, BaseViewHolder> {
    public CircleCenterAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, CircleCenterModel item) {
        helper.addOnClickListener(R.id.txt_share);
        RecyclerView gridLayout = helper.getView(R.id.grid_layout);
        gridLayout.setLayoutManager(new GridLayoutManager(mContext, 3));
        gridLayout.addItemDecoration(new SpacesItemDecoration(10));
        gridLayout.setHasFixedSize(true);
        List<String> mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic1xjab4j30ci08cjrv.jpg");
        }
        ImageAdapter imageAdapter = new ImageAdapter(R.layout.item_img);
        gridLayout.setAdapter(imageAdapter);
        imageAdapter.setNewData(mList);
    }
}
