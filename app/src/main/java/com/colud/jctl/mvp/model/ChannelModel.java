package com.colud.jctl.mvp.model;

import com.colud.jctl.api.ApiEngineCamera;
import com.colud.jctl.bean.ChannelsBean;
import com.colud.jctl.mvp.contract.ChannelContract;
import com.colud.jctl.rx.RxSchedulers;
import com.colud.jctl.utils.GsonUtil;
import com.colud.jctl.utils.GsonUtils;

import java.util.Map;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Jcty on 2017/3/11.
 */

public class ChannelModel implements ChannelContract.Model {


    @Override
    public Observable<ChannelsBean> getChannelData(Map<String, Object> map) {
        return ApiEngineCamera.getInstance().getApiService().getChannelData(map).map(new Func1<ChannelsBean, ChannelsBean>() {
            @Override
            public ChannelsBean call(ChannelsBean data) {
                if (data != null) {
                    String jsonObject = GsonUtils.newInstance().toJson(data.getEasyDarwin().getBody());
                    if (jsonObject != null) {
                        ChannelsBean.EasyDarwinBean.BodyBean info = GsonUtil.GsonToObject(jsonObject, ChannelsBean.EasyDarwinBean.BodyBean.class);
                        data.getEasyDarwin().setBody(info);
                    }
                }
                return data;
            }
        }).compose(RxSchedulers.<ChannelsBean>switchThread());
    }

    @Override
    public Observable<ChannelsBean> getPTZ(Map<String, Object> map) {
        return ApiEngineCamera.getInstance().getApiService().getPtzControl(map).map(new Func1<ChannelsBean, ChannelsBean>() {
            @Override
            public ChannelsBean call(ChannelsBean data) {
                if (data != null) {
                    String jsonObject = GsonUtils.newInstance().toJson(data.getEasyDarwin().getBody());
                    if (jsonObject != null) {
                        ChannelsBean.EasyDarwinBean.HeaderBean info = GsonUtil.GsonToObject(jsonObject, ChannelsBean.EasyDarwinBean.HeaderBean.class);
                        data.getEasyDarwin().setHeader(info);
                    }
                }
                return data;
            }
        }).compose(RxSchedulers.<ChannelsBean>switchThread());
    }
}
