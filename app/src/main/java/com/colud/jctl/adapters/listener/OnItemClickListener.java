package com.colud.jctl.adapters.listener;

import android.view.View;
import android.widget.CheckBox;

/**
 * Created by Jcty on 2017/3/2.
 */

public interface OnItemClickListener {

    void onItemClick(int position);

    void onItemClickItemView(View view, int position);

    void onItemClickCheckBox(CheckBox checkBox, int position);

    //    void onItemLongClick(View view, int position);
}
