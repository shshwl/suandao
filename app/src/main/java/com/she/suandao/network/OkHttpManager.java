package com.she.suandao.network;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.webkit.WebSettings;

import com.google.gson.Gson;
import com.mrj.framworklib.config.ApiConfig;
import com.mrj.framworklib.utils.OkHttpCallBack;
import com.she.suandao.R;
import com.she.suandao.activity.LoginActivity;
import com.she.suandao.config.StringConfig;
import com.she.suandao.utils.AppUtils;
import com.she.suandao.utils.MD5Utils;
import com.she.suandao.utils.PhoneUtils;
import com.she.suandao.utils.SharedPrefsUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 网络管理类
 *
 * @author 49829
 * @date 2018/6/27
 */

public class OkHttpManager {

    /**
     * mediaType 这个需要和服务端保持一致
     */
    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");

    /**
     * 图片上传使用
     */
    private static final MediaType MEDIA_TYPE_FILE = MediaType.parse("image/jpg");
    /**
     * 单利引用
     */
    private static volatile OkHttpManager mInstance;
    /**
     * okHttpClient 实例
     */
    private OkHttpClient mOkHttpClient;
    /**
     * 全局处理子线程和M主线程通信
     */
    private Handler okHttpHandler;


    private Context mContext;

    /**
     * 初始化OkHttpManager
     */

    public OkHttpManager(Context context) {
        //初始化OkHttpClient
        mContext = context;
        mOkHttpClient = new OkHttpClient().newBuilder()
                //设置超时时间
                .connectTimeout(10, TimeUnit.SECONDS)
                //设置读取超时时间
                .readTimeout(10, TimeUnit.SECONDS)
                //设置写入超时时间
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();
        //初始化Handler
        okHttpHandler = new Handler(context.getMainLooper());
    }

    /**
     * 获取单例引用
     *
     * @return 实例
     */

    public static OkHttpManager getInstance(Context context) {
        OkHttpManager inst = mInstance;
        if (inst == null) {
            synchronized (OkHttpManager.class) {
                inst = mInstance;
                if (inst == null) {
                    inst = new OkHttpManager(context.getApplicationContext());
                    mInstance = inst;
                }
            }
        }
        return inst;
    }

    /**
     * 2.1 okHttp get异步请求
     *
     * @param actionUrl 接口地址
     * @param paramsMap 请求参数
     * @param callBack  请求返回数据回调
     * @return
     */
    public void getByAsyn(final String actionUrl, Map<String, Object> paramsMap, final OkHttpCallBack callBack) {
        StringBuilder tempParams = new StringBuilder();
        paramsMap.put("Sign", getSign(paramsMap));
        try {
            int pos = 0;
            for (String key : paramsMap.keySet()) {
                if (pos > 0) {
                    tempParams.append("&");
                }
                tempParams.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap.get(key).toString(), "utf-8")));
                pos++;
            }
            final String requestUrl = String.format("%s%s?%s", ApiConfig.BASE_URL, actionUrl, tempParams.toString());
            final Request request = addHeaders().url(requestUrl).build();
            final Call call = mOkHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    onFailedCallBack(e, callBack);

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        String responseStr = response.body().string();
                        if (response.code() != 200) {
                            return;
                        }
