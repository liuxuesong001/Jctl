package com.colud.jctl.ui.activity;

import android.app.Dialog;
import android.os.Build;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.colud.jctl.JctlApplication;
import com.colud.jctl.api.KeyConstants;
import com.colud.jctl.base.BaseActivity;
import com.colud.jctl.bean.FarmManageBean;
import com.colud.jctl.bean.RelayDetailItem;
import com.colud.jctl.mvp.contract.RelayDetailContract;
import com.colud.jctl.mvp.presenter.RelayDetailPresenter;
import com.colud.jctl.base.AppManager;
import com.colud.jctl.ui.custom.TitleBar;
import com.colud.jctl.utils.BarStatusHeightUtil;
import com.colud.jctl.utils.ToastUtils;
import com.jctl.colud.R;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Jcty on 2017/4/7.
 */

public class RelayDetailActivity extends BaseActivity<RelayDetailPresenter> implements RelayDetailContract.View {

    @BindView(R.id.title_toolbar)
    TitleBar toolbar;
    @BindView(R.id.title_cotent)
    TextView tvContent;
    @BindView(R.id.btm_delete)
    LinearLayout btmDelete;
    //	@BindView(R.id.addrelay_detail_num)
    //	EditText etNum;
    @BindView(R.id.addrelay_sdnt_tv)
    TextView tvFarmName;
    @BindView(R.id.addrelay_detail_dl)
    TextView tvPs;
    //	@BindView(R.id.addrelay_detail_ren)
    //	EditText etRen;
    @BindView(R.id.addrelay_time_tv)
    TextView tvBdTime;
    @BindView(R.id.addrelay_time_ll)
    LinearLayout addrelayTimeLl;
    @BindView(R.id.addrelay_detail_nodenum)
    EditText etNodeNum;
    @BindView(R.id.addrelay_detail_jd)
    EditText etJd;
    @BindView(R.id.addrelay_detail_wd)
    EditText etWd;
    @BindView(R.id.addrelay_address_tv)
    TextView relayAddr;
    @BindView(R.id.addrelay_address_rl)
    LinearLayout addrelayAddressRl;
    @BindView(R.id.addrelay_bushe_tv)
    TextView tvAddTime;
    @BindView(R.id.addrelay_bushe_rl)
    LinearLayout addrelayBusheRl;
    @BindView(R.id.relay_detaul_imga)
    ImageView Imga;
    @BindView(R.id.tv_relay_a)
    TextView tvRelayA;
    @BindView(R.id.tv_relay_b)
    TextView tvRelayB;
    @BindView(R.id.tv_relay_c)
    TextView tvRelayC;
    @BindView(R.id.tv_relay_d)
    TextView tvRelayD;
    @BindView(R.id.tv_relay_e)
    TextView tvRelayE;
    @BindView(R.id.addrelay_ll)
    LinearLayout ssncLL;
    @BindView(R.id.relay_et_address)
    EditText etAddR;

    private Map<String, String> map = new ArrayMap<>();

    private Dialog progressDialog;

    private OptionsPickerView opvRelay;

    private String farmId = "";

    private String id = "";

    private List<String> nList = new ArrayList<>();
    private List<String> idList = new ArrayList<>();


    @Override
    public int getContentViewId() {
        return R.layout.activity_relay_detail;
    }

