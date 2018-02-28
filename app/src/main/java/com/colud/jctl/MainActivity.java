package com.colud.jctl;

import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.colud.jctl.adapters.MainFragmentAdapter;
import com.colud.jctl.api.IMutualListener;
import com.colud.jctl.api.KeyConstants;
import com.colud.jctl.base.AppManager;
import com.colud.jctl.base.BaseActivity;
import com.colud.jctl.base.BasePesenter;
import com.colud.jctl.bean.EarlyItme;
import com.colud.jctl.bean.ExitItem;
import com.colud.jctl.bean.UserItem;
import com.colud.jctl.bean.WeatherHome;
import com.colud.jctl.receiver.BroadCastManager;
import com.colud.jctl.ui.fragment.EarlyWarningFragment;
import com.colud.jctl.ui.fragment.HomeFragment;
import com.colud.jctl.ui.fragment.MineFragment;
import com.colud.jctl.ui.fragment.MoreFragment;
import com.colud.jctl.utils.ToastUtils;
import com.jctl.colud.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import q.rorbin.badgeview.QBadgeView;

import static com.colud.jctl.api.KeyConstants.LOGIN_SUCCESS;
import static com.colud.jctl.api.KeyConstants.LOGIN_SUCCESS_EXIT;
import static com.colud.jctl.counst.Counts.BROADCAST_EXIT;
import static com.colud.jctl.counst.Counts.BROADCAST_ISEXIT;
import static com.colud.jctl.counst.Counts.BROADCAST_LOGIN;
import static com.jctl.colud.R.string.more;


/**
 * MainActivity
 * Created by Jcty on 2017/3/1.
 */

public class MainActivity extends BaseActivity implements TabLayout.OnTabSelectedListener,IMutualListener {

    @BindView(R.id.home_ViewPager)
    ViewPager mViewPager;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    private List<String> mTabList = new ArrayList<>();

    private List<Fragment> mFragments = new ArrayList<>();

    private LocalBroadcastManager broadcastManager;

    private MainFragmentAdapter mFragmentAdapter;

    private int count = 0;

    private QBadgeView qBadgeView;

    private View tabView = null;

    private int[] tabIcons = {
            R.drawable.selector_home_normal,
            R.drawable.selector_yujing_normal,
            R.drawable.selector_mine_normal,
            R.drawable.selector_more_normal,
    };

    private int[] tabIconsPressed = {
            R.drawable.selector_home_pressed,
            R.drawable.selector_yujing_pressed,
            R.drawable.selector_mine_pressed,
            R.drawable.selector_more_pressed,
    };


    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    //    Handler mHandler = new Handler() {
    //        @Override
    //        public void handleMessage(Message msg) {
    //            switch (msg.what) {
    //                case 0:
    //
    //                    break;
    //                default:
    //            }
    //        }
    //    };
    //
    //    public void setHandler(Handler handler) {
    //        mHandler = handler;
    //    }

    @Override
    public void initViews() {
        receiveAdDownload();
        initFragmentList();
        initFragments();
        initLabeView();


    }

    /**
     * 配置角标
     */
    private void initLabeView() {
        //设置角标模式
        initBadgeViews();

    }

    @Override
    public void addListener() {
    }


