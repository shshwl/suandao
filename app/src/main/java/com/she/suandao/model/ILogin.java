package com.she.suandao.model;

/**
 * Description :
 *
 * @author : mrj
 * Date : 2019/10/23
 * Time : 11:01
 */
public interface ILogin {
    /**
     * 发送验证码短信
     *
     * @param phone 手机号
     */
    void sendCode(String phone);

    /**
     * 登录
     *
     * @param phone 手机号
     * @param code  验证码
     */
    void login(String phone, String code);

}
