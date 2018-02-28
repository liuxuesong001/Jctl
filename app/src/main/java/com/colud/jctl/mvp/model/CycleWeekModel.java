package com.colud.jctl.mvp.model;

import com.colud.jctl.api.ApiEngine;
import com.colud.jctl.bean.CycleWeekBean;
import com.colud.jctl.mvp.contract.CycleWeekContract;
import com.colud.jctl.rx.RxSchedulers;

import java.util.Map;

import rx.Observable;

/**
 * Created by Jcty on 2017/3/11.
 */

public class CycleWeekModel implements CycleWeekContract.Model {


    @Override
    //	public Observable<CycleWeekBean> getCycleWeekData(Map<String,String>date, Map<String, String[]> map) {
    public Observable<CycleWeekBean> getCycleWeekData(String cycle, Map<String, String> date, String[] week) {
        if (cycle.equals("on")) {
            return ApiEngine.getInstance().getApiService().getCycleWeekOn(date, week).compose(RxSchedulers.<CycleWeekBean>switchThread());
        } else {
            return ApiEngine.getInstance().getApiService().getCycleWeekOff(date, week).compose(RxSchedulers.<CycleWeekBean>switchThread());
        }
    }
}
