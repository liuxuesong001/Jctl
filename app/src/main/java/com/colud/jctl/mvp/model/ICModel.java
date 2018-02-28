package com.colud.jctl.mvp.model;

import com.colud.jctl.api.ApiEngine;
import com.colud.jctl.bean.ICItem;
import com.colud.jctl.mvp.contract.ICContract;
import com.colud.jctl.rx.RxSchedulers;

import java.util.Map;

import rx.Observable;

/**
 * Created by Jcty on 2017/3/11.
 */

public class ICModel implements ICContract.Model {


    @Override
    public Observable<ICItem> getICItem(String state, Map<String, String> map, double max, double min) {
        if (state != null && state.equals("update")) {
            return ApiEngine.getInstance().getApiService().getICUpdateItem(
                    map, max, min
            ).compose(RxSchedulers.<ICItem>switchThread());
        } else if (state != null && state.equals("del")) {
            return ApiEngine.getInstance().getApiService().getICDeleteItem(
                    map
            ).compose(RxSchedulers.<ICItem>switchThread());
        } else {
            return ApiEngine.getInstance().getApiService().getICItem(
                    map, max, min
            ).compose(RxSchedulers.<ICItem>switchThread());
        }
        //		getICDeleteItem
    }
}
