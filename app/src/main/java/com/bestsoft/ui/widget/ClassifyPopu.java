package com.bestsoft.ui.widget;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bestsoft.R;
import com.bestsoft.bean.ClassfyModel;
import com.bestsoft.utils.GlideUtil;

import java.util.List;

/**
 * @package: com.bestsoft.ui.widget
 * @user:xhkj
 * @date:2018/11/13
 * @description:
 **/
public class ClassifyPopu extends SpinerPopWindow<ClassfyModel> {
    private List<ClassfyModel> list;
    private Context context;

    public ClassifyPopu(Context context, List<ClassfyModel> list, int resId,int type) {
        super(context, list, resId,type);
        this.context = context;
        this.list = list;
    }

    @Override
    public void setData(SpinerAdapter.SpinerHolder holder, int position) {
        ImageView imgIcon = holder.getView(R.id.img_icon);
        GlideUtil.loadCirclePic(context, list.get(position).getIcon(), imgIcon);
        TextView txtTiltle = holder.getView(R.id.txt_icon_title);
        txtTiltle.setText(list.get(position).getName());
    }
}
