package com.colud.jctl.bean;

import java.util.List;

/**
 * Created by Jcty on 2017/6/5.
 */

public class FarmersBean extends BaseBean {

    private int flag;

    private String msg;

    private List<DataBean> data;


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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }


    public static class DataBean extends BaseBean {
        /**
         * nodes : 0
         * phone : 15510620132
         * name : 哒哒
         * id : af7a84bfee8b4b42b178efd2d6f54dc5
         * farmlands : 0
         */

        private int nodes;
        private String phone;
        private String name;
        private String id;
        private int farmlands;

        public int getNodes() {
            return nodes;
        }

        public void setNodes(int nodes) {
            this.nodes = nodes;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
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

        public int getFarmlands() {
            return farmlands;
        }

        public void setFarmlands(int farmlands) {
            this.farmlands = farmlands;
        }
    }
}
