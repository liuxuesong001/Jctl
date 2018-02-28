package com.colud.jctl.api;


import com.colud.jctl.bean.AddFarmItem;
import com.colud.jctl.bean.AddFieldData;
import com.colud.jctl.bean.AddFieldItem;
import com.colud.jctl.bean.BannerItem;
import com.colud.jctl.bean.BazaarItem;
import com.colud.jctl.bean.CameraBean;
import com.colud.jctl.bean.CapaCityItem;
import com.colud.jctl.bean.ChannelsBean;
import com.colud.jctl.bean.CycleUpdateBean;
import com.colud.jctl.bean.CycleWeekBean;
import com.colud.jctl.bean.EarlyItme;
import com.colud.jctl.bean.ExitItem;
import com.colud.jctl.bean.FacilityItem;
import com.colud.jctl.bean.FarmDetailItem;
import com.colud.jctl.bean.FarmManageBean;
import com.colud.jctl.bean.FarmersBean;
import com.colud.jctl.bean.FieldDetailItem;
import com.colud.jctl.bean.FieldManageItem;
import com.colud.jctl.bean.FindBean;
import com.colud.jctl.bean.GRItem;
import com.colud.jctl.bean.ICItem;
import com.colud.jctl.bean.IconItem;
import com.colud.jctl.bean.LastNodeDetailsBean;
import com.colud.jctl.bean.NewInfoItem;
import com.colud.jctl.bean.NodeCollectionCycle;
import com.colud.jctl.bean.NodeDataManageItem;
import com.colud.jctl.bean.NodeItem;
import com.colud.jctl.bean.NodeManageItem;
import com.colud.jctl.bean.OffOnItemManage;
import com.colud.jctl.bean.RegItem;
import com.colud.jctl.bean.RelayDetailItem;
import com.colud.jctl.bean.SmsItem;
import com.colud.jctl.bean.SplashBnBean;
import com.colud.jctl.bean.UserItem;
import com.colud.jctl.bean.VersionUpdateBean;
import com.colud.jctl.bean.Weather;
import com.colud.jctl.bean.WeatherHome;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by Nicholas on 2016/10/30.
 */

public interface ApiService {


    /**
     * 获取视频广场
     *
     * @return
     */
    @POST("api/v1/getchannels")
    Observable<CameraBean> getCameraData();


    /**
     * 获取视频单个通道
     *
     * @param map
     * @return
     */
    @POST("api/v1/getchannelstream")
    Observable<ChannelsBean> getChannelData(
            @QueryMap Map<String, Object> map
    );


    /**
     * 控制视频
     *
     * @param map
     * @return
     */
    @POST("api/v1/ptzcontrol")
    Observable<ChannelsBean> getPtzControl(
            @QueryMap Map<String, Object> map
    );

    @GET("weather")
        //天气实况
    Observable<Weather> getWeatherData(
            //            @Query("q")String cityname,
            @Query("lat") Double lat,
            @Query("lon") Double lon,
            @Query("units") String units,//温度单位
            @Query("appid") String key
    );
    //    @GET("index")//天气实况
    //    Observable<WeatherData> getWeatherData(
    //            @Query("cityname")String cityname,
    //            @Query("dtype")String dtype,
    //            @Query("format")int format,
    //            @Query("key")String key
    //    );

    @GET("/weather")
        //天气实况
    Observable<WeatherHome> getWeatherHomeTemp(
            @Query("name") String cityname);

    @POST("/login/doLogin")
        //登录
    Observable<UserItem> login(
            @QueryMap Map<String,String> map
    );

    @POST("/login/doLogin")
        //注册完成后登录
    Observable<UserItem> loginReg(
            @QueryMap Map<String, String> map
    );

    @POST("/login/doLoginSingleId")
        //登录SingleId
    Observable<UserItem> loginSingleId(
            @QueryMap Map<String,String>map
    );

    @POST("/user/resetPassword")
        //忘记密码
    Observable<FindBean> sendSmsFind(
            @QueryMap Map<String, String> map
    );

    /*
     *请求首页baner 和新闻动态数据
     * admin/index
     * */
    @POST("/common/index")
    Observable<SplashBnBean> getSplashBannerNew();


    @GET("/banner")
        //首页轮播
    Observable<BannerItem> getHomeBanner();

