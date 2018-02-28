package com.colud.jctl.bean;

import java.util.List;

/**
 * Created by Jcty on 2017/4/29.
 */

public class CapaCityItem extends BaseBean {
    private String msg;


    /**
     * flag : 1
     * info : [{"nodeNum":"246C8E91","min":5555,"max":656,"property":"soilHumidity1","id":"66","cycle":"856"},{"nodeNum":"246C8E72","min":1,"max":11,"property":"airTemperature","id":"60","cycle":"1"},{"nodeNum":"246C8E72","min":1,"max":111,"property":"soilTemperature3","id":"57","cycle":"22"},{"nodeNum":"246C8E93","min":22,"max":33,"property":"soilHumidity2","id":"51","cycle":"22"}]
     */

    private String flag;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private List<InfoBean> info;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<InfoBean> getInfo() {
        return info;
    }

    public void setInfo(List<InfoBean> info) {
        this.info = info;
    }

    public static class InfoBean extends BaseBean {
        /**
         * nodeNum : 246C8E91
         * min : 5555.0
         * max : 656.0
         * property : soilHumidity1
         * id : 66
         * cycle : 856
         */

        private String nodeNum;
        private double min;
        private double max;
        private String property;
        private String id;
        private String cycle;

        public String getNodeNum() {
            return nodeNum;
        }

        public void setNodeNum(String nodeNum) {
            this.nodeNum = nodeNum;
        }

        public double getMin() {
            return min;
        }

        public void setMin(double min) {
            this.min = min;
        }

        public double getMax() {
            return max;
        }

        public void setMax(double max) {
            this.max = max;
        }

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCycle() {
            return cycle;
        }

        public void setCycle(String cycle) {
            this.cycle = cycle;
        }
    }
}
