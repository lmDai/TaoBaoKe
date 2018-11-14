package com.bestsoft.mvp.model;

import android.content.Context;

import com.bestsoft.api.TaoBaoKeService;
import com.bestsoft.bean.ClassfyModel;
import com.bestsoft.bean.OrderConfirmModel;
import com.bestsoft.bean.ProductModel;
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

    /**
     * 分类列表
     *
     * @param channel_id
     * @return
     */
    public Observable<BaseResponse<List<ClassfyModel>>> getIconClassify(String channel_id) {
        Observable<BaseResponse<List<ClassfyModel>>> classify = mApiService.getIconClassify(channel_id);
        return classify;
    }

    /**
     * 类目商品列表
     *
     * @param channel_id
     * @return
     */
    public Observable<BaseResponse<List<ProductModel>>> getGoodHaoList(String key, String sort, String page
            , String user_id, String user_channel_id, int user_level) {
        Map<String, Object> requestMap = InterceptUtils.getRequstMap();
        requestMap.put("key", key);
        requestMap.put("sort", sort);
        requestMap.put("page", page);
        requestMap.put("user_id", user_id);
        requestMap.put("user_channel_id", user_channel_id);
        requestMap.put("user_level", user_level);
        Observable<BaseResponse<List<ProductModel>>> classify = mApiService.getGoodHaoList(requestMap);
        return classify;
    }

    /**
     * 类目商品详情
     *
     * @param channel_id
     * @return
     */
    public Observable<BaseResponse<ProductModel>> getHaoDetail(String item_id, String user_id, String user_channel_id) {
        Map<String, Object> requestMap = InterceptUtils.getRequstMap();
        requestMap.put("item_id", item_id);
        requestMap.put("user_id", user_id);
        requestMap.put("user_channel_id", user_channel_id);
        Observable<BaseResponse<ProductModel>> detail = mApiService.getGoodDetail(requestMap);
        return detail;
    }

    public Observable<BaseResponse<OrderConfirmModel>> orderConfirm(String item_id, String item_title,
                                                                    String item_price, String item_end_price,
                                                                    String tkrates, String tkmoney,
                                                                    String user_id, String user_channel_id,
                                                                    String couponmoney) {
        Map<String, Object> requestMap = InterceptUtils.getRequstMap();
        requestMap.put("item_id", item_id);
        requestMap.put("item_title", item_title);
        requestMap.put("item_price", item_price);
        requestMap.put("item_end_price", item_end_price);
        requestMap.put("tkrates", tkrates);
        requestMap.put("tkmoney", tkmoney);
        requestMap.put("user_id", user_id);
        requestMap.put("user_channel_id", user_channel_id);
        requestMap.put("couponmoney", couponmoney);
        Observable<BaseResponse<OrderConfirmModel>> orderConfirm = mApiService.orderConfirm(requestMap);
        return orderConfirm;
    }
}
