package com.colud.jctl.ui.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.colud.jctl.adapters.activity.LAdapter;
import com.colud.jctl.adapters.activity.RAdapter;
import com.colud.jctl.api.KeyConstants;
import com.colud.jctl.base.BaseFragment;
import com.colud.jctl.base.BasePesenter;
import com.colud.jctl.bean.NodeItem;
import com.colud.jctl.receiver.BroadCastManager;
import com.colud.jctl.ui.activity.GrowthRecordsActivity;
import com.colud.jctl.ui.activity.OffOnActivity;
import com.colud.jctl.ui.custom.bothway.CustomRecyclerView;
import com.colud.jctl.ui.custom.bothway.TitleAdapter;
import com.colud.jctl.ui.custom.bothway.VAdapter;
import com.jctl.colud.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Jcty on 2017/4/5.
 */

public class NodeFragment extends BaseFragment {


    @BindView(R.id.title_rv)
    RecyclerView recyclerView;
    @BindView(R.id.customRv)
    CustomRecyclerView listView;
    private List<String> listName = new ArrayList<>();
    private List<NodeItem.InfoBean> nodeList;

    //	private NodeDetailsFragmentAdapter mAdapter;


    @BindView(R.id.left_rv)
    RecyclerView l;
    @BindView(R.id.right_rv)
    RecyclerView r;
    //	@BindView(R.id.srf)
    //	SwipeRefreshLayout sRf;

    private Handler handler = new Handler();

    private VAdapter vAdapter;
    private TitleAdapter titleAdapter;
    private LAdapter lAdapter;
    private RAdapter rAdapter;

    private boolean isRecyclerGroupTouch = false;
    private LinearLayoutManager linearLayoutManager;

    private LocalReceiver mReceiver;

    @Override
    protected BasePesenter onCreatePresenter() {
        return new BasePesenter();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_node;
    }

    public static NodeFragment newInstance(List<NodeItem.InfoBean> list) {
        NodeFragment fragment = new NodeFragment();
        Bundle args = new Bundle();
        args.putSerializable("node", (Serializable) list);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initViews() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        l.setLayoutManager(linearLayoutManager);
        lAdapter = new LAdapter(getActivity(), listName, true);
        l.setAdapter(lAdapter);
        //		initAdapter();

        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        r.setLayoutManager(linearLayoutManager);
        rAdapter = new RAdapter(getActivity(), nodeList, true);
        r.setAdapter(rAdapter);

        //接收广播
        try {
            IntentFilter filter = new IntentFilter();
            filter.addAction(KeyConstants.NODE_INTENT);
            mReceiver = new LocalReceiver();
            BroadCastManager.getInstance().registerReceiver(getActivity(), mReceiver, filter);//注册广播接收者
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void initAdapter() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        if (nodeList != null && nodeList.size() > 0) {
            initLeftAdapter();
            initTitleAdapter();
        }

    }

    /**
     * 左侧列表
     */
    protected void initLeftAdapter() {
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listView.setLayoutManager(linearLayoutManager);
        vAdapter = new VAdapter(listView, listName, nodeList);
        listView.setAdapter(vAdapter);

    }

    /**
     * 头部列表
     */
    protected void initTitleAdapter() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        titleAdapter = new TitleAdapter(getActivity(), nodeList);
        recyclerView.setAdapter(titleAdapter);

        listView.addRecyclerView(recyclerView);
        recyclerView.setTag(CustomRecyclerView.HEADER_TAG);
    }

    @Override
    public void addListener() {
        listView.setTouchEventLister(new CustomRecyclerView.TouchEventLister() {
            @Override
            public void onRecyclerTouch(boolean isTouch) {
                isRecyclerGroupTouch = isTouch;
            }
        });
        recyclerView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (!isRecyclerGroupTouch)
                    listView.onTouch(motionEvent, true);
                //				KLog.w("motionEvent",motionEvent.getAction()+"");
                if (MotionEvent.ACTION_UP == motionEvent.getAction()) {
                    isRecyclerGroupTouch = false;
                }
                return false;
            }
        });
        listView.setSelectItemListener(new CustomRecyclerView.OnSelectItemListener() {
            @Override
            public void onSelect(int x, int y) {
                //选择的那个单元格。
                Intent intent;
                Toast.makeText(getActivity(), "您点击的位置：" + x + "行" + ":" + y + "列" + "的数据", Toast.LENGTH_SHORT).show();
                if (x > -1) {
                    if (y >= 11) {
                        intent = new Intent(getActivity(), OffOnActivity.class);
                        getActivity().startActivity(intent);
                    } else {
                        intent = new Intent(getActivity(), GrowthRecordsActivity.class);
                        getActivity().startActivity(intent);
                    }

                }
            }
        });
        //		/**
        //		 * 下拉刷新事件
        //		 */
        //		sRf.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
        //			@Override
        //			public void onRefresh() {
        //				handler.postDelayed(new Runnable() {
        //					@Override
        //					public void run() {
        //						//						getData();
        //						rAdapter.notifyDataSetChanged();
        //						sRf.setRefreshing(false);
        //					}
        //				}, 2000);
        //			}
        //		});
    }

    @Override
    public void initData() {
        listName.add("设备编号");
        listName.add("设备名称");
        listName.add("空气温度");
        listName.add("空气湿度");
        listName.add("采集时间");
        listName.add("1号采集点温度");
        listName.add("1号采集点湿度");
        listName.add("2号采集点温度");
        listName.add("2号采集点湿度");
        listName.add("3号采集点温度");
        listName.add("3号采集点湿度");
        listName.add("二氧化碳");
        listName.add("电量");
        listName.add("开关状态");

        //		nodeList= (List<NodeItem.Node>) JctlApplication.getCache().getAsObject(KeyConstants.KAY_NODE);
    }

    class LocalReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            //收到广播后的处理
            //			String orderid = intent.getStringExtra("order");
            //			loadData(orderid);
            List<NodeItem.InfoBean> list = (List<NodeItem.InfoBean>) intent.getSerializableExtra("nodeList");
            if (list.size() > 0) {
                rAdapter.setNewData(list);
            }
            rAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BroadCastManager.getInstance().unregisterReceiver(getActivity(), mReceiver);//注销广播接收者
    }
}
