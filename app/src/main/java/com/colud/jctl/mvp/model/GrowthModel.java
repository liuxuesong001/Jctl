package com.colud.jctl.mvp.model;

import android.text.TextUtils;

import com.colud.jctl.api.ApiEngine;
import com.colud.jctl.bean.GRItem;
import com.colud.jctl.mvp.contract.GrowthContract;
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

public class GrowthModel implements GrowthContract.Model {

    private List<String> timeList = new ArrayList<>();
    private List<Float> contentList = new ArrayList<>();


    @Override
    public Observable<GRItem> getGR(Map<String, String> map) {
        return ApiEngine.getInstance().getApiService().getGRItem(map)
                .map(new Func1<GRItem, GRItem>() {
                    @Override
                    public GRItem call(GRItem item) {
                        if (item != null && "1".equals(item.getFlag())) {
                            String jsonObject = GsonUtils.newInstance().toJson(item.getInfo());
                            if (!jsonObject.equals("null") && !TextUtils.isEmpty(jsonObject)) {
                                //							if (item.getNode().contains(item.getName())){
                                //
                                //							}
                                JsonParser parser = new JsonParser();
                                JsonArray jsonArray = parser.parse(jsonObject).getAsJsonArray();
                                for (JsonElement user : jsonArray) {
                                    //使用GSON，直接转成Bean对象
                                    GRItem.InfoBean data = GsonUtils.newInstance().fromJson(user, GRItem.InfoBean.class);
                                    item.setFlagBean(data);
                                }
                                KLog.json(jsonObject);
                                String time = GsonUtils.newInstance().toJson(item.getFlagBean().getAddTime());
                                if (time != null && !TextUtils.isEmpty(time)) {
                                    JsonParser parsera = new JsonParser();
                                    JsonArray jstime = parsera.parse(time).getAsJsonArray();
                                    timeList.clear();
                                    for (JsonElement user : jstime) {
                                        //使用GSON，直接转成Bean对象
                                        String ad = GsonUtils.newInstance().fromJson(user, String.class);
                                        timeList.add(ad);
                                    }
                                }
                                item.setAddTime(timeList);
                                KLog.json(time);
                                String content = GsonUtils.newInstance().toJson(item.getFlagBean().getContent());
                                if (content != null && !TextUtils.isEmpty(content)) {
                                    JsonParser parsera = new JsonParser();
                                    JsonArray jacontent = parsera.parse(content).getAsJsonArray();
                                    contentList.clear();
                                    for (JsonElement user : jacontent) {
                                        //使用GSON，直接转成Bean对象
                                        Float ad = GsonUtils.newInstance().fromJson(user, Float.class);
                                        contentList.add(ad);
                                    }
                                }
                                item.setContent(contentList);
                                KLog.json(content);
                            }

                        }
                        return item;
                    }
                }).compose(RxSchedulers.<GRItem>switchThread());
    }
}
