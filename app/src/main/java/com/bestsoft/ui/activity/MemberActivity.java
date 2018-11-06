package com.bestsoft.ui.activity;

import android.os.Bundle;

import com.bestsoft.R;
import com.bestsoft.base.BaseActivity;

/**
 * @package: com.bestsoft.ui.activity
 * @user:xhkj
 * @date:2018/11/6
 * @description:会员中心
 **/
public class MemberActivity extends BaseActivity {
    @Override
    protected int getLayout() {
        return R.layout.activity_member;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(true, 0.2f)
                .init();
    }
}
