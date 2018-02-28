package com.colud.jctl.adapters.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.colud.jctl.bean.CapaCityItem;
import com.colud.jctl.ui.activity.IntelControlActivity;
import com.jctl.colud.R;
import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.base.CommonBaseAdapter;

import java.util.List;

public class CapaCityAdapter extends CommonBaseAdapter<CapaCityItem.InfoBean> {


    public CapaCityAdapter(Context context, List<CapaCityItem.InfoBean> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(ViewHolder holder, final CapaCityItem.InfoBean data) {
        holder.setText(R.id.field_item_relaynum, data.getProperty());
        holder.setText(R.id.node_item_farmername, String.valueOf(data.getMax()));
        holder.setText(R.id.node_item_nh, String.valueOf(data.getMin()));
        holder.setText(R.id.node_item_powersupply, data.getCycle());

        holder.setOnClickListener(R.id.field_item_relaynum, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, IntelControlActivity.class);
                intent.putExtra("update", data);
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.include_field_manage;
    }
}