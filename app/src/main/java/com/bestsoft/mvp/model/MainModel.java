package com.bestsoft.mvp.model;

import android.content.Context;

import com.bestsoft.api.TaoBaoKeService;
import com.bestsoft.common.https.BaseResponse;
import com.bestsoft.common.https.RetrofitManager;

import java.util.List;

import io.reactivex.Observable;

/**
 * @package: com.bestsoft.mvp.model
 * @user:xhkj
 * @date:2018/10/29
 * @description:
 **/
public class MainModel {
    private static MainModel musicModel;
    private TaoBaoKeService mApiService;

    public MainModel(Context context) {
        mApiService = RetrofitManager.getInstance().getRetrofitService(TaoBaoKeService.class);
    }

    public static MainModel getInstance(Context context) {
        if (musicModel == null) {
            musicModel = new MainModel(context);
        }
        return musicModel;
    }

    public Observable<BaseResponse<List<String>>> getIconClassify() {
        Observable<BaseResponse<List<String>>> book = mApiService.getIconClassify("2");
        return book;
    }

}
