package com.bestsoft.common.https.rxUtils;

import android.text.TextUtils;
import android.util.Log;

import com.bestsoft.common.https.BaseResponse;
import com.bestsoft.common.https.IBaseView;
import com.bestsoft.common.https.Optional;
import com.bestsoft.common.https.exception.ApiException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * @package: com.bestsoft.common.https.rxUtils
 * @user:xhkj
 * @date:2018/10/29
 * @description: 结果预处理
 **/
public class RxUtil {
    public static <T> ObservableTransformer<T, T> observableIO2Main(IBaseView iBaseView) {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(iBaseView.bindLifeycle());
    }

    public static <T> ObservableTransformer<BaseResponse<T>, T> hanResult() {
        return upstream -> upstream.flatMap((Function<BaseResponse<T>, ObservableSource<T>>) tBaseResponse -> {
            if (tBaseResponse.getErrorcode() == 0) {
                return createData(tBaseResponse.getData());
            } else if (!TextUtils.isEmpty(tBaseResponse.getMsg())) {
                return Observable.error(new ApiException(tBaseResponse.getMsg(), tBaseResponse.getErrorcode()));
            } else {
                return Observable.error(new ApiException("*" + "服务器返回error", 10000));
            }
        });
    }

    public static <T> ObservableTransformer<T, T> handNoResponseResult() {
        return upstream -> upstream.flatMap((Function<T, ObservableSource<T>>) tBaseResponse -> {
            if (tBaseResponse != null) {
                return createData(tBaseResponse);
            } else {
                return Observable.error(new ApiException("*" + "服务器返回error", 10000));
            }
        });
    }

    /**
     * http请求结果处理方法
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<BaseResponse<T>, Optional<T>> handleNullresult() {
        return upstream -> upstream
                .flatMap(new Function<BaseResponse<T>, ObservableSource<Optional<T>>>() {
                             @Override
                             public ObservableSource<Optional<T>> apply(@NonNull BaseResponse<T> result) throws Exception {
                                 if (result.getErrorcode() == BaseResponse.SUCCESS) {
                                     // result.transform() 就是将返回结果进行包装
                                     return createHttpData(result.transform());
                                 } else if (!TextUtils.isEmpty(result.getMsg())) {
                                     return Observable.error(new ApiException(result.getMsg(), result.getErrorcode()));
                                 } else {
                                     return Observable.error(new ApiException("*" + "服务器返回error", 10000));
                                 }
                             }
                         }
                );
    }

    private static <T> Observable<Optional<T>> createHttpData(Optional<T> transform) {
        return Observable.create(e -> {
            try {
                e.onNext(transform);
                e.onComplete();
            } catch (Exception exc) {
                e.onError(exc);
            }
        });
    }

    /**
     * 生成Observable
     *
     * @param <T>
     * @return
     */
    private static <T> Observable<T> createData(final T t) {
        return Observable.create(subscriber -> {
            try {
                subscriber.onNext(t);
                subscriber.onComplete();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        });
    }
}
