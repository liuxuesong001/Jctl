package com.colud.jctl.ui.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v4.util.ArrayMap;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.colud.jctl.api.KeyConstants;
import com.colud.jctl.base.BaseFragment;
import com.colud.jctl.bean.LastNodeDetailsBean;
import com.colud.jctl.bean.NodeDataManageItem;
import com.colud.jctl.mvp.contract.LastNodeDatailsContract;
import com.colud.jctl.mvp.presenter.LastNodeDatailsPresenter;
import com.colud.jctl.receiver.BroadCastManager;
import com.colud.jctl.ui.activity.OffOnActivity;
import com.colud.jctl.utils.ToastUtils;
import com.jctl.colud.R;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 节点详情管理Frgament
 * Created by Jcty on 2017/4/7.
 */

public class NodeDataDetailsFragment extends BaseFragment<LastNodeDatailsPresenter> implements LastNodeDatailsContract.View {

    @BindView(R.id.details_nodeid)
    TextView detailsNodeid;
    @BindView(R.id.details_dqwd)
    TextView detailsDqwd;
    @BindView(R.id.details_dqsd)
    TextView detailsDqsd;
    @BindView(R.id.details_aw)
    TextView detailsAw;
    @BindView(R.id.details_as)
    TextView detailsAs;
    @BindView(R.id.details_bw)
    TextView detailsBw;
    @BindView(R.id.details_bs)
    TextView detailsBs;
    @BindView(R.id.details_cw)
    TextView detailsCw;
    @BindView(R.id.details_cs)
    TextView detailsCs;
    @BindView(R.id.details_eyhtnd)
    TextView detailsEyhtnd;
    @BindView(R.id.details_kgzt)
    TextView detailsKgzt;
    @BindView(R.id.details_dl)
    TextView detailsDl;
    @BindView(R.id.details_tjsj)
    TextView detailsTjsj;
    @BindView(R.id.node_deatails_ll)
    LinearLayout ndeOffLl;
    @BindView(R.id.nde_srl)
    SwipeRefreshLayout srl;

    private LocalReceiver mReceiver;

    private String id = "";


    private NodeDataManageItem.DataBean dataNode = new NodeDataManageItem.DataBean();

    private String openFlag = "";

    private Handler handler = new Handler();

    private String nowState = "";

    @OnClick({R.id.node_deatails_ll})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.node_deatails_ll:
                if (dataNode != null && nowState != null && nowState.equals("yes")) {
                    Intent intent = new Intent(getActivity(), OffOnActivity.class);
                    intent.putExtra("nodeId", id);
                    intent.putExtra("nodeMac", dataNode.getNodeMac());
                    intent.putExtra("status", openFlag);
                    getActivity().startActivity(intent);
                    nowState = null;
                } else {
                    ToastUtils.showLong("请点击编辑");
                }

