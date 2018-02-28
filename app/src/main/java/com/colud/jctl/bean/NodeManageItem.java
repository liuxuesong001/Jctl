package com.colud.jctl.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jcty on 2017/4/7.
 */

public class NodeManageItem implements Serializable {

    private static final long serialVersionUID = 4892023046333126577L;


    /**
     * flag : 1
     * info : [{"farmlandName":"河北廊坊一号鹏","id":"34","usedName":"","nodeNum":"246C8E72","power":""},{"farmlandName":"一号棚","id":"35","usedName":"rose","nodeNum":"246C8E93","power":""},{"farmlandName":"一号棚","id":"36","usedName":"rose","nodeNum":"246C8E99","power":""},{"farmlandName":"","id":"39","usedName":"","nodeNum":"246C8E98","power":""},{"farmlandName":"","id":"40","usedName":"","nodeNum":"246C8E91","power":""},{"farmlandName":"","id":"41","usedName":"","nodeNum":"0C0593C5","power":""}]
     */

    private int flag;
    private String msg;

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

    public static class InfoBean implements Serializable {
        private static final long serialVersionUID = -1127828125236041229L;
        /**
         * farmlandName : 河北廊坊一号鹏
         * id : 34
         * usedName :
         * nodeNum : 246C8E72
         * power :
         */

        private String farmlandName;
        private String id;
        private String usedName;
        private String nodeNum;
        private String power;
        private String onOffName;
        private String nodeAlise;

        public String getOnOffName() {
            return onOffName;
        }

        public void setOnOffName(String onOffName) {
            this.onOffName = onOffName;
        }

        public String getNodeAlise() {
            return nodeAlise;
        }

        public void setNodeAlise(String nodeAlise) {
            this.nodeAlise = nodeAlise;
        }

        public String getFarmlandName() {
            return farmlandName;
        }

        public void setFarmlandName(String farmlandName) {
            this.farmlandName = farmlandName;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsedName() {
            return usedName;
        }

        public void setUsedName(String usedName) {
            this.usedName = usedName;
        }

        public String getNodeNum() {
            return nodeNum;
        }

        public void setNodeNum(String nodeNum) {
            this.nodeNum = nodeNum;
        }

        public String getPower() {
            return power;
        }

        public void setPower(String power) {
            this.power = power;
        }
    }
}
