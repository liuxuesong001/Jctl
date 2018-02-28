package com.colud.jctl.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import com.colud.jctl.api.ApiConstants;

/**
 * Wing_Li
 * 2016/4/15.
 */
public class NetStatusUtil {

    /**
     * 检测当前打开的网络类型是否WIFI
     *
     * @param context 上下文
     * @return 是否是Wifi上网
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context
                .CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    /**
     * 是否有网
     */
    public static boolean isConnected(Context context) {
        return netSatus(context) != NetState.NET_NO;
    }

    /**
     * 枚举网络状态 NET_NO：没有网络 NET_2G:2g网络 NET_3G：3g网络 NET_4G：4g网络 NET_WIFI：wifi
     * NET_UNKNOWN：未知网络
     */
    public static enum NetState {
        NET_NO, NET_2G, NET_3G, NET_4G, NET_WIFI, NET_UNKNOWN
    }

    /**
     * 判断当前是否网络连接
     *
     * @param context 上下文
     * @return 状态码
     */
    public static NetState netSatus(Context context) {
        NetState stateCode = NetState.NET_NO;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni != null && ni.isConnectedOrConnecting()) {
            switch (ni.getType()) {
                case ConnectivityManager.TYPE_WIFI:
                    stateCode = NetState.NET_WIFI;
                    break;
                case ConnectivityManager.TYPE_MOBILE:
                    switch (ni.getSubtype()) {
                        case TelephonyManager.NETWORK_TYPE_GPRS: // 联通2g
                        case TelephonyManager.NETWORK_TYPE_CDMA: // 电信2g
                        case TelephonyManager.NETWORK_TYPE_EDGE: // 移动2g
                        case TelephonyManager.NETWORK_TYPE_1xRTT:
                        case TelephonyManager.NETWORK_TYPE_IDEN:
                            stateCode = NetState.NET_2G;
                            break;
                        case TelephonyManager.NETWORK_TYPE_EVDO_A: // 电信3g
                        case TelephonyManager.NETWORK_TYPE_UMTS:
                        case TelephonyManager.NETWORK_TYPE_EVDO_0:
                        case TelephonyManager.NETWORK_TYPE_HSDPA:
                        case TelephonyManager.NETWORK_TYPE_HSUPA:
                        case TelephonyManager.NETWORK_TYPE_HSPA:
                        case TelephonyManager.NETWORK_TYPE_EVDO_B:
                        case TelephonyManager.NETWORK_TYPE_EHRPD:
                        case TelephonyManager.NETWORK_TYPE_HSPAP:
                            stateCode = NetState.NET_3G;
                            break;
                        case TelephonyManager.NETWORK_TYPE_LTE:
                            stateCode = NetState.NET_4G;
                            break;
                        default:
                            stateCode = NetState.NET_UNKNOWN;
                    }
                    break;
                default:
                    stateCode = NetState.NET_UNKNOWN;
            }

        }
        return stateCode;
    }

    /**
     * 2 * 获取版本号 3 * @return 当前应用的版本号 4
     */
    public static int getVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(),
                    0);
            String version = info.versionName;
            int versioncode = info.versionCode;
            return versioncode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取开发版本号
     *
     * @param context
     * @return
     */

    public static String getVersionCode(Context context) {
        StringBuilder builder = new StringBuilder();
        try {
            PackageInfo packInfo = getPackageInfo(context);
            builder.append(packInfo.versionCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    /**
     * 鑾峰彇鍖呬俊鎭�.
     *
     * @param context the context
     */
    public static PackageInfo getPackageInfo(Context context) {
        PackageInfo info = null;
        try {
            String packageName = context.getPackageName();
            info = context.getPackageManager().getPackageInfo(packageName, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }

    /**
     * 获取当前版本号
     *
     * @return
     * @throws Exception
     */
    public static String getVersionName(Context context) {
        StringBuilder builder = new StringBuilder("v");
        try {
            PackageInfo packInfo = getPackageInfo(context);
            builder.append(packInfo.versionName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }


    // 判断服务是否运行
    public static boolean isServiceWorked(Context context) {
        ActivityManager myManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : myManager
                .getRunningServices(Integer.MAX_VALUE)) {

            if (ApiConstants.SERIVCE_DIALOG
                    .equals(service.service.getClassName())) {
                return true;
            }
            if (ApiConstants.SERIVCE_NOTIFICATION
                    .equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
