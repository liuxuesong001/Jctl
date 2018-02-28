package com.colud.jctl.ui.custom.dialog;

import android.app.Dialog;
import android.content.Context;
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

public class StewardRateDialog extends Dialog {

    private TextView tvTitle;
    private TextView tvContent;
    private Context mContext;
    private RateDialogItem item;

    public StewardRateDialog(Context context) {
        this(context, 0);
    }

    public StewardRateDialog(Context context, int themeResId) {
        super(context, themeResId);
        //		setCustomDialogUpdate();
        setCustomDialog(null);
    }

    public StewardRateDialog(Context context, int themeResId, RateDialogItem data) {
        super(context, themeResId);
        setCustomDialog(data);
    }

    /**
     * 缓存对话框
     */
    private void setCustomDialog(RateDialogItem data) {
        //		if (data != null) {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_steward, null);
        TextView tvQx = (TextView) mView.findViewById(R.id.steward_qx);
        //			TextView tvContent = (TextView) mView.findViewById(R.id.tv_dialog_content);
        //		Button positiveButton = (Button) mView.findViewById(R.id.dialog_confirm);
        Button stewardBtn = (Button) mView.findViewById(R.id.steward_btn);
        //			tvTitle.setText(data.getTvTitle());
        if (tvQx != null) {
            tvQx.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickRateListener != null) {
                        onClickRateListener.onClickRight();
                    }
                }
            });
        }
        if (stewardBtn != null) {
            stewardBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickRateListener != null) {
                        onClickRateListener.onClickBtn();
                    }
                }
            });
        }
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.setContentView(mView);
        //		}
    }

    onClickRateDialog onClickRateListener;

    public void setMyClickListener(onClickRateDialog listener) {
        onClickRateListener = listener;
    }


    public interface onClickRateDialog {

        void onClickRight();

        void onClickBtn();
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
        //		lp.width = (int) (screenWidth * 0.7); // 宽度
        //		lp.height = (int) (lp.width * 0.6);     // 高度
        lp.width = (int) (screenWidth * 0.9); // 宽度
        lp.height = (int) (lp.width * 1.2);     // 高度
        dialogWindow.setAttributes(lp);
    }

}
