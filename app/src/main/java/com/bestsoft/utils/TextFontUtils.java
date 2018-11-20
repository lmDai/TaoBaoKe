package com.bestsoft.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * @package: com.bestsoft.utils
 * @user:xhkj
 * @date:2018/11/20
 * @description:字体工具类
 **/
public class TextFontUtils {
    public static void setTextTypeHaiPai(Context mContext, TextView textView) {
        Typeface mtypeface = Typeface.createFromAsset(mContext.getAssets(), "海派腔调禅大黑简2.0.TTF");
        textView.setTypeface(mtypeface);

    }

    public static void setTextTypeDTr(Context mContext, TextView textView) {
        Typeface mtypeface = Typeface.createFromAsset(mContext.getAssets(), "DINMediumTr.otf");
        textView.setTypeface(mtypeface);
    }

    public static void setTextTypeRTW(Context mContext, TextView textView) {
        Typeface mtypeface = Typeface.createFromAsset(mContext.getAssets(), "RTWSYueGoTrial-Regular.otf");

        textView.setTypeface(mtypeface);
    }

}
