package com.colud.jctl.ui.custom;

import android.content.Context;
import android.view.View;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Jcty on 2017/4/1.
 */

public class CustomTimePickerView {

    private Context mContext;
    private TimePickerView timePickerView;
    private OptionsPickerView CustomOptions;
    private ArrayList<String> mList;

    public CustomTimePickerView(Context context, View v, TimePickerView timePickerView, OptionsPickerView customOptions) {
        this.mContext = context;
        this.timePickerView = timePickerView;
        this.CustomOptions = customOptions;
        getTimePickerView(context, v);
        //		initCustomOptionPicker(context);
    }
    //	public CustomTimePickerView(Context context,View v,OptionsPickerView customOptions) {
    //		this.mContext=context;
    //		this.CustomOptions=customOptions;
    //		initCustomOptionPicker(context,v);
    //	}

    public void showDialog() {
        timePickerView.show();
    }
    //	public void showDialogCustom() {
    //		CustomOptions.setPicker(mList);//添加数据
    //		CustomOptions.show();
    //	}

    public void getTimePickerView(Context context, final View view) {
        //		// 注意：自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针
        //		// 具体可参考demo 里面的两个自定义布局
        //		//因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        //		//控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        //		Calendar selectedDate = Calendar.getInstance();//系统当前时间
        //		Calendar startDate = Calendar.getInstance();
        //		startDate.set(2017,1,1);
        //		Calendar endDate = Calendar.getInstance();
        //		endDate.set(2027,12,31);
        //		timePickerView=new TimePickerView.Builder(context, new TimePickerView.OnTimeSelectListener() {
        //			@Override
        //			public void onTimeSelect(Date date, View v) {
        //				TextView managedate=(TextView)view.findViewById(R.id.managefield_fpdate);
        //				managedate.setText(getTime(date));
        //			}
        //		})
        //				.setDataTime(selectedDate)
        //				.setRangDate(startDate,endDate)
        //				.setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {
        //
        //					@Override
        //					public void customLayout(View v) {
        //						final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
        //						//						ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
        //						tvSubmit.setOnClickListener(new View.OnClickListener() {
        //							@Override
        //							public void onClick(View v) {
        //								timePickerView.returnData(tvSubmit);
        //							}
        //						});
        //						//						ivCancel.setOnClickListener(new View.OnClickListener() {
        //						//							@Override
        //						//							public void onClick(View v) {
        //						//								timePickerView.dismiss();
        //						//							}
        //						//						});
        //					}
        //				})
        //				.setDividerColor(view.getResources().getColor(R.color.color_CECECE))
        //				.setTextColorCenter(view.getResources().getColor(R.color.color_3AB07D)) //设置选中项文字颜色
        //				.setContentSize(18)
        //				.isDialog(true)
        //				.build();
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        //		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public void iniDataType(ArrayList<String> type) {
        this.mList = type;

    }
}
