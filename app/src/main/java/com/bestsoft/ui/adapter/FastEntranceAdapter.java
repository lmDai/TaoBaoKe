package com.bestsoft.ui.adapter;

import android.support.annotation.Nullable;

import com.bestsoft.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @package: com.bestsoft.ui.adapter
 * @user:xhkj
 * @date:2018/10/31
 * @description:
 **/
public class FastEntranceAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public FastEntranceAdapter(@Nullable List<String> data) {
        super(R.layout.item_vp_grid_iv, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
