/*
 * Copyright (c) 2016 咖枯 <kaku201313@163.com | 3772304@qq.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.colud.jctl.api;

public class ApiConstants {

    //    private static final String BASE_WEATHER_URL = "http://v.juhe.cn/weather/";
    //    private static final String BASE_WEATHER_URL = "http://api.openweathermap.org/data/2.5/";
    private static final String JUHE_WEATHER_KEY = "4bc023ab708f16a623db7c39658f0d64";//全国天气预报key(聚合账号)
    private static final String JUHE_WEATHER_TYPE = "json";
    private static final int JUHE_WEATHER_FORMAT = 2;
    //  private static final String AQI_KEY = "8adc361636f47136eded19131d71d09e";//空气质量key(聚合账号)
    //    0d1474df7a6901fdec827f3fbe2fd2db

    //彩云天气地址
    public static final String CAYUN_URL="http://www.caiyunapp.com/h5/";

    //测试接口地址
    private static final String BASE_URL = "http://47.92.129.52:92/";
    //        private static final String BASE_URL="http://60.255.50.141:5562/";   //四川



    //摄像头接口
    public static final String CAMERA_URL = "http://www.e-unite.cn:10800/";

    //    private static final String CAMERA_URL="http://192.168.0.110:10800/";


    public final static String BASEPATH = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
    //PATH
    public static final String DOWNLOADPATH = BASEPATH + "/ZhnyClient/";

    //下载服务
    public static final String SERIVCE_DIALOG = "com.jctl.colud.DownloadApkServiceDialog";

    public static final String SERIVCE_NOTIFICATION = "com.jctl.colud.DownloadApkServiceNotification";


    /**
     * 获取对应的host
     *
     * @param hostType host类型
     * @return host
     */
    public static String getHost(int hostType) {
        String host;
        switch (hostType) {
            case HostType.TYPE_COUNT:
                host = BASE_URL;
                break;
            case HostType.TYPE_CAMENRA:
                host = CAMERA_URL;
                break;
            default:
                host = "";
                break;
        }
        return host;
    }

}
