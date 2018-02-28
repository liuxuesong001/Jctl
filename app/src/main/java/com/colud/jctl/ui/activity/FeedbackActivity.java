package com.colud.jctl.ui.activity;

import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.colud.jctl.JctlApplication;
import com.colud.jctl.base.BaseActivity;
import com.colud.jctl.base.BasePesenter;
import com.colud.jctl.ui.custom.TitleBar;
import com.colud.jctl.utils.BarStatusHeightUtil;
import com.colud.jctl.utils.EditTextUtil;
import com.colud.jctl.utils.ToastUtils;
import com.jctl.colud.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 意见反馈
 * Created by Jcty on 2017/3/28.
 */

public class FeedbackActivity extends BaseActivity {

    @BindView(R.id.title_cotent)
    TextView tvContent;
    @BindView(R.id.feed_et)
    EditText feedEt;
    @BindView(R.id.login_toolbar)
    TitleBar toolbar;

    @OnClick({R.id.title_cotent})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_cotent:
                ToastUtils.showShort("已提交服务器，感谢您的支持!");
                break;
        }
    }

    @Override
    protected BasePesenter onCreatePresenter() {
        return new BasePesenter();
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_feedback;
    }

    @Override
    public void initViews() {
        setTitle();
        EditTextUtil.showSoftInputFromWindow(this, feedEt);

    }

    protected void setTitle() {
        tvContent.setVisibility(View.VISIBLE);
        tvContent.setText(JctlApplication.getAppResources().getString(R.string.mine_tj));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//不显示自带标题
        toolbar.setCenterTitle(JctlApplication.getAppResources().getString(R.string.more_yjfk));
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
