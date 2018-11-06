package com.bestsoft.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bestsoft.R;
import com.bestsoft.base.BaseActivity;
import com.bestsoft.ui.widget.ClearEditText;
import com.bestsoft.utils.KeyboardUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的支付宝
 */
public class MyAliPayActivity extends BaseActivity {


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

    @Override
    protected int getLayout() {
        return R.layout.activity_my_ali_pay;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        KeyboardUtils.setRipper(btnSave);
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


    @OnClick({R.id.img_back, R.id.btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_save:
                break;
        }
    }
}
