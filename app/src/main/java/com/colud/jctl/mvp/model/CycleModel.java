package com.colud.jctl.mvp.model;

import com.colud.jctl.api.ApiEngine;
import com.colud.jctl.bean.CycleUpdateBean;
import com.colud.jctl.mvp.contract.CycleContract;
import com.colud.jctl.rx.RxSchedulers;

import java.util.Map;

import rx.Observable;

/**
 * Created by Jcty on 2017/3/11.
 */

public class CycleModel implements CycleContract.Model {


    //	@Override
    //	public Observable<NodeCollectionCycle> setCycleData(Map<String, String> map) {
    //		return ApiEngine.getInstance().getApiService().getNodeCollectionCycle(map).compose(RxSchedulers.<NodeCollectionCycle>switchThread());
    //	}

    @Override
    public Observable<CycleUpdateBean> setCycleUpdate(Map<String, String> map) {
        return ApiEngine.getInstance().getApiService().getCycleUpdate(map).compose(RxSchedulers.<CycleUpdateBean>switchThread());
    }
}
