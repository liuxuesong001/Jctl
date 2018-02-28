package com.colud.jctl.ui.fragment;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.colud.jctl.JctlApplication;
import com.colud.jctl.MainActivity;
import com.colud.jctl.adapters.EarlyMenuAdapter;
import com.colud.jctl.adapters.fragment.EarlyAdapter;
import com.colud.jctl.api.KeyConstants;
import com.colud.jctl.base.BaseFragment;
import com.colud.jctl.base.UIInitF;
import com.colud.jctl.bean.EarlyItme;
import com.colud.jctl.bean.RateDialogItem;
import com.colud.jctl.counst.Constants;
import com.colud.jctl.mvp.contract.EarlyContract;
import com.colud.jctl.mvp.presenter.EarlyPresenter;
import com.colud.jctl.receiver.BroadCastManager;
import com.colud.jctl.ui.activity.OffOnActivity;
import com.colud.jctl.ui.custom.TitleBar;
import com.colud.jctl.ui.custom.dialog.RateDialog;
import com.colud.jctl.utils.BarStatusHeightUtil;
import com.colud.jctl.utils.NotificationUtils;
import com.colud.jctl.utils.ToastUtils;
import com.jctl.colud.R;
import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.interfaces.OnItemChildClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.colud.jctl.JctlApplication.context;

/**
 * 预警页
 * Created by Jcty on 2017/3/17.
 */

public class EarlyWarningFragment extends BaseFragment<EarlyPresenter> implements UIInitF, SwipeRefreshLayout.OnRefreshListener, EarlyContract.View {


    protected LinearLayoutManager layoutManager;


    @BindView(R.id.early_rv)
    SwipeMenuRecyclerView earlyRv;
    //    @BindView(R.id.early_srlayout)
    //    SwipeRefreshLayout earlySrlayout;
    @BindView(R.id.early_btm_delete)
    LinearLayout earlyBtmDelete;


    @BindView(R.id.title_toolbar)
    TitleBar toolbar;
    @BindView(R.id.title_cotent)
    TextView tvContent;
    @BindView(R.id.tv_zw_info)
    TextView tvZwInfo;


    private List<EarlyItme> data = new ArrayList<>();

    //	private EarlyFragmentAdapter adapter;


    private EarlyMenuAdapter adapter;

    private EarlyAdapter mAdapter;

    private LocalReceiver mReceiver;

    private List<EarlyItme> info = new ArrayList<>();

    private List<EarlyItme> mEarlys = new ArrayList<>();

    private Handler handler = new Handler();

    private RateDialog dialog;

    private RateDialogItem dialogItem = new RateDialogItem();

    private PendingIntent pendingIntent;

    private NotificationUtils utils;


    public static EarlyWarningFragment getInstance(String pageName) {
        Bundle args = new Bundle();
        args.putString(Constants.ARGS, pageName);
        EarlyWarningFragment earlyFragment = new EarlyWarningFragment();
        earlyFragment.setArguments(args);
        return earlyFragment;
    }

