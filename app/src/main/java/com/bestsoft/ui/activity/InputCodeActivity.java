package com.bestsoft.ui.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bestsoft.MainActivity;
import com.bestsoft.R;
import com.bestsoft.base.BaseMvpActivity;
import com.bestsoft.common.mvp_senior.annotaions.CreatePresenterAnnotation;
import com.bestsoft.mvp.contract.InputInvateInfoContract;
import com.bestsoft.mvp.presenter.InputInvateInfoPresenter;
import com.bestsoft.ui.widget.VerifyCodeView;
import com.bestsoft.utils.IntentUtils;


import butterknife.BindView;
import butterknife.OnClick;

/**
 * 输入邀请信息
 */
@CreatePresenterAnnotation(InputInvateInfoPresenter.class)
public class InputCodeActivity extends BaseMvpActivity<InputInvateInfoContract.View, InputInvateInfoContract.Presenter> implements InputInvateInfoContract.View {


    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.verify_code_view)
    VerifyCodeView verifyCodeView;

    @Override
    protected int getLayout() {
        return R.layout.activity_input_code;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        getMvpPresenter().getInvateInfo();

    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarView(R.id.top_view)
                .statusBarColor(R.color.colorWhite)
                .statusBarDarkFont(true, 0.2f)
                .init();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        verifyCodeView.setInputCompleteListener(new VerifyCodeView.InputCompleteListener() {
            @Override
            public void inputComplete() {
                btnNext.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_code_selected));
            }

            @Override
            public void invalidContent() {
                btnNext.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_code_unselected));
            }
        });

    }


    @OnClick({R.id.img_back, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_next:
                //todo 跳转首页
                IntentUtils.get().goActivity(mContext, MainActivity.class);
                break;
        }
    }
}
