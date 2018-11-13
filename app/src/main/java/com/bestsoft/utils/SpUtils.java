package com.bestsoft.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.bestsoft.utils.sharedpreference.DefaultRecoveryHandler;
import com.bestsoft.utils.sharedpreference.SecuredPreferenceStore;


/**
 * @package: com.juzhe.www.utils
 * @user:xhkj
 * @date:2018/11/4
 * @description:
 **/
public class SpUtils {
    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param mContext
     * @param key
     * @param object
     */
    public static void setParam(Context mContext, String key, Object object) {
        try {
            SecuredPreferenceStore.init(mContext, new DefaultRecoveryHandler());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String type = object.getClass().getSimpleName();
        SecuredPreferenceStore prefStore = SecuredPreferenceStore.getSharedInstance();
        SecuredPreferenceStore.Editor editor = prefStore.edit();
        if ("String".equals(type)) {
            editor.putString(key, (String) object);
        } else if ("Integer".equals(type)) {
            editor.putInt(key, (Integer) object);
        } else if ("Boolean".equals(type)) {
            editor.putBoolean(key, (Boolean) object);
        } else if ("Float".equals(type)) {
            editor.putFloat(key, (Float) object);
        } else if ("Long".equals(type)) {
            editor.putLong(key, (Long) object);
        }

        editor.commit();
    }


    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param key
     * @param defaultObject
     * @return
     */
    public static Object getParam(Context mContext, String key, Object defaultObject) {
        try {
            SecuredPreferenceStore.init(mContext, new DefaultRecoveryHandler());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String type = defaultObject.getClass().getSimpleName();
        SecuredPreferenceStore prefStore = SecuredPreferenceStore.getSharedInstance();
        if ("String".equals(type)) {
            return prefStore.getString(key, (String) defaultObject);
        } else if ("Integer".equals(type)) {
            return prefStore.getInt(key, (Integer) defaultObject);
        } else if ("Boolean".equals(type)) {
            return prefStore.getBoolean(key, (Boolean) defaultObject);
        } else if ("Float".equals(type)) {
            return prefStore.getFloat(key, (Float) defaultObject);
        } else if ("Long".equals(type)) {
            return prefStore.getLong(key, (Long) defaultObject);
        }
        return null;
    }

    /**
     * 清除所有数据
     *
     * @param context
     */
    public static void clearAll(Context context) {
        try {
            SecuredPreferenceStore.init(context, new DefaultRecoveryHandler());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SecuredPreferenceStore prefStore = SecuredPreferenceStore.getSharedInstance();
        SharedPreferences.Editor editor = prefStore.edit();
        editor.clear().apply();
    }

    /**
     * 清除指定数据
     *
     * @param context
     */
    public static void clear(Context context, String key) {
        SecuredPreferenceStore prefStore = SecuredPreferenceStore.getSharedInstance();
        SharedPreferences.Editor editor = prefStore.edit();
        editor.remove(key);
        editor.apply();
    }

}
