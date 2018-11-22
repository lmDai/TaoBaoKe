package com.bestsoft.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bestsoft.Constant;
import com.bestsoft.MyApplication;
import com.bestsoft.R;
import com.bestsoft.base.BaseMvpActivity;
import com.bestsoft.bean.UserModel;
import com.bestsoft.common.https.BaseNoDataResponse;
import com.bestsoft.common.https.rxUtils.RxEvent;
import com.bestsoft.common.mvp_senior.annotaions.CreatePresenterAnnotation;
import com.bestsoft.mvp.contract.MyAlipayContract;
import com.bestsoft.mvp.presenter.MyAlipayPresenter;
import com.bestsoft.ui.widget.ClearEditText;
import com.bestsoft.utils.KeyboardUtils;
import com.blankj.utilcode.util.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的支付宝
 */
@CreatePresenterAnnotation(MyAlipayPresenter.class)
public class MyAliPayActivity extends BaseMvpActivity<MyAlipayContract.View, MyAlipayPresenter> implements MyAlipayContract.View {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.edit_id)
    ClearEditText editId;
    @BindView(R.id.edit_name)
    ClearEditText editName;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.ll_bind)
    LinearLayout llBind;
    @BindView(R.id.txt_number)
    TextView txtNumber;
    @BindView(R.id.txt_real_name)
    TextView txtRealName;
    @BindView(R.id.btn_unbind)
    Button btnUnbind;
    @BindView(R.id.ll_unbind)
    LinearLayout llUnbind;


    @Override
    protected int getLayout() {
        return R.layout.activity_my_ali_pay;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        txtTitle.setText(mContext.getString(R.string.title_alipay));
        KeyboardUtils.setRipper(btnSave);
//        getMvpPresenter().getUserInfo(userModel.getId(), userModel.getUser_channel_id());
        if (userModel != null) {
            if (!TextUtils.isEmpty(userModel.getReal_name()) && !TextUtils.isEmpty(userModel.getAlipay_account())) {
                llUnbind.setVisibility(View.VISIBLE);
                llBind.setVisibility(View.GONE);
                txtNumber.setText(userModel.getAlipay_account());
                txtRealName.setText(userModel.getReal_name());
            } else {
                llUnbind.setVisibility(View.GONE);
                llBind.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        editId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0 && !TextUtils.isEmpty(editName.getText().toString())) {
                    btnSave.setEnabled(true);
                } else {
                    btnSave.setEnabled(false);
                }
            }
        });
        editName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0 && !TextUtils.isEmpty(editId.getText().toString())) {
                    btnSave.setEnabled(true);
                } else {
                    btnSave.setEnabled(false);
                }
            }
        });
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.toolbar)
                .statusBarColor(R.color.colorWhite)
                .statusBarDarkFont(true, 0.2f)
                .init();
    }


    @OnClick({R.id.img_back, R.id.btn_save, R.id.btn_unbind})
    public void onViewClicked(View view) {
        String account = editId.getText().toString();
        String name = editName.getText().toString();
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_save:
                getMvpPresenter().userSettingAliPay(account, name, userModel.getId(), userModel.getUser_channel_id());
                break;
            case R.id.btn_unbind:
                getMvpPresenter().untyingAlipay(userModel.getId(), userModel.getUser_channel_id());
                break;
        }
    }

    @Override
    public void setUserModel(UserModel userModel) {
        if (userModel != null) {
            if (!TextUtils.isEmpty(userModel.getReal_name()) && !TextUtils.isEmpty(userModel.getAlipay_account())) {
                llUnbind.setVisibility(View.VISIBLE);
                llBind.setVisibility(View.GONE);
                txtNumber.setText(userModel.getAlipay_account());
                txtRealName.setText(userModel.getReal_name());
            } else {
                llUnbind.setVisibility(View.GONE);
                llBind.setVisibility(View.VISIBLE);
            }
        }
        MyApplication.mApplication.setUserModel(userModel);
        EventBus.getDefault().post(new RxEvent(1, Constant.UPDATE_USER));
    }

    @Override
    public void userSettingAlipay(BaseNoDataResponse settingResult) {
        ToastUtils.showShort(settingResult.getMsg());
        getMvpPresenter().getUserInfo(userModel.getId(), userModel.getUser_channel_id());
    }

    @Override
    public void showUnbindInfo(BaseNoDataResponse response) {
        ToastUtils.showShort(response.getMsg());
        if (response.getErrorcode() == 0) {
            llBind.setVisibility(View.VISIBLE);
            llUnbind.setVisibility(View.GONE);
            editName.setText("");
            editId.setText("");
        }
        getMvpPresenter().getUserInfo(userModel.getId(), userModel.getUser_channel_id());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
