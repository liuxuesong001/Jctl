package com.colud.jctl.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.colud.jctl.JctlApplication;
import com.colud.jctl.api.KeyConstants;
import com.colud.jctl.base.BaseActivity;
import com.colud.jctl.bean.UserItem;
import com.colud.jctl.mvp.contract.LoginContract;
import com.colud.jctl.mvp.presenter.LoginPresenter;
import com.colud.jctl.ui.custom.TitleBar;
import com.colud.jctl.utils.BarStatusHeightUtil;
import com.colud.jctl.utils.ToastUtils;
import com.colud.jctl.utils.editview.EditTextWithDel;
import com.jctl.colud.R;
import com.socks.library.KLog;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.colud.jctl.api.KeyConstants.LOGIN_SUCCESS;
import static com.colud.jctl.api.KeyConstants.LOGIN_SUCCESS_EXIT;
import static com.colud.jctl.api.KeyConstants.USER_NAME;


/**
 * 登陆页
 * Created by Jcty on 2017/3/2.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {


    @BindView(R.id.login_user)
    EditTextWithDel loginUser;
    @BindView(R.id.login_password)
    EditTextWithDel loginPassword;
    @BindView(R.id.remember_password)
    CheckBox cb;
    @BindView(R.id.login_btn)
    Button btnLogin;
    @BindView(R.id.login_line_reg)
    ImageView loginLineReg;
    @BindView(R.id.login_toolbar)
    TitleBar toolbar;
    @BindView(R.id.title_cotent)
    TextView loginReg;
    @BindView(R.id.login_wjmm)
    TextView loginWjmm;
    @BindView(R.id.login_tv_jzmm)
    TextView loginJzmm;

    private String mUser = "";

    private String mPass = "";

    //是否记住密码
    private boolean isPass = true;

    private Dialog progressDialog;

    private Map<String,String> map = new ArrayMap<>();

    @Override
    public int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    public void initViews() {
        setToolBarTitle();

    }

    @Override
    public void addListener() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    @Override
    public void initData() {

    }

    /**
     * 设置ToolBar参数
     */
    public void setToolBarTitle() {
        btnLogin.getBackground().setAlpha(30);
        loginReg.setVisibility(View.VISIBLE);
        loginReg.setText(JctlApplication.getAppResources().getString(R.string.mine_zc));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//不显示自带标题
        toolbar.setNavigationIcon(R.drawable.img_back);

        //设置透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            toolbar.setPadding(0, BarStatusHeightUtil.getStatusBarHeightAll(this), 0, 0);
        }

    }


    @OnClick({R.id.login_tv_jzmm, R.id.title_cotent, R.id.login_wjmm, R.id.login_line_reg, R.id.login_btn})
    public void onClick(View v) {
        Intent in;
        switch (v.getId()) {
            case R.id.title_cotent:
                in = new Intent(getApplicationContext(), RegFarmActivity.class);
                startActivity(in);
                break;
            case R.id.login_wjmm:
                in = new Intent(getApplicationContext(), FindActivity.class);
                startActivity(in);
                break;
            case R.id.login_line_reg:
                in = new Intent(getApplicationContext(), RegFarmActivity.class);
                startActivity(in);
                break;
            case R.id.login_btn:
                if (!TextUtils.isEmpty(loginUser.getText().toString().trim())) {
                    mUser = loginUser.getText().toString().trim();
                } else {
                    ToastUtils.showShort("请输入用户名");
                    return;
                }
                if (!TextUtils.isEmpty(loginPassword.getText().toString().trim())) {
                    mPass = loginPassword.getText().toString().trim();
                } else {
                    ToastUtils.showShort("请输入密码");
                    return;
                }
                String channelId = JctlApplication.getCache().getAsString(KeyConstants.KAY_CHANNELID);
                //                @Query("loginName") String user,
                //            @Query("password") String pass,
                //            @Query("channelId") String channelId
                map.clear();
                map.put("loginName",mUser);
                map.put("password",mPass);
                map.put("channelId",channelId);
                mPresenter.getUserData(map);
                break;
            case R.id.login_tv_jzmm:
                if (cb.isChecked()) {
                    isPass = true;
                    cb.setChecked(false);
                } else {
                    isPass = false;
                    cb.setChecked(true);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void showDialog() {
        //		JctlApplication.getGifLoadingView().show(getFragmentManager(),"");
        if (progressDialog == null) {
            progressDialog = new Dialog(LoginActivity.this, R.style.progress_dialog);
            progressDialog.setContentView(R.layout.dialog);
            progressDialog.setCancelable(true);
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            TextView msg = (TextView) progressDialog.findViewById(R.id.id_tv_loadingmsg);
            msg.setText("登录中...");
            progressDialog.show();
        }

    }

    @Override
    public void onSucceed(UserItem userItem) {
        if (userItem != null && 1 == userItem.getFlag()) {
            if (isPass) {
                JctlApplication.getCache().put(KeyConstants.USER_ITEM, userItem);
                JctlApplication.getCache().put(USER_NAME, mUser);
            }
            Intent intent =new Intent();
            intent.putExtra("userData",userItem);
            int type = getIntent().getIntExtra("login",0);
            switch (type)
            {
                case LOGIN_SUCCESS_EXIT:
                    setResult(LOGIN_SUCCESS_EXIT,intent);
                    break;
                case LOGIN_SUCCESS:
                    setResult(LOGIN_SUCCESS,intent);
                    break;
                default:

            }
            finish();
            KeyConstants.LGOIN_IS = true;
            KeyConstants.USER_SINGLEID = userItem.getUser().getSingleId();
        } else {
            ToastUtils.showShort(userItem.getMsg());
        }
        KLog.i("SingleId:" + userItem.getUser().getSingleId());
    }

    @Override
    public void onFailure(String err, Throwable e) {

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
        //		JctlApplication.getGifLoadingView().dismiss();
    }


    @Override
    protected LoginPresenter onCreatePresenter() {
        return new LoginPresenter(this);
    }
}
