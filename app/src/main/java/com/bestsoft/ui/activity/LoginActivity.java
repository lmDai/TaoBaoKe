package com.bestsoft.ui.activity;

import android.graphics.Paint;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bestsoft.R;
import com.bestsoft.base.BaseMvpActivity;
import com.bestsoft.common.mvp_senior.annotaions.CreatePresenterAnnotation;
import com.bestsoft.mvp.contract.LoginContract;
import com.bestsoft.mvp.presenter.LoginPresenter;
import com.bestsoft.utils.DialogListener;
import com.bestsoft.utils.DialogUtils;
import com.bestsoft.utils.IntentUtils;

import butterknife.BindView;
import butterknife.OnClick;

@CreatePresenterAnnotation(LoginPresenter.class)
public class LoginActivity extends BaseMvpActivity<LoginContract.View, LoginContract.Presenter> implements LoginContract.View {

    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.btn_wechat_login)
    Button btnWechatLogin;
    @BindView(R.id.txt_cannot_login)
    TextView txtCannotLogin;
    @BindView(R.id.ll_wechat)
    LinearLayout llWechat;
    @BindView(R.id.txt_qq)
    TextView txtQq;
    @BindView(R.id.txt_phone)
    TextView txtPhone;
    @BindView(R.id.ll_other)
    LinearLayout llOther;
    @BindView(R.id.txt_other)
    TextView txtOther;
    @BindView(R.id.img_down)
    ImageButton imgDown;
    @BindView(R.id.ll_arrow)
    LinearLayout llArrow;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        txtCannotLogin.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        txtCannotLogin.getPaint().setAntiAlias(true);//抗锯齿
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarView(R.id.top_view)
                .statusBarColor(R.color.colorWhite)
                .statusBarDarkFont(true, 0.2f)
                .init();
    }

    @OnClick({R.id.btn_wechat_login, R.id.txt_qq, R.id.txt_phone, R.id.txt_other, R.id.img_down})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_wechat_login:
                DialogUtils.showPromptDialog(mContext, "你还没有注册或者未绑定微信", "提示", "去注册", "知道了", new DialogListener() {
                    @Override
                    public void onClick(boolean confirm) {
                        //todo 跳转注册页面
                        IntentUtils.get().goActivity(mContext, InputInvateInfoActivity.class);
                    }
                });
                break;
            case R.id.txt_qq:
                break;
            case R.id.txt_phone:
                break;
            case R.id.txt_other:
                if (llWechat.getVisibility() == View.VISIBLE) {
                    llWechat.setVisibility(View.GONE);
                    llOther.setVisibility(View.VISIBLE);
                    imgDown.setImageResource(R.drawable.ic_up);
                    String html = "登录既代表你同意<u><font color='#FFC72F'>巨折使用条款</u>";
                    txtOther.setText(Html.fromHtml(html));
                } else {
                    llWechat.setVisibility(View.VISIBLE);
                    llOther.setVisibility(View.GONE);
                    imgDown.setImageResource(R.drawable.ic_down);
                    txtOther.setText("其他登录方式");
                }
                break;
            case R.id.img_down:
                break;
        }
    }
}
