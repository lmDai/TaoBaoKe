package com.bestsoft.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.LinearLayout;

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
        super(R.layout.item_fast_entrance, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        LinearLayout linearLayout = helper.getView(R.id.ll_new_seed_item);
        if (item.equals("0")) {
            linearLayout.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_fast_entrace_1));
        } else {
            linearLayout.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_fast_entrace_2));
        }

    }
}
