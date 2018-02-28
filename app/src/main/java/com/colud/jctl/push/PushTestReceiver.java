package com.colud.jctl.push;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;

import com.baidu.android.pushservice.PushMessageReceiver;
import com.colud.jctl.JctlApplication;
import com.colud.jctl.api.KeyConstants;
import com.colud.jctl.bean.EarlyItme;
import com.colud.jctl.receiver.BroadCastManager;
import com.colud.jctl.ui.activity.OffOnActivity;
import com.colud.jctl.utils.GsonUtil;
import com.colud.jctl.utils.NotificationUtils;
import com.jctl.colud.R;
import com.socks.library.KLog;

import java.util.Date;
import java.util.List;


/**
 * 百度推送SDK
 * Created by Jcty on 2017/4/29.
 */

public class PushTestReceiver extends PushMessageReceiver {

    private PendingIntent pendingIntent;

    private NotificationUtils utils;

    @Override
    public void onBind(Context context, int errorCode, String appid, String userId, String channelId, String requestId) {
        String responseString = "onBind errorCode=" + errorCode + " appid="
                + appid + " userId=" + userId + " channelId=" +
                channelId
                + " requestId=" + requestId;
        if (!TextUtils.isEmpty(channelId)) {
            JctlApplication.getCache().put(KeyConstants.KAY_CHANNELID, channelId);
        }
        //		KLog.d(responseString);
    }

    @Override
    public void onUnbind(Context context, int i, String s) {
        //        KLog.d(s);
    }

    @Override
    public void onSetTags(Context context, int i, List<String> list, List<String> list1, String s) {
        //        KLog.d(s);
    }

    @Override
    public void onDelTags(Context context, int i, List<String> list, List<String> list1, String s) {
        //        KLog.d(s);
    }

    @Override
    public void onListTags(Context context, int i, List<String> list, String s) {
        //        KLog.d(s);
    }

    @Override
    public void onMessage(Context context, String message, String customContentString) {
        //        String messageString = "透传消息message=" + message + " customContentString="+ customContentString;
        //        KLog.d(messageString);
        //通过singleid 推送给唯一用户
        String singleID = JctlApplication.getCache().getAsString(KeyConstants.KEY_SINGLEID);

        String userId = JctlApplication.getCache().getAsString(KeyConstants.USER_NAME);

        if (!TextUtils.isEmpty(message) && !message.equals("null") && singleID != null) {
            KLog.json(message);
            //使用GSON，直接转成Bean对象
            EarlyItme earlay = GsonUtil.GsonToObject(message, EarlyItme.class);
            if (earlay != null && singleID.equals(earlay.getSingleId())) {
                /**
                 * 通知栏
                 */
                Intent intenta = new Intent(context, OffOnActivity.class);
                pendingIntent = PendingIntent.getActivity(context, (int) SystemClock.uptimeMillis(), intenta, PendingIntent.FLAG_UPDATE_CURRENT);
                utils = new NotificationUtils(context, 1001);
                utils.sendMoreLineNotification("智慧农业", "预警", earlay.getMsg(), R.drawable.jctl_launcher, pendingIntent, true, true, true);

                //更新底部推送数量
                Intent intent = new Intent(KeyConstants.MESSAGE_INFO);
                intent.putExtra("earlay", earlay);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

                //刷新列表
                Intent msg = new Intent();
                msg.putExtra("pushMsg", earlay);
                msg.setAction(KeyConstants.MESSAGE_YUJING);
                BroadCastManager.getInstance().sendBroadCast(context, msg);

                if (userId != null && !userId.equals("")) {
                    EarlyItme earlyItem = new EarlyItme();
                    earlyItem.setMsg(earlay.getMsg());
                    earlyItem.setUserId(userId);
                    earlyItem.setSingleId(earlay.getSingleId());
                    earlyItem.setDate(earlay.getDate());
                    earlyItem.setOpenFlag(earlay.getOpenFlag());
                    earlyItem.setFarmland_name(earlay.getFarmland_name());
                    earlyItem.setFarmland_num(earlay.getFarmland_num());
                    earlyItem.setStatus(earlay.getStatus());
                    earlyItem.setNode_num(earlay.getNode_num());
                    earlyItem.setPublishDate(new Date());
                    earlyItem.saveThrows();
                }
            }
            //            //更新底部推送数量
            //            Intent intent = new Intent(KeyConstants.MESSAGE_INFO);
            //            intent.putExtra("earlay",  earlay);
            //            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);


        }

        // 自定义内容获取方式，mykey和myvalue对应透传消息推送时自定义内容中设置的键和值
        //		if (customContentString != null & customContentString != "") {
        //			JSONObject customJson = null;
        //			try {
        //				customJson = new JSONObject(customContentString);
        //				String myvalue = null;
        //				if (!customJson.isNull("mykey")) {
        //					myvalue = customJson.getString("mykey");
        //				}
        //				KLog.d(myvalue);
        //			} catch (JSONException e) {
        //				e.printStackTrace();
        //			}
        //		}
    }

    @Override
    public void onNotificationClicked(Context context, String s, String s1, String s2) {
        KLog.d(s);

    }

    /**
     * @param context
     * @param s
     * @param s1
     * @param s2
     */
    @Override
    public void onNotificationArrived(Context context, String s, String s1, String s2) {
        KLog.d(s);
    }

    private void updateContent(Context context, String content) {
        //		Log.d(TAG, "updateContent");
        //		String logText = "" + Utils.logStringCache;
        //
        //		if (!logText.equals("")) {
        //			logText += "\n";发送通知兰
        //		}
        //
        //		SimpleDateFormat sDateFormat = new SimpleDateFormat("HH-mm-ss");
        //		logText += sDateFormat.format(new Date()) + ": ";
        //		logText += content;
        //
        //		Utils.logStringCache = logText;
        //
        //		Intent intent = new Intent();
        //		intent.setClass(context.getApplicationContext(), PushDemoActivity.class);
        //		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //		context.getApplicationContext().startActivity(intent);
    }
}
