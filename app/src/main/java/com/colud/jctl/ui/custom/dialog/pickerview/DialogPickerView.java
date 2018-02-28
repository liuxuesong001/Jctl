package com.colud.jctl.ui.custom.dialog.pickerview;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.colud.jctl.JctlApplication;
import com.jctl.colud.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Jcty on 2017/4/12.
 */

public class DialogPickerView {


    private Context mContext;

    private OptionsPickerView customOptions;


    private OptionsPickerView.OnOptionsSelectListener mlistener;

    private ArrayList<Integer> agelist;

    private List<String> content;


    public DialogPickerView(Context context, OptionsPickerView.OnOptionsSelectListener mlistener, ArrayList<Integer> cardItem) {
        this.mContext = context;
        this.mlistener = mlistener;
        this.agelist = cardItem;
        setAgePickerView();

    }


    public void setAgePickerView() {
        customOptions = new OptionsPickerView.Builder(mContext, mlistener)
                .setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tvFinish = (TextView) v.findViewById(R.id.tv_finish);
                        TextView prickerviewTitle = (TextView) v.findViewById(R.id.prickerview_title);
                        prickerviewTitle.setText("年龄");
                        tvFinish.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                customOptions.returnData();
                            }
                        });

                    }
                })

                .setDividerColor(JctlApplication.getAppResources().getColor(R.color.color_CECECE))
                .setTextColorCenter(JctlApplication.getAppResources().getColor(R.color.color_3AB07D)) //设置选中项文字颜色
                .setContentTextSize(15)
                .isDialog(true)
                .build();
        customOptions.setPicker(agelist);

    }

    public void isShow() {
        customOptions.show();
    }
}
