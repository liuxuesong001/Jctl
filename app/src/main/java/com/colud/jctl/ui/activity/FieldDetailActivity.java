package com.colud.jctl.ui.activity;

import android.app.Dialog;
import android.os.Build;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.colud.jctl.JctlApplication;
import com.colud.jctl.api.KeyConstants;
import com.colud.jctl.base.BaseActivity;
import com.colud.jctl.bean.AddFieldData;
import com.colud.jctl.bean.FieldDetailItem;
import com.colud.jctl.bean.RateDialogItem;
import com.colud.jctl.mvp.contract.FieldDetailContract;
import com.colud.jctl.mvp.presenter.FieldDetailPresenter;
import com.colud.jctl.base.AppManager;
import com.colud.jctl.ui.custom.TitleBar;
import com.colud.jctl.ui.custom.dialog.RateDialog;
import com.colud.jctl.utils.BarStatusHeightUtil;
import com.colud.jctl.utils.EditTextUtil;
import com.colud.jctl.utils.ToastUtils;
import com.jctl.colud.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jctl.colud.R.id.et_addr;


/**
 * 农田详情
 * Created by Jcty on 2017/4/1.
 */

public class FieldDetailActivity extends BaseActivity<FieldDetailPresenter> implements FieldDetailContract.View {


