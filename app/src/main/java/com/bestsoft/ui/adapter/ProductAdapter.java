package com.bestsoft.ui.adapter;

import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bestsoft.R;
import com.bestsoft.bean.ProductModel;
import com.bestsoft.utils.GlideUtil;
import com.bestsoft.utils.MagicTextViewUtil;
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
    private int level;

    public ProductAdapter(int layoutResId, int level) {
        super(layoutResId);
        this.level = level;
    }

    @Override
    protected void convert(BaseViewHolder helper, ProductModel item) {
        ImageView imgProduct = helper.getView(R.id.img_pic);
        if (item.getItem_pic() != null && item.getItem_pic().size() > 0)
            GlideUtil.loadRoundImage(mContext, item.getItem_pic().get(0), SizeUtils.px2dp(mContext, 4), imgProduct);
        TextView txtPrice = helper.getView(R.id.txt_price);
        TextView txtEstimate = helper.getView(R.id.txt_estimate);
        TextView txtUpgrade = helper.getView(R.id.txt_upgrade);
        txtPrice.setText("¥" + item.getItem_price());
        txtPrice.setPaintFlags(txtPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        helper.setText(R.id.txt_resource, item.getSource())
                .setText(R.id.txt_title, item.getItem_title())
                .setText(R.id.txt_end_price, item.getItem_end_price())
                .setText(R.id.txt_sale, item.getItem_sale() + "人已买")
                .setText(R.id.txt_couponmoney, item.getCouponmoney() + "元优惠券");
        if (level == 1) {
            txtEstimate.setVisibility(View.GONE);
            txtUpgrade.setVisibility(View.GONE);
        } else if (level == 3) {
            txtEstimate.setText("预计赚¥" + item.getEstimate());
            txtUpgrade.setVisibility(View.GONE);
        } else {
            txtEstimate.setText("预计赚¥" + item.getEstimate());
            txtUpgrade.setText("升级赚¥" + item.getUpgrade());
        }

    }
}
