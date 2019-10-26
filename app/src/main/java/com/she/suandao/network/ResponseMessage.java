package com.she.suandao.network;

/**
 * 通用返回结果
 *
 * @author mrj
 * @date 2019/10/23
 */

public class ResponseMessage<T> {
    private boolean Result;
    private int Status;
    private String Message;
    private int ResultCode;
    private T InnerData;

    public boolean isResult() {
        return Result;
    }

    public void setResult(boolean result) {
        Result = result;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public T getInnerData() {
        return InnerData;
    }

    public void setInnerData(T innerData) {
        InnerData = innerData;
    }

    public int getResultCode() {
        return ResultCode;
    }

    public void setResultCode(int resultCode) {
        ResultCode = resultCode;
    }
}
