package com.she.suandao;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mrj.framworklib.config.ApiConfig;
import com.mrj.framworklib.utils.OkHttpCallBack;
import com.mrj.framworklib.utils.ToastUtilsBase;
import com.she.suandao.base.BaseApplication;
import com.she.suandao.base.BaseFragmentActivity;
import com.she.suandao.customview.tabview.MainViewAdapter;
import com.she.suandao.customview.tabview.TabContainerView;
import com.she.suandao.entity.BuddhaList;
import com.she.suandao.entity.TributeList;
import com.she.suandao.entity.greendao.Buddha;
import com.she.suandao.entity.greendao.DaoSession;
import com.she.suandao.entity.greendao.Tribute;
import com.she.suandao.fragment.HomeFragment;
import com.she.suandao.fragment.MineFragment;
import com.she.suandao.model.IBlessing;
import com.she.suandao.network.ResponseMessage;
import com.she.suandao.presenter.BlessingPresenter;

import java.util.List;

import butterknife.BindView;

/**
 * @author mrj
 */
public class MainActivity extends BaseFragmentActivity implements OkHttpCallBack {

    @BindView(R.id.tab_container)
    TabContainerView tabContainer;

    private FragmentManager manager;

    private IBlessing blessingModel;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        manager = getSupportFragmentManager();
        MainViewAdapter mainViewAdapter = new MainViewAdapter(manager,
                new Fragment[]{new HomeFragment(),
                        new HomeFragment(),
                        new HomeFragment(),
                        new HomeFragment(),
                        new MineFragment()});
        tabContainer.setAdapter(mainViewAdapter);
        blessingModel = new BlessingPresenter(this);
        blessingModel.getBuddhas();
        blessingModel.getTributes();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            manager = getSupportFragmentManager();
            manager.popBackStackImmediate(null, 1);
        }
    }

    private long exitTime = 0;

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            ToastUtilsBase.showToastBottom(mContext, "再次按返回键退出");
            exitTime = System.currentTimeMillis();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onSuccess(String requestUrl, final String resultStr) {
        final DaoSession daoSession = BaseApplication.getInstance().getDaoSession();
        if (requestUrl.equals(ApiConfig.GET_BUDDHA)) {
            if ("".equals(resultStr)) {
                return;
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    BuddhaList buddhaList = new Gson().fromJson(resultStr,
                            BuddhaList.class);
                    for (int i = 0; i < buddhaList.getInnerData().size(); i++) {
                        BuddhaList.InnerDataEntity inner = buddhaList.getInnerData().get(i);
                        Buddha buddha = new Buddha();
                        buddha.setId((long) (i + 1));
                        buddha.setCategory(inner.getCategory());
                        buddha.setCode(inner.getCode());
                        buddha.setName(inner.getName());
                        buddha.setDescribe(inner.getDescribe());
                        buddha.setType(inner.getType());
                        buddha.setSumDescribe(inner.getSumDescribe());
                        buddha.setUrl(inner.getUrl());
                        daoSession.insertOrReplace(buddha);
                    }
                }
            });
        } else if (requestUrl.equals(ApiConfig.GET_TRIBUTE)) {
            if ("".equals(resultStr)) {
                return;
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    TributeList tributeList = new Gson().fromJson(resultStr,
                            TributeList.class);
                    for (int i = 0; i < tributeList.getInnerData().size(); i++) {
                        TributeList.InnerDataEntity inner = tributeList.getInnerData().get(i);
                        Tribute tribute = new Tribute();
                        tribute.setId((long) (i + 1));
                        tribute.setCategory(inner.getCategory());
                        tribute.setCode(inner.getCode());
                        tribute.setName(inner.getName());
                        tribute.setDescribe(inner.getDescribe());
                        tribute.setPrice(inner.getPrice());
                        tribute.setQuantity(inner.getQuantity());
                        tribute.setSort(inner.getSort());
                        tribute.setUrl(inner.getUrl());
                        daoSession.insertOrReplace(tribute);
                    }
                }
            });

        }
    }

    @Override
    public void onFailed(Exception e) {

    }
}
