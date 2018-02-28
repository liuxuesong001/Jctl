package com.colud.jctl.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jctl.colud.R;

import java.util.List;

public class RecyclerViewAdapter extends Adapter<ViewHolder> {

    private static final int TYPE_ITEM = 0;//说明是不带有header和footer的
    private static final int TYPE_FOOTER = 1;//说明是带有Footer的
    public static final int TYPE_HEADER = 2;  //说明是带有Header的
    private Context context;
    private List data;


    public RecyclerViewAdapter(Context context, List data) {
        this.context = context;
        this.data = data;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return data.size() == 0 ? 0 : data.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //        if(viewType==TYPE_HEADER){
        //            View view = LayoutInflater.from(context).inflate(R.layout.activity_header,parent,false);
        //            return new HeaderViewHolder(view);
        //        }else
        if (viewType == TYPE_ITEM) {
            //            View view = LayoutInflater.from(context).inflate(R.layout.item_base, parent,
            //                    false);
            //            return new ItemViewHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_foot, parent,
                    false);
            return new FootViewHolder(view);
        }
        return null;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            //holder.tv.setText(data.get(position));
            if (onItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = holder.getLayoutPosition();
                        onItemClickListener.onItemClick(holder.itemView, position);
                    }
                });

                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int position = holder.getLayoutPosition();
                        onItemClickListener.onItemLongClick(holder.itemView, position);
                        return false;
                    }
                });
            }
        }
    }


    static class ItemViewHolder extends ViewHolder {

        TextView tv;

        public ItemViewHolder(View view) {
            super(view);
            //            tv = (TextView) view.findViewById(R.id.tv_date);
        }
    }

    static class HeaderViewHolder extends ViewHolder {

        public HeaderViewHolder(View view) {
            super(view);
        }
    }

    static class FootViewHolder extends ViewHolder {

        //        Banner banner;

        public FootViewHolder(View view) {
            super(view);
            //            banner=(Banner)view.findViewById(R.id.banner);
            //            banner.setImages(JctlApplication.images)
            //                    .setImageLoader(new GlideImageLoader())
            //                    .setOnBannerListener(this).start();

        }
    }
}