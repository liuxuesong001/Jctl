package com.colud.jctl.ui.activity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.colud.jctl.JctlApplication;
import com.colud.jctl.base.BaseActivity;
import com.colud.jctl.base.BasePesenter;
import com.colud.jctl.bean.WeatherHome;
import com.colud.jctl.ui.custom.Html5WebView;
import com.colud.jctl.ui.custom.TitleBar;
import com.jctl.colud.R;

import butterknife.BindView;


/**
 * 天气详情页
 * Created by Jcty on 2017/3/13.
 */

public class WeatherAtivity extends BaseActivity {


    @BindView(R.id.title_toolbar)
    TitleBar toolbar;

    @BindView(R.id.web_layout)
    LinearLayout mLayout;

    private String mUrl;

    private Html5WebView mWebView;

    private WeatherHome tempData;


    @Override
    public int getContentViewId() {
        return R.layout.activity_weather;
    }

    @Override
    protected BasePesenter onCreatePresenter() {
        return null;
    }

    protected void setTitle() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//不显示自带标题
//        toolbar.setCenterTitle(JctlApplication.getAppResources().getString(R.string.tqxq));
        toolbar.setNavigationIcon(R.drawable.img_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //设置透明状态栏
        //		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        //			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //			toolbar.setPadding(0, BarStatusHeightUtil.getStatusBarHeightAll(this), 0, 0);
        //		}
        if (tempData != null && tempData.getInfo().getTemperature() != null) {
            toolbar.setCenterTitle(tempData.getInfo().getTemperature() + "℃");
        } else {
            toolbar.setCenterTitle(JctlApplication.getAppResources().getString(R.string.tqxq));
        }
    }

    @Override
    public void initViews() {

        getHomeData();

        setTitle();

        initWebView();

    }

    /**
     * 获取到数据
     */
    private void getHomeData() {

        tempData = (WeatherHome)getIntent().getSerializableExtra("temp");

        mUrl = getIntent().getStringExtra("url");
    }

    protected void initWebView() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams
                .MATCH_PARENT);
        mWebView = new Html5WebView(WeatherAtivity.this);
        mWebView.addProgressBar();
        mWebView.setLayoutParams(params);
        mLayout.addView(mWebView);

        if (mUrl != null && !"".equals(mUrl)) {
            mWebView.loadUrl(mUrl);
            //			toolbar.setCenterTitle(mUrl);
        }

    }

    @Override
    public void addListener() {

    }

    @Override
    public void initData() {

    }


    private void setupViewPager() {

        //		WeatherFragmentAdapter adapter = new WeatherFragmentAdapter(this, getSupportFragmentManager());
        //
        //		mWeatherFragment = WeatherFragment.newInstance();
        //		adapter.addFrag(mWeatherFragment);
        //
        //
        //		mViewPager.setAdapter(adapter);
        //		toolbarTab.setupWithViewPager(mViewPager);
        //		toolbarTab.getTabAt(0).setCustomView(adapter.getTabView(0, toolbarTab));
        //		mViewPager.setOffscreenPageLimit(1);
        //		mViewPager.setCurrentItem(0);

    }

}
