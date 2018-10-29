package com.bestsoft.common.https;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.bestsoft.common.R;
import com.bestsoft.common.https.exception.ApiException;
import com.bestsoft.common.https.exception.ExceptionUtil;
import com.bestsoft.common.https.rxUtils.RxBus;
import com.bestsoft.common.utils.Utils;
import com.blankj.utilcode.utils.LogUtils;
import com.blankj.utilcode.utils.NetworkUtils;
import com.blankj.utilcode.utils.ToastUtils;

import java.lang.ref.WeakReference;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @package: com.bestsoft.common.https
 * @user:xhkj
 * @date:2018/10/29
 * @description: 预处理服务器返回数据
 **/
public abstract class BaseObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {
        if (!NetworkUtils.isConnected(Utils.getContext())) {
            onComplete();
        }
    }

    @Override
    public final void onNext(T result) {
        onSuccess(result);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        String msg;
        int code = ExceptionUtil.exceptionHandler(e);
        LogUtils.d("错误信息---" + e.getMessage() + "-----错误码: " + code);
        if (e instanceof ApiException) {
            msg = e.getMessage();
        } else {
            msg = ExceptionUtil.getMsg(code);
        }
        onFailure(e, code, msg);
    }

    @Override
    public void onComplete() {

    }

    public abstract void onSuccess(T result);

    public void onFailure(Throwable e, int code, String errorMsg) {
        ToastUtils.showShortToastSafe(Utils.getContext(), errorMsg);
    }
}
