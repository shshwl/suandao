package com.she.suandao.base;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 基础碎片
 *
 * @author 49829
 * @date 2017/11/23
 */

public abstract class BaseFragment extends Fragment {


    private View contentView;
    private Unbinder unbinder;

    protected String TAG = "marj";

    protected Context mContext;

    /**
     * 所有子类必须实现，绑定Act视图
     *
     * @return 布局文件
     */
    protected abstract int getLayoutResId();

    /**
     * 所有子类必须实现，里面做页面初始化
     *
     * @param view 布局
     */
    protected abstract void initView(View view);

    /**
     * 所有子类必须实现，里面做数据方面等操作。
     */
    protected abstract void initData();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getLayoutResId() != 0) {
            contentView = inflater.inflate(getLayoutResId(), container, false);
            unbinder = ButterKnife.bind(this, contentView);
            mContext = getActivity();
            initView(contentView);
            return contentView;
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }


    /**
     * @param clz
     * @return void 返回类型
     * @Title: startActivity
     * @Description: 进入到下个activity
     * @author luck
     */
    protected void startAct(Class clz) {
        if (isFastDoubleClick()) {
            return;
        }
        Intent intent = new Intent(getActivity(), clz);
        startActivity(intent);
    }

    /**
     * @param clz
     * @return void 返回类型
     * @Title: startActivity
     * @Description: 进入到下个activity
     * @author luck
     */
    protected void startAct(Class clz, Bundle bundle) {
        if (isFastDoubleClick()) {
            return;
        }
        Intent intent = new Intent(getActivity(), clz);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 防止连续点击跳转两个页面
     */
    protected long lastClickTime;

    protected boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 900) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * 检测网络是否可用
     *
     * @return
     */
    public boolean isNetworkConnected() {
        try {
            ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo ni = cm.getActiveNetworkInfo();
            return ni != null && ni.isConnectedOrConnecting();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
