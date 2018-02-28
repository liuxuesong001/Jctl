package com.colud.jctl.adapters.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.colud.jctl.JctlApplication;
import com.colud.jctl.bean.CameraBean;
import com.colud.jctl.ui.activity.PlayActivity;
import com.jctl.colud.R;

import java.util.ArrayList;
import java.util.List;

import static com.colud.jctl.JctlApplication.context;
import static com.colud.jctl.api.ApiConstants.CAMERA_URL;

public class CameraAdapter extends ArrayAdapter<CameraBean.EasyDarwinBean.BodyBean.ChannelsBean> {



    private Context mContext;
    private int layoutResourceId;
    private List<CameraBean.EasyDarwinBean.BodyBean.ChannelsBean> mGridData = new ArrayList<>();

    public CameraAdapter(Context context, int resource, ArrayList<CameraBean.EasyDarwinBean.BodyBean.ChannelsBean> list) {
        super(context, resource, list);
        this.mContext = context;
        this.layoutResourceId = resource;
        this.mGridData = list;
    }

    @Nullable
    @Override
    public CameraBean.EasyDarwinBean.BodyBean.ChannelsBean getItem(int position) {
        return mGridData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {

        return mGridData.size();
    }

    /**
     * 设置数据
     *
     * @param mGridData
     */
    public void setGridData(ArrayList<CameraBean.EasyDarwinBean.BodyBean.ChannelsBean> mGridData) {
        this.mGridData = mGridData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.tv_name = (TextView) convertView.findViewById(R.id.camera_name);
            holder.tv_on = (TextView) convertView.findViewById(R.id.camera_online);
            holder.img = (ImageView) convertView.findViewById(R.id.camera_img);
            holder.ll = (LinearLayout) convertView.findViewById(R.id.camera_ll);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final CameraBean.EasyDarwinBean.BodyBean.ChannelsBean item = mGridData.get(position);
        holder.tv_name.setText(item.getName());
        if (item.getOnline() == 0) {
            holder.tv_on.setText("离线");
        } else {
            holder.tv_on.setText("在线");
            holder.tv_on.setTextColor(JctlApplication.getAppResources().getColor(R.color.color_3AB07D));
        }
        Glide
                .with(context)
                .load(CAMERA_URL + item.getSnapURL())
                .into(holder.img);

        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(mContext, PlayActivity.class);
                in.putExtra("channel", String.valueOf(item.getChannel()));
                in.putExtra("name", item.getName());
                mContext.startActivity(in);
            }
        });


        return convertView;

    }

    private class ViewHolder {
        TextView tv_name;
        ImageView img;
        TextView tv_on;
        LinearLayout ll;
    }
}