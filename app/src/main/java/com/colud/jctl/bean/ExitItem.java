package com.colud.jctl.bean;

import java.io.Serializable;

/**
 * Created by Jcty on 2017/4/20.
 */

public class ExitItem implements Serializable {
    private static final long serialVersionUID = -7763807391539184027L;

    /**
     * flag : 1
     * msg : 退出成功！
     */

    private int flag;
    private String msg;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
