package com.colud.jctl.bean;

import java.io.Serializable;

/**
 * Created by Jcty on 2017/4/17.
 */

public class SplashDataItem implements Serializable {

    private static final long serialVersionUID = 1063478403072859029L;
    private UserItem userItem;
    private WeatherHome homeTemp;

    public SplashDataItem(UserItem userItem, WeatherHome homeTemp) {
        this.userItem = userItem;
        this.homeTemp = homeTemp;
    }

    public UserItem getUserItem() {
        return userItem;
    }

    public void setUserItem(UserItem userItem) {
        this.userItem = userItem;
    }

    public WeatherHome getHomeTemp() {
        return homeTemp;
    }

    public void setHomeTemp(WeatherHome homeTemp) {
        this.homeTemp = homeTemp;
    }
}
