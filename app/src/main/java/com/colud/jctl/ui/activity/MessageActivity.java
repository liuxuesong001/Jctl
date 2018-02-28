package com.colud.jctl.ui.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.colud.jctl.base.BaseActivity;
import com.colud.jctl.base.BasePesenter;
import com.jctl.colud.R;

import butterknife.BindView;

/**
 * Created by Jcty on 2017/3/15.
 */

public class MessageActivity extends BaseActivity {
    //	@BindView(R.id.toolbar_title)
    //	CustomToolbar toolbarTitle;
    @BindView(R.id.recyclerview_msg)
    RecyclerView recyclerviewMsg;
    @BindView(R.id.swiperefreshlayout_msg)
    SwipeRefreshLayout swiperefreshlayoutMsg;

    @Override
    protected BasePesenter onCreatePresenter() {
        return null;
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_message;
    }

    @Override
    public void initViews() {
        setToolbarTitle();

    }

    /**
     *
     */
    protected void setToolbarTitle() {
        //		toolbarTitle.setMainTitle("消息");
        //		toolbarTitle.setMainTitleLeftDrawable(R.mipmap.jctl_launcher);
        //		toolbarTitle.setToolbarBackground(R.color.color_3AB07D);
        //		toolbarTitle.setMainTitleRightText("管理");
    }

    @Override
    public void addListener() {

    }

    @Override
    public void initData() {

    }

}
