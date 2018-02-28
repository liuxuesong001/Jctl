package com.colud.jctl.mvp.presenter;

import android.content.Context;

import com.baidu.location.BDLocation;
import com.colud.jctl.bean.WeatherHome;
import com.colud.jctl.mvp.contract.SplashContract;
import com.colud.jctl.mvp.model.SplashModel;
import com.colud.jctl.utils.location.LocationUtil;
import com.google.gson.JsonParseException;
import com.socks.library.KLog;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import rx.Subscriber;
import rx.Subscription;

/**
 *
 * Created by Jcty on 2017/3/11.
 */

public class SplashPresenter extends SplashContract.Presenter {


    public SplashPresenter(SplashContract.View view) {
        mView = view;
        mModel = new SplashModel();
    }

    @Override
    public void getLocation(Context context) {
        Subscription s = mModel.setLocation().subscribe(new Subscriber<BDLocation>() {

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.onLocatedFail(null);
                onCompleted();
            }

            @Override
            public void onNext(BDLocation bdLocation) {
                //				mView.onSucceedHand(bdLocation);
                if (LocationUtil.isLocationResultEffective(bdLocation)) {
                    //					onLocatedSuccess(bdLocation);
                    mView.onSucceedLocation(bdLocation);
                } else {
                    mView.onLocatedFail(bdLocation);
                }
            }
        });
        addSubscribe(s);
    }

    @Override
    public void locateLastKnown(Context context) {
        Subscription s = mModel.locateLastKnown().subscribe(new Subscriber<BDLocation>() {

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                //				mView.onFail(e.getMessage());
                //				onCompleted();
                mView.onLocatedFail(null);
                onCompleted();
            }

            @Override
            public void onNext(BDLocation bdLocation) {
                //				mView.onSucceedHand(bdLocation);
                if (LocationUtil.isLocationResultEffective(bdLocation)) {
                    //										mView.onLocatedSuccess(bdLocation);
                    mView.onSucceedLocation(bdLocation);
                } else {
                    mView.onLocatedFail(bdLocation);
                }
            }
        });
        addSubscribe(s);
    }


    @Override
    public void getHomeTemp() {
        Subscription s = mModel.setHomeTemp().subscribe(new Subscriber<WeatherHome>() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof JsonParseException) {
                    mView.onFailure("数据异常", e);
                } else if (e instanceof IOException) {
                    mView.onFailure("网络异常", e);
                } else if (e instanceof TimeoutException) {
                    mView.onFailure("网络异常", e);
                } else {
                    mView.onFail(e.getMessage());
                }
                KLog.e("天气数据：" + e.getMessage());
                onCompleted();
            }

            @Override
            public void onNext(WeatherHome homeTemp) {
                    mView.onSuccedTemp(homeTemp);
            }
        });
        addSubscribe(s);
    }

}
