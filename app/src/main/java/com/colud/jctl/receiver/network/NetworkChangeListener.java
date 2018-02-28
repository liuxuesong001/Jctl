package com.colud.jctl.receiver.network;

/**
 * @ explain:
 * @ author：xujun on 2016/10/30 15:05
 * @ email：gdutxiaoxu@163.com
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.util.Log;

import com.colud.jctl.JctlApplication;
import com.socks.library.KLog;

/**
 * 网络改变监控广播
 * <p>
 * 监听网络的改变状态,只有在用户操作网络连接开关(wifi,mobile)的时候接受广播,
 * 然后对相应的界面进行相应的操作，并将 状态 保存在我们的APP里面
 * <p>
 * <p>
 * Created by xujun
 */
public class NetworkChangeListener extends BroadcastReceiver {

    public static final String TAG1 = "liusong";

    @Override
    public void onReceive(Context context, Intent intent) {
        State wifiState = null;
        State mobileState = null;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context
                .CONNECTIVITY_SERVICE);
        wifiState = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        mobileState = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();

        KLog.d(TAG1,
                "wifi状态:" + wifiState + "\n mobile状态:" + mobileState);

        if (wifiState != null && mobileState != null
                && State.CONNECTED != wifiState
                && State.CONNECTED == mobileState) {// 手机网络连接成功  
            KLog.d(TAG1, "手机2g/3g/4g网络连接成功");

            JctlApplication.getAppInstance().setMobile(true);
            JctlApplication.getAppInstance().setWifi(false);
            JctlApplication.getAppInstance().setConnected(true);

        } else if (wifiState != null && State.CONNECTED == wifiState) {// 无线网络连接成功  
            Log.d(TAG1, "无线网络连接成功");

            JctlApplication.getAppInstance().setMobile(false);
            JctlApplication.getAppInstance().setWifi(true);
            JctlApplication.getAppInstance().setConnected(true);

        } else if (wifiState != null && mobileState != null
                && State.CONNECTED != wifiState
                && State.CONNECTED != mobileState) {// 手机没有任何的网络  
            KLog.d(TAG1, "手机没有任何的网络");

            JctlApplication.getAppInstance().setMobile(false);
            JctlApplication.getAppInstance().setWifi(false);
            JctlApplication.getAppInstance().setConnected(false);

        }

    }

}  
