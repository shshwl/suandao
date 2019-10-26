package com.she.suandao.presenter;

import android.app.Activity;

import com.mrj.framworklib.config.ApiConfig;
import com.she.suandao.MainActivity;
import com.she.suandao.base.BaseFragmentActivity;
import com.she.suandao.model.IBlessing;
import com.she.suandao.network.OkHttpManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Description :
 *
 * @author : mrj
 * Date : 2019/10/25
 * Time : 13:40
 */
public class BlessingPresenter implements IBlessing {

    private MainActivity context;

    public BlessingPresenter(MainActivity context) {
        this.context = context;
    }

    @Override
    public void getBuddhas() {
        Map<String, Object> params = new HashMap<>(1);
        OkHttpManager.getInstance(context).getByAsyn(ApiConfig.GET_BUDDHA, params, context);
    }

    @Override
    public void getTributes() {
        Map<String, Object> params = new HashMap<>(1);
        OkHttpManager.getInstance(context).getByAsyn(ApiConfig.GET_TRIBUTE, params, context);
    }
}
