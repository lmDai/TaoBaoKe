package com.bestsoft.mvp.model;

import android.content.Context;

import com.bestsoft.api.TaoBaoKeService;
import com.bestsoft.bean.ArticleModel;
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
public class SkillModule {
    private static SkillModule musicModel;
    private TaoBaoKeService mApiService;

    public SkillModule(Context context) {
        mApiService = RetrofitManager.getInstance().getRetrofitService(TaoBaoKeService.class);
    }

    public static SkillModule getInstance(Context context) {
        if (musicModel == null) {
            musicModel = new SkillModule(context);
        }
        return musicModel;
    }

    public Observable<BaseResponse<List<ArticleModel>>> shareAticle(int share_type,int page,String user_id, String user_channel_id) {
        Map<String, Object> requestMap = InterceptUtils.getRequstMap();
        requestMap.put("share_type", share_type);
        requestMap.put("page", page);
        requestMap.put("user_id", user_id);
        requestMap.put("user_channel_id", user_channel_id);
        Observable<BaseResponse<List<ArticleModel>>> shareAticle = mApiService.shareAticle(requestMap);
        return shareAticle;
    }
}
