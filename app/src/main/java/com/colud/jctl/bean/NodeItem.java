package com.colud.jctl.bean;

import java.util.List;

/**
 * Created by Jcty on 2017/4/5.
 */

public class NodeItem extends BaseBean {

    /**
     * flag : 1
     * info : [{"soilTemperature3":-3.55,"soilTemperature2":-3.55,"soilTemperature1":-3.55,"soilHumidity1":-3.55,"addTime":1493260445000,"co2":384,"nodeMac":"246C8E99","airTemperature":25.81,"openFlag":"1","airHumidity":45.7,"soilHumidity3":-3.55,"id":"343","soilHumidity2":-3.55,"power":38},{"soilTemperature3":-3.55,"soilTemperature2":-3.55,"soilTemperature1":-3.55,"soilHumidity1":-3.55,"addTime":1493258149000,"co2":390,"nodeMac":"246C8E99","airTemperature":25.24,"openFlag":"0","airHumidity":47.07,"soilHumidity3":-3.55,"id":"342","soilHumidity2":-3.55,"power":41},{"soilTemperature3":-3.55,"soilTemperature2":-3.55,"soilTemperature1":-3.55,"soilHumidity1":-3.55,"addTime":1493258029000,"co2":391,"nodeMac":"246C8E99","airTemperature":25.2,"openFlag":"0","airHumidity":46.31,"soilHumidity3":-3.55,"id":"341","soilHumidity2":-3.55,"power":41},{"soilTemperature3":-3.55,"soilTemperature2":-3.55,"soilTemperature1":-3.55,"soilHumidity1":-3.55,"addTime":1493257956000,"co2":396,"nodeMac":"246C8E91","airTemperature":25.2,"openFlag":"1","airHumidity":46.09,"soilHumidity3":-3.55,"id":"340","soilHumidity2":-3.55,"power":38}]
     */

    private String flag;
    private String msg;

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
         * soilTemperature3 : -3.55
         * soilTemperature2 : -3.55
         * soilTemperature1 : -3.55
         * soilHumidity1 : -3.55
         * addTime : 1493260445000
         * co2 : 384
         * nodeMac : 246C8E99
         * airTemperature : 25.81
         * openFlag : 1
         * airHumidity : 45.7
         * soilHumidity3 : -3.55
         * id : 343
         * soilHumidity2 : -3.55
         * power : 38
         */

        private double soilTemperature3;
        private double soilTemperature2;
        private double soilTemperature1;
        private double soilHumidity1;
        private String addTime;
        private String nodeName;

        public String getNodeName() {
            return nodeName;
        }

        public void setNodeName(String nodeName) {
            this.nodeName = nodeName;
        }

        private int co2;
        private String nodeMac;
        private double airTemperature;
        private String openFlag;
        private double airHumidity;
        private double soilHumidity3;
        private String id;
        private double soilHumidity2;
        private int power;

        public double getSoilTemperature3() {
            return soilTemperature3;
        }

        public void setSoilTemperature3(double soilTemperature3) {
            this.soilTemperature3 = soilTemperature3;
        }

        public double getSoilTemperature2() {
            return soilTemperature2;
        }

        public void setSoilTemperature2(double soilTemperature2) {
            this.soilTemperature2 = soilTemperature2;
        }

        public double getSoilTemperature1() {
            return soilTemperature1;
        }

        public void setSoilTemperature1(double soilTemperature1) {
            this.soilTemperature1 = soilTemperature1;
        }

        public double getSoilHumidity1() {
            return soilHumidity1;
        }

        public void setSoilHumidity1(double soilHumidity1) {
            this.soilHumidity1 = soilHumidity1;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public int getCo2() {
            return co2;
        }

        public void setCo2(int co2) {
            this.co2 = co2;
        }

        public String getNodeMac() {
            return nodeMac;
        }

        public void setNodeMac(String nodeMac) {
            this.nodeMac = nodeMac;
        }

        public double getAirTemperature() {
            return airTemperature;
        }

        public void setAirTemperature(double airTemperature) {
            this.airTemperature = airTemperature;
        }

        public String getOpenFlag() {
            return openFlag;
        }

        public void setOpenFlag(String openFlag) {
            this.openFlag = openFlag;
        }

        public double getAirHumidity() {
            return airHumidity;
        }

        public void setAirHumidity(double airHumidity) {
            this.airHumidity = airHumidity;
        }

        public double getSoilHumidity3() {
            return soilHumidity3;
        }

        public void setSoilHumidity3(double soilHumidity3) {
            this.soilHumidity3 = soilHumidity3;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public double getSoilHumidity2() {
            return soilHumidity2;
        }

        public void setSoilHumidity2(double soilHumidity2) {
            this.soilHumidity2 = soilHumidity2;
        }

        public int getPower() {
            return power;
        }

        public void setPower(int power) {
            this.power = power;
        }
    }
}
