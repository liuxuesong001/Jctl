package com.colud.jctl.adapters;

import android.content.Context;

import com.colud.jctl.bean.CycleUpdateBean;
import com.jctl.colud.R;
import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.base.CommonBaseAdapter;

import java.util.List;

public class CollectionFragmentAdapter extends CommonBaseAdapter<CycleUpdateBean> {


    public CollectionFragmentAdapter(Context context, List<CycleUpdateBean> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(ViewHolder holder, CycleUpdateBean data) {
        //		holder.setText(R.id.collect_item_title,data.getCollTitle());
        //		holder.setText(R.id.collect_item_content,data.getCollContent());
        //		holder.setText(R.id.collect_item_date,data.getCollDate());
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_collectionner_layout;
    }
}