    @Override
    public void initData() {
//        getTempData();
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    private void initFragmentList() {

        mFragments.clear();

        mFragments.add(HomeFragment.getInstance(JctlApplication.getAppResources().getString(R.string.home)));
        mFragments.add(EarlyWarningFragment.getInstance(JctlApplication.getAppResources().getString(R.string.early)));
        mFragments.add(MineFragment.getInstance(JctlApplication.getAppResources().getString(R.string.mine)));
        mFragments.add(MoreFragment.getInstance(JctlApplication.getAppResources().getString(R.string.more)));
    }


    @Override
    protected void onStop() {
        super.onStop();


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected BasePesenter onCreatePresenter() {
        return new BasePesenter();
    }

    /**
     * 初始化Fragment
     */
    private void initFragments() {

        initTabList();

        mFragmentAdapter = new MainFragmentAdapter(getSupportFragmentManager(), mTabList, getApplicationContext(), mFragments);

        mViewPager.setAdapter(mFragmentAdapter);
        mViewPager.setCurrentItem(0);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.addOnTabSelectedListener(this);

        mTabLayout.getTabAt(0).setCustomView(getTabView(0));
        mTabLayout.getTabAt(1).setCustomView(getTabView(1));
        mTabLayout.getTabAt(2).setCustomView(getTabView(2));
        mTabLayout.getTabAt(3).setCustomView(getTabView(3));
    }

    /**
     * 设置fragment 文字
     */
    private void initTabList() {
        mTabList.clear();
        mTabList.add(JctlApplication.getAppResources().getString(R.string.home));
        mTabList.add(JctlApplication.getAppResources().getString(R.string.early));
        mTabList.add(JctlApplication.getAppResources().getString(R.string.mine));
        mTabList.add(JctlApplication.getAppResources().getString(more));

    }

    /**
     * 设置角标
     */
    private void initBadgeViews() {
        qBadgeView = new QBadgeView(getApplicationContext());
        qBadgeView.bindTarget(getTabBageView(1));
        qBadgeView.setBadgeBackgroundColor(JctlApplication.getAppResources().getColor(R.color.red));
        qBadgeView.setBadgeTextColor(JctlApplication.getAppResources().getColor(R.color.white));
        qBadgeView.setBadgeGravity(Gravity.END | Gravity.TOP);
        qBadgeView.setGravityOffset(20, 4, true);
        //        qBadgeView.setBadgePadding(0, true);
        qBadgeView.setBadgeTextSize(10, true);
        count++;

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        changeTabStatus(tab, true);

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        changeTabStatus(tab, false);
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    /**
     * 注册广播接收器
     */
    private void receiveAdDownload() {
        broadcastManager = LocalBroadcastManager.getInstance(getApplicationContext());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(KeyConstants.MESSAGE_INFO);
//        intentFilter.addAction(BROADCAST_REG);
        broadcastManager.registerReceiver(mAdDownLoadReceiver, intentFilter);
    }

    /**
     * 接受预计推送 更新底部角标数量
     */
    private final BroadcastReceiver mAdDownLoadReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //            String action =intent.getAction();
            //            switch (action)
            //            {
            //                case BROADCAST_REG:
            //                    TabLayout.Tab tab = mTabLayout.getTabAt(0);
            //                    tab.select();
            //                    ToastUtils.showLong(JctlApplication.getAppResources().getString(R.string.login_success));
            //                    break;
            //                default:
            //            }
            //处理删除角标
            String adapter = intent.getStringExtra("----");
            if (adapter != null && adapter.equals("less")) {
                // 更新CustomView
                --count;
                qBadgeView.setBadgeNumber(--count);
                count++;
            } else if (adapter != null && adapter.equals("all")) {
                count = 0;
                qBadgeView.setBadgeNumber(count);
                qBadgeView.hide(true);
                count++;

            }

            //处理更新角标
            EarlyItme earlay = (EarlyItme) intent.getSerializableExtra("earlay");
            if (earlay != null) {

                qBadgeView.setBadgeNumber(count++);

            }
            int dbCount = intent.getIntExtra("count", 0);
            if (dbCount != 0) {
                qBadgeView.setBadgeNumber(dbCount);
                count = dbCount;
                count++;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        broadcastManager.unregisterReceiver(mAdDownLoadReceiver);

    }

    @Override
    public void onBackPressed() {
        if (isTaskRoot()) {
            AppManager.newInstance().appExit();
            //			Toast.makeText(getApplicationContext(), "当前activity是该应用唯一，一个存活的activity", Toast.LENGTH_SHORT).show();
        }
        super.onBackPressed();
    }

    /**
     * 点击和滑动更新
     *
     * @param tab
     * @param isSelector
     */

    private void changeTabStatus(TabLayout.Tab tab, boolean isSelector) {

        tabView = tab.getCustomView();

        ImageView img_title = (ImageView) tabView.findViewById(R.id.view_pager_image);
        TextView txt_title = (TextView) tabView.findViewById(R.id.view_pager_text);

        if (isSelector) {
            txt_title.setTextColor(JctlApplication.getAppResources().getColor(R.color.color_3AB07D));

            if (txt_title.getText().toString().equals("首页")) {
                img_title.setImageResource(R.drawable.selector_home_pressed);
            } else if (txt_title.getText().toString().equals("预警")) {
                img_title.setImageResource(R.drawable.selector_yujing_pressed);
            } else if (txt_title.getText().toString().equals("我的")) {
                img_title.setImageResource(R.drawable.selector_mine_pressed);
            } else if (txt_title.getText().toString().equals("更多")) {
                img_title.setImageResource(R.drawable.selector_more_pressed);
            }

        } else {
            txt_title.setTextColor(JctlApplication.getAppResources().getColor(R.color.color_9F9F9F));
            if (txt_title.getText().toString().equals("首页")) {
                img_title.setImageResource(R.drawable.selector_home_normal);
            } else if (txt_title.getText().toString().equals("预警")) {
                img_title.setImageResource(R.drawable.selector_yujing_normal);
            } else if (txt_title.getText().toString().equals("我的")) {
                img_title.setImageResource(R.drawable.selector_mine_normal);
            } else if (txt_title.getText().toString().equals("更多")) {
                img_title.setImageResource(R.drawable.selector_more_normal);
            }
        }


    }


    /**
     * 通过反射获取当前TabLayout 子view
     *
     * @param index
     * @return
     */
    public View getTabBageView(int index) {
        View tabView = null;
        TabLayout.Tab tab = mTabLayout.getTabAt(index);
        Field view = null;
        try {
            view = TabLayout.Tab.class.getDeclaredField("mView");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        view.setAccessible(true);
        try {
            tabView = (View) view.get(tab);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return tabView;
    }

    /**
     * 自定义View
     *
     * @param position
     * @return
     */
    public View getTabView(final int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.item_tab_layout, null);
        TextView tv = (TextView) view.findViewById(R.id.view_pager_text);
        final ImageView img = (ImageView) view.findViewById(R.id.view_pager_image);
        img.setImageResource(tabIcons[position]);
        tv.setText(mTabList.get(position));
        if (position == 0) {
            tv.setTextColor(JctlApplication.getAppResources().getColor(R.color.color_3AB07D));
            img.setImageResource(tabIconsPressed[position]);
        } else {
            tv.setTextColor(JctlApplication.getAppResources().getColor(R.color.color_9F9F9F));
            img.setImageResource(tabIcons[position]);
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPropertyAnim(img);
                mViewPager.setCurrentItem(position);
            }
        });
        return view;
    }

    private void startPropertyAnim(ImageView v) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(v, "rotation", 180f, 360f);
        anim.setDuration(500);
        anim.start();
    }


    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        TabLayout.Tab tab = mTabLayout.getTabAt(0);
        switch (resultCode) {
            case LOGIN_SUCCESS:
                if(data!=null) {
                    if (tab != null) {
                        tab.select();
                        ToastUtils.showLong(JctlApplication.getAppResources().getString(R.string.login_success));

                    }
                }
                break;
            case LOGIN_SUCCESS_EXIT:
                if (data!=null){
                    if (tab != null) {
                        tab.select();
                        ToastUtils.showLong(JctlApplication.getAppResources().getString(R.string.login_success));
                        UserItem userItem = (UserItem) data.getSerializableExtra("userData");
                        if (userItem != null) {
                            data.setAction(BROADCAST_LOGIN);
                            data.putExtra("user", userItem);
                            BroadCastManager.getInstance().sendBroadCast(this, data);

                        }
                    }
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


//    /**
//     * 返回天气数据
//     *
//     * @return
//     */
//    public WeatherHome getTempData() {
//
//        WeatherHome tempdata = (WeatherHome) getIntent().getSerializableExtra("temp");
//
//        return tempdata;
//    }


    @Override
    public WeatherHome getTemp() {

        WeatherHome tempdata = (WeatherHome) getIntent().getSerializableExtra("temp");

        return  tempdata;
    }

    @Override
    public void actActivity(int type, Object str) {
        Intent intent =new Intent();
        switch (type)
        {
            case 0:
                ExitItem data = (ExitItem) str;
                if (data!=null && data.getFlag()==1)
                {
                    TabLayout.Tab tab = mTabLayout.getTabAt(0);
                    if (tab != null) {
                        tab.select();
                        intent.putExtra("state",data);
                        intent.setAction(BROADCAST_EXIT);
                        BroadCastManager.getInstance().sendBroadCast(this,intent);
                    }


                }
                break;
            case 1:
                UserItem userItem = (UserItem)str;
                if (userItem!=null)
                {
                    intent.putExtra("user",userItem);
                    intent.setAction(BROADCAST_ISEXIT);
                    BroadCastManager.getInstance().sendBroadCast(this,intent);
                }
                break;
            default:
        }

    }

    @Override
    public void fragmentData(int type, Object str) {
        switch (type)
        {
            case 3:
                UserItem userItem=(UserItem)str;
                if (userItem!=null)
                {
                    Intent intent = new Intent();
                    intent.setAction(BROADCAST_LOGIN);
                    intent.putExtra("userData",userItem);
                    BroadCastManager.getInstance().sendBroadCast(this,intent);

                }
                break;
            default:
        }
        /**
         * 获取当前Fragment
         */
        //        Fragment  mFragment = (HomeFragment) mFragmentAdapter.instantiateItem(mViewPager, mViewPager.getCurrentItem());
        //        NewDynamicFragment fragmentB = new NewDynamicFragment();
        //        fragmentB.setNewsData((List<SplashBnBean.NewsBean>) str);
        //            mFragment.setNewsData(str);
        //        NewDynamicFragment.getInstance(str);

    }
}
