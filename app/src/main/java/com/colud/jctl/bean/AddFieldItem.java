package com.colud.jctl.bean;

import java.util.List;

/**
 * Created by Jcty on 2017/4/25.
 */

public class AddFieldItem extends BaseBean {


    /**
     * flag : 1
     * info : [{"name":"yumingze","id":"3b1a32ab98e240299ef6fce99f7e1f35"},{"name":"网吧","id":"44"}]
     */

    private String flag;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private InfoBean infoBean;
    private List<String> sList;

    public List<String> getIdList() {
        return idList;
    }

    public void setIdList(List<String> idList) {
        this.idList = idList;
    }

    private List<String> idList;

    public List<String> getsList() {
        return sList;
    }

    public void setsList(List<String> sList) {
        this.sList = sList;
    }

    public InfoBean getInfoBean() {
        return infoBean;
    }

    public void setInfoBean(InfoBean infoBean) {
        this.infoBean = infoBean;
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
         * name : yumingze
         * id : 3b1a32ab98e240299ef6fce99f7e1f35
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
}
