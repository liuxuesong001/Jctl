package com.colud.jctl.adapters.lvAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jctl.colud.R;

import java.util.List;


/**
 * Created by Lunger on 7/13 0013 16:05
 */
public class LvLeftAdapter extends BaseAdapter {
    private Context context;
    private List<String> Llist;

    public LvLeftAdapter(Context context, List<String> list) {
        this.context = context;
        this.Llist = list;
    }

    @Override
    public int getCount() {
        return Llist.size();
    }

    @Override
    public String getItem(int position) {
        return Llist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_node_left, null);
            //            holder.tv_goodname = (TextView) convertView.findViewById(R.id.left_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String left = Llist.get(position);
        holder.tv_goodname.setText(left);
        return convertView;
    }

    class ViewHolder {
        TextView tv_goodname;
    }
}