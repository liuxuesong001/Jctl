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
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.colud.jctl.JctlApplication;
import com.colud.jctl.adapters.activity.FarmManageAdapter;
import com.colud.jctl.api.KeyConstants;
import com.colud.jctl.base.BaseActivity;
import com.colud.jctl.bean.FarmManageBean;
import com.colud.jctl.mvp.contract.FarmManageContract;
import com.colud.jctl.mvp.presenter.FarmManagePresenter;
import com.colud.jctl.ui.custom.CleanEditText;
import com.colud.jctl.ui.custom.TitleBar;
import com.colud.jctl.utils.BarStatusHeightUtil;
import com.colud.jctl.utils.ToastUtils;
import com.jctl.colud.R;
import com.othershe.baseadapter.interfaces.OnLoadMoreListener;
import com.socks.library.KLog;

import java.lang.reflect.Method;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 农场列表页
 * Created by Jcty on 2017/3/29.
 */

public class FarmManageActivity extends BaseActivity<FarmManagePresenter> implements FarmManageContract.View {


    @BindView(R.id.farm_rv)
    RecyclerView farmRv;
    @BindView(R.id.farm_srlayout)
    SwipeRefreshLayout sr;

    @BindView(R.id.et_search)
    CleanEditText etSearch;

    @BindView(R.id.search_ll)
    RelativeLayout searchRl;

    @BindView(R.id.title_toolbar)
    TitleBar toolbar;

    @BindView(R.id.title_right_img)
    ImageView addImg;

    private FarmManageAdapter mAdapter;

    private Handler handler = new Handler();

    private boolean isFailed = false;

    private boolean isFailedEt = false;

    private int pageCount = 1;

    private Dialog progressDialog;

    private Map<String, String> map = new ArrayMap<>();

    private boolean down = false;


