package com.colud.jctl.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.ArrayMap;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.colud.jctl.JctlApplication;
import com.colud.jctl.base.BaseActivity;
import com.colud.jctl.bean.CycleUpdateBean;
import com.colud.jctl.bean.NodeCollectionCycle;
import com.colud.jctl.mvp.contract.CycleContract;
import com.colud.jctl.mvp.presenter.CyclePresenter;
import com.colud.jctl.base.AppManager;
import com.colud.jctl.utils.ToastUtils;
import com.jctl.colud.R;

import java.util.ArrayList;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

;


/**
 * 周期控制
 * Created by Jcty on 2017/4/6.
 */

public class CycleUpdateActivity extends BaseActivity<CyclePresenter> implements CycleContract.View {

    @BindView(R.id.btn_back)
    ImageButton btnBack;
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.cycles_date)
    TextView cyclesDate;
    @BindView(R.id.cycles_ll)
    LinearLayout cyclesLl;
    private String nodeMac, cyclyTime;

    private OptionsPickerView opvtype;
    private ArrayList<Integer> cardItem = new ArrayList<>();
    private NodeCollectionCycle item;
    @SuppressLint("NewApi")
    Map<String, String> map = new ArrayMap<>();


    @OnClick({R.id.cycles_ll, R.id.btn_back, R.id.ll_back})
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.cycles_ll:
                if (opvtype != null)
                    opvtype.show();

                break;
            case R.id.ll_back:
                AppManager.newInstance().finishCurrentActivity();
                break;
            case R.id.btn_back:
                AppManager.newInstance().finishCurrentActivity();
                break;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected CyclePresenter onCreatePresenter() {
        return new CyclePresenter(this);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_cycles;
    }

    @Override
    public void initViews() {
        setTypePickerView();
        tvTitle.setText(JctlApplication.getAppResources().getString(R.string.tv_zqsx));
    }

    @Override
    public void addListener() {

    }

    @Override
    public void initData() {
        getData();
        Intent intent = getIntent();
        item = (NodeCollectionCycle) intent.getSerializableExtra("cycleItem");
        if (item.getNodeId() != null && item.getCycleTime() != null) {
            nodeMac = item.getNodeId();
            cyclesDate.setText(item.getCycleTime() + "\t(分钟)");
        }
    }

    public void setTypePickerView() {
        opvtype = new OptionsPickerView.Builder(CycleUpdateActivity.this, listener)
                .setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tvFinish = (TextView) v.findViewById(R.id.tv_finish);
                        TextView prickerviewTitle = (TextView) v.findViewById(R.id.prickerview_title);
                        prickerviewTitle.setText("分钟选择");
                        tvFinish.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                opvtype.returnData();
                            }
                        });

                    }
                })

                .setDividerColor(JctlApplication.getAppResources().getColor(R.color.color_CECECE))
                .setTextColorCenter(JctlApplication.getAppResources().getColor(R.color.color_3AB07D)) //设置选中项文字颜色
                .setContentTextSize(15)
                .isDialog(true)
                .build();
        opvtype.setPicker(cardItem);

    }

    private OptionsPickerView.OnOptionsSelectListener listener = new OptionsPickerView.OnOptionsSelectListener() {
        @Override
        public void onOptionsSelect(int options1, int options2, int options3, View v) {
            int tx = cardItem.get(options1);
            cyclesDate.setText(String.valueOf(tx) + "\t(分钟)");
            @SuppressLint({"NewApi", "LocalSuppress"})
            Map<String, String> map = new ArrayMap<>();
            map.put("nodeId", nodeMac);
            map.put("cycleTime", String.valueOf(tx));
            mPresenter.getCycleUpdate(map);
        }
    };

    public void getData() {
        for (int i = 1; i < 61; i++) {
            cardItem.add(i);
        }
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void onSucceedCycleUpdate(CycleUpdateBean data) {
        cyclesDate.setText(data.getCycleTime() + "\t(分钟)");
        ToastUtils.showLong(data.getMsg());

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
}
