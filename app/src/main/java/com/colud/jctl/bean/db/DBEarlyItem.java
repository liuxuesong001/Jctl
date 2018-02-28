package com.colud.jctl.bean.db;

import org.litepal.crud.DataSupport;

import java.util.Date;
import java.util.List;

/**
 * 数据库item
 * Created by Administrator on 2017/9/18.
 */

public class DBEarlyItem extends DataSupport {

    /**
     * date : 2017-09-08 14:38:44
     * farmland_name : 我的田
     * farmland_num : 123
     * node_num : 0C059666
     */

    private int id;
    private String userId;
    private String singleId;
    private String date;
    private String farmland_name;
    private String farmland_num;
    private String node_num;
    private String status;
    private String openFlag;
    private String msg;
    private Date PublishDate;

    private DBUserItem mDBUserItem;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getPublishDate() {
        return PublishDate;
    }

    public void setPublishDate(Date publishDate) {
        PublishDate = publishDate;
    }

    public DBUserItem getDBUserItem() {
        return mDBUserItem;
    }

    public void setDBUserItem(DBUserItem DBUserItem) {
        mDBUserItem = DBUserItem;
    }

    public String getSingleId() {
        return singleId;
    }

    public void setSingleId(String singleId) {
        this.singleId = singleId;
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

    public List<DBEarlyItem> getComments() {
        return DataSupport.select("userid")
                .where("userid > ?", "")
                //        "userid = ?","15510620188"
                .order("publishdate desc").find(DBEarlyItem.class, true);
    }
    //    select * from 表名 where userid=155;
}