package com.bestsoft.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bestsoft.R;
import com.bestsoft.base.BaseActivity;
import com.bestsoft.utils.IntentUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 余额
 */
public class BanlanceActivity extends BaseActivity {


    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_right)
    TextView txtRight;
    @BindView(R.id.btn_logout)
    Button btnLogout;

    @Override
    protected int getLayout() {
        return R.layout.activity_banlance;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        txtRight.setVisibility(View.VISIBLE);
        txtRight.setText("明细");
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.toolbar)
                .statusBarColor(R.color.colorWhite)
                .statusBarDarkFont(true, 0.2f)
                .init();
    }


    @OnClick({R.id.img_back, R.id.txt_right, R.id.btn_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                break;
            case R.id.txt_right:
                IntentUtils.get().goActivity(mContext, IncomeDetailsActivity.class);
                break;
            case R.id.btn_logout:
                break;
        }
    }
}