package com.colud.jctl.api;

import com.colud.jctl.counst.Constants;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Jcty on 2017/5/9.
 */

public class HttpStatus {
    @SerializedName("code")
    private int mCode;
    @SerializedName("message")
    private String mMessage;

    public int getCode() {
        return mCode;
    }

    public String getMessage() {
        return mMessage;
    }

    /**
     * API是否请求失败
     *
     * @return 失败返回true, 成功返回false
     */
    public boolean isCodeInvalid() {
        return mCode != Constants.WEB_RESP_CODE_SUCCESS;
    }
}
