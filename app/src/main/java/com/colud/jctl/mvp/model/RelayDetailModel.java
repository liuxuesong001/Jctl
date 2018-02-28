package com.colud.jctl.mvp.model;

import android.text.TextUtils;

import com.colud.jctl.api.ApiEngine;
import com.colud.jctl.bean.FarmManageBean;
import com.colud.jctl.bean.RelayDetailItem;
import com.colud.jctl.mvp.contract.RelayDetailContract;
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

public class RelayDetailModel implements RelayDetailContract.Model {

    private List<String> nList = new ArrayList<>();
    private List<String> idList = new ArrayList<>();

    @Override
    public Observable<RelayDetailItem> getRelayData(Map<String, String> map) {
        return ApiEngine.getInstance().getApiService().getRelayDetail(map).map(new Func1<RelayDetailItem, RelayDetailItem>() {
            @Override
            public RelayDetailItem call(RelayDetailItem data) {
                if (data != null && data.getFlag() == 1) {
                    nList.clear();
                    idList.clear();
                    if (data.getInfo() != null && data.getInfo().size() > 0) {
                        String jsonObject = GsonUtils.newInstance().toJson(data.getInfo());
                        if (jsonObject != null && !TextUtils.isEmpty(jsonObject)) {
                            JsonParser parser = new JsonParser();
                            JsonArray jsonArray = parser.parse(jsonObject).getAsJsonArray();
                            for (JsonElement user : jsonArray) {
                                //使用GSON，直接转成Bean对象
                                RelayDetailItem.InfoBean info = GsonUtils.newInstance().fromJson(user, RelayDetailItem.InfoBean.class);
                                nList.add(info.getName());
                                idList.add(info.getId());
                            }
                            data.setnList(nList);
                            data.setIdList(idList);
                        }
                    }
                }
                return data;
            }
        }).compose(RxSchedulers.<RelayDetailItem>switchThread());
    }

    @Override
    public Observable<RelayDetailItem> getRelayDelete(Map<String, String> map) {
        return ApiEngine.getInstance().getApiService().getRelayDelete(map).compose(RxSchedulers.<RelayDetailItem>switchThread());
    }

    @Override
    public Observable<FarmManageBean> getRelayUpdate(Map<String, String> map) {
        return ApiEngine.getInstance().getApiService().getAddRelay(map).compose(RxSchedulers.<FarmManageBean>switchThread());
    }
}
