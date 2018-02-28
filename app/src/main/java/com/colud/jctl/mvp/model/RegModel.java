package com.colud.jctl.mvp.model;

import com.colud.jctl.api.ApiEngine;
import com.colud.jctl.bean.FindBean;
import com.colud.jctl.bean.RegItem;
import com.colud.jctl.bean.SmsItem;
import com.colud.jctl.mvp.contract.RegContract;
import com.colud.jctl.rx.RxSchedulers;

import java.util.Map;

import rx.Observable;

/**
 * Created by Jcty on 2017/3/11.
 */

public class RegModel implements RegContract.Model {


    @Override
    public Observable<RegItem> getRegData(Map<String, String> map) {
        return ApiEngine.getInstance().getApiService().getRegData(map)
                .compose(RxSchedulers.<RegItem>switchThread());
    }

    @Override
    public Observable<SmsItem> getSmsData(Map<String, String> map) {
        return ApiEngine.getInstance().getApiService().getSendSms(map)
                .compose(RxSchedulers.<SmsItem>switchThread());
    }

    @Override
    public Observable<FindBean> getFindData(Map<String, String> map) {
        return ApiEngine.getInstance().getApiService().sendSmsFind(map)
                .compose(RxSchedulers.<FindBean>switchThread());
    }
}
