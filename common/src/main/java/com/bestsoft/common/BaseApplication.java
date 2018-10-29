package com.bestsoft.common;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.bestsoft.common.utils.Utils;

/**
 * @package: com.bestsoft.common
 * @user:xhkj
 * @date:2018/10/29 BaseApplication
 * @description:
 **/
public class BaseApplication extends Application {
    private static BaseApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        Utils.init(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    /**
     * 利用单利模式获取Application实例
     *
     * @return mApplicaiton
     */
    public static BaseApplication getInstance() {
        if (null == mApplication) {
            mApplication = new BaseApplication();
        }
        return mApplication;
    }
}
