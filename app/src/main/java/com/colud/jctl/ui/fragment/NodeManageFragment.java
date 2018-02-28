package com.colud.jctl.ui.fragment;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.colud.jctl.JctlApplication;
import com.colud.jctl.api.KeyConstants;
import com.colud.jctl.base.AppManager;
import com.colud.jctl.base.BaseFragment;
import com.colud.jctl.bean.NodeDataManageItem;
import com.colud.jctl.mvp.contract.NodeDataManageContract;
import com.colud.jctl.mvp.presenter.NodeDataManagePresenter;
import com.colud.jctl.receiver.BroadCastManager;
import com.colud.jctl.utils.EditTextUtil;
import com.colud.jctl.utils.ToastUtils;
import com.jctl.colud.R;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 设备页面的节点管理
 * Created by Jcty on 2017/4/10.
 */

public class NodeManageFragment extends BaseFragment<NodeDataManagePresenter> implements NodeDataManageContract.View {
    @BindView(R.id.node_num)
    TextView nodeNum;
    @BindView(R.id.et_node_num)
    EditText etNodeNum;
    @BindView(R.id.node_jdmc)
    TextView nodeJdmc;
    @BindView(R.id.et_node_jdmc)
    EditText etNodeJdmc;
    @BindView(R.id.node_sblx)
    TextView nodeSblx;
    @BindView(R.id.node_ssren)
    TextView nodeSsren;
    @BindView(R.id.node_syren)
    TextView nodeSyren;
    @BindView(R.id.node_relay)
    TextView nodeRelay;
    @BindView(R.id.addrelay_time_ll)
    LinearLayout addrelayTimeLl;
    @BindView(R.id.node_sjcjzq)
    TextView nodeSjcjzq;
    @BindView(R.id.addrelay_detail_nodenum)
    EditText addrelayDetailNodenum;
    @BindView(R.id.node_jssj)
    TextView nodeJssj;
    @BindView(R.id.addrelay_detail_jd)
    EditText addrelayDetailJd;
    @BindView(R.id.node_ssnt)
    TextView nodeSsnt;
    @BindView(R.id.node_sbmc)
    TextView nodeSbmc;
    @BindView(R.id.et_node_sbmc)
    EditText etNodeOfnMc;
    @BindView(R.id.node_dl)
    TextView nodeDl;
    @BindView(R.id.relay_detaul_imgd)
    ImageView relayDetaulImgd;
    @BindView(R.id.addrelay_bushe_rl)
    LinearLayout addrelayBusheRl;
    //	@BindView(R.id.tv_gl)
    //	TextView tvGl;
    @BindView(R.id.ssnt_ll)
    //	LinearLayout ssntLl;
            //	@BindView(R.id.btm_gl)
            LinearLayout btmGl;
    @BindView(R.id.img_ssnt)
    ImageView riImg;

    private LocalReceiver mReceiver;


    //	private NodeDetailsManageFragmentAdapter mAdapter;


    private OptionsPickerView opvnh;

    private String id = "";

    private List<String> nameList;
    private List<String> idList;

    private String nhId = "";

    private String nodeName = "";

    private String onOffName = "";

    private Map<String, String> map = new ArrayMap<>();

