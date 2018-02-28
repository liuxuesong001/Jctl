package com.colud.jctl.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;

import com.colud.jctl.JctlApplication;
import com.colud.jctl.adapters.activity.BazaarAdapter;
import com.colud.jctl.base.BaseActivity;
import com.colud.jctl.base.BasePesenter;
import com.colud.jctl.bean.BazaarItem;
import com.colud.jctl.ui.custom.TitleBar;
import com.colud.jctl.utils.BarStatusHeightUtil;
import com.jctl.colud.R;

import java.util.List;

import butterknife.BindView;

/**
 * 市场动态
 * Created by Jcty on 2017/4/18.
 */

public class BazaarDetailActivity extends BaseActivity {

    @BindView(R.id.rv_bazaar)
    RecyclerView rv;
    @BindView(R.id.title_toolbar)
    TitleBar toolbar;

    private int titleType;

    private BazaarAdapter mAdapter;



    @Override
    protected BasePesenter onCreatePresenter() {
        return null;
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_bazaardetail;
    }

    @Override
    public void initViews() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//不显示自带标题
        toolbar.setNavigationIcon(R.drawable.img_back);
        //设置透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            toolbar.setPadding(0, BarStatusHeightUtil.getStatusBarHeightAll(this), 0, 0);
        }
        initAdapter();
    }

    protected void initAdapter() {
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(manager);
        mAdapter = new BazaarAdapter(this, null, true);
        rv.setAdapter(mAdapter);

    }

    @Override
    public void addListener() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void initData() {
        Intent intent= getIntent();
        List<BazaarItem.MktDynsBean> list = (List<BazaarItem.MktDynsBean>) intent.getSerializableExtra("address");
        titleType = intent.getIntExtra("type", 0);
        if (list!=null && list.size()>0 && titleType!=0){
            switch (titleType) {
                case 1:
                    toolbar.setCenterTitle(JctlApplication.getAppResources().getString(R.string.tv_hb));
                    break;
                case 2:
                    toolbar.setCenterTitle(JctlApplication.getAppResources().getString(R.string.tv_hd));
                    break;
                case 3:
                    toolbar.setCenterTitle(JctlApplication.getAppResources().getString(R.string.tv_hn));
                    break;
                case 4:
                    toolbar.setCenterTitle(JctlApplication.getAppResources().getString(R.string.tv_hz));
                    break;
                case 5:
                    toolbar.setCenterTitle(JctlApplication.getAppResources().getString(R.string.tv_xb));
                    break;
                case 6:
                    toolbar.setCenterTitle(JctlApplication.getAppResources().getString(R.string.tv_xn));
                    break;
                case 7:
                    toolbar.setCenterTitle(JctlApplication.getAppResources().getString(R.string.tv_db));
                    break;
                default:
                    break;
            }
            mAdapter.setNewData(list);
        }
    }

}
