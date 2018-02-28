package com.colud.jctl.bean;

import java.util.List;

/**
 * Created by Jcty on 2017/4/22.
 */

public class RelayDetailItem extends BaseBean {


    /**
     * flag : 1
     * data : {"id":"23","isNewRecord":false,"addTime":"2017-03-10 15:14:40",
     * "relayNum":"323430303034","bindingTime":"2017-03-10 15:14:40","log":"112.00","lat":"24.66","area":"lkdsajlkfa","user":{"id":"cbaf1cd8e5374bcdbdcc07adb0265edc","isNewRecord":false,"loginFlag":"1","admin":false,"roleNames":""},"used":{"id":"-1","isNewRecord":false,"loginFlag":"1","admin":false,"roleNames":""},"powerSupply":"91%","farmer":{"id":"2","isNewRecord":false,"name":"云南农场园"},"nodeNum":2}
     */

    private int flag;
    private DataBean data;
    private List<String> nList;

    public List<String> getnList() {
        return nList;
    }

    public void setnList(List<String> nList) {
        this.nList = nList;
    }

    private List<String> idList;

    public List<String> getIdList() {
        return idList;
    }

    public void setIdList(List<String> idList) {
        this.idList = idList;
    }

    public List<InfoBean> getInfo() {
        return info;
    }

    public void setInfo(List<InfoBean> info) {
        this.info = info;
    }

    private List<InfoBean> info;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private List<DataBean> list;

    public List<DataBean> getList() {
        return list;
    }

    public void setList(List<DataBean> list) {
        this.list = list;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean extends BaseBean {

        /**
         * id : 23
         * isNewRecord : false
         * addTime : 2017-03-10 15:14:40
         * relayNum : 323430303034
         * bindingTime : 2017-03-10 15:14:40
         * log : 112.00
         * lat : 24.66
         * area : lkdsajlkfa
         * user : {"id":"cbaf1cd8e5374bcdbdcc07adb0265edc","isNewRecord":false,"loginFlag":"1","admin":false,"roleNames":""}
         * used : {"id":"-1","isNewRecord":false,"loginFlag":"1","admin":false,"roleNames":""}
         * powerSupply : 91%
         * farmer : {"id":"2","isNewRecord":false,"name":"云南农场园"}
         * nodeNum : 2
         */

        private String id;
        private boolean isNewRecord;
        private String addTime;
        private String relayNum;
        private String bindingTime;
        private String log;
        private String lat;
        private String area;
        private UserBean user;
        private UsedBean used;
        private String powerSupply;
        private FarmerBean farmer;
        private String nodeNum;

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

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public String getRelayNum() {
            return relayNum;
        }

        public void setRelayNum(String relayNum) {
            this.relayNum = relayNum;
        }

        public String getBindingTime() {
            return bindingTime;
        }

        public void setBindingTime(String bindingTime) {
            this.bindingTime = bindingTime;
        }

        public String getLog() {
            return log;
        }

        public void setLog(String log) {
            this.log = log;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public UsedBean getUsed() {
            return used;
        }

        public void setUsed(UsedBean used) {
            this.used = used;
        }

        public String getPowerSupply() {
            return powerSupply;
        }

        public void setPowerSupply(String powerSupply) {
            this.powerSupply = powerSupply;
        }

        public FarmerBean getFarmer() {
            return farmer;
        }

        public void setFarmer(FarmerBean farmer) {
            this.farmer = farmer;
        }

        public String getNodeNum() {
            return nodeNum;
        }

        public void setNodeNum(String nodeNum) {
            this.nodeNum = nodeNum;
        }

        public static class UserBean extends BaseBean {
            /**
             * id : cbaf1cd8e5374bcdbdcc07adb0265edc
             * isNewRecord : false
             * loginFlag : 1
             * admin : false
             * roleNames :
             */

            private String id;
            private boolean isNewRecord;
            private String loginFlag;
            private boolean admin;
            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            private String roleNames;

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

            public String getLoginFlag() {
                return loginFlag;
            }

            public void setLoginFlag(String loginFlag) {
                this.loginFlag = loginFlag;
            }

            public boolean isAdmin() {
                return admin;
            }

            public void setAdmin(boolean admin) {
                this.admin = admin;
            }

            public String getRoleNames() {
                return roleNames;
            }

            public void setRoleNames(String roleNames) {
                this.roleNames = roleNames;
            }
        }

        public static class UsedBean extends BaseBean {
            /**
             * id : -1
             * isNewRecord : false
             * loginFlag : 1
             * admin : false
             * roleNames :
             */

            private String id;
            private boolean isNewRecord;
            private String loginFlag;
            private boolean admin;
            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            private String roleNames;

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

            public String getLoginFlag() {
                return loginFlag;
            }

            public void setLoginFlag(String loginFlag) {
                this.loginFlag = loginFlag;
            }

            public boolean isAdmin() {
                return admin;
            }

            public void setAdmin(boolean admin) {
                this.admin = admin;
            }

            public String getRoleNames() {
                return roleNames;
            }

            public void setRoleNames(String roleNames) {
                this.roleNames = roleNames;
            }
        }

        public static class FarmerBean extends BaseBean {
            /**
             * id : 2
             * isNewRecord : false
             * name : 云南农场园
             */

            private String id;
            private boolean isNewRecord;
            private String name;

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
        }
    }

    public static class InfoBean extends BaseBean {

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
}
