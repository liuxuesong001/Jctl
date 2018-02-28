package com.colud.jctl.ui.activity;

import android.app.Dialog;
import android.os.Build;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
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
import com.colud.jctl.bean.FarmDetailItem;
import com.colud.jctl.bean.RateDialogItem;
import com.colud.jctl.utils.GetJsonDataUtil;
import com.colud.jctl.mvp.contract.FarmDetailContract;
import com.colud.jctl.mvp.presenter.FarmDetailPresenter;
import com.colud.jctl.base.AppManager;
import com.colud.jctl.ui.custom.TitleBar;
import com.colud.jctl.ui.custom.dialog.RateDialog;
import com.colud.jctl.utils.BarStatusHeightUtil;
import com.colud.jctl.utils.EditTextUtil;
import com.colud.jctl.utils.ToastUtils;
import com.jctl.colud.R;
import com.socks.library.KLog;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 农场详情
 * Created by Jcty on 2017/4/1.
 */

public class FarmDetailActivity extends BaseActivity<FarmDetailPresenter> implements FarmDetailContract.View {

    @BindView(R.id.title_cotent)
    TextView tvContent;
    @BindView(R.id.tv_farmdetail_name)
    TextView tvFarmName;
    @BindView(R.id.tv_farmdetail_num)
    TextView tvFarmNum;
    @BindView(R.id.tv_farmdetail_address)
    TextView tvFarmAddress;
    @BindView(R.id.tv_farmdetail_area)
    TextView tvFarmArea;
    @BindView(R.id.tv_farmdetail_kind)
    TextView tvFarmKind;
    @BindView(R.id.farmdetail_ll)
    LinearLayout framdetailLl;
    @BindView(R.id.tv_farmdetail_whom)
    TextView tvFarmWhom;
    @BindView(R.id.tv_farmdetail_count)
    TextView tvFarmCount;
    @BindView(R.id.tv_relaydetail_num)
    TextView tvRelayNum;
    @BindView(R.id.btm_delete)
    LinearLayout btmDelete;
    @BindView(R.id.et_farm_kind)
    EditText etFarmKind;
    @BindView(R.id.et_farm_name)
    EditText etFarmName;
    @BindView(R.id.et_farm_num)
    EditText etFarmNum;
    @BindView(R.id.et_farm_area)
    EditText etFarmArea;
    //	@BindView(R.id.et_farm_whom)
    //	EditText etFarmWhom;
    @BindView(R.id.farm_address_ll)
    LinearLayout lladdRess;
    @BindView(R.id.et_ncjj)
    EditText etNcjj;
    @BindView(R.id.ncjj)
    TextView ncjj;

    @BindView(R.id.title_toolbar)
    TitleBar toolbar;

    private boolean isState = false;


    private OptionsPickerView addRessPv;

    private RateDialogItem dialogItem;
    private RateDialog dialog;
    private String id;
    private Map<String, String> map = new ArrayMap<>();

    private String farmName = "";


    private String farmId, name, num, addr, area, kind, whom, count, relay;

    private Dialog dlog;

    private TextView msg;


    @OnClick({R.id.farm_address_ll, R.id.btm_delete, R.id.title_cotent})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                AppManager.newInstance().finishCurrentActivity();
                break;
            case R.id.btn_back:
                AppManager.newInstance().finishCurrentActivity();
                break;
            case R.id.title_cotent:
                if ("编辑".equals(tvContent.getText().toString())) {
                    isState = true;
                    tvContent.setText("完成");
                    tvFarmKind.setVisibility(View.GONE);
                    etFarmKind.setVisibility(View.VISIBLE);
                    btmDelete.setVisibility(View.VISIBLE);
                    tvFarmName.setVisibility(View.GONE);
                    etFarmName.setVisibility(View.VISIBLE);
                    tvFarmNum.setVisibility(View.GONE);
                    etFarmNum.setVisibility(View.VISIBLE);
                    tvFarmArea.setVisibility(View.GONE);
                    etFarmArea.setVisibility(View.VISIBLE);
                    tvFarmCount.setVisibility(View.GONE);
                    tvRelayNum.setVisibility(View.GONE);
                    ncjj.setVisibility(View.GONE);
                    etNcjj.setVisibility(View.VISIBLE);
                    btmDelete.setVisibility(View.VISIBLE);
                    EditTextUtil.showSoftInputFromWindow(this, etFarmName);
                } else {
                    isState = false;
                    etFarmName.setVisibility(View.VISIBLE);
                    etFarmNum.setVisibility(View.VISIBLE);
                    etFarmArea.setVisibility(View.VISIBLE);
                    //					etFarmWhom.setVisibility(View.VISIBLE);
                    etFarmKind.setVisibility(View.VISIBLE);
                    ifSubmitData();
                }

