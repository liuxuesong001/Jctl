package com.colud.jctl.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.colud.jctl.adapters.listener.OnItemClickListener;
import com.colud.jctl.bean.EarlyItme;
import com.colud.jctl.bean.RateDialogItem;
import com.colud.jctl.ui.custom.dialog.RateDialog;
import com.colud.jctl.utils.ToastUtils;
import com.jctl.colud.R;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;


/**
 * Created by YOLANDA on 2016/7/22.
 */
public class EarlyMenuAdapter extends SwipeMenuAdapter<EarlyMenuAdapter.DefaultViewHolder> {

    private List<EarlyItme> titles;
    private Context mContext;

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
        EarlyMenuAdapter.isSelected = isSelected;
    }

    public EarlyMenuAdapter(Context context, List<EarlyItme> titles) {
        this.mContext = context;
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
        //		if (viewType == TYPE_ITEM) {
        //			View view = LayoutInflater.from(context).inflate(R.layout.early_item_layout, parent,
        //					false);
        //			if (view != null) {
        //				mISlideHelper.add(new EarlyFragmentAdapter.ItemViewHolder(view));
        //			}
        //			return new EarlyFragmentAdapter.ItemViewHolder(view);
        //		} else if (viewType == TYPE_FOOTER) {
        //			View view = LayoutInflater.from(context).inflate(R.layout.item_foot, parent,
        //					false);
        //			return new EarlyFragmentAdapter.FootViewHolder(view);
        //		}
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_early_layout, parent, false);
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
        @BindView(R.id.early_farmName)
        TextView earlyFarmName;
        @BindView(R.id.early_delete)
        ImageView earlyDelete;
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
        RelativeLayout earlyItemLayou;
        @BindView(R.id.early_checkbox)
        CheckBox earlyCheckbox;

        OnItemClickListener mOnItemClickListener;

        public DefaultViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void setData(EarlyItme data) {
            //			if (data != null) {
            //				earlyFarmName.setText(data.getFarmName());
            //				earlyFarmNum.setText(data.getFarmNum());
            //				earlyFroplandNum.setText(data.getFroplandNum());
            //				earlyDate.setText(data.getEarlyDate());
            //				earlyContent.setText(data.getEarlyContent());
            //				if (!stateTouch) {
            //					this.earlyCheckbox.setVisibility(View.GONE);
            //					this.earlyDelete.setVisibility(View.VISIBLE);
            //				} else {
            //					this.earlyCheckbox.setVisibility(View.VISIBLE);
            //					this.earlyDelete.setVisibility(View.GONE);
            //					// 根据isSelected来设置checkbox的选中状况
            //				}
            //				this.earlyCheckbox.setChecked(is);
            //			}
        }

        RateDialogItem item = new RateDialogItem();

        @OnClick({R.id.early_delete})
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.early_delete:
                    item.setTvTitle("确定删除该消息吗？");
                    final RateDialog dialog = new RateDialog(mContext, R.style.MyDialog, item);
                    dialog.setMyClickListener(new RateDialog.onClickRateDialog() {
                        @Override
                        public void onClickLeft() {
                            dialog.dismiss();
                        }

                        @Override
                        public void onClickRight() {
                            ToastUtils.showShort("已删除");
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                    item.setTvTitle("");
                    break;
            }
            if (mOnItemClickListener != null && stateTouch) {
                //				this.msginfoCheckbox.setChecked(true);
                mOnItemClickListener.onItemClickCheckBox(this.earlyCheckbox, getAdapterPosition());
            } else {
                mOnItemClickListener.onItemClick(getAdapterPosition());
            }

        }

        @OnCheckedChanged({R.id.early_checkbox})
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
