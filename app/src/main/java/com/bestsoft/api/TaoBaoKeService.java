package com.bestsoft.api;

import com.bestsoft.bean.ClassfyModel;
import com.bestsoft.bean.CodeModel;
import com.bestsoft.common.https.BaseResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
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
    Observable<BaseResponse<List<ClassfyModel>>> getIconClassify(@Field("channel_id") String channel_id);

    //邀请码验证
    @POST(TaoBaoKeApi.CODE)
    @FormUrlEncoded
    Observable<BaseResponse<CodeModel>> validateInviteCode(@FieldMap Map<String, Object> map);
    //发送短信验证码
    @POST(TaoBaoKeApi.SEND_SMS_CODE)
    @FormUrlEncoded
    Observable<BaseResponse<String>> sendSmsCode(@FieldMap Map<String, Object> map);
    //会员注册
    @POST(TaoBaoKeApi.USER_REGISTER)
    @FormUrlEncoded
    Observable<BaseResponse<String>> userRegister(@FieldMap Map<String, Object> map);
}
