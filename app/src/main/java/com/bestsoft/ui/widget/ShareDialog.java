package com.bestsoft.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bestsoft.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @package: com.bestsoft.ui.widget
 * @user:xhkj
 * @date:2018/11/5
 * @description:
 **/
public class ShareDialog extends Dialog {


    @BindView(R.id.txt_share_wechat)
    TextView txtShareWechat;
    @BindView(R.id.txt_share_wechat_circle)
    TextView txtShareWechatCircle;
    @BindView(R.id.txt_share_qq)
    TextView txtShareQq;
    @BindView(R.id.txt_share_qzone)
    TextView txtShareQzone;
    @BindView(R.id.dialog_zhifubao)
    LinearLayout dialogZhifubao;
    private OnCloseListener listener;
    @BindView(R.id.btn_close)
    Button btnClose;
    private Context context;

    /**
     * @param context
     * @param themeResId
     */
    public ShareDialog(Context context, int themeResId, OnCloseListener listener) {
        super(context, themeResId);
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_share);
        ButterKnife.bind(this);
        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = AbsListView.LayoutParams.MATCH_PARENT;
        lp.y = 0;//设置Dialog距离底部的距离
        dialogWindow.setAttributes(lp);
    }

    @OnClick({R.id.btn_close, R.id.txt_share_wechat, R.id.txt_share_wechat_circle, R.id.txt_share_qq, R.id.txt_share_qzone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_close:
                if (listener != null) {
                    listener.onClick(this, false, 0);
                }
                this.dismiss();
                break;
            case R.id.txt_share_wechat:
                if (listener != null) {
                    listener.onClick(this, true, 1);
                }
                this.dismiss();
                break;
            case R.id.txt_share_wechat_circle:
                if (listener != null) {
                    listener.onClick(this, true, 2);
                }
                this.dismiss();
                break;
            case R.id.txt_share_qq:
                if (listener != null) {
                    listener.onClick(this, true, 3);
                }
                this.dismiss();
                break;
            case R.id.txt_share_qzone:
                if (listener != null) {
                    listener.onClick(this, true, 4);
                }
                this.dismiss();
                break;
        }
    }


    public interface OnCloseListener {
        void onClick(Dialog dialog, boolean confirm, int type);
    }
}
