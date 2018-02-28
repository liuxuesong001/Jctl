package com.colud.jctl.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * BaseActivity
 * Created by Jcty on 2017/3/1.
 */

public abstract class BaseActivity<P extends BasePesenter> extends AppCompatActivity implements UIInitA {

    protected P mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (onCreatePresenter() != null) {
            mPresenter = onCreatePresenter();
        }
        AppManager.newInstance().addActivivty(this);
        setContentView(getContentViewId());
        ButterKnife.bind(this);
        initViews();
        addListener();
        initData();

        //        if (isRegisterEventBus()){
        //            EventBus.getDefault().register(this);
        //        }
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
    protected void onStart() {
        //        IntentFilter filter = new IntentFilter();
        //        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        //        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        //        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        //        registerReceiver(new NetworkConnectChangedReceiver(), filter);
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.newInstance().removectivity(this);
        if (mPresenter != null) {
            onCreatePresenter().unSubscribe();
        }
        //        if (isRegisterEventBus())
        //        {
        //            EventBus.getDefault().unregister(this);
        //        }
    }

    protected abstract P onCreatePresenter();
    /**
     * 是否注册事件分发
     *
     * @return true绑定EventBus事件分发，默认不绑定，子类需要绑定的话复写此方法返回true.
     */
    //    protected boolean isRegisterEventBus() {
    //        return false;
    //    }

}
