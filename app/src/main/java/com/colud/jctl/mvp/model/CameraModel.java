package com.colud.jctl.mvp.model;

import com.colud.jctl.api.ApiEngineCamera;
import com.colud.jctl.bean.CameraBean;
import com.colud.jctl.mvp.contract.CameraContract;
import com.colud.jctl.rx.RxSchedulers;
import com.colud.jctl.utils.GsonUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Jcty on 2017/3/11.
 */

public class CameraModel implements CameraContract.Model {


    private List<CameraBean.EasyDarwinBean.BodyBean.ChannelsBean> list = new ArrayList<>();


    @Override
    public Observable<CameraBean> getCameraData() {
        return ApiEngineCamera.getInstance().getApiService().getCameraData().map(
                new Func1<CameraBean, CameraBean>() {
                    @Override
                    public CameraBean call(CameraBean cameraBean) {
                        if (cameraBean != null) {
                            String jsonObject = GsonUtils.newInstance().toJson(cameraBean.getEasyDarwin().getBody().getChannels());
                            if (!jsonObject.equals("null") && jsonObject != null) {
                                JsonParser parser = new JsonParser();
                                JsonArray jsonArray = parser.parse(jsonObject).getAsJsonArray();
                                for (JsonElement user : jsonArray) {
                                    //使用GSON，直接转成Bean对象
                                    CameraBean.EasyDarwinBean.BodyBean.ChannelsBean info = GsonUtils.newInstance().fromJson(user, CameraBean.EasyDarwinBean.BodyBean.ChannelsBean.class);
                                    list.add(info);
                                    //									CameraBean.EasyDarwinBean.BodyBean.ChannelsBean list = GsonUtils.GsonToList(jsonObject,user);
                                }
                                cameraBean.getEasyDarwin().getBody().setChannels(list);
                            }
                        }
                        return cameraBean;
                    }
                }
        )
                .compose(RxSchedulers.<CameraBean>switchThread());
    }
}
