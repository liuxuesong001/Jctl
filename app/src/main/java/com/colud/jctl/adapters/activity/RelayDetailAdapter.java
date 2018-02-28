package com.colud.jctl.adapters.activity;

import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.colud.jctl.adapters.listener.OnItemClickListener;
import com.jctl.colud.R;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by YOLANDA on 2016/7/22.
 */
public class RelayDetailAdapter extends SwipeMenuAdapter<RelayDetailAdapter.DefaultViewHolder> {


    private List<String> titles;

    private OnItemClickListener mOnItemClickListener;

    private boolean stateTouch;

    private static SparseBooleanArray isSelected;

    /**
     * 用SparseBooleanArray来代替map
     **/


    public static SparseBooleanArray getIsSelected() {
        return isSelected;
    }

    public static void setIsSelected(SparseBooleanArray isSelected) {
        RelayDetailAdapter.isSelected = isSelected;
    }

    public RelayDetailAdapter(List<String> titles) {
        this.titles = titles;
        isSelected = new SparseBooleanArray();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return titles == null ? 0 : titles.size();
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_relay_singel, parent, false);
    }

    @Override
    public DefaultViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        DefaultViewHolder viewHolder = new DefaultViewHolder(realContentView);
        viewHolder.mOnItemClickListener = mOnItemClickListener;
        return viewHolder;
    }

    /**
     * 绑定holder
     *
     * @param holder
     * @param position
     */
    private static boolean is;

    @Override
    public void onBindViewHolder(DefaultViewHolder holder, int position) {
        is = isSelected.get(position);
        holder.setData(titles.get(position), position);
    }

    public void setStateTouch(boolean stateTouch) {
        this.stateTouch = stateTouch;
    }

    class DefaultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.relay_left_content)
        TextView relayLeftContent;
        @BindView(R.id.relay_mid_content)
        TextView relayMidContent;
        @BindView(R.id.relay_right_img)
        ImageView relayRightImg;

        OnItemClickListener mOnItemClickListener;

        public DefaultViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void setData(String data, int position) {
            if (data != null) {
                this.relayLeftContent.setText(data);
                //				this.msginfoTitle.setText(data.getInfoTitle());
                //				this.msginfoContent.setText(data.getInfoContent());
                //				this.msginfoDate.setText(data.getInfoDate());
                //				this.msginfoCheckbox.setChecked(data.getInfoState());
                if (stateTouch) {
                    if (position == 1) {
                        this.relayRightImg.setVisibility(View.VISIBLE);
                    } else if (position == 4) {
                        this.relayRightImg.setVisibility(View.VISIBLE);
                    } else if (position == 8) {
                        this.relayRightImg.setVisibility(View.VISIBLE);
                    } else if (position == 9) {
                        this.relayRightImg.setVisibility(View.VISIBLE);
                    }
                } else {
                    this.relayRightImg.setVisibility(View.GONE);
                }
            }
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null && stateTouch) {
                mOnItemClickListener.onItemClick(getAdapterPosition());
            }

        }


    }

}
