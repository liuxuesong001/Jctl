package com.colud.jctl.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;

import java.io.IOException;

/**
 * Created by Jcty on 2017/3/23.
 */

public class ShowBlurUtil {

    public static void showBlurBackground(Activity activity) {
        Bitmap bjImg;
        View rootView = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        try {
            bjImg = BitmapFactory.decodeStream(activity.getResources().getAssets().open("login_bj.png"));
            //缩放并显示
            Bitmap newImg = BlurUtil.doBlur(bjImg, 20, 5);
            bjImg.recycle();

            rootView.setBackground(new BitmapDrawable(activity.getResources(), newImg));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
