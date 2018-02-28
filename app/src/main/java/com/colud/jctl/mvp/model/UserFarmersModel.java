package com.colud.jctl.mvp.model;

import com.colud.jctl.api.ApiEngine;
import com.colud.jctl.bean.FarmersBean;
import com.colud.jctl.mvp.contract.UserFarmersContract;
import com.colud.jctl.rx.RxSchedulers;

import java.util.Map;

import rx.Observable;

/**
 * Created by Jcty on 2017/3/11.
 */

public class UserFarmersModel implements UserFarmersContract.Model {

    @Override
    public Observable<FarmersBean> getUserFarmers(Map<String, String> map) {
        return ApiEngine.getInstance().getApiService().getFarmerManage(map)
                .compose(RxSchedulers.<FarmersBean>switchThread());
    }
}
