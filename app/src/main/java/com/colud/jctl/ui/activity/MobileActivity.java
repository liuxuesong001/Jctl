package com.colud.jctl.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v4.util.ArrayMap;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.colud.jctl.base.AppManager;
import com.colud.jctl.base.BaseActivity;
import com.colud.jctl.bean.FindBean;
import com.colud.jctl.bean.RegItem;
import com.colud.jctl.bean.SmsItem;
import com.colud.jctl.mvp.contract.RegContract;
import com.colud.jctl.mvp.presenter.RegPresenter;
import com.colud.jctl.ui.custom.TitleBar;
import com.colud.jctl.utils.BarStatusHeightUtil;
import com.colud.jctl.utils.BlurUtil;
import com.colud.jctl.utils.ToastUtils;
import com.colud.jctl.utils.editview.EditTextWithDel;
import com.jctl.colud.R;
import com.socks.library.KLog;

import java.io.IOException;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 注册 提交提交页
 * Created by Jcty on 2017/3/22.
 */

public class MobileActivity extends BaseActivity<RegPresenter> implements RegContract.View {
    @BindView(R.id.reg_mobile)
    EditTextWithDel regMobile;
    @BindView(R.id.reg_pass)
    EditTextWithDel regPass;
    @BindView(R.id.reg_password)
    EditTextWithDel regSubimtpass;
    @BindView(R.id.reg_vercode)
    EditText regVercode;
    @BindView(R.id.mine_btnSendCode)
    Button sendBtn;
    @BindView(R.id.mobile_consent)
    CheckBox mobileConsent;
    @BindView(R.id.reg_btn)
    Button regBtn;
    @BindView(R.id.reg_compact)
    TextView regCompact;
    @BindView(R.id.login_toolbar)
    TitleBar toolbar;

    private String etMobile;
    private String etPass;
    private String etPassWord;
    private String etVerCode;
    private CountDownTimer countTime;

    private Dialog progressDialog;


    @Override
    public int getContentViewId() {
        return R.layout.activity_mobile;
    }


    @Override
    public void initViews() {
        setToolBarTitle();
        sendBtn.getBackground().setAlpha(30);
        regBtn.getBackground().setAlpha(30);
        showBlurBackground();
    }

