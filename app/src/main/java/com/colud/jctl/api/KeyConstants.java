package com.colud.jctl.api;

/**
 * 全部的key
 * Created by Jcty on 2017/4/14.
 */

public class KeyConstants {
    //flag
    public static boolean LGOIN_IS = false;
    //newState
    public static boolean NEW_STATE = false;
    //login
    public static boolean LGOIN_IS_STATE = false;

    //更新的状态
    public static boolean isDownloading = true;

    /*推送Key*/
    public static final String MESSAGE_INFO = "pushMessage";
    /*预警*/
    public static final String MESSAGE_YUJING = "pushYj";

    /*startActivityForResult*/
    public static int INTENT_RESULT = 1004;

    //	public static int okHttpTimeou =12;

    //百度推送 channelId
    public static final String KAY_CHANNELID = "channelId";

    //天气的key
    public static final String HOME_TEMP = "homeTemp";
    //用户数据
    public static final String USER_ITEM = "loginData";
    //UserName 用户名
    public static final String USER_NAME = "userName";
    /*singleId*/
    public static String USER_SINGLEID= null;
    //SingleId
    public static final String KEY_SINGLEID = "singleId";
    //注册的密码
    public static final String KAY_REG = "regData";
    //新闻动态
    public static final String KAY_NEWINFO = "newinfoData";
    //banenr
    public static final String KAY_BANENR = "banenrData";
    //banenrUrl
    public static final String KAY_BANENRURl = "banenrUrl";
    //市场动态
    public static final String KAY_MARKET = "marketData";
    //缓存id
    public static final String KAY_MARKET_ID = "marketDataId";
    //农场数据
    public static final String KAY_FARM_MANAGE = "farmManagData";
    //设备管理数据列表
    public static final String KAY_FACILITY_MANAGE = "FacilityManagData";
    //地址数据
    public static final String KAY_ADDFARM_ADDRESS = "addRessData";
    //节点数据管理
    public static final String KAY_NODEDATA_MANAGE = "nodeManage";
    //
    public static final String KAY_FARMLIST_MANAGE = "nodeFarmList";
    //节点数据详情
    public static final String KAY_NODE_DETAIL = "nodeDetail";
    //头像
    public static String KAY_ICON_URL = "iconUrl";
    //节点ID
    public static final String KAY_NODE_ID = "nodeId";
    //节点Node
    public static final String KAY_NODE = "node";

    /*传递数据*/
    public static final String NODE_INTENT = "nodeFragment";

    public static final String NODE_INTENT_MANAGE = "nodeManage";

    public static final String NEWLIST_INTENT = "newList";

    public static final String NODE_DATAILS_INTENT = "nodeDatails";

    public static final String NODE_MANAGE_INTENT = "nodeManage";
    //头像信息
    public static final String USER_PHOTO = "photo";
    /**/
    public static final String TEMP_INTENT = "tempFragment";

    /*头像*/
    public static String BITMAP_ICON = "bitmapIcon";

    /*推送数据*/
    public static String PUSH_EARLY_INFO = "earlyinfo";

    /*预计缓冲*/
    //    public static final String  EARLY_CACHE="earlycache";

    /*地步缓存数量*/
    //    public static final String  EARLY_COUNT="earlycount";

    //登录成功
    public static final int LOGIN_SUCCESS=1002;

    public static final int LOGIN_SUCCESS_EXIT=1003;
}
