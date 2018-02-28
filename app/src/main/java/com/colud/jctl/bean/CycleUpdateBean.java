package com.colud.jctl.bean;

/**
 * Created by Jcty on 2017/3/22.
 */

public class CycleUpdateBean extends BaseBean {


    /**
     * flag : 1
     * cycleTime : 10
     */

    private String flag;
    private String cycleTime;
    private String msg;

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

    public String getCycleTime() {
        return cycleTime;
    }

    public void setCycleTime(String cycleTime) {
        this.cycleTime = cycleTime;
    }
}
