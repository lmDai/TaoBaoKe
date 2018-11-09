package com.bestsoft.common.https;

import com.bestsoft.common.https.intercept.InterceptUtils;
import com.bestsoft.common.utils.Utils;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @package: com.bestsoft.common.https
 * @user:xhkj
 * @date:2018/10/29
 * @description:统一接口实例的管理类RetrofitManager 特性：
 * 单例形式创建Retrofit实例；
 * 使用okhttp3作为请求客户端；
 * 使用gson作为数据转换器；
 * 使用RxJava优化异步请求流程；
 * 开启数据缓存，无网络时可从缓存读取数据；
 * 辅助类静态方法获取Retrofit Service实例。
 **/
public class RetrofitManager {
    private HashMap<String, Retrofit> mRetrofitHashMap = new HashMap<>();//保存retrofit的实例
    private static final int DEFAULT_MILLISECONDS = 3000;

    /**
     * 内部类单例设计模式
     */
    private RetrofitManager() {
    }

    private static class RetrofitManagerInstance {
        private final static RetrofitManager RETROFIT_MANAGER = new RetrofitManager();
    }

    public static RetrofitManager getInstance() {
        return RetrofitManagerInstance.RETROFIT_MANAGER;
    }

    /**
     * 获取retrofit的实例
     *
     * @return Retrofit
     */
    private Retrofit getRetrofit(String baseUrl) {
        Retrofit retrofit;
        if (mRetrofitHashMap.containsKey(baseUrl)) {
            retrofit = mRetrofitHashMap.get(baseUrl);
        } else {
            retrofit = createRetrofit(baseUrl);
        }
        return retrofit;
    }

    /**
     * 创建retrofit
     *
     * @return Retrofit
     */
    private Retrofit createRetrofit(String baseUrl) {
        File cacheFile = new File(Utils.getContext().getExternalCacheDir(), "bestcache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        OkHttpClient httpClient = new OkHttpClient().newBuilder()
                .readTimeout(DEFAULT_MILLISECONDS, TimeUnit.SECONDS)
                .connectTimeout(DEFAULT_MILLISECONDS, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_MILLISECONDS, TimeUnit.SECONDS)
                .addNetworkInterceptor(InterceptUtils.getRequestHeader())
//                .addInterceptor(InterceptUtils.commonParamsInterceptor())
                .addInterceptor(InterceptUtils.getHttpLoggingInterceptor(true))//添加日志拦截器
                .addInterceptor(InterceptUtils.getCacheInterceptor())
                .cache(cache)
                .retryOnConnectionFailure(true)
                .build();

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build();
    }

    /**
     * 根据各模块业务接口 获取不同的retrofit service接口对象
     */
    public <T> T getRetrofitService(Class<T> cls) {

        return createRetrofit(BaseApi.getBaseUrl()).create(cls);
    }
}
