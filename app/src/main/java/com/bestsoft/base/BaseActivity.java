package com.bestsoft.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;

import com.bestsoft.utils.KeyboardUtils;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.Unbinder;

/**
 * @package: com.bestsoft.base
 * @user:xhkj
 * @date:2018/10/29
 * @description:
 **/
public abstract class BaseActivity extends RxAppCompatActivity {
    protected Activity mContext;
    protected Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        if (getLayout() != 0) {
            setContentView(getLayout());
        }
        getIntentData();
        initView(savedInstanceState);
        initEvent();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnBinder != null)
            mUnBinder.unbind();
    }

    protected abstract int getLayout();

    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 初始化
     */
    protected void init() {

    }

    protected void initEvent() {
    }

    protected void getIntentData() {
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                View view = getCurrentFocus();
                KeyboardUtils.clickBlankArea2HideSoftInput(view, ev, mContext);//调用方法判断是否需要隐藏键盘
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
