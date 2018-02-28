package com.colud.jctl.bean.db;

import com.colud.jctl.bean.EarlyItme;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户id
 * Created by Administrator on 2017/9/19.
 */

public class DBUserItem extends DataSupport {

    private int id;

    private String userID;

    private List<EarlyItme> mEarlyItems = new ArrayList<>();

    private Date PublishDate;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    private int EarlyItemsCount;

    public int getEarlyItemsCount() {
        return EarlyItemsCount;
    }

    public void setEarlyItemsCount(int earlyItemsCount) {
        EarlyItemsCount = earlyItemsCount;
    }

    public Date getPublishDate() {
        return PublishDate;
    }

    public void setPublishDate(Date publishDate) {
        PublishDate = publishDate;
    }

    public List<EarlyItme> getEarlyItems() {
        return mEarlyItems;
    }

    public void setEarlyItems(List<EarlyItme> earlyItems) {
        mEarlyItems = earlyItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
