package com.colud.jctl.ui.activity;

import android.content.Intent;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.colud.jctl.JctlApplication;
import com.colud.jctl.base.BaseActivity;
import com.colud.jctl.bean.CapaCityItem;
import com.colud.jctl.bean.ICItem;
import com.colud.jctl.mvp.contract.ICContract;
import com.colud.jctl.mvp.presenter.ICPresenter;
import com.colud.jctl.base.AppManager;
import com.colud.jctl.utils.ToastUtils;
import com.jctl.colud.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 智能控制选填页
 * Created by Jcty on 2017/4/7.
 */

public class IntelControlActivity extends BaseActivity<ICPresenter> implements ICContract.View {


    @BindView(R.id.btn_back)
    ImageButton btnBack;
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_qx)
    TextView tvQx;
    @BindView(R.id.tv_qx_ll)
    LinearLayout tvQxLl;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.iv_bar)
    ImageButton ivBar;
    @BindView(R.id.title_rl)
    RelativeLayout titleRl;
    @BindView(R.id.intel_tv)
    TextView tvSx;
    @BindView(R.id.intel_ll)
    LinearLayout intelLl;
    @BindView(R.id.et_max)
    EditText etMax;
    @BindView(R.id.intel_max)
    TextView intelMax;
    @BindView(R.id.et_mix)
    EditText etMix;
    @BindView(R.id.intel_mix)
    TextView intelMix;
    @BindView(R.id.intel_et)
    EditText intelEt;
    @BindView(R.id.intel_delete)
    LinearLayout llDelete;
    private String sxType;
    private String nodeMac;
    private String id;

    private String update = null;


    private List<String> listName = new ArrayList<>();

    private OptionsPickerView opv;

    private CapaCityItem.InfoBean data;


    @OnClick({R.id.intel_delete, R.id.btn_back, R.id.tv_content, R.id.ll_back, R.id.intel_ll})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                AppManager.newInstance().finishCurrentActivity();
                break;
            case R.id.btn_back:
                AppManager.newInstance().finishCurrentActivity();
                break;
            case R.id.intel_ll:
                if (update != null && update.equals("update")) {

                } else {
                    if (opv != null) {
                        opv.show();
                    }
                }
                break;
            case R.id.tv_content:
                editTextIF();
                break;
            case R.id.intel_delete:
                if (data != null && data.getId() != null) {
                    Map<String, String> map = new ArrayMap<>();
                    map.put("id", data.getId());
                    mPresenter.setICItem("del", map, 0.0, 0.0);
                }
                break;
        }
    }

    protected void editTextIF() {
        if (!"请选择属性".equals(tvSx.getText().toString().trim())) {

        } else {
            ToastUtils.showLong("请选择属性");
            return;
        }
        if (!TextUtils.isEmpty(etMax.getText().toString().trim())) {

        } else {
            ToastUtils.showLong("请填写最高指数");
            return;
        }
        if (!TextUtils.isEmpty(etMix.getText().toString().trim())) {

        } else {
            ToastUtils.showLong("请填写最低指数");
            return;
        }
        if (!TextUtils.isEmpty(intelEt.getText().toString().trim())) {

        } else {
            ToastUtils.showLong("请填写异常数");
            return;
        }

        Map<String, String> map = new ArrayMap<>();
        map.put("nodeNum", nodeMac);
        if (id != null)
            map.put("id", id);
        if (update != null && update.equals("update")) {

        } else {
            map.put("property", sxType);
        }
        map.put("cycle", intelEt.getText().toString().trim());
        double max = Double.valueOf(etMax.getText().toString().trim());
        double min = Double.valueOf(etMix.getText().toString().trim());

        mPresenter.setICItem(update, map, max, min);

    }


    @Override
    public int getContentViewId() {
        return R.layout.activity_intel_control;
    }

    @Override
    public void initViews() {
        tvTitle.setText(JctlApplication.getAppResources().getString(R.string.tv_znkz));
        tvContent.setVisibility(View.VISIBLE);
        tvContent.setText(JctlApplication.getAppResources().getString(R.string.mine_wc));
        setTypePickerView();

    }

    @Override
    public void addListener() {

    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        nodeMac = intent.getStringExtra("nodeId");
        data = (CapaCityItem.InfoBean) intent.getSerializableExtra("update");
        if (data != null) {
            id = data.getId();
            nodeMac = data.getNodeNum();
            tvSx.setText(data.getProperty());
            etMax.setText(String.valueOf(data.getMax()));
            etMix.setText(String.valueOf(data.getMin()));
            intelEt.setText(data.getCycle());
            update = "update";
            llDelete.setVisibility(View.VISIBLE);
        } else {
            tvSx.setText("请选择属性");
        }

        listName.add("空气温度");
        listName.add("空气湿度");
        listName.add("1号采集点温度");
        listName.add("1号采集点湿度");
        listName.add("2号采集点温度");
        listName.add("2号采集点湿度");
        listName.add("3号采集点温度");
        listName.add("3号采集点湿度");
        listName.add("二氧化碳");
    }

    public void setTypePickerView() {
        opv = new OptionsPickerView.Builder(IntelControlActivity.this, listener)
                .setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tvFinish = (TextView) v.findViewById(R.id.tv_finish);
                        TextView prickerviewTitle = (TextView) v.findViewById(R.id.prickerview_title);
                        prickerviewTitle.setText("属性");
                        tvFinish.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                opv.returnData();
                            }
                        });

                    }
                })

                .setDividerColor(JctlApplication.getAppResources().getColor(R.color.color_CECECE))
                .setTextColorCenter(JctlApplication.getAppResources().getColor(R.color.color_3AB07D)) //设置选中项文字颜色
                .setContentTextSize(15)
                .isDialog(true)
                .build();
        opv.setPicker(listName);

    }

    private OptionsPickerView.OnOptionsSelectListener listener = new OptionsPickerView.OnOptionsSelectListener() {
        @Override
        public void onOptionsSelect(int options1, int options2, int options3, View v) {
            //			private double airTemperature;
            //			private double airHumidity;
            //			private double soilTemperature1;
            //			private double soilHumidity1;
            //			private double soilTemperature2;
            //			private double soilHumidity2;
            //			private double soilTemperature3;
            //			private double soilHumidity3;
            //			private int co2;
            String tx = listName.get(options1);
            if ("空气温度".equals(tx)) {
                sxType = "airTemperature";
            } else if ("空气湿度".equals(tx)) {
                sxType = "airHumidity";
            } else if ("1号采集点温度".equals(tx)) {
                sxType = "soilTemperature1";
            } else if ("1号采集点湿度".equals(tx)) {
                sxType = "soilHumidity1";
            } else if ("2号采集点温度".equals(tx)) {
                sxType = "soilTemperature2";
            } else if ("2号采集点湿度".equals(tx)) {
                sxType = "soilHumidity2";
            } else if ("3号采集点温度".equals(tx)) {
                sxType = "soilTemperature3";
            } else if ("3号采集点湿度".equals(tx)) {
                sxType = "soilHumidity3";
            } else if ("二氧化碳".equals(tx)) {
                sxType = "co2";
            }
            tvSx.setText(tx);
        }
    };

    @Override
    public void showDialog() {

    }

    @Override
    public void onSucceed(ICItem data) {
        //		ToastUtils.showLong(data.getMsg());
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

    }

    @Override
    protected ICPresenter onCreatePresenter() {
        return new ICPresenter(this);
    }

}
