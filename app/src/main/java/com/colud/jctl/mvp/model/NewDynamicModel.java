package com.colud.jctl.mvp.model;

import android.text.TextUtils;

import com.colud.jctl.JctlApplication;
import com.colud.jctl.api.ApiEngine;
import com.colud.jctl.api.KeyConstants;
import com.colud.jctl.bean.NewInfoItem;
import com.colud.jctl.mvp.contract.NewDynamicContract;
import com.colud.jctl.rx.RxSchedulers;
import com.colud.jctl.utils.GsonUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Jcty on 2017/3/11.
 */

public class NewDynamicModel implements NewDynamicContract.Model {

    private List<NewInfoItem.InfoBean> list = new ArrayList<>();

    @Override
    public Observable<NewInfoItem> getNewDynamicData() {
        return ApiEngine.getInstance().getApiService().getNewInfoData().map(new Func1<NewInfoItem, NewInfoItem>() {
            @Override
            public NewInfoItem call(NewInfoItem newData) {
                if (newData != null || "1".equals(newData.getFlag())) {
                    String jsonObject = GsonUtils.newInstance().toJson(newData.getInfo());
                    if (!"null".equals(jsonObject) && !TextUtils.isEmpty(jsonObject)) {
                        JsonParser parser = new JsonParser();
                        JsonArray jsonArray = parser.parse(jsonObject).getAsJsonArray();
                        for (JsonElement user : jsonArray) {
                            //使用GSON，直接转成Bean对象
                            NewInfoItem.InfoBean info = GsonUtils.newInstance().fromJson(user, NewInfoItem.InfoBean.class);
                            list.add(info);

                        }
                        newData.setInfo(list);
                        if (newData.getInfo().size() > 0) {
                            JctlApplication.getCache().put(KeyConstants.KAY_NEWINFO, (Serializable) list);
                        }
                    }

                }
                return newData;
            }
        }).compose(RxSchedulers.<NewInfoItem>switchThread());
    }
}
