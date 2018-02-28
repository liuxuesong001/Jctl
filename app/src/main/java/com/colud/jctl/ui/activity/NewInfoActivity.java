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
import com.suke.widget.SwitchButton;

import butterknife.BindView;

/**
 * 新消息提醒
 * Created by Jcty on 2017/3/28.
 */

public class NewInfoActivity extends BaseActivity {

    @BindView(R.id.newinfo_sbtn)
    SwitchButton newinfoSbtn;
    @BindView(R.id.sound_sbtn)
    SwitchButton soundSbtn;
    @BindView(R.id.shake_sbtn)
    SwitchButton shakeSbtn;
    @BindView(R.id.title_toolbar)
    TitleBar toolbar;


    @Override
    protected BasePesenter onCreatePresenter() {
        return new BasePesenter();
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_newinfo;
    }

    @Override
    public void initViews() {
        setTitle();
        setSwitchBtn();
    }

    protected void setTitle() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//不显示自带标题
        toolbar.setCenterTitle(JctlApplication.getAppResources().getString(R.string.more_newinfo));
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

    protected void setSwitchBtn() {
        //		newinfoSbtn.setChecked(true);
        //		newinfoSbtn.isChecked();
        //		newinfoSbtn.toggle();     //switch state 状态
        //		newinfoSbtn.toggle(false);//switch without animation 动画
        //		newinfoSbtn.setShadowEffect(true);//disable shadow effect 阴影效果
        //		newinfoSbtn.setEnabled(false);//disable button 禁用
        //		newinfoSbtn.setEnableEffect(false);//disable the switch animation 禁用效果
        //		newinfoSbtn.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
        //			@Override
        //			public void onCheckedChanged(SwitchButton view, boolean isChecked) {
        //               //回调监听
        //				ToastUtils.showShort("点击");
        //			}
        //		});


    }

    @Override
    public void addListener() {

    }

    @Override
    public void initData() {

    }

}