                break;
            case R.id.btm_delete:
                dialogItem.setTvTitle("确定删除？");
                dialog = new RateDialog(this, R.style.MyDialog, dialogItem);
                dialog.setMyClickListener(new RateDialog.onClickRateDialog() {
                    @Override
                    public void onClickLeft() {
                        dialog.dismiss();
                    }

                    @Override
                    public void onClickRight() {
                        mPresenter.setDeleteData(farmId);
                    }
                });
                dialog.show();
                break;
            case R.id.farm_address_ll:
                if (isState && addRessPv != null) {
                    addRessPv.show();
                }
                break;
        }
    }

    /**
     * 判断填写数据
     */
    private void ifSubmitData() {
        if (!TextUtils.isEmpty(etFarmName.getText().toString().trim())) {
            name = etFarmName.getText().toString().trim();
        } else {
            ToastUtils.showShort("请填写农场名称");
            return;
        }
        if (!TextUtils.isEmpty(etFarmNum.getText().toString().trim())) {
            num = etFarmNum.getText().toString().trim();
        } else {
            ToastUtils.showShort("请填写农场编号");
            return;
        }
        if (!TextUtils.isEmpty(tvFarmAddress.getText().toString().trim())) {
            addr = tvFarmAddress.getText().toString().trim();
        } else {
            ToastUtils.showShort("请选择农场地址");
            return;
        }
        if (!TextUtils.isEmpty(etFarmArea.getText().toString().trim())) {
            area = etFarmArea.getText().toString().trim();
        } else {
            ToastUtils.showShort("请填写农场面积(亩)");
            return;
        }
        if (!TextUtils.isEmpty(etFarmKind.getText().toString().trim())) {
            kind = etFarmKind.getText().toString().trim();
        } else {
            ToastUtils.showShort("请填写作物种类");
            return;
        }
        //		if (!TextUtils.isEmpty(etFarmWhom.getText().toString().trim())){
        //			whom=etFarmWhom.getText().toString().trim();
        //		}else{
        //			ToastUtils.showShort("请填写所属人");
        //			return;
        //		}
        map.clear();
        map.put("id", id);
        map.put("singleId", KeyConstants.USER_SINGLEID);
        map.put("farmerNum", num);
        map.put("name", name);
        map.put("addr", addr);
        map.put("area", area);
        map.put("plantVariety", kind);
        //		map.put("user.name",whom);
        if (!"".equals(etNcjj.getText().toString().trim())) {
            map.put("remarks", etNcjj.getText().toString().trim());
        }
        mPresenter.setFarmDataUpdate(map);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_farmdetail;
    }

    @Override
    public void initViews() {
        setTitle();
        dialogItem = new RateDialogItem();
    }

    protected void setTitle() {
        tvContent.setVisibility(View.VISIBLE);
        tvContent.setText(JctlApplication.getAppResources().getString(R.string.mine_bj));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//不显示自带标题
        if (farmName != null && !"".equals(farmName)) {
            toolbar.setCenterTitle(farmName);
        } else {
            toolbar.setCenterTitle(JctlApplication.getAppResources().getString(R.string.tv_farm_xq));
        }

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

    }

    @Override
    public void initData() {
        //            @Query("id") String uid,
        //            @Query("singleId")String singleId
        map.clear();
        addRJson();
        id = getIntent().getStringExtra("id");
        farmName = getIntent().getStringExtra("farmName");
        if (id != null && !"".equals(id)) {
            map.put("singleId", KeyConstants.USER_SINGLEID);
            map.put("id", id);
            mPresenter.setFarmData(map);
        }
    }

    protected void addRJson() {
        JsonBeanList addJs = (JsonBeanList) JctlApplication.getCache().getAsObject(KeyConstants.KAY_ADDFARM_ADDRESS);
        if (addJs != null && !TextUtils.isEmpty(addJs.getAddress())) {
            setAddressPickerView(addJs);
        } else {
            addJs = new JsonBeanList();
            String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据
            addJs.setAddress(JsonData);
            mPresenter.setAddRessJson(addJs);
        }
    }

    @Override
    public void showDialog() {
        //		JctlApplication.getGifLoadingView().show(getFragmentManager(),"");
        if (dlog == null) {
            dlog = new Dialog(FarmDetailActivity.this, R.style.progress_dialog);
            dlog.setContentView(R.layout.dialog);
            dlog.setCancelable(true);
            dlog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            msg = (TextView) dlog.findViewById(R.id.id_tv_loadingmsg);
            msg.setText(JctlApplication.getAppResources().getString(R.string.please_wait));
            dlog.show();
        }

    }

    @Override
    public void onSucceed(FarmDetailItem data) {
        if (!TextUtils.isEmpty(data.getInfoBean().getId())) {
            farmId = data.getInfoBean().getId();
        }

        if (!TextUtils.isEmpty(data.getInfoBean().getName())) {
            tvFarmName.setText(data.getInfoBean().getName());
            etFarmName.setText(data.getInfoBean().getName());
        }
        if (!TextUtils.isEmpty(data.getInfoBean().getFarmerNum())) {
            tvFarmNum.setText(data.getInfoBean().getFarmerNum());
        }
        if (!TextUtils.isEmpty(data.getInfoBean().getAddr())) {
            tvFarmAddress.setText(data.getInfoBean().getAddr());
        }
        if (!TextUtils.isEmpty(String.valueOf(data.getInfoBean().getArea()))) {
            tvFarmArea.setText(String.valueOf(data.getInfoBean().getArea()));
            etFarmArea.setText(String.valueOf(data.getInfoBean().getArea()));
        }

        if (!TextUtils.isEmpty(data.getInfoBean().getPlantVariety())) {
            tvFarmKind.setText(data.getInfoBean().getPlantVariety());
        }
        if (!TextUtils.isEmpty(data.getInfoBean().get_$UserName201())) {
            tvFarmWhom.setText(data.getInfoBean().get_$UserName201());
            //			etFarmWhom.setText(data.getInfoBean().get_$UserName201());
        }
        if (!TextUtils.isEmpty(data.getInfoBean().getFarmlandNumber())) {
            tvFarmCount.setText(data.getInfoBean().getFarmlandNumber());
        }

        if (!TextUtils.isEmpty(data.getInfoBean().getRelayNumber())) {
            tvRelayNum.setText(data.getInfoBean().getRelayNumber());
        }


        if (!TextUtils.isEmpty(data.getInfoBean().getFarmerNum())) {
            etFarmNum.setText(data.getInfoBean().getFarmerNum());
        }

        if (!TextUtils.isEmpty(data.getInfoBean().getPlantVariety())) {
            etFarmKind.setText(data.getInfoBean().getPlantVariety());
        }
        if (!TextUtils.isEmpty(data.getInfoBean().getRemarks())) {
            ncjj.setText(data.getInfoBean().getRemarks());
            etNcjj.setText(data.getInfoBean().getRemarks());
        }
    }

    @Override
    public void onSucceedJson(JsonBeanList data) {
        if (data != null) {
            setAddressPickerView(data);
        }
    }

    @Override
    public void onFailure(String err, Throwable e) {

    }

    @Override
    public void onSucceedUpdate(FarmDetailItem data) {
        if (data != null && "1".equals(data.getFlag())) {
            tvContent.setText("编辑");
            ToastUtils.showShort(data.getMsg());
        } else {
            ToastUtils.showShort(data.getMsg());
        }

    }

    @Override
    public void onSucceedDelete(FarmDetailItem data) {
        if (data != null && "1".equals(data.getFlag())) {
            ToastUtils.showShort(data.getMsg());
            AppManager.newInstance().finishCurrentActivity();
        } else {
            ToastUtils.showShort(data.getMsg());
        }
        dialog.dismiss();
    }

    @Override
    public void onFail(String err) {
        //		dialog.dismiss();
        KLog.d(err);
    }

    @Override
    public void hideDialog() {
        //		JctlApplication.getGifLoadingView().dismiss();
        if (dlog != null && dlog.isShowing()) {
            dlog.dismiss();
            dlog = null;
        }
    }

    @Override
    protected FarmDetailPresenter onCreatePresenter() {
        return new FarmDetailPresenter(this);
    }

    public void setAddressPickerView(final JsonBeanList data) {
        addRessPv = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = data.getOptions1Items().get(options1).getPickerViewText() +
                        data.getOptions2Items().get(options1).get(options2) +
                        data.getOptions3Items().get(options1).get(options2).get(options3);
                tvFarmAddress.setText(tx);
            }
        })
                .setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tvFinish = (TextView) v.findViewById(R.id.tv_finish);
                        TextView prickerviewTitle = (TextView) v.findViewById(R.id.prickerview_title);
                        prickerviewTitle.setText("地址");
                        tvFinish.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addRessPv.returnData();
                            }
                        });

                    }
                })

                .setDividerColor(JctlApplication.getAppResources().getColor(R.color.color_CECECE))
                .setTextColorCenter(JctlApplication.getAppResources().getColor(R.color.color_3AB07D)) //设置选中项文字颜色
                .setContentTextSize(15)
                .isDialog(true)
                .build();

        addRessPv.setPicker(data.getOptions1Items(), data.getOptions2Items(), data.getOptions3Items());//三级选择器
    }
}
