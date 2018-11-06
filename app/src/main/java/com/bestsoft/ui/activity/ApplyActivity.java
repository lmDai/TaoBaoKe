package com.bestsoft.ui.activity;

import android.os.Bundle;

import com.bestsoft.R;
import com.bestsoft.base.BaseActivity;

/**
 * 提现申请页面
 */
public class ApplyActivity extends BaseActivity {

    @Override
    protected int getLayout() {
        return R.layout.activity_apply;
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


}