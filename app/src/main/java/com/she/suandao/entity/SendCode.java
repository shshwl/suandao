package com.she.suandao.entity;

import java.io.Serializable;

/**
 * Created by DJM51 on 2017/8/8.
 */

public class SendCode implements Serializable {


    /**
     * Result : true
     * Status : 1
     * Message : 发送成功！
     * InnerData : null
     * ResultCode :
     */

    private boolean Result;
    private int Status;
    private String Message;
    private Object InnerData;
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

    public Object getInnerData() {
        return InnerData;
    }

    public void setInnerData(Object InnerData) {
        this.InnerData = InnerData;
    }

    public String getResultCode() {
        return ResultCode;
    }

    public void setResultCode(String ResultCode) {
        this.ResultCode = ResultCode;
    }
}
