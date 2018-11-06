package com.bestsoft.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bestsoft.R;
import com.bestsoft.base.BaseActivity;
import com.bestsoft.utils.DialogListener;
import com.bestsoft.utils.DialogUtils;
import com.bestsoft.utils.IntentUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 提现
 */
public class WithdrawActivity extends BaseActivity {


    @BindView(R.id.img_question)
    ImageView imgQuestion;
    @BindView(R.id.ll_way)
    LinearLayout llWay;
    @BindView(R.id.btn_withdraw)
    Button btnWithdraw;

    @Override
    protected int getLayout() {
        return R.layout.activity_withdraw;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.toolbar)
                .statusBarColor(R.color.colorWhite)
                .statusBarDarkFont(true, 0.2f)
                .init();
    }

    @OnClick({R.id.img_question, R.id.ll_way, R.id.btn_withdraw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_question:
                DialogUtils.showDialogWithDrawInfo(mContext, "1、待到账金额暂时不能提现，详情在钱包页我的余额中查看", "知道了", "知道了", new DialogListener() {
                    @Override
                    public void onClick(boolean confirm) {
                        //todo 跳转注册页面

                    }
                });
                break;
            case R.id.ll_way:
                DialogUtils.showDialogPayWay(mContext, new DialogListener() {
                    @Override
                    public void onClick(boolean confirm) {
                        //todo
                    }
                });
                break;
            case R.id.btn_withdraw:
                IntentUtils.get().goActivity(mContext, ApplyActivity.class);
                break;
        }
    }
}
