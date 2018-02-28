package com.colud.jctl.adapters.activity;

import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.colud.jctl.adapters.listener.OnItemClickListener;
import com.jctl.colud.R;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;


/**
 * Created by YOLANDA on 2016/7/22.
 */
public class IntelPeriodiAdapter extends SwipeMenuAdapter<IntelPeriodiAdapter.DefaultViewHolder> {


    private List<String> titles;

    private OnItemClickListener mOnItemClickListener;

    private boolean stateTouch;

    private static SparseBooleanArray isSelected;/**用SparseBooleanArray来代替map**/

    /**
     * 全选回调接口
     */
    CheckedAllListener mListener;

    public void setCheckedAllListener(CheckedAllListener listener) {
        mListener = listener;
    }

    public static SparseBooleanArray getIsSelected() {
        return isSelected;
    }

    public static void setIsSelected(SparseBooleanArray isSelected) {
        IntelPeriodiAdapter.isSelected = isSelected;
    }

    public IntelPeriodiAdapter(List<String> titles) {
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
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repetition, parent, false);
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
        holder.setData(titles.get(position));
    }

    public void setStateTouch(boolean stateTouch) {
        this.stateTouch = stateTouch;
    }

    class DefaultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

        @BindView(R.id.rep_xingqi)
        TextView repXingqi;
        @BindView(R.id.rep_checkbox)
        CheckBox repCheckbox;

        OnItemClickListener mOnItemClickListener;

        public DefaultViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void setData(String data) {
            if (data != null) {
                this.repXingqi.setText(data);
                this.repCheckbox.setChecked(is);
            }
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null && stateTouch) {
                //				this.msginfoCheckbox.setChecked(true);
                mOnItemClickListener.onItemClickCheckBox(this.repCheckbox, getAdapterPosition());
            } else {
                mOnItemClickListener.onItemClick(getAdapterPosition());
            }

        }

        @OnCheckedChanged({R.id.rep_checkbox})
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


        }

    }

    /**
     * 当所有CheckBox全选时回调
     *
     * @author Administrator
     */
    public interface CheckedAllListener {

        void CheckAll(SparseBooleanArray checkall);

    }

}
