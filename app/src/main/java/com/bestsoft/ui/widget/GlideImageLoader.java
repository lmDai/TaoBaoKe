package com.bestsoft.ui.widget;

import android.content.Context;
import android.widget.ImageView;

import com.bestsoft.utils.GlideUtil;
import com.blankj.utilcode.util.SizeUtils;
import com.youth.banner.loader.ImageLoader;

/**
 * @package: com.bestsoft.ui.widget
 * @user:xhkj
 * @date:2018/11/5
 * @description:
 **/
public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        GlideUtil.loadRoundImage(context, (String) path, SizeUtils.dp2px(10), imageView);
    }
}
