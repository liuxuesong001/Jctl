package com.colud.jctl.mvp.model;

/**
 * Created by Jcty on 2017/3/7.
 */

public class WeatherDataModel implements WeatherDataModelApi {

    private static String JH_WEATHER_URL = "http://v.juhe.cn/weather/index";

    @Override
    public void requestWeatherData() {

        locationThenGetWeatherData();
    }

    @Override
    public void requestWeatherData(String cityName) {

    }

    @Override
    public void setRequestWeatherDataListener(RequestWeatherDataListener requestWeatherDataListener) {

    }

    private void locationThenGetWeatherData() {
        //        BDLocation bdLocation =
        //                DataCache.getInstance().get(DataCache.Key.BD_LOCATION, BDLocation.class);
        //        if (bdLocation == null){
        //            LocationHelper.getInstance().startLocation();
        //            LocationHelper.getInstance().setListener(Location ->){
        //                String cityname = bdLocation.getCity();
        //                if (!TextUtils.isEmpty(cityname)) {
        //                    VolleyRequestUtil.RequestGet(JctlApplication.getContext(), JH_WEATHER_URL, "tag",
        //                            new VolleyListenerInterface() {
        //                        @Override
        //                        public void onMySuccess(String result) {
        //
        //                        }
        //
        //                        @Override
        //                        public void onMyError(VolleyError error) {
        //
        //                        }
        //                    });
        //                }
        //            });
        //        }
        //                    location ->{
        //                LocationHelper.getInstance().stopLocation();
        //                String cityname = location.getCity();
        //                if (!TextUtils.isEmpty(cityname)) {
        //
        //                }
        //                DataCache.getInstance().add(DataCache.Key.BD_LOCATION, location); // 缓存定位信息
        //            });
        //        } else {
        //            String cityname = bdLocation.getCity();
        //            if (!TextUtils.isEmpty(cityname)) {
        //            }
        //        }
    }
}