    private Dialog dialog;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_node_details_manage;
    }

    @Override
    public void onResume() {
        super.onResume();
        //接收广播
        try {
            IntentFilter filter = new IntentFilter();
            filter.addAction(KeyConstants.NODE_DATAILS_INTENT);
            filter.addAction(KeyConstants.NODE_INTENT_MANAGE);
            mReceiver = new LocalReceiver();
            BroadCastManager.getInstance().registerReceiver(getActivity(), mReceiver, filter);//注册广播接收者
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initViews() {

    }


    @Override
    public void addListener() {

    }

    @Override
    public void initData() {
    }

    @Override
    public void showDialog() {
        if (dialog == null) {
            dialog = new Dialog(getActivity(), R.style.progress_dialog);
            dialog.setContentView(R.layout.dialog);
            dialog.setCancelable(true);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            TextView msg = (TextView) dialog.findViewById(R.id.id_tv_loadingmsg);
            msg.setText("加载中...");
            dialog.show();
        }

    }


    @Override
    public void onSucceed(NodeDataManageItem data) {

    }

    @Override
    public void onSucceedUpdate(NodeDataManageItem data) {
        ToastUtils.showLong(data.getMsg());
        AppManager.newInstance().finishCurrentActivity();

    }

    @Override
    public void onFailure(String err, Throwable e) {

    }

    @Override
    public void onFail(String err) {

    }

    @Override
    public void hideDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    protected NodeDataManagePresenter onCreatePresenter() {
        return new NodeDataManagePresenter(this);
    }

    public void setNhTypePickerView() {
        opvnh = new OptionsPickerView.Builder(getActivity(), listenerNh)
                .setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tvFinish = (TextView) v.findViewById(R.id.tv_finish);
                        TextView prickerviewTitle = (TextView) v.findViewById(R.id.prickerview_title);
                        prickerviewTitle.setText("农户");
                        tvFinish.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                opvnh.returnData();
                            }
                        });

                    }
                })

                .setDividerColor(JctlApplication.getAppResources().getColor(R.color.color_CECECE))
                .setTextColorCenter(JctlApplication.getAppResources().getColor(R.color.color_3AB07D)) //设置选中项文字颜色
                .setContentTextSize(15)
                .isDialog(true)
                .build();
        opvnh.setPicker(nameList);
    }

    private OptionsPickerView.OnOptionsSelectListener listenerNh = new OptionsPickerView.OnOptionsSelectListener() {
        @Override
        public void onOptionsSelect(int options1, int options2, int options3, View v) {
            String tx = nameList.get(options1);
            nhId = idList.get(options1);
            nodeSsnt.setText(tx);
        }
    };


    @OnClick({R.id.ssnt_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ssnt_ll:
                if (opvnh != null) {
                    opvnh.show();
                } else {
                    ToastUtils.showLong("暂无所属农田");
                }
                break;
            //			case R.id.btm_gl:
            //				if ("管理".equals(tvGl.getText().toString().trim())) {
            //					tvGl.setText(JctlApplication.getAppResources().getString(R.string.mine_wc));
            //					nodeJdmc.setVisibility(View.GONE);
            //					nodeSbmc.setVisibility(View.GONE);
            //					etNodeOfnMc.setVisibility(View.VISIBLE);
            //					etNodeJdmc.setVisibility(View.VISIBLE);
            //					riImg.setVisibility(View.VISIBLE);
            //					EditTextUtil.showSoftInputFromWindow(getActivity(),etNodeJdmc);
            //				} else {
            //					//					@Query("id") String id,
            //					//					@Query("farmlandId") String farmlandId,
            //					//					@Query("nodeNum") String nodeNum,
            //					map.clear();
            //					map.put("singleId",KeyConstants.KAY_USERID);
            //
            //					if (!"".equals(id)){
            //						map.put("id",id);
            //					}
            //					if (!"".equals(etNodeJdmc.getText().toString().trim())){
            //						nodeName=etNodeJdmc.getText().toString().trim();
            //					}
            //					if (!"".equals(nhId)){
            //						map.put("farmlandId",nhId);
            //					}
            //					if (!"".equals(etNodeOfnMc.getText().toString().trim())){
            //						onOffName=etNodeOfnMc.getText().toString().trim();
            //					}
            //					map.put("nodeName",nodeName);
            //					map.put("onOffName",onOffName);
            //					mPresenter.setNodeUpdate(map);
            //				}
            //			break;
        }
    }

    class LocalReceiver extends BroadcastReceiver {


        @Override
        public void onReceive(Context context, Intent intent) {
            //收到广播后的处理
            NodeDataManageItem.NodeBean data = (NodeDataManageItem.NodeBean) intent.getSerializableExtra("nodeDatails");
            if (data != null) {
                if (data.getId() != null && !"".equals(data.getId())) {
                    id = data.getId();
                }
                if (!TextUtils.isEmpty(data.getNodeNum())) {
                    nodeNum.setText(data.getNodeNum());
                }
                if (!TextUtils.isEmpty(data.getNodeAlise())) {
                    nodeJdmc.setText(data.getNodeAlise());
                    etNodeJdmc.setText(data.getNodeAlise());
                }
                //			if (data.getType() != null && !TextUtils.isEmpty(data.getType())) {
                //				nodeSblx.setText(data.getType());
                //			}
                //			if (!TextUtils.isEmpty(data.get_$UserName37())) {
                //				nodeSsren.setText(data.get_$UserName37());
                //			}
                if (!TextUtils.isEmpty(data.getUsedName())) {
                    nodeSyren.setText(data.getUsedName());
                }
                if (!TextUtils.isEmpty(data.getRelayNum())) {
                    nodeRelay.setText(data.getRelayNum());
                }
                //			if (data.getCycle() != null && !TextUtils.isEmpty(data.getCycle())) {
                //				nodeSjcjzq.setText(data.getCycle());
                //			}
                if (!TextUtils.isEmpty(data.getAddTime())) {
                    nodeJssj.setText(data.getAddTime());
                }
                if (data.getFarmlandName() != null && !TextUtils.isEmpty(data.getFarmlandName())) {
                    nodeSsnt.setText(data.getFarmlandName());
                }
                if (!TextUtils.isEmpty(data.getPower())) {
                    nodeDl.setText(data.getPower());
                }
                if (!TextUtils.isEmpty(data.getOnOffName())) {
                    nodeSbmc.setText(data.getOnOffName());
                    etNodeOfnMc.setText(data.getOnOffName());
                }
                if (!TextUtils.isEmpty(data.getNodeAlise())) {
                    nodeJdmc.setText(data.getNodeAlise());
                    etNodeJdmc.setText(data.getNodeAlise());
                }
                nameList = (List<String>) intent.getSerializableExtra("nodeName");
                idList = (List<String>) intent.getSerializableExtra("nodeId");
                if (nameList != null && nameList.size() > 0 && idList != null && idList.size() > 0) {
                    setNhTypePickerView();
                }
            }
            String yes = intent.getStringExtra("yes");
            if (yes != null && "yes".equals(yes)) {
                nodeJdmc.setVisibility(View.GONE);
                nodeSbmc.setVisibility(View.GONE);
                etNodeOfnMc.setVisibility(View.VISIBLE);
                etNodeJdmc.setVisibility(View.VISIBLE);
                riImg.setVisibility(View.VISIBLE);
                EditTextUtil.showSoftInputFromWindow(getActivity(), etNodeJdmc);
            } else if (yes != null && "no".equals(yes)) {
                map.clear();
                map.put("singleId", KeyConstants.USER_SINGLEID);

                if (!"".equals(id)) {
                    map.put("id", id);
                }
                if (!"".equals(etNodeJdmc.getText().toString().trim())) {
                    nodeName = etNodeJdmc.getText().toString().trim();
                }
                if (!"".equals(nhId)) {
                    map.put("farmlandId", nhId);
                }
                if (!"".equals(etNodeOfnMc.getText().toString().trim())) {
                    onOffName = etNodeOfnMc.getText().toString().trim();
                }
                map.put("nodeAlise", nodeName);
                map.put("onOffName", onOffName);
                mPresenter.setNodeUpdate(map);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BroadCastManager.getInstance().unregisterReceiver(getActivity(), mReceiver);//注销广播接收者
    }
}
