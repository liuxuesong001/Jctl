package com.colud.jctl.adapters.activity;

import android.content.Context;

import com.colud.jctl.bean.NewInfoItem;
import com.jctl.colud.R;
import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.base.CommonBaseAdapter;

import java.util.List;

/**
 * Created by Jcty on 2017/3/16.
 */

public class MoreNewDynamicAdapter extends CommonBaseAdapter<NewInfoItem.InfoBean> {

    public MoreNewDynamicAdapter(Context context, List<NewInfoItem.InfoBean> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(ViewHolder holder, NewInfoItem.InfoBean data) {
        if (data != null) {
            //			holder.setBgRes(R.id.new_fragment_img,data.getImg());
            holder.setText(R.id.new_fragment_title, data.getTitle());
            holder.setText(R.id.new_fragment_date, data.getDataTime());
            holder.setText(R.id.new_fragment_content, data.getContent());
        }

    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_newdynamic;
    }
}
