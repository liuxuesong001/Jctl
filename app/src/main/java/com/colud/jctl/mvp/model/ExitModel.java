package com.colud.jctl.mvp.model;

import com.colud.jctl.api.ApiEngine;
import com.colud.jctl.api.KeyConstants;
import com.colud.jctl.bean.ExitItem;
import com.colud.jctl.bean.VersionUpdateBean;
import com.colud.jctl.mvp.contract.ExitContract;
import com.colud.jctl.rx.RxSchedulers;

import java.util.Map;

import rx.Observable;

/**
 * Created by Jcty on 2017/3/11.
 */

public class ExitModel implements ExitContract.Model {


    @Override
    public Observable<ExitItem> getExit() {
        return ApiEngine.getInstance().getApiService().getExit(KeyConstants.USER_SINGLEID).compose(RxSchedulers.<ExitItem>switchThread());
    }

    @Override
    public Observable<VersionUpdateBean> getUpdateV(Map<String, String> map) {
        return ApiEngine.getInstance().getApiService().getVeesionUpdate(map).compose(RxSchedulers.<VersionUpdateBean>switchThread());
    }
}
