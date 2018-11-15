package com.bestsoft.ui.adapter;

import android.widget.ImageView;

import com.bestsoft.R;
import com.bestsoft.bean.IconModel;
import com.bestsoft.bean.IntroductionModel;
import com.bestsoft.utils.GlideUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * @package: com.bestsoft.ui.adapter
 * @user:xhkj
 * @date:2018/11/2
 * @description: 介绍
 **/
public class IconAdapter extends BaseQuickAdapter<IconModel, BaseViewHolder> {
    public IconAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, IconModel item) {
        ImageView imgIcon = helper.getView(R.id.img_icon);
        GlideUtil.loadCirclePic(mContext, item.getIcon(), imgIcon);
        helper.setText(R.id.txt_icon_title, item.getName());
    }
}
