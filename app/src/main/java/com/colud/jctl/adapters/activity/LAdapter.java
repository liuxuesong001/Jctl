package com.colud.jctl.adapters.activity;

import android.content.Context;

import com.jctl.colud.R;
import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.base.CommonBaseAdapter;

import java.util.List;

public class LAdapter extends CommonBaseAdapter<String> {


    public LAdapter(Context context, List<String> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(ViewHolder holder, String data) {
        holder.setText(R.id.tv_l, data);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.l_layout;
    }
}