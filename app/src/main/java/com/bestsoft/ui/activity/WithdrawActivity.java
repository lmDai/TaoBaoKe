package com.bestsoft.ui.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bestsoft.R;
import com.bestsoft.base.BaseMvpActivity;
import com.bestsoft.bean.UserModel;
import com.bestsoft.bean.WithDrawModel;
import com.bestsoft.common.https.BaseNoDataResponse;
import com.bestsoft.common.mvp_senior.annotaions.CreatePresenterAnnotation;
import com.bestsoft.mvp.contract.WithDrawContract;
import com.bestsoft.mvp.presenter.WithdrawPresenter;
import com.bestsoft.utils.DialogListener;
import com.bestsoft.utils.DialogUtils;
import com.bestsoft.utils.IntentUtils;
import com.bestsoft.utils.MagicTextViewUtil;
import com.bestsoft.utils.ShareDialogListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 提现
 */
@CreatePresenterAnnotation(WithdrawPresenter.class)
public class WithdrawActivity extends BaseMvpActivity<WithDrawContract.View, WithdrawPresenter> implements WithDrawContract.View {


    @BindView(R.id.img_question)
    ImageView imgQuestion;
    @BindView(R.id.ll_way)
    LinearLayout llWay;
    @BindView(R.id.btn_withdraw)
    Button btnWithdraw;
    @BindView(R.id.edit_amount)
    EditText editAmount;
    @BindView(R.id.img_wechat)
    ImageView imgWechat;
    @BindView(R.id.txt_info)
    TextView txtInfo;
    @BindView(R.id.txt_amount_info)
    TextView txtAmountInfo;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_right)
    TextView txtRight;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private String amount;
    private String type = "2";


    @Override
    protected int getLayout() {
        return R.layout.activity_withdraw;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        txtTitle.setText("提现");
        editAmount.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        getMvpPresenter().getUserInfo(userModel.getId(), userModel.getUser_channel_id());
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        editAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    if (Double.valueOf(s.toString()) < 1) {
                        MagicTextViewUtil.getInstance(txtAmountInfo)
                                .append("提现最低金额为1元", ContextCompat.getColor(mContext, R.color.color_FF0000))
                                .show();
                        return;
                    }
                    if (Double.valueOf(s.toString()) > userModel.getBalance()) {
                        MagicTextViewUtil.getInstance(txtAmountInfo)
                                .append("超过可提现金额", ContextCompat.getColor(mContext, R.color.color_FF0000))
                                .show();
                        return;
                    }
                    MagicTextViewUtil.getInstance(txtAmountInfo)
                            .append("可提现的金额" + userModel.getBalance() + "元，")
                            .append("全部提现", ContextCompat.getColor(mContext, R.color.color_6D88EF), new MagicTextViewUtil.OnTextClickListener() {
                                @Override
                                public void onClick(String text) {
                                    editAmount.setText(userModel.getBalance() + "");
                                    editAmount.setSelection(editAmount.getText().length());
                                }
                            })
                            .show();
                    btnWithdraw.setEnabled(true);
                } else {
                    MagicTextViewUtil.getInstance(txtAmountInfo)
                            .append("可提现的金额" + userModel.getBalance() + "元，")
                            .append("全部提现", ContextCompat.getColor(mContext, R.color.color_6D88EF), new MagicTextViewUtil.OnTextClickListener() {
                                @Override
                                public void onClick(String text) {
                                    editAmount.setText(userModel.getBalance() + "");
                                    editAmount.setSelection(editAmount.getText().length());
                                }
                            })
                            .show();
                    btnWithdraw.setEnabled(false);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMvpPresenter().getUserInfo(userModel.getId(), userModel.getUser_channel_id());
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.toolbar)
                .statusBarColor(R.color.colorWhite)
                .statusBarDarkFont(true, 0.2f)
                .init();
    }

    @OnClick({R.id.img_question, R.id.img_back, R.id.ll_way, R.id.btn_withdraw})
    public void onViewClicked(View view) {
        amount = editAmount.getText().toString();
        switch (view.getId()) {
            case R.id.img_question:
                DialogUtils.showDialogWithDrawInfo(mContext, "1、待到账金额暂时不能提现，详情在钱包页我的余额中查看", "提现须知", "知道了", new DialogListener() {
                    @Override
                    public void onClick(boolean confirm) {
                        //todo 跳转注册页面

                    }
                });
                break;
            case R.id.ll_way:
                DialogUtils.showDialogPayWay(mContext, new ShareDialogListener() {
                    @Override
                    public void onClick(boolean confirm, int type) {
                        if (confirm) {
                            WithdrawActivity.this.type = type + "";
                            if (type == 2) {
                                imgWechat.setImageResource(R.drawable.ic_weixin_pay);
                                txtInfo.setText("微信");
                            } else {
                                txtInfo.setText("支付宝");
                                imgWechat.setImageResource(R.drawable.ic_alipay);
                            }
                        }
                    }

                }, type);
                break;
            case R.id.btn_withdraw:
                getMvpPresenter().withDrawApply(amount, type, userModel.getId(), userModel.getUser_channel_id());
                break;
            case R.id.img_back:
                finish();
                break;
        }
    }

    @Override
    public void setUserModel(UserModel userModel) {
        WithdrawActivity.this.userModel = userModel;
        MagicTextViewUtil.getInstance(txtAmountInfo)
                .append("可提现的金额" + userModel.getBalance() + "元，")
                .append("全部提现", ContextCompat.getColor(mContext, R.color.color_6D88EF), new MagicTextViewUtil.OnTextClickListener() {
                    @Override
                    public void onClick(String text) {
                        editAmount.setText(userModel.getBalance() + "");
                        editAmount.setSelection(editAmount.getText().length());
                    }
                })
                .show();
    }

    @Override
    public void withDrawApply(WithDrawModel settingResult) {
        IntentUtils.get().goActivity(mContext, ApplyActivity.class, settingResult);
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
