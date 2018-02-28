package com.colud.jctl.bean;

import java.util.List;

/**
 * Created by Jcty on 2017/4/18.
 */

public class FarmManageBean extends BaseBean {


    /**
     * flag : 1
     * info : [{"plantVariety":"川芎","lng":"104.030000","name":"鹤泉村试点","id":"87","addr":"四川省彭州市敖平镇鹤泉村","farmlandNumber":2,"lat":"31.100000"},{"plantVariety":"西红柿","lng":"116.606929","name":"北京都市农汇农业科技示范园","id":"91","addr":"北京市朝阳区黑庄户乡大东路1号","farmlandNumber":3,"lat":"39.882262"},{"plantVariety":"空军建军节","lng":"105.000000","name":"考虑考虑","id":"92","addr":"北京市北京市崇文区","farmlandNumber":1,"lat":"39.000000"},{"plantVariety":"草菇","lng":"122.000000","name":"北京菌益农种植区","id":"93","addr":"北京市大兴区黄村镇李村村委会西北1000米","farmlandNumber":3,"lat":"122.000000"}]
     */

    private int flag;
    private String msg;
    private List<String> nList;
    private List<String> idList;

    public List<String> getIdList() {
        return idList;
    }

    public void setIdList(List<String> idList) {
        this.idList = idList;
    }

    public List<String> getnList() {
        return nList;
    }

    public void setnList(List<String> nList) {
        this.nList = nList;
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
         * plantVariety : 川芎
         * lng : 104.030000
         * name : 鹤泉村试点
         * id : 87
         * addr : 四川省彭州市敖平镇鹤泉村
         * farmlandNumber : 2
         * lat : 31.100000
         */

        private String plantVariety;
        private String lng;
        private String name;
        private String id;
        private String addr;
        private int farmlandNumber;
        private String lat;

        public String getPlantVariety() {
            return plantVariety;
        }

        public void setPlantVariety(String plantVariety) {
            this.plantVariety = plantVariety;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

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

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public int getFarmlandNumber() {
            return farmlandNumber;
        }

        public void setFarmlandNumber(int farmlandNumber) {
            this.farmlandNumber = farmlandNumber;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }
    }
}