//                        ResponseMessage responseMessage = new Gson().fromJson(responseStr, ResponseMessage.class);
//                        if (responseMessage.getStatus() == 99) {
//                            EventBus.getDefault().post(new ExpiredEvent());
//                            return;
//                        }
                        onSuccessCallBack(actionUrl, responseStr, callBack);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {

        }
    }


    /**
     * 2.2 okHttp post异步请求
     *
     * @param actionUrl 接口地址
     * @param paramsMap 请求参数
     * @param callBack  请求返回数据回调
     * @return
     */
    public void postByAsyn(final String actionUrl, Map<String, Object> paramsMap, final OkHttpCallBack callBack) {
        try {
            paramsMap.put("Sign", getSign(paramsMap));
            StringBuilder tempParams = new StringBuilder();
            int pos = 0;

            for (String key : paramsMap.keySet()) {
                if (pos > 0) {
                    tempParams.append("&");
                }
                tempParams.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap.get(key).toString(), "utf-8")));
                pos++;
            }

            String params = tempParams.toString();
            RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, params);
            final String requestUrl = String.format("%s%s", ApiConfig.BASE_URL, actionUrl);
            final Request request = addHeaders()
                    .url(requestUrl).post(body).build();
            final Call call = mOkHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    onFailedCallBack(e, callBack);

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        String responseStr = response.body().string();
                        if (response.code() != 200) {
                            return;
                        }
                        ResponseMessage responseMessage = new Gson().fromJson(responseStr, ResponseMessage.class);
                        if (responseMessage.getStatus() == 99) {
                            mContext.startActivity(new Intent(mContext, LoginActivity.class));
                            return;
                        }
                        onSuccessCallBack(actionUrl, responseStr, callBack);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            Log.e("marj", e.toString());
        }
    }

    private static String getSign(Map<String, Object> map) {
        Map<String, Object> sign = new HashMap<>(map.size());
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            sign.put(entry.getKey().toLowerCase(), entry.getValue());
        }
        return sign(sign);
    }

    /**
     * 2.1 okHttp get异步请求
     *
     * @param actionUrl 接口地址
     * @param callBack  请求返回数据回调
     * @return
     */
    public void getHide(final String actionUrl, final OkHttpCallBack callBack) {
        StringBuilder tempParams = new StringBuilder();
        try {
            int pos = 0;
            final String requestUrl = actionUrl;
            final Request request = addHeaders().url(requestUrl).build();
            final Call call = mOkHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    onFailedCallBack(e, callBack);

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        String responseStr = response.body().string();
                        ResponseMessage responseMessage = new Gson().fromJson(responseStr, ResponseMessage.class);
                        onSuccessCallBack(actionUrl, responseStr, callBack);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {

        }
    }

    /**
     * 上传图片 带参数
     *
     * @param actionUrl 请求地址
     * @param paramsMap 参数
     * @param fileMap   图片地址
     * @param callBack  回调
     */
    public void postFiles(final String actionUrl, Map<String, Object> paramsMap, Map<String, File> fileMap, final OkHttpCallBack callBack) {
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        paramsMap.put("Sign", getSign(paramsMap));
        //入参-字符串
        for (Map.Entry entry : paramsMap.entrySet()) {
            requestBody.addFormDataPart(entry.getKey().toString(), entry.getValue().toString());
        }
        //入参-文件
        for (Map.Entry entry : fileMap.entrySet()) {
            File file = (File) entry.getValue();
            RequestBody fileBody = RequestBody.create(MEDIA_TYPE_FILE, file);
            String fileName = file.getName();
            requestBody.addFormDataPart(entry.getKey().toString(), fileName, fileBody);
        }

        Request request = addHeaders().url(actionUrl).post(requestBody.build()).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onFailedCallBack(e, callBack);
            }


            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String responseStr = response.body().string();
                    onSuccessCallBack(actionUrl, responseStr, callBack);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }


    /**
     * 添加消息头
     *
     * @return 返回一个builder
     */
    private Request.Builder addHeaders() {
        Request.Builder builder = null;
        builder = new Request.Builder()
                //addHeader，可添加多个请求头  header，唯一，会覆盖
                .addHeader("Connection", "keep-alive")
                .addHeader("User-Agent", getUserAgent())
                .addHeader("AppTerminalType", "android")
                .addHeader("AppVersion", AppUtils.getVersionName(mContext))
                .addHeader("AppChannel", AppUtils.getUmengChannel(mContext))
//                .addHeader("AppChannel", "huawei")
                .addHeader("AppPackageName", mContext.getPackageName())
                .addHeader("NativeId", SharedPrefsUtil.getValue(StringConfig.USER_INFO, StringConfig.NATIVE_ID, ""))
                .addHeader("UniqueId", PhoneUtils.getIMEI(mContext));
        try {
            builder.addHeader("AppName", URLEncoder.encode(
                    mContext.getResources().getString(R.string.app_name), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return builder;
    }

    private String getUserAgent() {
        try {
            String userAgent;
            try {
                userAgent = WebSettings.getDefaultUserAgent(mContext);
            } catch (Exception e) {
                userAgent = System.getProperty("http.agent");
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0, length = userAgent.length(); i < length; i++) {
                char c = userAgent.charAt(i);
                if (c <= '\u001f' || c >= '\u007f') {
                    sb.append(String.format("\\u%04x", (int) c));
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "android";
    }

    /**
     * 成功的回调
     *
     * @param requestUrl 请求地址
     * @param response   返回消息
     * @param callBack   回调
     */
    private void onSuccessCallBack(final String requestUrl, final String response, final OkHttpCallBack callBack) {
        //因为okhttp3 UI的处理不能在子线程中，要在主线程中，所以要这样写
        okHttpHandler.post(new Runnable() {
            @Override
            public void run() {
                if (null != callBack) {
                    callBack.onSuccess(requestUrl, response);
                }
            }
        });
    }

    /**
     * 成功的回调
     *
     * @param e        错误
     * @param callBack 回调
     */
    private void onFailedCallBack(final IOException e, final OkHttpCallBack callBack) {
        okHttpHandler.post(new Runnable() {
            @Override
            public void run() {
                if (null != callBack) {
                    callBack.onFailed(e);
                }
            }
        });
    }

    private static final String SING_KEY = "ejd92sdx931d";

    /**
     * 签名
     *
     * @param map 签名数据
     * @return 签名好的字符串
     */
    private static String sign(Map<String, Object> map) {
        List<Map.Entry<String, Object>> infoIds =
                new ArrayList<>(map.entrySet());

        Collections.sort(infoIds, new Comparator<Map.Entry<String, Object>>() {
            @Override
            public int compare(Map.Entry<String, Object> stringObjectEntry, Map.Entry<String, Object> t1) {
                return (stringObjectEntry.getKey()).compareTo(t1.getKey());
            }
        });
        String sss = "";
        for (int i = 0; i < infoIds.size(); i++) {
            sss = sss + infoIds.get(i).toString();
        }
        String fin = sss.toLowerCase() + "p=" + SING_KEY;
        MD5Utils.getMd5Value(fin);
        return MD5Utils.getMd5Value(MD5Utils.getMd5Value(fin));
    }
}
