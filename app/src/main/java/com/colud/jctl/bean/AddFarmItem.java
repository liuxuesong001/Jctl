package com.colud.jctl.bean;

/**
 * Created by Jcty on 2017/4/18.
 */

public class AddFarmItem extends BaseBean {


    private UserBean user;

    public UserBean getUserBean() {
        return user;
    }

    public void setUserBean(UserBean userBean) {
        this.user = userBean;
    }

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


    public static class UserBean extends BaseBean {


        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        private String farmerNum;        // 农场编号
        private String name;        // 农场名称
        private String area;         //农场面积
        private String plantVariety;//种植植物种类名称
        private String addr;        // 农场地址
        private String remarks; //简介

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getPlantVariety() {
            return plantVariety;
        }

        public void setPlantVariety(String plantVariety) {
            this.plantVariety = plantVariety;
        }


        public String getFarmerNum() {
            return farmerNum;
        }

        public void setFarmerNum(String farmerNum) {
            this.farmerNum = farmerNum;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }
    }
}
