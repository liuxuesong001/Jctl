package com.colud.jctl.mvp.model;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.ArrayMap;

import com.colud.jctl.JctlApplication;
import com.colud.jctl.api.ApiEngine;
import com.colud.jctl.api.KeyConstants;
import com.colud.jctl.bean.JsonBeanList;
import com.colud.jctl.bean.FarmDetailItem;
import com.colud.jctl.bean.ProvinceData.JsonBean;
import com.colud.jctl.mvp.contract.FarmDetailContract;
import com.colud.jctl.rx.RxSchedulers;
import com.colud.jctl.utils.GsonUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.socks.library.KLog;

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

public class FarmDetailModel implements FarmDetailContract.Model {

    private ArrayList<JsonBean> addlist = new ArrayList<>();
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    private List<FarmDetailItem.InfoBean> mList = new ArrayList<>();

    @SuppressLint("NewApi")
    private Map<String, String> map = new ArrayMap<>();

    @Override
    public Observable<FarmDetailItem> getFarmData(Map<String, String> map) {
        return ApiEngine.getInstance().getApiService().getFarmDetail(map).map(new Func1<FarmDetailItem, FarmDetailItem>() {
            @Override
            public FarmDetailItem call(FarmDetailItem data) {
                if (data != null && data.getFlag().equals("1")) {
                    if (data.getInfo() != null && data.getInfo().size() > 0) {
                        String jsonObject = GsonUtils.newInstance().toJson(data.getInfo());
                        if (jsonObject != null && !TextUtils.isEmpty(jsonObject)) {
                            JsonParser parser = new JsonParser();
                            JsonArray jsonArray = parser.parse(jsonObject).getAsJsonArray();
                            for (JsonElement user : jsonArray) {
                                //使用GSON，直接转成Bean对象
                                FarmDetailItem.InfoBean infoBean = GsonUtils.newInstance().fromJson(user, FarmDetailItem.InfoBean.class);
                                //						mList.add(infoBean);
                                data.setInfoBean(infoBean);
                            }
                            data.setInfo(mList);
                            KLog.json(jsonObject);
                        }
                    }
                }
                return data;
            }
        }).compose(RxSchedulers.<FarmDetailItem>switchThread());
    }

    @Override
    public Observable<FarmDetailItem> getDeleteData(String id) {
        return ApiEngine.getInstance().getApiService().getDeleteData(id).compose(RxSchedulers.<FarmDetailItem>switchThread());
    }

    @Override
    public Observable<FarmDetailItem> getFarmDataUpdate(Map<String, String> map) {
        return ApiEngine.getInstance().getApiService().getFarmDataUpdate(map)
                .compose(RxSchedulers.<FarmDetailItem>switchThread());
    }

    @Override
    public Observable<JsonBeanList> getAddRessJson(final JsonBeanList jblist) {
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
                }).subscribeOn(Schedulers.newThread()).compose(RxSchedulers.<JsonBeanList>switchThread());
    }

}
