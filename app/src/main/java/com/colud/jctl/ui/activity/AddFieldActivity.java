package com.colud.jctl.ui.activity;

import android.app.Dialog;
import android.os.Build;
import android.support.v4.util.ArrayMap;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.colud.jctl.JctlApplication;
import com.colud.jctl.api.KeyConstants;
import com.colud.jctl.base.BaseActivity;
import com.colud.jctl.bean.JsonBeanList;
import com.colud.jctl.bean.AddFieldData;
import com.colud.jctl.bean.AddFieldItem;
import com.colud.jctl.mvp.contract.AddFieldContract;
import com.colud.jctl.mvp.presenter.AddFieldPresenter;
import com.colud.jctl.base.AppManager;
import com.colud.jctl.ui.custom.TitleBar;
import com.colud.jctl.utils.BarStatusHeightUtil;
import com.colud.jctl.utils.ToastUtils;
import com.jctl.colud.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 添加农田
 * Created by Jcty on 2017/4/1.
 */

public class AddFieldActivity extends BaseActivity<AddFieldPresenter> implements AddFieldContract.View {
    @BindView(R.id.title_cotent)
    TextView tvContent;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_ncz)
    EditText etNcz;
    @BindView(R.id.lx)
    TextView lxTv;
    @BindView(R.id.et_crop)
    EditText etCrop;
    @BindView(R.id.fpsj)
    TextView ffsj;
    @BindView(R.id.ntlx_ll)
    LinearLayout ntlxLl;
    @BindView(R.id.nh_ll)
    LinearLayout nhLl;
    @BindView(R.id.et_area)
    EditText etArea;
    @BindView(R.id.et_num)
    EditText etNum;
    @BindView(R.id.et_addr)
    EditText etAddr;
    @BindView(R.id.et_ncjj)
    EditText etNcjj;
    @BindView(R.id.nhfield)
    TextView nhTv;
    @BindView(R.id.title_toolbar)
    TitleBar toolbar;

    private OptionsPickerView opvtype, opvnh;
    private List<String> typelist = new ArrayList<>();
    private List<String> nList;
    private List<String> idList;
    private String nhId;
    private String typeInt;

    private String num, name, type, crop, addr, crea, ncjj;
    private String nh = "";

    private String farmerId;
    private Map<String, String> map = new ArrayMap<>();

    private Dialog progressDialog;


    @OnClick({R.id.title_cotent, R.id.ntlx_ll})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_cotent:
                ifExitView();
                break;
            case R.id.ntlx_ll:
                if (opvtype != null) {
                    opvtype.show();
                }
                break;
            case R.id.nh_ll:
                if (opvnh != null) {
                    opvnh.show();
                }
                break;
        }
    }


    @Override
    public int getContentViewId() {
        return R.layout.activity_addfield;
    }

    @Override
    public void initViews() {
        setTitle();
        setTypePickerView();
    }

    protected void setTitle() {
        tvContent.setVisibility(View.VISIBLE);
        tvContent.setText(JctlApplication.getAppResources().getString(R.string.mine_wc));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//不显示自带标题
        toolbar.setCenterTitle(JctlApplication.getAppResources().getString(R.string.tv_tjnt));
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

    }

    @Override
    protected void onResume() {
        super.onResume();
        //		String uid=JctlApplication.getCache().getAsString(KeyConstants.KAY_USERID);
        //		if (!TextUtils.isEmpty(uid)){
        //			mPresenter.setAddField(uid);
        //		}
    }

    @Override
    public void initData() {
        farmerId = getIntent().getStringExtra("farmerId");
        typelist.add("大棚");
        typelist.add("农田");
        if (!"".equals(KeyConstants.USER_SINGLEID)) {
            mPresenter.setAddField(KeyConstants.USER_SINGLEID);
        }
    }

    protected void ifExitView() {
        if (!"".equals(etNum.getText().toString().trim())) {
            num = etNum.getText().toString().trim();
        } else {
            ToastUtils.showLong("请填写农田编号");
            return;
        }
        if (!"".equals(etName.getText().toString().trim())) {
            name = etName.getText().toString().trim();
        } else {
            ToastUtils.showLong("请填写农田名");
            return;
        }

        if (!"".equals(nhTv.getText().toString().trim())) {
            nh = nhTv.getText().toString().trim();
        }
        //		else {
        //			ToastUtils.showLong("请选择农户");
        //			return;
        //		}

        if (!"".equals(lxTv.getText().toString().trim())) {
            type = lxTv.getText().toString().trim();
        } else {
            ToastUtils.showLong("请选择农田类型");
            return;
        }

        if (!"".equals(etCrop.getText().toString().trim())) {
            crop = etCrop.getText().toString().trim();
        } else {
            ToastUtils.showLong("请填写农田作物");
            return;
        }


        if (!"".equals(etArea.getText().toString().trim())) {
            crea = etArea.getText().toString().trim();
        } else {
            ToastUtils.showLong("请填写农田面积");
            return;
        }

        if (!"".equals(etAddr.getText().toString().trim())) {
            addr = etAddr.getText().toString().trim();
        }

        if (!"".equals(etNcjj.getText().toString().trim())) {
            ncjj = etNcjj.getText().toString().trim();
        }
        //		@Query("singleId")String uid,
        //		@Query("farmerId")String farmerId,
        //		@Query("farmlandNum")String num,
        //		@Query("alias") String alias,
        //		@Query("usedName") String name,
        //		@Query("landType") String type,
        //		@Query("plantVariety") String plantVariety,
        //		@Query("addr") String addr,
        //		@Query("area") Double area,
        //		@Query("remarks") String remarks
        map.clear();
        map.put("singleId", KeyConstants.USER_SINGLEID);
        map.put("farmerId", farmerId);
        map.put("farmlandNum", num);
        map.put("alias", name);
        if (nh != null && !"".equals(nh)) {
            map.put("usedName", nh);
        }
        map.put("landType", typeInt);
        map.put("plantVaritety", crop);
        map.put("addr", addr);
        map.put("area", crea);
        if (ncjj != null && !"".equals(ncjj)) {
            map.put("remarks", ncjj);
        }
        mPresenter.setAddData(map);


    }

    //	private void initCustomTimePicker() {
    //		// 注意：自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针
    //		// 具体可参考demo 里面的两个自定义布局
    //		//因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
    //		//控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
    //		Calendar selectedDate = Calendar.getInstance();//系统当前时间
    //		Calendar startDate = Calendar.getInstance();
    //		startDate.set(2014,1,23);
    //		Calendar endDate = Calendar.getInstance();
    //		endDate.set(2027,2,28);
    //		//时间选择器 ，自定义布局
    //		pvCustomTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
    //			@Override
    //			public void onTimeSelect(Date date, View v) {//选中事件回调
    //				btn_CustomTime.setText(getTime(date));
    //			}
    //		})
    //				.setDate(selectedDate)
    //				.setRangDate(startDate,endDate)
    //				.setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {
    //
    //					@Override
    //					public void customLayout(View v) {
    //						final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
    //						//						ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
    //						tvSubmit.setOnClickListener(new View.OnClickListener() {
    //							@Override
    //							public void onClick(View v) {
    //								pvCustomTime.returnData(tvSubmit);
    //							}
    //						});
    //						//						ivCancel.setOnClickListener(new View.OnClickListener() {
    //						//							@Override
    //						//							public void onClick(View v) {
    //						//								pvCustomTime.dismiss();
    //						//							}
    //						//						});
    //					}
    //				})
    //				.setDividerColor(Color.BLACK)
    //				.build();
    //
    //	}
    private String getTime(Date date) {//可根据需要自行截取数据显示
        //        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }


    public void setTypePickerView() {
        opvtype = new OptionsPickerView.Builder(AddFieldActivity.this, listener)
                .setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tvFinish = (TextView) v.findViewById(R.id.tv_finish);
                        TextView prickerviewTitle = (TextView) v.findViewById(R.id.prickerview_title);
                        prickerviewTitle.setText("农田类型");
                        tvFinish.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                opvtype.returnData();
                            }
                        });

                    }
                })

                .setDividerColor(JctlApplication.getAppResources().getColor(R.color.color_CECECE))
                .setTextColorCenter(JctlApplication.getAppResources().getColor(R.color.color_3AB07D)) //设置选中项文字颜色
                .setContentTextSize(15)
                .isDialog(true)
                .build();
        opvtype.setPicker(typelist);

    }

    private OptionsPickerView.OnOptionsSelectListener listener = new OptionsPickerView.OnOptionsSelectListener() {
        @Override
        public void onOptionsSelect(int options1, int options2, int options3, View v) {
            String tx = typelist.get(options1);
            if (tx.equals("大棚")) {
                typeInt = "0";
            } else {
                typeInt = "1";
            }
            lxTv.setText(tx);
        }
    };

    @Override
    public void showDialog() {
        if (progressDialog == null) {
            progressDialog = new Dialog(AddFieldActivity.this, R.style.progress_dialog);
            progressDialog.setContentView(R.layout.dialog);
            progressDialog.setCancelable(true);
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            TextView msg = (TextView) progressDialog.findViewById(R.id.id_tv_loadingmsg);
            msg.setText("请稍后...");
            progressDialog.show();
        }
    }

    @Override
    public void onSucceedUserId(AddFieldItem data) {
        if (data.getsList().size() > 0) {
            nList = data.getsList();
            idList = data.getIdList();
            setNhTypePickerView();
        }

    }

    @Override
    public void onSucceedAdd(AddFieldData data) {
        if ("1".equals(data.getFlag())) {
            ToastUtils.showLong(data.getMsg());
            AppManager.newInstance().finishCurrentActivity();
        }

    }

    @Override
    public void onSucceedJson(JsonBeanList data) {

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
    protected AddFieldPresenter onCreatePresenter() {
        return new AddFieldPresenter(this);
    }

    public void setNhTypePickerView() {
        opvnh = new OptionsPickerView.Builder(AddFieldActivity.this, listenerNh)
                .setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tvFinish = (TextView) v.findViewById(R.id.tv_finish);
                        TextView prickerviewTitle = (TextView) v.findViewById(R.id.prickerview_title);
                        prickerviewTitle.setText("农户");
                        tvFinish.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                opvnh.returnData();
                            }
                        });

                    }
                })

                .setDividerColor(JctlApplication.getAppResources().getColor(R.color.color_CECECE))
                .setTextColorCenter(JctlApplication.getAppResources().getColor(R.color.color_3AB07D)) //设置选中项文字颜色
                .setContentTextSize(15)
                .isDialog(true)
                .build();
        opvnh.setPicker(nList);

    }

    private OptionsPickerView.OnOptionsSelectListener listenerNh = new OptionsPickerView.OnOptionsSelectListener() {
        @Override
        public void onOptionsSelect(int options1, int options2, int options3, View v) {
            String tx = nList.get(options1);
            nhTv.setText(tx);
        }
    };
}
