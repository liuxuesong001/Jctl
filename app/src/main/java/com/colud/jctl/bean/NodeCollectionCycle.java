package com.colud.jctl.bean;

import java.util.List;

/**
 * Created by Jcty on 2017/5/10.
 */

public class NodeCollectionCycle extends BaseBean {

    private String flag;
    private String msg;


    /**
     * onWeeks : ["2","3","4","5"]
     * offWeeks : ["3","4"]
     * nodeId : 246C3733
     * cycleTime : 5
     * cycleOff : 13:30:00
     * cycleOn : 35
     */

    private String nodeId;
    private String cycleTime;
    private String cyclOnTime;

    public String getCyclOffTime() {
        return cyclOffTime;
    }

    public void setCyclOffTime(String cyclOffTime) {
        this.cyclOffTime = cyclOffTime;
    }

    private String cyclOffTime;

    public String getCyclOnTime() {
        return cyclOnTime;
    }

    public void setCyclOnTime(String cyclOnTime) {
        this.cyclOnTime = cyclOnTime;
    }

    private String cycleOff;
    private String cycleOn;
    private List<String> onWeeks;
    private List<String> offWeeks;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getCycleTime() {
        return cycleTime;
    }

    public void setCycleTime(String cycleTime) {
        this.cycleTime = cycleTime;
    }

    public String getCycleOff() {
        return cycleOff;
    }

    public void setCycleOff(String cycleOff) {
        this.cycleOff = cycleOff;
    }

    public String getCycleOn() {
        return cycleOn;
    }

    public void setCycleOn(String cycleOn) {
        this.cycleOn = cycleOn;
    }

    public List<String> getOnWeeks() {
        return onWeeks;
    }

    public void setOnWeeks(List<String> onWeeks) {
        this.onWeeks = onWeeks;
    }

    public List<String> getOffWeeks() {
        return offWeeks;
    }

    public void setOffWeeks(List<String> offWeeks) {
        this.offWeeks = offWeeks;
    }
}
