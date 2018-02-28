package com.colud.jctl.bean;

import java.util.List;

/**
 * Created by Jcty on 2017/4/25.
 */

public class FieldDetailItem extends BaseBean {


    private String msg;
    private InfoBean infoBean;

    public InfoBean getInfoBean() {
        return infoBean;
    }

    public void setInfoBean(InfoBean infoBean) {
        this.infoBean = infoBean;
    }

    /**
     * flag : 1
     * farmerList : [{"name":"鹤泉村试点","id":"87"}]
     * userList : []
     * info : [{"area":0.26,"plantVaritety":"川芎","relay":{"id":"43","isNewRecord":false,"relayNum":"356330303034"},"nodeNumber":"","assigneTime":"2017-04-26 00:00:00","usedId":"","farmlandNum":"2","alias":"鹤泉村二号试点大棚","id":"45","addr":"四川省彭州市敖平镇鹤泉村","landType":"0","usedName":"","user":{"id":"6e66721c16024e40bc39b54f1f7f7b20","isNewRecord":false,"name":"陈智刚","loginFlag":"1","roleNames":"","admin":false},"remarks":""}]
     */

    private String flag;
    private List<FarmerListBean> farmerList;
    private List<?> userList;
    private List<InfoBean> info;//这几个东西他的里边没有啊。你写这里边什么意思？

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<FarmerListBean> getFarmerList() {
        return farmerList;
    }

    public void setFarmerList(List<FarmerListBean> farmerList) {
        this.farmerList = farmerList;
    }

    public List<?> getUserList() {
        return userList;
    }

    public void setUserList(List<?> userList) {
        this.userList = userList;
    }

    public List<InfoBean> getInfo() {
        return info;
    }

    public void setInfo(List<InfoBean> info) {
        this.info = info;
    }

    public static class FarmerListBean extends BaseBean {
        /**
         * name : 鹤泉村试点
         * id : 87
         */

        private String name;
        private String id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class InfoBean extends BaseBean {
        /**
         * area : 0.26
         * plantVaritety : 川芎
         * relay : {"id":"43","isNewRecord":false,"relayNum":"356330303034"}
         * nodeNumber :
         * assigneTime : 2017-04-26 00:00:00
         * usedId :
         * farmlandNum : 2
         * alias : 鹤泉村二号试点大棚
         * id : 45
         * addr : 四川省彭州市敖平镇鹤泉村
         * landType : 0
         * usedName :
         * user : {"id":"6e66721c16024e40bc39b54f1f7f7b20","isNewRecord":false,"name":"陈智刚","loginFlag":"1","roleNames":"","admin":false}
         * remarks :
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
        private UserBean user;
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

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public static class RelayBean extends BaseBean {
            /**
             * id : 43
             * isNewRecord : false
             * relayNum : 356330303034
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

        public static class UserBean extends BaseBean {
            /**
             * id : 6e66721c16024e40bc39b54f1f7f7b20
             * isNewRecord : false
             * name : 陈智刚
             * loginFlag : 1
             * roleNames :
             * admin : false
             */

            private String id;
            private boolean isNewRecord;
            private String name;
            private String loginFlag;
            private String roleNames;
            private boolean admin;

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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLoginFlag() {
                return loginFlag;
            }

            public void setLoginFlag(String loginFlag) {
                this.loginFlag = loginFlag;
            }

            public String getRoleNames() {
                return roleNames;
            }

            public void setRoleNames(String roleNames) {
                this.roleNames = roleNames;
            }

            public boolean isAdmin() {
                return admin;
            }

            public void setAdmin(boolean admin) {
                this.admin = admin;
            }
        }
    }
}
