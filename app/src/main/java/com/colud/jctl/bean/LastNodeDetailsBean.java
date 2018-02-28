package com.colud.jctl.bean;

import java.util.List;

/**
 * Created by Jcty on 2017/5/19.
 */

public class LastNodeDetailsBean extends BaseBean {

    private int flag;
    private String msg;
    private InfoBean info;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private List<InfoBean> infoBeen;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public List<InfoBean> getInfoBeen() {
        return infoBeen;
    }

    public void setInfoBeen(List<InfoBean> infoBeen) {
        this.infoBeen = infoBeen;
    }

    public static class InfoBean extends BaseBean {
        /**
         * soilTemperature3 : 28.07
         * soilTemperature2 : 32.29
         * soilTemperature1 : 30.82
         * soilHumidity1 : 44.89
         * addTime : 2017-05-09 13:34:06
         * co2 : 377.0
         * nodeMac : 246C8E83
         * airTemperature : 33.06
         * openFlag : 1
         * airHumidity : 43.84
         * soilHumidity3 : 48.19
         * soilHumidity2 : 24.9
         * power : 53
         */

        private double soilTemperature3;
        private double soilTemperature2;
        private double soilTemperature1;
        private double soilHumidity1;
        private String addTime;
        private double co2;
        private String nodeMac;
        private double airTemperature;
        private String openFlag;
        private double airHumidity;
        private double soilHumidity3;
        private double soilHumidity2;
        private int power;
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

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

        public double getCo2() {
            return co2;
        }

        public void setCo2(double co2) {
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