                break;
        }

    }


    @Override
    public int getContentViewId() {
        return R.layout.fragment_node_details;
    }

    @Override
    public void initViews() {
        //接收广播
        try {
            IntentFilter filter = new IntentFilter();
            filter.addAction(KeyConstants.NODE_MANAGE_INTENT);
            filter.addAction(KeyConstants.NODE_INTENT_MANAGE);
            mReceiver = new LocalReceiver();
            BroadCastManager.getInstance().registerReceiver(getActivity(), mReceiver, filter);//注册广播接收者
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void addListener() {
        /**
         * 下拉刷新事件
         */
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (!"".equals(id)) {
                            Map<String, String> map = new ArrayMap<>();
                            map.put("id", id);
                            mPresenter.setLastNodeDetails(map);
                        }
                        srl.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }

    //	private String nodeMac;          //节点id
    //	private double soilTemperature3;   //3温度
    //	private double airHumidity;         //大气湿度
    //	private double soilTemperature2;     //2温度
    //	private double soilTemperature1; //1温度
    //	private double airTemperature;      //大气温度
    //	private double soilHumidity1;   //1湿度
    //	private double soilHumidity2;//2湿度
    //	private int power;             //电量
    //	private int co2;             //二氧化碳
    //	private double soilHumidity3;  //3湿度
    //	private String openFlag;         //当前状态
    //	private long addTime;          //当前数据时间

    @Override
    public void initData() {

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void onSucceed(LastNodeDetailsBean data) {
        if (data.getInfo() != null) {
            if (!TextUtils.isEmpty(data.getInfo().getNodeMac())) {
                detailsNodeid.setText(data.getInfo().getNodeMac());
            }
            if (Double.valueOf(data.getInfo().getAirTemperature()) != null) {
                detailsDqwd.setText(String.valueOf(data.getInfo().getAirTemperature()));
            }
            if (Double.valueOf(data.getInfo().getAirHumidity()) != null) {
                detailsDqsd.setText(String.valueOf(data.getInfo().getAirHumidity()));
            }
            if (Double.valueOf(data.getInfo().getSoilTemperature1()) != null) {
                detailsAw.setText(String.valueOf(data.getInfo().getSoilTemperature1()));
            }
            if (Double.valueOf(data.getInfo().getSoilHumidity1()) != null) {
                detailsAs.setText(String.valueOf(data.getInfo().getSoilHumidity1()));
            }
            if (Double.valueOf(data.getInfo().getSoilTemperature2()) != null) {
                detailsBw.setText(String.valueOf(data.getInfo().getSoilTemperature2()));
            }
            if (Double.valueOf(data.getInfo().getSoilHumidity2()) != null) {
                detailsBs.setText(String.valueOf(data.getInfo().getSoilHumidity2()));
            }
            if (Double.valueOf(data.getInfo().getSoilTemperature3()) != null) {
                detailsCw.setText(String.valueOf(data.getInfo().getSoilTemperature3()));
            }
            if (Double.valueOf(data.getInfo().getSoilHumidity3()) != null) {
                detailsCs.setText(String.valueOf(data.getInfo().getSoilHumidity3()));
            }
            if (Double.valueOf(data.getInfo().getCo2()) != null) {
                detailsEyhtnd.setText(String.valueOf(data.getInfo().getCo2()));
            }
            if (!TextUtils.isEmpty(data.getInfo().getOpenFlag())) {
                if ("0".equals(data.getInfo().getOpenFlag())) {
                    detailsKgzt.setText("关");
                } else {
                    detailsKgzt.setText("开");
                }
                openFlag = data.getInfo().getOpenFlag();
            }
            if (Integer.valueOf(data.getInfo().getPower()) != null) {
                detailsDl.setText(String.valueOf(data.getInfo().getPower()));
            }
            if (!TextUtils.isEmpty(data.getInfo().getAddTime())) {
                detailsTjsj.setText(data.getInfo().getAddTime());
            }
            ToastUtils.showLong("更新成功");
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

    class LocalReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            //			//收到广播后的处理
            NodeDataManageItem.DataBean data = (NodeDataManageItem.DataBean) intent.getSerializableExtra("nodeManage");
            nowState = intent.getStringExtra("yes");
            id = intent.getStringExtra("id");
            if (data != null) {
                dataNode = data;
                if (!TextUtils.isEmpty(data.getNodeMac())) {
                    detailsNodeid.setText(data.getNodeMac());
                }
                if (Double.valueOf(data.getAirTemperature()) != null) {
                    detailsDqwd.setText(String.valueOf(data.getAirTemperature()));
                }
                if (Double.valueOf(data.getAirHumidity()) != null) {
                    detailsDqsd.setText(String.valueOf(data.getAirHumidity()));
                }
                if (Double.valueOf(data.getSoilTemperature1()) != null) {
                    detailsAw.setText(String.valueOf(data.getSoilTemperature1()));
                }
                if (Double.valueOf(data.getSoilHumidity1()) != null) {
                    detailsAs.setText(String.valueOf(data.getSoilHumidity1()));
                }
                if (Double.valueOf(data.getSoilTemperature2()) != null) {
                    detailsBw.setText(String.valueOf(data.getSoilTemperature2()));
                }
                if (Double.valueOf(data.getSoilHumidity2()) != null) {
                    detailsBs.setText(String.valueOf(data.getSoilHumidity2()));
                }
                if (Double.valueOf(data.getSoilTemperature3()) != null) {
                    detailsCw.setText(String.valueOf(data.getSoilTemperature3()));
                }
                if (Double.valueOf(data.getSoilHumidity3()) != null) {
                    detailsCs.setText(String.valueOf(data.getSoilHumidity3()));
                }
                if (Double.valueOf(data.getCo2()) != null) {
                    detailsEyhtnd.setText(String.valueOf(data.getCo2()));
                }
                if (!TextUtils.isEmpty(data.getOpenFlag())) {
                    if ("0".equals(data.getOpenFlag())) {
                        detailsKgzt.setText("关");
                    } else {
                        detailsKgzt.setText("开");
                    }
                    openFlag = data.getOpenFlag();
                }
                if (Integer.valueOf(data.getPower()) != null) {
                    detailsDl.setText(String.valueOf(data.getPower()));
                }
                if (!TextUtils.isEmpty(String.valueOf(data.getAddTime()))) {
                    detailsTjsj.setText(String.valueOf(data.getAddTime()));
                }
            }
            data = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BroadCastManager.getInstance().unregisterReceiver(getActivity(), mReceiver);//注销广播接收者
    }

    @Override
    protected LastNodeDatailsPresenter onCreatePresenter() {
        return new LastNodeDatailsPresenter(this);
    }


}
