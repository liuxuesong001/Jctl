package com.colud.jctl.adapters.fragment;

import android.content.Context;

import com.colud.jctl.JctlApplication;
import com.colud.jctl.bean.EarlyItme;
import com.jctl.colud.R;
import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.base.CommonBaseAdapter;

import java.util.List;

public class EarlyAdapter extends CommonBaseAdapter<EarlyItme> {


    public EarlyAdapter(Context context, List<EarlyItme> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(final ViewHolder holder, final EarlyItme data) {
        holder.setText(R.id.early_farmName, data.getFarmland_name());
        holder.setText(R.id.early_farmNum, data.getFarmland_num());
        holder.setText(R.id.early_nodeNum, data.getNode_num());
        holder.setText(R.id.early_Date, data.getDate());
        holder.setText(R.id.early_content, data.getMsg());

        if (data.getStatus() != null && data.getStatus().equals("-1")) {
            holder.setText(R.id.early_new_staue, "(已处理)");
            holder.setTextColor(R.id.early_new_staue, JctlApplication.getAppResources().getColor(R.color.color_3AB07D));
        } else {
            holder.setText(R.id.early_new_staue, "(未处理)");
            holder.setTextColor(R.id.early_new_staue, JctlApplication.getAppResources().getColor(R.color.red));
        }


        //        holder.setOnClickListener(R.id.early_delete, new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                dialogItem.setTvTitle("阀值还没有恢复正常,确定要删除？");
        //                dialog = new RateDialog(mContext, R.style.MyDialog,dialogItem);
        //                dialog.setMyClickListener(new RateDialog.onClickRateDialog() {
        //                    @Override
        //                    public void onClickLeft() {
        //                        dialog.dismiss();
        //                        dialog=null;
        //                    }
        //
        //                    @Override
        //                    public void onClickRight() {
        //                        if (holder.getPosition()==0)
        //                        {
        //                            remove(holder.getPosition());
        //
        //                        } else {
        //                            remove(holder.getPosition());
        //                        }
        //
        //                        //发送广播
        //                        Intent intent = new Intent(KeyConstants.MESSAGE_INFO);
        //                        intent.putExtra("----","less");
        //                        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        //
        //                        dialog.dismiss();
        //                        dialog=null;
        //
        //
        //                    }
        //                });
        //                dialog.show();
        //            }
        //        });
    }


    @Override
    protected int getItemLayoutId() {
        return R.layout.item_early_layout;
    }

}