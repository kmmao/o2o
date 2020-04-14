package com.imooc.o2o.dto;

public class Result<T> {

    //是否成功
    private boolean success;
    //成功是返回的数据
    private T data;
    //错误信息
    private String errorMag;
    //错误的状态码
    private int errorCode;

    private Result() {
    }

    //成功时的构造器
    public Result(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    //错误时的构造器
    public Result(boolean success,int errorCode,String errorMag) {
        this.success = success;
        this.errorCode = errorCode;
        this.errorMag = errorMag;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMag() {
        return errorMag;
    }

    public void setErrorMag(String errorMag) {
        this.errorMag = errorMag;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
