package com.bestsoft.mvp.model;

import android.content.Context;

import com.bestsoft.api.TaoBaoKeService;
import com.bestsoft.bean.OrderModel;
import com.bestsoft.bean.ProfitModel;
import com.bestsoft.bean.UpgradeModel;
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
 * @description:订单模块
 **/
public class MemberModule {
    private static MemberModule musicModel;
    private TaoBaoKeService mApiService;

    public MemberModule(Context context) {
        mApiService = RetrofitManager.getInstance().getRetrofitService(TaoBaoKeService.class);
    }
    public static MemberModule getInstance(Context context) {
        if (musicModel == null) {
            musicModel = new MemberModule(context);
        }
        return musicModel;
    }

    public Observable<BaseResponse<UpgradeModel>> userUpgrade(String user_id, String user_channel_id) {
        Map<String, Object> requestMap = InterceptUtils.getRequstMap();
        requestMap.put("user_id", user_id);
        requestMap.put("user_channel_id", user_channel_id);
        Observable<BaseResponse<UpgradeModel>> upgradeModel = mApiService.userUpgrade(requestMap);
        return upgradeModel;
    }
}
