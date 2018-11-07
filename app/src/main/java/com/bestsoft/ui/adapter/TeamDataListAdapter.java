package com.bestsoft.ui.adapter;

import android.view.View;
import android.widget.TextView;

import com.bestsoft.R;
import com.bestsoft.bean.OrderModel;
import com.bestsoft.bean.TeamDataListModel;
import com.bestsoft.utils.MagicTextViewUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * @package: com.bestsoft.ui.adapter
 * @user:xhkj
 * @date:2018/11/2
 * @description: 团队数据列表
 **/
public class TeamDataListAdapter extends BaseQuickAdapter<TeamDataListModel, BaseViewHolder> {


    public TeamDataListAdapter(int layoutResId) {
        super(layoutResId);

    }

    @Override
    protected void convert(BaseViewHolder helper, TeamDataListModel item) {
        TextView mTextView = helper.getView(R.id.txt_title);
        MagicTextViewUtil.getInstance(mTextView)
                .append(R.drawable.ic_order_tag)
                .append("红色123454541213456456445飞机撒开了饭啊减肥的拉萨附近啊飞机开酸辣粉解开了将阿凡达死了阿警方撒了饭")
                .show();
    }
}