    @OnClick({R.id.title_right_img})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_right_img:
                Intent in = new Intent(getApplicationContext(), AddFarmActivity.class);
                startActivity(in);
                break;
        }
    }


    @Override
    public int getContentViewId() {
        return R.layout.activity_farmmanage;
    }

    protected void initAdapter(boolean isFailed) {

        if (isFailed) {
            mAdapter = new FarmManageAdapter(this, null, true);

        } else {

            mAdapter = new FarmManageAdapter(this, null, false);
        }
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        farmRv.setLayoutManager(manager);
        moreAdapter();
        farmRv.setAdapter(mAdapter);

    }

    /**
     * 设置加载更多View
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

    @Override
    public void initViews() {
        //		searchRl.bringToFront();
        setTitle();
        //		initAdapter();

    }

    protected void setTitle() {
        addImg.setVisibility(View.VISIBLE);
        //		tvTitle.setText(JctlApplication.getAppResources().getString(R.string.text_ncgl));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//不显示自带标题
        toolbar.setCenterTitle(JctlApplication.getAppResources().getString(R.string.text_ncgl));
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
    protected void onResume() {
        super.onResume();
        //		if (!"".equals(KeyConstants.KAY_USERID)
        //				&& !"".equals(KeyConstants.KAY_USERID)) {
        //			map.clear();
        //			map.put("singleId",KeyConstants.KAY_USERID);
        //			mPresenter.setFarmManageData(map);
        //		}

        searchRl.setFocusable(true);
        searchRl.setFocusableInTouchMode(true);
        searchRl.requestFocus();
        //		searchRl.findFocus();
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
                        if (down)
                            pageCount = 1;
                        initData();

                    }
                }, 2000);
            }
        });

        //根据是否有焦点更新背景（这里只是演示使用，其实这种更换背景的效果可以通过Selector来实现）
        //		etSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
        //			@Override
        //			public void onFocusChange(View v, boolean hasFocus) {
        //				if (hasFocus) {
        //					KLog.e("abc", "et1获取到焦点了。。。。。。");
        //					//					etSearch.setBackgroundResource(R.drawable.shape_edit1);  //获取焦点后更改背景色
        //				} else {
        //					KLog.e("abc", "et1失去焦点了。。。。。。");
        //					//					etSearch.setBackgroundResource(R.drawable.shape_edit2);  //失去焦点后更改背景色
        //				}
        //			}
        //		});


        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (!"".equals(v.getText().toString())) {
                        isFailed = true;
                        map.clear();
                        map.put("singleId", KeyConstants.USER_SINGLEID);
                        map.put("name", v.getText().toString());
                        mPresenter.setFarmManageData(map);
                    } else {
                        ToastUtils.showLong("请输入农场名称或者地址");
                    }

                }
                return false;
            }
        });


        //		searchRl.setOnTouchListener(new View.OnTouchListener() {
        //			@Override
        //			public boolean onTouch(View v, MotionEvent event) {
        //				//				etSearch.setCursorVisible(true);//动态代码设置显示光标方式
        //				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        //				imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        //				//				boolean isOpen = imm.isActive();//isOpen若返回true，则表示输入法打开
        //				//				if (isOpen) {
        //				//					try {
        //				//						etSearch.setCursorVisible(true);//动态代码设置显示光标方式
        //				//
        //				//						imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0); //强制隐藏键盘
        //				//					} catch (Exception e) {
        //				//						e.printStackTrace();
        //				//					}
        //				//				}else {
        //				//					imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        //				//				}
        //				return false;
        //			}
        //		});
        //
        //		searchRl.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
        //			@Override
        //			public void onGlobalLayout() {
        //				//比较Activity根布局与当前布局的大小
        //				int heightDiff = searchRl.getRootView().getHeight() - etSearch.getHeight();
        //				if (heightDiff > 100) {
        //					//大小超过100时，一般为显示虚拟键盘事件
        //					ToastUtils.showLong("大于100");
        //				} else {
        //					//大小小于100时，为不显示虚拟键盘或虚拟键盘隐藏
        //					ToastUtils.showLong("小于100");
        //					//					changeDiscount();
        //				}
        //			}
        //		});

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
        //		if (!TextUtils.isEmpty(KeyConstants.KAY_USERID)){
        //			mPresenter.setFarmManageData(KeyConstants.KAY_USERID);
        //		}
        if (!"".equals(KeyConstants.USER_SINGLEID)) {
            map.clear();
            map.put("singleId", KeyConstants.USER_SINGLEID);
            mPresenter.setFarmManageData(map);
        }
    }

    @Override
    public void showDialog() {
        //		JctlApplication.getGifLoadingView().show(getFragmentManager(),"");
        if (progressDialog == null) {
            progressDialog = new Dialog(FarmManageActivity.this, R.style.progress_dialog);
            progressDialog.setContentView(R.layout.dialog);
            progressDialog.setCancelable(true);
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            TextView msg = (TextView) progressDialog.findViewById(R.id.id_tv_loadingmsg);
            msg.setText(JctlApplication.getAppResources().getString(R.string.loading));
            progressDialog.show();
        }
    }

    @Override
    public void onSucceed(FarmManageBean data) {

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
            ToastUtils.showLong(data.getMsg());
            sr.setRefreshing(false);
            if (mAdapter.getItemCount() > 0) {
                mAdapter.remove(0);
            }
        }
    }

    @Override
    public void onSucceedMore(FarmManageBean data) {
        if (data.getInfo() != null && data.getInfo().size() > 0) {
            mAdapter.setLoadMoreData(data.getInfo());
            sr.setRefreshing(false);
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
        KLog.d(err);
        sr.setRefreshing(false);
    }

    @Override
    public void hideDialog() {
        //		JctlApplication.getGifLoadingView().dismiss();
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override
    protected FarmManagePresenter onCreatePresenter() {
        return new FarmManagePresenter(this);
    }

    /**
     * 获取测试数据
     */
    //	private void getData() {
    //		if (KeyConstants.KAY_USERID!=null&&!"".equals(KeyConstants.KAY_USERID)){
    //			pageCount=1;
    //			map.clear();
    //			map.put("singleId",KeyConstants.KAY_USERID);
    //			mPresenter.setFarmManageData(map);
    //		}
    //	}
    public void setKeyPad(EditText editText) {
        if (android.os.Build.VERSION.SDK_INT <= 10) {
            editText.setInputType(InputType.TYPE_NULL);
        } else {
            getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            try {
                Class<EditText> cls = EditText.class;
                Method setShowSoftInputOnFocus;
                setShowSoftInputOnFocus = cls.getMethod(
                        "setShowSoftInputOnFocus", boolean.class);
                setShowSoftInputOnFocus.setAccessible(true);
                setShowSoftInputOnFocus.invoke(editText, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 加载更多
     *
     * @param isFailed
     */
    private void loadMore(final boolean isFailed) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (isFailed) {
                    pageCount++;
                    map.put("singleId", KeyConstants.USER_SINGLEID);
                    map.put("pageNum", String.valueOf(pageCount));
                    map.put("name", etSearch.getText().toString().trim());
                    mPresenter.setFarmManageMore(map);
                    isFailedEt = false;
                } else {
                    pageCount++;
                    map.put("singleId", KeyConstants.USER_SINGLEID);
                    map.put("pageNum", String.valueOf(pageCount));
                    mPresenter.setFarmManageMore(map);
                }

            }
        }, 2000);
    }

}
