package com.bestsoft.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bestsoft.common.https.BasePresenter;
import com.bestsoft.common.https.IBaseView;
import com.bestsoft.common.mvp_senior.factory.IMvpPresenterFactroy;
import com.bestsoft.common.mvp_senior.factory.IPresenterProxyFactroy;
import com.bestsoft.common.mvp_senior.factory.MvpPresenterFactroyImpl;
import com.bestsoft.common.mvp_senior.factory.PresenterProxyFactroyImpl;
import com.bestsoft.ui.widget.LoadingDialog;

import io.reactivex.ObservableTransformer;

/**
 * @package: com.bestsoft.base
 * @user:xhkj
 * @date:2018/10/29 TODO:注意  需要创建 presenter，必需要添加注解 @CreatePresenterAnnotation(xxx.class)
 **/
public abstract class BaseMvpFragment<V extends IBaseView, P extends BasePresenter<V>> extends BaseFragment
        implements IPresenterProxyFactroy<V, P>, IBaseView {
    private static final String PRESENTER_SAVE_KEY = "presenter_save_key";
    /**
     * 创建被代理对象,传入默认Presenter的工厂
     */
    private PresenterProxyFactroyImpl<V, P> mProxy = new PresenterProxyFactroyImpl<>(MvpPresenterFactroyImpl.<V, P>creater(getClass()));

    private LoadingDialog mLoadingDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mProxy.onRestoreInstanceState(savedInstanceState.getBundle(PRESENTER_SAVE_KEY));
        }
        mLoadingDialog = new LoadingDialog(mContext);
        mProxy.onCreate((V) this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("perfect-mvp", "V onResume");
//        mProxy.onResume((V) this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("perfect-mvp", "V onDestroy = ");
        mProxy.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("perfect-mvp", "V onSaveInstanceState");
        outState.putBundle(PRESENTER_SAVE_KEY, mProxy.onSaveInstanceState());
    }

    @Override
    public void setPresenterFactory(IMvpPresenterFactroy<V, P> presenterFactory) {
        Log.e("perfect-mvp", "V setPresenterFactory");
        mProxy.setPresenterFactory(presenterFactory);
    }

    @Override
    public IMvpPresenterFactroy<V, P> getPresenterFactory() {
        Log.e("perfect-mvp", "V getPresenterFactory");
        return mProxy.getPresenterFactory();
    }

    @Override
    public P getMvpPresenter() {
        Log.e("perfect-mvp", "V getMvpPresenter");
        return mProxy.getMvpPresenter();
    }

    @Override
    public void showProgress(String message) {
        if (mLoadingDialog != null) {
            mLoadingDialog.setMessage(message).show();
        }else {
            mLoadingDialog = new LoadingDialog(mContext);
            mLoadingDialog.setMessage(message).show();
        }
    }

    @Override
    public void hideProgress() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
            mLoadingDialog=null;
        }
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifeycle() {
        return this.bindToLifecycle();
    }
}

