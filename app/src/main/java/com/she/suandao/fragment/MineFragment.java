package com.she.suandao.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.she.suandao.R;
import com.she.suandao.activity.BlessingActivity;
import com.she.suandao.activity.SettingActivity;
import com.she.suandao.activity.UserManagerActivity;
import com.she.suandao.base.BaseFragment;
import com.she.suandao.config.StringConfig;
import com.she.suandao.customview.CircleImageView;
import com.she.suandao.customview.FontTextView;
import com.she.suandao.utils.SharedPrefsUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Description :
 *
 * @author : mrj
 * Date : 2019/10/23
 * Time : 11:39
 */
public class MineFragment extends BaseFragment {
    @BindView(R.id.iv_avatar)
    CircleImageView ivAvatar;
    @BindView(R.id.tv_name)
    FontTextView tvName;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View view) {
        tvName.setText(SharedPrefsUtil.getValue(StringConfig.USER_INFO, StringConfig.USER_NAME, ""));
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.iv_avatar
            , R.id.tv_name
            , R.id.ll_gd
            , R.id.ll_fz
            , R.id.ll_dd
            , R.id.ll_lf
            , R.id.ll_zb
            , R.id.ll_fs
            , R.id.ll_new
            , R.id.ll_kf
            , R.id.iv_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_avatar:
                startAct(BlessingActivity.class);
                break;
            case R.id.tv_name:
                startAct(UserManagerActivity.class);
                break;
            case R.id.ll_gd:
                break;
            case R.id.ll_fz:
                break;
            case R.id.ll_dd:
                break;
            case R.id.ll_lf:
                break;
            case R.id.ll_zb:
                break;
            case R.id.ll_fs:
                break;
            case R.id.ll_new:
                break;
            case R.id.ll_kf:
                break;
            case R.id.iv_setting:
                startAct(SettingActivity.class);
                break;
            default:
                break;
        }
    }
}
