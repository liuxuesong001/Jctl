package com.colud.jctl.adapters.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.colud.jctl.JctlApplication;
import com.colud.jctl.bean.FieldManageItem;
import com.colud.jctl.ui.activity.CropActivity;
import com.colud.jctl.ui.activity.FieldDetailActivity;
import com.colud.jctl.ui.activity.NodeActivity;
import com.jctl.colud.R;
import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.base.CommonBaseAdapter;

import java.util.List;

public class FieldManageAdapter extends CommonBaseAdapter<FieldManageItem.InfoBean> {

    private Intent intent;

    private String farmerId;

    private int colorPosion = 0;

    public FieldManageAdapter(Context context, List<FieldManageItem.InfoBean> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    public void setfarmerId(String farmerId) {
        this.farmerId = farmerId;
    }

    @Override
    protected void convert(ViewHolder holder, final FieldManageItem.InfoBean data) {
        holder.setText(R.id.field_item_relaynum, data.getAlias());
        holder.setText(R.id.node_item_farmername, String.valueOf(data.getArea()));
        holder.setText(R.id.node_item_nh, data.getPlantVaritety());
        holder.setText(R.id.node_item_powersupply, data.getNodeNumber());


        if (colorPosion < JctlApplication.getAppResources().getIntArray(R.array.colorBj).length - 1) {
            holder.setBgColor(R.id.ll_adapter_bj, JctlApplication.getAppResources().getIntArray(R.array.colorBj)[colorPosion]);
            colorPosion++;
        } else {
            holder.setBgColor(R.id.ll_adapter_bj, JctlApplication.getAppResources().getIntArray(R.array.colorBj)[colorPosion]);
            colorPosion = 0;
        }


        holder.setOnClickListener(R.id.field_ll_a, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(mContext, FieldDetailActivity.class);
                intent.putExtra("id", data.getId());
                mContext.startActivity(intent);
            }
        });
        holder.setOnClickListener(R.id.field_ll_c, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(mContext, CropActivity.class);
                intent.putExtra("farmCrop", data.getPlantVaritety());
                intent.putExtra("farmerId", farmerId);
                mContext.startActivity(intent);
            }
        });
        holder.setOnClickListener(R.id.field_ll_d, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!data.getNodeNumber().equals("0")) {
                    intent = new Intent(mContext, NodeActivity.class);
                    intent.putExtra("area", data.getAlias());
                    intent.putExtra("farmerId", farmerId);
                    mContext.startActivity(intent);
                }
            }
        });

    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.include_field_manage;
    }

}