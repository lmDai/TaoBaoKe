package com.bestsoft.ui.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bestsoft.R;
import com.bestsoft.base.BaseMvpActivity;
import com.bestsoft.common.mvp_senior.annotaions.CreatePresenterAnnotation;
import com.bestsoft.mvp.contract.InputInvateInfoContract;
import com.bestsoft.mvp.presenter.InputInvateInfoPresenter;
import com.bestsoft.utils.IntentUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 输入邀请信息
 */
@CreatePresenterAnnotation(InputInvateInfoPresenter.class)
public class InputPhoneActivity extends BaseMvpActivity<InputInvateInfoContract.View, InputInvateInfoContract.Presenter> implements InputInvateInfoContract.View {


    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.edit_code)
    EditText editCode;
    @BindView(R.id.txt_info)
    TextView txtInfo;
    private boolean isUserInput;

    @Override
    protected int getLayout() {
        return R.layout.activity_input_phone;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        String html = "注册代表你已经同意<font color='red'>《巨折App用户协议》";
        txtInfo.setText(Html.fromHtml(html));
        getMvpPresenter().getInvateInfo();
        editCode.setInputType(InputType.TYPE_CLASS_NUMBER);
        editCode.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(13)
        });
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
        editCode.addTextChangedListener(new PhoneNumberFormattingTextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                isUserInput = after == 1;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().equals("")) return;
                String startString = s.toString().replace(" ", "");
                if (isUserInput) {
                    if (startString.length() > 3) {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < startString.length(); i++) {
                            if (i == 3 || i == 7) {
                                sb.append(" " + startString.charAt(i));
                            } else {
                                sb.append(startString.charAt(i));
                            }
                        }
                        editCode.setText(sb.toString());
                        editCode.setSelection(sb.toString().length());
                    }
                    isUserInput = false;
                } else {//删除一个或者多个
                    if (!isLegalInput(s)) {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < startString.length(); i++) {
                            if (i == 3 || i == 7) {
                                sb.append(" " + startString.charAt(i));
                            } else {
                                sb.append(startString.charAt(i));
                            }
                        }
                        editCode.setText(sb.toString());
                        editCode.setSelection(start);
                    } else {
                        if ((s.length() == 4) || s.length() == 9
                                && String.valueOf(s.charAt(s.length() - 1)).equals(" ")) {
                            s = s.subSequence(0, s.length() - 1);
                            editCode.setText(s.toString());
                            editCode.setSelection(s.length());
                        }

                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 13) {
                    btnNext.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_btn_selected));
                } else {
                    btnNext.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_btn_unselected));
                }
            }
        });
    }

    /**
     * 验证输入合法性
     *
     * @param s
     * @return
     */
    private boolean isLegalInput(CharSequence s) {
        String start = s.toString().replace(" ", "");
        if (start.length() <= 3 && start.length() == s.length()) {
            return true;
        } else if (start.length() <= 7 && String.valueOf(s.charAt(3)).equals(" ")
                && (s.length() - start.length()) == 1) {
            return true;
        } else if (start.length() <= 11
                && String.valueOf(s.charAt(3)).equals(" ")
                && String.valueOf(s.charAt(8)).equals(" ")
                && (s.length() - start.length() == 2)) {
            return true;
        }
        return false;
    }

    @OnClick({R.id.img_back, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_next:
                IntentUtils.get().goActivity(mContext, InputCodeActivity.class);
                break;
        }
    }
}
