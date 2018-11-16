package com.bestsoft.mvp.model;

import android.content.Context;

import com.bestsoft.api.TaoBaoKeService;
import com.bestsoft.bean.ExtractModel;
import com.bestsoft.bean.UserModel;
import com.bestsoft.common.https.BaseNoDataResponse;
import com.bestsoft.common.https.BaseResponse;
import com.bestsoft.common.https.RetrofitManager;
import com.bestsoft.common.https.intercept.InterceptUtils;

import java.util.Map;

import io.reactivex.Observable;

/**
 * @package: com.bestsoft.mvp.model
 * @user:xhkj
 * @date:2018/10/29
 * @description:
 **/
public class PersonModule {
    private static PersonModule musicModel;
    private TaoBaoKeService mApiService;

    public PersonModule(Context context) {
        mApiService = RetrofitManager.getInstance().getRetrofitService(TaoBaoKeService.class);
    }

    public static PersonModule getInstance(Context context) {
        if (musicModel == null) {
            musicModel = new PersonModule(context);
        }
        return musicModel;
    }

    public Observable<BaseResponse<UserModel>> getUserInfo(String user_id, String user_channel_id) {
        Map<String, Object> requestMap = InterceptUtils.getRequstMap();
        requestMap.put("user_id", user_id);
        requestMap.put("user_channel_id", user_channel_id);
        Observable<BaseResponse<UserModel>> detail = mApiService.getUserInfo(requestMap);
        return detail;
    }

    public Observable<BaseResponse<ExtractModel>> userExtract(String user_id, String user_channel_id) {
        Map<String, Object> requestMap = InterceptUtils.getRequstMap();
        requestMap.put("user_id", user_id);
        requestMap.put("user_channel_id", user_channel_id);
        Observable<BaseResponse<ExtractModel>> userExtract = mApiService.userExtract(requestMap);
        return userExtract;
    }

    public Observable<BaseNoDataResponse> userSettingTaobao(String user_id, String user_channel_id) {
        Map<String, Object> requestMap = InterceptUtils.getRequstMap();
        requestMap.put("user_id", user_id);
        requestMap.put("user_channel_id", user_channel_id);
        Observable<BaseNoDataResponse> userSettingTaobao = mApiService.userSettingTaobao(requestMap);
        return userSettingTaobao;
    }
}
