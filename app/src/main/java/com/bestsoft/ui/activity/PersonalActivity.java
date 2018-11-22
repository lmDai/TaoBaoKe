package com.bestsoft.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.ali.auth.third.login.callback.LogoutCallback;
import com.ali.auth.third.ui.context.CallbackContext;
import com.alibaba.baichuan.android.trade.adapter.login.AlibcLogin;
import com.alibaba.baichuan.android.trade.callback.AlibcLoginCallback;
import com.bestsoft.Constant;
import com.bestsoft.MyApplication;
import com.bestsoft.R;
import com.bestsoft.base.BaseMvpActivity;
import com.bestsoft.bean.UserModel;
import com.bestsoft.bean.VersionModel;
import com.bestsoft.common.https.BaseNoDataResponse;
import com.bestsoft.common.https.rxUtils.RxEvent;
import com.bestsoft.common.mvp_senior.annotaions.CreatePresenterAnnotation;
import com.bestsoft.mvp.contract.PersonalContract;
import com.bestsoft.mvp.presenter.PersonalPresenter;
import com.bestsoft.utils.AppManager;
import com.bestsoft.utils.AppUpdateService;
import com.bestsoft.utils.DialogListener;
import com.bestsoft.utils.DialogUtils;
import com.bestsoft.utils.GlideUtil;
import com.bestsoft.utils.IntentUtils;
import com.bestsoft.utils.KeyboardUtils;
import com.bestsoft.utils.SpUtils;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.Setting;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@CreatePresenterAnnotation(PersonalPresenter.class)
public class PersonalActivity extends BaseMvpActivity<PersonalContract.View, PersonalPresenter> implements PersonalContract.View {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.txt_id)
    TextView txtId;
    @BindView(R.id.img_head)
    ImageView imgHead;
    @BindView(R.id.ll_share)
    LinearLayout llShare;
    @BindView(R.id.ll_auth)
    LinearLayout llAuth;
    @BindView(R.id.ll_alypay)
    LinearLayout llAlypay;
    @BindView(R.id.ll_center)
    LinearLayout llCenter;
    @BindView(R.id.ll_feedback)
    LinearLayout llFeedback;
    @BindView(R.id.ll_check_update)
    LinearLayout llCheckUpdate;
    @BindView(R.id.btn_logout)
    Button btnLogout;
    @BindView(R.id.btn_switch)
    Switch btnSwitch;

    @Override
    protected int getLayout() {
        return R.layout.activity_personal;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        txtTitle.setText(mContext.getString(R.string.title_personal));
//        KeyboardUtils.setRipper(llShare);
//        KeyboardUtils.setRipper(llAuth);
//        KeyboardUtils.setRipper(llCenter);
//        KeyboardUtils.setRipper(llAlypay);
//        KeyboardUtils.setRipper(llCheckUpdate);
//        KeyboardUtils.setRipper(llFeedback);
//        KeyboardUtils.setRipper(btnLogout);
        getMvpPresenter().getUserInfo(userModel.getId(), userModel.getUser_channel_id());
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        btnSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //防止初始化的时候出发监听
                if (!buttonView.isPressed()) {
                    return;
                }
                if (isChecked) {
                    login();
                } else {
                    logout();
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

    @OnClick({R.id.img_back, R.id.img_head, R.id.ll_share, R.id.ll_auth, R.id.ll_alypay, R.id.ll_center, R.id.ll_feedback, R.id.ll_check_update, R.id.btn_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
            case R.id.img_head:
                break;
            case R.id.ll_share:
                break;
            case R.id.ll_auth:
                login();
                break;
            case R.id.ll_alypay:
                IntentUtils.get().goActivity(mContext, MyAliPayActivity.class);
                break;
            case R.id.ll_center:
                break;
            case R.id.ll_feedback:
                IntentUtils.get().goActivity(mContext, FeedbackActivity.class);
                break;
            case R.id.ll_check_update:
                getMvpPresenter().userVersion(AppUtils.getAppVersionName(), userModel.getId(), userModel.getUser_channel_id());
                break;
            case R.id.btn_logout:
                AppManager.getAppManager().finishAllActivity();
                SpUtils.clear(mContext, Constant.isLOGIN);
                ActivityUtils.startActivity(LoginActivity.class);
                break;
        }
    }

    @Override
    public void setUserModel(UserModel userModel) {
        MyApplication.mApplication.setUserModel(userModel);
        btnSwitch.setChecked(userModel.getSettingtaobao() == 1);
        txtName.setText(userModel.getNickname());
        txtId.setText(userModel.getId());
        GlideUtil.loadCirclePic(mContext, userModel.getHeadimgurl(), imgHead);
    }

    @Override
    public void userSettingTaobao(BaseNoDataResponse settingResult) {
        ToastUtils.showShort(settingResult.getMsg());
        if (settingResult.getErrorcode() == 0) {
            userModel.setSettingtaobao(userModel.getSettingtaobao() == 1 ? 2 : 1);
            MyApplication.mApplication.setUserModel(userModel);
            btnSwitch.setChecked(userModel.getSettingtaobao() == 1);
        }
    }

    @Override
    public void setUserVersion(VersionModel version) {
        DialogUtils.showPromptDialog(mContext, version.getContent(), "提示", "立即更新", "暂不更新", new DialogListener() {
            @Override
            public void onClick(boolean confirm) {
                if (confirm) {
                    if (PermissionUtils.isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        String downloadUrl = version.getAddress();
                        Intent intent = new Intent(mContext, AppUpdateService.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("download_url", downloadUrl);
                        bundle.putInt("download_id", 10);
                        bundle.putString("download_file", downloadUrl.substring(downloadUrl.lastIndexOf('/') + 1));
                        intent.putExtras(bundle);
                        startService(intent);
                    } else {
                        ToastUtils.showShort("请在设置-应用中打开存储权限");
                    }
                }
            }
        });
    }

    public void login() {
        final AlibcLogin alibcLogin = AlibcLogin.getInstance();
        if (!alibcLogin.isLogin()) {
            alibcLogin.showLogin(mContext, new AlibcLoginCallback() {
                @Override
                public void onSuccess() {
                    //获取淘宝用户信息
                    LogUtils.i("获取淘宝用户信息: " + AlibcLogin.getInstance().getSession());
                    getMvpPresenter().userSettingTaobao(userModel.getId(), userModel.getUser_channel_id());
                }

                @Override
                public void onFailure(int code, String msg) {
                    ToastUtils.showShort(msg);
                    btnSwitch.setChecked(false);
                    userModel.setSettingtaobao(2);
                    MyApplication.mApplication.setUserModel(userModel);
                    EventBus.getDefault().post(new RxEvent(1, Constant.UPDATE_USER));
                }
            });
        } else {
            getMvpPresenter().userSettingTaobao(userModel.getId(), userModel.getUser_channel_id());
        }
    }

    public void logout() {
        AlibcLogin alibcLogin = AlibcLogin.getInstance();
        alibcLogin.logout(mContext, new LogoutCallback() {
            @Override
            public void onSuccess() {
                LogUtils.i("onSuccess: ");
//                getMvpPresenter().userSettingTaobao(userModel.getId(), userModel.getUser_channel_id());
            }

            @Override
            public void onFailure(int code, String msg) {
                LogUtils.i("code: " + code + msg);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        CallbackContext.onActivityResult(requestCode, resultCode, data);
    }
}
