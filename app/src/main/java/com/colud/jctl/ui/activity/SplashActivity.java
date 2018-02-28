package com.colud.jctl.ui.activity;

import android.Manifest;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.baidu.location.BDLocation;
import com.colud.jctl.MainActivity;
import com.colud.jctl.adapters.activity.GuideViewPagerAdapter;
import com.colud.jctl.base.AppManager;
import com.colud.jctl.base.BaseActivity;
import com.colud.jctl.bean.WeatherHome;
import com.colud.jctl.mvp.contract.SplashContract;
import com.colud.jctl.mvp.presenter.SplashPresenter;
import com.colud.jctl.receiver.BroadCastManager;
import com.colud.jctl.receiver.network.NetStateUtils;
import com.colud.jctl.utils.DialogUtils;
import com.colud.jctl.utils.SharedPreferencesUtil;
import com.colud.jctl.utils.ToastUtils;
import com.jctl.colud.R;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

import static com.colud.jctl.JctlApplication.context;
import static com.colud.jctl.counst.Counts.BROADCAST_TEMP;


/**
 * 启动页面
 * Created by Jcty on 2017/3/1.
 */

public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashContract.View, View.OnClickListener {

    private static final String TAG = SplashActivity.class.getSimpleName();

    private static boolean isPermissionRequested = false;

    private boolean isFirstOpen;

    private ViewPager vp;
    private GuideViewPagerAdapter adapter;
    private List<View> views;
    private Button startBtn;



    //当前网络状态
    protected boolean netStatue= false;

    // 引导页图片资源
    private static final int[] pics = {
            R.layout.guide_view1,
            R.layout.guide_view2,
            R.layout.guide_view3,
            R.layout.guide_view4
    };

    // 底部小点图片
    private ImageView[] dots;

    // 记录当前选中位置
    private int currentIndex;


    @Override
    public int getContentViewId() {
        // 判断是否是第一次开启应用
        isFirstOpen = SharedPreferencesUtil.getBoolean(this, SharedPreferencesUtil.FIRST_OPEN, true);
        // 如果是第一次启动，则先进入功能引导页
        if (isFirstOpen) {
            setTheme(R.style.welTheme);
            return R.layout.activity_guide;
        } else {
            return R.layout.activity_splash;
        }
    }

    @Override
    public void initViews() {
        if (isFirstOpen) {
            views = new ArrayList<View>();

            // 初始化引导页视图列表
            for (int i = 0; i < pics.length; i++) {
                View view = LayoutInflater.from(this).inflate(pics[i], null);

                if (i == pics.length - 1) {
                    startBtn = (Button) view.findViewById(R.id.btn_enter);
                    startBtn.setTag("enter");
                    startBtn.setOnClickListener(this);
                }

                views.add(view);

            }

            vp = (ViewPager) findViewById(R.id.vp_guide);
            adapter = new GuideViewPagerAdapter(views);
            vp.setAdapter(adapter);
            vp.addOnPageChangeListener(new PageChangeListener());

            initDots();
        }
    }

    private void initDots() {
        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
        dots = new ImageView[pics.length];

        // 循环取得小点图片
        for (int i = 0; i < pics.length; i++) {
            // 得到一个LinearLayout下面的每一个子元素
            dots[i] = (ImageView) ll.getChildAt(i);
            dots[i].setEnabled(false);// 都设为灰色
            dots[i].setOnClickListener(this);
            dots[i].setTag(i);// 设置位置tag，方便取出与当前位置对应
        }

        currentIndex = 0;
        dots[currentIndex].setEnabled(true); // 设置为白色，即选中状态

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocationManager locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            //            未打开位置开关，可能导致定位失败或定位不准，提示用户或做相应处理
            DialogUtils.showNoNetWorkDlg(this);
        } else {
            requestPermission();
        }
    }



    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void addListener() {

    }

    /**
     * 设置当前view
     *
     * @param position
     */
    private void setCurView(int position) {
        if (position < 0 || position >= pics.length) {
            return;
        }
        vp.setCurrentItem(position);
    }

    /**
     * 设置当前指示点
     *
     * @param position
     */
    private void setCurDot(int position) {
        if (position < 0 || position > pics.length || currentIndex == position) {
            return;
        }
        dots[position].setEnabled(true);
        dots[currentIndex].setEnabled(false);
        currentIndex = position;
    }

    @Override
    public void onClick(View v) {
        if (v.getTag().equals("enter")) {
            enterMainActivity();
            return;
        }

        int position = (Integer) v.getTag();
        setCurView(position);
        setCurDot(position);
    }


    private void enterMainActivity() {
        //        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        //        startActivity(intent);
        SharedPreferencesUtil.putBoolean(SplashActivity.this, SharedPreferencesUtil.FIRST_OPEN, false);
        //        AppManager.newInstance().finishCurrentActivity();
        mPresenter.getHomeTemp();
    }



    private class PageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int position) {

        }

        @Override
        public void onPageScrolled(int position, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int position) {
            // 设置底部小点选中状态
            setCurDot(position);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (netStatue == NetStateUtils.isNetworkConnected(this)) {
            DialogUtils.showWifiDlg(this);
        }
    }

    @Override
    public void initData() {

    }



    @Override
    protected SplashPresenter onCreatePresenter() {
        return new SplashPresenter(this);

    }

    @Override
    public void onSucceedLocation(BDLocation bdLocation) {
        KLog.d("百度定位成功");
    }

    @Override
    public void onSuccedTemp(WeatherHome homeTemp) {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        intent.putExtra("temp",homeTemp);
        startActivity(intent);
        intent.setAction(BROADCAST_TEMP);

        BroadCastManager.getInstance().sendBroadCast(this,intent);
        AppManager.newInstance().finishCurrentActivity();
    }

    @Override
    public void onFail(String err) {
        //        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        //        startActivity(intent);
        //        AppManager.newInstance().finishCurrentActivity();
    }

    @Override
    public void onFailure(String err, Throwable e) {
        if (err.equals("网络异常")) {
            ToastUtils.showLong("网络异常");
        }
    }


    @Override
    public void onLocatedFail(BDLocation bdLocation) {
        KLog.e("百度定位失败");
    }

    //自定义一个权限获取码，用于回调函数中做对应处理
    private static final int BAIDU_READ_PHONE_STATE = 100;

    /**
     * 动态获取百度定位权限Android6.0
     */
    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= 23 && !isPermissionRequested) {

            isPermissionRequested = true;

            ArrayList<String> permissions = new ArrayList<>();

            if (context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            }

            if (permissions.size() == 0) {

                return;

            } else {

                //                String[] array = new String[permissions.size()];

                //                requestPermissions(permissions.toArray(array), BAIDU_READ_PHONE_STATE);
                requestPermissions(permissions.toArray(new String[permissions.size()]), 0);

            }

        } else {
            mPresenter.getHomeTemp();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            // requestCode即所声明的权限获取码，在checkSelfPermission时传入
            case BAIDU_READ_PHONE_STATE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 获取到权限，作相应处理（调用定位SDK应当确保相关权限均被授权，否则可能引起定位失败）
                    KLog.d("Android6.0 动态获取权限成功");

                    mPresenter.getHomeTemp();

                } else {
                    // 没有获取到权限，做特殊处理
                    requestPermission();
                }
                break;
            default:
                break;

        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 如果切换到后台，就设置下次不进入功能引导页
        //        SharedPreferencesUtil.putBoolean(SplashActivity.this, SharedPreferencesUtil.FIRST_OPEN, false);
        //        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
