package com.bestsoft.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bestsoft.R;
import com.bestsoft.base.BaseActivity;
import com.bestsoft.utils.IntentUtils;
import com.bestsoft.utils.KeyboardUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class PersonalActivity extends BaseActivity {


    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.txt_id)
    TextView txtId;
    @BindView(R.id.img_head)
    ImageView imgHead;
    @BindView(R.id.ll_share)
    LinearLayout llShare;
    @BindView(R.id.ll_auth)
    LinearLayout llAuth;
    @BindView(R.id.ll_alypay)
    LinearLayout llAlypay;
    @BindView(R.id.ll_center)
    LinearLayout llCenter;
    @BindView(R.id.ll_feedback)
    LinearLayout llFeedback;
    @BindView(R.id.ll_check_update)
    LinearLayout llCheckUpdate;
    @BindView(R.id.btn_logout)
    Button btnLogout;

    @Override
    protected int getLayout() {
        return R.layout.activity_personal;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        KeyboardUtils.setRipper(llShare);
        KeyboardUtils.setRipper(llAuth);
        KeyboardUtils.setRipper(llCenter);
        KeyboardUtils.setRipper(llAlypay);
        KeyboardUtils.setRipper(llCheckUpdate);
        KeyboardUtils.setRipper(llFeedback);
        KeyboardUtils.setRipper(btnLogout);
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.toolbar)
                .statusBarColor(R.color.colorWhite)
                .statusBarDarkFont(true, 0.2f)
                .init();
    }


    @OnClick({R.id.img_back, R.id.img_head, R.id.ll_share, R.id.ll_auth, R.id.ll_alypay, R.id.ll_center, R.id.ll_feedback, R.id.ll_check_update, R.id.btn_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
            case R.id.img_head:
                break;
            case R.id.ll_share:
                break;
            case R.id.ll_auth:
                break;
            case R.id.ll_alypay:
                IntentUtils.get().goActivity(mContext, MyAliPayActivity.class);
                break;
            case R.id.ll_center:
                break;
            case R.id.ll_feedback:
                IntentUtils.get().goActivity(mContext, FeedbackActivity.class);
                break;
            case R.id.ll_check_update:
                break;
            case R.id.btn_logout:
                break;
        }
    }
}