    @Override
    public void initViews() {
        String num = getIntent().getStringExtra("number");
        tvContent.setVisibility(View.VISIBLE);
        tvContent.setText(JctlApplication.getAppResources().getString(R.string.mine_bj));

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
        if (num != null && !"".equals(num)) {
            toolbar.setCenterTitle("中继" + num);
        } else {
            toolbar.setCenterTitle(JctlApplication.getAppResources().getString(R.string.tv_jzxq));
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void addListener() {

    }

    @Override
    public void initData() {
        //		@Query("id") String uid,
        //		@Query("singleId") String singleId
        map.clear();
        id = getIntent().getStringExtra("id");
        if (id != null && !"".equals(id)) {
            map.put("singleId", KeyConstants.USER_SINGLEID);
            map.put("id", id);
            mPresenter.setRelayData(map);
        }
    }


    @OnClick({R.id.title_cotent, R.id.btm_delete, R.id.addrelay_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_cotent:
                if ("编辑".equals(tvContent.getText().toString())) {
                    tvContent.setText(JctlApplication.getAppResources().getString(R.string.mine_wc));
                    btmDelete.setVisibility(View.VISIBLE);
                    Imga.setVisibility(View.VISIBLE);
                    //					tvRelayA.setVisibility(View.GONE);
                    tvRelayC.setVisibility(View.GONE);
                    tvRelayD.setVisibility(View.GONE);
                    tvRelayE.setVisibility(View.GONE);
                    //					etNum.setVisibility(View.VISIBLE);
                    etNodeNum.setVisibility(View.VISIBLE);
                    etJd.setVisibility(View.VISIBLE);
                    etWd.setVisibility(View.VISIBLE);
                    relayAddr.setVisibility(View.GONE);
                    etAddR.setVisibility(View.VISIBLE);

                    //					EditTextUtil.isEdit(true, etNum);
                } else {
                    etInputData();
                }
                break;
            case R.id.btm_delete:
                if (id != null && !"".equals(id)) {
                    map.clear();
                    map.put("id", id);
                    mPresenter.setRelayDelete(map);
                }

                break;
            case R.id.addrelay_ll:
                if (opvRelay != null) {
                    opvRelay.show();
                } else {
                    ToastUtils.showLong("暂无农场");
                }
                break;
        }
    }

    /**
     * 输入判断
     */
    protected void etInputData() {
        map.clear();
        map.put("singleId", KeyConstants.USER_SINGLEID);
        if (!"".equals(id)) {
            map.put("id", id);
        }
        if (!"".equals(tvRelayA.getText().toString().trim())) {
            map.put("relayNum", tvRelayA.getText().toString().trim());
        }
        if (!"".equals(farmId)) {
            map.put("farmerId", farmId);
        } else {
            ToastUtils.showLong("请选择所属农场");
            return;
        }
        map.put("log", etJd.getText().toString().trim());
        map.put("lat", etWd.getText().toString().trim());
        map.put("area", etAddR.getText().toString().trim());


        mPresenter.setRelayUpdate(map);

    }

    @Override
    protected RelayDetailPresenter onCreatePresenter() {
        return new RelayDetailPresenter(this);
    }

    @Override
    public void showDialog() {
        if (progressDialog == null) {
            progressDialog = new Dialog(RelayDetailActivity.this, R.style.progress_dialog);
            progressDialog.setContentView(R.layout.dialog);
            progressDialog.setCancelable(true);
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            TextView msg = (TextView) progressDialog.findViewById(R.id.id_tv_loadingmsg);
            msg.setText("加载中...");
            progressDialog.show();
        }
    }

    @Override
    public void onSucceedInfo(RelayDetailItem data) {
        if (data.getData() != null) {
            if (!TextUtils.isEmpty(data.getData().getRelayNum()) && data.getData().getRelayNum() != null) {
                tvRelayA.setText(data.getData().getRelayNum());
            }
            if (data.getData().getFarmer() != null) {
                if (data.getData().getFarmer().getName() != null) {
                    tvFarmName.setText(data.getData().getFarmer().getName());
                }
                if (data.getData().getFarmer().getId() != null) {
                    farmId = data.getData().getFarmer().getId();
                }
            }
            if (!TextUtils.isEmpty(data.getData().getPowerSupply()) && data.getData().getPowerSupply() != null) {
                tvPs.setText(data.getData().getPowerSupply());
            }

            if (data.getData().getUser() != null && !"".equals(data.getData().getUser())) {
                if (!"".equals(data.getData().getUser().getName()) && data.getData().getUser().getName() != null) {
                    tvRelayB.setText(data.getData().getUser().getName());
                }
            }

            if (!"".equals(data.getData().getArea()) && !TextUtils.isEmpty(data.getData().getArea())) {
                relayAddr.setText(data.getData().getArea());
                etAddR.setText(data.getData().getArea());
            }

            if (!TextUtils.isEmpty(data.getData().getBindingTime()) && data.getData().getBindingTime() != null) {
                tvBdTime.setText(data.getData().getBindingTime());
            }

            if (!TextUtils.isEmpty(data.getData().getNodeNum())) {
                tvRelayC.setText(data.getData().getNodeNum());
            }

            if (!TextUtils.isEmpty(data.getData().getLat()) && data.getData().getLat() != null) {
                tvRelayD.setText(data.getData().getLat());
            }

            if (!TextUtils.isEmpty(data.getData().getLog()) && data.getData().getLog() != null) {
                tvRelayE.setText(data.getData().getLog());
            }

            if (!TextUtils.isEmpty(data.getData().getAddTime()) && data.getData().getAddTime() != null) {
                tvAddTime.setText(data.getData().getAddTime());
            }


            if (data.getnList() != null
                    && data.getnList().size() > 0
                    && data.getIdList() != null
                    && data.getIdList().size() > 0) {
                nList.clear();
                idList.clear();
                nList = data.getnList();
                idList = data.getIdList();
                setFarmPickerView();
            }

        }
    }

    @Override
    public void onSucceedRelayUpdate(FarmManageBean data) {
        ToastUtils.showLong(data.getMsg());
        AppManager.newInstance().finishCurrentActivity();
    }

    @Override
    public void onSucceedRelayDelete(RelayDetailItem data) {
        ToastUtils.showLong(data.getMsg());
        AppManager.newInstance().finishCurrentActivity();

    }


    @Override
    public void onFailure(String err, Throwable e) {
        ToastUtils.showLong(err);
    }

    @Override
    public void onFail(String err) {
        KLog.e(err);
    }

    @Override
    public void hideDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    public void setFarmPickerView() {
        opvRelay = new OptionsPickerView.Builder(RelayDetailActivity.this, listenerRelay)
                .setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tvFinish = (TextView) v.findViewById(R.id.tv_finish);
                        TextView prickerviewTitle = (TextView) v.findViewById(R.id.prickerview_title);
                        prickerviewTitle.setText("农场列表");
                        tvFinish.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                opvRelay.returnData();
                            }
                        });

                    }
                })

                .setDividerColor(JctlApplication.getAppResources().getColor(R.color.color_CECECE))
                .setTextColorCenter(JctlApplication.getAppResources().getColor(R.color.color_3AB07D)) //设置选中项文字颜色
                .setContentTextSize(15)
                .isDialog(true)
                .build();
        opvRelay.setPicker(nList);

    }

    private OptionsPickerView.OnOptionsSelectListener listenerRelay = new OptionsPickerView.OnOptionsSelectListener() {
        @Override
        public void onOptionsSelect(int options1, int options2, int options3, View v) {
            String farnName = nList.get(options1);
            farmId = idList.get(options1);
            tvFarmName.setText(farnName);
        }
    };
}
