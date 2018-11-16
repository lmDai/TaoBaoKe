package com.bestsoft.ui.widget;

import android.content.Context;
import android.widget.ImageView;

import com.bestsoft.utils.GlideUtil;
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
        GlideUtil.into(context.getApplicationContext(), (String) path, imageView);
    }
}
