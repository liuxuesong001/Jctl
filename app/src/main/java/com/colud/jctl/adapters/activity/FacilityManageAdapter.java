package com.colud.jctl.adapters.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.colud.jctl.JctlApplication;
import com.colud.jctl.bean.FacilityItem;
import com.colud.jctl.ui.activity.FarmDetailActivity;
import com.colud.jctl.ui.activity.NodeManageActivity;
import com.colud.jctl.ui.activity.RelayDetailActivity;
import com.jctl.colud.R;
import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.base.CommonBaseAdapter;

import java.util.List;

public class FacilityManageAdapter extends CommonBaseAdapter<FacilityItem.InfoBean> {

    Intent in;

    private int colorPosion = 0;


    public FacilityManageAdapter(Context context, List<FacilityItem.InfoBean> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }


    @Override
    protected void convert(ViewHolder holder, final FacilityItem.InfoBean data) {
        holder.setText(R.id.field_item_relaynum, data.getRelayNum());
        holder.setText(R.id.node_item_farmername, data.getFarmerName());
        holder.setText(R.id.node_item_nh, String.valueOf(data.getNodeNum()));
        holder.setText(R.id.node_item_powersupply, data.getPowerSupply());

        if (colorPosion < JctlApplication.getAppResources().getIntArray(R.array.colorBj).length - 1) {
            holder.setBgColor(R.id.ll_adapter_bj, JctlApplication.getAppResources().getIntArray(R.array.colorBj)[colorPosion]);
            colorPosion++;
        } else {
            holder.setBgColor(R.id.ll_adapter_bj, JctlApplication.getAppResources().getIntArray(R.array.colorBj)[colorPosion]);
            colorPosion = 0;
        }

        holder.setOnClickListener(R.id.facilit_ll_a, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in = new Intent(mContext, RelayDetailActivity.class);
                in.putExtra("id", data.getId());
                in.putExtra("number", data.getRelayNum());
                mContext.startActivity(in);
            }
        });
        holder.setOnClickListener(R.id.facilit_ll_b, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in = new Intent(mContext, FarmDetailActivity.class);
                in.putExtra("id", data.getId());
                in.putExtra("farmName", data.getFarmerName());
                mContext.startActivity(in);
            }
        });
        holder.setOnClickListener(R.id.facilit_ll_c, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in = new Intent(mContext, NodeManageActivity.class);
                in.putExtra("id", data.getId());
                in.putExtra("node", String.valueOf(data.getRelayNum()));
                mContext.startActivity(in);
            }
        });

    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.include_facilit_manage;
    }
}