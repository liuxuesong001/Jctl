package com.colud.jctl.bean;

import java.util.List;

/**
 * Created by Jcty on 2017/4/7.
 */

public class FacilityItem extends BaseBean {

    /**
     * flag : 1
     * info : [{"id":"38","farmerName":"暂未绑定到农场！","nodeNum":0,"powerSupply":"","relayNum":"11"}]
     */

    private int flag;
    private String msg;
    private InfoBean infoBean;

    public InfoBean getInfoBean() {
        return infoBean;
    }

    public void setInfoBean(InfoBean infoBean) {
        this.infoBean = infoBean;
    }

    private List<FacilityItem.InfoBean> list;

    public List<FacilityItem.InfoBean> getList() {
        return list;
    }

    public void setList(List<FacilityItem.InfoBean> list) {
        this.list = list;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private List<InfoBean> info;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
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
         * id : 38
         * farmerName : 暂未绑定到农场！
         * nodeNum : 0
         * powerSupply :
         * relayNum : 11
         */

        private String id;
        private String farmerName;
        private int nodeNum;
        private String powerSupply;
        private String relayNum;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFarmerName() {
            return farmerName;
        }

        public void setFarmerName(String farmerName) {
            this.farmerName = farmerName;
        }

        public int getNodeNum() {
            return nodeNum;
        }

        public void setNodeNum(int nodeNum) {
            this.nodeNum = nodeNum;
        }

        public String getPowerSupply() {
            return powerSupply;
        }

        public void setPowerSupply(String powerSupply) {
            this.powerSupply = powerSupply;
        }

        public String getRelayNum() {
            return relayNum;
        }

        public void setRelayNum(String relayNum) {
            this.relayNum = relayNum;
        }
    }
}
