package com.bestsoft.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.bestsoft.R;
import com.bestsoft.common.widget.FlowTagLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @package: com.bestsoft.ui.widget
 * @user:xhkj
 * @date:2018/11/13
 * @description:明细
 **/
public class IncomPopWindow extends PopupWindow {
    private List<String> datas;
    private Context mContext;
    private LayoutInflater mInflater;
    private Activity mActivity;
    private FlowTagLayout tagLayout;
    private TagClickListener listener;

    public IncomPopWindow(Context context, List<String> list, TagClickListener listener) {
        this.mContext = context;
        this.datas = list;
        mInflater = LayoutInflater.from(mContext);
        mActivity = (Activity) context;
        this.listener = listener;
        initPopup();
    }

    /**
     * 初始化PopupWindow
     */
    private void initPopup() {
        View view = mInflater.inflate(R.layout.layout_income_popu, null);
        setContentView(view);
        //设置PopupWindow宽高
        //设置PopupWindow宽高
        setAnimationStyle(R.style.popwin_anim_style);
        WindowManager windowManager = mActivity.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        setWidth(display.getWidth());
        setHeight(display.getHeight());
        //设置背景
        ColorDrawable dw = new ColorDrawable(0x60000000);
        setBackgroundDrawable(dw);
        setOutsideTouchable(true);
        setFocusable(true);
        tagLayout = view.findViewById(R.id.tag_income);
        tagLayout.addTags(datas);
        view.findViewById(R.id.ll_container).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        tagLayout.setTagClickListener(new FlowTagLayout.OnTagClickListener() {
            @Override
            public void tagClick(int position) {
                dismiss();
                listener.tagClickListener(position);

            }
        });
    }

    @Override
    public void showAsDropDown(View anchor) {
        if (Build.VERSION.SDK_INT >= 24) {
            Rect rect = new Rect();
            anchor.getGlobalVisibleRect(rect);
            int h = anchor.getResources().getDisplayMetrics().heightPixels - rect.bottom;
            setHeight(h);
        }
        super.showAsDropDown(anchor);
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff) {
        if (Build.VERSION.SDK_INT >= 24) {
            Rect rect = new Rect();
            anchor.getGlobalVisibleRect(rect);
            int h = anchor.getResources().getDisplayMetrics().heightPixels - rect.bottom;
            setHeight(h);
        }
        super.showAsDropDown(anchor, xoff, yoff);
    }

    /**
     * 设置PopupWindow的位置
     *
     * @param parent
     */
    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            this.showAsDropDown(parent, 0, 0);
        } else {
            this.dismiss();
        }
    }

    public interface TagClickListener {
        void tagClickListener(int position);
    }
}

