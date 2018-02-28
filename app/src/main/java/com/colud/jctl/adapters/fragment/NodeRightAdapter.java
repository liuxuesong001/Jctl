package com.colud.jctl.adapters.fragment;

import android.content.Context;

import com.colud.jctl.bean.NodeItem;
import com.jctl.colud.R;
import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.base.CommonBaseAdapter;

import java.util.List;

/**
 * Created by Jcty on 2017/4/27.
 */

public class NodeRightAdapter extends CommonBaseAdapter<NodeItem.InfoBean> {

    private List<String> sList;
    private String name;


    public NodeRightAdapter(Context context, List<NodeItem.InfoBean> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(ViewHolder holder, NodeItem.InfoBean data) {

        //		if (holder.getAdapterPosition()==0){
        //			holder.setText(R.id.tv_title_id,"a");
        //		}else if (holder.getAdapterPosition()==1){
        //			holder.setText(R.id.tv_title_id,"b");
        //		}else if (holder.getAdapterPosition()==2){
        //			holder.setText(R.id.tv_title_id,"c");
        //		}else if (holder.getAdapterPosition()==3){
        //			holder.setText(R.id.tv_title_id,"d");
        //		}else {
        //			holder.setText(R.id.tv_title_id,"");
        //		}
        if (data != null) {
            holder.setText(R.id.tv_title_id, data.getNodeMac());
        }
    }


    @Override
    protected int getItemLayoutId() {
        return R.layout.fragment_node_right;
    }

    public void setlestList(List<String> list) {
        this.sList = list;
    }

}
