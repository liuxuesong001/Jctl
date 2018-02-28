package com.colud.jctl.bean;

import java.util.List;

/**
 * Created by Jcty on 2017/4/23.
 */

public class NodeDataManageItem extends BaseBean {


    private String msg;
    private List<String> nList;
    private List<String> idList;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<String> getnList() {
        return nList;
    }

    public void setnList(List<String> nList) {
        this.nList = nList;
    }

    public List<String> getIdList() {
        return idList;
    }

    public void setIdList(List<String> idList) {
        this.idList = idList;
    }

    /**
     * node : {"relayNum":"356330303034","farmlandName":"鹤泉村一号试点大棚","onOffName":"一号大棚灌溉","nodeNum":"246C8E83","addTime":"2017-04-26 16:13:11","nodeAlise":"一号大棚监控节点","id":"46","power":"","type":"","user":{"id":"6e66721c16024e40bc39b54f1f7f7b20","isNewRecord":false,"startNum":0,"name":"陈智刚","loginFlag":"1","admin":false,"roleNames":""},"usedName":"","cycle":""}
     * flag : 1
     * data : {"soilTemperature3":32.94,"soilTemperature2":35.22,"soilTemperature1":34.08,"soilHumidity1":46.36,"addTime":"2017-05-17 16:55:05","co2":367,"nodeMac":"246C8E83","airTemperature":25,"openFlag":"1","airHumidity":55.97,"soilHumidity3":48.3,"soilHumidity2":8.56,"power":1}
     * lands : [{"id":"13","isNewRecord":false,"remarks":"对方是否","startNum":0,"farmlandNum":"hc001","alias":"河北廊坊一号鹏","landType":"0","plantVaritety":"水稻","user":{"id":"1","isNewRecord":false,"startNum":0,"name":"系统管理员","loginFlag":"1","admin":true,"roleNames":""},"usedId":"65e9573a8c0a45b991e2ae16fe4010d4","assigneTime":"2017-04-25 00:00:00","farmer":{"id":"90","isNewRecord":false,"startNum":0},"area":500,"addr":"河北廊坊农场","relay":{"id":"35","isNewRecord":false,"startNum":0,"relayNum":"155462005586"},"nodeNumber":0,"plantNum":0},{"id":"14","isNewRecord":false,"remarks":"戊二醛无若","startNum":0,"farmlandNum":"LF002","alias":"河北廊坊二号田","landType":"1","plantVaritety":"大豆","user":{"id":"1","isNewRecord":false,"startNum":0,"name":"系统管理员","loginFlag":"1","admin":true,"roleNames":""},"usedId":"","farmer":{"id":"90","isNewRecord":false,"startNum":0},"area":100,"addr":"河北廊坊农场","relay":{"id":"23","isNewRecord":false,"startNum":0,"relayNum":"323430303034"},"nodeNumber":0,"plantNum":0},{"id":"20","isNewRecord":false,"remarks":"","startNum":0,"farmlandNum":"JY0023","alias":"油菜花观赏基地","landType":"0","plantVaritety":"油菜花","user":{"id":"1","isNewRecord":false,"startNum":0,"name":"系统管理员","loginFlag":"1","admin":true,"roleNames":""},"usedId":"7e9a6aac6b5447c1b5950205dc2353e7","assigneTime":"2017-04-26 00:00:00","farmer":{"id":"21","isNewRecord":false,"startNum":0},"area":100,"addr":"河北","relay":{"id":"37","isNewRecord":false,"startNum":0,"relayNum":"934483304950349"},"nodeNumber":0,"plantNum":0},{"id":"59","isNewRecord":false,"remarks":"","createDate":"2017-04-28 11:29:32","startNum":0,"farmlandNum":"士大夫","alias":"一号菌棚","landType":"0","plantVaritety":"黄瓜","user":{"id":"1","isNewRecord":false,"startNum":0,"name":"系统管理员","loginFlag":"1","admin":true,"roleNames":""},"area":500,"addr":"北京市朝阳区黑庄户乡大东路1号","nodeNumber":0,"plantNum":0},{"id":"63","isNewRecord":false,"remarks":"333","startNum":0,"landType":"1","user":{"id":"1","isNewRecord":false,"startNum":0,"name":"系统管理员","loginFlag":"1","admin":true,"roleNames":""},"area":333,"addr":"555","nodeNumber":0,"plantNum":0},{"id":"70","isNewRecord":false,"remarks":"","createDate":"2017-05-18 00:00:00","startNum":0,"farmlandNum":"111","alias":"110实验室","landType":"0","plantVaritety":"111","user":{"id":"1","isNewRecord":false,"startNum":0,"name":"系统管理员","loginFlag":"1","admin":true,"roleNames":""},"usedId":"","farmer":{"id":"113","isNewRecord":false,"startNum":0,"name":"大唐实验室"},"area":111,"addr":"北京","relay":{"id":"72","isNewRecord":false,"startNum":0,"relayNum":"366230303034"},"nodeNumber":1,"plantNum":0},{"id":"87","isNewRecord":false,"remarks":"80","startNum":0,"landType":"1","user":{"id":"1","isNewRecord":false,"startNum":0,"name":"系统管理员","loginFlag":"1","admin":true,"roleNames":""},"area":80,"addr":"考虑考虑","nodeNumber":0,"plantNum":0},{"id":"88","isNewRecord":false,"remarks":"230","startNum":0,"landType":"1","user":{"id":"1","isNewRecord":false,"startNum":0,"name":"系统管理员","loginFlag":"1","admin":true,"roleNames":""},"area":230,"addr":"哦嘻嘻嘻","nodeNumber":0,"plantNum":0},{"id":"108","isNewRecord":false,"remarks":"暂无雪松","createDate":"2017-05-24 11:10:54","updateDate":"2017-05-24 11:10:54","startNum":0,"farmlandNum":"001","alias":"雪松农场001","landType":"0","plantVaritety":"水稻","user":{"id":"1","isNewRecord":false,"startNum":0,"name":"系统管理员","loginFlag":"1","admin":true,"roleNames":""},"usedId":"1","usedName":"系统管理员","farmer":{"id":"89","isNewRecord":false,"startNum":0,"name":"菏泽牡丹东关小农场"},"area":10,"addr":"北京朝阳","nodeNumber":0,"plantNum":0},{"id":"109","isNewRecord":false,"remarks":"严重","createDate":"2017-05-24 11:11:49","updateDate":"2017-05-24 11:11:49","startNum":0,"farmlandNum":"002","alias":"雪松农场002","landType":"1","plantVaritety":"麦子","user":{"id":"1","isNewRecord":false,"startNum":0,"name":"系统管理员","loginFlag":"1","admin":true,"roleNames":""},"usedId":"1","usedName":"系统管理员","farmer":{"id":"89","isNewRecord":false,"startNum":0,"name":"菏泽牡丹东关小农场"},"area":306,"addr":"北京丰台","nodeNumber":0,"plantNum":0},{"id":"110","isNewRecord":false,"remarks":"一下午","createDate":"2017-05-24 11:13:05","updateDate":"2017-05-24 11:13:05","startNum":0,"farmlandNum":"003","alias":"雪松农场003","landType":"0","plantVaritety":"火腿","user":{"id":"1","isNewRecord":false,"startNum":0,"name":"系统管理员","loginFlag":"1","admin":true,"roleNames":""},"usedId":"1","usedName":"系统管理员","farmer":{"id":"89","isNewRecord":false,"startNum":0,"name":"菏泽牡丹东关小农场"},"area":30,"addr":"北京昌平","nodeNumber":0,"plantNum":0},{"id":"111","isNewRecord":false,"remarks":"看看咯","createDate":"2017-05-24 11:17:53","updateDate":"2017-05-24 11:17:53","startNum":0,"farmlandNum":"003","alias":"雪松003","landType":"0","plantVaritety":"兔兔","user":{"id":"1","isNewRecord":false,"startNum":0,"name":"系统管理员","loginFlag":"1","admin":true,"roleNames":""},"usedId":"1","usedName":"系统管理员","farmer":{"id":"89","isNewRecord":false,"startNum":0,"name":"菏泽牡丹东关小农场"},"area":6566,"addr":"考虑考虑","nodeNumber":0,"plantNum":0}]
     */

