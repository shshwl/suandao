package com.she.suandao.activity;

import com.she.suandao.R;
import com.she.suandao.base.BaseFragmentActivity;

/**
 * Description :
 *
 * @author : mrj
 * Date : 2019/10/25
 * Time : 11:32
 */
public class SettingActivity extends BaseFragmentActivity {
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initData() {
        setTitle("设置");
    }
}
