package com.bestsoft.api;

import com.bestsoft.common.https.BaseResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @package: com.bestsoft.api
 * @user:xhkj
 * @date:2018/10/29
 * @description:
 **/
public interface TaoBaoKeService {
    //全部标签
    @POST(TaoBaoKeApi.TAG)
    Observable<BaseResponse<List<String>>> getTagList();
    //全部标签
    @POST(TaoBaoKeApi.TAG)
    @FormUrlEncoded
    Observable<BaseResponse<List<String>>> getIconClassify(@Field("channel_id") String id);
}
