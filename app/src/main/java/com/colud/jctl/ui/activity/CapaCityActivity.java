package com.colud.jctl.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.colud.jctl.JctlApplication;
import com.colud.jctl.adapters.activity.CapaCityAdapter;
import com.colud.jctl.base.BaseActivity;
import com.colud.jctl.bean.CapaCityItem;
import com.colud.jctl.bean.NodeCollectionCycle;
import com.colud.jctl.mvp.contract.CapaCityContract;
import com.colud.jctl.mvp.presenter.CapaCityPresenter;
import com.colud.jctl.base.AppManager;
import com.colud.jctl.utils.ToastUtils;
import com.jctl.colud.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 智能控制页
 * Created by Jcty on 2017/4/7.
 */

public class CapaCityActivity extends BaseActivity<CapaCityPresenter> implements CapaCityContract.View {


    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.btn_back)
    ImageButton btnBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.iv_bar)
    ImageButton addBtn;
    private CapaCityAdapter adapter;

    private NodeCollectionCycle item;

    @OnClick({R.id.btn_back, R.id.ll_back, R.id.iv_bar})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                AppManager.newInstance().finishCurrentActivity();
                break;
            case R.id.ll_back:
                AppManager.newInstance().finishCurrentActivity();
                break;
            case R.id.tv_content:
                AppManager.newInstance().finishCurrentActivity();
                break;
            case R.id.iv_bar:
                Intent intent = new Intent(getApplicationContext(), IntelControlActivity.class);
                intent.putExtra("nodeId", item.getNodeId());
                startActivity(intent);
                break;
        }
    }


    @Override
    public int getContentViewId() {
        return R.layout.activity_intel_zq;
    }

    @Override
    public void initViews() {
        tvTitle.setText(JctlApplication.getAppResources().getString(R.string.tv_znkz));
        addBtn.setVisibility(View.VISIBLE);
        initAdapter();

    }

    protected void initAdapter() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(manager);
        adapter = new CapaCityAdapter(this, null, true);
        rv.setAdapter(adapter);
        //		adapter=new MsgInformAdapter(this,mlist,true);
        // 为SwipeRecyclerViewItem创建菜单就两句话，不错就是这么简单：
        //		mAdapter = new IntelPeriodiAdapter(sList);
        //		mAdapter.setOnItemClickListener(onItemClickListener);
        //		rv.setAdapter(mAdapter);
        //		mAdapter.setStateTouch(STATE_TOUCH);
    }

    @Override
    protected void onResume() {
        super.onResume();
        item = (NodeCollectionCycle) getIntent().getSerializableExtra("cycleItem");
        if (item != null && item.getNodeId() != null) {
            mPresenter.setCapaCity(item.getNodeId());
        }

    }

    @Override
    public void addListener() {

    }

    @Override
    public void initData() {
    }


    @Override
    protected CapaCityPresenter onCreatePresenter() {
        return new CapaCityPresenter(this);
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void onSucceed(CapaCityItem data) {
        if (data != null && data.getInfo().size() > 0) {
            adapter.setNewData(data.getInfo());
        }
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
}
