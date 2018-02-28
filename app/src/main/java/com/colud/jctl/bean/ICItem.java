package com.colud.jctl.bean;

import java.io.Serializable;

/**
 * Created by Jcty on 2017/429.
 */

public class ICItem implements Serializable {
    private static final long serialVersionUID = 963019279114258630L;
    private String flag;
    private String msg;

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

    public String getNodeNum() {
        return nodeNum;
    }

    public void setNodeNum(String nodeNum) {
        this.nodeNum = nodeNum;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    private String nodeNum;        // 节点编号
    private String property;        // 属性
    private Double max;        // 最大值
    private Double min;        // 最小值
    private String cycle;        // 异常周期
}
