package com.colud.jctl.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.util.ArrayMap;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.colud.jctl.JctlApplication;
import com.colud.jctl.adapters.activity.NodeDetailsFragmentPagerAdapter;
import com.colud.jctl.api.KeyConstants;
import com.colud.jctl.base.BaseActivity;
import com.colud.jctl.bean.NodeDataManageItem;
import com.colud.jctl.mvp.contract.NodeDataManageContract;
import com.colud.jctl.mvp.presenter.NodeDataManagePresenter;
import com.colud.jctl.receiver.BroadCastManager;
import com.colud.jctl.ui.custom.NoScrollViewPager;
import com.colud.jctl.ui.custom.TitleBar;
import com.colud.jctl.utils.BarStatusHeightUtil;
import com.colud.jctl.utils.ToastUtils;
import com.jctl.colud.R;

import java.io.Serializable;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 设备页面单个节点管理
 * Created by Jcty on 2017/4/5.
 */

public class NodeDetailsActivity extends BaseActivity<NodeDataManagePresenter> implements NodeDataManageContract.View, TabLayout.OnTabSelectedListener {

    @BindView(R.id.title_toolbar)
    TitleBar toolbar;
    @BindView(R.id.node_details_tableLayout)
    TabLayout nodeTableLayout;
    @BindView(R.id.node_detail_ViewPager)
    NoScrollViewPager nodeViewPager;
    @BindView(R.id.title_cotent)
    TextView tvContent;

    private int NOW = 0;

    private NodeDetailsFragmentPagerAdapter nodeAdapter;

    protected TabLayout.Tab NodeDetailsManageFragment;
    protected TabLayout.Tab NodeDetailsFragment;

    private String id = "";

    private String nodeNum = "";

    private Map<String, String> map = new ArrayMap<>();


    @OnClick(R.id.title_cotent)
    public void onClick() {
        if ("编辑".equals(tvContent.getText().toString().trim())) {
            tvContent.setText("完成");
            //发送广播
            Intent intent = new Intent();
            intent.putExtra("yes", "yes");
            intent.putExtra("id", id);
            intent.setAction(KeyConstants.NODE_INTENT_MANAGE);
            BroadCastManager.getInstance().sendBroadCast(this, intent);
        } else {
            tvContent.setText("编辑");
            //发送广播
            Intent intent = new Intent();
            intent.putExtra("yes", "no");
            intent.setAction(KeyConstants.NODE_INTENT_MANAGE);
            BroadCastManager.getInstance().sendBroadCast(this, intent);
        }
    }


    @Override
    public int getContentViewId() {
        return R.layout.activity_node_details;
    }

    @Override
    public void initViews() {
        nodeTableLayout.setOnTabSelectedListener(this);
        //		if (nodeNum!=null&&!"".equals(nodeNum))
        //			tvTitle.setText(JctlApplication.getAppResources().getString(R.string.tv_node)+nodeNum);
        tvContent.setVisibility(View.VISIBLE);
        tvContent.setText(JctlApplication.getAppResources().getString(R.string.mine_bj));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//不显示自带标题
        if (nodeNum != null && !"".equals(nodeNum)) {
            toolbar.setCenterTitle(nodeNum);
        } else {
            toolbar.setCenterTitle(JctlApplication.getAppResources().getString(R.string.tv_jdxq));

        }
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
        setViewPager();
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        nodeNum = intent.getStringExtra("nodeNum");
        if (id != null && !"".equals(id)) {
            map.clear();
            map.put("singleId", KeyConstants.USER_SINGLEID);
            map.put("id", id);
            mPresenter.setNodeManage(map);
        }
        JctlApplication.getCache().put(KeyConstants.KAY_NODE_ID, id);
    }

    /**
     * 设置viewpager
     */
    protected void setViewPager() {
        //绑定viewpager
        nodeAdapter = new NodeDetailsFragmentPagerAdapter(getSupportFragmentManager());
        nodeViewPager.setAdapter(nodeAdapter);
        nodeTableLayout.setupWithViewPager(nodeViewPager);
        //获取fragment位置
        NodeDetailsManageFragment = nodeTableLayout.getTabAt(0);
        NodeDetailsFragment = nodeTableLayout.getTabAt(1);
    }


    @Override
    public void showDialog() {

    }

    @Override
    public void onSucceed(NodeDataManageItem data) {
        if (data != null && 1 == data.getFlag()) {
            //发送广播
            Intent a = new Intent();
            a.putExtra("nodeDatails", data.getNode());
            a.putExtra("nodeName", (Serializable) data.getnList());
            a.putExtra("nodeId", (Serializable) data.getIdList());
            a.setAction(KeyConstants.NODE_DATAILS_INTENT);
            BroadCastManager.getInstance().sendBroadCast(NodeDetailsActivity.this, a);

            //发送广播
            Intent b = new Intent();
            b.putExtra("nodeManage", data.getData());
            b.putExtra("id", id);
            b.setAction(KeyConstants.NODE_MANAGE_INTENT);
            BroadCastManager.getInstance().sendBroadCast(NodeDetailsActivity.this, b);
        } else {
            ToastUtils.showLong(data.getMsg());
        }
    }

    @Override
    public void onSucceedUpdate(NodeDataManageItem data) {

    }

    @Override
    public void onFailure(String err, Throwable e) {
        ToastUtils.showLong(err);
    }

    @Override
    public void onFail(String err) {
    }

    @Override
    public void hideDialog() {

    }

    @Override
    protected NodeDataManagePresenter onCreatePresenter() {
        return new NodeDataManagePresenter(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        JctlApplication.getCache().remove(KeyConstants.KAY_NODEDATA_MANAGE);
        JctlApplication.getCache().remove(KeyConstants.KAY_NODE_DETAIL);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if (tab.getPosition() == NOW) {
            tvContent.setVisibility(View.VISIBLE);
        } else {
            tvContent.setVisibility(View.GONE);
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
