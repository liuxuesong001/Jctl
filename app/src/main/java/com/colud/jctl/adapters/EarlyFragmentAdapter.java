package com.colud.jctl.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.colud.jctl.bean.EarlyItme;
import com.jctl.colud.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 弃用
 */

public class EarlyFragmentAdapter extends Adapter<ViewHolder> {


    private static final int TYPE_ITEM = 0;//说明是不带有header和footer的
    private static final int TYPE_FOOTER = 1;//说明是带有Footer的


    private Context context;
    private List<EarlyItme> data;


    public EarlyFragmentAdapter(Context context, List<EarlyItme> data) {
        this.context = context;
        this.data = data;
        notifyDataSetChanged();
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
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_early_layout, parent,
                    false);
            return new ItemViewHolder(view);
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
            EarlyItme item = data.get(position);
            if (item != null) {
                //				((ItemViewHolder) holder).earlyFarmName.setText(data.get(position).getFarmName());
                //				((ItemViewHolder) holder).earlyFarmNum.setText(data.get(position).getFarmNum());
                //				((ItemViewHolder) holder).earlyFroplandNum.setText(data.get(position).getFroplandNum());
                //				((ItemViewHolder) holder).earlyDate.setText(data.get(position).getEarlyDate());
                //				((ItemViewHolder) holder).earlyContent.setText(data.get(position).getEarlyContent());
            }
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


    static class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.early_farmName)
        TextView earlyFarmName;
        @BindView(R.id.early_farmNum)
        TextView earlyFarmNum;
        @BindView(R.id.early_froplandNum)
        TextView earlyFroplandNum;
        @BindView(R.id.early_nodeNum)
        TextView earlyNodeNum;
        @BindView(R.id.early_Date)
        TextView earlyDate;
        @BindView(R.id.early_content)
        TextView earlyContent;
        @BindView(R.id.early_item_layou)
        RelativeLayout earlyLayout;
        @BindView(R.id.early_delete)
        ImageView earlyDelele;


        public ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }


    }


    static class FootViewHolder extends ViewHolder {


        public FootViewHolder(View view) {
            super(view);

        }
    }

}