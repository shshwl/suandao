package com.she.suandao.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * @author Doris.
 * @date 2019/1/2.
 */
public class AppUtils {

    /**
     * 获取版本号
     *
     * @param context Context
     * @return 当前应用的版本名称
     */
    public static String getVersionName(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取友盟渠道名
     *
     * @param context Context
     * @return 如果没有获取成功，那么返回值为空字符串
     */
    public static String getUmengChannel(Context context) {
        return getMateDataValue(context, "UMENG_CHANNEL", "");
    }

    /**
     * 获取友盟AppKey
     * @param context Context
     * @return 如果没有获取成功，那么返回值为空字符串
     */
    public static String getUmengAppKey(Context context){
        return getMateDataValue(context, "UMENG_APPKEY", "");
    }

    /**
     * 获取 AndroidManifest中application节点下meta-data节点的值
     *
     * @param context      Context
     * @param key          指定节点key
     * @param defaultValue 默认值
     * @return key的值
     */
    public static String getMateDataValue(Context context, String key, String defaultValue) {
        if (defaultValue == null){
            defaultValue = "";
        }
        if (context == null){
            return defaultValue;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(),
                        PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        //此处这样写的目的是为了在debug模式下也能获取到渠道号，如果用getString的话只能在Release下获取到。
                        defaultValue = applicationInfo.metaData.get(key) + "";
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

}
