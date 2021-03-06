package com.colud.jctl.adapters.fragment;

import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.colud.jctl.adapters.listener.OnItemClickListener;
import com.jctl.colud.R;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jcty on 2017/4/5.
 */

public class NodeDetailsManageFragmentAdapter extends SwipeMenuAdapter<NodeDetailsManageFragmentAdapter.DefaultViewHolder> {


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
        NodeDetailsManageFragmentAdapter.isSelected = isSelected;
    }

    public NodeDetailsManageFragmentAdapter(List<String> titles) {
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
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_node_singel, parent, false);
    }

    @Override
    public NodeDetailsManageFragmentAdapter.DefaultViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        NodeDetailsManageFragmentAdapter.DefaultViewHolder viewHolder = new NodeDetailsManageFragmentAdapter.DefaultViewHolder(realContentView);
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
    public void onBindViewHolder(NodeDetailsManageFragmentAdapter.DefaultViewHolder holder, int position) {
        is = isSelected.get(position);
        holder.setData(titles.get(position), position);
    }

    public void setStateTouch(boolean stateTouch) {
        this.stateTouch = stateTouch;
    }

    class DefaultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.node_left_content)
        TextView nodeLeftContent;
        @BindView(R.id.node_mid_content)
        TextView nodeMidContent;

        OnItemClickListener mOnItemClickListener;

        public DefaultViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void setData(String data, int position) {
            if (data != null) {
                this.nodeLeftContent.setText(data);
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
