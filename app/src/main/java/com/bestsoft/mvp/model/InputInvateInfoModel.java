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
public class InputInvateInfoModel {
    private static InputInvateInfoModel musicModel;
    private TaoBaoKeService mApiService;

    public InputInvateInfoModel(Context context) {
        mApiService = RetrofitManager.getInstance().getRetrofitService(TaoBaoKeService.class);
    }

    public static InputInvateInfoModel getInstance(Context context) {
        if (musicModel == null) {
            musicModel = new InputInvateInfoModel(context);
        }
        return musicModel;
    }

    public Observable<BaseResponse<List<String>>> getTag() {
        Observable<BaseResponse<List<String>>> book = mApiService.getTagList();
        return book;
    }

}
