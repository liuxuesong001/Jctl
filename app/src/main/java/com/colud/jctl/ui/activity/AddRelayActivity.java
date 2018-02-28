package com.colud.jctl.ui.activity;

import android.app.Dialog;
import android.os.Build;
import android.support.v4.util.ArrayMap;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.colud.jctl.JctlApplication;
import com.colud.jctl.api.KeyConstants;
import com.colud.jctl.base.BaseActivity;
import com.colud.jctl.bean.FarmManageBean;
import com.colud.jctl.mvp.contract.AddRelayContract;
import com.colud.jctl.mvp.presenter.AddRelayPresenter;
import com.colud.jctl.base.AppManager;
import com.colud.jctl.ui.custom.TitleBar;
import com.colud.jctl.utils.BarStatusHeightUtil;
import com.colud.jctl.utils.ToastUtils;
import com.jctl.colud.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 添加中继
 * Created by Jcty on 2017/4/7.
 */

public class AddRelayActivity extends BaseActivity<AddRelayPresenter> implements AddRelayContract.View {


    @BindView(R.id.title_toolbar)
    TitleBar toolbar;
    @BindView(R.id.title_cotent)
    TextView tvContent;


    @BindView(R.id.addrelay_et)
    EditText etNum;
    @BindView(R.id.addrelay_sdnt_tv)
    TextView addFarmList;
    @BindView(R.id.addrelay_ssnt_ll)
    LinearLayout addrelayLl;
    @BindView(R.id.addrelay_et_ren)
    EditText addrelayEtRen;
    @BindView(R.id.addrelay_time_tv)
    TextView addrelayTimeTv;
    @BindView(R.id.addrelay_time_ll)
    LinearLayout addrelayTimeLl;
    @BindView(R.id.addrelay_et_nodenum)
    EditText addrelayEtNodenum;
    @BindView(R.id.addrelay_et_jd)
    EditText etLog;
    @BindView(R.id.addrelay_et_wd)
    EditText etLat;
    @BindView(R.id.addrelay_et_address)
    EditText etAddr;
    @BindView(R.id.addrelay_address_rl)
    LinearLayout addrelayAddressRl;
    @BindView(R.id.addrelay_bushe_tv)
    TextView addrelayBusheTv;
    @BindView(R.id.addrelay_bushe_rl)
    LinearLayout addrelayBusheRl;


    private Map<String, String> map = new ArrayMap<>();

    private OptionsPickerView opvFarm;

    private List<String> sList = new ArrayList<>();
    private List<String> idList = new ArrayList<>();

    private String farmId = "";

    private String log = "";

    private String lat = "";

    private String addR = "";

    private Dialog progressDialog;


    @Override
    public int getContentViewId() {
        return R.layout.activity_addrelay;
    }

    @Override
    public void initViews() {
        tvContent.setVisibility(View.VISIBLE);
        tvContent.setText(JctlApplication.getAppResources().getString(R.string.mine_wc));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//不显示自带标题
        toolbar.setCenterTitle(JctlApplication.getAppResources().getString(R.string.tv_tjzj));
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

    }

    @Override
    public void initData() {
        if (!"".equals(KeyConstants.USER_SINGLEID)) {
            map.clear();
            map.put("singleId", KeyConstants.USER_SINGLEID);
            mPresenter.setAddFarmList(map);
        }
    }


    @OnClick({R.id.title_cotent, R.id.addrelay_ssnt_ll, R.id.addrelay_time_ll, R.id.addrelay_address_rl, R.id.addrelay_bushe_rl})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_cotent:
                if (!"".equals(etNum.getText().toString().trim())) {

                } else {
                    ToastUtils.showLong("请填写中继编号");
                    return;
                }
                if (!"".equals(addFarmList.getText().toString().trim())) {

                } else {
                    ToastUtils.showLong("请选择所属农场");
                    return;
                }
                map.clear();

                log = etLog.getText().toString().trim();

                lat = etLat.getText().toString().trim();

                addR = etAddr.getText().toString().trim();

                if (!"".equals(farmId)) {
                    map.put("farmerId", farmId);
                }
                map.put("singleId", KeyConstants.USER_SINGLEID);
                map.put("relayNum", etNum.getText().toString().trim());
                map.put("log", log);
                map.put("lat", lat);
                map.put("area", addR);

                mPresenter.setAddRelay(map);

                break;
            case R.id.addrelay_ssnt_ll:
                if (opvFarm != null) {
                    opvFarm.show();
                } else {
                    ToastUtils.showLong("农场列表还未加载完成,请稍后再试");
                }
                break;
            case R.id.addrelay_time_ll:
                break;
            case R.id.addrelay_address_rl:
                break;
            case R.id.addrelay_bushe_rl:
                break;
            default:
        }
    }

    @Override
    protected AddRelayPresenter onCreatePresenter() {
        return new AddRelayPresenter(this);
    }

    @Override
    public void showDialog() {
        if (progressDialog == null) {
            progressDialog = new Dialog(AddRelayActivity.this, R.style.progress_dialog);
            progressDialog.setContentView(R.layout.dialog);
            progressDialog.setCancelable(true);
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            TextView msg = (TextView) progressDialog.findViewById(R.id.id_tv_loadingmsg);
            msg.setText("加载农场列表...");
            progressDialog.show();
        }
    }

    @Override
    public void onSucceedFarmList(FarmManageBean data) {
        if (data.getnList() != null && data.getnList().size() > 0) {
            sList.clear();
            idList.clear();
            sList = data.getnList();
            idList = data.getIdList();
            setFarmPickerView();
        }

    }

    @Override
    public void onSucceedAddRelay(FarmManageBean data) {
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
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    public void setFarmPickerView() {
        opvFarm = new OptionsPickerView.Builder(AddRelayActivity.this, listenerRelay)
                .setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tvFinish = (TextView) v.findViewById(R.id.tv_finish);
                        TextView prickerviewTitle = (TextView) v.findViewById(R.id.prickerview_title);
                        prickerviewTitle.setText("农场列表");
                        tvFinish.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                opvFarm.returnData();
                            }
                        });

                    }
                })

                .setDividerColor(JctlApplication.getAppResources().getColor(R.color.color_CECECE))
                .setTextColorCenter(JctlApplication.getAppResources().getColor(R.color.color_3AB07D)) //设置选中项文字颜色
                .setContentTextSize(15)
                .isDialog(true)
                .build();
        opvFarm.setPicker(sList);

    }

    private OptionsPickerView.OnOptionsSelectListener listenerRelay = new OptionsPickerView.OnOptionsSelectListener() {
        @Override
        public void onOptionsSelect(int options1, int options2, int options3, View v) {
            String farnName = sList.get(options1);
            farmId = idList.get(options1);
            addFarmList.setText(farnName);
        }
    };

}
