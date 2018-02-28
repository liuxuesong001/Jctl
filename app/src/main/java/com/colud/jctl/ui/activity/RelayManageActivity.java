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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.colud.jctl.JctlApplication;
import com.colud.jctl.adapters.activity.FacilityManageAdapter;
import com.colud.jctl.api.KeyConstants;
import com.colud.jctl.base.BaseActivity;
import com.colud.jctl.bean.FacilityItem;
import com.colud.jctl.mvp.contract.FacilityManageContract;
import com.colud.jctl.mvp.presenter.FacilityManagePresenter;
import com.colud.jctl.ui.custom.CleanEditText;
import com.colud.jctl.ui.custom.TitleBar;
import com.colud.jctl.utils.BarStatusHeightUtil;
import com.colud.jctl.utils.ToastUtils;
import com.jctl.colud.R;
import com.othershe.baseadapter.interfaces.OnLoadMoreListener;
import com.socks.library.KLog;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 设备管理
 * Created by Jcty on 2017/4/7.
 */

public class RelayManageActivity extends BaseActivity<FacilityManagePresenter> implements FacilityManageContract.View {

    @BindView(R.id.title_right_img)
    ImageView ivBar;
    @BindView(R.id.faci_rv)
    RecyclerView faciRv;
    @BindView(R.id.faci_sr)
    SwipeRefreshLayout sr;
    @BindView(R.id.search_Rl_facilit)
    RelativeLayout searchRl;
    @BindView(R.id.et_search)
    CleanEditText etSearch;
    @BindView(R.id.title_toolbar)
    TitleBar toolbar;


    private FacilityManageAdapter mAdapter;

    private boolean down = false;

    private Handler handler = new Handler();

    private Dialog progressDialog;

    private int pageCount = 1;

    private boolean isFailed = false;

    private boolean isFailedEt = false;

    private Map<String, String> map = new ArrayMap<>();

    private String relayId = "";

    @OnClick({R.id.title_right_img})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_right_img:
                Intent intent = new Intent(getApplicationContext(), AddRelayActivity.class);
                startActivity(intent);
                break;
        }

    }


    @Override
    public int getContentViewId() {
        return R.layout.activity_facilitmanage;
    }


    @Override
    protected void onResume() {
        super.onResume();
        searchRl.setFocusable(true);
        searchRl.setFocusableInTouchMode(true);
        searchRl.requestFocus();
    }

    @Override
    protected FacilityManagePresenter onCreatePresenter() {
        return new FacilityManagePresenter(this);
    }

    @Override
    public void initViews() {
        ivBar.setVisibility(View.VISIBLE);
        //		farmTvA.setText(JctlApplication.getAppResources().getString(R.string.tv_zjbh));
        //		farmTvB.setText(JctlApplication.getAppResources().getString(R.string.tv_ssnc));
        //		farmTvC.setText(JctlApplication.getAppResources().getString(R.string.tv_zjsl));
        //		farmTvD.setText(JctlApplication.getAppResources().getString(R.string.tv_dl));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//不显示自带标题
        toolbar.setCenterTitle(JctlApplication.getAppResources().getString(R.string.text_sbgl));
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

        //		initAdapter();

    }

    protected void initAdapter(boolean isFailed) {

        if (isFailed) {
            mAdapter = new FacilityManageAdapter(this, null, true);

        } else {
            mAdapter = new FacilityManageAdapter(this, null, false);
        }


        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        faciRv.setLayoutManager(manager);
        moreAdapter();
        faciRv.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();


    }

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
                            pageCount = 1;
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
                        map.clear();
                        map.put("singleId", KeyConstants.USER_SINGLEID);
                        map.put("relayNum", v.getText().toString().trim());
                        mPresenter.setFacilityManageData(map);
                        isFailed = true;
                    } else {
                        ToastUtils.showLong("请输入中继编号");
                    }

                }
                return false;
            }
        });
    }

    //处理输入检索请求
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
                //				isFailed=true;
                //				up=false;
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
        //		userId=JctlApplication.getCache().getAsString(KeyConstants.KAY_USERID);
        if (!"".equals(KeyConstants.USER_SINGLEID)) {
            map.clear();
            map.put("singleId", KeyConstants.USER_SINGLEID);
            mPresenter.setFacilityManageData(map);
        }
    }

    @Override
    public void showDialog() {
        //         JctlApplication.getGifLoadingView().show(getFragmentManager(),"");
        if (progressDialog == null) {
            progressDialog = new Dialog(RelayManageActivity.this, R.style.progress_dialog);
            progressDialog.setContentView(R.layout.dialog);
            progressDialog.setCancelable(true);
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            TextView msg = (TextView) progressDialog.findViewById(R.id.id_tv_loadingmsg);
            msg.setText("加载中...");
            progressDialog.show();
        }
    }

    @Override
    public void onSucceed(FacilityItem data) {
        if (data != null && 1 == data.getFlag()) {

            if (data.getInfo() != null && data.getInfo().size() > 0) {

                if (down) {

                    if (data.getInfo().size() >= 10) {

                        initAdapter(true);
                        mAdapter.setNewData(data.getInfo());

                    } else {

                        initAdapter(false);
                        mAdapter.setNewData(data.getInfo());
                        ToastUtils.showLong(R.string.tv_ending);
                    }
                    sr.setRefreshing(false);

                } else {

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
            //			if (data.getList()!=null&&data.getList().size()>0){
            //				data.getList().clear();
            //				mAdapter.notifyDataSetChanged();
            //			}
        }

    }

    @Override
    public void onSucceedMore(FacilityItem data) {
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
        sr.setRefreshing(false);
    }

    @Override
    public void onFail(String err) {
        KLog.d(err);
        sr.setRefreshing(false);
    }

    @Override
    public void hideDialog() {
        //      JctlApplication.getGifLoadingView().dismiss();
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    private void loadMore(final boolean isFailed) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                map.clear();
                if (isFailed) {
                    pageCount++;
                    map.put("singleId", KeyConstants.USER_SINGLEID);
                    map.put("pageNum", String.valueOf(pageCount));
                    map.put("name", etSearch.getText().toString().trim());
                    mPresenter.setFacilityManageMore(map);
                    isFailedEt = false;
                } else {
                    pageCount++;
                    map.put("singleId", KeyConstants.USER_SINGLEID);
                    map.put("pageNum", String.valueOf(pageCount));
                    mPresenter.setFacilityManageMore(map);
                }
            }
        }, 2000);
    }
}
