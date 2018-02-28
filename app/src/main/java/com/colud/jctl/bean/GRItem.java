package com.colud.jctl.bean;

import java.util.List;

/**
 * Created by Jcty on 2017/4/10.
 */

public class GRItem extends BaseBean {
    private static final long serialVersionUID = -5766874108471517538L;
    private List<InfoBean> info;

    public List<InfoBean> getInfo() {
        return info;
    }

    public void setInfo(List<InfoBean> info) {
        this.info = info;
    }

    public static class InfoBean extends BaseBean {
        private List<String> addTime;
        private List<String> content;

        public List<String> getAddTime() {
            return addTime;
        }

        public void setAddTime(List<String> addTime) {
            this.addTime = addTime;
        }

        public List<String> getContent() {
            return content;
        }

        public void setContent(List<String> content) {
            this.content = content;
        }
    }


    private String flag;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    private String name;
    private String num;
    private String msg;
    private String date;
    private List<String> addTime;

    public List<Float> getContent() {
        return content;
    }

    public void setContent(List<Float> content) {
        this.content = content;
    }

    private List<Float> content;
    private InfoBean flagBean;

    public InfoBean getFlagBean() {
        return flagBean;
    }

    public void setFlagBean(InfoBean flagBean) {
        this.flagBean = flagBean;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getAddTime() {
        return addTime;
    }

    public void setAddTime(List<String> addTime) {
        this.addTime = addTime;
    }

}
