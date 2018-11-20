package com.bestsoft.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bestsoft.R;
import com.bestsoft.base.BaseActivity;
import com.bestsoft.bean.WithDrawModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 提现申请页面
 */
public class ApplyActivity extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_right)
    TextView txtRight;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn_withdraw)
    Button btnWithdraw;
    @BindView(R.id.txt_money)
    TextView txtMoney;
    @BindView(R.id.txt_poundage)
    TextView txtPoundage;
    @BindView(R.id.txt_type)
    TextView txtType;
    private WithDrawModel withDrawModel;

    @Override
    protected int getLayout() {
        return R.layout.activity_apply;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        txtTitle.setText("提现");
        withDrawModel = getIntent().getParcelableExtra("open_activity");
        txtMoney.setText("¥" + withDrawModel.getMoney());
        txtPoundage.setText("¥" + withDrawModel.getPoundage());
        if (withDrawModel.getType().equals("1")) {
            txtType.setText("支付宝");
        } else if (withDrawModel.getType().equals("2")) {
            txtType.setText("微信");
        }
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.toolbar)
                .statusBarColor(R.color.colorWhite)
                .statusBarDarkFont(true, 0.2f)
                .init();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.img_back, R.id.btn_withdraw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_withdraw:
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}