package com.colud.jctl.adapters.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.colud.jctl.adapters.listener.OnItemClickListener;
import com.colud.jctl.bean.FarmManageItem;
import com.jctl.colud.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NodeAdapter extends Adapter<ViewHolder> {

    private static final int TYPE_ITEM = 0;//说明是不带有header和footer的
    private static final int TYPE_FOOTER = 1;//说明是带有Footer的


    private Context context;
    private List<FarmManageItem> data;


    public NodeAdapter(Context context, List<FarmManageItem> data) {
        this.context = context;
        this.data = data;
        notifyDataSetChanged();
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
            View view = LayoutInflater.from(context).inflate(R.layout.include_farm_manage, parent,
                    false);
            if (view != null) {
            }
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
            ((ItemViewHolder) holder).setData(data.get(position));
            //			final FarmManageItem item = data.get(position);
            //			if (item != null) {
            //				((ItemViewHolder) holder).farmItemName.setText(item.getFarm_name());
            //				((ItemViewHolder) holder).farmItemAddress.setText(item.getFarm_address());
            //				((ItemViewHolder) holder).farmItemNum.setText(item.getFarm_num());
            //				((ItemViewHolder) holder).farmItemCrop.setText(item.getFarm_crop());
            //			}
            //			((ItemViewHolder) holder).farmItemAddress.setOnClickListener(new View.OnClickListener() {
            //				@Override
            //				public void onClick(View v) {
            //					Intent in=new Intent(context, FarmAddressMapActivity.class);
            //					in.putExtra("farmName",item.getFarm_name());
            //					context.startActivity(in);
            //				}
            //			});
        }
    }


    class ItemViewHolder extends ViewHolder implements View.OnClickListener {
        @BindView(R.id.farm_item_name)
        TextView farmItemName;
        @BindView(R.id.farm_item_address)
        TextView farmItemAddress;
        @BindView(R.id.farm_item_num)
        TextView farmItemNum;
        @BindView(R.id.farm_item_crop)
        TextView farmItemCrop;

        public ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            //			view.setOnClickListener(this);
        }

        @OnClick({R.id.farm_item_address})
        @Override
        public void onClick(View v) {
            //			if (onItemClickListener != null) {
            //				onItemClickListener.onItemClick(getAdapterPosition());
            //			}
        }

        Intent in;

        public void setData(final FarmManageItem data) {
            //			if (data != null) {
            //				this.farmItemName.setText(data.getFarm_name());
            //				this.farmItemAddress.setText(data.getFarm_address());
            //				this.farmItemNum.setText(data.getFarm_num());
            //				this.farmItemCrop.setText(data.getFarm_crop());
            //			}
            //			this.farmItemName.setOnClickListener(new View.OnClickListener() {
            //				@Override
            //				public void onClick(View v) {
            //					in=new Intent(context, FarmDetailActivity.class);
            //					in.putExtra("farmName",data.getFarm_name());
            //					context.startActivity(in);
            //				}
            //			});
            //			this.farmItemAddress.setOnClickListener(new View.OnClickListener() {
            //				@Override
            //				public void onClick(View v) {
            //					in=new Intent(context, FarmAddressMapActivity.class);
            //					in.putExtra("farmName",data.getFarm_name());
            //					context.startActivity(in);
            //				}
            //			});
            //			this.farmItemNum.setOnClickListener(new View.OnClickListener() {
            //				@Override
            //				public void onClick(View v) {
            //					in=new Intent(context, FieldManageActivity.class);
            //					in.putExtra("farmName",data.getFarm_name());
            //					//					in.putExtra("fieldNum",data.getFarm_num());
            //					context.startActivity(in);
            //				}
            //			});
            //			this.farmItemCrop.setOnClickListener(new View.OnClickListener() {
            //				@Override
            //				public void onClick(View v) {
            //					in=new Intent(context, CropActivity.class);
            //					in.putExtra("farmCrop",data.getFarm_crop());
            //					context.startActivity(in);
            //				}
            //			});
        }
    }

    static class FootViewHolder extends ViewHolder {


        public FootViewHolder(View view) {
            super(view);

        }
    }
}