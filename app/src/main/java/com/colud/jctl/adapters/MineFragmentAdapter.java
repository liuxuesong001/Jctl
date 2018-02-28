package com.colud.jctl.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * Created by Jcty on 2017/3/2.
 */

public class MineFragmentAdapter extends RecyclerView.Adapter<MineFragmentAdapter.MyViewHolder> {

    private Context mContext;
    private List<String> mDataset;
    private LayoutInflater mInflater;


    //提供一个合适的构造方法
    public MineFragmentAdapter(Context context, List<String> dataset) {
        this.mContext = context;
        this.mDataset = dataset;
        mInflater = LayoutInflater.from(context);
    }

    /**
     * 将布局转换为View并传递给自定义的MyViewHolder
     *
     * @param viewGroup
     * @param viewType
     * @return
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        //		View view = mInflater.inflate(R.layout.item_mine_singel, viewGroup, false);
        //		MyViewHolder viewHolder = new MyViewHolder(view);
        //		return viewHolder;
        return null;
    }

    /**
     * 建立起MyViewHolder中视图与数据的关联
     *
     * @param viewHolder
     * @param position
     */
    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        //		viewHolder.mineGl.setText(mDataset.get(position));
    }

    /**
     * 获取item的数目
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    //自定义的ViewHoder，持有item的所有控件
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        //		@BindView(R.id.mine_iv_gl)
        //		ImageView mineIvGl;
        //		@BindView(R.id.mine_gl)
        //		TextView mineGl;
        //		@BindView(R.id.mine_right_gl)
        //		ImageView mineRightGl;

        public MyViewHolder(View view) {
            super(view);
        }
    }
}
