package com.colud.jctl.mvp.model;

import android.text.TextUtils;

import com.colud.jctl.JctlApplication;
import com.colud.jctl.api.ApiEngine;
import com.colud.jctl.api.KeyConstants;
import com.colud.jctl.bean.JsonBeanList;
import com.colud.jctl.bean.AddFieldData;
import com.colud.jctl.bean.AddFieldItem;
import com.colud.jctl.bean.ProvinceData.JsonBean;
import com.colud.jctl.mvp.contract.AddFieldContract;
import com.colud.jctl.rx.RxSchedulers;
import com.colud.jctl.utils.GsonUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Jcty on 2017/3/11.
 */

public class AddFieldModel implements AddFieldContract.Model {


    private ArrayList<JsonBean> addlist = new ArrayList<>();
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    private List<AddFieldItem.InfoBean> mList = new ArrayList<>();

    private List<String> nList = new ArrayList<>();
    private List<String> idList = new ArrayList<>();


    @Override
    public Observable<AddFieldItem> getAddField(String uid) {
        return ApiEngine.getInstance().getApiService().getAddFielduId(uid).map(new Func1<AddFieldItem, AddFieldItem>() {
            @Override
            public AddFieldItem call(AddFieldItem data) {
                if (data != null & "1".equals(data.getFlag())) {
                    mList.clear();
                    String jsonObject = GsonUtils.newInstance().toJson(data.getInfo());
                    if (!"null".equals(jsonObject) && !TextUtils.isEmpty(jsonObject)) {
                        JsonParser parser = new JsonParser();
                        JsonArray jsonArray = parser.parse(jsonObject).getAsJsonArray();
                        for (JsonElement user : jsonArray) {
                            //使用GSON，直接转成Bean对象
                            AddFieldItem.InfoBean info = GsonUtils.newInstance().fromJson(user, AddFieldItem.InfoBean.class);
                            mList.add(info);
                            data.setInfoBean(info);
                        }
                        for (int i = 0; i < mList.size(); i++) {
                            AddFieldItem.InfoBean name = mList.get(i);
                            nList.add(name.getName());
                            idList.add(name.getId());
                        }
                        data.setsList(nList);
                        data.setIdList(idList);
                    }
                }

                return data;
            }
        }).compose(RxSchedulers.<AddFieldItem>switchThread());
    }

    @Override
    public Observable<AddFieldData> getAddData(Map<String, String> map) {
        return ApiEngine.getInstance().getApiService().getAddFieldData(
                map
        ).compose(RxSchedulers.<AddFieldData>switchThread());
    }

    @Override
    public Observable<JsonBeanList> getAddRessJson(final JsonBeanList jblist) {
        //		Observable.OnSubscribe
        return Observable.create(new Observable.OnSubscribe<JsonBeanList>() {
            @Override
            public void call(Subscriber<? super JsonBeanList> subscriber) {
                if (!TextUtils.isEmpty(jblist.getAddress())) {
                    JsonParser parser = new JsonParser();
                    JsonArray jsonArray = parser.parse(jblist.getAddress()).getAsJsonArray();
                    for (JsonElement user : jsonArray) {
                        //使用GSON，直接转成Bean对象
                        JsonBean json = GsonUtils.newInstance().fromJson(user, JsonBean.class);
                        addlist.add(json);
                    }
                    options1Items = addlist;
                }
                subscriber.onNext(jblist);
                subscriber.onCompleted();
            }
        }).map(new Func1<JsonBeanList, JsonBeanList>() {
            @Override
            public JsonBeanList call(JsonBeanList jsonBeanList) {
                for (int i = 0; i < addlist.size(); i++) {//遍历省份
                    ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
                    ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）
                    for (int c = 0; c < addlist.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                        String CityName = addlist.get(i).getCityList().get(c).getName();
                        CityList.add(CityName);//添加城市
                        ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表
                        //如果无地区数据，建议添加空数据，防止数据为null 导致三个选项长度不匹配造成崩溃
                        if (addlist.get(i).getCityList().get(c).getArea().size() == 0) {
                            City_AreaList.add("");
                        }
                        for (int d = 0; d < addlist.get(i).getCityList().get(c).getArea().size(); d++) {//该城市对应地区所有数据
                            String AreaName = addlist.get(i).getCityList().get(c).getArea().get(d);

                            City_AreaList.add(AreaName);//添加该城市所有地区数据
                        }
                        Province_AreaList.add(City_AreaList);//添加该省所有地区数据
                    }

                    /**
                     * 添加城市数据
                     */
                    options2Items.add(CityList);

                    /**
                     * 添加地区数据
                     */
                    options3Items.add(Province_AreaList);

                    jblist.setOptions1Items(options1Items);

                    jblist.setOptions2Items(options2Items);

                    jblist.setOptions3Items(options3Items);


                }
                return jblist;
            }
        })
                .map(new Func1<JsonBeanList, JsonBeanList>() {
                    @Override
                    public JsonBeanList call(JsonBeanList jsonBeanList) {
                        JctlApplication.getCache().put(KeyConstants.KAY_ADDFARM_ADDRESS, jblist);
                        return jsonBeanList;
                    }
                    //		}).compose(RxSchedulers.<JsonBeanList>switchThread());
                }).subscribeOn(Schedulers.newThread()).compose(RxSchedulers.<JsonBeanList>switchThread());
    }

}
