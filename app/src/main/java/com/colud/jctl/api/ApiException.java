package com.colud.jctl.api;

import com.colud.jctl.counst.Constants;

/**
 * Created by Jcty on 2017/5/9.
 */

public class ApiException extends RuntimeException {
    private int mErrorCode;

    public ApiException(int errorCode, String errorMessage) {
        super(errorMessage);
        mErrorCode = errorCode;
    }

    /**
     * 判断是否是token失效
     *
     * @return 失效返回true, 否则返回false;
     */
    public boolean isTokenExpried() {
        return mErrorCode == Constants.TOKEN_EXPRIED;
    }
}
