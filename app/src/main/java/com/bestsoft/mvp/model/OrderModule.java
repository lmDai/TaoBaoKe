package com.bestsoft.mvp.model;

import android.content.Context;

import com.bestsoft.api.TaoBaoKeService;
import com.bestsoft.bean.OrderModel;
import com.bestsoft.bean.ProfitModel;
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
public class OrderModule {
    private static OrderModule musicModel;
    private TaoBaoKeService mApiService;

    public OrderModule(Context context) {
        mApiService = RetrofitManager.getInstance().getRetrofitService(TaoBaoKeService.class);
    }

    public static OrderModule getInstance(Context context) {
        if (musicModel == null) {
            musicModel = new OrderModule(context);
        }
        return musicModel;
    }

    public Observable<BaseResponse<ProfitModel>> userProfit(String user_id, String user_channel_id) {
        Map<String, Object> requestMap = InterceptUtils.getRequstMap();
        requestMap.put("user_id", user_id);
        requestMap.put("user_channel_id", user_channel_id);
        Observable<BaseResponse<ProfitModel>> userProfit = mApiService.userProfit(requestMap);
        return userProfit;
    }

    public Observable<BaseResponse<List<OrderModel>>> userOrder(String order_status, String page, String user_id, String user_channel_id) {
        Map<String, Object> requestMap = InterceptUtils.getRequstMap();
        requestMap.put("order_status", order_status);
        requestMap.put("page", page);
        requestMap.put("user_id", user_id);
        requestMap.put("user_channel_id", user_channel_id);
        Observable<BaseResponse<List<OrderModel>>> userOrder = mApiService.userOrder(requestMap);
        return userOrder;
    }
}
