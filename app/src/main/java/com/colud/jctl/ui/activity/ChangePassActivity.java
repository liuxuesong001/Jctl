package com.colud.jctl.ui.activity;

import android.app.Dialog;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v4.util.ArrayMap;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.colud.jctl.JctlApplication;
import com.colud.jctl.api.KeyConstants;
import com.colud.jctl.base.BaseActivity;
import com.colud.jctl.bean.FindBean;
import com.colud.jctl.bean.RegItem;
import com.colud.jctl.bean.SmsItem;
import com.colud.jctl.mvp.contract.RegContract;
import com.colud.jctl.mvp.presenter.RegPresenter;
import com.colud.jctl.base.AppManager;
import com.colud.jctl.ui.custom.TitleBar;
import com.colud.jctl.utils.BarStatusHeightUtil;
import com.colud.jctl.utils.ToastUtils;
import com.jctl.colud.R;
import com.socks.library.KLog;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 修改密码
 * Created by Jcty on 2017/3/28.
 */

public class ChangePassActivity extends BaseActivity<RegPresenter> implements RegContract.View {

    @BindView(R.id.change_btnSendCode)
    Button sendBtn;
    @BindView(R.id.change_confirm)
    Button submitBtn;
    @BindView(R.id.change_phone)
    EditText changePhone;
    @BindView(R.id.change_pass)
    EditText changePass;
    @BindView(R.id.change_passqr)
    EditText changePassqr;
    @BindView(R.id.change_code)
    EditText changeCode;
    @BindView(R.id.login_toolbar)
    TitleBar toolbar;

    private Dialog progressDialog;

    private CountDownTimer countTime;

    private Map<String, String> map = new ArrayMap<>();

    private String mContent = "";

    private String phone = "";

    @OnClick({R.id.change_confirm, R.id.change_btnSendCode})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.change_btnSendCode:
                mContent = "发送中...";
                if (!"".equals(changePhone.getText().toString().trim())) {
                    if (11 == changePhone.getText().length()) {
                        phone = changePhone.getText().toString();
                        map.clear();
                        map.put("mobile", phone);
                        KLog.d(map.toString());
                        mPresenter.setSmsData(map);
                    } else {
                        ToastUtils.showLong("请输入正确的手机号");
                        return;
                    }
                } else {
                    ToastUtils.showLong("请输入您的手机号");
                }

                break;
            case R.id.change_confirm:
                mContent = "修改中...";
                if (!"".equals(changePhone.getText().toString().trim())) {
                    if (11 == changePhone.getText().length()) {

                    } else {
                        ToastUtils.showLong("请输入正确的手机号");
                        return;
                    }
                    if (!"".equals(changePass.getText().toString().trim())) {
                        if (!"".equals(changePassqr.getText().toString().trim())) {
                            if (changePass.getText().toString().trim().equals(changePassqr.getText().toString().trim())) {
                                if (!"".equals(changeCode.getText().toString().trim())) {
                                    map.clear();
                                    map.put("loginName", changePhone.getText().toString().trim());
                                    map.put("code", changeCode.getText().toString().trim());
                                    map.put("newPassword", changePass.getText().toString().trim());
                                    mPresenter.setFindData(map);
                                } else {
                                    ToastUtils.showLong("请填写验证码");
                                }
                            } else {
                                ToastUtils.showLong("密码不一致");
                            }

                        } else {
                            ToastUtils.showLong("确认密码不能为空");
                        }

                    } else {
                        ToastUtils.showLong("新密码不能为空");
                    }

                } else {
                    ToastUtils.showLong("请输入您的手机号");
                }
                break;
        }
    }


    @Override
    public int getContentViewId() {
        return R.layout.activity_changepw;
    }

    @Override
    public void initViews() {
        setTitle();
        //		EditTextUtil.showSoftInputFromWindow(this,changePhone);
    }

    protected void setTitle() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//不显示自带标题
        toolbar.setCenterTitle(JctlApplication.getAppResources().getString(R.string.more_xgmm));
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

    @Override
    public void initData() {

    }

    @Override
    protected RegPresenter onCreatePresenter() {
        return new RegPresenter(this);
    }

    @Override
    public void showDialog() {
        if (progressDialog == null) {
            progressDialog = new Dialog(ChangePassActivity.this, R.style.progress_dialog);
            progressDialog.setContentView(R.layout.dialog);
            progressDialog.setCancelable(true);
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            TextView msg = (TextView) progressDialog.findViewById(R.id.id_tv_loadingmsg);
            msg.setText(mContent);
            progressDialog.show();
        }
    }

    @Override
    public void onSucceed(RegItem data) {

    }

    @Override
    public void onSucceedSms(SmsItem data) {
        if (data.getCode() != null && !"".equals(data.getCode())) {
            ToastUtils.showLong("发送成功");
            countTime.start();
        }
    }

    @Override
    public void onSucceedFind(FindBean data) {
        if ("".equals(data.getMsg()) && data.getMsg() == null) {
            ToastUtils.showLong(data.getMsg());
        } else {
            ToastUtils.showLong("修改成功,请重新登录");
        }
        KeyConstants.LGOIN_IS = false;
        JctlApplication.getCache().remove(KeyConstants.USER_ITEM);
        AppManager.newInstance().finishCurrentActivity();

    }

    @Override
    public void onFailure(String err, Throwable e) {

    }

    @Override
    public void onFail(String err) {

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
