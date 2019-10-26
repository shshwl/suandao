package com.she.suandao.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mrj.framworklib.config.ApiConfig;
import com.mrj.framworklib.utils.OkHttpCallBack;
import com.mrj.framworklib.utils.ToastUtilsBase;
import com.she.suandao.MainActivity;
import com.she.suandao.R;
import com.she.suandao.base.BaseFragmentActivity;
import com.she.suandao.config.StringConfig;
import com.she.suandao.customview.CountDownButton;
import com.she.suandao.entity.Login;
import com.she.suandao.entity.SendCode;
import com.she.suandao.model.ILogin;
import com.she.suandao.presenter.LoginPresenter;
import com.she.suandao.utils.ProgressDialogUtil;
import com.she.suandao.utils.SharedPrefsUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description :
 *
 * @author : mrj
 * Date : 2019/10/23
 * Time : 15:04
 */
public class LoginActivity extends BaseFragmentActivity implements OkHttpCallBack {

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_verify)
    TextView tvVerify;

    private String phoneString;
    private String codeString;

    private CountDownButton helper;

    private ILogin iLogin;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
        iLogin = new LoginPresenter(this);
    }

    @OnClick({R.id.tv_verify, R.id.tv_ok, R.id.iv_we_chat})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_verify:
                if (checkPhone()) {
                    iLogin.sendCode(phoneString);
                    ProgressDialogUtil.showDialog(mContext, "发送中...");
                }
                break;
            case R.id.tv_ok:
                if (checkLogin()) {
                    iLogin.login(phoneString, codeString);
                    ProgressDialogUtil.showDialog(mContext, "正在登录...");
                }
                break;
            case R.id.iv_we_chat:
                break;
            default:
                break;
        }
    }

    @Override
    public void onSuccess(String requestUrl, String resultStr) {
        ProgressDialogUtil.dismissDialog();
        if (ApiConfig.SEND_LOGIN_CODE.equals(requestUrl)) {
            SendCode sendCode = new Gson().fromJson(resultStr, SendCode.class);
            if (sendCode.isResult()) {
                countDownStart();
            }
            ToastUtilsBase.showToastCenter(mContext, sendCode.getMessage());
        } else if (ApiConfig.NEW_LOGIN.equals(requestUrl)) {
            Login model = new Gson().fromJson(resultStr, Login.class);
            if (model.getStatus() == StringConfig.RESULT_OK) {
                SharedPrefsUtil.putValue(StringConfig.USER_INFO, StringConfig.USER_AVATAR, model.getInnerData().getAvatar());
                SharedPrefsUtil.putValue(StringConfig.USER_INFO, StringConfig.USER_MOBILE, model.getInnerData().getMobile());
                SharedPrefsUtil.putValue(StringConfig.USER_INFO, StringConfig.USER_NAME, model.getInnerData().getNickName());
                SharedPrefsUtil.putValue(StringConfig.USER_INFO, StringConfig.NATIVE_ID, model.getInnerData().getNativeId());
                startAct(MainActivity.class);
                finish();
            }
            ToastUtilsBase.showToastCenter(mContext, model.getMessage());
        }
    }

    @Override
    public void onFailed(Exception e) {
        ProgressDialogUtil.dismissDialog();
    }

    /**
     * 判断手机号码是否正确
     *
     * @return 是否
     */
    private boolean checkPhone() {
        phoneString = etPhone.getText().toString().trim();
        if (!phoneString.matches("(13[0-9]|16[0-9]|19[0-9]|15[0-9]|17[0-9]|18[0-9]|14[0-9])[0-9]{8}$")) {
            ToastUtilsBase.showToastCenter(mContext, getString(R.string.login_register_toast_1));
            return false;
        }
        return true;
    }

    /**
     * 验证输入账号密码格式是否正确
     *
     * @return 正确与否
     */
    private boolean checkLogin() {
        phoneString = etPhone.getText().toString().trim();
        codeString = etCode.getText().toString().trim();
        if (!phoneString.matches("(13[0-9]|16[0-9]|19[0-9]|15[0-9]|17[0-9]|18[0-9]|14[0-9])[0-9]{8}$")) {
            ToastUtilsBase.showToastCenter(mContext, getString(R.string.login_register_toast_1));
            return false;
        }
        if (codeString.equals("") || codeString.length() < 4 || codeString.length() > 8) {
            ToastUtilsBase.showToastCenter(mContext, getString(R.string.login_register_toast_2));
            return false;
        }
        return true;
    }

    /**
     * 开始倒计时
     */
    private void countDownStart() {
        helper = new CountDownButton(tvVerify,
                getResources().getString(R.string.login_register_code_get_remain), 60, 1);
        helper.setOnFinishListener(new CountDownButton.OnFinishListener() {
            @Override
            public void finish() {
                if (null != tvVerify) {
                    tvVerify.setText(getResources().getString(R.string.login_register_code_get));
                }
            }
        });
        helper.start();
    }
}
