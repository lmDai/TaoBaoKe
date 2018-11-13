package com.bestsoft;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.baichuan.android.trade.AlibcTradeSDK;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeInitCallback;
import com.alibaba.fastjson.JSON;
import com.bestsoft.bean.UserModel;
import com.bestsoft.common.BaseApplication;
import com.bestsoft.utils.SpUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

/**
 * @package: com.bestsoft
 * @user:xhkj
 * @date:2018/10/29
 * @description:
 **/
public class MyApplication extends BaseApplication {
    public UserModel userModel;
    public static MyApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        AlibcTradeSDK.asyncInit(this, new AlibcTradeInitCallback() {
            @Override
            public void onSuccess() {
                //初始化成功，设置相关的全局配置参数
                Log.i("single","onSuccess");
                // ...
            }

            @Override
            public void onFailure(int code, String msg) {
                //初始化失败，可以根据code和msg判断失败原因，详情参见错误说明
                Log.i("single","onFailure"+code+msg);
            }
        });
    }

    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorWhite, R.color.colorBlack);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    public UserModel getUser() {
        String user = (String) SpUtils.getParam(this, Constant.USER, "");
        return userModel == null ? JSON.parseObject(user, UserModel.class) : userModel;
    }

    public void setUserModel(UserModel userModel) {
        String userResult = JSON.toJSONString(userModel);
        SpUtils.setParam(this, Constant.USER, userResult);
        this.userModel = userModel;
    }
}
