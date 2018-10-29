package com.bestsoft.common.https;

/**
 * @package: com.bestsoft.common.https
 * @user:xhkj
 * @date:2018/10/29
 * @description: 获取地址
 **/
public class BaseApi {
    public final static String BASE_HOST = "http://tt.api.v1.317youxi.com/";
    public final static String VERSION_HOST = "http://tt.api.v1.317youxi.com/";
    public final static String OTHER_HOST = "http://tt.api.v1.317youxi.com/";

    public enum HostType {
        BASE_TYPE,
        VERSION_TYPE,
        OTHER_TYPE
    }

    private static String sHost;

    public static void host(HostType hostType, boolean isDebug) {
        if (isDebug) {
            switch (hostType) {
                case BASE_TYPE:
                    sHost = BASE_HOST;//测试环境
                    break;
                case VERSION_TYPE:
                    sHost = VERSION_HOST;//
                    break;
                case OTHER_TYPE:
                    sHost = OTHER_HOST;//
                    break;
                default:
                    sHost = BASE_HOST;//测试环境
                    break;
            }
        } else {
            sHost = BASE_HOST;
        }
    }

    public static String getBaseUrl() {
        return sHost == null ? BASE_HOST : sHost;
    }
}
