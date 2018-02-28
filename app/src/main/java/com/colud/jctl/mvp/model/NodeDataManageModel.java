package com.colud.jctl.mvp.model;

import android.text.TextUtils;

import com.colud.jctl.api.ApiEngine;
import com.colud.jctl.bean.NodeDataManageItem;
import com.colud.jctl.mvp.contract.NodeDataManageContract;
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

public class NodeDataManageModel implements NodeDataManageContract.Model {
    private List<String> nList = new ArrayList<>();
    private List<String> idList = new ArrayList<>();

    @Override
    public Observable<NodeDataManageItem> getNodeManage(Map<String, String> map) {
        return ApiEngine.getInstance().getApiService().getNodeDataManage(map).map(new Func1<NodeDataManageItem, NodeDataManageItem>() {
            @Override
            public NodeDataManageItem call(NodeDataManageItem data) {
                if (data != null && 1 == data.getFlag()) {
                    nList.clear();
                    idList.clear();
                    if (data.getLands() != null && data.getLands().size() > 0) {
                        String jsonObject = GsonUtils.newInstance().toJson(data.getLands());
                        if (jsonObject != null && !"null".equals(jsonObject) && !TextUtils.isEmpty(jsonObject)) {
                            JsonParser parser = new JsonParser();
                            JsonArray jsonArray = parser.parse(jsonObject).getAsJsonArray();
                            for (JsonElement user : jsonArray) {
                                //使用GSON，直接转成Bean对象
                                NodeDataManageItem.LandsBean info = GsonUtils.newInstance().fromJson(user, NodeDataManageItem.LandsBean.class);
                                nList.add(info.getAlias());
                                idList.add(info.getId());
                            }
                            data.setnList(nList);
                            data.setIdList(idList);
                        }
                        KLog.json("节点管理:" + jsonObject);
                    }

                }
                return data;
            }
        }).compose(RxSchedulers.<NodeDataManageItem>switchThread());
    }

    @Override
    public Observable<NodeDataManageItem> getNodeUpdate(Map<String, String> map) {
        return ApiEngine.getInstance().getApiService().getNodeUpdate(map).compose(RxSchedulers.<NodeDataManageItem>switchThread());
    }

}
