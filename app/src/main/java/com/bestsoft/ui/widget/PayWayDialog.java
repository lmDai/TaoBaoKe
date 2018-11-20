package com.bestsoft.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.ImageView;

import com.bestsoft.Constant;
import com.bestsoft.R;

import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @package: com.bestsoft.ui.widget
 * @user:xhkj
 * @date:2018/11/5
 * @description:
 **/
public class PayWayDialog extends Dialog {

    @BindViews({R.id.recharge_zhifubao_cb, R.id.recharge_wechat_cb})
    List<ImageView> checks;
    private OnCloseListener listener;

    /**
     * 区别三种支付方式 2:微信支付 1:支付宝
     **/
    public static int payWay = Constant.MY_WALLET;

    private Context context;
    private String index;

    /**
     * @param context
     * @param themeResId
     */
    public PayWayDialog(Context context, int themeResId, OnCloseListener listener, String index) {
        super(context, themeResId);
        this.context = context;
        this.listener = listener;
        this.index = index;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_pay);
        ButterKnife.bind(this);
        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = AbsListView.LayoutParams.MATCH_PARENT;
        lp.y = 0;//设置Dialog距离底部的距离
        dialogWindow.setAttributes(lp);
//        checkChanges(Integer.parseInt(index));

    }


    @OnClick({R.id.dialog_zhifubao, R.id.dialog_wechat})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dialog_zhifubao:
                checkChanges(0);
                if (listener != null) {
                    listener.onClick(this, true, 2);
                }
                this.dismiss();
                break;
            case R.id.dialog_wechat:
                checkChanges(1);
                if (listener != null) {
                    listener.onClick(this, true, 1);
                }
                this.dismiss();
                break;
        }
    }

    /**
     * 改变选中
     */
    private void checkChanges(int index) {
        for (int i = 0; i < checks.size(); i++) {
            if (i != index) {
                checks.get(i).setClickable(false);
            }
        }
        payWay = index;
        checks.get(index).setClickable(true);
    }

    public interface OnCloseListener {
        void onClick(Dialog dialog, boolean confirm, int type);
    }
}
