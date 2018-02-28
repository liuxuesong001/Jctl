package com.colud.jctl.bean;

import java.util.List;

/**
 * Created by Jcty on 2017/4/1.
 */

public class FieldManageItem extends BaseBean {


    /**
     * flag : 1
     * info : [{"area":500,"plantVaritety":"水稻","nodeNumber":"","alias":"河北廊坊一号鹏","id":"13"},{"area":100,"plantVaritety":"大豆","nodeNumber":"","alias":"河北廊坊二号田","id":"14"},{"area":50,"plantVaritety":"番茄2号","nodeNumber":"","alias":"番茄二号试验田","id":"16"},{"area":100,"plantVaritety":"番茄3号","nodeNumber":"","alias":"番茄3号试验田","id":"17"},{"area":50,"plantVaritety":"番茄4号","nodeNumber":"","alias":"番茄4号试验田","id":"18"},{"area":20,"plantVaritety":"油菜种","nodeNumber":"","alias":"油菜种子留地","id":"19"},{"area":100,"plantVaritety":"油菜花","nodeNumber":"","alias":"油菜花观赏基地","id":"20"}]
     */

    private String flag;
    private String msg;
    private String farmerId;
    private InfoBean infoBean;

    public InfoBean getInfoBean() {
        return infoBean;
    }

    public void setInfoBean(InfoBean infoBean) {
        this.infoBean = infoBean;
    }

    public String getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(String farmerId) {
        this.farmerId = farmerId;
    }

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
         * area : 500.0
         * plantVaritety : 水稻
         * nodeNumber :
         * alias : 河北廊坊一号鹏
         * id : 13
         */

        private double area;
        private String plantVaritety;
        private String nodeNumber;
        private String alias;
        private String id;

        public double getArea() {
            return area;
        }

        public void setArea(double area) {
            this.area = area;
        }

        public String getPlantVaritety() {
            return plantVaritety;
        }

        public void setPlantVaritety(String plantVaritety) {
            this.plantVaritety = plantVaritety;
        }

        public String getNodeNumber() {
            return nodeNumber;
        }

        public void setNodeNumber(String nodeNumber) {
            this.nodeNumber = nodeNumber;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
