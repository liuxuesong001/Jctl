package com.colud.jctl.bean;

/**
 * Created by Jcty on 2017/4/26.
 */

public class AddFieldData extends BaseBean {

    //	private String farmlandNum;		// 农田编号
    //	private String alias;		// 别名
    //	private String landType;		// 农田类型
    //	private String plantVaritety; //种植植物种类名称
    //	private User user;		// 所有人农田的主权拥有者
    //	private String usedId;		// 使用人农田的当前使用者id
    //	private String usedName; //使用人农田的当前使用者名称
    //	private Date assigneTime;		// 分配时间
    //	private Farmer farmer;		// 所属农场
    //	private Double area;		//面积
    //	private String addr; //位置
    //	private Node relay;// 所属中继
    //	private Integer nodeNumber; //节点数量
    //	private String remarks;//备注

    private String farmlandNum;
    private String farmerId;

    public String getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(String farmerId) {
        this.farmerId = farmerId;
    }

    private String flag;
    private String msg;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String alias;
    private String userName;
    private String landType;
    private String plantVaritety;
    private String addr;
    private double area;
    private String remarks;

    public String getFarmlandNum() {
        return farmlandNum;
    }

    public void setFarmlandNum(String farmlandNum) {
        this.farmlandNum = farmlandNum;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLandType() {
        return landType;
    }

    public void setLandType(String landType) {
        this.landType = landType;
    }

    public String getPlantVaritety() {
        return plantVaritety;
    }

    public void setPlantVaritety(String plantVaritety) {
        this.plantVaritety = plantVaritety;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
