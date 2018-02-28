package com.colud.jctl.ui.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.transition.TransitionManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.acker.simplezxing.activity.CaptureActivity;
import com.colud.jctl.JctlApplication;
import com.colud.jctl.MainActivity;
import com.colud.jctl.adapters.fragment.HomeFragmentPagerAdapter;
import com.colud.jctl.api.ApiConstants;
import com.colud.jctl.api.IMutualListener;
import com.colud.jctl.api.KeyConstants;
import com.colud.jctl.base.BaseFragment;
import com.colud.jctl.base.UIInitF;
import com.colud.jctl.bean.RateDialogItem;
import com.colud.jctl.bean.SplashBnBean;
import com.colud.jctl.bean.UserItem;
import com.colud.jctl.bean.VersionUpdateBean;
import com.colud.jctl.bean.WeatherHome;
import com.colud.jctl.counst.Constants;
import com.colud.jctl.counst.Counts;
import com.colud.jctl.mvp.contract.HomeContract;
import com.colud.jctl.mvp.presenter.HomePresenter;
import com.colud.jctl.receiver.BroadCastManager;
import com.colud.jctl.service.DownloadApkServiceNotification;
import com.colud.jctl.ui.activity.FarmManageActivity;
import com.colud.jctl.ui.activity.MoreNewDynamicActivity;
import com.colud.jctl.ui.activity.NewInfoDetailActivity;
import com.colud.jctl.ui.activity.PeopleManageActivity;
import com.colud.jctl.ui.activity.RelayManageActivity;
import com.colud.jctl.ui.activity.WeatherAtivity;
import com.colud.jctl.ui.custom.GlideImageLoader;
import com.colud.jctl.ui.custom.NoScrollViewPager;
import com.colud.jctl.ui.custom.TitleBar;
import com.colud.jctl.ui.custom.VerticalScrollView;
import com.colud.jctl.ui.custom.dialog.RateDialog;
import com.colud.jctl.utils.BarStatusHeightUtil;
import com.colud.jctl.utils.FileUtil;
import com.colud.jctl.utils.NetStatusUtil;
import com.colud.jctl.utils.ToastUtils;
import com.jctl.colud.R;
import com.socks.library.KLog;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.acker.simplezxing.activity.CaptureActivity.EXTRA_SCAN_RESULT;
import static com.colud.jctl.JctlApplication.context;
import static com.colud.jctl.api.ApiConstants.CAMERA_URL;
import static com.colud.jctl.api.KeyConstants.HOME_TEMP;
import static com.colud.jctl.api.KeyConstants.KAY_BANENRURl;
import static com.colud.jctl.api.KeyConstants.USER_NAME;
import static com.colud.jctl.api.KeyConstants.isDownloading;
import static com.colud.jctl.counst.Counts.BROADCAST_NEWS;
import static com.colud.jctl.counst.Counts.BROADCAST_TEMP;
import static com.jctl.colud.R.id.more_ll;
import static com.jctl.colud.R.layout.dialog;


/**
 * 首页
 * Created by Jcty on 2017/2/27.
 */

public class HomeFragment extends BaseFragment<HomePresenter> implements UIInitF, OnBannerListener, HomeContract.View, AppBarLayout.OnOffsetChangedListener, TabLayout.OnTabSelectedListener,IMutualListener {


    @BindView(R.id.title_img)
    ImageView imgTitle;
    @BindView(R.id.title_right_img)
    ImageView rightimg;