    //        @Multipart
    /*
    * 上传一张图片
    * @param description
    * @param imgs
    * @return
            */
    //    @Multipart
    @POST("/admin/upload")
    //    Observable<ResponseBody>uploadImage(
    //            @Part MultipartBody.Part file
    //            );
    Observable<IconItem> uploadImage(//上传头像
                                     //            @Part("file") String name,
                                     //            @Part("file\"; filename=\"icon.png\"") RequestBody file);
                                     //            @Part("file") String file);
                                     //                                     @Query("file") String file,
                                     //                                     @Query("reg") String type
                                     @QueryMap Map<String, String> map
    );
    //    @POST("/upload")
    //    Observable<String> uploadImage(
    //            @Part("fileName") String description,
    //            @Part("file\"; filename=\"image.png\"") RequestBody imgs);
    //    MultipartBody.Part user =
    //            MultipartBody.Part.createFormData("name","");
    //    MultipartBody.Part pass =
    //            MultipartBody.Part.createFormData("pass","");

    @GET("/news/list")
        //新闻状态
    Observable<NewInfoItem> getNewInfoData();

    @POST("/aAnlisDocController/getMktDyns")
        //市场动态
    Observable<BazaarItem> getBazaarData(@Query("areaId") String areaId);


    @POST("/farmer/list")
        //农场管理
    Observable<FarmManageBean> getFarmManageData(
            @QueryMap Map<String, String> map
            //            @Query("singleId") String userId,
            //            @Query("name") String name
    );


    //    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("/farmland/list")
    //农田管理
    //    Observable<FieldManageItem> getFarmlandData(@Query("farmland") String userId,@Body RequestBody route);
    Observable<FieldManageItem> getFarmlandData(
            @QueryMap Map<String, String> map
    );

    @POST("/relay/list")
        //设备管理
    Observable<FacilityItem> getFacilityManageData(
            @QueryMap Map<String, String> map
            //            @Query("singleId") String userId,
            //            @Query("relayNum")String relayNum
    );


    @POST("/node/list")
        //节点管理
    Observable<NodeManageItem> getNodeManageData(
            @QueryMap Map<String, String> map

    );

    @POST("/node/detailList")
        //节点列表
    Observable<NodeItem> getNodeData(
            @QueryMap Map<String, String> map
            //            @Query("farmlandId") String userId,
            //            @Query("date") String date

    );

    @POST("/node/get")
        //节点数据管理
    Observable<NodeDataManageItem> getNodeDataManage(
            @QueryMap Map<String, String> map
    );

    @POST("/relay/update")
        //添加中继设备
    Observable<FarmManageBean> getAddRelay(
            @QueryMap Map<String, String> map
    );

    @POST("/farmer/allFarmers")
        //添加中继设备+获取全部农场
    Observable<FarmManageBean> getAddRelayFarmList(
            @QueryMap Map<String, String> map
    );

    @POST("node/lastNodeDetails")
        //刷新获取节点数据详情
    Observable<LastNodeDetailsBean> getLastNodeDetails(
            @QueryMap Map<String, String> map
    );

    @GET("/node/HandOpenClose")
        //节点数据管理
    Observable<OffOnItemManage> getOffOnManage(
            @Query("id") String id,
            @Query("status") String status
    );

    @POST("/relay/delete")
        //删除中继
    Observable<RelayDetailItem> getRelayDelete(
            @QueryMap Map<String, String> map
    );


    @POST("/node/getHandlerResult")
        //根据控制开关状态，分次请求接口
    Observable<OffOnItemManage> getOffOnHandlerResult(
            @QueryMap Map<String, String> map
    );

    @POST("/node/update")
        //更新节点数据管理
    Observable<NodeDataManageItem> getNodeUpdate(
            @QueryMap Map<String, String> map
    );

    /*周期控制*/
    @POST("/timing/saveCyclOn")
    //周期策略开启
    Observable<CycleWeekBean> getCycleWeekOn(
            @QueryMap Map<String, String> date,
            //            @QueryMap Map<String,String[]> map
            //            @Query("on") List<String> info
            @Query("on") String[] week
    );

    @POST("/timing/saveCyclOff")
        //周期策略关闭
    Observable<CycleWeekBean> getCycleWeekOff(
            @QueryMap Map<String, String> date,
            //            @QueryMap Map<String,String[]> map
            //            @Query("on") List<String> info
            @Query("off") String[] week
    );


    /*智能控制*/
    @GET("/node/cycleList")
    //智能控制列表
    Observable<CapaCityItem> getCapaCityList(
            @Query("nodeNum") String nodeMac);


    /*
    * 生长记录
    * */
    @POST("/node/getByDay")
    Observable<GRItem> getGRItem(
            @QueryMap Map<String, String> map
            //            @Query("nodeNum")String nodeMac,
            //            @Query("type")String type,
            //            @Query("data")String data
    );