    @OnClick({R.id.title_cotent})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_cotent:
                if (mEarlys != null && mEarlys.size() > 0) {
                    dialogItem.setTvTitle("是否全部删除?");
                    dialog = new RateDialog(getActivity(), R.style.MyDialog, dialogItem);
                    dialog.setMyClickListener(new RateDialog.onClickRateDialog() {
                        @Override
                        public void onClickLeft() {
                            dialog.dismiss();
                            dialog = null;
                        }

                        @Override
                        public void onClickRight() {

                            //从界面全部删除
                            mAdapter.clearDataAll(mEarlys);
                            //从数据库全部删除
                            DataSupport.deleteAll(EarlyItme.class);

                            Intent intent = new Intent(KeyConstants.MESSAGE_INFO);
                            intent.putExtra("----", "all");
                            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

                            if (mAdapter.getItemCount() == 0) {
                                tvContent.setVisibility(View.GONE);
                                earlyRv.setVisibility(View.GONE);
                                tvZwInfo.setVisibility(View.VISIBLE);
                                tvZwInfo.setText(JctlApplication.getAppResources().getString(R.string.early_jkz));
                                ToastUtils.showLong("清空成功");
                            }


                            dialog.dismiss();

                            dialog = null;


                        }
                    });
                    dialog.show();
                }
                break;
            default:
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_early;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //接收广播
        try {
            IntentFilter filter = new IntentFilter();
            filter.addAction(KeyConstants.MESSAGE_YUJING);
            mReceiver = new LocalReceiver();
            BroadCastManager.getInstance().registerReceiver(getActivity(), mReceiver, filter);//注册广播接收者
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void onSucceed(EarlyItme data) {
        //        if (data!=null&&data.getFlag()==1){
        //            if (data.getInfo()!=null&&data.getInfo().size()>0){
        //                mAdapter.setNewData(data.getInfo());
        //                if (earlyRv.getVisibility()==View.GONE){
        //                    earlyRv.setVisibility(View.VISIBLE);
        //                    tvZwInfo.setVisibility(View.GONE);
        //                }
        //            }
        //        }else {
        //            KLog.d(data.getMsg());
        //            tvZwInfo.setVisibility(View.VISIBLE);
        //            tvZwInfo.setText(data.getMsg());
        //            earlyRv.setVisibility(View.GONE);
        //        }
    }

    @Override
    public void onFailure(String err, Throwable e) {

    }

    @Override
    public void onFail(String err) {

    }

    @Override
    public void hideDialog() {

    }

    /**
     * 广播处理
     */
    class LocalReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            //收到广播后的处理
            EarlyItme pushMsg = (EarlyItme) intent.getSerializableExtra("pushMsg");
            String userId = intent.getStringExtra(KeyConstants.USER_NAME);
            if (pushMsg != null) {
                info = new ArrayList<>();
                info.add(pushMsg);
                if (info.size() > 0) {
                    mEarlys.addAll(info);
                    tvContent.setVisibility(View.VISIBLE);
                    tvContent.setText(R.string.early_allclear);
                    mAdapter.setDataAnt(info);
                    earlyRv.scrollToPosition(0);
                }

                if (earlyRv.getVisibility() == View.GONE) {
                    earlyRv.setVisibility(View.VISIBLE);
                    tvZwInfo.setVisibility(View.GONE);
                }
            } else if (userId != null) {
                EarlyItme db = new EarlyItme();
                List<EarlyItme> eraly = db.getComments(userId);
                mAdapter.setDataAnt(eraly);
                earlyRv.scrollToPosition(0);
                mEarlys.addAll(eraly);
                if (eraly.size() > 0) {
                    if (earlyRv.getVisibility() == View.GONE) {
                        earlyRv.setVisibility(View.VISIBLE);
                        tvZwInfo.setVisibility(View.GONE);
                        tvContent.setVisibility(View.VISIBLE);
                        tvContent.setText(R.string.early_allclear);
                    }
                    int count = db.getCount(userId);
                    //更新底部推送数量
                    Intent countInt = new Intent(KeyConstants.MESSAGE_INFO);
                    countInt.putExtra("count", count);
                    LocalBroadcastManager.getInstance(context).sendBroadcast(countInt);

                    db = DataSupport.findLast(EarlyItme.class);
                    if (db != null) {
                        /**
                         * 通知栏
                         */
                        Intent intenta = new Intent(getActivity(), OffOnActivity.class);
                        intenta.putExtra("status", db.getOpenFlag());
                        intenta.putExtra("nodeMac", db.getNode_num());
                        pendingIntent = PendingIntent.getActivity(getActivity(), (int) SystemClock.uptimeMillis(), intenta, PendingIntent.FLAG_UPDATE_CURRENT);
                        utils = new NotificationUtils(context, 1001);
                        utils.sendMoreLineNotification("智慧农业", "预警", db.getMsg(), R.drawable.jctl_launcher, pendingIntent, true, true, true);
                    }
                }
            }

        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void addListener() {
        /**
         * 下拉刷新事件
         */
        //        earlySrlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
        //            @Override
        //            public void onRefresh() {
        //                handler.postDelayed(new Runnable() {
        //                    @Override
        //                    public void run() {
        //                        data.clear();
        //                        earlySrlayout.setRefreshing(false);
        //                    }
        //                }, 2000);
        //            }
        //        });

        /**
         * 跳转开关页
         */
        mAdapter.setOnItemClickListener(new com.othershe.baseadapter.interfaces.OnItemClickListener<EarlyItme>() {
            @Override
            public void onItemClick(ViewHolder viewHolder, EarlyItme data, int position) {
                Intent intent = new Intent(getActivity(), OffOnActivity.class);
                intent.putExtra("status", data.getOpenFlag());
                intent.putExtra("nodeMac", data.getNode_num());
                getActivity().startActivity(intent);
            }
        });

        /**
         * 点击item 控件操作
         */
        mAdapter.setOnItemChildClickListener(R.id.early_delete, new OnItemChildClickListener<EarlyItme>() {
            @Override
            public void onItemChildClick(ViewHolder viewHolder, final EarlyItme data, final int position) {
                dialogItem.setTvTitle("确定要删除？");
                dialog = new RateDialog(getActivity(), R.style.MyDialog, dialogItem);
                dialog.setMyClickListener(new RateDialog.onClickRateDialog() {
                    @Override
                    public void onClickLeft() {
                        dialog.dismiss();
                        dialog = null;
                    }

                    @Override
                    public void onClickRight() {

                        //从界面删除
                        mAdapter.removeDataAnt(position);
                        //接着从数据库删除
                        DataSupport.delete(EarlyItme.class, data.getId());
                        //发送广播
                        Intent intent = new Intent(KeyConstants.MESSAGE_INFO);
                        intent.putExtra("----", "less");
                        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

                        if (mAdapter.getItemCount() == 0) {
                            tvContent.setVisibility(View.GONE);
                            earlyRv.setVisibility(View.GONE);
                            tvZwInfo.setVisibility(View.VISIBLE);
                            tvZwInfo.setText(JctlApplication.getAppResources().getString(R.string.early_jkz));
                        }

                        dialog.dismiss();
                        dialog = null;
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public void initViews() {

        initToolbar();
        initAdapter();

    }

    private void initToolbar() {
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);//不显示自带标题
        toolbar.setCenterTitle(JctlApplication.getAppResources().getString(R.string.early_yj));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        //设置透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            toolbar.setPadding(0, BarStatusHeightUtil.getStatusBarHeightAll(getActivity()), 0, 0);
        }
    }

    /**
     *
     */
    protected void initAdapter() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        earlyRv.setLayoutManager(manager);
        earlyRv.setItemAnimator(new DefaultItemAnimator());
        earlyRv.getItemAnimator().setAddDuration(1500);
        //		adapter=new MsgInformAdapter(this,mlist,true);
        // 为SwipeRecyclerView的Item创建菜单就两句话，不错就是这么简单：
        // 设置菜单创建器。
        //		earlyRv.setSwipeMenuCreator(swipeMenuCreator);
        // 设置菜单Item点击监听。
        //		earlyRv.setSwipeMenuItemClickListener(menuItemClickListener);
        //		adapter = new EarlyMenuAdapter(getActivity(), data);
        //		adapter.setOnItemClickListener(onItemClickListener);
        //		earlyRv.setAdapter(adapter);
        mAdapter = new EarlyAdapter(getActivity(), null, true);

        earlyRv.setAdapter(mAdapter);

        if (mAdapter.getItemCount() == 0) {
            earlyRv.setVisibility(View.GONE);
            tvZwInfo.setVisibility(View.VISIBLE);
            tvZwInfo.setText(JctlApplication.getAppResources().getString(R.string.early_jkz));
        }
        //        EarlyItme.CustomContentBean db =new EarlyItme.CustomContentBean();
        //        List<EarlyItme.CustomContentBean> eraly = db.getComments();
        //        List<DBEarlyItem> newsList = db.getComments();
        //        product = DataSupport.where("productName like ?", "_" + name + "%")
        //                .find(Product.class);
        //        List<DBEarlyItem> newsList = DataSupport.where("userid = ?","15510620188").find(DBEarlyItem.class);
        //        KLog.d("DB="+newsList);
    }

    /**
     * Item点击监听。
     */
    //    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
    //        @Override
    //        public void onItemClick(int position) {
    //            Toast.makeText(getActivity(), "我是第" + position + "条。", Toast.LENGTH_SHORT).show();
    //        }
    //
    //        @Override
    //        public void onItemClickCheckBox(CheckBox cb, int position) {
    //            Intent gl = new Intent(Counts.BROADCAST_TAB_FRAGMENT);
    //            isPoistion = position;
    //            if (cb.isChecked()) {
    //                cb.setChecked(false);
    //                gl.putExtra("isTure", false);
    //                earlyBtmDelete.setVisibility(View.GONE);
    //            } else {
    //                cb.setChecked(true);
    //                gl.putExtra("isTure", true);
    //                earlyBtmDelete.setVisibility(View.VISIBLE);
    //            }
    //            getActivity().sendBroadcast(gl);
    //        }
    //
    //        @Override
    //        public void onItemClickItemView(View view, int position) {
    //        }
    //    };
    @Override
    public void initData() {

    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onRefresh() {
        //设置2秒内刷新数据测试用
        //        handler.postDelayed(new Runnable() {
        //            @Override
        //            public void run() {
        //                data.clear();
        //                earlySrlayout.setRefreshing(false);
        //            }
        //        }, 1000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BroadCastManager.getInstance().unregisterReceiver(getActivity(), mReceiver);//注销广播接收者
    }

    @Override
    protected EarlyPresenter onCreatePresenter() {
        return new EarlyPresenter(this);
    }


}