    @BindView(R.id.home_title_ll)
    LinearLayout home_title_ll;

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.home_layout)
    LinearLayout homeLayout;
    @BindView(R.id.home_tableLayout)
    TabLayout homeTableLayout;
    @BindView(R.id.home_ViewPager)
    NoScrollViewPager homeViewPager;


    @BindView(R.id.home_layout_ncgl)
    LinearLayout homeLayoutNcgl;
    @BindView(R.id.home_layout_sbgl)
    LinearLayout homeLayoutSbgl;
    @BindView(R.id.home_layout_rygl)
    LinearLayout homeLayoutRygl;
    @BindView(R.id.home_layout_spjk)
    LinearLayout homeLayoutSpjk;
    @BindView(R.id.more_ll)
    LinearLayout moreLL;
    @BindView(R.id.title_toolbar)
    TitleBar toolbar;
    @BindView(R.id.btmSlv)
    VerticalScrollView scrollView;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.morelltv)
    TextView morellTv;
    @BindView(R.id.morelliv)
    ImageView morellIv;


    protected HomeFragmentPagerAdapter homeAdapter;

    protected TabLayout.Tab newDynamicFragment;

    protected TabLayout.Tab bazaarDynaminFragment;

    private boolean newState = true;

    private int now = 0;

    private WeatherHome tempData;

    private UserItem userItem;

    private List<String> sUrl = new ArrayList<>();

    private static final int REQUEST_QR_CODE = 1;

    private Handler handler = new Handler();

    private Dialog progressDialog;

    private Map<String, String> map = new ArrayMap<>();

    private RateDialog rateDialog;

    private RateDialogItem dialogItem = new RateDialogItem();

    private SplashBnBean banr;

    private TempReceiver mTempReceiver;

    private IMutualListener mIMutualListener;

    public static HomeFragment getInstance(String pageName) {
        Bundle args = new Bundle();
        args.putString(Constants.ARGS, pageName);
        HomeFragment pageFragment = new HomeFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTempReceiver= new TempReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(BROADCAST_TEMP);
        BroadCastManager.getInstance().registerReceiver(getActivity(),mTempReceiver,filter);

    }

    @Override
    public void onSucceedBannerNew(SplashBnBean news)
    {
        if (news.getBanner()==null)
        {
            SplashBnBean banr= (SplashBnBean) JctlApplication.getCache().getAsObject(KAY_BANENRURl);
            if (banr==null)
            {
                return;
            }else {
                if (news.getBanners().size() > 0) {
                    banner.
                            setImages(news.getBanners())
                            .setImageLoader(new GlideImageLoader())
                            .setOnBannerListener(this)
                            .start();
                }
                if (news.getUrls()!=null)
                {
                    sUrl = news.getUrls();
                }
            }
        }else {
            if (news.getBanners().size() > 0) {
                banner.
                        setImages(news.getBanners())
                        .setImageLoader(new GlideImageLoader())
                        .setOnBannerListener(this)
                        .start();
            }
            if (news.getUrls()!=null)
            {
                sUrl = news.getUrls();
            }
        }
        banr = news;
        if (news.getNews()!=null &&news.getNews().size()>0)
        {
            //            mIMutualListener.fragmentData(1,news.getNews());
            //            mMainActivity.setNewsData(news.getNews());
            //            NewDynamicFragment nd = new NewDynamicFragment();
            //            Bundle bundle = new Bundle();
            //            bundle.putSerializable("news", (Serializable) news.getNews());
            //            nd.setArguments(bundle);
            //            NewDynamicFragment newDynamicFragment =
            //                    (NewDynamicFragment) getActivity()
            //                            .getSupportFragmentManager()
            //                            .findFragmentByTag("newDynamicFragment");
            //            newDynamicFragment.setData(news.getNews());
            Intent intent =new Intent();
            intent.setAction(BROADCAST_NEWS);
            intent.putExtra("news", (Serializable) news.getNews());
            BroadCastManager.getInstance().sendBroadCast(getActivity(),intent);

        }
    }
    @Override
    public void onAttach(Activity activity) {
        //        mMainActivity = (MainActivity) activity;
        mIMutualListener=(IMutualListener)activity;
        super.onAttach(activity);

    }



    private static final int REQ_CODE_PERMISSION = 1;

    @OnClick({R.id.home_title_ll, R.id.title_right_img, more_ll, R.id.home_layout_ncgl, R.id.home_layout_sbgl, R.id.home_layout_rygl, R.id.home_layout_spjk})
    public void onClick(View v) {
        Intent in;
        switch (v.getId()) {
            case R.id.home_title_ll:
                in = new Intent(getActivity(), WeatherAtivity.class);
                in.putExtra("url", ApiConstants.CAYUN_URL);
                in.putExtra("temp",tempData);
                startActivity(in);
                break;
            case R.id.title_right_img:
                //Android 6.0权限判断
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, REQ_CODE_PERMISSION);
                } else {
                    startCamera();
                }
                break;
            case R.id.home_layout_ncgl:
                if (KeyConstants.LGOIN_IS) {
                    in = new Intent(getActivity(), FarmManageActivity.class);
                    startActivity(in);
                } else {
                    ToastUtils.showShort(JctlApplication.getAppResources().getString(R.string.please_login));
                }

                break;
            case R.id.home_layout_sbgl:
                if (KeyConstants.LGOIN_IS) {
                    in = new Intent(getActivity(), RelayManageActivity.class);
                    startActivity(in);
                } else {
                    ToastUtils.showShort(JctlApplication.getAppResources().getString(R.string.please_login));
                }

                break;
            case R.id.home_layout_rygl:
                if (KeyConstants.LGOIN_IS) {
                    in = new Intent(getActivity(), PeopleManageActivity.class);
                    startActivity(in);
                } else {
                    ToastUtils.showShort(JctlApplication.getAppResources().getString(R.string.please_login));
                }


                break;
            case R.id.home_layout_spjk:
                if (KeyConstants.LGOIN_IS) {
                    Intent intent = new Intent(getActivity(), NewInfoDetailActivity.class);
                    //					intent.putExtra("url","http://60.255.50.139/");
                    intent.putExtra("url", CAMERA_URL);
                    startActivity(intent);
                } else {
                    ToastUtils.showShort(JctlApplication.getAppResources().getString(R.string.please_login));
                }
                break;
            case more_ll:
                if (newState && banr!=null) {
                    in = new Intent(getActivity(), MoreNewDynamicActivity.class);
                    in.putExtra("news", (Serializable) banr.getNews());
                    startActivity(in);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_home;
    }

    @Override
    public void addListener() {
        homeTableLayout.setOnTabSelectedListener(this);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }


    @Override
    public void initViews() {
        setViewPager();
        title.setVisibility(View.VISIBLE);
        imgTitle.setVisibility(View.VISIBLE);
        rightimg.setVisibility(View.VISIBLE);
        setToolBarTitle();
    }

    /**
     * 设置viewpager
     */
    protected void setViewPager() {
        //绑定viewpager
        homeAdapter = new HomeFragmentPagerAdapter(getChildFragmentManager());
        homeViewPager.setAdapter(homeAdapter);
        homeTableLayout.setupWithViewPager(homeViewPager);
        //获取fragment位置
        newDynamicFragment = homeTableLayout.getTabAt(0);
        bazaarDynaminFragment = homeTableLayout.getTabAt(1);

        homeViewPager.setScroll(true);

    }

    /**
     * 设置ToolBar参数
     */
    public void setToolBarTitle() {
        AppCompatActivity activity = (MainActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);//不显示自带标题
        //设置透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            toolbar.setPadding(0, BarStatusHeightUtil.getStatusBarHeightAll(getActivity()), 0, 0);
        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(ScrollView.FOCUS_UP);
            }
        });
    }

    /**
     * 设置天气数据
     */
    private void setTempData() {
        tempData=mIMutualListener.getTemp();
        KLog.d("Temp:"+title.getText().toString());
        if (tempData==null)
        {
            tempData= (WeatherHome) JctlApplication.getCache().getAsObject(HOME_TEMP);
        }else if(tempData.getStatus()==1)
        {
            if (title.getText().toString().equals("")&&tempData.getInfo().getTemperature()!=null )
            {
                title.setText(tempData.getInfo().getTemperature() + "℃");
                if (!"晴".equals(tempData.getInfo().getText())&& tempData.getInfo().getTemperature() != null) {
                    imgTitle.setImageResource(R.drawable.yun);
                } else {
                    imgTitle.setImageResource(R.drawable.sun);
                }
            }
        }
        else{
            title.setText(JctlApplication.getAppResources().getString(R.string.tqxq));
        }
    }


    @Override
    public void onStop() {
        super.onStop();

    }



    @Override
    public void initData() {
        setTempData();

        /**
         * 获取新闻动态
         */
        mPresenter.setBannerNew();
        //获取登录信息缓存
        userItem = (UserItem) JctlApplication.getCache().getAsObject(KeyConstants.USER_ITEM);
        String channelId = JctlApplication.getCache().getAsString(KeyConstants.KAY_CHANNELID);
        if (userItem != null && userItem.getFlag() == 1) {
            map.clear();
            map.put("singleId",userItem.getUser().getSingleId());
            map.put("channelId",channelId);
            mPresenter.setSingleId(map);
            KLog.d("LiuSong:","singleId="+userItem.getUser().getSingleId());
        }
    }

    @Override
    public void OnBannerClick(int position) {
        if (sUrl != null && sUrl.size() > 0) {
            Intent intent = new Intent(getActivity(), NewInfoDetailActivity.class);
            intent.putExtra("url", sUrl.get(position));
            startActivity(intent);
        }else {
            banr= (SplashBnBean) JctlApplication.getCache().getAsObject(KAY_BANENRURl);
            if (banr!=null&&banr.getUrls()!=null)
            {
                Intent intent = new Intent(getActivity(), NewInfoDetailActivity.class);
                intent.putExtra("url",banr.getUrls().get(position));
                startActivity(intent);
            }

        }
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void showDialog() {
        if (progressDialog == null) {
            progressDialog = new Dialog(getActivity(), R.style.progress_dialog);
            progressDialog.setContentView(dialog);
            progressDialog.setCancelable(false);
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            TextView msg = (TextView) progressDialog.findViewById(R.id.id_tv_loadingmsg);
            msg.setText("登录中...");
            progressDialog.show();
        }
    }

    @Override
    public void onSucceedSingleId(UserItem data) {
        String  userId = JctlApplication.getCache().getAsString(USER_NAME);
        KeyConstants.LGOIN_IS = true;
        if (userId != null) {
            //通知数据库刷新
            Intent msg = new Intent();
            msg.putExtra("userId", userId);
            msg.setAction(KeyConstants.MESSAGE_YUJING);
            BroadCastManager.getInstance().sendBroadCast(context, msg);
        } else {
            JctlApplication.getCache().remove(KeyConstants.USER_ITEM);
            ToastUtils.showLong(data.getMsg());
        }

        mIMutualListener.fragmentData(3,userItem);

        ToastUtils.showLong(JctlApplication.getAppResources().getString(R.string.login_success));
    }

    @Override
    public void onFailure(String err, Throwable e) {
        //        banr= (SplashBnBean) JctlApplication.getCache().getAsObject(KAY_BANENRURl);
        //        if (banr.getBanners()!=null && banr.getBanners().size() > 0)
        //        {
        //            banner.setImages(banr.getBanners())
        //                    .setImageLoader(new GlideImageLoader())
        //                    .setOnBannerListener(this)
        //                    .start();
        //        }else
        //        {
        //            banr= (SplashBnBean) JctlApplication.getCache().getAsObject(KAY_BANENRURl);
        //            banner.setImages(banr.getBanners())
        //                    .setImageLoader(new GlideImageLoader())
        //                    .setOnBannerListener(this)
        //                    .start();
        //        }

        JctlApplication.getCache().remove(KeyConstants.USER_ITEM);
    }

    @Override
    public void onFailureLogin(String err, Throwable e) {
        ToastUtils.showLong("登录失败,请重新登录");
        JctlApplication.getCache().remove(KeyConstants.USER_ITEM);
        JctlApplication.getCache().remove(KeyConstants.USER_PHOTO);

    }

    @Override
    public void onSucceedRegLogin(UserItem userItem) {
        if (userItem != null && userItem.getFlag() == 1) {
            ToastUtils.showLong(JctlApplication.getAppResources().getString(R.string.login_success));
            JctlApplication.getCache().remove(KeyConstants.KAY_REG);
            map.clear();
        }
    }

    @Override
    public void onSucceedUpdate(final VersionUpdateBean data) {
        if (data.getVersion().getInVersion() != null) {

            if (FileUtil.FilePath(getActivity(), ApiConstants.DOWNLOADPATH + "zhny.apk")) {
                dialogItem.setTvTitle("已经下载完成!");
                dialogItem.setTvContent("确定安装?");
                rateDialog = new RateDialog(getContext(), R.style.MyDialog, dialogItem);
                rateDialog.setMyClickListener(new RateDialog.onClickRateDialog() {
                    @Override
                    public void onClickLeft() {
                        rateDialog.dismiss();

                    }

                    @Override
                    public void onClickRight() {
                        rateDialog.dismiss();
                        // 通过Intent安装APK文件
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setDataAndType(Uri.parse("file://" + ApiConstants.DOWNLOADPATH + "zhny.apk"),
                                "application/vnd.android.package-archive");
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                    }
                });
                rateDialog.show();
            } else {
                dialogItem.setTvTitle("已更新至" + data.getVersion().getOutVersion());
                dialogItem.setTvContent(data.getVersion().getRemark());
                rateDialog = new RateDialog(getContext(), R.style.MyDialog, dialogItem);
                rateDialog.setMyClickListener(new RateDialog.onClickRateDialog() {
                    @Override
                    public void onClickLeft() {
                        rateDialog.dismiss();

                    }

                    @Override
                    public void onClickRight() {
                        rateDialog.dismiss();
                        if (NetStatusUtil.netSatus(getActivity()) == NetStatusUtil.NetState.NET_2G ||
                                NetStatusUtil.netSatus(getActivity()) == NetStatusUtil.NetState.NET_NO ||
                                NetStatusUtil.netSatus(getActivity()) == NetStatusUtil.NetState.NET_UNKNOWN) {
                            ToastUtils.showLong("抱歉,您当前网络较差,请连接Wifi网络进行下载更新。");
                            return;

                        } else if (NetStatusUtil.netSatus(getActivity()) == NetStatusUtil.NetState.NET_3G)

                        {
                            dialogItem.setTvTitle("您当前的网络是 3G网络");
                            dialogItem.setTvContent("是否要下载更新?");
                            rateDialog = new RateDialog(getContext(), R.style.MyDialog, dialogItem);
                            rateDialog.setMyClickListener(new RateDialog.onClickRateDialog() {
                                @Override
                                public void onClickLeft() {
                                    rateDialog.dismiss();

                                }

                                @Override
                                public void onClickRight() {
                                    rateDialog.dismiss();
                                    if (!NetStatusUtil.isServiceWorked(getActivity()) && isDownloading) {
                                        Intent serviceIntent = new Intent(getActivity(), DownloadApkServiceNotification.class);
                                        serviceIntent.putExtra("downloadURL", data.getVersion().getUrl());
                                        serviceIntent.putExtra("savePath", ApiConstants.DOWNLOADPATH);
                                        serviceIntent.putExtra("mApkName", "zhny.apk");
                                        getActivity().startService(serviceIntent);
                                        isDownloading = false;
                                    } else {
                                        ToastUtils.showLong("下载中请稍后...");
                                    }
                                }
                            });
                            rateDialog.show();
                        } else {
                            if (!NetStatusUtil.isServiceWorked(getActivity()) && isDownloading) {
                                Intent serviceIntent = new Intent(getActivity(), DownloadApkServiceNotification.class);
                                serviceIntent.putExtra("downloadURL", data.getVersion().getUrl());
                                serviceIntent.putExtra("savePath", ApiConstants.DOWNLOADPATH);
                                serviceIntent.putExtra("mApkName", "zhny.apk");
                                getActivity().startService(serviceIntent);
                                isDownloading = false;
                            } else

                            {
                                ToastUtils.showLong("下载中请稍后...");
                            }
                        }
                    }
                });
                rateDialog.show();
            }
        }

    }

    @Override
    public void onFail(String err) {
        //        SplashBnBean  banr= (SplashBnBean) JctlApplication.getCache().getAsObject(KAY_BANENRURl);
        //        if (banr.getBanners()!=null && banr.getBanners().size() > 0)
        //        {
        //            banner.setImages(banr.getBanners())
        //                    .setImageLoader(new GlideImageLoader())
        //                    .setOnBannerListener(this)
        //                    .start();
        //        }
    }


    @Override
    public void hideDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;

        }

    }

    @Override
    protected HomePresenter onCreatePresenter() {
        return new HomePresenter(this);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        banner.stopAutoPlay();
        BroadCastManager.getInstance().unregisterReceiver(getActivity(),mTempReceiver);
        // 从CompositeSubscription中移除取消订阅事件,防止内存泄漏
        //        RxSubscriptions.remove(mRxSub);
    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        map.clear();
        if (NetStatusUtil.getVersion(getActivity()) != 0) {
            map.put("inVersion", String.valueOf(NetStatusUtil.getVersion(getActivity())));
            mPresenter.setUpdateV(map);
        }

    }


    //当标签被选中时运行的方法
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        //动画效果参数直接定义
        TransitionManager.beginDelayedTransition(moreLL);

        if (tab.getPosition() == now) {
            newState = true;
            if (moreLL.getVisibility() == View.VISIBLE) {
                moreLL.setVisibility(View.GONE);
            } else {
                moreLL.setVisibility(View.VISIBLE);
            }
        } else {
            newState = false;
            if (moreLL.getVisibility() == View.VISIBLE) {
                moreLL.setVisibility(View.GONE);
            } else {
                moreLL.setVisibility(View.VISIBLE);
            }
        }
    }

    //当标签被取消选中时运行的方法
    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    //当标签被重复选中时运行的方法
    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    private void startCamera() {
        Intent intent = new Intent(getActivity(), CaptureActivity.class);
        Bundle bundle = new Bundle();
        bundle.putBoolean(CaptureActivity.KEY_NEED_BEEP, CaptureActivity.VALUE_BEEP);
        bundle.putBoolean(CaptureActivity.KEY_NEED_VIBRATION, CaptureActivity.VALUE_VIBRATION);
        bundle.putBoolean(CaptureActivity.KEY_NEED_EXPOSURE, CaptureActivity.VALUE_NO_EXPOSURE);
        bundle.putByte(CaptureActivity.KEY_FLASHLIGHT_MODE, CaptureActivity.VALUE_FLASHLIGHT_OFF);
        bundle.putByte(CaptureActivity.KEY_ORIENTATION_MODE, CaptureActivity.VALUE_ORIENTATION_AUTO);
        intent.putExtra(CaptureActivity.EXTRA_SETTING_BUNDLE, bundle);
        startActivityForResult(intent, CaptureActivity.REQ_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CaptureActivity.REQ_CODE:
                if (data != null) {
                    String scanInfo = data.getStringExtra(EXTRA_SCAN_RESULT);
                    if (!TextUtils.isEmpty(scanInfo)) {
                        switch (resultCode) {
                            case CaptureActivity.RESULT_OK:
                                Intent intent = new Intent(getActivity(), NewInfoDetailActivity.class);
                                intent.putExtra("url", scanInfo);
                                startActivity(intent);
                                KLog.d(scanInfo);  //or do sth
                                break;
                            case CaptureActivity.RESULT_CANCELED:
                                // for some reason camera is not working correctly
                                KLog.d(data.getStringExtra(EXTRA_SCAN_RESULT));
                                break;
                            default:
                                break;
                        }
                    }
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public WeatherHome getTemp() {
        return null;
    }

    @Override
    public void actActivity(int type, Object str) {

    }

    @Override
    public void fragmentData(int type, Object str) {
        switch (type)
        {
            case 1:
                String string=(String) str;
                KLog.d(string);
                break;
            default:
        }

    }

    /**
     * 接受广播并处理
     */
    class TempReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action=intent.getAction();
            switch (action)
            {
                case Counts.BROADCAST_TEMP:
                    //收到广播后的处理
                    tempData = (WeatherHome) intent.getSerializableExtra("temp");
                    if (tempData != null && tempData.getInfo().getTemperature() != null) {
                        title.setText(tempData.getInfo().getTemperature() + "℃");
                        if (!"晴".equals(tempData.getInfo().getText())) {
                            imgTitle.setImageResource(R.drawable.yun);
                        } else {
                            imgTitle.setImageResource(R.drawable.sun);
                        }

                    } else {
                        title.setText(JctlApplication.getAppResources().getString(R.string.tqxq));
                    }
                    break;
                default:
            }
        }
    }

}
