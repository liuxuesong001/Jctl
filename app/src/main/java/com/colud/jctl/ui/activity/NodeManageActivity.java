package com.colud.jctl.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v4.util.ArrayMap;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.colud.jctl.JctlApplication;
import com.colud.jctl.adapters.activity.NodeManageAdapter;
import com.colud.jctl.api.KeyConstants;
import com.colud.jctl.base.BaseActivity;
import com.colud.jctl.bean.NodeManageItem;
import com.colud.jctl.mvp.contract.NodeManageContract;
import com.colud.jctl.mvp.presenter.NodeManagePresenter;
import com.colud.jctl.ui.custom.CleanEditText;
import com.colud.jctl.ui.custom.TitleBar;
import com.colud.jctl.utils.BarStatusHeightUtil;
import com.colud.jctl.utils.ToastUtils;
import com.jctl.colud.R;
import com.othershe.baseadapter.interfaces.OnLoadMoreListener;
import com.socks.library.KLog;

import java.util.Map;

import butterknife.BindView;

/**
 * 节点管理
 * Created by Jcty on 2017/4/7.
 */

public class NodeManageActivity extends BaseActivity<NodeManagePresenter> implements NodeManageContract.View {

    @BindView(R.id.title_toolbar)
    TitleBar toolbar;

    @BindView(R.id.node_rv)
    RecyclerView faciRv;
    @BindView(R.id.node_sr)
    SwipeRefreshLayout sr;
    @BindView(R.id.search_Rl_node)
    RelativeLayout searchRl;
    @BindView(R.id.et_search)
    CleanEditText etSearch;

    private String id;


    private NodeManageAdapter mAdapter;

    private Handler handler = new Handler();

    private boolean down = false;

    private boolean isFailed = false;

    private boolean isFailedEt = false;

    private int pageCount = 1;

    private Dialog progressDialog;

    private Map<String, String> map = new ArrayMap<>();

    private String nodeNum = "";


    @Override
    protected void onResume() {
        super.onResume();

        searchRl.setFocusable(true);
        searchRl.setFocusableInTouchMode(true);
        searchRl.requestFocus();
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_node_layout;
    }

    @Override
    public void initViews() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//不显示自带标题
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
        if (!"".equals(nodeNum)) {
            toolbar.setCenterTitle(nodeNum + JctlApplication.getAppResources().getString(R.string.mine_jdgl));
        } else {
            toolbar.setCenterTitle(JctlApplication.getAppResources().getString(R.string.mine_jdgl));
        }

