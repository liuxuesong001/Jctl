package com.colud.jctl.ui.custom.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.colud.jctl.bean.RateDialogItem;
import com.jctl.colud.R;

/**
 * Created by Jcty on 2017/3/28.
 */

public class RateDialog extends Dialog {
    private TextView tvTitle;
    private TextView tvContent;
    private Context mContext;
    private RateDialogItem item;

    public RateDialog(Context context) {
        this(context, 0);
    }

    public RateDialog(Context context, int themeResId) {
        super(context, themeResId);
        //		setCustomDialogUpdate();
    }

    public RateDialog(Context context, int themeResId, RateDialogItem data) {
        super(context, themeResId);
        setCustomDialog(data);
    }

    /**
     * 缓存对话框
     */
    private void setCustomDialog(RateDialogItem data) {
        if (data != null) {
            View mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_alert, null);
            TextView tvTitle = (TextView) mView.findViewById(R.id.tv_dialog);
            TextView tvContent = (TextView) mView.findViewById(R.id.tv_dialog_content);
            Button positiveButton = (Button) mView.findViewById(R.id.dialog_confirm);
            Button negativeButton = (Button) mView.findViewById(R.id.dialog_cancel);

            tvTitle.setText(data.getTvTitle());
            if (!TextUtils.isEmpty(data.getTvContent())) {
                tvContent.setVisibility(View.VISIBLE);
                tvContent.setText(data.getTvContent());
            }

            if (positiveButton != null)
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onClickRateListener != null)
                            onClickRateListener.onClickRight();
                    }
                });
            if (negativeButton != null)
                negativeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onClickRateListener != null)
                            onClickRateListener.onClickLeft();
                    }
                });
            getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            requestWindowFeature(android.view.Window.FEATURE_NO_TITLE);
            super.setContentView(mView);
        }
    }

    onClickRateDialog onClickRateListener;

    public void setMyClickListener(onClickRateDialog listener) {
        onClickRateListener = listener;
    }

    public interface onClickRateDialog {
        void onClickLeft();

        void onClickRight();
    }

    @Override
    public void show() {
        super.show();
        Window dialogWindow = this.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        /////////获取屏幕宽度
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        /////////设置高宽
        lp.width = (int) (screenWidth * 0.7); // 宽度
        lp.height = (int) (lp.width * 0.6);     // 高度
        dialogWindow.setAttributes(lp);
    }

}
