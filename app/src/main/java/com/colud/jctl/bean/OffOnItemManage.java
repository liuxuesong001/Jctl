package com.colud.jctl.bean;

import java.io.Serializable;

/**
 * Created by Jcty on 2017/4/28.
 */

public class OffOnItemManage implements Serializable {
    private static final long serialVersionUID = 5751977464914772252L;

    private String msg;
    private String flag;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
