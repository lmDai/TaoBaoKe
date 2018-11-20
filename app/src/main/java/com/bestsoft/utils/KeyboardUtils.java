package com.bestsoft.utils;

import android.content.Context;
import android.graphics.Color;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.bestsoft.ui.widget.MaterialRippleLayout;

/**
 * @package: com.bestsoft.utils
 * @user:xhkj
 * @date:2018/10/29
 * @description:
 **/
public class KeyboardUtils {
    /**
     * 点击屏幕空白区域隐藏软键盘（方法2）
     * <p>根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘</p>
     * <p>需重写dispatchTouchEvent</p>
     * <p>参照以下注释代码</p>
     */
    public static void clickBlankArea2HideSoftInput(View view, MotionEvent event, Context mContext) {

        try {
            if (view != null && view instanceof EditText) {
                int[] location = {0, 0};
                view.getLocationInWindow(location);
                int left = location[0], top = location[1], right = left
                        + view.getWidth(), bootom = top + view.getHeight();
                // 判断焦点位置坐标是否在空间内，如果位置在控件外，则隐藏键盘
                if (event.getRawX() < left || event.getRawX() > right
                        || event.getY() < top || event.getRawY() > bootom) {
                    // 隐藏键盘
                    IBinder token = view.getWindowToken();
                    InputMethodManager inputMethodManager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(token,
                            InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setRipper(View view) {
        MaterialRippleLayout.on(view)
                .rippleColor(Color.parseColor("#999999"))
                .rippleAlpha(0.2f)
                .rippleDelayClick(false)
                .rippleOverlay(false)
                .rippleHover(false)
                .create();
    }


    private static long mExitTime;

    public static boolean isFastDoubleClick() {
        if ((System.currentTimeMillis() - mExitTime) > 500) {
            mExitTime = System.currentTimeMillis();
            return false;
        } else {
            return true;
        }
    }
}
