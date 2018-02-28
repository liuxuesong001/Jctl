package com.colud.jctl.mvp.model;

import android.text.TextUtils;

import com.colud.jctl.api.ApiEngine;
import com.colud.jctl.bean.LastNodeDetailsBean;
import com.colud.jctl.mvp.contract.LastNodeDatailsContract;
import com.colud.jctl.rx.RxSchedulers;
import com.colud.jctl.utils.GsonUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.Map;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Jcty on 2017/3/11.
 */

public class LastNodeDatailsModel implements LastNodeDatailsContract.Model {


    @Override
    public Observable<LastNodeDetailsBean> getLastNodeDetails(Map<String, String> map) {
        return ApiEngine.getInstance().getApiService().getLastNodeDetails(map).map(new Func1<LastNodeDetailsBean, LastNodeDetailsBean>() {
            @Override
            public LastNodeDetailsBean call(LastNodeDetailsBean data) {
                if (data != null && data.getFlag() == 1) {
                    if (data.getInfoBeen() != null && data.getInfoBeen().size() > 0) {
                        String jsonObject = GsonUtils.newInstance().toJson(data.getInfoBeen());
                        if (jsonObject != null && !TextUtils.isEmpty(jsonObject)) {
                            JsonParser parser = new JsonParser();
                            JsonArray jsonArray = parser.parse(jsonObject).getAsJsonArray();
                            for (JsonElement user : jsonArray) {
                                //使用GSON，直接转成Bean对象
                                LastNodeDetailsBean.InfoBean info = GsonUtils.newInstance().fromJson(user, LastNodeDetailsBean.InfoBean.class);
                                data.setInfo(info);
                            }
                        }
                    }
                }
                return data;
            }
        }).compose(RxSchedulers.<LastNodeDetailsBean>switchThread());
    }
}
