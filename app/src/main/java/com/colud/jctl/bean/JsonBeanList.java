package com.colud.jctl.bean;

import com.colud.jctl.bean.ProvinceData.JsonBean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Jcty on 2017/4/19.
 */

public class JsonBeanList implements Serializable {

    private static final long serialVersionUID = 679541564773233130L;

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private JsonBean jsonBean;

    public JsonBean getJsonBean() {
        return jsonBean;
    }

    public void setJsonBean(JsonBean jsonBean) {
        this.jsonBean = jsonBean;
    }

    private ArrayList<JsonBean> detail;

    public ArrayList<JsonBean> getDetail() {
        return detail;
    }

    public void setDetail(ArrayList<JsonBean> detail) {
        this.detail = detail;
    }

    public ArrayList<JsonBean> getOptions1Items() {
        return options1Items;
    }

    public void setOptions1Items(ArrayList<JsonBean> options1Items) {
        this.options1Items = options1Items;
    }

    public ArrayList<ArrayList<String>> getOptions2Items() {
        return options2Items;
    }

    public void setOptions2Items(ArrayList<ArrayList<String>> options2Items) {
        this.options2Items = options2Items;
    }

    public ArrayList<ArrayList<ArrayList<String>>> getOptions3Items() {
        return options3Items;
    }

    public void setOptions3Items(ArrayList<ArrayList<ArrayList<String>>> options3Items) {
        this.options3Items = options3Items;
    }

    private ArrayList<JsonBean> options1Items;
    private ArrayList<ArrayList<String>> options2Items;
    private ArrayList<ArrayList<ArrayList<String>>> options3Items;


}
