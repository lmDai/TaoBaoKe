package com.bestsoft.utils;

import android.app.Dialog;
import android.content.Context;

import com.bestsoft.R;
import com.bestsoft.ui.widget.CommonDialog;
import com.bestsoft.ui.widget.PayWayDialog;
import com.bestsoft.ui.widget.ShareDialog;
import com.bestsoft.ui.widget.WithdrawDialog;

/**
 * @package: com.bestsoft.utils
 * @user:xhkj
 * @date:2018/10/30
 * @description: dialog工具类
 **/
public class DialogUtils {
    /**
     * 提示框
     *
     * @param mContext 上下文
     * @param content  提示内容
     * @param title    标题
     * @param positive 确定按钮
     * @param negative 取消按钮
     * @param listener 点击监听
     */
    public static void showPromptDialog(Context mContext, String content, String title, String positive, String negative, DialogListener listener) {
        new CommonDialog(mContext, R.style.common_dialog, content, (dialog, confirm) -> {
            if (confirm) {
                dialog.dismiss();
            }
            listener.onClick(confirm);
        }).setTitle(title).setPositiveButton(positive).setNegativeButton(negative).show();
    }

    public static void showDialogWithDrawInfo(Context mContext, String content, String title, String positive, DialogListener listener) {
        new WithdrawDialog(mContext, R.style.common_dialog, content, (dialog, confirm) -> {
            if (confirm) {
                dialog.dismiss();
            }
            listener.onClick(confirm);
        }).setTitle(title).setPositiveButton(positive).show();
    }

    public static void showDialogPayWay(Context mContext, ShareDialogListener listener, String index) {
        new PayWayDialog(mContext, R.style.common_dialog, new PayWayDialog.OnCloseListener() {
            @Override
            public void onClick(Dialog dialog, boolean confirm, int type) {
                listener.onClick(confirm, type);
            }
        }, index).show();
    }

    public static void showDialogShare(Context mContext, ShareDialogListener listener) {
        new ShareDialog(mContext, R.style.common_dialog, new ShareDialog.OnCloseListener() {
            @Override
            public void onClick(Dialog dialog, boolean confirm, int type) {
                listener.onClick(confirm, type);
            }

        }).show();
    }
}
