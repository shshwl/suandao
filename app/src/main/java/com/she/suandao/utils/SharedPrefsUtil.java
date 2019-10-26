package com.she.suandao.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.she.suandao.base.BaseApplication;


/**
 * 偏好设置管理工具
 * @author 49829
 */
public class SharedPrefsUtil {

    public final static String SETTING = "SharedPreferences";

    private static final Context context = BaseApplication.getInstance().getApplicationContext();

    /**
     * 使用SharedPreferences保存数据
     * @param spName
     * @param key
     * @param value
     */
    public static void putValue(String spName, String key, int value) {
        Editor sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE).edit();
        sp.putInt(key, value);
        sp.apply();
    }


    /**
     * 使用SharedPreferences保存数据
     * @param spName
     * @param key
     * @param value
     */
    public static void putValue(String spName, String key, Long value) {
        Editor sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE).edit();
        sp.putLong(key, value);

        sp.apply();
    }

    /**
     * 使用SharedPreferences保存数据
     * @param spName
     * @param key
     * @param value
     */
    public static void putValue(String spName, String key, boolean value) {
        Editor sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE).edit();
        sp.putBoolean(key, value);
        sp.apply();
    }

    /**
     * 使用SharedPreferences保存数据
     * @param spName
     * @param key
     * @param value
     */
    public static void putValue(String spName, String key, String value) {
        Editor sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE).edit();
        sp.putString(key, value);
        sp.apply();
    }
    /**
     * 使用SharedPreferences读取数据
     * @param spName
     * @param key
     * @param defValue
     * @return
     */
    public static int getValue(String spName, String key, int defValue) {
        SharedPreferences sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        int value = sp.getInt(key, defValue);
        return value;
    }

    /**
     * 使用SharedPreferences读取数据
     * @param spName
     * @param key
     * @param defValue
     * @return
     */
    public static long getLongValue(String spName, String key, long defValue) {
        SharedPreferences sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        long value = sp.getLong(key, defValue);
        return value;
    }

    /**
     * 使用SharedPreferences读取数据
     * @param spName
     * @param key
     * @param defValue
     * @return
     */
    public static boolean getValue(String spName, String key, boolean defValue) {
        SharedPreferences sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        boolean value = sp.getBoolean(key, defValue);
        return value;
    }

    /**
     * 使用SharedPreferences读取数据
     * @param spName
     * @param key
     * @param defValue
     * @return
     */
    public static String getValue(String spName, String key, String defValue) {
        //实例化SharedPreferences
        SharedPreferences sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        String value = sp.getString(key, defValue);
        return value;
    }

    /**
     * 删除某个数据
     * @param spName
     * @param key
     */
    public static void remove(String spName, String key) {
        SharedPreferences sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.remove(key);
        //提交当前数据
        editor.apply();
    }

    /**
     * 删除所有数据
     * @param spName
     */
    public static void removeAll(String spName) {
        SharedPreferences sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        sp.edit().clear().apply();
    }

    /**
     * 判断某个KEY对应的偏好设置是否存在
     * @param spName
     * @param key
     * @return
     */
    public static boolean existed(String spName, String key) {
        SharedPreferences sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        if ("".equals(sp.getString(key, ""))) {
            return false;
        } else {
            return true;
        }
    }
}

