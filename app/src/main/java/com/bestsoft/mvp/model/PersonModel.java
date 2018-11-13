package com.bestsoft.mvp.model;

import android.content.Context;

import com.ali.auth.third.core.model.User;
import com.bestsoft.api.TaoBaoKeService;
import com.bestsoft.bean.ClassfyModel;
import com.bestsoft.bean.ProductModel;
import com.bestsoft.bean.UserModel;
import com.bestsoft.common.https.BaseResponse;
import com.bestsoft.common.https.RetrofitManager;
import com.bestsoft.common.https.intercept.InterceptUtils;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

/**
 * @package: com.bestsoft.mvp.model
 * @user:xhkj
 * @date:2018/10/29
 * @description:
 **/
public class PersonModel {
    private static PersonModel musicModel;
    private TaoBaoKeService mApiService;

    public PersonModel(Context context) {
        mApiService = RetrofitManager.getInstance().getRetrofitService(TaoBaoKeService.class);
    }

    public static PersonModel getInstance(Context context) {
        if (musicModel == null) {
            musicModel = new PersonModel(context);
        }
        return musicModel;
    }


    /**
     * 会员信息
     *
     * @param channel_id
     * @return
     */
    public Observable<BaseResponse<UserModel>> getUserInfo(String user_id, String user_channel_id) {
        Map<String, Object> requestMap = InterceptUtils.getRequstMap();
        requestMap.put("user_id", user_id);
        requestMap.put("user_channel_id", user_channel_id);
        Observable<BaseResponse<UserModel>> detail = mApiService.getUserInfo(requestMap);
        return detail;
    }
}