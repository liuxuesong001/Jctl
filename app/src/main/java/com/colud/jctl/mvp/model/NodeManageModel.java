package com.colud.jctl.mvp.model;

import android.text.TextUtils;

import com.colud.jctl.api.ApiEngine;
import com.colud.jctl.bean.NodeManageItem;
import com.colud.jctl.mvp.contract.NodeManageContract;
import com.colud.jctl.rx.RxSchedulers;
import com.colud.jctl.utils.GsonUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Jcty on 2017/3/11.
 */

public class NodeManageModel implements NodeManageContract.Model {


    private List<NodeManageItem.InfoBean> mList = new ArrayList<>();

    @Override
    public Observable<NodeManageItem> getNodeManageData(Map<String, String> map) {
        return ApiEngine.getInstance().getApiService().getNodeManageData(map).map(new Func1<NodeManageItem, NodeManageItem>() {
            @Override
            public NodeManageItem call(NodeManageItem data) {
                if (data != null && 1 == data.getFlag()) {
                    mList.clear();
                    if (data.getInfo() != null && data.getInfo().size() > 0) {
                        String jsonObject = GsonUtils.newInstance().toJson(data.getInfo());
                        if (jsonObject != null && !TextUtils.isEmpty(jsonObject)) {
                            JsonParser parser = new JsonParser();
                            JsonArray jsonArray = parser.parse(jsonObject).getAsJsonArray();
                            for (JsonElement user : jsonArray) {
                                //使用GSON，直接转成Bean对象
                                NodeManageItem.InfoBean bean = GsonUtils.newInstance().fromJson(user, NodeManageItem.InfoBean.class);
                                mList.add(bean);
                            }
                            data.setInfo(mList);
                        }
                    }
                }
                return data;
            }
        }).compose(RxSchedulers.<NodeManageItem>switchThread());
    }

    @Override
    public Observable<NodeManageItem> getNodeManageMore(Map<String, String> map) {
        return ApiEngine.getInstance().getApiService().getNodeManageData(map).map(new Func1<NodeManageItem, NodeManageItem>() {
            @Override
            public NodeManageItem call(NodeManageItem data) {
                if (data != null && 1 == data.getFlag()) {
                    mList.clear();
                    if (data.getInfo() != null && data.getInfo().size() > 0) {
                        String jsonObject = GsonUtils.newInstance().toJson(data.getInfo());
                        if (jsonObject != null && !TextUtils.isEmpty(jsonObject)) {
                            JsonParser parser = new JsonParser();
                            JsonArray jsonArray = parser.parse(jsonObject).getAsJsonArray();
                            for (JsonElement user : jsonArray) {
                                //使用GSON，直接转成Bean对象
                                NodeManageItem.InfoBean bean = GsonUtils.newInstance().fromJson(user, NodeManageItem.InfoBean.class);
                                mList.add(bean);
                            }
                            data.setInfo(mList);
                        }
                    }
                }
                return data;
            }
        }).compose(RxSchedulers.<NodeManageItem>switchThread());
    }
}
