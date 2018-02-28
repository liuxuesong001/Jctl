package com.colud.jctl.ui.activity;

import android.os.Build;
import android.view.View;
import android.view.WindowManager;

import com.colud.jctl.JctlApplication;
import com.colud.jctl.base.BaseActivity;
import com.colud.jctl.base.BasePesenter;
import com.colud.jctl.ui.custom.TitleBar;
import com.colud.jctl.utils.BarStatusHeightUtil;
import com.colud.jctl.utils.ToastUtils;
import com.jctl.colud.R;

import butterknife.BindView;

/**
 * Created by Jcty on 2017/3/22.
 */

public class HelpCenterActivity extends BaseActivity {

    @BindView(R.id.login_toolbar)
    TitleBar toolbar;

    @Override
    protected BasePesenter onCreatePresenter() {
        return new BasePesenter();
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_help_center;
    }

    @Override
    public void initViews() {
        ToastUtils.showLong("帮助中心页面,只是展示样式界面，后期在继续完善，敬请期待。");
        setToolBarTitle();
    }

    /**
     * 设置ToolBar参数
     */
    public void setToolBarTitle() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//不显示自带标题
        toolbar.setCenterTitle(JctlApplication.getAppResources().getString(R.string.mine_help));
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
