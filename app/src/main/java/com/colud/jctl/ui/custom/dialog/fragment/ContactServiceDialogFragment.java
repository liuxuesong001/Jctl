package com.colud.jctl.ui.custom.dialog.fragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.colud.jctl.utils.ToastUtils;
import com.jctl.colud.R;

/**
 * Created by Jcty on 2017/3/28.
 */

public class ContactServiceDialogFragment extends DialogFragment implements View.OnClickListener {

    //写一个静态方法产生实例
    public static ContactServiceDialogFragment newInstance() {
        ContactServiceDialogFragment csdf = new ContactServiceDialogFragment();
        return csdf;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_alert, container, false); //引入布局
        Button cancel = (Button) v.findViewById(R.id.dialog_cancel); //找到控件
        Button call = (Button) v.findViewById(R.id.dialog_confirm);
        cancel.setOnClickListener(this); //设置监听
        call.setOnClickListener(this);
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { //此处可以设置Dialog的style等等
        super.onCreate(savedInstanceState);
        setCancelable(false);//无法直接点击外部取消dialog
        setStyle(DialogFragment.STYLE_NO_FRAME, 0); //NO_FRAME就是dialog无边框，0指的是默认系统Theme
    }

    @Override
    public void onClick(View v) {  //点击事件
        switch (v.getId()) {
            case R.id.dialog_cancel:
                dismiss();
                break;
            case R.id.dialog_confirm:
                ToastUtils.showShort("打电话");
                break;
            default:
                break;
        }
    }
}