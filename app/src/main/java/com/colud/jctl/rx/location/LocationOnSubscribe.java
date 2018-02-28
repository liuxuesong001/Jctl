package com.colud.jctl.rx.location;

import android.content.Context;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.colud.jctl.utils.location.LocationClientBaidu;

import rx.Observable;
import rx.Subscriber;

/**
 * 立即定位
 * Created by Jcty on 2017/3/30.
 */

public class LocationOnSubscribe implements Observable.OnSubscribe<BDLocation> {
    private final Context context;

    public LocationOnSubscribe(Context context) {
        this.context = context;
    }

    @Override
    public void call(final Subscriber<? super BDLocation> subscriber) {
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
