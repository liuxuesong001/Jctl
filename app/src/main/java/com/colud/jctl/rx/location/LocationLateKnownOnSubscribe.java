package com.colud.jctl.rx.location;

import android.content.Context;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.colud.jctl.utils.location.LocationClientBaidu;
import com.colud.jctl.utils.location.LocationUtil;

import rx.Observable;
import rx.Subscriber;

/**
 * 获取最近一次定位信息
 * Created by Jcty on 2017/3/30.
 */

public class LocationLateKnownOnSubscribe implements Observable.OnSubscribe<BDLocation> {

    private final Context context;

    public LocationLateKnownOnSubscribe(Context context) {

        this.context = context;
    }


    @Override
    public void call(final Subscriber<? super BDLocation> subscriber) {
        BDLocation lateKnownLocation = LocationClientBaidu.get(context).getLateKnownLocation();
        if (LocationUtil.isLocationResultEffective(lateKnownLocation)) {
            subscriber.onNext(lateKnownLocation);
            subscriber.onCompleted();
        } else {
            BDLocationListener bdLocationListener = new BDLocationListener() {
                @Override
                public void onReceiveLocation(BDLocation bdLocation) {
                    subscriber.onNext(bdLocation);
                    subscriber.onCompleted();
                }

                @Override
                public void onConnectHotSpotMessage(String s, int i) {

                }
            };
            LocationClientBaidu.get(context).locate(bdLocationListener);
        }
    }
}
