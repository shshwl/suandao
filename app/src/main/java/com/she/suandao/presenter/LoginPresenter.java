package com.she.suandao.presenter;

import com.mrj.framworklib.config.ApiConfig;
import com.she.suandao.activity.LoginActivity;
import com.she.suandao.model.ILogin;
import com.she.suandao.network.OkHttpManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Description :
 *
 * @author : mrj
 * Date : 2019/10/23
 * Time : 16:34
 */
public class LoginPresenter implements ILogin {

    private LoginActivity context;

    public LoginPresenter(LoginActivity context) {
        this.context = context;
    }

    @Override
    public void sendCode(String phone) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("Mobile", phone);
        OkHttpManager.getInstance(context).postByAsyn(ApiConfig.SEND_LOGIN_CODE, params, context);
    }

    @Override
    public void login(String phone, String code) {
        Map<String, Object> params = new HashMap<>(4);
        params.put("mobile", phone);
        params.put("vcode", code);
        OkHttpManager.getInstance(context).postByAsyn(ApiConfig.NEW_LOGIN, params, context);
    }
}