    /*添加*/
    @POST("/farmland/getUser")
    //添加农场
    Observable<AddFieldItem> getAddFielduId(@Query("singleId") String userId);


    @POST("/farmer/saveOrUpdate")
        //添加农场
    Observable<AddFarmItem> getAddFarmData(
            @QueryMap Map<String, String> map
    );

    @POST("/farmland/saveOrUpdate")
        //添加修改农田
    Observable<AddFieldData> getAddFieldData(
            @QueryMap Map<String, String> map
    );

    @GET("/farmland/delete")
        //删除农田
    Observable<FieldDetailItem> getDeleteField(
            @QueryMap Map<String, String> map
    );


    @POST("/node/autoMsg")
        //添加智能控制
    Observable<ICItem> getICItem(
            //            @Query("nodeNum")String nodeMac,
            //            @Query("property") String alias,
            //            @Query("max")Double max,
            //            @Query("min")Double min,
            //            @Query("cycle") String name
            @QueryMap Map<String, String> map,
            @Query("max") Double max,
            @Query("min") Double min
    );

    @POST("/node/cycleUpdate")
        //修改智能控制
    Observable<ICItem> getICUpdateItem(
            //            @Query("nodeNum")String nodeMac,
            //            @Query("property") String alias,
            @QueryMap Map<String, String> map,
            @Query("max") Double max,
            @Query("min") Double min
            //            @Query("cycle") String name
    );

    @POST("/node/cycleDel")
        //删除智能控制
    Observable<ICItem> getICDeleteItem(
            @QueryMap Map<String, String> map
    );


    /*预警*/
    @GET("/waringmsg/list")
    //智能控制列表
    Observable<EarlyItme> getEarlyData(
            @Query("singleId") String userId);

    /*开关控制总接口*/
    @GET("/timing/getStrategy")
    Observable<NodeCollectionCycle> getNodeCollectionCycle
    (
            @QueryMap Map<String, String> map
    );

    /*周期刷新*/
    @POST("/timing/saveCyclTime")
    Observable<CycleUpdateBean> getCycleUpdate(
            @QueryMap Map<String, String> map
    );


    /*人员管理(农户)*/
    @POST("/user/farmers")
    Observable<FarmersBean> getFarmerManage(

            @QueryMap Map<String, String> map
    );


    @GET("/farmer/delete")
        //删除农场
    Observable<FarmDetailItem> getDeleteData(
            @Query("id") String id
    );

    @POST("/farmer/get")
        //农场详情
    Observable<FarmDetailItem> getFarmDetail(
            @QueryMap Map<String, String> map
    );

    @POST("/farmland/get")
        //农田详情
    Observable<FieldDetailItem> getFieldDetail(
            @QueryMap Map<String, String> map
    );

    @POST("/relay/info")
        //中继详情
    Observable<RelayDetailItem> getRelayDetail(
            @QueryMap Map<String, String> map
    );

    @POST("/farmer/saveOrUpdate")
        //修改农场
    Observable<FarmDetailItem> getFarmDataUpdate(
            @QueryMap Map<String, String> map
            //            @Query("userId") String uid ,
            //            @Query("name") String name ,
            //            @Query("farmerNum") String farmerNum,
            //            @Query("addr") String addr,
            //            @Query("area") Double area,
            //            @Query("plantVariety") String plantVariety,
            //            @Query("user.name") String user,
            //            @Query("farmlandNumber") String farmlandNumber,
            //            @Query("relayNumber") String relayNumber
    );

    //短信验证
    @GET("/register/sendSmsCode")
    Observable<SmsItem> getSendSms(
            @QueryMap Map<String, String> map
    );

    @POST("/register")
        //注册
    Observable<RegItem> getRegData(
            @QueryMap Map<String, String> map
            //            @Query("loginName") String loginName ,
            //            @Query("sex") String sex ,
            //            @Query("age") String age ,
            //            @Query("address") String address ,
            //            @Query("company") String company ,
            //            @Query("mobile") String mobile ,
            //            @Query("name") String name ,
            //            @Query("password") String password ,
            //            @Query("verCode") String ver
    );


    /**
     * 版本更新
     *
     * @param map
     * @return
     */
    @POST("/version/check")
    Observable<VersionUpdateBean> getVeesionUpdate
    (
            @QueryMap Map<String, String> map
    );


    @GET("/login/loginOut")
        //退出登录
    Observable<ExitItem> getExit(
            @Query("singleId") String singleId
    );


}
