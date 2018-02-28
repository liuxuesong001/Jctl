package com.colud.jctl.adapters;

import android.content.Context;

import com.jctl.colud.R;
import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.base.CommonBaseAdapter;

import java.util.List;

public class PersonageAdapter extends CommonBaseAdapter<String> {


    public PersonageAdapter(Context context, List<String> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }


    @Override
    protected void convert(ViewHolder holder, String data) {
        if (data != null) {
            holder.setText(R.id.personage_headline, data);
        }
    }


    @Override
    protected int getItemLayoutId() {
        return R.layout.item_personage_layout;
    }


}