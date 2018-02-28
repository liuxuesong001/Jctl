package com.colud.jctl.ui.activity;

import android.os.Build;
import android.view.View;
import android.view.WindowManager;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.colud.jctl.base.BaseActivity;
import com.colud.jctl.bean.FarmManageBean;
import com.colud.jctl.bean.Weather;
import com.colud.jctl.bean.WeatherHome;
import com.colud.jctl.mvp.contract.SplashContract;
import com.colud.jctl.mvp.presenter.SplashPresenter;
import com.colud.jctl.ui.custom.TitleBar;
import com.colud.jctl.utils.BarStatusHeightUtil;
import com.jctl.colud.R;

import butterknife.BindView;

/**
 * 农场地址
 * Created by Jcty on 2017/3/31.
 */

public class FarmAddressMapActivity extends BaseActivity<SplashPresenter> implements SplashContract.View {

    @BindView(R.id.login_toolbar)
    TitleBar toolbar;
    @BindView(R.id.farm_address_map)
    MapView mMapView;
    private BaiduMap mBaiduMap;

    private Weather weather;
    private boolean isFirstLoc = true; // 是否首次定位

    private FarmManageBean.InfoBean farmName;

    @Override
    protected SplashPresenter onCreatePresenter() {
        return new SplashPresenter(this);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_farmaddress_map;
    }

    @Override
    public void initViews() {
        toolbar.bringToFront();
        toolbar.getBackground().setAlpha(80);
        setMapView();
    }

    @Override
    public void addListener() {

    }

    protected void setMapView() {
        //		String cacheData = JctlApplication.initTCache.getSerializable("cacheWeather");
        //		if(!TextUtils.isEmpty(cacheData)){
        //			weather = GsonUtils.newInstance().fromJson(cacheData, Weather.class);
        //		}
        //		// 开启定位图层
        //		mBaiduMap=mMapView.getMap();
        //		mBaiduMap.setMyLocationEnabled(true);
        //		mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);//普通地图
    }

    @Override
    public void initData() {
        farmName = (FarmManageBean.InfoBean) getIntent().getSerializableExtra("info");
        if (farmName != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);//不显示自带标题
            toolbar.setCenterTitle(farmName.getAddr());
            toolbar.setNavigationIcon(R.drawable.img_back);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
            //设置透明状态栏
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                toolbar.setPadding(0, BarStatusHeightUtil.getStatusBarHeightAll(this), 0, 0);
            }
        }
        mPresenter.locateLastKnown(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;

    }

    private double lat, lng;

    @Override
    public void onSucceedLocation(BDLocation bdLocation) {
        // 开启定位图层
        if (bdLocation != null) {
            if (farmName.getLat() != null && !"".equals(farmName.getLat())) {
                lat = Double.valueOf(farmName.getLat());
            } else {
                lat = bdLocation.getLatitude();
            }
            if (farmName.getLng() != null && !"".equals(farmName.getLng())) {
                lng = Double.valueOf(farmName.getLng());
            } else {
                lng = bdLocation.getLongitude();
            }
            mBaiduMap = mMapView.getMap();
            mBaiduMap.setMyLocationEnabled(true);
            mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);//普通地图
            // 构造定位数据
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(bdLocation.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100)
                    .latitude(lat)
                    .longitude(lng)
                    .build();
            // 设置定位数据
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(lat, lng);
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
        }
    }


    @Override
    public void onSuccedTemp(WeatherHome homeTemp) {

    }


    @Override
    public void onFail(String err) {

    }

    @Override
    public void onFailure(String err, Throwable e) {

    }


    @Override
    public void onLocatedFail(BDLocation bdLocation) {
        // 开启定位图层
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMyLocationEnabled(true);
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);//普通地图
        // 构造定位数据
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(bdLocation.getRadius())
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(100).latitude(bdLocation.getLatitude())
                .longitude(bdLocation.getLongitude()).build();
        // 设置定位数据
        mBaiduMap.setMyLocationData(locData);
        if (isFirstLoc) {
            isFirstLoc = false;
            LatLng ll = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
            MapStatus.Builder builder = new MapStatus.Builder();
            builder.target(ll).zoom(18.0f);
            mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        }
        //		// 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
        //		BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory.fromResource(R.drawable.jctl_launcher);
        //		MyLocationConfiguration config = new MyLocationConfiguration(mCurrentMode, true, mCurrentMarker);
        //		mBaiduMap.setMyLocationConfiguration();
        // 当不需要定位图层时关闭定位图层
        //		mBaiduMap.setMyLocationEnabled(false);

        //		BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory.fromResource(R.drawable.jctl_launcher);
    }


}
