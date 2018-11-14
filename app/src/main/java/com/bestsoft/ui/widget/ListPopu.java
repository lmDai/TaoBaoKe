package com.bestsoft.ui.widget;

import android.content.Context;
import android.widget.TextView;

import com.bestsoft.R;
import com.bestsoft.bean.SelectModel;

import java.util.List;

/**
 * @package: com.bestsoft.ui.widget
 * @user:xhkj
 * @date:2018/11/13
 * @description:
 **/
public class ListPopu extends SpinerPopWindow<SelectModel> {
    private List<SelectModel> list;
    private Context context;

    public ListPopu(Context context, List<SelectModel> list, int resId) {
        super(context, list, resId);
        this.context = context;
        this.list = list;
    }

    @Override
    public void setData(SpinerAdapter.SpinerHolder holder, int position) {
        TextView item = holder.getView(R.id.txt_filter);
        String s = list.get(position).getSelectname();
        item.setText(s);
    }
}
