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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;

import static com.baidu.location.d.a.i;


/**
 * Created by YOLANDA on 2016/7/22.
 */
public class RepetitionAdapter extends SwipeMenuAdapter<RepetitionAdapter.DefaultViewHolder> {


    private List<String> titles;

    private OnItemClickListener mOnItemClickListener;

    private boolean stateTouch;

    /**
     * 用SparseBooleanArray来代替map
     **/
    private static SparseBooleanArray isSelected;


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
        RepetitionAdapter.isSelected = isSelected;
    }

    //根据位置判断条目是否选中
    private boolean isItemChecked(int position) {
        return isSelected.get(position);
    }

    public RepetitionAdapter(List<String> titles) {
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

    private void setItemChecked(int position, boolean isChecked) {
        isSelected.put(position, isChecked);
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
        //		is = isSelected.get(position);
        //		holder.setData(titles.get(position));
        //设置条目状态
        //		((ListItemViewHolder) holder).mainTitle.setText(mList.get(i));
        //		((ListItemViewHolder) holder).checkBox.setChecked(isItemChecked(i));
        holder.repXingqi.setText(titles.get(position));
        holder.repCheckbox.setChecked(isItemChecked(position));

        //条目view的监听
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isItemChecked(i)) {
                    setItemChecked(i, false);
                } else {
                    setItemChecked(i, true);
                }
                notifyItemChanged(i);
            }
        });
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
                //				this.msginfoTitle.setText(data.getInfoTitle());
                //				this.msginfoContent.setText(data.getInfoContent());
                //				this.msginfoDate.setText(data.getInfoDate());
                //				this.msginfoCheckbox.setChecked(data.getInfoState());
                //				if (!stateTouch) {
                //					this.msginfoCheckbox.setVisibility(View.GONE);
                //					this.msginfoIcon.setVisibility(View.VISIBLE);
                //				} else {
                //					this.msginfoCheckbox.setVisibility(View.VISIBLE);
                //					this.msginfoIcon.setVisibility(View.GONE);
                //					// 根据isSelected来设置checkbox的选中状况
                //				}
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

    //获得选中条目的结果
    public ArrayList<String> getSelectedItem() {
        ArrayList<String> selectList = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            if (isItemChecked(i)) {
                selectList.add(titles.get(i));
            }
        }
        return selectList;
    }
}
