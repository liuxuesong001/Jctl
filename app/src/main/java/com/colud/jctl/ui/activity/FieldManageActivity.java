package com.colud.jctl.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v4.util.ArrayMap;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
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
import com.colud.jctl.adapters.activity.FieldManageAdapter;
import com.colud.jctl.api.KeyConstants;
import com.colud.jctl.base.BaseActivity;
import com.colud.jctl.bean.FieldManageItem;
import com.colud.jctl.mvp.contract.FieldManageContract;
import com.colud.jctl.mvp.presenter.FieldManagePresenter;
import com.colud.jctl.ui.custom.CleanEditText;
import com.colud.jctl.ui.custom.TitleBar;
import com.colud.jctl.utils.BarStatusHeightUtil;
import com.colud.jctl.utils.ToastUtils;
import com.jctl.colud.R;
import com.othershe.baseadapter.interfaces.OnLoadMoreListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 农田管理
 * Created by Jcty on 2017/3/29.
 */

public class FieldManageActivity extends BaseActivity<FieldManagePresenter> implements FieldManageContract.View {

    @BindView(R.id.et_search)
    CleanEditText etSearch;
    @BindView(R.id.field_rv)
    SwipeMenuRecyclerView fieldRv;
    @BindView(R.id.farm_srlayout)
    SwipeRefreshLayout srl;
    @BindView(R.id.search_Rl_field)
    RelativeLayout searchRl;

    @BindView(R.id.title_toolbar)
    TitleBar toolbar;
    @BindView(R.id.title_right_img)
    ImageView imgRight;


    private String id;

    private boolean down = false;

    private FieldManageAdapter mAdapter;


    private Handler handler = new Handler();

    private int pageCount = 1;

    private Map<String, String> map = new ArrayMap<>();

    private boolean isFailed = false;

    private boolean isFailedEt = false;

    private Dialog progressDialog;


    @OnClick({R.id.title_right_img})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_right_img:
                Intent intent = new Intent(getApplicationContext(), AddFieldActivity.class);
                intent.putExtra("farmerId", id);
                startActivity(intent);
                break;
        }
    }


    @Override
    public int getContentViewId() {
        return R.layout.activity_fieldmanage;
    }

    @Override
    public void initViews() {
        setTitle();
        //		initAdapter();
    }

    @Override
    protected void onResume() {
        super.onResume();

        searchRl.setFocusable(true);
        searchRl.setFocusableInTouchMode(true);
        searchRl.requestFocus();


    }

    protected void initAdapter(boolean isFailed) {
        if (isFailed) {
            mAdapter = new FieldManageAdapter(this, null, true);
        } else {
            mAdapter = new FieldManageAdapter(this, null, false);
        }

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        fieldRv.setLayoutManager(manager);
        moreAdapter();
        fieldRv.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 加载更多数据的adapter
     */
    public void moreAdapter() {
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

    protected void setTitle() {
        imgRight.setVisibility(View.VISIBLE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//不显示自带标题
        String farmName = getIntent().getStringExtra("farmName");
        if (farmName != null && !"".equals(farmName)) {
            toolbar.setCenterTitle(farmName + JctlApplication.getAppResources().getString(R.string.tv_sxnt));
        } else {
            toolbar.setCenterTitle(JctlApplication.getAppResources().getString(R.string.tv_btlb));
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
        etSearch.addTextChangedListener(watcher);
        /**
         * 下拉刷新事件
         */
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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
                        isFailed = true;
                        map.clear();
                        map.put("farmerId", id);
                        map.put("singleId", KeyConstants.USER_SINGLEID);
                        map.put("name", v.getText().toString());
                        mPresenter.setFieldManageData(map);
                    } else {
                        ToastUtils.showLong("请输入农场名称或者地址");
                    }

                }
                return false;
            }
        });
    }

    @Override
    public void initData() {
        id = getIntent().getStringExtra("id");
        if (id != null && !"".equals(id)) {
            //			@Query("farmerId") String userId
            //			FieldManageItem item=new FieldManageItem();
            //			FieldManageItem.Node data=new FieldManageItem.Node();
            //			data.setId(id);
            //			data.setAlias("阿斯大声");
            //			item.setUser(data);
            //			String route= GsonUtils.newInstance().toJson(item);//通过Gson将Bean转化为Json字符串形式
            map.clear();
            map.put("farmerId", id);
            map.put("singleId", KeyConstants.USER_SINGLEID);
            mPresenter.setFieldManageData(map);
        }
    }

    @Override
    public void showDialog() {
        //          JctlApplication.getGifLoadingView().show(getFragmentManager(),"");
        if (progressDialog == null) {
            progressDialog = new Dialog(FieldManageActivity.this, R.style.progress_dialog);
            progressDialog.setContentView(R.layout.dialog);
            progressDialog.setCancelable(true);
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            TextView msg = (TextView) progressDialog.findViewById(R.id.id_tv_loadingmsg);
            msg.setText("加载中...");
            progressDialog.show();
        }
    }

    @Override
    public void onSucceed(FieldManageItem data) {

        if (data.getInfo() != null && "1".equals(data.getFlag())) {

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

                    srl.setRefreshing(false);

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

            mAdapter.setfarmerId(data.getInfoBean().getId());
            mAdapter.notifyDataSetChanged();
        } else {
            ToastUtils.showLong(data.getMsg());
            srl.setRefreshing(false);
            if (mAdapter.getItemCount() > 0) {
                mAdapter.remove(0);
            }
        }
    }

    @Override
    public void onSucceedMore(FieldManageItem data) {
        if (data.getInfo() != null && data.getInfo().size() > 0) {
            if (data.getInfo().size() >= 10) {
                mAdapter.setLoadMoreData(data.getInfo());
            } else {
                mAdapter.setLoadMoreData(data.getInfo());
                //加载完成，更新footer view提示
                mAdapter.setLoadEndView(R.layout.load_end_layout);
            }
        } else {
            //加载完成，更新footer view提示
            mAdapter.setLoadEndView(R.layout.load_end_layout);
            ToastUtils.showLong(data.getMsg());
            pageCount = 1;
        }
        srl.setRefreshing(false);
    }

    @Override
    public void onFailure(String err, Throwable e) {
        ToastUtils.showLong(err);
    }

    @Override
    public void onFail(String err) {
        ToastUtils.showLong(err);
    }

    @Override
    public void hideDialog() {
        //         JctlApplication.getGifLoadingView().dismiss();
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override
    protected FieldManagePresenter onCreatePresenter() {
        return new FieldManagePresenter(this);
    }

    /**
     * 获取测试数据
     */
    //	private void getData() {
    //		if (id!=null&&!"".equals(id)){
    //			map.clear();
    //			map.put("farmerId",id);
    //			map.put("singleId", KeyConstants.KAY_USERID);
    //			mPresenter.setFieldManageData(map);
    //		}
    //	}
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
                //				getData();
                searchRl.setFocusable(true);
                searchRl.setFocusableInTouchMode(true);
                searchRl.requestFocus();
                //				imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0); //强制隐藏键盘
            }
        }
    };

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
                    mPresenter.setFieldManageMore(map);
                    isFailedEt = false;
                } else {
                    pageCount++;
                    map.put("singleId", KeyConstants.USER_SINGLEID);
                    map.put("farmerId", id);
                    map.put("pageNum", String.valueOf(pageCount));
                    mPresenter.setFieldManageMore(map);
                }

            }
        }, 2000);
    }
}
