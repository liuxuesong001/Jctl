package com.colud.jctl.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Jcty on 2017/4/19.
 */

public class FarmDetailItem extends BaseBean {
    private static final long serialVersionUID = 8341493711136853093L;


    /**
     * flag : 1
     * data : [{"area":500,"plantVaritety":"水稻","relay":{"id":"23","isNewRecord":false,"relayNum":"323430303034"},"nodeNumber":"","assigneTime":"","usedId":"cbaf1cd8e5374bcdbdcc07adb0265edc","farmlandNum":"hc001","alias":"河北廊坊一号鹏","id":"13","addr":"河北廊坊农场","landType":"0","usedName":"","user":"","remarks":"对方是否"},{"area":100,"plantVaritety":"大豆","relay":{"id":"1","isNewRecord":false},"nodeNumber":"","assigneTime":"","usedId":"","farmlandNum":"LF002","alias":"河北廊坊二号田","id":"14","addr":"河北廊坊农场","landType":"1","usedName":"","user":"","remarks":"戊二醛无若"}]
     * info : [{"area":500,"plantVariety":"水稻","farmerNum":"nc001","user.id":"1","name":"河北廊坊农场","user.name":"系统管理员","id":"13","relayNumber":"","addr":"河北廊坊","farmlandNumber":"","remarks":"发生的发生"}]
     */

    private String flag;
    private String msg;
    private InfoBean infoBean;

    public InfoBean getInfoBean() {
        return infoBean;
    }

    public void setInfoBean(InfoBean infoBean) {
        this.infoBean = infoBean;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private List<DataBean> data;
    private List<InfoBean> info;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public List<InfoBean> getInfo() {
        return info;
    }

    public void setInfo(List<InfoBean> info) {
        this.info = info;
    }

    public static class DataBean extends BaseBean {
        private static final long serialVersionUID = 7386676779032595655L;
        /**
         * area : 500.0
         * plantVaritety : 水稻
         * relay : {"id":"23","isNewRecord":false,"relayNum":"323430303034"}
         * nodeNumber :
         * assigneTime :
         * usedId : cbaf1cd8e5374bcdbdcc07adb0265edc
         * farmlandNum : hc001
         * alias : 河北廊坊一号鹏
         * id : 13
         * addr : 河北廊坊农场
         * landType : 0
         * usedName :
         * user :
         * remarks : 对方是否
         */

        private double area;
        private String plantVaritety;
        private RelayBean relay;
        private String nodeNumber;
        private String assigneTime;
        private String usedId;
        private String farmlandNum;
        private String alias;
        private String id;
        private String addr;
        private String landType;
        private String usedName;
        private String user;
        private String remarks;

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

        public RelayBean getRelay() {
            return relay;
        }

        public void setRelay(RelayBean relay) {
            this.relay = relay;
        }

        public String getNodeNumber() {
            return nodeNumber;
        }

        public void setNodeNumber(String nodeNumber) {
            this.nodeNumber = nodeNumber;
        }

        public String getAssigneTime() {
            return assigneTime;
        }

        public void setAssigneTime(String assigneTime) {
            this.assigneTime = assigneTime;
        }

        public String getUsedId() {
            return usedId;
        }

        public void setUsedId(String usedId) {
            this.usedId = usedId;
        }

        public String getFarmlandNum() {
            return farmlandNum;
        }

        public void setFarmlandNum(String farmlandNum) {
            this.farmlandNum = farmlandNum;
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

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getLandType() {
            return landType;
        }

        public void setLandType(String landType) {
            this.landType = landType;
        }

        public String getUsedName() {
            return usedName;
        }

        public void setUsedName(String usedName) {
            this.usedName = usedName;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public static class RelayBean extends BaseBean {
            private static final long serialVersionUID = -3690774175963405004L;
            /**
             * id : 23
             * isNewRecord : false
             * relayNum : 323430303034
             */

            private String id;
            private boolean isNewRecord;
            private String relayNum;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public boolean isIsNewRecord() {
                return isNewRecord;
            }

            public void setIsNewRecord(boolean isNewRecord) {
                this.isNewRecord = isNewRecord;
            }

            public String getRelayNum() {
                return relayNum;
            }

            public void setRelayNum(String relayNum) {
                this.relayNum = relayNum;
            }
        }
    }

    public static class InfoBean extends BaseBean {
        private static final long serialVersionUID = 8016304957701506787L;
        /**
         * area : 500.0
         * plantVariety : 水稻
         * farmerNum : nc001
         * user.id : 1
         * name : 河北廊坊农场
         * user.name : 系统管理员
         * id : 13
         * relayNumber :
         * addr : 河北廊坊
         * farmlandNumber :
         * remarks : 发生的发生
         */

        private double area;
        private String userId;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        private String plantVariety;
        private String farmerNum;
        @SerializedName("user.id")
        private String _$UserId47; // FIXME check this code
        private String name;
        @SerializedName("user.name")
        private String _$UserName201; // FIXME check this code
        private String id;
        private String relayNumber;
        private String addr;
        private String farmlandNumber;
        private String remarks;

        public double getArea() {
            return area;
        }

        public void setArea(double area) {
            this.area = area;
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

        public String get_$UserId47() {
            return _$UserId47;
        }

        public void set_$UserId47(String _$UserId47) {
            this._$UserId47 = _$UserId47;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String get_$UserName201() {
            return _$UserName201;
        }

        public void set_$UserName201(String _$UserName201) {
            this._$UserName201 = _$UserName201;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRelayNumber() {
            return relayNumber;
        }

        public void setRelayNumber(String relayNumber) {
            this.relayNumber = relayNumber;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getFarmlandNumber() {
            return farmlandNumber;
        }

        public void setFarmlandNumber(String farmlandNumber) {
            this.farmlandNumber = farmlandNumber;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }
    }
}
