package com.bestsoft.common.https.intercept;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

import com.bestsoft.common.utils.Utils;
import com.blankj.utilcode.utils.LogUtils;
import com.blankj.utilcode.utils.NetworkUtils;
import com.blankj.utilcode.utils.PhoneUtils;

import java.io.IOException;
import java.security.Permission;
import java.util.HashMap;
import java.util.Map;

import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @package: com.bestsoft.common.https.intercept
 * @user:xhkj
 * @date:2018/10/29
 * @description:
 **/
public class InterceptUtils {
    /**
     * 创建日志拦截器
     *
     * @param debug
     * @return
     */
    public static HttpLoggingInterceptor getHttpLoggingInterceptor(boolean debug) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(
                new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        LogUtils.d("result", message);
                    }
                });
        if (debug) {
            // 测试
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            // 打包
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        return loggingInterceptor;
    }

    /**
     * 请求头拦截器
     * 使用addhear() 不会覆盖之前设置的header，若使用header（）则会覆盖之前的header
     *
     * @return
     */
    public static Interceptor getRequestHeader() {
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder builder = originalRequest.newBuilder();
                builder.addHeader("version", "1");
                builder.addHeader("time", System.currentTimeMillis() + "");
                String method = originalRequest.method();
                RequestBody body = originalRequest.body();
                Request.Builder requestBuilder = builder.method(method, body);
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
        return headerInterceptor;
    }

    /**
     * 统一请求拦截器
     * 统一的请求参数
     * 公共参数：可能接口有某些参数是公共的，不可能一个个接口都去加吧
     */
    public static Interceptor commonParamsInterceptor() {
        Interceptor commonParams = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originRequest = chain.request();
                Request request;
                HttpUrl httpUrl = originRequest.url().newBuilder()
                        .addQueryParameter("channel_id", "2")
                        .addQueryParameter("client", "Android")
                        .build();
                request = originRequest.newBuilder()
                        .url(httpUrl)
                        .build();
                return chain.proceed(request);
            }
        };
        return commonParams;
    }

    /**
     * 在无网络的情况下读取缓存，有网络的情况下根据缓存的过期时间重新请求
     *
     * @return
     */
    public static Interceptor getCacheInterceptor() {
        Interceptor commonParams = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetworkUtils.isConnected(Utils.getContext())) {
                    //无网络下强制使用缓存，无论缓存是否过期,此时该请求实际上不会被发送出去。
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (NetworkUtils.isConnected(Utils.getContext())) {
                    //有网络情况下，根据请求接口的设置，配置缓存。
                    // 这样在下次请求时，根据缓存决定是否真正发出请求。
                    String cacheControl = request.cacheControl().toString();
                    //当然如果你想在有网络的情况下都直接走网络，那么只需要
                    //将其超时时间这是为0即可:String cacheControl="Cache-Control:public,max-age=0"
                    int maxAge = 60 * 60;
                    // read from cache for 1 minute
                    return response.newBuilder()
                            .header("Cache-Control", cacheControl)
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma").build();
                } else {
                    //无网络
                    // tolerate 4-weeks stale
                    int maxStale = 60 * 60 * 24 * 28;
                    return response.newBuilder()
                            .header("Cache-Control", "public,only-if-cached,max-stale=360000")
                            .header("Cache-Control", "public,only-if-cached,max-stale=" + maxStale)
                            .removeHeader("Pragma").build();
                }
            }
        };
        return commonParams;
    }

    public static final Map<String, Object> getRequstMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("channel_id", "2");
        map.put("client", "Android");
        map.put("device_id", checkPermission() ? PhoneUtils.getIMEI(Utils.getContext()) : "");
        return map;
    }

    private static boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(Utils.getContext(), Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            return false;
        } else {
            return true;
        }
    }
}
