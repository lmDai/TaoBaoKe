package com.bestsoft.mvp.model;

import android.content.Context;

import com.bestsoft.api.TaoBaoKeService;
import com.bestsoft.bean.UpgradeModel;
import com.bestsoft.bean.UserModel;
import com.bestsoft.common.https.BaseResponse;
import com.bestsoft.common.https.RetrofitManager;
import com.bestsoft.common.https.intercept.InterceptUtils;

import java.util.Map;

import io.reactivex.Observable;

/**
 * @package: com.bestsoft.mvp.model
 * @user:xhkj
 * @date:2018/10/29
 * @description:团队数据
 **/
public class TeamModule {
    private static TeamModule musicModel;
    private TaoBaoKeService mApiService;

    public TeamModule(Context context) {
        mApiService = RetrofitManager.getInstance().getRetrofitService(TaoBaoKeService.class);
    }

    public static TeamModule getInstance(Context context) {
        if (musicModel == null) {
            musicModel = new TeamModule(context);
        }
        return musicModel;
    }


    /**
     * 团队收益
     *
     * @return
     */
    public Observable<BaseResponse<UpgradeModel>> userTeamProfit(String user_id, String user_channel_id) {
        Map<String, Object> requestMap = InterceptUtils.getRequstMap();
        requestMap.put("user_id", user_id);
        requestMap.put("user_channel_id", user_channel_id);
        Observable<BaseResponse<UpgradeModel>> userTeamProfit = mApiService.userTeamProfit(requestMap);
        return userTeamProfit;
    }
}
