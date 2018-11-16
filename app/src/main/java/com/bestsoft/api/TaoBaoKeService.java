package com.bestsoft.api;

import com.bestsoft.bean.AdvertModel;
import com.bestsoft.bean.ArticleModel;
import com.bestsoft.bean.ChartModel;
import com.bestsoft.bean.ClassfyModel;
import com.bestsoft.bean.CodeModel;
import com.bestsoft.bean.ExtractModel;
import com.bestsoft.bean.IconModel;
import com.bestsoft.bean.KeyWordModel;
import com.bestsoft.bean.OrderConfirmModel;
import com.bestsoft.bean.OrderModel;
import com.bestsoft.bean.ProductModel;
import com.bestsoft.bean.ProfitModel;
import com.bestsoft.bean.TeamOrderModel;
import com.bestsoft.bean.TeamProfitModel;
import com.bestsoft.bean.UpgradeModel;
import com.bestsoft.bean.UserModel;
import com.bestsoft.common.https.BaseNoDataResponse;
import com.bestsoft.common.https.BasePageResponse;
import com.bestsoft.common.https.BaseResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

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

    //登录
    @FormUrlEncoded
    @POST(TaoBaoKeApi.LOGIN)
    Observable<BaseResponse<UserModel>> login(@FieldMap Map<String, Object> requestMap);

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
    Observable<BaseNoDataResponse> sendSmsCode(@FieldMap Map<String, Object> map);

    //会员注册
    @POST(TaoBaoKeApi.USER_REGISTER)
    @FormUrlEncoded
    Observable<BaseNoDataResponse> userRegister(@FieldMap Map<String, Object> map);

    //类目商品列表
    @POST(TaoBaoKeApi.HAO_LIST)
    @FormUrlEncoded
    Observable<BasePageResponse<List<ProductModel>>> getGoodHaoList(@FieldMap Map<String, Object> map);

    //商品关键字
    @POST(TaoBaoKeApi.HAO_SEARCH)
    @FormUrlEncoded
    Observable<BasePageResponse<List<ProductModel>>> getGoodsSearch(@FieldMap Map<String, Object> map);

    //获取热搜关键词
    @POST(TaoBaoKeApi.HOT_KEYWORD)
    @FormUrlEncoded
    Observable<BaseResponse<List<KeyWordModel>>> getHotKeyWord(@FieldMap Map<String, Object> map);

    //商品详情
    @POST(TaoBaoKeApi.HAO_DETAIL)
    @FormUrlEncoded
    Observable<BaseResponse<ProductModel>> getGoodDetail(@FieldMap Map<String, Object> map);

    //会员信息
    @POST(TaoBaoKeApi.USER_INFO)
    @FormUrlEncoded
    Observable<BaseResponse<UserModel>> getUserInfo(@FieldMap Map<String, Object> map);

    //下单
    @FormUrlEncoded
    @POST(TaoBaoKeApi.ORDER_CONFIRM)
    Observable<BaseResponse<OrderConfirmModel>> orderConfirm(@FieldMap Map<String, Object> map);

    //下单
    @FormUrlEncoded
    @POST(TaoBaoKeApi.ORDER_PAY_CONFIRM)
    Observable<BaseNoDataResponse> orderPayConfirm(@FieldMap Map<String, Object> map);

    //会员收益数据
    @FormUrlEncoded
    @POST(TaoBaoKeApi.USER_PROFIT)
    Observable<BaseResponse<ProfitModel>> userProfit(@FieldMap Map<String, Object> map);

    //会员订单数据
    @FormUrlEncoded
    @POST(TaoBaoKeApi.USER_ORDER)
    Observable<BaseResponse<List<OrderModel>>> userOrder(@FieldMap Map<String, Object> map);

    //会员订单数据
    @FormUrlEncoded
    @POST(TaoBaoKeApi.USER_UPGRADE)
    Observable<BaseResponse<UpgradeModel>> userUpgrade(@FieldMap Map<String, Object> map);

    //团队收益数据
    @FormUrlEncoded
    @POST(TaoBaoKeApi.TEAM_PROFIT)
    Observable<BaseResponse<TeamProfitModel>> userTeamProfit(@FieldMap Map<String, Object> map);

    //会员团队订单
    @FormUrlEncoded
    @POST(TaoBaoKeApi.TEAM_ORDER)
    Observable<BaseResponse<List<TeamOrderModel>>> userTeamOrder(@FieldMap Map<String, Object> map);

    //会员团队收益图表
    @FormUrlEncoded
    @POST(TaoBaoKeApi.USER_CHART)
    Observable<BaseResponse<ChartModel>> userChart(@FieldMap Map<String, Object> map);

    //首页广告
    @FormUrlEncoded
    @POST(TaoBaoKeApi.ADVERT_HOME)
    Observable<BaseResponse<List<AdvertModel>>> homeAdavert(@FieldMap Map<String, Object> map);

    //首页图标
    @FormUrlEncoded
    @POST(TaoBaoKeApi.ICON_PAGE)
    Observable<BaseResponse<List<IconModel>>> homeIconpage(@FieldMap Map<String, Object> map);

    //分享文章列表
    @FormUrlEncoded
    @POST(TaoBaoKeApi.SHARE_ARTICLE)
    Observable<BaseResponse<List<ArticleModel>>> shareAticle(@FieldMap Map<String, Object> map);

    //会员提现页面数据
    @FormUrlEncoded
    @POST(TaoBaoKeApi.USER_EXTRACT)
    Observable<BaseResponse<ExtractModel>> userExtract(@FieldMap Map<String, Object> map);

    //设置淘宝授权
    @POST(TaoBaoKeApi.USER_SETTING_TAOBAO)
    @FormUrlEncoded
    Observable<BaseNoDataResponse> userSettingTaobao(@FieldMap Map<String, Object> map);
}
