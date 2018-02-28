package com.colud.jctl.adapters.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.colud.jctl.JctlApplication;
import com.colud.jctl.bean.NodeManageItem;
import com.colud.jctl.ui.activity.NodeDetailsActivity;
import com.jctl.colud.R;
import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.base.CommonBaseAdapter;

import java.util.List;

public class NodeManageAdapter extends CommonBaseAdapter<NodeManageItem.InfoBean> {

    Intent in;
    private int colorPosion = 0;


    public NodeManageAdapter(Context context, List<NodeManageItem.InfoBean> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }


    @Override
    protected void convert(ViewHolder holder, final NodeManageItem.InfoBean data) {
        //		if (!TextUtils.isEmpty(data.getOnOffName())){
        //			holder.setText(R.id.node_item_nodeName,data.getOnOffName());
        //		}else {
        //			holder.setText(R.id.node_item_nodeName,data.getNodeNum());
        //		}
        holder.setText(R.id.node_item_nodeName, data.getNodeNum());
        if (!TextUtils.isEmpty(data.getFarmlandName())) {
            holder.setText(R.id.node_item_farmername, data.getFarmlandName());
        } else {
            holder.setVisibilityGONE(R.id.node_img_b);
        }
        holder.setText(R.id.node_item_nh, data.getUsedName());
        holder.setText(R.id.node_item_powersupply, data.getPower());


        if (colorPosion < JctlApplication.getAppResources().getIntArray(R.array.colorBj).length - 1) {
            holder.setBgColor(R.id.ll_adapter_bj, JctlApplication.getAppResources().getIntArray(R.array.colorBj)[colorPosion]);
            colorPosion++;
        } else {
            holder.setBgColor(R.id.ll_adapter_bj, JctlApplication.getAppResources().getIntArray(R.array.colorBj)[colorPosion]);
            colorPosion = 0;
        }

        holder.setOnClickListener(R.id.node_ll_a, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in = new Intent(mContext, NodeDetailsActivity.class);
                in.putExtra("id", data.getId());
                in.putExtra("nodeNum", data.getNodeAlise());
                mContext.startActivity(in);
            }
        });
        //		holder.setOnClickListener(R.id.field_item_farmername, new View.OnClickListener() {
        //			@Override
        //			public void onClick(View v) {
        //				in=new Intent(mContext, FarmDetailActivity.class);
        //				in.putExtra("id",data.getId());
        //				in.putExtra("farmName",data.getFarmerName());
        //				mContext.startActivity(in);
        //			}
        //		});
        //		holder.setOnClickListener(R.id.field_item_nodenum, new View.OnClickListener() {
        //			@Override
        //			public void onClick(View v) {
        //				in=new Intent(mContext, NodeManageActivity.class);
        //				in.putExtra("node",String.valueOf(data.getNodeNum()));
        //				mContext.startActivity(in);
        //			}
        //		});

    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.include_node_manage;
    }
}