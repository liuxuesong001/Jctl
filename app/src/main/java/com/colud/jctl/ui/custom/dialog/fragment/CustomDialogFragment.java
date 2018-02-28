package com.colud.jctl.ui.custom.dialog.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.jctl.colud.R;


public class CustomDialogFragment extends DialogFragment {

    private static final String TITLE = "title";

    private static final String DESCRIPTION = "description";

    private View.OnClickListener cancelCallback;
    private View.OnClickListener confirmCallback;
    private Activity activity;

    private String title;

    private String description;

    public static CustomDialogFragment getInstance(String title, String description) {
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        bundle.putString(DESCRIPTION, description);
        CustomDialogFragment versionDialogFragment = new CustomDialogFragment();
        versionDialogFragment.setArguments(bundle);
        return versionDialogFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        title = bundle.getString(TITLE);
        description = bundle.getString(DESCRIPTION);
        setStyle(CustomDialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog_MinWidth);
    }

    public void show(FragmentManager fragmentManager, View.OnClickListener cancelCallback, View.OnClickListener confirmCallback) {
        this.cancelCallback = cancelCallback;
        this.confirmCallback = confirmCallback;
        show(fragmentManager, "DialogFragment");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_alert, null);
        //        ConfirmDialog confirmDialog =ConfirmDialog.newInstance();
        //        confirmDialog.show(getChildFragmentManager(), "confirm");
        Button btn_cancel = (Button) view.findViewById(R.id.dialog_cancel);
        Button btn_confirm = (Button) view.findViewById(R.id.dialog_confirm);
        //        tv_title.setText(title);
        //        tv_description.setText(description);
        btn_cancel.setOnClickListener(cancelCallback);
        btn_confirm.setOnClickListener(confirmCallback);
        builder.setView(view);
        return builder.create();
    }

    public static WindowManager.LayoutParams createLayoutParams(Dialog dialog) {
        Activity context = dialog.getOwnerActivity();
        final DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        final boolean isPortrait = metrics.widthPixels < metrics.heightPixels;

        float percent;
        if (isPortrait) {
            percent = context.getResources().getFraction(R.fraction.dialog_min_width_minor, 1, 1);
        } else {
            percent = context.getResources().getFraction(R.fraction.dialog_min_width_major, 1, 1);
        }
        int width = (int) (context.getResources().getDisplayMetrics().widthPixels * percent);
        int height = (int) (context.getResources().getDisplayMetrics().heightPixels * percent);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = width;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        return lp;
    }

    @Override
    public void onStart() {
        super.onStart();
        WindowManager.LayoutParams lp = createLayoutParams(getDialog());
        getDialog().getWindow().setAttributes(lp);
    }
}
