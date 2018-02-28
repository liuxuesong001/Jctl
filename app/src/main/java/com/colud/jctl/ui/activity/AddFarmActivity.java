package com.colud.jctl.ui.activity;

import android.app.Dialog;
import android.os.Build;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.colud.jctl.JctlApplication;
import com.colud.jctl.api.KeyConstants;
import com.colud.jctl.base.BaseActivity;
import com.colud.jctl.bean.JsonBeanList;
import com.colud.jctl.bean.AddFarmItem;
import com.colud.jctl.utils.GetJsonDataUtil;
import com.colud.jctl.mvp.contract.AddFarmContract;
import com.colud.jctl.mvp.presenter.AddFarmPresenter;
import com.colud.jctl.base.AppManager;
import com.colud.jctl.ui.custom.TitleBar;
import com.colud.jctl.utils.BarStatusHeightUtil;
import com.colud.jctl.utils.ToastUtils;
import com.jctl.colud.R;
import com.socks.library.KLog;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jctl.colud.R.id.tv_kindaddRess;

/**
 * 添加农场
 * Created by Jcty on 2017/3/29.
 */

public class AddFarmActivity extends BaseActivity<AddFarmPresenter> implements AddFarmContract.View {

    @BindView(R.id.title_cotent)
    TextView tvContent;
    @BindView(R.id.addfarm_address_rl)
    RelativeLayout addfarmAddressRl;
    @BindView(R.id.et_addfarm_kind)
    EditText tvKindContent;
    @BindView(tv_kindaddRess)
    TextView tvKindaddress;
    @BindView(R.id.et_addfarm_num)
    EditText etAddfarmNum;
    @BindView(R.id.et_addfarm_name)
    EditText etAddfarmName;
    @BindView(R.id.et_addfarm_crea)
    EditText etAddfarmCrea;
    @BindView(R.id.et_jj)
    EditText etJj;
    @BindView(R.id.title_toolbar)
    TitleBar toolbar;

    private String etNum, etName, etCrea, tvKind, tvAddress;

    private int isType;


    private OptionsPickerView addRessPv;

    private Map<String, String> map = new ArrayMap<>();

    private Dialog dialog;


    @OnClick({R.id.title_cotent, R.id.addfarm_kind_rl, R.id.addfarm_address_rl})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_cotent:
                if (!TextUtils.isEmpty(etAddfarmNum.getText().toString().trim())) {
                    etNum = etAddfarmNum.getText().toString().trim();
                } else {
                    ToastUtils.showShort("请输入农场编号");
                    return;
                }
                if (!TextUtils.isEmpty(etAddfarmName.getText().toString().trim())) {
                    etName = etAddfarmName.getText().toString().trim();
                } else {
                    ToastUtils.showShort("请输入农场名称");
                    return;
                }
                if (!TextUtils.isEmpty(etAddfarmCrea.getText().toString().trim())) {
                    etCrea = etAddfarmCrea.getText().toString().trim();
                } else {
                    ToastUtils.showShort("请输入农场面积(亩)");
                    return;
                }
                if (!TextUtils.isEmpty(tvKindContent.getText().toString().trim())) {
                    tvKind = tvKindContent.getText().toString().trim();
                } else {
                    ToastUtils.showShort("请输入种植种类");
                    return;
                }
                if (!TextUtils.isEmpty(tvKindaddress.getText().toString().trim())) {
                    tvAddress = tvKindaddress.getText().toString().trim();
                } else {
                    ToastUtils.showShort("请选择地址");
                    return;
                }
                //            @Query("singleId")String userId,
                //            @Query("farmerNum") String farmerNum,
                //            @Query("name") String name,
                //            @Query("area") String area,
                //            @Query("plantVariety") String plantVariety,
                //            @Query("addr") String addr
                map.clear();
                map.put("singleId", KeyConstants.USER_SINGLEID);
                map.put("farmerNum", etNum);
                map.put("name", etName);
                map.put("area", etCrea);
                map.put("plantVariety", tvKind);
                map.put("addr", tvAddress);
                if (!"".equals(etJj.getText().toString().trim())) {
                    map.put("remarks", etJj.getText().toString().trim());
                }
                mPresenter.setAddFarmata(map);
                break;
            case R.id.addfarm_address_rl:
                if (addRessPv != null) {
                    addRessPv.show();
                }
                break;
            default:
        }
    }


    @Override
    public int getContentViewId() {
        return R.layout.activity_addfarm;
    }

    @Override
    public void initViews() {
        //		pickerView=new DialogPickerView(this,listener,null);
        setTitle();
    }

    protected void setTitle() {
        tvContent.setVisibility(View.VISIBLE);
        tvContent.setText(JctlApplication.getAppResources().getString(R.string.mine_wc));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//不显示自带标题
        toolbar.setCenterTitle(JctlApplication.getAppResources().getString(R.string.tv_add_farm));
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
        if (dialog == null) {
            dialog = new Dialog(AddFarmActivity.this, R.style.progress_dialog);
            dialog.setContentView(R.layout.dialog);
            dialog.setCancelable(true);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            TextView msg = (TextView) dialog.findViewById(R.id.id_tv_loadingmsg);
            msg.setText("请稍后...");
            dialog.show();

        }

    }

    @Override
    public void onSucceed(AddFarmItem data) {
        if (!TextUtils.isEmpty(data.getMsg())) {
            ToastUtils.showShort(data.getMsg());
            AppManager.newInstance().finishCurrentActivity();
        }
    }

    @Override
    public void onFailure(String err, Throwable e) {

    }


    @Override
    public void onSucceedJson(JsonBeanList data) {
        if (data != null) {
            setAddressPickerView(data);
        }
    }

    @Override
    public void onFail(String err) {
        KLog.d(err);
    }

    @Override
    public void hideDialog() {
        //		JctlApplication.getGifLoadingView().dismiss();
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
    }

    @Override
    protected AddFarmPresenter onCreatePresenter() {
        return new AddFarmPresenter(this);
    }

    public void setAddressPickerView(final JsonBeanList data) {
        addRessPv = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = data.getOptions1Items().get(options1).getPickerViewText() +
                        data.getOptions2Items().get(options1).get(options2) +
                        data.getOptions3Items().get(options1).get(options2).get(options3);
                tvKindaddress.setText(tx);

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
