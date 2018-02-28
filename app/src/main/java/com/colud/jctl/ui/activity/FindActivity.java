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

import com.colud.jctl.base.AppManager;
import com.colud.jctl.base.BaseActivity;
import com.colud.jctl.bean.FindBean;
import com.colud.jctl.bean.RegItem;
import com.colud.jctl.bean.SmsItem;
import com.colud.jctl.mvp.contract.RegContract;
import com.colud.jctl.mvp.presenter.RegPresenter;
import com.colud.jctl.ui.custom.TitleBar;
import com.colud.jctl.utils.BarStatusHeightUtil;
import com.colud.jctl.utils.EditTextUtil;
import com.colud.jctl.utils.ShowBlurUtil;
import com.colud.jctl.utils.ToastUtils;
import com.colud.jctl.utils.editview.EditTextWithDel;
import com.jctl.colud.R;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 找回密码
 * Created by Jcty on 2017/3/3.
 */

public class FindActivity extends BaseActivity<RegPresenter> implements RegContract.View {

    @BindView(R.id.find_tel)
    EditTextWithDel findTel;
    @BindView(R.id.find_vercode)
    EditText findVercode;
    @BindView(R.id.find_btnSendCode)
    Button findBtnSendCode;
    @BindView(R.id.find_btn)
    Button findBtn;
    @BindView(R.id.mine_toolbar)
    TitleBar toolbar;
    @BindView(R.id.find_pass)
    EditTextWithDel etPass;

    private Dialog progressDialog;

    private CountDownTimer countTime;
    private Map<String, String> map = new ArrayMap<>();

    @Override
    public int getContentViewId() {
        return R.layout.activity_find;
    }

    @Override
    public void initViews() {
        findBtnSendCode.getBackground().setAlpha(30);
        findBtn.getBackground().setAlpha(30);
        ShowBlurUtil.showBlurBackground(FindActivity.this);
        initToolbar();
    }

    private void initToolbar() {
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
        countTime = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                findBtnSendCode.setText(millisUntilFinished / 1000 + "\t秒");
            }

            @Override
            public void onFinish() {
                findBtnSendCode.setEnabled(true);
                findBtnSendCode.setText("发送验证码");
            }
        };
    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.find_btn, R.id.find_btnSendCode})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.find_btn:
                if (!"".equals(findTel.getText().toString().trim()) && 11 == findTel.getText().toString().length()) {
                    if (!"".equals(findVercode.getText().toString().trim()) &&
                            4 == findVercode.getText().toString().length()) {
                        map.clear();
                        if (!"".equals(etPass.getText().toString().trim())) {
                            if (etPass.getText().length() >= 8) {
                                map.clear();
                                map.put("loginName", findTel.getText().toString().trim());
                                map.put("code", findVercode.getText().toString().trim());
                                map.put("newPassword", etPass.getText().toString().trim());
                                mPresenter.setFindData(map);
                            } else {
                                ToastUtils.showLong("密码不能少于8位");
                            }
                        } else {
                            ToastUtils.showLong("密码不能为空");
                        }
                    } else {
                        ToastUtils.showLong("请填写正确的验证码");
                    }
                } else {
                    ToastUtils.showLong("请填写手机号");
                }
                break;
            case R.id.find_btnSendCode:
                if (!"".equals(findTel.getText().toString().trim())) {
                    if (11 == findTel.getText().toString().length()) {
                        map.clear();
                        map.put("mobile", findTel.getText().toString().trim());
                        mPresenter.setSmsData(map);
                    } else {
                        ToastUtils.showLong("请填写正确的手机号码");
                    }
                } else {
                    ToastUtils.showLong("请填写手机号");
                }

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countTime != null) {
            countTime.cancel();
        }
    }

    @Override
    protected RegPresenter onCreatePresenter() {
        return new RegPresenter(this);
    }

    @Override
    public void showDialog() {
        if (progressDialog == null) {
            progressDialog = new Dialog(FindActivity.this, R.style.progress_dialog);
            progressDialog.setContentView(R.layout.dialog);
            progressDialog.setCancelable(true);
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            TextView msg = (TextView) progressDialog.findViewById(R.id.id_tv_loadingmsg);
            msg.setText("发送中...");
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
            EditTextUtil.showSoftInputFromWindow(this, findVercode);
        }
    }

    @Override
    public void onSucceedFind(FindBean data) {
        if ("".equals(data.getMsg()) && data.getMsg() == null) {
            ToastUtils.showLong(data.getMsg());
        } else {
            ToastUtils.showLong("找回成功,请重新登录");
        }
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
}
