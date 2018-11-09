package com.bestsoft.ui.activity;

import android.os.Bundle;
import android.support.design.ripple.RippleUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bestsoft.R;
import com.bestsoft.base.BaseMvpActivity;
import com.bestsoft.bean.CodeModel;
import com.bestsoft.common.mvp_senior.annotaions.CreatePresenterAnnotation;
import com.bestsoft.mvp.contract.InputInvateInfoContract;
import com.bestsoft.mvp.presenter.InputInvateInfoPresenter;
import com.bestsoft.utils.IntentUtils;
import com.bestsoft.utils.KeyboardUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 输入邀请信息
 */
@CreatePresenterAnnotation(InputInvateInfoPresenter.class)
public  class InputInvateInfoActivity extends BaseMvpActivity<InputInvateInfoContract.View, InputInvateInfoPresenter> implements InputInvateInfoContract.View {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.edit_code)
    EditText editCode;
    private CodeModel codeModel;

    @Override
    protected int getLayout() {
        return R.layout.activity_input_invate_info;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        KeyboardUtils.setRipper(btnNext);
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
        editCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                btnNext.setEnabled(false);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 6 && s.length() < 9) {
                    getMvpPresenter().getInvateInfo(s.toString());
                }
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
                IntentUtils.get().goActivityKill(mContext, InputPhoneActivity.class, codeModel);
                break;
        }
    }

    @Override
    public void setCodeInfo(CodeModel codeInfo) {
        this.codeModel = codeInfo;
        btnNext.setEnabled(true);
    }

    @Override
    public void sendCodeSuccess() {

    }

    @Override
    public void registerSuccess() {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
