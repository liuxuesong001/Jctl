package com.colud.jctl.adapters.activity;

import android.content.Context;

import com.colud.jctl.bean.BazaarItem;
import com.jctl.colud.R;
import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.base.CommonBaseAdapter;

import java.util.List;

public class BazaarAdapter extends CommonBaseAdapter<BazaarItem.MktDynsBean> {


    public BazaarAdapter(Context context, List<BazaarItem.MktDynsBean> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(ViewHolder holder, BazaarItem.MktDynsBean data) {
        holder.setText(R.id.bazaar_name, data.getProductName());
        holder.setText(R.id.bazaar_price, data.getPrice());
        holder.setText(R.id.bazaar_address, data.getMarketName());
        holder.setText(R.id.bazaar_date, data.getReleaseDate());
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_bazzr_layout;
    }
}