    /*
    * 设置ToolBar参数
    */
    public void setToolBarTitle() {
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

    @Override
    public void addListener() {
        //		regMobile.addTextChangedListener(watcher);
        countTime = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                sendBtn.setText(millisUntilFinished / 1000 + "\t秒");
            }

            @Override
            public void onFinish() {
                sendBtn.setEnabled(true);
                sendBtn.setText("发送验证码");
            }
        };
    }

    private TextWatcher watcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // TODO Auto-generated method stub

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub

        }

        @Override
        public void afterTextChanged(Editable temp) {
            // TODO Auto-generated method stub
            //			editStart = mEditText.getSelectionStart();
            //			editEnd = mEditText.getSelectionEnd();
            if (temp.length() == 11) {//限制长度
                //				EditTextUtil.showSoftInputFromWindow(MobileActivity.this,regPass);
                //				Toast.makeText(MainActivity.this,
                //						"输入的字数已经超过了限制！", Toast.LENGTH_SHORT)
                //						.show();
                //				s.delete(editStart - 1, editEnd);
                //				int tempSelection = editStart;
                //				mEditText.setText(s);
                //				mEditText.setSelection(tempSelection);
            }
        }
    };

    @Override
    public void initData() {

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

    @OnClick({R.id.mine_btnSendCode, R.id.reg_compact, R.id.reg_btn})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine_btnSendCode:
                Map<String, String> sms = new ArrayMap<>();
                if (!TextUtils.isEmpty(regMobile.getText().toString().trim())) {
                    if (11 == regMobile.getText().toString().length()) {
                        sms.put("mobile", regMobile.getText().toString().trim());
                        mPresenter.setSmsData(sms);
                    } else {
                        ToastUtils.showShort("请填写正确手机号");
                        return;
                    }
                } else {
                    ToastUtils.showShort("请填写手机号");
                    return;
                }
                break;
            case R.id.reg_btn:
                if (!TextUtils.isEmpty(regMobile.getText().toString().trim())) {
                    etMobile = regMobile.getText().toString().trim();
                } else {
                    ToastUtils.showShort("请填写手机号");
                    return;
                }
                if (!TextUtils.isEmpty(regPass.getText().toString().trim())) {
                    etPass = regPass.getText().toString().trim();
                } else {
                    ToastUtils.showShort("请输入您的密码");
                    return;
                }
                if (!TextUtils.isEmpty(regSubimtpass.getText().toString().trim())) {
                    etPassWord = regSubimtpass.getText().toString().trim();
                } else {
                    ToastUtils.showShort("请输入确认密码");
                    return;
                }
                if (!TextUtils.isEmpty(regVercode.getText().toString().trim())) {
                    etVerCode = regVercode.getText().toString().trim();
                } else {
                    ToastUtils.showShort("请输入您的验证码");
                    return;
                }
                if (mobileConsent.isChecked()) {
                } else {
                    ToastUtils.showShort("还没同意用户使用协议");
                    return;
                }
                RegItem regItem = (RegItem) getIntent().getSerializableExtra("rega");
                regItem.setmMobile(etMobile);
                regItem.setPassword(etPass);
                regItem.setmVerCode(etVerCode);
                regItem.setLoginName(etMobile);
                //            @Query("loginName") String loginName ,
                //            @Query("sex") String sex ,
                //            @Query("age") String age ,
                //            @Query("address") String address ,
                //            @Query("company") String company ,
                //            @Query("mobile") String mobile ,
                //            @Query("name") String name ,
                //            @Query("password") String password ,
                //            @Query("verCode") String ver
                Map<String, String> map = new ArrayMap<>();
                map.put("loginName", regItem.getLoginName());
                map.put("sex", String.valueOf(regItem.getmSex()));
                map.put("age", regItem.getmAge());
                map.put("address", regItem.getmAddress());
                map.put("company", regItem.getCompanyName());
                map.put("mobile", regItem.getmMobile());
                map.put("name", regItem.getmName());
                map.put("password", regItem.getPassword());
                map.put("verCode", regItem.getmVerCode());
                mPresenter.setRegData(map);
                break;
            case R.id.reg_compact:
                break;
            default:
        }
    }

    @Override
    protected RegPresenter onCreatePresenter() {
        return new RegPresenter(this);
    }

    @Override
    public void showDialog() {
        if (progressDialog == null) {
            progressDialog = new Dialog(MobileActivity.this, R.style.progress_dialog);
            progressDialog.setContentView(R.layout.dialog);
            progressDialog.setCancelable(true);
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            TextView msg = (TextView) progressDialog.findViewById(R.id.id_tv_loadingmsg);
            msg.setText("加载中...");
            progressDialog.show();
        }
    }

    @Override
    public void onSucceed(RegItem data) {
        if (data != null && "1".equals(data.getFlag())) {
            //            JctlApplication.getCache().put(KeyConstants.KAY_REG, data);
            ToastUtils.showShort(data.getMsg());
            //            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            //            startActivity(intent);
            //            AppManager.newInstance().finishAllActivity();
            Intent intent= new Intent(this, SplashActivity.class);
//            intent.putExtra("reg",data);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            AppManager.newInstance().finishCurrentActivity();
            startActivity(intent);
        } else {
            ToastUtils.showShort(data.getMsg());
        }

    }

    @Override
    public void onSucceedSms(SmsItem data) {
        if (data.getCode() != null && !"".equals(data.getCode()))
        {
            etVerCode = data.getCode();
        }
        countTime.start();
        ToastUtils.showLong("发送成功");
    }

    @Override
    public void onSucceedFind(FindBean data) {

    }

    @Override
    public void onFailure(String err, Throwable e) {
        ToastUtils.showLong(err);

    }

    @Override
    public void onFail(String err) {
        KLog.d(err);
    }

    @Override
    public void hideDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countTime != null) {
            countTime.cancel();
        }
    }
}
