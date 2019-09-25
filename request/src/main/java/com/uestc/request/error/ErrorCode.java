package com.uestc.request.error;

/**
 * Created by PengFeifei on 17-4-21.
 */

public @interface ErrorCode {

    int LOGIN_ERR = 1;
    int RESPONSE_NULL_ERR = 2;
    int TIMEOUT_ERR = 3;
    int OTHER_ERR = 4;
}