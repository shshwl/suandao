package com.she.suandao.entity;

import java.io.Serializable;

/**
 * Created by DJM51 on 2017/8/8.
 */

public class Login implements Serializable {


    /**
     * Result : true
     * Status : 1
     * Message : 登录成功！
     * InnerData : {"Mobile":"13022173958","SessionId":"83c13344-a87b-4d8d-9bb0-d4cff42c8854","NickName":"buti","Avatar":"http://i2.houputech.com/upload/bbd\\avatar\\999c55d802b2472ebdea654616f2ac3f.png"}
     * ResultCode :
     */

    private boolean Result;
    private int Status;
    private String Message;
    private InnerDataBean InnerData;
    private String ResultCode;

    public boolean isResult() {
        return Result;
    }

    public void setResult(boolean Result) {
        this.Result = Result;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public InnerDataBean getInnerData() {
        return InnerData;
    }

    public void setInnerData(InnerDataBean InnerData) {
        this.InnerData = InnerData;
    }

    public String getResultCode() {
        return ResultCode;
    }

    public void setResultCode(String ResultCode) {
        this.ResultCode = ResultCode;
    }

    public static class InnerDataBean implements Serializable {
        /**
         * Mobile : 13022173958
         * SessionId : 83c13344-a87b-4d8d-9bb0-d4cff42c8854
         * NickName : buti
         * Avatar : http://i2.houputech.com/upload/bbd\avatar\999c55d802b2472ebdea654616f2ac3f.png
         */

        private String Mobile;
        private String NickName;
        private String Avatar;
        private String NativeId;

        public String getMobile() {
            return Mobile;
        }

        public void setMobile(String Mobile) {
            this.Mobile = Mobile;
        }

        public String getNickName() {
            return NickName;
        }

        public void setNickName(String NickName) {
            this.NickName = NickName;
        }

        public String getAvatar() {
            return Avatar;
        }

        public void setAvatar(String Avatar) {
            this.Avatar = Avatar;
        }

        public String getNativeId() {
            return NativeId;
        }

        public void setNativeId(String nativeId) {
            NativeId = nativeId;
        }
    }
}
