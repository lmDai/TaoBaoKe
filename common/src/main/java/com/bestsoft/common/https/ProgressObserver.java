package com.bestsoft.common.https;

import android.support.annotation.NonNull;

import io.reactivex.disposables.Disposable;

/**
 * @author Administrator
 * @date 2018/10/16  15:21
 * @description 扩展类，用来调用请求网络时的进度条
 **/
public abstract class ProgressObserver<T> extends BaseObserver<T> {
    private BasePresenter mBasePresenter;
    private boolean isShowProgress = false;

    protected ProgressObserver(BasePresenter basePresenter) {
        this.mBasePresenter = basePresenter;
    }

    protected ProgressObserver(BasePresenter basePresenter, boolean isShowProgress) {
        this.mBasePresenter = basePresenter;
        this.isShowProgress = isShowProgress;
    }

    /**
     * 展示进度框
     */
    private void showProgress() {
        if (isShowProgress) {
            mBasePresenter.getView().showProgress();
        }
    }

    /**
     * 取消进度框
     */
    private void dismissProgress() {
        if (isShowProgress) {
            mBasePresenter.getView().hideProgress();
        }
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        if (!d.isDisposed()) {
            showProgress();
        }
    }


    @Override
    public void onComplete() {
        if (mBasePresenter.isViewAttached()) {
            dismissProgress();
        }
    }


    @Override
    public void onError(@NonNull Throwable e) {
        super.onError(e);
        if (mBasePresenter.isViewAttached()) {
            dismissProgress();
        }
    }
}

