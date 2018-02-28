package com.colud.jctl.mvp.model;

import android.text.TextUtils;

import com.colud.jctl.api.ApiEngine;
import com.colud.jctl.bean.FacilityItem;
import com.colud.jctl.mvp.contract.FacilityManageContract;
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
import rx.functions.Func1;

/**
 * Created by Jcty on 2017/3/11.
 */

public class FacilityManageModel implements FacilityManageContract.Model {


    private List<FacilityItem.InfoBean> mList = new ArrayList<>();

    @Override
    public Observable<FacilityItem> getFacilityManageData(Map<String, String> map) {
        return ApiEngine.getInstance().getApiService().getFacilityManageData(map).map(new Func1<FacilityItem, FacilityItem>() {
            @Override
            public FacilityItem call(FacilityItem facilityItem) {
                if (facilityItem != null && 1 == facilityItem.getFlag()) {
                    mList.clear();
                    String jsonObject = GsonUtils.newInstance().toJson(facilityItem.getInfo());
                    if (jsonObject != null && !TextUtils.isEmpty(jsonObject)) {
                        JsonParser parser = new JsonParser();
                        JsonArray jsonArray = parser.parse(jsonObject).getAsJsonArray();
                        for (JsonElement user : jsonArray) {
                            //使用GSON，直接转成Bean对象
                            FacilityItem.InfoBean bean = GsonUtils.newInstance().fromJson(user, FacilityItem.InfoBean.class);
                            mList.add(bean);
                        }
                        facilityItem.setList(mList);
                        //						JctlApplication.getCache().put(KeyConstants.KAY_FACILITY_MANAGE, (Serializable) facilityItem.getList());
                    }
                    //					KLog.json(jsonObject);
                }
                return facilityItem;
            }
        }).compose(RxSchedulers.<FacilityItem>switchThread());
    }

    @Override
    public Observable<FacilityItem> getFacilityManageMore(Map<String, String> map) {
        return ApiEngine.getInstance().getApiService().getFacilityManageData(map).map(new Func1<FacilityItem, FacilityItem>() {
            @Override
            public FacilityItem call(FacilityItem facilityItem) {
                if (facilityItem != null && 1 == facilityItem.getFlag()) {
                    mList.clear();
                    String jsonObject = GsonUtils.newInstance().toJson(facilityItem.getInfo());
                    if (jsonObject != null && !TextUtils.isEmpty(jsonObject)) {
                        JsonParser parser = new JsonParser();
                        JsonArray jsonArray = parser.parse(jsonObject).getAsJsonArray();
                        for (JsonElement user : jsonArray) {
                            //使用GSON，直接转成Bean对象
                            FacilityItem.InfoBean bean = GsonUtils.newInstance().fromJson(user, FacilityItem.InfoBean.class);
                            mList.add(bean);
                        }
                        facilityItem.setList(mList);
                        //						JctlApplication.getCache().put(KeyConstants.KAY_FACILITY_MANAGE, (Serializable) facilityItem.getList());
                    }
                    KLog.json(jsonObject);
                }
                return facilityItem;
            }
        }).compose(RxSchedulers.<FacilityItem>switchThread());
    }
}
