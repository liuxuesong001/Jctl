package com.colud.jctl.mvp.model;

import android.text.TextUtils;

import com.colud.jctl.api.ApiEngine;
import com.colud.jctl.bean.AddFieldData;
import com.colud.jctl.bean.FieldDetailItem;
import com.colud.jctl.mvp.contract.FieldDetailContract;
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

public class FieldDetailModel implements FieldDetailContract.Model {

    private List<FieldDetailItem.InfoBean> mList = new ArrayList<>();

    @Override
    public Observable<FieldDetailItem> getFieldData(Map<String, String> map) {
        return ApiEngine.getInstance().getApiService().getFieldDetail(map)
                .map(new Func1<FieldDetailItem, FieldDetailItem>() {
                    @Override
                    public FieldDetailItem call(FieldDetailItem data) {
                        if (data != null && data.getFlag().equals("1")) {
                            if (data.getInfo() != null && data.getInfo().size() > 0) {
                                String jsonObject = GsonUtils.newInstance().toJson(data.getInfo());
                                if (jsonObject != null && !"null".equals(jsonObject) && !TextUtils.isEmpty(jsonObject)) {
                                    mList.clear();
                                    JsonParser parser = new JsonParser();
                                    JsonArray jsonArray = parser.parse(jsonObject).getAsJsonArray();
                                    for (JsonElement user : jsonArray) {
                                        //使用GSON，直接转成Bean对象
                                        FieldDetailItem.InfoBean infoBean = GsonUtils.newInstance().fromJson(user, FieldDetailItem.InfoBean.class);
                                        mList.add(infoBean);
                                        data.setInfoBean(infoBean);
                                    }
                                    data.setInfo(mList);
                                }
                                KLog.json(jsonObject);
                            }

                        }

                        return data;
                    }
                }).compose(RxSchedulers.<FieldDetailItem>switchThread());
    }

    @Override
    public Observable<FieldDetailItem> getDeleteData(Map<String, String> map) {
        return ApiEngine.getInstance().getApiService().getDeleteField(map).compose(RxSchedulers.<FieldDetailItem>switchThread());
    }

    @Override
    public Observable<AddFieldData> getFieldDataUpdate(Map<String, String> map) {
        return ApiEngine.getInstance().getApiService().getAddFieldData(map).compose(RxSchedulers.<AddFieldData>switchThread());
    }
}
