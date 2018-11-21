package com.bestsoft.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bestsoft.Constant;
import com.bestsoft.MainActivity;
import com.bestsoft.MyApplication;
import com.bestsoft.R;
import com.bestsoft.base.BaseMvpActivity;
import com.bestsoft.bean.ThirdLoginModel;
import com.bestsoft.bean.UserModel;
import com.bestsoft.common.https.BaseNoDataResponse;
import com.bestsoft.common.mvp_senior.annotaions.CreatePresenterAnnotation;
import com.bestsoft.mvp.contract.LoginContract;
import com.bestsoft.mvp.contract.PhoneLoginContract;
import com.bestsoft.mvp.presenter.LoginPresenter;
import com.bestsoft.mvp.presenter.PhoneLoginPresenter;
import com.bestsoft.utils.DialogListener;
import com.bestsoft.utils.DialogUtils;
import com.bestsoft.utils.IntentUtils;
import com.bestsoft.utils.RuntimeRationale;
import com.bestsoft.utils.SpUtils;
import com.bestsoft.utils.TextFontUtils;
import com.blankj.utilcode.util.LogUtils;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.Setting;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import io.reactivex.annotations.NonNull;

@CreatePresenterAnnotation(LoginPresenter.class)
public class LoginActivity extends BaseMvpActivity<LoginContract.View, LoginPresenter> implements LoginContract.View {

    @BindView(R.id.txt_info)
    TextView txtInfo;
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
    public static String type = "";//wechat，qq
    public static String unionid = "";
    public static String nickName = "";
    public static String headimgurl = "";

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        txtCannotLogin.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        txtCannotLogin.getPaint().setAntiAlias(true);//抗锯齿
        requestPermission(Permission.READ_PHONE_STATE, Permission.WRITE_EXTERNAL_STORAGE, Permission.READ_EXTERNAL_STORAGE);
        if ((boolean) SpUtils.getParam(mContext, Constant.isLOGIN, false)) {
            IntentUtils.get().goActivityKill(mContext, MainActivity.class);
        }
        TextFontUtils.setTextTypeRTW(mContext, txtInfo);
    }

    /**
     * Request permissions.
     */
    private void requestPermission(String... permissions) {
        AndPermission.with(this)
                .runtime()
                .permission(permissions)
                .rationale(new RuntimeRationale())
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {

                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(@NonNull List<String> permissions) {

                        if (AndPermission.hasAlwaysDeniedPermission(LoginActivity.this, permissions)) {
                            showSettingDialog(LoginActivity.this, permissions);
                        }
                    }
                })
                .start();
    }

    /**
     * Display setting dialog.
     */
    public void showSettingDialog(Context context, final List<String> permissions) {
        List<String> permissionNames = Permission.transformText(context, permissions);
        String message = context.getString(R.string.message_permission_always_failed, TextUtils.join("\n", permissionNames));

        new AlertDialog.Builder(context)
                .setCancelable(false)
                .setTitle(R.string.title_dialog)
                .setMessage(message)
                .setPositiveButton(R.string.setting, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setPermission();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }

    /**
     * Set permissions.
     */
    private void setPermission() {
        AndPermission.with(this)
                .runtime()
                .setting()
                .onComeback(new Setting.Action() {
                    @Override
                    public void onAction() {

                    }
                })
                .start();
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarView(R.id.top_view)
                .statusBarColor(R.color.colorWhite)
                .statusBarDarkFont(true, 0.2f)
                .init();
    }

    @OnClick({R.id.btn_wechat_login, R.id.ll_qq, R.id.ll_phone, R.id.txt_other, R.id.img_down})
    public void onViewClicked(View view) {
        Platform plat = null;
        switch (view.getId()) {
            case R.id.btn_wechat_login:
                plat = ShareSDK.getPlatform(Wechat.NAME);
                if (plat.isAuthValid()) {
                    plat.removeAccount(true);
                }
                plat.SSOSetting(true);
                plat.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        LogUtils.i("onComplete" + platform.getDb().getUserId() + hashMap.get("nickname") + hashMap.get("figureurl"));
                        unionid = platform.getDb().getUserId();
                        nickName = platform.getDb().getUserName();//获取用户名字
                        headimgurl = platform.getDb().getUserIcon(); //获取用户头像

                        type = "wechat";
                        LoginActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (userModel != null) {
                                    getMvpPresenter().thirdLogin(type, unionid, userModel.getId(), userModel.getUser_channel_id());
                                } else {
                                    getMvpPresenter().thirdLogin(type, unionid, "", "");

                                }
                            }
                        });

                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {
                        LogUtils.i("onError" + platform + throwable.getMessage());
                    }

                    @Override
                    public void onCancel(Platform platform, int i) {
                        LogUtils.i("onCancel" + platform);
                    }
                });
                plat.showUser(null);
                break;
            case R.id.ll_qq:
                plat = ShareSDK.getPlatform(QQ.NAME);
                if (plat.isAuthValid()) {
                    plat.removeAccount(true);
                }
                plat.SSOSetting(true);
                plat.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        LogUtils.i("onComplete" + platform.getDb().getUserId() + hashMap.get("nickname") + hashMap.get("figureurl"));
                        unionid = platform.getDb().getUserId();
                        nickName = (String) hashMap.get("nickname");
                        headimgurl = (String) hashMap.get("figureurl_qq_2");
                        type = "QQ";
                        LoginActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (userModel != null) {
                                    getMvpPresenter().thirdLogin(type, unionid, userModel.getId(), userModel.getUser_channel_id());
                                } else {
                                    getMvpPresenter().thirdLogin(type, unionid, "", "");

                                }
                            }
                        });

                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {
                        LogUtils.i("onError" + platform + throwable.getMessage());
                    }

                    @Override
                    public void onCancel(Platform platform, int i) {
                        LogUtils.i("onCancel" + platform);
                    }
                });
                plat.showUser(null);

                break;
            case R.id.ll_phone:
                IntentUtils.get().goActivity(mContext, PhoneLoginActivity.class);//手机号登录
                break;
            case R.id.txt_other:

                break;
            case R.id.img_down:
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
        }
    }

    @Override
    public void loginSuccess(ThirdLoginModel userModel) {
        if (userModel.getErrorcode() == 2) {
            showRegisterDialog();
        } else if (userModel.getErrorcode() == 0) {
            SpUtils.setParam(mContext, Constant.isLOGIN, true);
            UserModel user = JSON.parseObject(JSON.toJSONString(userModel.getData()), UserModel.class);
            MyApplication.mApplication.setUserModel(user);
            IntentUtils.get().goActivityKill(mContext, MainActivity.class);//手机号登录
        }

    }

    public void showRegisterDialog() {
        DialogUtils.showPromptDialog(mContext, "你还没有注册或者未绑定微信", "提示", "去注册", "知道了", new DialogListener() {
            @Override
            public void onClick(boolean confirm) {
                if (confirm)
                    //todo 跳转注册页面
                    IntentUtils.get().goActivity(mContext, InputInvateInfoActivity.class);
            }
        });

    }
}
