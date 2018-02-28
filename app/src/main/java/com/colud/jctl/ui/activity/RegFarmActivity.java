package com.colud.jctl.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.colud.jctl.JctlApplication;
import com.colud.jctl.base.BaseActivity;
import com.colud.jctl.base.BasePesenter;
import com.colud.jctl.ui.custom.TitleBar;
import com.colud.jctl.utils.BarStatusHeightUtil;
import com.colud.jctl.utils.BlurUtil;
import com.jctl.colud.R;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jctl.colud.R.id.farmers_img_boor;


/**
 * 注册第一个页面
 * Created by Jcty on 2017/3/22.
 */

public class RegFarmActivity extends BaseActivity {
    @BindView(R.id.farmers_img_fram)
    CheckBox farmersImgFram;
    @BindView(R.id.farmers_layou_fram)
    LinearLayout farmersLayouFram;
    @BindView(farmers_img_boor)
    CheckBox farmersImgBoor;
    @BindView(R.id.farmers_layou_boor)
    LinearLayout farmersLayouBoor;
    @BindView(R.id.title_cotent)
    TextView loginReg;
    @BindView(R.id.login_toolbar)
    TitleBar toolbar;


    private boolean isState = true;


    @Override
    protected BasePesenter onCreatePresenter() {
        return new BasePesenter();
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_farmers;
    }

    @Override
    public void initViews() {
        setToolBarTitle();
        showBlurBackground();
    }

    @Override
    public void addListener() {

    }

    @Override
    public void initData() {

    }

    /**
     * 设置ToolBar参数
     */
    public void setToolBarTitle() {
        loginReg.setVisibility(View.VISIBLE);
        loginReg.setText(JctlApplication.getAppResources().getString(R.string.mine_next));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//不显示自带标题
        toolbar.setNavigationIcon(R.drawable.img_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //设置透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            toolbar.setPadding(0, BarStatusHeightUtil.getStatusBarHeightAll(this), 0, 0);
        }

    }

    private void showBlurBackground() {
        Bitmap bjImg;
        View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
        try {
            bjImg = BitmapFactory.decodeStream(getResources().getAssets().open("login_bj.png"));
            //缩放并显示
            Bitmap newImg = BlurUtil.doBlur(bjImg, 20, 5);
            bjImg.recycle();

            rootView.setBackground(new BitmapDrawable(getResources(), newImg));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.farmers_img_boor, R.id.farmers_img_fram, R.id.title_cotent, R.id.farmers_layou_fram, R.id.farmers_layou_boor})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.farmers_img_boor:
                farmersImgBoor.setChecked(true);
                farmersImgFram.setChecked(false);
                break;
            case R.id.farmers_img_fram:
                farmersImgBoor.setChecked(false);
                farmersImgFram.setChecked(true);
                break;
            case R.id.title_cotent:
                Intent intent = new Intent(getApplicationContext(), RegActivity.class);
                intent.putExtra("firm", isState);
                startActivity(intent);
                break;
            case R.id.farmers_layou_fram:
                isState = true;
                farmersImgFram.setChecked(true);
                farmersImgBoor.setChecked(false);
                break;
            case R.id.farmers_layou_boor:
                isState = false;
                farmersImgFram.setChecked(false);
                farmersImgBoor.setChecked(true);
                break;
            default:
        }
    }

}
