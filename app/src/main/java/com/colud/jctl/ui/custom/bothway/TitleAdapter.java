package com.colud.jctl.ui.custom.bothway;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.colud.jctl.bean.NodeItem;
import com.jctl.colud.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jonney on 2016/9/26.
 */
public class TitleAdapter extends RecyclerView.Adapter<TitleAdapter.ViewHoderTitle> {

    private Context context;
    private List<NodeItem.InfoBean> sList;


    public TitleAdapter(Context context, List<NodeItem.InfoBean> list) {
        this.context = context;
        this.sList = list;
    }

    @Override
    public ViewHoderTitle onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHoderTitle(LayoutInflater.from(context).inflate(R.layout.fragment_node_right, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHoderTitle holder, int position) {
        holder.tvTitle.setText(position + 1 + "");
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

    static class ViewHoderTitle extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title_id)
        TextView tvTitle;
        int postion;

        public ViewHoderTitle(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}