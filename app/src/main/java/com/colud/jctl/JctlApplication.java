package com.colud.jctl;

import android.content.Context;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.baidu.mapapi.SDKInitializer;
import com.colud.jctl.cache.ACache;
import com.colud.jctl.receiver.network.NetworkConnectChangedReceiver;
import com.jctl.colud.BuildConfig;
import com.socks.library.KLog;

import org.litepal.LitePalApplication;
import org.litepal.tablemanager.Connector;


/**
 * Application
 * Created by Jcty on 2017/3/1.
 */

public class JctlApplication extends LitePalApplication {


    public static Context context;

    public static JctlApplication app;
    /**
     * 维护Activity 的list
     */
    //    private static List<Activity> mActivitys = Collections.synchronizedList(new LinkedList<Activity>());
    /**
     * 缓存
     */
    public static ACache cache;

    private NetworkConnectChangedReceiver mNetworkConnectChangedReceiver;
    //表示是否连接
    public boolean isConnected;
    //    表示是否是移动网络
    public boolean isMobile;
    //    表示是否是WiFi
    public boolean isWifi;
    //    表示WiFi开关是否打开
    public boolean isEnablaWifi;
    //    表示移动网络数据是否打开
    public boolean isEnableMobile;


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        new ImplLitePalApplication().onCreate();
        initDBSQLite();
        PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY, "gdPsyG6Sga0u714WRoMcWGs3");
        app = this;
        context = getApplicationContext();
        SDKInitializer.initialize(context);
        KLog.init(BuildConfig.LOG_DEBUG, "LiuSong");
        initReceiver();


    }

    /**
     * 数据库初始化
     */
    private void initDBSQLite() {
        SQLiteDatabase db = Connector.getDatabase();

    }


    /**
     * 获取本地资源
     *
     * @return
     */
    public static Resources getAppResources() {

        return context.getResources();
    }

    public static Context getContext() {

        return context;
    }

    public static ACache getCache() {
        if (cache == null) {
            cache = ACache.get(context);
        }
        return cache;
    }

    public static JctlApplication getAppInstance() {

        return app;
    }

    private void initReceiver() {

        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        filter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        filter.addAction("android.net.wifi.STATE_CHANGE");
        mNetworkConnectChangedReceiver = new NetworkConnectChangedReceiver();
        registerReceiver(mNetworkConnectChangedReceiver, filter);


    }


    public boolean isConnected() {
        return isWifi || isMobile;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public boolean isMobile() {
        return isMobile;
    }

    public void setMobile(boolean mobile) {
        isMobile = mobile;
    }

    public boolean isWifi() {
        return isWifi;
    }

    public void setWifi(boolean wifi) {
        isWifi = wifi;
    }

    public boolean isEnablaWifi() {
        return isEnablaWifi;
    }

    public void setEnablaWifi(boolean enablaWifi) {
        isEnablaWifi = enablaWifi;
    }

    public boolean isEnableMobile() {
        return isEnableMobile;
    }

    public void setEnableMobile(boolean enableMobile) {
        isEnableMobile = enableMobile;
    }


    public class ImplLitePalApplication extends MultiDexApplication {
        @Override
        public void onCreate() {
            super.onCreate();

        }
    }
    //    /**
    //     * @param activity 作用说明 ：添加一个activity到管理里
    //     */
    //    public void pushActivity(Activity activity) {
    //        mActivitys.add(activity);
    //    }
    //
    //    /**
    //     * @param activity 作用说明 ：删除一个activity在管理里
    //     */
    //    public void popActivity(Activity activity) {
    //        mActivitys.remove(activity);
    //    }
    //
    //    /**
    //     * get current Activity 获取当前Activity（栈中最后一个压入的）
    //     */
    //    public static Activity currentActivity() {
    //        if (mActivitys == null||mActivitys.isEmpty()) {
    //            return null;
    //        }
    //        Activity activity = mActivitys.get(mActivitys.size()-1);
    //        return activity;
    //    }
    //
    //    /**
    //     * 结束当前Activity（栈中最后一个压入的）
    //     */
    //    public static void finishCurrentActivity() {
    //        if (mActivitys == null||mActivitys.isEmpty()) {
    //            return;
    //        }
    //        Activity activity = mActivitys.get(mActivitys.size()-1);
    //        finishActivity(activity);
    //    }
    //
    //    /**
    //     * 结束指定的Activity
    //     */
    //    public static void finishActivity(Activity activity) {
    //        if (mActivitys == null||mActivitys.isEmpty()) {
    //            return;
    //        }
    //        if (activity != null) {
    //            mActivitys.remove(activity);
    //            activity.finish();
    //            activity = null;
    //        }
    //    }
    //
    //    /**
    //     * 结束指定类名的Activity
    //     */
    //    public static void finishActivity(Class<?> cls) {
    //        if (mActivitys == null||mActivitys.isEmpty()) {
    //            return;
    //        }
    //        for (Activity activity : mActivitys) {
    //            if (activity.getClass().equals(cls)) {
    //                finishActivity(activity);
    //            }
    //        }
    //    }
    //
    //    /**
    //     * 按照指定类名找到activity
    //     *
    //     * @param cls
    //     * @return
    //     */
    //    public static Activity findActivity(Class<?> cls) {
    //        Activity targetActivity = null;
    //        if (mActivitys != null) {
    //            for (Activity activity : mActivitys) {
    //                if (activity.getClass().equals(cls)) {
    //                    targetActivity = activity;
    //                    break;
    //                }
    //            }
    //        }
    //        return targetActivity;
    //    }
    //
    //    /**
    //     * @return 作用说明 ：获取当前最顶部activity的实例
    //     */
    //    public Activity getTopActivity() {
    //        Activity mBaseActivity = null;
    //        synchronized (mActivitys) {
    //            final int size = mActivitys.size() - 1;
    //            if (size < 0) {
    //                return null;
    //            }
    //            mBaseActivity = mActivitys.get(size);
    //        }
    //        return mBaseActivity;
    //
    //    }
    //
    //    /**
    //     * @return 作用说明 ：获取当前最顶部的acitivity 名字
    //     */
    //    public String getTopActivityName() {
    //        Activity mBaseActivity = null;
    //        synchronized (mActivitys) {
    //            final int size = mActivitys.size() - 1;
    //            if (size < 0) {
    //                return null;
    //            }
    //            mBaseActivity = mActivitys.get(size);
    //        }
    //        return mBaseActivity.getClass().getName();
    //    }
    //
    //    /**
    //     * 结束所有Activity
    //     */
    //    public static void finishAllActivity() {
    //        if (mActivitys == null) {
    //            return;
    //        }
    //        for (Activity activity : mActivitys) {
    //            activity.finish();
    //        }
    //        mActivitys.clear();
    //    }
    //
    //    /**
    //     * 退出应用程序
    //     */
    //    public  static void appExit() {
    //        try {
    //            finishAllActivity();
    //            JctlApplication.getCache().remove(KeyConstants.HOME_TEMP);
    //        } catch (Exception e) {
    //        }
    //    }
    //
    //    private void registerActivityListener() {
    //        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
    //            registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
    //                @Override
    //                public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    //                    /**
    //                     *  监听到 Activity创建事件 将该 Activity 加入list
    //                     */
    //                    pushActivity(activity);
    //                    //                    AppManager.newInstance().addActivivty(activity);
    //
    //                }
    //
    //                @Override
    //                public void onActivityStarted(Activity activity) {
    //
    //                }
    //
    //                @Override
    //                public void onActivityResumed(Activity activity) {
    //
    //                }
    //
    //                @Override
    //                public void onActivityPaused(Activity activity) {
    //
    //                }
    //
    //                @Override
    //                public void onActivityStopped(Activity activity) {
    //
    //                }
    //
    //                @Override
    //                public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    //
    //                }
    //
    //                @Override
    //                public void onActivityDestroyed(Activity activity) {
    //                    if (null==mActivitys&&mActivitys.isEmpty()){
    //                        return;
    //                    }
    //                    if (mActivitys.contains(activity)){
    //                        /**
    //                         *  监听到 Activity销毁事件 将该Activity 从list中移除
    //                         */
    //                        popActivity(activity);
    //                    }
    //                }
    //            });
    //        }
    //    }
}
