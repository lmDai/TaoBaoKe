package com.bestsoft.ui.adapter;

import com.bestsoft.bean.OrderModel;
import com.bestsoft.bean.ProductModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * @package: com.bestsoft.ui.adapter
 * @user:xhkj
 * @date:2018/11/2
 * @description: 商品列表
 **/
public class OrderListAdapter extends BaseQuickAdapter<OrderModel,BaseViewHolder> {
    public OrderListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderModel item) {

    }
}
