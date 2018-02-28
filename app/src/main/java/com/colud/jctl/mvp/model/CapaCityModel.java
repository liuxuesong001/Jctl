package com.colud.jctl.mvp.model;

import android.text.TextUtils;

import com.colud.jctl.api.ApiEngine;
import com.colud.jctl.bean.CapaCityItem;
import com.colud.jctl.mvp.contract.CapaCityContract;
import com.colud.jctl.rx.RxSchedulers;
import com.colud.jctl.utils.GsonUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Jcty on 2017/3/11.
 */

public class CapaCityModel implements CapaCityContract.Model {

    private List<CapaCityItem.InfoBean> mList = new ArrayList<>();

    @Override
    public Observable<CapaCityItem> getCapaCity(String num) {
        return ApiEngine.getInstance().getApiService().getCapaCityList(num)
                .map(new Func1<CapaCityItem, CapaCityItem>() {
                    @Override
                    public CapaCityItem call(CapaCityItem capaCityItem) {
                        if (capaCityItem != null && "1".equals(capaCityItem.getFlag())) {
                            mList.clear();
                            String jsonObject = GsonUtils.newInstance().toJson(capaCityItem.getInfo());
                            if (jsonObject != null && !TextUtils.isEmpty(jsonObject)) {
                                JsonParser parser = new JsonParser();
                                JsonArray jsonArray = parser.parse(jsonObject).getAsJsonArray();
                                for (JsonElement user : jsonArray) {
                                    //使用GSON，直接转成Bean对象
                                    CapaCityItem.InfoBean bazaar = GsonUtils.newInstance().fromJson(user, CapaCityItem.InfoBean.class);
                                    mList.add(bazaar);
                                }
                                capaCityItem.setInfo(mList);
                                KLog.json(jsonObject);
                            }
                        }
                        return capaCityItem;
                    }
                }).compose(RxSchedulers.<CapaCityItem>switchThread());
    }
}
