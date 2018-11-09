package com.bestsoft.common.https.rxUtils;

import android.text.TextUtils;

import com.bestsoft.common.https.BaseResponse;
import com.bestsoft.common.https.IBaseView;
import com.bestsoft.common.https.exception.ApiException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
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
