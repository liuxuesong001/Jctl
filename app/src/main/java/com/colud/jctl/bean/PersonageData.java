package com.colud.jctl.bean;

import java.io.Serializable;

/**
 * Created by Jcty on 2017/3/23.
 */

public class PersonageData implements Serializable {
    private static final long serialVersionUID = -6471356317189785103L;

    private String username;
    private String useraccount;
    private String usertype;
    private String usersex;
    private String userage;
    private String nestpadnum;//nestpad数量
    private String peasantnum;//农户数量

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseraccount() {
        return useraccount;
    }

    public void setUseraccount(String useraccount) {
        this.useraccount = useraccount;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getUsersex() {
        return usersex;
    }

    public void setUsersex(String usersex) {
        this.usersex = usersex;
    }

    public String getUserage() {
        return userage;
    }

    public void setUserage(String userage) {
        this.userage = userage;
    }

    public String getNestpadnum() {
        return nestpadnum;
    }

    public void setNestpadnum(String nestpadnum) {
        this.nestpadnum = nestpadnum;
    }

    public String getPeasantnum() {
        return peasantnum;
    }

    public void setPeasantnum(String peasantnum) {
        this.peasantnum = peasantnum;
    }
}
