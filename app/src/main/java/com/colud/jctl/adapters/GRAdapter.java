package com.colud.jctl.adapters;

import android.content.Context;

import com.colud.jctl.bean.GRItem;
import com.jctl.colud.R;
import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.base.CommonBaseAdapter;

import java.util.List;

public class GRAdapter extends CommonBaseAdapter<GRItem> {


    public GRAdapter(Context context, List<GRItem> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(ViewHolder holder, GRItem data) {
        if (data != null) {
            //			holder.setText(R.id.item_date,data.getDate());
            //			holder.setText(R.id.item_temp,data.getTemp());
            //			holder.setText(R.id.item_quarter,data.getQuarter());
        }
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_gr_layout;
    }
}