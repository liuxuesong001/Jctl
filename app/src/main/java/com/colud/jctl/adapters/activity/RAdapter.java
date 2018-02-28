package com.colud.jctl.adapters.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.colud.jctl.JctlApplication;
import com.colud.jctl.bean.NodeItem;
import com.colud.jctl.ui.activity.GrowthRecordsActivity;
import com.colud.jctl.ui.activity.OffOnActivity;
import com.jctl.colud.R;
import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.base.CommonBaseAdapter;

import java.util.List;

public class RAdapter extends CommonBaseAdapter<NodeItem.InfoBean> {

    Intent intent;

    private int colorPosion = 1;


    public RAdapter(Context context, List<NodeItem.InfoBean> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(ViewHolder holder, final NodeItem.InfoBean data) {

        //		if(colorPosion< JctlApplication.getAppResources().getIntArray(R.array.colorBj).length-1){
        //			holder.setBgColor(R.id.ll_adapter_bj,JctlApplication.getAppResources().getIntArray(R.array.colorBj)[colorPosion]);
        //			colorPosion++;
        //		}else{
        //			holder.setBgColor(R.id.ll_adapter_bj,JctlApplication.getAppResources().getIntArray(R.array.colorBj)[colorPosion]);
        //			colorPosion=0;
        //		}


        holder.setText(R.id.a, data.getNodeMac());
        holder.setText(R.id.aa, data.getNodeName());
        holder.setText(R.id.b, String.valueOf(data.getAirTemperature()));
        holder.setText(R.id.c, String.valueOf(data.getAirHumidity()));
        holder.setText(R.id.d, String.valueOf(data.getAddTime()));
        holder.setText(R.id.e, String.valueOf(data.getSoilTemperature1()));
        holder.setText(R.id.f, String.valueOf(data.getSoilHumidity1()));
        holder.setText(R.id.g, String.valueOf(data.getSoilTemperature2()));
        holder.setText(R.id.h, String.valueOf(data.getSoilHumidity2()));
        holder.setText(R.id.y, String.valueOf(data.getSoilTemperature3()));
        holder.setText(R.id.z, String.valueOf(data.getSoilHumidity3()));
        holder.setText(R.id.k, String.valueOf(data.getCo2()));
        holder.setText(R.id.l, String.valueOf(data.getPower()));
        if ("0".equals(data.getOpenFlag())) {
            holder.setText(R.id.m, "关");
            holder.setTextColor(R.id.m, JctlApplication.getAppResources().getColor(R.color.red));
        } else {
            holder.setText(R.id.m, "开");
            holder.setTextColor(R.id.m, JctlApplication.getAppResources().getColor(R.color.color_3AB07D));
        }

        holder.setOnClickListener(R.id.b, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(mContext, GrowthRecordsActivity.class);
                //				intent.putExtra("num",data.getNodeMac());
                //				intent.putExtra("content","airTemperature");
                //				intent.putExtra("value",String.valueOf(data.getAirTemperature()));
                //				intent.putExtra("text","空气温度");
                intent.putExtra("data", data);
                intent.putExtra("type", "a");
                mContext.startActivity(intent);
            }
        });
        holder.setOnClickListener(R.id.c, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(mContext, GrowthRecordsActivity.class);
                //				intent.putExtra("num",data.getNodeMac());
                //				intent.putExtra("content","airHumidity");
                //				intent.putExtra("value",String.valueOf(data.getAirHumidity()));
                //				intent.putExtra("text","空气湿度");
                intent.putExtra("data", data);
                intent.putExtra("type", "b");
                mContext.startActivity(intent);
            }
        });
        holder.setOnClickListener(R.id.e, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(mContext, GrowthRecordsActivity.class);
                //				intent.putExtra("num",data.getNodeMac());
                //				intent.putExtra("content","soilTemperature1");
                //				intent.putExtra("value",String.valueOf(data.getSoilTemperature1()));
                //				intent.putExtra("text","1号采集温度");
                intent.putExtra("data", data);
                intent.putExtra("type", "c");
                mContext.startActivity(intent);
            }
        });
        holder.setOnClickListener(R.id.f, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(mContext, GrowthRecordsActivity.class);
                //				intent.putExtra("num",data.getNodeMac());
                //				intent.putExtra("content","soilHumidity1");
                //				intent.putExtra("value",String.valueOf(data.getSoilHumidity1()));
                //				intent.putExtra("text","1号采集湿度");
                intent.putExtra("data", data);
                intent.putExtra("type", "d");
                mContext.startActivity(intent);
            }
        });
        holder.setOnClickListener(R.id.g, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(mContext, GrowthRecordsActivity.class);
                //				intent.putExtra("num",data.getNodeMac());
                //				intent.putExtra("content","soilTemperature2");
                //				intent.putExtra("value",String.valueOf(data.getSoilTemperature2()));
                //				intent.putExtra("text","2号采集温度");
                intent.putExtra("data", data);
                intent.putExtra("type", "e");
                mContext.startActivity(intent);
            }
        });
        holder.setOnClickListener(R.id.h, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(mContext, GrowthRecordsActivity.class);
                //				intent.putExtra("num",data.getNodeMac());
                //				intent.putExtra("content","soilHumidity2");
                //				intent.putExtra("value",String.valueOf(data.getSoilHumidity2()));
                //				intent.putExtra("text","2号采集湿度");
                intent.putExtra("data", data);
                intent.putExtra("type", "f");
                mContext.startActivity(intent);
            }
        });
        holder.setOnClickListener(R.id.y, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(mContext, GrowthRecordsActivity.class);
                //				intent.putExtra("num",data.getNodeMac());
                //				intent.putExtra("content","soilTemperature3");
                //				intent.putExtra("value",String.valueOf(data.getSoilTemperature3()));
                //				intent.putExtra("text","3号采集温度");
                intent.putExtra("data", data);
                intent.putExtra("type", "g");
                mContext.startActivity(intent);
            }
        });
        holder.setOnClickListener(R.id.z, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(mContext, GrowthRecordsActivity.class);
                //				intent.putExtra("num",data.getNodeMac());
                //				intent.putExtra("content","soilTemperature3");
                //				intent.putExtra("value",String.valueOf(data.getSoilHumidity3()));
                //				intent.putExtra("text","3号采集湿度");
                intent.putExtra("data", data);
                intent.putExtra("type", "h");
                mContext.startActivity(intent);
            }
        });
        holder.setOnClickListener(R.id.k, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(mContext, GrowthRecordsActivity.class);
                //				intent.putExtra("num",data.getNodeMac());
                //				intent.putExtra("content","co2");
                //				intent.putExtra("value",String.valueOf(data.getCo2()));
                //				intent.putExtra("text","二氧化碳");
                intent.putExtra("data", data);
                intent.putExtra("type", "y");
                mContext.startActivity(intent);
            }
        });

        holder.setOnClickListener(R.id.m, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(mContext, OffOnActivity.class);
                intent.putExtra("nodeId", data.getId());
                intent.putExtra("nodeMac", data.getNodeMac());
                intent.putExtra("status", data.getOpenFlag());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.r_layout;
    }
}