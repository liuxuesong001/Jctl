package com.colud.jctl.adapters.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.colud.jctl.JctlApplication;
import com.colud.jctl.bean.FarmManageBean;
import com.colud.jctl.ui.activity.CropActivity;
import com.colud.jctl.ui.activity.FarmAddressMapActivity;
import com.colud.jctl.ui.activity.FarmDetailActivity;
import com.colud.jctl.ui.activity.FieldManageActivity;
import com.jctl.colud.R;
import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.base.CommonBaseAdapter;

import java.util.List;

public class FarmManageAdapter extends CommonBaseAdapter<FarmManageBean.InfoBean> {

    private Intent in;

    private int colorPosion = 0;


    public FarmManageAdapter(Context context, List<FarmManageBean.InfoBean> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }


    @Override
    protected void convert(ViewHolder holder, final FarmManageBean.InfoBean data) {
        //		if (data.getName()!=null){
        //			holder.setTextColor(R.id.farm_item_name, JctlApplication.getAppResources().getColor(R.color.blue));
        //		}
        holder.setText(R.id.farm_item_name, data.getName());
        holder.setText(R.id.farm_item_address, data.getAddr());
        holder.setText(R.id.farm_item_num, String.valueOf(data.getFarmlandNumber()));
        holder.setText(R.id.farm_item_crop, data.getPlantVariety());


        //随机颜色方法
        //		int customizedColor = colorBj[random.nextInt(colorBj.length)];
        //		holder.setBgColor(R.id.ll_adapter_bj,customizedColor);

        //		for (int i=0;i<colorBj.length;i++)
        //		{
        //			KLog.a(i);
        //			holder.setBgColor(R.id.ll_adapter_bj,colorBj[i]);
        //		}


        if (colorPosion < JctlApplication.getAppResources().getIntArray(R.array.colorBj).length - 1) {
            holder.setBgColor(R.id.ll_adapter_bj, JctlApplication.getAppResources().getIntArray(R.array.colorBj)[colorPosion]);
            colorPosion++;
        } else {
            holder.setBgColor(R.id.ll_adapter_bj, JctlApplication.getAppResources().getIntArray(R.array.colorBj)[colorPosion]);
            colorPosion = 0;
        }


        holder.setOnClickListener(R.id.farm_ll_a, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in = new Intent(mContext, FarmDetailActivity.class);
                in.putExtra("id", data.getId());
                in.putExtra("farmName", data.getName());
                mContext.startActivity(in);
            }
        });
        holder.setOnClickListener(R.id.farm_ll_b, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in = new Intent(mContext, FarmAddressMapActivity.class);
                in.putExtra("info", data);
                mContext.startActivity(in);
            }
        });
        holder.setOnClickListener(R.id.farm_ll_c, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in = new Intent(mContext, FieldManageActivity.class);
                //				in.putExtra("farmName",data.getFarmlandNumber());
                in.putExtra("farmName", data.getName());
                in.putExtra("id", data.getId());
                mContext.startActivity(in);
                //				if (data.getFarmlandNumber()>0){
                //					in=new Intent(mContext, FieldManageActivity.class);
                //					//				in.putExtra("farmName",data.getFarmlandNumber());
                //					in.putExtra("farmName",data.getName());
                //					in.putExtra("id",data.getId());
                //					mContext.startActivity(in);
                //				}else {
                //					ToastUtils.showLong("暂无农田信息");
                //				}

            }
        });
        holder.setOnClickListener(R.id.farm_ll_d, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in = new Intent(mContext, CropActivity.class);
                in.putExtra("farmCrop", data.getPlantVariety());
                in.putExtra("id", data.getId());
                mContext.startActivity(in);
            }
        });
    }

    @Override
    protected int getItemLayoutId() {

        return R.layout.include_farm_manage;
    }

}