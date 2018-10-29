package com.bestsoft.ui.activity;

import android.os.Bundle;

import com.bestsoft.R;
import com.bestsoft.base.BaseMvpActivity;
import com.bestsoft.common.mvp_senior.annotaions.CreatePresenterAnnotation;
import com.bestsoft.mvp.contract.LoginContract;
import com.bestsoft.mvp.presenter.LoginPresenter;

@CreatePresenterAnnotation(LoginPresenter.class)
public class LoginActivity extends BaseMvpActivity<LoginContract.View, LoginContract.Presenter> implements LoginContract.View {

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        getMvpPresenter().getTag();
    }
}
