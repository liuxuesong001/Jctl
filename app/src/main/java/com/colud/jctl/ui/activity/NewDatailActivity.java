package com.colud.jctl.ui.activity;

import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.colud.jctl.JctlApplication;
import com.colud.jctl.base.BaseActivity;
import com.colud.jctl.base.BasePesenter;
import com.colud.jctl.bean.SplashBnBean;
import com.colud.jctl.ui.custom.MyTextView;
import com.colud.jctl.ui.custom.TitleBar;
import com.colud.jctl.utils.BarStatusHeightUtil;
import com.jctl.colud.R;

import butterknife.BindView;

/**
 * Created by Jcty on 2017/5/5.
 */

public class NewDatailActivity extends BaseActivity {

    @BindView(R.id.title_toolbar)
    TitleBar toolbar;
    @BindView(R.id.new_title)
    MyTextView newTitle;
    @BindView(R.id.new_date)
    TextView newDate;
    @BindView(R.id.new_source)
    TextView newSource;
    @BindView(R.id.new_content)
    TextView newContent;

    @Override
    protected BasePesenter onCreatePresenter() {
        return null;
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_new_datail;
    }

    @Override
    public void initViews() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//不显示自带标题
        toolbar.setNavigationIcon(R.drawable.img_back);
        toolbar.setCenterTitle(JctlApplication.getAppResources().getString(R.string.tv_xwdt));
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
        SplashBnBean.NewsBean infoBean = (SplashBnBean.NewsBean) getIntent().getSerializableExtra("infoBean");
        if (infoBean != null) {
            newTitle.setText(infoBean.getTitle());
            newDate.setText("时间:" + infoBean.getDataTime());
            newContent.setText("\t\t\t" + infoBean.getContent());

        }

    }


}
