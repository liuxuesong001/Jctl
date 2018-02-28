package com.colud.jctl.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Message;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.colud.jctl.JctlApplication;
import com.colud.jctl.base.BaseActivity;
import com.colud.jctl.bean.NodeCollectionCycle;
import com.colud.jctl.bean.OffOnItemManage;
import com.colud.jctl.mvp.contract.OffOnManageContract;
import com.colud.jctl.mvp.presenter.OffOnManagePresenter;
import com.colud.jctl.ui.custom.TitleBar;
import com.colud.jctl.utils.BarStatusHeightUtil;
import com.colud.jctl.utils.ToastUtils;
import com.jctl.colud.R;
import com.suke.widget.SwitchButton;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 开关控制页
 * Created by Jcty on 2017/4/6.
 */

public class OffOnActivity extends BaseActivity<OffOnManagePresenter> implements OffOnManageContract.View {

    @BindView(R.id.title_toolbar)
    TitleBar toolbar;
    @BindView(R.id.off_a)
    RelativeLayout offA;
    @BindView(R.id.off_b)
    RelativeLayout offB;
    @BindView(R.id.off_d)
    RelativeLayout offD;
    @BindView(R.id.off_on_sd)
    SwitchButton offOnSd;

    private String nodeId, status, nodeMac;
    private NodeCollectionCycle cycleItem = new NodeCollectionCycle();
    private Timer timer = new Timer();
    private TimerTask task;

    public String OFFSTATE = ""; //保留当前开关状态

    private int count = 1;

    private boolean isSucceed = true;

    private Dialog progressDialog;