    private NodeBean node;
    private int flag;
    private DataBean data;
    private List<LandsBean> lands;

    public NodeBean getNode() {
        return node;
    }

    public void setNode(NodeBean node) {
        this.node = node;
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

    public List<LandsBean> getLands() {
        return lands;
    }

    public void setLands(List<LandsBean> lands) {
        this.lands = lands;
    }

    public static class NodeBean extends BaseBean {
        /**
         * relayNum : 356330303034
         * farmlandName : 鹤泉村一号试点大棚
         * onOffName : 一号大棚灌溉
         * nodeNum : 246C8E83
         * addTime : 2017-04-26 16:13:11
         * nodeAlise : 一号大棚监控节点
         * id : 46
         * power :
         * type :
         * user : {"id":"6e66721c16024e40bc39b54f1f7f7b20","isNewRecord":false,"startNum":0,"name":"陈智刚","loginFlag":"1","admin":false,"roleNames":""}
         * usedName :
         * cycle :
         */

        private String relayNum;
        private String farmlandName;
        private String onOffName;
        private String nodeNum;
        private String addTime;
        private String nodeAlise;
        private String id;
        private String power;
        private String type;
        private UserBean user;
        private String usedName;
        private String cycle;

        public String getRelayNum() {
            return relayNum;
        }

        public void setRelayNum(String relayNum) {
            this.relayNum = relayNum;
        }

        public String getFarmlandName() {
            return farmlandName;
        }

        public void setFarmlandName(String farmlandName) {
            this.farmlandName = farmlandName;
        }

        public String getOnOffName() {
            return onOffName;
        }

        public void setOnOffName(String onOffName) {
            this.onOffName = onOffName;
        }

        public String getNodeNum() {
            return nodeNum;
        }

        public void setNodeNum(String nodeNum) {
            this.nodeNum = nodeNum;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public String getNodeAlise() {
            return nodeAlise;
        }

        public void setNodeAlise(String nodeAlise) {
            this.nodeAlise = nodeAlise;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPower() {
            return power;
        }

        public void setPower(String power) {
            this.power = power;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public String getUsedName() {
            return usedName;
        }

        public void setUsedName(String usedName) {
            this.usedName = usedName;
        }

        public String getCycle() {
            return cycle;
        }

        public void setCycle(String cycle) {
            this.cycle = cycle;
        }

        public static class UserBean extends BaseBean {
            /**
             * id : 6e66721c16024e40bc39b54f1f7f7b20
             * isNewRecord : false
             * startNum : 0
             * name : 陈智刚
             * loginFlag : 1
             * admin : false
             * roleNames :
             */

            private String id;
            private boolean isNewRecord;
            private int startNum;
            private String name;
            private String loginFlag;
            private boolean admin;
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

            public int getStartNum() {
                return startNum;
            }

            public void setStartNum(int startNum) {
                this.startNum = startNum;
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
    }

    public static class DataBean extends BaseBean {
        /**
         * soilTemperature3 : 32.94
         * soilTemperature2 : 35.22
         * soilTemperature1 : 34.08
         * soilHumidity1 : 46.36
         * addTime : 2017-05-17 16:55:05
         * co2 : 367.0
         * nodeMac : 246C8E83
         * airTemperature : 25.0
         * openFlag : 1
         * airHumidity : 55.97
         * soilHumidity3 : 48.3
         * soilHumidity2 : 8.56
         * power : 1
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

    public static class LandsBean extends BaseBean {
        /**
         * id : 13
         * isNewRecord : false
         * remarks : 对方是否
         * startNum : 0
         * farmlandNum : hc001
         * alias : 河北廊坊一号鹏
         * landType : 0
         * plantVaritety : 水稻
         * user : {"id":"1","isNewRecord":false,"startNum":0,"name":"系统管理员","loginFlag":"1","admin":true,"roleNames":""}
         * usedId : 65e9573a8c0a45b991e2ae16fe4010d4
         * assigneTime : 2017-04-25 00:00:00
         * farmer : {"id":"90","isNewRecord":false,"startNum":0}
         * area : 500.0
         * addr : 河北廊坊农场
         * relay : {"id":"35","isNewRecord":false,"startNum":0,"relayNum":"155462005586"}
         * nodeNumber : 0
         * plantNum : 0
         * createDate : 2017-04-28 11:29:32
         * updateDate : 2017-05-24 11:10:54
         * usedName : 系统管理员
         */

        private String id;
        private boolean isNewRecord;
        private String remarks;
        private int startNum;
        private String farmlandNum;
        private String alias;
        private String landType;
        private String plantVaritety;
        private UserBeanX user;
        private String usedId;
        private String assigneTime;
        private FarmerBean farmer;
        private double area;
        private String addr;
        private RelayBean relay;
        private int nodeNumber;
        private int plantNum;
        private String createDate;
        private String updateDate;
        private String usedName;

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

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public int getStartNum() {
            return startNum;
        }

        public void setStartNum(int startNum) {
            this.startNum = startNum;
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

        public String getLandType() {
            return landType;
        }

        public void setLandType(String landType) {
            this.landType = landType;
        }

        public String getPlantVaritety() {
            return plantVaritety;
        }

        public void setPlantVaritety(String plantVaritety) {
            this.plantVaritety = plantVaritety;
        }

        public UserBeanX getUser() {
            return user;
        }

        public void setUser(UserBeanX user) {
            this.user = user;
        }

        public String getUsedId() {
            return usedId;
        }

        public void setUsedId(String usedId) {
            this.usedId = usedId;
        }

        public String getAssigneTime() {
            return assigneTime;
        }

        public void setAssigneTime(String assigneTime) {
            this.assigneTime = assigneTime;
        }

        public FarmerBean getFarmer() {
            return farmer;
        }

        public void setFarmer(FarmerBean farmer) {
            this.farmer = farmer;
        }

        public double getArea() {
            return area;
        }

        public void setArea(double area) {
            this.area = area;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public RelayBean getRelay() {
            return relay;
        }

        public void setRelay(RelayBean relay) {
            this.relay = relay;
        }

        public int getNodeNumber() {
            return nodeNumber;
        }

        public void setNodeNumber(int nodeNumber) {
            this.nodeNumber = nodeNumber;
        }

        public int getPlantNum() {
            return plantNum;
        }

        public void setPlantNum(int plantNum) {
            this.plantNum = plantNum;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getUsedName() {
            return usedName;
        }

        public void setUsedName(String usedName) {
            this.usedName = usedName;
        }

        public static class UserBeanX extends BaseBean {
            /**
             * id : 1
             * isNewRecord : false
             * startNum : 0
             * name : 系统管理员
             * loginFlag : 1
             * admin : true
             * roleNames :
             */

            private String id;
            private boolean isNewRecord;
            private int startNum;
            private String name;
            private String loginFlag;
            private boolean admin;
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

            public int getStartNum() {
                return startNum;
            }

            public void setStartNum(int startNum) {
                this.startNum = startNum;
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
             * id : 90
             * isNewRecord : false
             * startNum : 0
             */

            private String id;
            private boolean isNewRecord;
            private int startNum;

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

            public int getStartNum() {
                return startNum;
            }

            public void setStartNum(int startNum) {
                this.startNum = startNum;
            }
        }

        public static class RelayBean extends BaseBean {
            /**
             * id : 35
             * isNewRecord : false
             * startNum : 0
             * relayNum : 155462005586
             */

            private String id;
            private boolean isNewRecord;
            private int startNum;
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

            public int getStartNum() {
                return startNum;
            }

            public void setStartNum(int startNum) {
                this.startNum = startNum;
            }

            public String getRelayNum() {
                return relayNum;
            }

            public void setRelayNum(String relayNum) {
                this.relayNum = relayNum;
            }
        }
    }
}
