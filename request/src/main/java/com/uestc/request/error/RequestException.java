package com.uestc.request.error;

import com.google.gson.Gson;

import okhttp3.HttpUrl;

/**
 * Created by PengFeifei on 17-4-21.
 * errMsg和errCode通过Exception的方式传递到上层
 */

public class RequestException extends RuntimeException {

    private HttpUrl url;
    private int code;
    private String errMsg;

    public RequestException(HttpUrl url, int code, Throwable cause) {
        super(cause);
        this.url = url;
        this.code = code;
        this.errMsg = cause.getMessage();
    }


    public RequestException(HttpUrl url, int code, String errMsg) {
        super(new Throwable(errMsg));
        this.url = url;
        this.code = code;
        this.errMsg = errMsg;
    }

    public String getUrl() {
        return url.toString();
    }

    public int getCode() {
        return code;
    }

    public String getErrMsg() {
        return errMsg;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }


}