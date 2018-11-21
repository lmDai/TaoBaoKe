package com.bestsoft.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bestsoft.MainActivity;
import com.bestsoft.R;
import com.bestsoft.base.BaseMvpActivity;
import com.bestsoft.bean.CodeModel;
import com.bestsoft.common.https.BaseNoDataResponse;
import com.bestsoft.common.mvp_senior.annotaions.CreatePresenterAnnotation;
import com.bestsoft.mvp.contract.RegisterContract;
import com.bestsoft.mvp.presenter.RegisterPresenter;
import com.bestsoft.ui.widget.VerifyCodeView;
import com.bestsoft.utils.IntentUtils;
import com.blankj.utilcode.util.ToastUtils;


import butterknife.BindView;
import butterknife.OnClick;

/**
 * 输入邀请信息
 */
@CreatePresenterAnnotation(RegisterPresenter.class)
public class InputCodeActivity extends BaseMvpActivity<RegisterContract.View, RegisterPresenter> implements RegisterContract.View {


    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.verify_code_view)
    VerifyCodeView verifyCodeView;
    private String phone;//手机号
    private CodeModel codeModel;
    private String nickName = LoginActivity.nickName;
    private String headimgurl = LoginActivity.headimgurl;
    private String openid = LoginActivity.unionid;
    private String type = LoginActivity.type;//注册方式WeChat，QQ  普通注册为空''


    @Override
    protected int getLayout() {
        return R.layout.activity_input_code;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {


    }

    @Override
    protected void getIntentData() {
        super.getIntentData();
        Bundle bundle = getIntent().getBundleExtra("bundle");
        if (bundle != null) {
            phone = bundle.getString("phone");
            codeModel = bundle.getParcelable("codeMode");
        }
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
                btnNext.setEnabled(true);
            }

            @Override
            public void invalidContent() {
                btnNext.setEnabled(false);
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
                if (codeModel == null) {
                    ToastUtils.showShort("邀请码不正确");
                    return;
                }
                getMvpPresenter().userRegister(nickName, headimgurl, openid, type, phone, verifyCodeView.getEditContent(), codeModel.getUser_channel_id()
                        , codeModel.getPid());
                break;
        }
    }

    @Override
    public void registerSuccess(BaseNoDataResponse result) {
        ToastUtils.showShort(result.getMsg());
        if (result.getErrorcode() == 0) {
            IntentUtils.get().goActivityKill(mContext, LoginActivity.class);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
