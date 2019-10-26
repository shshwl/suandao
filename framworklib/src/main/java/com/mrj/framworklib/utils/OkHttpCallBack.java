package com.mrj.framworklib.utils;

/**
 * 网络请求回调
 *
 * @author 49829
 * @date 2017/11/27
 */

public interface OkHttpCallBack {
    /**
     * 请求成功
     *
     * @param requestUrl 请求地址
     * @param resultStr  返回的信息
     */
    void onSuccess(String requestUrl, String resultStr);

    /**
     * 请求失败
     *
     * @param e 错误原因
     */
    void onFailed(Exception e);
}
