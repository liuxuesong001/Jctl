package com.colud.jctl.bean;

import java.util.List;

/**
 * Created by Jcty on 2017/5/16.
 */

public class CycleWeekBean extends BaseBean {

    /**
     * cyclOnWeek : ["1","2","3"]
     * flag : 1
     * cyclOnTime : 10:22:00
     */

    private String flag;
    private String cyclOnTime;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private List<String> cyclOnWeek;
    private List<String> cyclOffWeek;

    public List<String> getCyclOffWeek() {
        return cyclOffWeek;
    }

    public void setCyclOffWeek(List<String> cyclOffWeek) {
        this.cyclOffWeek = cyclOffWeek;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCyclOnTime() {
        return cyclOnTime;
    }

    public void setCyclOnTime(String cyclOnTime) {
        this.cyclOnTime = cyclOnTime;
    }

    public List<String> getCyclOnWeek() {
        return cyclOnWeek;
    }

    public void setCyclOnWeek(List<String> cyclOnWeek) {
        this.cyclOnWeek = cyclOnWeek;
    }
}
