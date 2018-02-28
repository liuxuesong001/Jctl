package com.colud.jctl.rx;

import android.content.Context;

import com.baidu.location.BDLocation;
import com.colud.jctl.rx.location.LocationLateKnownOnSubscribe;
import com.colud.jctl.rx.location.LocationOnSubscribe;

import rx.Observable;

/**
 * Created by Jcty on 2017/3/30.
 */

public class RxLocation {

    private static RxLocation instance = new RxLocation();

    private RxLocation() {

    }

    public static RxLocation get() {
        return instance;
    }

    public Observable<BDLocation> locate(Context context) {
        return Observable.create(new LocationOnSubscribe(context));
    }

    public Observable<BDLocation> locateLastKnown(Context context) {
        return Observable.create(new LocationLateKnownOnSubscribe(context));
    }

}
