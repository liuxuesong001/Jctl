package com.colud.jctl.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.acker.simplezxing.activity.CaptureActivity;
import com.jctl.colud.R;


/**
 * Created by Jcty on 2017/5/6.
 */

public class SimpleCaptureActivity extends AppCompatActivity {
    private static final int REQUEST_QR_CODE = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_SWIPE_TO_DISMISS);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.action_item_layout);
        Intent in = new Intent(SimpleCaptureActivity.this, CaptureActivity.class);
        startActivityForResult(in, REQUEST_QR_CODE);
    }


    //	if (Build.VERSION.SDK_INT >= 23) {
    //		int checkCallPhonePermission =
    //				ContextCompat.checkSelfPermission(ZXingActivity.this,Manifest.permission.CAMERA);
    //		if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
    //			ActivityCompat.requestPermissions(ZXingActivity.this, new String[]{Manifest.permission.CAMERA}, 222);
    //			return;
    //		} else {
    //			startCamera();
    //		}
    //	} else {
    //		startCamera();
    //	}
}
