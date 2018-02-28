package com.colud.jctl.bean;

import java.util.ArrayList;

/**
 * Created by Jcty on 2017/5/16.
 */

public class RepBean extends BaseBean {

    private ArrayList<String> week;

    public ArrayList<String> getWeek() {
        return week;
    }

    public void setWeek(ArrayList<String> week) {
        this.week = week;
    }

    private boolean isCheck;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
