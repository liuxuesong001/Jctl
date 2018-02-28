package com.colud.jctl.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.support.v4.util.ArrayMap;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.colud.jctl.api.KeyConstants;
import com.colud.jctl.base.BaseActivity;
import com.colud.jctl.bean.FarmersBean;
import com.colud.jctl.mvp.contract.UserFarmersContract;
import com.colud.jctl.mvp.presenter.UserFarmersPresenter;
import com.colud.jctl.ui.custom.TitleBar;
import com.colud.jctl.utils.BarStatusHeightUtil;
import com.colud.jctl.utils.ToastUtils;
import com.jctl.colud.R;

import java.io.Serializable;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 人员管理
 * Created by Jcty on 2017/4/11.
 */

public class PeopleManageActivity extends BaseActivity<UserFarmersPresenter> implements UserFarmersContract.View {

    @BindView(R.id.title_toolbar)
    TitleBar toolbar;
    @BindView(R.id.peo_gj_ll)
    LinearLayout peoGjLl;
    @BindView(R.id.peo_nh_ll)
    LinearLayout peoNhLl;


    private Map<String, String> map = new ArrayMap<>();


    @Override
    public int getContentViewId() {
        return R.layout.activity_people_manage;
    }

    @Override
    public void initViews() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//不显示自带标题
        toolbar.setNavigationIcon(R.drawable.img_back);
        toolbar.setCenterTitle(R.string.tv_rygl);
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

    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.peo_gj_ll, R.id.peo_nh_ll})
    public void onClick(View view) {
        Intent in;
        switch (view.getId()) {
            case R.id.peo_gj_ll:
                ToastUtils.showLong("功能正在完善");
                //				in=new Intent(getApplicationContext(),StewardActivity.class);
                //				startActivity(in);
                break;
            case R.id.peo_nh_ll:
                if (!"".equals(KeyConstants.USER_SINGLEID)) {
                    map.clear();
                    map.put("singleId", KeyConstants.USER_SINGLEID);
                    mPresenter.setUserFarmers(map);
                }
                break;
            default:
        }
    }

    @Override
    protected UserFarmersPresenter onCreatePresenter() {
        return new UserFarmersPresenter(this);
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void onSucceed(FarmersBean data) {
        Intent in = new Intent(getApplicationContext(), FarmerActivity.class);
        in.putExtra("data", (Serializable) data.getData());
        startActivity(in);

    }

    @Override
    public void onFailure(String err, Throwable e) {

    }

    @Override
    public void onFail(String err) {

    }

    @Override
    public void hideDialog() {

    }
}
