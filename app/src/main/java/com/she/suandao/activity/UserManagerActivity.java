package com.she.suandao.activity;

import com.she.suandao.R;
import com.she.suandao.base.BaseFragmentActivity;

/**
 * Description :
 *
 * @author : mrj
 * Date : 2019/10/25
 * Time : 11:21
 */
public class UserManagerActivity extends BaseFragmentActivity {
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_user_manager;
    }

    @Override
    protected void initData() {
        setTitle("福主信息");
    }
}
