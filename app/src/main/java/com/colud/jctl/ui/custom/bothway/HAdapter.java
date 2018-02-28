package com.colud.jctl.ui.custom.bothway;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.colud.jctl.bean.NodeItem;
import com.jctl.colud.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jonney on 2016/9/26.
 */
public class HAdapter extends RecyclerView.Adapter<HAdapter.ViewHoder> {


    private Context context;
    private CustomRecyclerView recyclerView;
    private List<NodeItem.InfoBean> sList;
    private NodeItem.InfoBean info;
    private List<String> macList = new ArrayList<>();

    public HAdapter(Context context, List<NodeItem.InfoBean> sList) {
        this.context = context;
        this.sList = sList;
    }
    //    private OnItemClickListener mOnItemClickListener;
    //
    //    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
    //        this.mOnItemClickListener = onItemClickListener;
    //    }

    @Override
    public ViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHoder(LayoutInflater.from(context).inflate(R.layout.fragment_node_right, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHoder holder, int position) {
        //        if (sList!=null&&sList.size()>0){
        //            for (int i=0; i<sList.size();i++){
        //                info=sList.get(i);
        //            }
        //            if (position==0){
        //                holder.titleTv.setText(info.getNodeMac());
        //            }else if (position==2){
        //                holder.titleTv.setText(info.getId());
        //            }else {
        //                holder.titleTv.setText("暂无");
        //            }
        //            //            holder.titleTv.setText(position);
        //        }
        //		info=sList.get(position);
        //		for (int i=0; i<sList.size();i++){
        //			info=sList.get(i);
        //			macList.add(info.getNodeMac());
        //		}
        //
        //		for (int i=0; i<macList.size();i++){
        //			String info=macList.get(i);
        //			KLog.d(info);
        //		}
        holder.postion = position;
        holder.tvTitle.setText("row" + position);
    }

    @Override
    public int getItemCount() {
        return sList.size();
    }


    //    @Override
    //    public int getCount() {
    //        return 10;
    //    }
    //
    //    @Override
    //    public Object getItem(int i) {
    //        return null;
    //    }
    //
    //    @Override
    //    public long getItemId(int i) {
    //        return 0;
    //    }
    //
    //
    //
    //    @Override
    //    public View getView(int i, View view, ViewGroup viewGroup) {
    //        ViewHolder viewHoder=null;
    //        if (view==null){
    //            viewHoder=new ViewHolder();
    //            view= LayoutInflater.from(context).inflate(R.layout.h_item,viewGroup,false);
    //            viewHoder.textView=(TextView) view.findViewById(R.id.h_item_id);
    //            view.setTag(viewHoder);
    //        }else {
    //            viewHoder=(ViewHolder) view.getTag();
    //        }
    //        viewHoder.textView.setText("row"+i);
    //        return view;
    //    }


    class ViewHoder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title_id)
        TextView tvTitle;
        int postion;

        public ViewHoder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    //    static class ViewHolder extends RecyclerView.ViewHolder {
    //        TextView textView;
    //        int postion;
    //        //        OnItemClickListener mOnItemClickListener;
    //        public ViewHolder(View itemView) {
    //            super(itemView);
    //            //            itemView.setOnClickListener(this);
    //            textView=(TextView) itemView.findViewById(R.id.tv_title_id);
    //        }
    //
    //        //        @Override
    //        //        public void onClick(View v) {
    //        //            if (mOnItemClickListener != null) {
    //        //                mOnItemClickListener.onItemClick(getAdapterPosition());
    //        //            }
    //        //        }
    //    }
}
