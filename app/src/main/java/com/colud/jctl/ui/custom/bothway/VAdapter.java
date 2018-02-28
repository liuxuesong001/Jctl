package com.colud.jctl.ui.custom.bothway;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.colud.jctl.adapters.fragment.NodeRightAdapter;
import com.colud.jctl.adapters.listener.OnItemClickListener;
import com.colud.jctl.bean.NodeItem;
import com.jctl.colud.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jonney on 2016/9/26.
 */
public class VAdapter extends RecyclerView.Adapter<VAdapter.ViewHoder> {
    Context context;
    CustomRecyclerView crv;
    private List<String> vList;
    private HAdapter hAdapter;
    private NodeRightAdapter rightAdapter;
    private List<NodeItem.InfoBean> infoList;

    public VAdapter(CustomRecyclerView recyclerView, List<String> list, List<NodeItem.InfoBean> infolist) {
        this.context = recyclerView.getContext();
        this.vList = list;
        this.crv = recyclerView;
        this.infoList = infolist;
    }

    @Override
    public ViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHoder(LayoutInflater.from(context).inflate(R.layout.fragment_node_left, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHoder holder, int position) {
        String left = vList.get(position);
        holder.titleTv.setText(left);
    }

    @Override
    public void onViewRecycled(ViewHoder holder) {
        super.onViewRecycled(holder);
        //		KLog.w("onViewRecycled","scrollY="+ getScrollY(holder.listView)+",position"+holder.postion);

    }

    @Override
    public int getItemCount() {
        return vList.size();
    }

    class ViewHoder extends RecyclerView.ViewHolder {
        @BindView(R.id.h_list)
        RecyclerView leftRv;
        @BindView(R.id.tv_left_id)
        TextView titleTv;

        public ViewHoder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            leftRv.setLayoutManager(linearLayoutManager);
            //			rightAdapter=new NodeRightAdapter(context,infoList,true);
            //			rightAdapter.setlestList(vList);
            hAdapter = new HAdapter(context, infoList);
            leftRv.setAdapter(hAdapter);
            //			leftRv.setAdapter(rightAdapter);
            leftRv.setAdapter(hAdapter);
            crv.addRecyclerView(leftRv);

            //						new HAdapter(context).setOnItemClickListener(onItemClickListener);
        }
    }


    private OnItemClickListener onItemClickListener = new OnItemClickListener() {

        @Override
        public void onItemClick(int position) {
            Toast.makeText(context, "点击的是：" + position, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onItemClickItemView(View view, int position) {

        }

        @Override
        public void onItemClickCheckBox(CheckBox checkBox, int position) {

        }
    };
}
