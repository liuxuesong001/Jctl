package com.colud.jctl.ui.activity;

import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.colud.jctl.JctlApplication;
import com.colud.jctl.base.BaseActivity;
import com.colud.jctl.base.BasePesenter;
import com.colud.jctl.ui.custom.TitleBar;
import com.colud.jctl.utils.BarStatusHeightUtil;
import com.jctl.colud.R;

import butterknife.BindView;

/**
 * 作物
 * Created by Jcty on 2017/4/1.
 */

public class CropActivity extends BaseActivity {


    @BindView(R.id.cropName)
    TextView cropName;
    @BindView(R.id.cropDate)
    TextView cropDate;
    @BindView(R.id.cropRen)
    TextView cropRen;
    @BindView(R.id.cropAlterDate)
    TextView cropAlterDate;
    @BindView(R.id.cropAlterRen)
    TextView cropAlterRen;
    @BindView(R.id.title_toolbar)
    TitleBar toolbar;


    @Override
    protected BasePesenter onCreatePresenter() {
        return null;
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_cropinfo;
    }

    @Override
    public void initViews() {
        setTitle();
    }

    protected void setTitle() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//不显示自带标题
        toolbar.setCenterTitle(JctlApplication.getAppResources().getString(R.string.tv_zwxx));
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
        String farmCrop = getIntent().getStringExtra("farmCrop");
        if (farmCrop != null && !"".equals(farmCrop)) {
            cropName.setText(farmCrop);
        }
    }

}
