package com.bestsoft.ui.adapter;

import com.bestsoft.R;
import com.bestsoft.bean.RuleModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * @package: com.bestsoft.ui.adapter
 * @user:xhkj
 * @date:2018/11/2
 * @description: 收入明细
 **/
public class RuleAdapter extends BaseQuickAdapter<RuleModel, BaseViewHolder> {
    public RuleAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, RuleModel item) {
        helper.setText(R.id.txt_position, helper.getAdapterPosition() + 1 + "")
                .setText(R.id.txt_rule, item.getRule());

    }
}
