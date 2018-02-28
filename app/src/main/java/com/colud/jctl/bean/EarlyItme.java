package com.colud.jctl.bean;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Jcty on 2017/3/20.
 */

public class EarlyItme extends DataSupport implements Serializable {

    private int id;
    private String singleId;
    private String date;
    private String farmland_name;
    private String farmland_num;
    private String node_num;
    private String status;
    private String openFlag;
    private String userId;
    private Date PublishDate;
    private String msg;

    private Custom_content mContent;

    public Custom_content getContent() {
        return mContent;
    }

    public void setContent(Custom_content content) {
        mContent = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getPublishDate() {
        return PublishDate;
    }

    public void setPublishDate(Date publishDate) {
        PublishDate = publishDate;
    }

    public String getSingleId() {
        return singleId;
    }

    public void setSingleId(String singleId) {
        this.singleId = singleId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOpenFlag() {
        return openFlag;
    }

    public void setOpenFlag(String openFlag) {
        this.openFlag = openFlag;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFarmland_name() {
        return farmland_name;
    }

    public void setFarmland_name(String farmland_name) {
        this.farmland_name = farmland_name;
    }

    public String getFarmland_num() {
        return farmland_num;
    }

    public void setFarmland_num(String farmland_num) {
        this.farmland_num = farmland_num;
    }

    public String getNode_num() {
        return node_num;
    }

    public void setNode_num(String node_num) {
        this.node_num = node_num;
    }

    public List<EarlyItme> getComments(String userId) {
        return DataSupport
                .where("userid = ?", userId)
                .order("publishdate desc").find(EarlyItme.class);
    }

    public int getCount(String userId) {
        return DataSupport.where("userid = ?", userId).count(EarlyItme.class);
    }

    public static class Custom_content extends BaseBean {

        private int id;
        private String singleId;
        private String date;
        private String farmland_name;
        private String farmland_num;
        private String node_num;
        private String status;
        private String openFlag;
        private String userId;
        private Date PublishDate;
        private String msg;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public Date getPublishDate() {
            return PublishDate;
        }

        public void setPublishDate(Date publishDate) {
            PublishDate = publishDate;
        }

        public String getSingleId() {
            return singleId;
        }

        public void setSingleId(String singleId) {
            this.singleId = singleId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getOpenFlag() {
            return openFlag;
        }

        public void setOpenFlag(String openFlag) {
            this.openFlag = openFlag;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getFarmland_name() {
            return farmland_name;
        }

        public void setFarmland_name(String farmland_name) {
            this.farmland_name = farmland_name;
        }

        public String getFarmland_num() {
            return farmland_num;
        }

        public void setFarmland_num(String farmland_num) {
            this.farmland_num = farmland_num;
        }

        public String getNode_num() {
            return node_num;
        }

        public void setNode_num(String node_num) {
            this.node_num = node_num;
        }

        public List<EarlyItme> getComments(String userId) {
            return DataSupport
                    .where("userid = ?", userId)
                    .order("publishdate desc").find(EarlyItme.class);
        }

        public int getCount(String userId) {
            return DataSupport.where("userid = ?", userId).count(EarlyItme.class);
        }

    }
}
