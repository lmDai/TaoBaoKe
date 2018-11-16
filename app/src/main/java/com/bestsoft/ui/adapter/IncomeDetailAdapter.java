package com.bestsoft.ui.adapter;

import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.bestsoft.R;
import com.bestsoft.bean.IncomeDetailModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * @package: com.bestsoft.ui.adapter
 * @user:xhkj
 * @date:2018/11/2
 * @description: 收入明细
 **/
public class IncomeDetailAdapter extends BaseQuickAdapter<IncomeDetailModel, BaseViewHolder> {
    public IncomeDetailAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, IncomeDetailModel item) {
        TextView txtMoney=helper.getView(R.id.txt_money);
        txtMoney.setTextColor(item.getStatus()==1?ContextCompat.getColor(mContext,R.color.item_income_title)
        :ContextCompat.getColor(mContext,R.color.color_green));
        helper.setText(R.id.txt_money, item.getMoney())
                .setText(R.id.txt_desc, item.getDesc())
                .setText(R.id.txt_create_at, item.getCreate_at());

    }
}
