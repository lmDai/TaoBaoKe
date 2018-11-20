package com.bestsoft.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @package: com.bestsoft.utils
 * @user:xhkj
 * @date:2018/11/2
 * @description:
 **/
public class HeaderItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public HeaderItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        if (parent.getChildPosition(view) != 0) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;
            // Add top margin only for the first item to avoid double space between items
            outRect.top = space;
        }
        if (parent.getChildAdapterPosition(view) == 1) {
            outRect.left = space;
            outRect.right = space;
            // Add top margin only for the first item to avoid double space between items
            outRect.top = space * 2;
        }
    }
}
