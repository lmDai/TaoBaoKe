package com.bestsoft.mvp.model;

import android.content.Context;

import com.bestsoft.api.TaoBaoKeService;
import com.bestsoft.bean.ChartModel;
import com.bestsoft.bean.TeamOrderModel;
import com.bestsoft.bean.TeamProfitModel;
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
    public Observable<BaseResponse<TeamProfitModel>> userTeamProfit(String user_id, String user_channel_id) {
        Map<String, Object> requestMap = InterceptUtils.getRequstMap();
        requestMap.put("user_id", user_id);
        requestMap.put("user_channel_id", user_channel_id);
        Observable<BaseResponse<TeamProfitModel>> userTeamProfit = mApiService.userTeamProfit(requestMap);
        return userTeamProfit;
    }

    /**
     * 会员团队订单
     *
     * @return
     */
    public Observable<BaseResponse<List<TeamOrderModel>>> userTeamOrder(int order_status, int order_type, int page, String user_id, String user_channel_id) {
        Map<String, Object> requestMap = InterceptUtils.getRequstMap();
        requestMap.put("order_status", order_status);
        requestMap.put("order_type", order_type);
        requestMap.put("page", page);
        requestMap.put("user_id", user_id);
        requestMap.put("user_channel_id", user_channel_id);
        Observable<BaseResponse<List<TeamOrderModel>>> userTeamOrder = mApiService.userTeamOrder(requestMap);
        return userTeamOrder;
    }

    /**
     * 会员团队订单
     *
     * @return
     */
    public Observable<BaseResponse<ChartModel>> userChart(String user_id, String user_channel_id) {
        Map<String, Object> requestMap = InterceptUtils.getRequstMap();
        requestMap.put("user_id", user_id);
        requestMap.put("user_channel_id", user_channel_id);
        Observable<BaseResponse<ChartModel>> userChart = mApiService.userChart(requestMap);
        return userChart;
    }
}
