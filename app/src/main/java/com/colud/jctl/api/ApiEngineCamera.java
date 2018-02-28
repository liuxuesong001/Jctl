package com.colud.jctl.api;


import com.colud.jctl.JctlApplication;
import com.colud.jctl.api.factory.GsonDConverterFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


/**
 * Created by Nicholas on 2016/10/30.
 */

public class ApiEngineCamera {

    private volatile static ApiEngineCamera apiEngine;


    private Retrofit retrofit;

    private ApiEngineCamera(int hostType) {

        //日志拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //缓存
        int size = 1024 * 1024 * 100;
        File cacheFile = new File(JctlApplication.getContext().getCacheDir(), "OkHttpCache");
        Cache cache = new Cache(cacheFile, size);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .addNetworkInterceptor(new NetworkInterceptor())
                .addInterceptor(loggingInterceptor)
                .cache(cache)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.getHost(hostType))
                .client(client)
                .addConverterFactory(GsonDConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                //                .addConverterFactory(StringConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                //String转换器
                .build();


    }


    public static ApiEngineCamera getInstance() {
        if (apiEngine == null) {

            synchronized (ApiEngineCamera.class) {
                if (apiEngine == null) {

                    apiEngine = new ApiEngineCamera(HostType.TYPE_CAMENRA);
                }
            }
        }
        return apiEngine;
    }

    public ApiService getApiService() {
        return retrofit.create(ApiService.class);
    }

}
