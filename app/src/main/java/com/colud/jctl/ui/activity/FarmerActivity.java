package com.colud.jctl.ui.activity;

import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.colud.jctl.JctlApplication;
import com.colud.jctl.adapters.StewardAdapterDemo;
import com.colud.jctl.base.BaseActivity;
import com.colud.jctl.base.BasePesenter;
import com.colud.jctl.bean.FarmersBean;
import com.colud.jctl.ui.custom.TitleBar;
import com.colud.jctl.ui.custom.dialog.FarmerDialog;
import com.colud.jctl.utils.BarStatusHeightUtil;
import com.jctl.colud.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Jcty on 2017/4/11.
 */

public class FarmerActivity extends BaseActivity {

    @BindView(R.id.title_toolbar)
    TitleBar toolbar;
    @BindView(R.id.title_cotent)
    TextView tvContent;
    @BindView(R.id.farmer_rv)
    RecyclerView rv;
    //	@BindView(R.id.add_btn)
    //	ImageButton addBtn;
    @BindView(R.id.btm_delete)
    LinearLayout btmDelete;

    private List<FarmersBean.DataBean> list = new ArrayList<>();

    private LinearLayoutManager layoutManager;

    private StewardAdapterDemo mAdapter;

    private boolean isTouchState = false;

    private FarmerDialog dialog;

    @Override
    protected BasePesenter onCreatePresenter() {
        return null;
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_farmer;
    }

    @Override
    public void initViews() {
        tvContent.setVisibility(View.VISIBLE);
        tvContent.setText(JctlApplication.getAppResources().getString(R.string.mine_bj));
        initToolbar();
        initAdapter();

    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//不显示自带标题
        toolbar.setNavigationIcon(R.drawable.img_back);
        toolbar.setCenterTitle(JctlApplication.getAppResources().getString(R.string.tv_nh));
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

    protected void initAdapter() {
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        mAdapter = new StewardAdapterDemo(this, null, true);
        rv.setAdapter(mAdapter);

        if (list != null && list.size() > 0) {
            mAdapter.setNewData(list);
        }

    }

    @Override
    public void addListener() {
        //		mAdapter.setOnItemClickListener(new OnItemClickListener<FarmersBean.DataBean>() {
        //			@Override
        //			public void onItemClick(ViewHolder viewHolder, FarmersBean.DataBean data, int position) {
        //				ToastUtils.showShort(data.getName());
        //				viewHolder.setOnClickListener(R.id.steward_checkbox, new View.OnClickListener() {
        //					@Override
        //					public void onClick(View v) {
        //						CheckBox cb=(CheckBox) v;
        //						if (cb.isChecked()) {
        //							cb.setChecked(false);
        //						} else {
        //							cb.setChecked(true);
        //						}
        //
        //					}
        //				});
        //			}
        //		});

    }

    @Override
    public void initData() {
        list = (List<FarmersBean.DataBean>) getIntent().getSerializableExtra("data");

    }


    @OnClick({R.id.title_cotent, R.id.add_btn, R.id.btm_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_cotent:
                if ("编辑".equals(tvContent.getText().toString())) {
                    isTouchState = true;
                    //					addBtn.setVisibility(View.GONE);
                    btmDelete.setVisibility(View.VISIBLE);
                    tvContent.setText(JctlApplication.getAppResources().getString(R.string.tv_qxiao));
                } else {
                    isTouchState = false;
                    //					addBtn.setVisibility(View.VISIBLE);
                    btmDelete.setVisibility(View.GONE);
                    tvContent.setText(JctlApplication.getAppResources().getString(R.string.mine_bj));

                }
                mAdapter.setStateTouch(isTouchState);
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.add_btn:
                dialog = new FarmerDialog(this, R.style.MyDialog);
                dialog.setMyClickListener(new FarmerDialog.onClickRateDialog() {
                    @Override
                    public void onClickRight() {
                        dialog.dismiss();
                    }

                    @Override
                    public void onClickBtn() {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                dialog.setCancelable(false);
                break;
            case R.id.btm_delete:

                break;
        }
    }


}
