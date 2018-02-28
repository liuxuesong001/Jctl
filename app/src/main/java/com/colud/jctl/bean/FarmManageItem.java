package com.colud.jctl.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jcty on 2017/3/29.
 */

public class FarmManageItem implements Serializable {
    private static final long serialVersionUID = -7852721992008378871L;

    private String id;
    private String name;
    private String addr;
    private String farmlandNumber;
    private String plantVariety;

    private List<FarmDetailItem> list;

    public List<FarmDetailItem> getList() {
        return list;
    }

    public void setList(List<FarmDetailItem> list) {
        this.list = list;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getFarmlandNumber() {
        return farmlandNumber;
    }

    public void setFarmlandNumber(String farmlandNumber) {
        this.farmlandNumber = farmlandNumber;
    }

    public String getPlantVariety() {
        return plantVariety;
    }

    public void setPlantVariety(String plantVariety) {
        this.plantVariety = plantVariety;
    }
}
