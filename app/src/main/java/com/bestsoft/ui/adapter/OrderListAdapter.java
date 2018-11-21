package com.bestsoft.ui.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bestsoft.R;
import com.bestsoft.bean.OrderModel;
import com.bestsoft.utils.GlideUtil;
import com.bestsoft.utils.MagicTextViewUtil;
import com.blankj.utilcode.util.SizeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * @package: com.bestsoft.ui.adapter
 * @user:xhkj
 * @date:2018/11/2
 * @description: 商品列表
 **/
public class OrderListAdapter extends BaseQuickAdapter<OrderModel, BaseViewHolder> {
    private int type;

    public OrderListAdapter(int layoutResId, int type) {
        super(layoutResId);
        this.type = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderModel item) {
        ImageView imageView = helper.getView(R.id.img_pic);
        GlideUtil.loadRoundImage(mContext, item.getItem_pic(), SizeUtils.px2dp(mContext, 4), imageView);
        helper.getView(R.id.ll_bottom).setVisibility(type == 0 ? View.GONE : View.VISIBLE);
        TextView mTextView = helper.getView(R.id.txt_title);
        MagicTextViewUtil.getInstance(mTextView)
                .append(R.drawable.ic_order_tag)
                .append(item.getItem_title())
                .show();
        helper.setText(R.id.txt_commission, item.getCommission())
                .setText(R.id.txt_commission1, "收货到账" + item.getCommission());

    }
}
