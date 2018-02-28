package com.colud.jctl.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.colud.jctl.rx.event.MyEvent;

/**
 *
 * Created by Jcty on 2017/5/4.
 */

public class BroadCastManager {

    private static BroadCastManager broadCastManager;

    /**
     * @return
     */
    public static BroadCastManager getInstance() {
        if (broadCastManager == null) {
            synchronized (MyEvent.class) {
                if (broadCastManager == null) {
                    broadCastManager = new BroadCastManager();
                }
            }
        }
        return broadCastManager;
    }
    //注册广播接收者
    public void registerReceiver(Context context, BroadcastReceiver receiver, IntentFilter filter) {
        context.registerReceiver(receiver, filter);
    }

    //注销广播接收者
    public void unregisterReceiver(Context context, BroadcastReceiver receiver) {
        context.unregisterReceiver(receiver);
    }

    //发送广播
    public void sendBroadCast(Context context, Intent intent) {
        context.sendBroadcast(intent);
    }
}
