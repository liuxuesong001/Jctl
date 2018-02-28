package com.colud.jctl.mvp.model;

import android.text.TextUtils;

import com.colud.jctl.JctlApplication;
import com.colud.jctl.api.ApiEngine;
import com.colud.jctl.api.KeyConstants;
import com.colud.jctl.bean.NodeItem;
import com.colud.jctl.mvp.contract.NodeContract;
import com.colud.jctl.rx.RxSchedulers;
import com.colud.jctl.utils.GsonUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Jcty on 2017/3/11.
 */

public class NodeModel implements NodeContract.Model {


    private List<NodeItem.InfoBean> mList = new ArrayList<>();

    @Override
    public Observable<NodeItem> getNodeData(Map<String, String> map) {
        return ApiEngine.getInstance().getApiService().getNodeData(map).map(new Func1<NodeItem, NodeItem>() {
            @Override
            public NodeItem call(NodeItem data) {
                mList.clear();
                if (data != null && "1".equals(data.getFlag())) {
                    String jsonObject = GsonUtils.newInstance().toJson(data.getInfo());
                    if (jsonObject != null && !TextUtils.isEmpty(jsonObject)) {
                        JsonParser parser = new JsonParser();
                        JsonArray jsonArray = parser.parse(jsonObject).getAsJsonArray();
                        for (JsonElement user : jsonArray) {
                            //使用GSON，直接转成Bean对象
                            NodeItem.InfoBean bean = GsonUtils.newInstance().fromJson(user, NodeItem.InfoBean.class);
                            mList.add(bean);
                        }
                        data.setInfo(mList);
                    }
                    JctlApplication.getCache().put(KeyConstants.KAY_NODE, (Serializable) data.getInfo());
                }
                return data;
            }
        }).compose(RxSchedulers.<NodeItem>switchThread());
    }
}
