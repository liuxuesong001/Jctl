package com.colud.jctl.mvp.model;

import com.baidu.location.BDLocation;
import com.colud.jctl.JctlApplication;
import com.colud.jctl.api.ApiEngine;
import com.colud.jctl.bean.WeatherHome;
import com.colud.jctl.mvp.contract.SplashContract;
import com.colud.jctl.rx.RxSchedulers;
import com.colud.jctl.rx.location.LocationLateKnownOnSubscribe;
import com.colud.jctl.rx.location.LocationOnSubscribe;

import rx.Observable;
import rx.functions.Func1;

import static com.colud.jctl.api.KeyConstants.HOME_TEMP;


/**
 *
 * Created by Jcty on 2017/3/11.
 */

public class SplashModel implements SplashContract.Model {

    private String cityName = null;

    @Override
    public Observable<BDLocation> setLocation() {

        return Observable.create(new LocationOnSubscribe(JctlApplication.getContext()));
    }


    @Override
    public Observable<BDLocation> locateLastKnown() {
        return Observable.create(new LocationLateKnownOnSubscribe(JctlApplication.getContext()));
    }

    @Override
    public Observable<WeatherHome> setHomeTemp() {
        return setLocation().concatMap(new Func1<BDLocation, Observable<? extends WeatherHome>>() {
            @Override
            public Observable<? extends WeatherHome> call(BDLocation bdLocation) {
                if (bdLocation != null) {
                    String str = bdLocation.getCity();
                    if (str != null)
                    {
                        cityName = str.substring(0, str.indexOf("市"));
                    }
                    if (cityName==null)
                    {
                        return null;
                    } else {
                        return ApiEngine.getInstance().getApiService().getWeatherHomeTemp(cityName)
                                .map(new Func1<WeatherHome, WeatherHome>() {
                                    @Override
                                    public WeatherHome call(WeatherHome home) {
                                        JctlApplication.getCache().remove(HOME_TEMP);
                                        if (home != null) {
                                            JctlApplication.getCache().put(HOME_TEMP, home);
                                        }
                                        return home;
                                    }
                                });
                    }

                } else {
                    return locateLastKnown().concatMap(new Func1<BDLocation, Observable<? extends WeatherHome>>() {
                        @Override
                        public Observable<? extends WeatherHome> call(BDLocation bdLocation) {
                            if (bdLocation != null) {
                                cityName = bdLocation.getCity().substring(bdLocation.getCity().indexOf("市"));
                            }
                            if (cityName==null)
                            {
                                return null;
                            } else {
                                return ApiEngine.getInstance().getApiService().getWeatherHomeTemp(cityName).map(new Func1<WeatherHome, WeatherHome>() {
                                    @Override
                                    public WeatherHome call(WeatherHome home) {
                                        JctlApplication.getCache().remove(HOME_TEMP);
                                        if (home != null) {
                                            JctlApplication.getCache().put(HOME_TEMP, home);

                                        }
                                        return home;
                                    }
                                });
                            }
                        }
                    });
                }
            }
        })
                .compose(RxSchedulers.<WeatherHome>switchThread());

    }

}
