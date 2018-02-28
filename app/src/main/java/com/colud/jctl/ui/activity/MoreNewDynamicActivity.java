package com.colud.jctl.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;

import com.colud.jctl.JctlApplication;
import com.colud.jctl.adapters.fragment.NewDynamicAdapter;
import com.colud.jctl.api.KeyConstants;
import com.colud.jctl.base.BaseActivity;
import com.colud.jctl.base.BasePesenter;
import com.colud.jctl.bean.SplashBnBean;
import com.colud.jctl.ui.custom.TitleBar;
import com.colud.jctl.utils.BarStatusHeightUtil;
import com.jctl.colud.R;
import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.interfaces.OnItemClickListener;

import java.util.List;

import butterknife.BindView;

/**
 * 更多也没跳转
 * Created by Jcty on 2017/4/11.
 */

public class MoreNewDynamicActivity extends BaseActivity  {

    @BindView(R.id.more_rv)
    RecyclerView rv;
    @BindView(R.id.title_toolbar)
    TitleBar toolbar;

    private LinearLayoutManager layoutManager;
    private NewDynamicAdapter mAdapter;

    @Override
    protected BasePesenter onCreatePresenter() {
        return null;
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_more_newdynamic;
    }

    @Override
    public void initViews() {
        initToolbar();
        initAdapter();

    }

    private void initToolbar() {
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
        mAdapter.setOnItemClickListener(new OnItemClickListener<SplashBnBean.NewsBean>() {
            @Override
            public void onItemClick(ViewHolder viewHolder, SplashBnBean.NewsBean data, int position) {
                //				Intent intent=new Intent(getApplicationContext(), NewInfoDetailActivity.class);
                //				intent.putExtra("url",data.getUrl());
                //				startActivity(intent);
                Intent intent = new Intent(MoreNewDynamicActivity.this, NewDatailActivity.class);
                intent.putExtra("infoBean", data);
                startActivity(intent);
            }
        });
    }

    protected void initAdapter() {
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(layoutManager);
        mAdapter = new NewDynamicAdapter(getApplicationContext(), null, true);
        rv.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        List<SplashBnBean.NewsBean>data =(List<SplashBnBean.NewsBean>) getIntent().getSerializableExtra("news");
        if (data!=null)
        {
            mAdapter.setNewData(data);
        }else {
            data = (List<SplashBnBean.NewsBean>) JctlApplication.getCache().getAsObject(KeyConstants.KAY_NEWINFO);
            mAdapter.setNewData(data);
        }
    }

}
