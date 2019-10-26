package com.she.suandao.base;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.mrj.framworklib.utils.AppManager;
import com.mrj.framworklib.utils.ButtonUtils;
import com.mrj.framworklib.utils.HideInputUtils;
import com.mrj.framworklib.utils.ScreenUtilBase;
import com.she.suandao.R;
import com.she.suandao.config.DefaultCode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Description:
 * 活动基类
 *
 * @author : mrj
 * Date: 2019/10/17
 * Time: 10:40
 */
public abstract class BaseFragmentActivity extends AppCompatActivity {
    private Unbinder unbinder;
    protected Context mContext;

    //title返回
    @Nullable
    @BindView(R.id.item_title_back_layout)
    LinearLayout backLayout;
    //title标题
    @Nullable
    @BindView(R.id.item_title_name)
    TextView titleName;


    /**
     * 所有子类必须实现，绑定Act视图
     *
     * @return 布局文件
     */
    protected abstract int getLayoutResId();

    /**
     * 所有子类必须实现，里面做数据方面等操作。
     */
    protected abstract void initData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        ImmersionBar.with(this).statusBarDarkFont(true).init();
        AppManager.getAppManager().addActivity(this);
//        EventBus.getDefault().register(this);
        initBackClick();
    }


    /**
     * 返回按钮监听
     */
    public void initBackClick() {
        if (backLayout != null) {
            backLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    InputMethodManager inputmanger = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    inputmanger.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    AppManager.getAppManager().finishActivity(BaseFragmentActivity.this);
                    finish();
                }
            });
        }
    }
    /**
     * 设置标题
     *
     * @param name
     */
    public void setTitle(String name) {
        if (titleName != null) {
            titleName.setText(name);
        }
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ScreenUtilBase.setStatusBarColor(this, DefaultCode.G_STATUS_BAR_COLOR);
        unbinder = ButterKnife.bind(this);
        mContext = this;
        initData();
    }

    /**
     * @param clz
     * @author luck
     */
    protected void startAct(Class clz) {
        if (ButtonUtils.isFastDoubleClick()) {
            return;
        }
        Intent intent = new Intent(this, clz);
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
        if (ButtonUtils.isFastDoubleClick()) {
            return;
        }
        Intent intent = new Intent(this, clz);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    /**
     * 检测网络是否可用
     *
     * @return true 可用 false 不可用
     */
    public boolean isNetworkConnected() {
        try {
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo ni = cm.getActiveNetworkInfo();
            return ni != null && ni.isConnectedOrConnecting();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 隐藏软键盘
     *
     * @param ev 点击事件
     * @return 是否操作
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            // get current focus,Generally it is EditText
            View view = getCurrentFocus();
            if (HideInputUtils.isShouldHideSoftKeyBoard(view, ev)) {
                HideInputUtils.hideSoftKeyBoard(view.getWindowToken(), this);
            }
        }
        return super.dispatchTouchEvent(ev);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
        unbinder.unbind();
//        EventBus.getDefault().unregister(this);
    }
}
