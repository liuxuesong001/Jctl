package com.colud.jctl.mvp.model;

import android.text.TextUtils;

import com.colud.jctl.api.ApiEngine;
import com.colud.jctl.bean.FarmManageBean;
import com.colud.jctl.mvp.contract.FarmManageContract;
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

public class FarmManageModel implements FarmManageContract.Model {

    private List<FarmManageBean.InfoBean> mList = new ArrayList<>();


    @Override
    public Observable<FarmManageBean> getFarmManageData(Map<String, String> map) {
        return ApiEngine.getInstance().getApiService().getFarmManageData(map)
                .map(new Func1<FarmManageBean, FarmManageBean>() {
                    @Override
                    public FarmManageBean call(FarmManageBean farmManageBean) {
                        if (farmManageBean != null && 1 == farmManageBean.getFlag()) {
                            mList.clear();
                            if (farmManageBean.getInfo() != null && farmManageBean.getInfo().size() > 0) {
                                String jsonObject = GsonUtils.newInstance().toJson(farmManageBean.getInfo());
                                if (jsonObject != null && !TextUtils.isEmpty(jsonObject)) {
                                    JsonParser parser = new JsonParser();
                                    JsonArray jsonArray = parser.parse(jsonObject).getAsJsonArray();
                                    for (JsonElement user : jsonArray) {
                                        //使用GSON，直接转成Bean对象
                                        FarmManageBean.InfoBean bazaar = GsonUtils.newInstance().fromJson(user, FarmManageBean.InfoBean.class);
                                        mList.add(bazaar);

                                    }
                                }
                                farmManageBean.setInfo(mList);
                                //							JctlApplication.getCache().put(KeyConstants.KAY_FARM_MANAGE, (Serializable) farmManageBean.getNode());
                                KLog.json(jsonObject);
                            }
                        }
                        return farmManageBean;
                    }
                }).compose(RxSchedulers.<FarmManageBean>switchThread());
    }

    @Override
    public Observable<FarmManageBean> getFarmManageMore(Map<String, String> map) {
        return ApiEngine.getInstance().getApiService().getFarmManageData(map)
                .map(new Func1<FarmManageBean, FarmManageBean>() {
                    @Override
                    public FarmManageBean call(FarmManageBean farmManageBean) {
                        if (farmManageBean != null && 1 == farmManageBean.getFlag()) {
                            mList.clear();
                            String jsonObject = GsonUtils.newInstance().toJson(farmManageBean.getInfo());
                            if (!TextUtils.isEmpty(jsonObject)) {
                                JsonParser parser = new JsonParser();
                                JsonArray jsonArray = parser.parse(jsonObject).getAsJsonArray();
                                for (JsonElement user : jsonArray) {
                                    //使用GSON，直接转成Bean对象
                                    FarmManageBean.InfoBean bazaar = GsonUtils.newInstance().fromJson(user, FarmManageBean.InfoBean.class);
                                    mList.add(bazaar);

                                }
                            }
                            farmManageBean.setInfo(mList);
                            //							JctlApplication.getCache().put(KeyConstants.KAY_FARM_MANAGE, (Serializable) farmManageBean.getNode());
                            KLog.json(jsonObject);
                        }
                        return farmManageBean;
                    }
                }).compose(RxSchedulers.<FarmManageBean>switchThread());
    }
}
