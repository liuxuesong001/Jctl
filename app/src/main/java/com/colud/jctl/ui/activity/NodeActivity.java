package com.colud.jctl.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.colud.jctl.JctlApplication;
import com.colud.jctl.adapters.activity.NodeFragmentPagerAdapter;
import com.colud.jctl.api.KeyConstants;
import com.colud.jctl.base.BaseActivity;
import com.colud.jctl.bean.NodeItem;
import com.colud.jctl.mvp.contract.NodeContract;
import com.colud.jctl.mvp.presenter.NodePresenter;
import com.colud.jctl.receiver.BroadCastManager;
import com.colud.jctl.ui.custom.NoScrollViewPager;
import com.colud.jctl.ui.custom.TitleBar;
import com.colud.jctl.utils.BarStatusHeightUtil;
import com.colud.jctl.utils.ToastUtils;
import com.jctl.colud.R;
import com.socks.library.KLog;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *
 * Created by Jcty on 2017/4/5.
 */

public class NodeActivity extends BaseActivity<NodePresenter> implements NodeContract.View {


    @BindView(R.id.node_tableLayout)
    TabLayout nodeTableLayout;
    @BindView(R.id.node_ViewPager)
    NoScrollViewPager nodeViewPager;
    @BindView(R.id.title_cotent)
    TextView tvContent;
    @BindView(R.id.title_toolbar)
    TitleBar toolbar;

    private NodeFragmentPagerAdapter nodeAdapter;

    protected TabLayout.Tab NodeFragment;
    protected TabLayout.Tab CameraFragment;

    private TimePickerView pvCustomTime;
    private String dats;
    private String id;
    private String title;

    private Dialog dialog;

    @OnClick({R.id.title_cotent})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_cotent:
                if (pvCustomTime != null)
                    pvCustomTime.show();
                break;
        }
    }


    @Override
    public int getContentViewId() {
        return R.layout.activity_node;
    }

    @Override
    public void initViews() {
        tvContent.setVisibility(View.VISIBLE);
        tvContent.setText("选择日期");
        initCustomTimePicker();
        setViewPager();
    }

    @Override
    public void addListener() {

    }

    @Override
    public void initData() {
        id = getIntent().getStringExtra("farmerId");
        title = getIntent().getStringExtra("area");
        if (id != null && !"".equals(id)) {
            //			@Query("farmlandId") String userId,
            //            @Query("date") String date
            Map<String, String> map = new ArrayMap<>();
            map.put("singleId", KeyConstants.USER_NAME);
            map.put("farmlandId", id);
            //			map.put("date",date);
            mPresenter.setNodeData(map);
        }
        tvContent.setVisibility(View.VISIBLE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//不显示自带标题
        if (title != null && !"".equals(title)) {
            toolbar.setCenterTitle(title);
        } else {
            toolbar.setCenterTitle(JctlApplication.getAppResources().getString(R.string.tv_jdmc));
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

    /**
     * 设置viewpager
     */
    protected void setViewPager() {
        //绑定viewpager
        nodeAdapter = new NodeFragmentPagerAdapter(getSupportFragmentManager());
        nodeViewPager.setAdapter(nodeAdapter);
        nodeTableLayout.setupWithViewPager(nodeViewPager);
        //获取fragment位置
        NodeFragment = nodeTableLayout.getTabAt(0);
        CameraFragment = nodeTableLayout.getTabAt(1);
    }


    @Override
    public void showDialog() {
        //		JctlApplication.getGifLoadingView().show(getFragmentManager(),"");
        if (dialog == null) {
            dialog = new Dialog(NodeActivity.this, R.style.progress_dialog);
            dialog.setContentView(R.layout.dialog);
            dialog.setCancelable(true);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            TextView msg = (TextView) dialog.findViewById(R.id.id_tv_loadingmsg);
            msg.setText(JctlApplication.getAppResources().getString(R.string.loading));
            dialog.show();
        }
    }

    @Override
    public void onSucceed(NodeItem data) {
        if (data.getFlag().equals("1") && data.getInfo().size() <= 0) {
            ToastUtils.showLong("暂无数据");
        }
        KLog.d(data.getMsg());
        //发送广播
        Intent intent = new Intent();
        intent.putExtra("nodeList", (Serializable) data.getInfo());
        intent.setAction(KeyConstants.NODE_INTENT);
        BroadCastManager.getInstance().sendBroadCast(this, intent);
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
        //		JctlApplication.getGifLoadingView().dismiss();
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
    }

    @Override
    protected NodePresenter onCreatePresenter() {
        return new NodePresenter(this);
    }

    private void initCustomTimePicker() {
        // 注意：自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针
        // 具体可参考demo 里面的两个自定义布局
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(2017, 1, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2027, 2, 28);
        //时间选择器 ，自定义布局
        pvCustomTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                dats = getTime(date);
                if (!TextUtils.isEmpty(dats)) {
                    tvContent.setText(dats);
                    if (id != null && !"".equals(id)) {
                        //			@Query("farmlandId") String userId,
                        //            @Query("date") String date
                        Map<String, String> map = new ArrayMap<>();
                        map.put("singleId", KeyConstants.USER_NAME);
                        map.put("farmlandId", id);
                        map.put("date", dats);
                        mPresenter.setNodeData(map);
                    }
                }
            }
        })
                //				.setDate(selectedDate)
                //				.setRangDate(startDate,endDate)
                .setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {

                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        //						ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.returnData();
                            }
                        });
                        //						ivCancel.setOnClickListener(new View.OnClickListener() {
                        //							@Override
                        //							public void onClick(View v) {
                        //								pvCustomTime.dismiss();
                        //							}
                        //						});
                    }
                })
                .setDividerColor(JctlApplication.getAppResources().getColor(R.color.color_CECECE))
                .setTextColorCenter(JctlApplication.getAppResources().getColor(R.color.color_3AB07D)) //设置选中项文字颜色
                .setContentSize(15)
                .isDialog(true)
                .build();

    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        //        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    private void changeFragment(Fragment fromFragment, Fragment toFragment) {

        //		if (nowFragment != toFragment) {
        //			nowFragment = toFragment;
        //		}
        //
        //		FragmentManager fm = getSupportFragmentManager();
        //		FragmentTransaction ft = fm.beginTransaction();
        //
        //		if (toFragment.isAdded() == false) {
        //			ft.hide(fromFragment).add(R.id.center_view_main_activity, toFragment).commit();
        //		} else {
        //			ft.hide(fromFragment).show(toFragment).commit();
        //		}

    }
}
