package com.colud.jctl.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.colud.jctl.JctlApplication;
import com.colud.jctl.base.BaseActivity;
import com.colud.jctl.base.BasePesenter;
import com.colud.jctl.bean.NodeCollectionCycle;
import com.colud.jctl.base.AppManager;
import com.jctl.colud.R;
import com.suke.widget.SwitchButton;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 周期刷新
 * Created by Jcty on 2017/5/11.
 */

public class CycleControlActivity extends BaseActivity {
    @BindView(R.id.btn_back)
    ImageButton btnBack;
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.open_date)
    TextView openDate;
    @BindView(R.id.open_cycle)
    TextView openCycle;
    @BindView(R.id.off_on_day)
    SwitchButton offOnDay;
    @BindView(R.id.off_ll_a)
    LinearLayout offLlA;
    @BindView(R.id.close_date)
    TextView closeDate;
    @BindView(R.id.close_date_cycle)
    TextView closeDateCycle;
    @BindView(R.id.off_on_xq)
    SwitchButton offOnXq;
    @BindView(R.id.off_ll_b)
    LinearLayout offLlB;
    @BindView(R.id.tv_on)
    TextView tvOn;
    @BindView(R.id.tv_off)
    TextView tvOff;
    @BindView(R.id.on_ll)
    LinearLayout onLL;
    @BindView(R.id.off_ll)
    LinearLayout offLL;

    private NodeCollectionCycle item;

    @Override
    protected BasePesenter onCreatePresenter() {
        return null;
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_cycle_control;
    }

    @Override
    public void initViews() {
        tvTitle.setText(JctlApplication.getAppResources().getString(R.string.tv_zqkz));

    }

    @Override
    public void addListener() {

    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.on_ll, R.id.off_ll, R.id.btn_back, R.id.ll_back})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.on_ll:
                intent = new Intent(CycleControlActivity.this, PeriodActivity.class);
                intent.putExtra("cycleItem", item);
                intent.putExtra("cycle", "on");
                startActivity(intent);
                break;
            case R.id.off_ll:
                intent = new Intent(CycleControlActivity.this, PeriodActivity.class);
                intent.putExtra("cycleItem", item);
                intent.putExtra("cycle", "off");
                startActivity(intent);
                break;
            case R.id.btn_back:
                AppManager.newInstance().finishCurrentActivity();
                break;
            case R.id.ll_back:
                AppManager.newInstance().finishCurrentActivity();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        item = (NodeCollectionCycle) getIntent().getSerializableExtra("cycleItem");
        if (item.getOnWeeks() != null) {
            tvOn.setVisibility(View.GONE);
            openDate.setVisibility(View.VISIBLE);
            openCycle.setVisibility(View.VISIBLE);
            if (item.getCycleOn() != null) {
                openDate.setText(item.getCycleOn());
            }
            if (item.getOnWeeks().size() > 0) {
                openCycle.setText("");
                for (String week : item.getOnWeeks()) {
                    if (week.equals("1")) {
                        week = "日";
                    } else if (week.equals("2")) {
                        week = "一";
                    } else if (week.equals("3")) {
                        week = "二";
                    } else if (week.equals("4")) {
                        week = "三";
                    } else if (week.equals("5")) {
                        week = "四";
                    } else if (week.equals("6")) {
                        week = "五";
                    } else if (week.equals("7")) {
                        week = "六";
                    }
                    openCycle.append("周" + week + "\t");
                }
            }
        }
        if (item.getOffWeeks() != null) {
            tvOff.setVisibility(View.GONE);
            closeDate.setVisibility(View.VISIBLE);
            closeDateCycle.setVisibility(View.VISIBLE);
            if (item.getCycleOff() != null) {
                closeDate.setText(item.getCycleOff());
            }
            if (item.getOffWeeks().size() > 0) {
                closeDateCycle.setText("");
                for (String week : item.getOffWeeks()) {
                    if (week.equals("1")) {
                        week = "日";
                    } else if (week.equals("2")) {
                        week = "一";
                    } else if (week.equals("3")) {
                        week = "二";
                    } else if (week.equals("4")) {
                        week = "三";
                    } else if (week.equals("5")) {
                        week = "四";
                    } else if (week.equals("6")) {
                        week = "五";
                    } else if (week.equals("7")) {
                        week = "六";
                    }
                    closeDateCycle.append("周" + week + "\t");
                }
            }
        }
    }
}
