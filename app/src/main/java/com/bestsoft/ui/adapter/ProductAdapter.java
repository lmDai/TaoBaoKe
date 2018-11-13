package com.bestsoft.ui.adapter;

import android.widget.ImageView;

import com.bestsoft.R;
import com.bestsoft.bean.ProductModel;
import com.bestsoft.utils.GlideUtil;
import com.blankj.utilcode.utils.ScreenUtils;
import com.blankj.utilcode.utils.SizeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * @package: com.bestsoft.ui.adapter
 * @user:xhkj
 * @date:2018/11/2
 * @description: 商品列表
 **/
public class ProductAdapter extends BaseQuickAdapter<ProductModel, BaseViewHolder> {
    public ProductAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProductModel item) {
        ImageView imgProduct = helper.getView(R.id.img_pic);
        if (item.getItem_pic() != null && item.getItem_pic().size() > 0)
            GlideUtil.loadRoundImage(mContext, item.getItem_pic().get(0), SizeUtils.px2dp(mContext, 4), imgProduct);
        helper.setText(R.id.txt_resource, item.getSource())
                .setText(R.id.txt_title, item.getItemt_itle())
                .setText(R.id.txt_sale, item.getItem_sale())
                .setText(R.id.txt_couponmoney, item.getCouponmoney() + "元优惠券");

    }
}
