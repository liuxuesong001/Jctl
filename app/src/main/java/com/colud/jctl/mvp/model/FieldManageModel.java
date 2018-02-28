package com.colud.jctl.mvp.model;

import android.text.TextUtils;

import com.colud.jctl.api.ApiEngine;
import com.colud.jctl.bean.FieldManageItem;
import com.colud.jctl.mvp.contract.FieldManageContract;
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

public class FieldManageModel implements FieldManageContract.Model {

    private List<FieldManageItem.InfoBean> mListData = new ArrayList<>();


    @Override
    public Observable<FieldManageItem> getFieldManageData(Map<String, String> map) {

        //		RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),data);

        return ApiEngine.getInstance().getApiService().getFarmlandData(map).map(new Func1<FieldManageItem, FieldManageItem>() {
            @Override
            public FieldManageItem call(FieldManageItem item) {
                if (item != null && "1".equals(item.getFlag())) {
                    mListData.clear();
                    String jsoninfo = GsonUtils.newInstance().toJson(item.getInfo());
                    if (jsoninfo != null && !TextUtils.isEmpty(jsoninfo) && !"null".equals(jsoninfo)) {
                        JsonParser parser = new JsonParser();
                        JsonArray jsonArray = parser.parse(jsoninfo).getAsJsonArray();
                        for (JsonElement user : jsonArray) {
                            //使用GSON，直接转成Bean对象
                            FieldManageItem.InfoBean info = GsonUtils.newInstance().fromJson(user, FieldManageItem.InfoBean.class);
                            mListData.add(info);
                            item.setInfoBean(info);
                        }
                        item.setInfo(mListData);
                        //						JctlApplication.getCache().put(KeyConstants.KAY_FARM_MANAGE, (Serializable) item.getNode());
                    }
                    KLog.json(jsoninfo);
                }
                return item;
            }
        }).compose(RxSchedulers.<FieldManageItem>switchThread());
    }

    @Override
    public Observable<FieldManageItem> getFieldManageMore(Map<String, String> map) {
        return ApiEngine.getInstance().getApiService().getFarmlandData(map).map(new Func1<FieldManageItem, FieldManageItem>() {
            @Override
            public FieldManageItem call(FieldManageItem item) {
                if (item != null && "1".equals(item.getFlag())) {
                    mListData.clear();
                    String jsoninfo = GsonUtils.newInstance().toJson(item.getInfo());
                    if (jsoninfo != null && !TextUtils.isEmpty(jsoninfo) && !"null".equals(jsoninfo)) {
                        JsonParser parser = new JsonParser();
                        JsonArray jsonArray = parser.parse(jsoninfo).getAsJsonArray();
                        for (JsonElement user : jsonArray) {
                            //使用GSON，直接转成Bean对象
                            FieldManageItem.InfoBean info = GsonUtils.newInstance().fromJson(user, FieldManageItem.InfoBean.class);
                            mListData.add(info);
                            item.setInfoBean(info);
                        }
                        item.setInfo(mListData);
                        //						JctlApplication.getCache().put(KeyConstants.KAY_FARM_MANAGE, (Serializable) item.getNode());
                    }
                    KLog.json(jsoninfo);
                }
                return item;
            }
        }).compose(RxSchedulers.<FieldManageItem>switchThread());
    }
}
