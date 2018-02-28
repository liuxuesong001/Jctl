package com.colud.jctl.bean;

import java.io.Serializable;

/**
 * Created by Jcty on 2017/4/11.
 */

public class StewardItem implements Serializable {
    private static final long serialVersionUID = -8059769501586158458L;

    private String farmName;
    private String manageNc;
    private String relayNum;
    private String farmerNum;

    public StewardItem(String farmName, String manageNc, String relayNum, String farmerNum) {
        this.farmName = farmName;
        this.manageNc = manageNc;
        this.relayNum = relayNum;
        this.farmerNum = farmerNum;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public String getManageNc() {
        return manageNc;
    }

    public void setManageNc(String manageNc) {
        this.manageNc = manageNc;
    }

    public String getRelayNum() {
        return relayNum;
    }

    public void setRelayNum(String relayNum) {
        this.relayNum = relayNum;
    }

    public String getFarmerNum() {
        return farmerNum;
    }

    public void setFarmerNum(String farmerNum) {
        this.farmerNum = farmerNum;
    }
}
