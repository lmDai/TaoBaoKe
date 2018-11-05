package com.bestsoft.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bestsoft.R;

/**
 * @package: com.bestsoft.ui.widget
 * @user:xhkj
 * @date:2018/10/30
 * @description: 提现须知
 **/
public class WithdrawDialog extends Dialog implements View.OnClickListener {
    private TextView contentTxt;
    private TextView titleTxt;
    private TextView submitTxt;


    private Context mContext;
    private String content;
    private OnCloseListener listener;
    private String positiveName;
    private String title;

    public WithdrawDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    public WithdrawDialog(Context context, int themeResId, String content) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
    }

    public WithdrawDialog(Context context, int themeResId, String content, OnCloseListener listener) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
        this.listener = listener;
    }

    protected WithdrawDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }

    public WithdrawDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public WithdrawDialog setPositiveButton(String name) {
        this.positiveName = name;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_withdraw_info);
        setCanceledOnTouchOutside(false);
        initView();
    }

    private void initView() {
        contentTxt = findViewById(R.id.content);
        titleTxt = findViewById(R.id.title);
        submitTxt = findViewById(R.id.submit);
        submitTxt.setOnClickListener(this);
        contentTxt.setText(content);
        if (!TextUtils.isEmpty(positiveName)) {
            submitTxt.setText(positiveName);
        }
        if (!TextUtils.isEmpty(title)) {
            titleTxt.setText(title);
        }


    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.submit) {
            if (listener != null) {
                listener.onClick(this, true);
            }
            this.dismiss();
        }
    }

    public interface OnCloseListener {
        void onClick(Dialog dialog, boolean confirm);
    }
}
