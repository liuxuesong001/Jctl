package com.colud.jctl.ui.activity;

import android.os.Build;
import android.view.View;
import android.view.WindowManager;

import com.colud.jctl.JctlApplication;
import com.colud.jctl.base.BaseActivity;
import com.colud.jctl.base.BasePesenter;
import com.colud.jctl.ui.custom.TitleBar;
import com.colud.jctl.utils.BarStatusHeightUtil;
import com.jctl.colud.R;

import butterknife.BindView;

/**
 * 关于我们
 * Created by Jcty on 2017/3/28.
 */

public class AboutUsActivity extends BaseActivity {

    @BindView(R.id.login_toolbar)
    TitleBar toolbar;


    @Override
    protected BasePesenter onCreatePresenter() {
        return new BasePesenter();
    }

    @Override
    public int getContentViewId() {
        return R.layout.aboutas_activity;
    }

    @Override
    public void initViews() {
        setTitle();
    }

    protected void setTitle() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//不显示自带标题
        toolbar.setCenterTitle(JctlApplication.getAppResources().getString(R.string.more_gymw));
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

    @Override
    public void addListener() {

    }

    @Override
    public void initData() {

    }

}
