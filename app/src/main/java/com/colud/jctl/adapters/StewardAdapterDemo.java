package com.colud.jctl.adapters;

import android.content.Context;

import com.colud.jctl.bean.FarmersBean;
import com.jctl.colud.R;
import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.base.CommonBaseAdapter;

import java.util.List;

public class StewardAdapterDemo extends CommonBaseAdapter<FarmersBean.DataBean> {

    private boolean stateTouch;


    public StewardAdapterDemo(Context context, List<FarmersBean.DataBean> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    public void setStateTouch(boolean stateTouch) {
        this.stateTouch = stateTouch;
    }

    @Override
    protected void convert(ViewHolder holder, FarmersBean.DataBean data) {
        holder.setText(R.id.farm_item_name, data.getName());
        holder.setText(R.id.farm_item_address, String.valueOf(data.getFarmlands()));
        holder.setText(R.id.farm_item_num, String.valueOf(data.getNodes()));
        holder.setText(R.id.farm_item_crop, data.getPhone());
        if (stateTouch) {
            holder.setVisibilityVISIBLE(R.id.steward_checkbox);
        } else {
            holder.setVisibilityGONE(R.id.steward_checkbox);
        }

    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.include_farm_manage;
    }
}