        //		initAdapter();

    }

    protected void initAdapter(boolean isFailed) {
        //		mAdapter=new NodeManageAdapter(this,null,true);
        //		LinearLayoutManager manager = new LinearLayoutManager(this);
        //		manager.setOrientation(LinearLayoutManager.VERTICAL);
        //		faciRv.setLayoutManager(manager);
        //		mAdapter = new NodeManageAdapter(this, mlist);
        //		adapter.setOnItemClickListener(onItemClickListener);
        //		faciRv.setAdapter(mAdapter);
        if (isFailed) {
            mAdapter = new NodeManageAdapter(this, null, true);

        } else {

            mAdapter = new NodeManageAdapter(this, null, false);
        }
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        faciRv.setLayoutManager(manager);
        moreAdapter();
        faciRv.setAdapter(mAdapter);

    }

    /**
     * 加载更多
     */
    protected void moreAdapter() {
        //初始化 开始加载更多的loading View
        mAdapter.setLoadingView(R.layout.load_loading_layout);
        //设置加载更多触发的事件监听
        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {

                loadMore(isFailedEt);
            }
        });

    }

    @Override
    public void addListener() {
        etSearch.addTextChangedListener(watcher);
        /**
         * 下拉刷新事件
         */
        sr.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        down = true;
                        if (down) {
                            initData();
                        }
                    }
                }, 2000);
            }
        });

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (!"".equals(v.getText().toString())) {
                        isFailed = true;
                        map.clear();
                        map.put("singleId", KeyConstants.USER_SINGLEID);
                        map.put("relayId", id);
                        map.put("nodeNum", v.getText().toString().trim());
                        mPresenter.setNodeManageData(map);
                    } else {
                        ToastUtils.showLong("请输入农场名称或者地址");
                    }

                }
                return false;
            }
        });

    }

    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //			KAY_FARM_MANAGE
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            //			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (s.length() == 0) {
                //				getData();
                searchRl.setFocusable(true);
                searchRl.setFocusableInTouchMode(true);
                searchRl.requestFocus();
                //				imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0); //强制隐藏键盘
            }
        }
    };

    @Override
    public void initData() {

        Intent intent = getIntent();
        nodeNum = intent.getStringExtra("node");
        id = intent.getStringExtra("id");
        //		if (id!=null&&!"".equals(id)){
        //			mPresenter.setNodeManageData(id);
        //		}
        if (id != null && !"".equals(id) && !"".equals(KeyConstants.USER_SINGLEID)) {
            map.clear();
            //            @Query("relayId") String relayId,
            //            @Query("singleId") String userId)
            map.put("singleId", KeyConstants.USER_SINGLEID);
            map.put("relayId", id);
            mPresenter.setNodeManageData(map);
        }

    }

    @Override
    public void showDialog() {
        if (progressDialog == null) {
            progressDialog = new Dialog(NodeManageActivity.this, R.style.progress_dialog);
            progressDialog.setContentView(R.layout.dialog);
            progressDialog.setCancelable(true);
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            TextView msg = (TextView) progressDialog.findViewById(R.id.id_tv_loadingmsg);
            msg.setText("加载中...");
            progressDialog.show();
        }
    }

    @Override
    public void onSucceed(NodeManageItem data) {

        if (data != null && 1 == data.getFlag()) {

            if (data.getInfo() != null && data.getInfo().size() > 0) {

                if (down) {

                    moreAdapter();
                    mAdapter.setNewData(data.getInfo());

                    //					loadMore(isFailedEt);

                    sr.setRefreshing(false);


                } else {

                    //					mAdapter.setNewData(data.getInfo());
                    if (data.getInfo().size() >= 10) {

                        initAdapter(true);
                        mAdapter.setNewData(data.getInfo());

                    } else {

                        initAdapter(false);
                        mAdapter.setNewData(data.getInfo());
                        ToastUtils.showLong(R.string.tv_ending);
                    }

                }
            }

            mAdapter.notifyDataSetChanged();

        } else {
            ToastUtils.showShort(data.getMsg());
            sr.setRefreshing(false);
            if (mAdapter.getItemCount() > 0) {
                mAdapter.remove(0);
            }
        }

    }

    @Override
    public void onSucceedMore(NodeManageItem data) {
        if (data.getInfo() != null && data.getInfo().size() > 0) {
            mAdapter.setLoadMoreData(data.getInfo());
        } else {
            //加载完成，更新footer view提示
            mAdapter.setLoadEndView(R.layout.load_end_layout);
            ToastUtils.showLong(data.getMsg());
            pageCount = 1;
        }
    }

    @Override
    public void onFailure(String err, Throwable e) {
        ToastUtils.showLong(err);
    }

    @Override
    public void onFail(String err) {
        KLog.v(err);
        sr.setRefreshing(false);
    }

    @Override
    public void hideDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    protected NodeManagePresenter onCreatePresenter() {
        return new NodeManagePresenter(this);
    }


    private void loadMore(final boolean isFailed) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                map.clear();
                if (isFailed) {
                    pageCount++;
                    map.put("singleId", KeyConstants.USER_SINGLEID);
                    map.put("relayId", id);
                    map.put("nodeNum", etSearch.getText().toString().trim());
                    mPresenter.setNodeManageMore(map);
                    isFailedEt = false;
                } else {
                    pageCount++;
                    map.put("singleId", KeyConstants.USER_SINGLEID);
                    map.put("relayId", id);
                    map.put("pageNum", String.valueOf(pageCount));
                    mPresenter.setNodeManageMore(map);
                }

            }
        }, 2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        progressDialog = null;
    }
}