    @OnClick({R.id.off_a, R.id.off_b, R.id.off_d})
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.off_a:
                intent = new Intent(OffOnActivity.this, CycleUpdateActivity.class);
                intent.putExtra("cycleItem", cycleItem);
                startActivity(intent);
                break;
            case R.id.off_b:
                intent = new Intent(getApplicationContext(), CapaCityActivity.class);
                intent.putExtra("cycleItem", cycleItem);
                startActivity(intent);
                break;
            case R.id.off_d:
                intent = new Intent(getApplicationContext(), CycleControlActivity.class);
                intent.putExtra("cycleItem", cycleItem);
                startActivity(intent);
                break;
        }
    }


    @Override
    public int getContentViewId() {
        return R.layout.activity_on_off;
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void initViews() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//不显示自带标题
        toolbar.setCenterTitle(JctlApplication.getAppResources().getString(R.string.tv_kgkz));
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
        offOnSd.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked && isSucceed) {
                    status = "1";
                    if (!TextUtils.isEmpty(nodeId) && !TextUtils.isEmpty(status)) {
                        //						ToastUtils.showLong("请等待...");
                        mPresenter.setOffOnItem(nodeId, status);
                    }
                } else if (isSucceed) {
                    status = "0";
                    if (!TextUtils.isEmpty(nodeId) && !TextUtils.isEmpty(status)) {
                        //						ToastUtils.showLong("请等待...");
                        mPresenter.setOffOnItem(nodeId, status);
                    }
                }
                isSucceed = true;
            }
        });

    }

    @Override
    public void initData() {
        status = getIntent().getStringExtra("status");

        OFFSTATE = status;

        nodeMac = getIntent().getStringExtra("nodeMac");

        if (nodeMac != null && !"".equals(nodeMac)) {
            Map<String, String> map = new ArrayMap<>();
            map.clear();
            map.put("nodeId", nodeMac);
            mPresenter.getNodeCycle(map);
        }
        if (status != null && "0".equals(status)) {
            offOnSd.setChecked(false);
        } else {
            offOnSd.setChecked(true);
        }
    }

    @Override
    protected OffOnManagePresenter onCreatePresenter() {
        return new OffOnManagePresenter(this);
    }

    @Override
    public void showDialog() {
        if (progressDialog == null) {
            progressDialog = new Dialog(OffOnActivity.this, R.style.progress_dialog);
            progressDialog.setContentView(R.layout.dialog);
            progressDialog.setCancelable(false);
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            TextView msg = (TextView) progressDialog.findViewById(R.id.id_tv_loadingmsg);
            msg.setText("加载中...");
            progressDialog.show();
        }

    }

    @Override
    public void onSucceedHand(OffOnItemManage data) {
        if (data != null && data.getFlag().equals("1")) {
            if (data.getMsg().equals("操作成功")) {
                ToastUtils.showLong("请等待...");
                //				offOnSd.setChecked(true);
                //				Map<String,String>map=new ArrayMap<>();
                //				map.put("id",nodeId);
                //				map.put("status",status);
                //				mPresenter.getHandlerResult(map);
            } else {
                isSucceed = false;
                if (data.getFlag().equals("1")) {
                    ToastUtils.showLong(data.getMsg());
                    if (OFFSTATE.equals("0")) {
                        offOnSd.setChecked(true);
                    } else {
                        offOnSd.setChecked(false);
                    }
                } else {
                    ToastUtils.showLong(data.getMsg());
                    if (OFFSTATE.equals("0")) {
                        offOnSd.setChecked(false);
                    } else {
                        offOnSd.setChecked(true);
                    }
                }

            }
        } else {
            isSucceed = false;
            ToastUtils.showLong(data.getMsg());
            if (OFFSTATE.equals("0")) {
                offOnSd.setChecked(false);
            } else {
                offOnSd.setChecked(true);
            }
        }
        //		if (data.getFlag().equals("0")){
        //			ToastUtils.showLong(data.getMsg());
        //			offOnSd.setChecked(true);
        //		}else {
        //			ToastUtils.showLong(data.getMsg());
        //			offOnSd.setChecked(false);
        //		}
    }

    @Override
    public void onSucceedHandlerResult(OffOnItemManage data) {
        if (data != null && data.getFlag().equals("0")) {
            //第一种方法(5秒之后开始，间隔5秒)
            if (timer != null && count < 5) {
                task = new TimerTask() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = 1;
                        //						handler.sendMessage(message);
                    }
                };
                timer.scheduleAtFixedRate(task, 5000, 5000);
            }
            //				if (timer!=null){
            //					timer.cancel();
            //				}
            //				timer.purge();
        } else {
            ToastUtils.showLong(data.getMsg());
            offOnSd.setChecked(true);
            timer.cancel();
            timer.purge();
        }
        isSucceed = false;
    }

    @Override
    public void onSucceedAll(NodeCollectionCycle data) {
        cycleItem = data;

    }

    @Override
    public void onSucceedState(OffOnItemManage data) {
        //		if (offOnSd.isChecked()){
        //			offOnSd.setChecked(false);
        //		}else {
        //			offOnSd.setChecked(true);
        //		}
    }

    @Override
    public void onFailure(String err, Throwable e) {
        ToastUtils.showLong(err);
    }

    @Override
    public void onFailureHandlerResult(String err, Throwable e) {

    }

    @Override
    public void onFail(String err) {
        isSucceed = false;
        if (OFFSTATE.equals("0")) {
            offOnSd.setChecked(false);
        } else {
            offOnSd.setChecked(true);
        }
    }

    @Override
    public void hideDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
        //		JctlApplication.getGifLoadingView().dismiss();
    }
    //	private Handler handler=new Handler(){
    //		public void handleMessage(android.os.Message msg) {
    //			switch (msg.what) {
    //				case 1:
    //					updateState();
    //					break;
    //
    //				default:
    //					break;
    //			}
    //		};
    //	};
    //	protected  void updateState(){
    //		if (count<5){
    //			Map<String,String>map=new ArrayMap<>();
    //			map.put("id",nodeId);
    //			map.put("status",status);
    //			mPresenter.getHandlerResult(map);
    //			count++;
    //		}else {
    //			ToastUtils.showLong("请求超时");
    //			if (OFFSTATE.equals("0")) {
    //				offOnSd.setChecked(false);
    //			} else {
    //				offOnSd.setChecked(true);
    //			}
    //		}
    //		isSucceed=false;
    //	}
}
