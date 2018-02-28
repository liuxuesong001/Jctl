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
import com.colud.jctl.bean.MsgInformItem;
import com.jctl.colud.R;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by YOLANDA on 2016/7/22.
 */
public class StewardAdapter extends SwipeMenuAdapter<StewardAdapter.DefaultViewHolder> {


    private List<MsgInformItem> titles;

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
        StewardAdapter.isSelected = isSelected;
    }

    public StewardAdapter(List<MsgInformItem> titles) {
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
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_msg_inform_layout, parent, false);
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

        @BindView(R.id.msginfo_icon)
        CircleImageView msginfoIcon;
        @BindView(R.id.msginfo_title)
        TextView msginfoTitle;
        @BindView(R.id.msginfo_content)
        TextView msginfoContent;
        @BindView(R.id.msginfo_date)
        TextView msginfoDate;
        @BindView(R.id.msginfo_checkbox)
        CheckBox msginfoCheckbox;
        OnItemClickListener mOnItemClickListener;

        public DefaultViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void setData(MsgInformItem data) {
            if (data != null) {
                this.msginfoTitle.setText(data.getInfoTitle());
                this.msginfoContent.setText(data.getInfoContent());
                this.msginfoDate.setText(data.getInfoDate());
                this.msginfoCheckbox.setChecked(data.getInfoState());
                if (!stateTouch) {
                    this.msginfoCheckbox.setVisibility(View.GONE);
                    this.msginfoIcon.setVisibility(View.VISIBLE);
                } else {
                    this.msginfoCheckbox.setVisibility(View.VISIBLE);
                    this.msginfoIcon.setVisibility(View.GONE);
                    // 根据isSelected来设置checkbox的选中状况
                }
                this.msginfoCheckbox.setChecked(is);
            }
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null && stateTouch) {
                //				this.msginfoCheckbox.setChecked(true);
                mOnItemClickListener.onItemClickCheckBox(this.msginfoCheckbox, getAdapterPosition());
            } else {
                mOnItemClickListener.onItemClick(getAdapterPosition());
            }

        }

        @OnCheckedChanged({R.id.msginfo_checkbox})
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
