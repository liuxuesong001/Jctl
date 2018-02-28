package com.colud.jctl.rx.listener;

import android.support.annotation.NonNull;

import com.baidu.location.BDLocation;
import com.colud.jctl.utils.location.LocationUtil;

import rx.Subscriber;

/**
 * Created by Jcty on 2017/3/30.
 */

public abstract class LocationSubscriber extends Subscriber<BDLocation> {
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(BDLocation bdLocation) {
        if (LocationUtil.isLocationResultEffective(bdLocation)) {
            onLocatedSuccess(bdLocation);
        } else {
            onLocatedFail(bdLocation);
        }
    }

    public abstract void onLocatedSuccess(@NonNull BDLocation bdLocation);

    public abstract void onLocatedFail(BDLocation bdLocation);
}