    @BindView(R.id.title_cotent)
    TextView tvContent;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.ncz)
    TextView ncz;
    @BindView(R.id.nh)
    TextView nh;
    //	@BindView(R.id.et_nh)
    //	EditText etNh;
    @BindView(R.id.lx)
    TextView lx;
    @BindView(R.id.crop)
    TextView crop;
    @BindView(R.id.et_crop)
    EditText etCrop;
    @BindView(R.id.fpsj)
    TextView fpsj;
    @BindView(R.id.sszj)
    TextView sszj;
    @BindView(R.id.jdsl)
    TextView jdsl;
    @BindView(R.id.area)
    TextView area;
    @BindView(R.id.et_area)
    EditText etArea;
    @BindView(R.id.btm_delete)
    LinearLayout btmDelete;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.num)
    TextView num;
    @BindView(R.id.lx_ll)
    LinearLayout lxLl;
    @BindView(R.id.addr)
    TextView addr;
    @BindView(et_addr)
    EditText etAddr;
    @BindView(R.id.et_ncjj)
    EditText etNcjj;
    @BindView(R.id.ncjj)
    TextView ncjj;
    @BindView(R.id.et_ncz)
    EditText etNcz;
    @BindView(R.id.lx_img)
    ImageView lxImg;
    @BindView(R.id.title_toolbar)
    TitleBar toolbar;

    private String id;

    private OptionsPickerView opvtype;

    private String typeInt = "";

    private List<String> typelist = new ArrayList<>();

    private Map<String, String> map = new ArrayMap<>();

    private Dialog progressDialog;

    private String farmName = "";
    private String farmNum = "";
    private String farmCrop = "";
    private String farmAddr = "";
    private String farmArea = "";
    private String farmNcjj = "";
    private RateDialogItem dialogItem;
    private RateDialog dialog;


    @OnClick({R.id.btm_delete, R.id.title_cotent})
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.lx_ll:
                if ("完成".equals(tvContent.getText().toString()) && opvtype != null) {
                    opvtype.show();
                }
                break;
            case R.id.ll_back:
                AppManager.newInstance().finishCurrentActivity();
                break;
            case R.id.btn_back:
                AppManager.newInstance().finishCurrentActivity();
                break;
            case R.id.title_cotent:
                if ("编辑".equals(tvContent.getText().toString())) {
                    tvContent.setText("完成");
                    btmDelete.setVisibility(View.VISIBLE);

                    name.setVisibility(View.GONE);
                    ncz.setVisibility(View.GONE);
                    crop.setVisibility(View.GONE);
                    area.setVisibility(View.GONE);
                    addr.setVisibility(View.GONE);
                    ncjj.setVisibility(View.GONE);

                    etCrop.setVisibility(View.VISIBLE);
                    etArea.setVisibility(View.VISIBLE);
                    etNcz.setVisibility(View.VISIBLE);
                    etName.setVisibility(View.VISIBLE);
                    //					etNh.setVisibility(View.VISIBLE);
                    etNcjj.setVisibility(View.VISIBLE);
                    etAddr.setVisibility(View.VISIBLE);
                    lxImg.setVisibility(View.VISIBLE);
                    EditTextUtil.showSoftInputFromWindow(this, etName);
                } else {
                    etInputData();
                }
                break;
            case R.id.btm_delete:
                //				@Query("id")String id
                dialogItem.setTvTitle("确定删除？");
                dialog = new RateDialog(this, R.style.MyDialog, dialogItem);
                dialog.setMyClickListener(new RateDialog.onClickRateDialog() {
                    @Override
                    public void onClickLeft() {
                        dialog.dismiss();
                    }

                    @Override
                    public void onClickRight() {
                        map.clear();
                        if (!"".equals(id) && id != null) {
                            map.put("id", id);
                            mPresenter.setDeleteData(map);
                        }
                    }
                });
                dialog.show();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     *
     */
    protected void etInputData() {
        if (!"".equals(etName.getText().toString().trim()) && !TextUtils.isEmpty(etName.getText().toString().trim())) {
            farmName = etName.getText().toString().trim();
        } else {
            ToastUtils.showLong("请填写农田名");
            return;
        }
        if (!"".equals(etCrop.getText().toString().trim()) && !TextUtils.isEmpty(etCrop.getText().toString().trim())) {
            farmCrop = etCrop.getText().toString().trim();
        } else {
            ToastUtils.showLong("请填写作物");
            return;
        }
        if (!"".equals(etAddr.getText().toString().trim()) && !TextUtils.isEmpty(etAddr.getText().toString().trim())) {
            farmAddr = etAddr.getText().toString().trim();
        } else {
            ToastUtils.showLong("请填写地址");
            return;
        }
        if (!"".equals(etArea.getText().toString().trim()) && !TextUtils.isEmpty(etArea.getText().toString().trim())) {
            farmArea = etArea.getText().toString().trim();
        } else {
            ToastUtils.showLong("请填写面积(亩)");
            return;
        }
        if (!"".equals(etNcjj.getText().toString().trim()) && !TextUtils.isEmpty(etNcjj.getText().toString().trim())) {
            farmNcjj = etNcjj.getText().toString().trim();
        }
        map.clear();

        map.put("singleId", KeyConstants.USER_SINGLEID);
        map.put("id", id);
        //		map.put("usedName",etNh.getText().toString().trim());
        map.put("landType", typeInt);
        map.put("alias", farmName);
        map.put("plantVaritety", farmCrop);
        map.put("addr", farmAddr);
        map.put("area", farmArea);
        map.put("remarks", farmNcjj);
        mPresenter.setFieldDataUpdate(map);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_managefield;
    }

    @Override
    public void initViews() {
        dialogItem = new RateDialogItem();
        setTitle();
        setTypePickerView();
    }

    protected void setTitle() {
        tvContent.setVisibility(View.VISIBLE);
        tvContent.setText(JctlApplication.getAppResources().getString(R.string.mine_bj));
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

    }

    @Override
    public void initData() {
        //		map.put("id",id);
        //		map.put("singleId", KeyConstants.KAY_USERID);
        map.clear();
        id = getIntent().getStringExtra("id");
        if (id != null && !"".equals(id)) {
            map.put("singleId", KeyConstants.USER_SINGLEID);
            map.put("id", id);
            mPresenter.setFieldData(map);
        }
        typelist.add("大棚");
        typelist.add("农田");

    }

    @Override
    protected FieldDetailPresenter onCreatePresenter() {
        return new FieldDetailPresenter(this);
    }

    @Override
    public void showDialog() {
        if (progressDialog == null) {
            progressDialog = new Dialog(FieldDetailActivity.this, R.style.progress_dialog);
            progressDialog.setContentView(R.layout.dialog);
            progressDialog.setCancelable(true);
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            TextView msg = (TextView) progressDialog.findViewById(R.id.id_tv_loadingmsg);
            msg.setText("请稍后...");
            progressDialog.show();
        }
    }

    @Override
    public void onSucceed(FieldDetailItem data) {
        if (data != null) {
            //农田编号
            if (!TextUtils.isEmpty(data.getInfoBean().getFarmlandNum())) {
                num.setText(data.getInfoBean().getFarmlandNum());
            }
            //别名
            if (!TextUtils.isEmpty(data.getInfoBean().getAlias())) {
                toolbar.setCenterTitle(data.getInfoBean().getAlias());
                name.setText(data.getInfoBean().getAlias());
                etName.setText(data.getInfoBean().getAlias());
            } else {
                toolbar.setCenterTitle(JctlApplication.getAppResources().getString(R.string.tv_field_name));
            }
            //农场主
            if (!TextUtils.isEmpty(data.getInfoBean().getUsedName())) {
                ncz.setText(data.getInfoBean().getUsedName());
                etNcz.setText(data.getInfoBean().getUsedName());
            }
            //农户
            if (data.getInfoBean().getUsedName() != null && !TextUtils.isEmpty(data.getInfoBean().getUsedName())) {
                nh.setText(data.getInfoBean().getUsedName());
                //				etNh.setText(data.getInfoBean().getUser().getName());
            }
            //类型 0是大棚 1是农田
            if (!TextUtils.isEmpty(data.getInfoBean().getLandType())) {
                if (data.getInfoBean().getLandType().equals("0")) {
                    lx.setText("大棚");
                    typeInt = "0";
                } else {
                    typeInt = "1";
                    lx.setText("农田");
                }
            } else {
                lx.setText("请选择农田类型");
            }
            //作物
            if (!TextUtils.isEmpty(data.getInfoBean().getPlantVaritety())) {
                crop.setText(data.getInfoBean().getPlantVaritety());
                etCrop.setText(data.getInfoBean().getPlantVaritety());
            }
            //分配时间
            if (!TextUtils.isEmpty(String.valueOf(data.getInfoBean().getAssigneTime()))) {
                fpsj.setText(String.valueOf(data.getInfoBean().getAssigneTime()));
            }
            //			//所属中继
            //			if (!TextUtils.isEmpty(data.getInfoBean().getRelay().getRelayNum())){
            //				sszj.setText(data.getInfoBean().getRelay().getRelayNum());
            //			}
            //节点数量
            if (!TextUtils.isEmpty(data.getInfoBean().getNodeNumber())) {
                jdsl.setText(data.getInfoBean().getNodeNumber());
            }
            //地址
            if (!TextUtils.isEmpty(data.getInfoBean().getAddr())) {
                addr.setText(data.getInfoBean().getAddr());
                etAddr.setText(data.getInfoBean().getAddr());
            }
            //面积
            if (!TextUtils.isEmpty(String.valueOf(data.getInfoBean().getArea()))) {
                area.setText(String.valueOf(data.getInfoBean().getArea()));
                etArea.setText(String.valueOf(data.getInfoBean().getArea()));
            }
            //农场简介
            if (!TextUtils.isEmpty(data.getInfoBean().getRemarks())) {
                etNcjj.setText(data.getInfoBean().getRemarks());
                ncjj.setText(data.getInfoBean().getRemarks());
            }
        }


    }

    @Override
    public void onSucceedUpdate(AddFieldData data) {
        if ("1".equals(data.getFlag())) {
            ToastUtils.showLong(data.getMsg());
            AppManager.newInstance().finishCurrentActivity();
        }
    }

    @Override
    public void onSucceedDelete(FieldDetailItem data) {
        ToastUtils.showLong(data.getMsg());
        AppManager.newInstance().finishCurrentActivity();
    }

    @Override
    public void onFail(String err) {

    }

    @Override
    public void onFailure(String err, Throwable e) {

    }

    @Override
    public void hideDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    public void setTypePickerView() {
        opvtype = new OptionsPickerView.Builder(FieldDetailActivity.this, listener)
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
            lx.setText(tx);
        }
    };
}
