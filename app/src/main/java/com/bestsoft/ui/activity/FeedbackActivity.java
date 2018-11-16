package com.bestsoft.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bestsoft.R;
import com.bestsoft.base.BaseActivity;
import com.bestsoft.base.BaseMvpActivity;
import com.bestsoft.common.https.BaseNoDataResponse;
import com.bestsoft.common.mvp_senior.annotaions.CreatePresenterAnnotation;
import com.bestsoft.mvp.contract.FeedBackContract;
import com.bestsoft.mvp.presenter.FeedBackPresenter;
import com.bestsoft.utils.KeyboardUtils;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 用户反馈
 */
@CreatePresenterAnnotation(FeedBackPresenter.class)
public class FeedbackActivity extends BaseMvpActivity<FeedBackContract.View, FeedBackPresenter> implements FeedBackContract.View {


    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.edit_content)
    EditText editContent;
    @BindView(R.id.edit_contact)
    EditText editContact;
    @BindView(R.id.btn_commit)
    Button btnCommit;

    @Override
    protected int getLayout() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        txtTitle.setText(mContext.getString(R.string.title_feed_back));
        KeyboardUtils.setRipper(btnCommit);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        editContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0 && !TextUtils.isEmpty(editContact.getText().toString())) {
                    btnCommit.setEnabled(true);
                } else {
                    btnCommit.setEnabled(false);
                }
            }
        });
        editContact.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0 && !TextUtils.isEmpty(editContent.getText().toString())) {
                    btnCommit.setEnabled(true);
                } else {
                    btnCommit.setEnabled(false);
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


    @OnClick({R.id.img_back, R.id.btn_commit})
    public void onViewClicked(View view) {
        String content=editContent.getText().toString();
        String contact=editContact.getText().toString();
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_commit:
                getMvpPresenter().feedBack(content,contact,userModel.getId(),userModel.getUser_channel_id());
                break;
        }
    }

    @Override
    public void setFeedBackStatus(BaseNoDataResponse settingResult) {
        ToastUtils.showShort(settingResult.getMsg());
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
