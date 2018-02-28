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

public class ApiEngine {

    private volatile static ApiEngine apiEngine;


    private Retrofit retrofit;

    private ApiEngine(int hostType) {

        //日志拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //缓存
        int size = 1024 * 1024 * 100;
        File cacheFile = new File(JctlApplication.getContext().getCacheDir(), "OkHttpCache");
        Cache cache = new Cache(cacheFile, size);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(40, TimeUnit.SECONDS)
                .writeTimeout(12, TimeUnit.SECONDS)
                .writeTimeout(12, TimeUnit.SECONDS)
                .addNetworkInterceptor(new NetworkInterceptor())
                //                .addInterceptor(loggingInterceptor)
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

    /**
     * 初始化 OkHttpClient
     *
     * @return
     */
    //    private static OkHttpClient getClient() {
    //        OkHttpClient.Builder client = new OkHttpClient().newBuilder();
    //
    //        client.interceptors().add(new Interceptor() {
    //            @Override
    //            public Response intercept(Chain chain) throws IOException {
    //                // 获取 Cookie
    //                Response resp = chain.proceed(chain.request());
    //                List<String> cookies = resp.headers("Set-Cookie");
    //                String cookieStr = "";
    //                if (cookies != null && cookies.size() > 0) {
    //                    for (int i = 0; i < cookies.size(); i++) {
    //                        cookieStr += cookies.get(i);
    //                    }
    //                    UserUtil.saveUserCookieId(cookieStr);
    //                }
    //                return resp;
    //            }
    //        });
    //        client.interceptors().add(new Interceptor() {
    //            @Override
    //            public Response intercept(Chain chain) throws IOException {
    //                // 设置 Cookie
    //                String cookieStr = UserUtil.getCookieId();
    //                if (LvEmptyUtil.isNotEmpty(cookieStr)) {
    //                    return chain.proceed(chain.request().newBuilder().header("Cookie", cookieStr).build());
    //                }
    //                return chain.proceed(chain.request());
    //            }
    //        });
    //        client.connectTimeout(TIMOUT, TimeUnit.MILLISECONDS);
    //        client.writeTimeout(TIMOUT, TimeUnit.MILLISECONDS);
    //        client.readTimeout(TIMOUT, TimeUnit.MILLISECONDS);
    //        return client.build();
    //}
    public static ApiEngine getInstance() {
        if (apiEngine == null) {

            synchronized (ApiEngine.class) {
                if (apiEngine == null) {

                    apiEngine = new ApiEngine(HostType.TYPE_COUNT);
                }
            }
        }
        return apiEngine;
    }

    public ApiService getApiService() {
        return retrofit.create(ApiService.class);
    }
    /**
     * 增加后台返回""和"null"的处理
     * 1.int=>0
     * 2.double=>0.00
     * 3.long=>0L
     *
     * @return
     */
    //    private static Gson gson=null;
    //    public static Gson buildGson() {
    //        if (gson== null) {
    //            gson = new GsonBuilder()
    //                    .registerTypeAdapter(Integer.class, new IntegerDefault0Adapter())
    //                    .registerTypeAdapter(int.class, new IntegerDefault0Adapter())
    //                    .registerTypeAdapter(Double.class, new DoubleDefault0Adapter())
    //                    .registerTypeAdapter(double.class, new DoubleDefault0Adapter())
    //                    .registerTypeAdapter(Long.class, new LongDefault0Adapter())
    //                    .registerTypeAdapter(long.class, new LongDefault0Adapter())
    //                    .registerTypeAdapter(String.class, new StringDefault0Adapter())
    //                    .create();
    //        }
    //        return gson;
    //    }
}
