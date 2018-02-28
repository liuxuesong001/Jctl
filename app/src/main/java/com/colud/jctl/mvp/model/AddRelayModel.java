package com.colud.jctl.mvp.model;

import android.text.TextUtils;

import com.colud.jctl.api.ApiEngine;
import com.colud.jctl.bean.FarmManageBean;
import com.colud.jctl.mvp.contract.AddRelayContract;
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

public class AddRelayModel implements AddRelayContract.Model {

    private List<String> sList = new ArrayList<>();
    private List<String> idList = new ArrayList<>();

    @Override
    public Observable<FarmManageBean> getAddRelay(Map<String, String> map) {
        return ApiEngine.getInstance().getApiService().getAddRelay(map).compose(RxSchedulers.<FarmManageBean>switchThread());
    }

    @Override
    public Observable<FarmManageBean> getRelayFarmList(Map<String, String> map) {
        return ApiEngine.getInstance().getApiService().getAddRelayFarmList(map).map(new Func1<FarmManageBean, FarmManageBean>() {
            @Override
            public FarmManageBean call(FarmManageBean data) {
                if (data != null && data.getFlag() == 1) {
                    sList.clear();
                    idList.clear();
                    if (data.getInfo() != null && data.getInfo().size() > 0) {
                        String jsonObject = GsonUtils.newInstance().toJson(data.getInfo());
                        if (jsonObject != null && !TextUtils.isEmpty(jsonObject)) {
                            JsonParser parser = new JsonParser();
                            JsonArray jsonArray = parser.parse(jsonObject).getAsJsonArray();
                            for (JsonElement user : jsonArray) {
                                //使用GSON，直接转成Bean对象
                                FarmManageBean.InfoBean info = GsonUtils.newInstance().fromJson(user, FarmManageBean.InfoBean.class);
                                sList.add(info.getName());
                                idList.add(info.getId());
                            }
                            data.setnList(sList);
                            data.setIdList(idList);
                            //							for (int i=0;i<mList.size();i++){
                            //								FarmManageBean.Node name=mList.get(i);
                            //								sList.add(name.getName());
                            //							}
                            KLog.json("添加中继(所有农场数据)" + jsonObject);
                        }
                    }
                }
                return data;
            }
        }).compose(RxSchedulers.<FarmManageBean>switchThread());
    }
}
