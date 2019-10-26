package com.mrj.framworklib.config;

/**
 * Description:
 *
 * @author: mrj
 * Date: 2019/10/17
 * Time: 10:50
 */
public class ApiConfig {
    public static final String BASE_URL = "http://suandao-api-test.she-tech.cn";
    /**
     * 发送登录验证码
     */
    public static final String SEND_LOGIN_CODE = "/1.0/account/SendLoginVCode";
    /**验证码登录
     *
     */
    public static final String NEW_LOGIN = "/1.0/account/LoginByVCode";
    /**
     * 上传头像
     */
    public static final String UPLOADE_IMAGE = "/1.0/account/UploadAvatar";

    /**
     * 获取神像列表
     */
    public static final String GET_BUDDHA = "/1.0/qifu/shenxiangs";

    /**
     * 获取贡品列表
     */
    public static final String GET_TRIBUTE = "/1.0/qifu/gongpins";
}
