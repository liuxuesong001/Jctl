package com.colud.jctl.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.colud.jctl.JctlApplication;
import com.colud.jctl.api.KeyConstants;
import com.colud.jctl.base.BaseActivity;
import com.colud.jctl.bean.AddFarmItem;
import com.colud.jctl.bean.JsonBeanList;
import com.colud.jctl.bean.RegItem;
import com.colud.jctl.mvp.contract.AddFarmContract;
import com.colud.jctl.mvp.presenter.AddFarmPresenter;
import com.colud.jctl.ui.custom.TitleBar;
import com.colud.jctl.ui.custom.dialog.pickerview.DialogPickerView;
import com.colud.jctl.utils.BarStatusHeightUtil;
import com.colud.jctl.utils.BlurUtil;
import com.colud.jctl.utils.GetJsonDataUtil;
import com.colud.jctl.utils.ToastUtils;
import com.colud.jctl.utils.editview.EditTextWithDel;
import com.jctl.colud.R;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by Jcty on 2017/3/2.
 */

public class RegActivity extends BaseActivity<AddFarmPresenter> implements AddFarmContract.View {


    @BindView(R.id.title_cotent)
    TextView loginReg;
    @BindView(R.id.reg_name)
    EditTextWithDel regName;
    @BindView(R.id.mine_xta)
    TextView mineXta;
    @BindView(R.id.mine_address)
    TextView mineAddress;
    @BindView(R.id.mine_xtb)
    TextView mineXtb;

    protected static int INT_SEX = 0;
    //	protected static int INT_TYPE=0;
    @BindView(R.id.mine_age)
    TextView mineAge;
    @BindView(R.id.nan_checkbox)
    CheckBox nanbox;
    @BindView(R.id.nv_checkbox)
    CheckBox nvbox;
    @BindView(R.id.reg_firm_ll)
    LinearLayout firm;
    @BindView(R.id.firm_name)
    EditTextWithDel etFirm;

    private ArrayList<Integer> cardItem = new ArrayList<>();

    private DialogPickerView pickerView;

    private boolean isFirm;

    private int sexType = 1;

    private String intName;
    private String intAge;
    private String intAddress;
    private String intFirm;

    @BindView(R.id.login_toolbar)
    TitleBar toolbar;


    private OptionsPickerView addRessPv;


    @Override
    public int getContentViewId() {
        return R.layout.activity_register;
    }


    @Override
    public void initViews() {
        isFirm = getIntent().getBooleanExtra("firm", false);
        setToolBarTitle();
        showBlurBackground();
        pickerView = new DialogPickerView(this, listener, cardItem);
        if (isFirm) {
            firm.setVisibility(View.VISIBLE);
        }

    }


    private OptionsPickerView.OnOptionsSelectListener listener = new OptionsPickerView.OnOptionsSelectListener() {
        @Override
        public void onOptionsSelect(int options1, int options2, int options3, View v) {
            int tx = cardItem.get(options1);
            mineAge.setText(String.valueOf(tx));
        }
    };

    @Override
    public void addListener() {
        loginReg.setVisibility(View.VISIBLE);
        loginReg.setText(JctlApplication.getAppResources().getString(R.string.mine_next));
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

        getData();
    }

    /**
     * 设置ToolBar参数
     */
    public void setToolBarTitle() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected AddFarmPresenter onCreatePresenter() {
        return new AddFarmPresenter(this);
    }

    private void showBlurBackground() {
        Bitmap bjImg;
        View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
        try {
            bjImg = BitmapFactory.decodeStream(getResources().getAssets().open("login_bj.png"));
            //缩放并显示
            Bitmap newImg = BlurUtil.doBlur(bjImg, 20, 5);
            bjImg.recycle();

            rootView.setBackground(new BitmapDrawable(getResources(), newImg));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.nv_checkbox, R.id.nan_checkbox, R.id.title_cotent, R.id.mine_address, R.id.mine_age})
    public void onClick(View v) {
        Intent in;
        switch (v.getId()) {
            case R.id.title_cotent:
                if (!TextUtils.isEmpty(regName.getText().toString().trim())) {
                    intName = regName.getText().toString().trim();
                } else {
                    ToastUtils.showShort("请输入您的姓名");
                    return;
                }
                if (!TextUtils.isEmpty(mineAge.getText().toString().trim())) {
                    intAge = mineAge.getText().toString().trim();
                } else {
                    ToastUtils.showShort("请选择年龄");
                    return;
                }
                if (!TextUtils.isEmpty(mineAddress.getText().toString().trim())) {
                    intAddress = mineAddress.getText().toString().trim();
                } else {
                    ToastUtils.showShort("请选择地址");
                }
                if (firm.getVisibility() == View.VISIBLE) {
                    if (!TextUtils.isEmpty(etFirm.getText().toString().trim())) {
                        intFirm = etFirm.getText().toString().trim();
                    } else {
                        ToastUtils.showShort("请输入您的公司名称");
                        return;
                    }
                }
                RegItem regItem = new RegItem();
                in = new Intent(getApplicationContext(), MobileActivity.class);
                regItem.setmSex(sexType);
                regItem.setmAge(intAge);
                regItem.setmAddress(intAddress);
                regItem.setCompanyName(intFirm);
                regItem.setmName(intName);
                in.putExtra("rega", regItem);
                startActivity(in);
                break;
            case R.id.mine_age:
                if (pickerView != null) {
                    pickerView.isShow();
                }
                break;
            case R.id.mine_address:
                if (addRessPv != null) {
                    addRessPv.show();
                }
                break;
            case R.id.nan_checkbox:
                sexType = 1;
                if (nanbox.isChecked()) {
                    nvbox.setChecked(false);
                } else {
                    nanbox.setChecked(true);
                }
                break;
            case R.id.nv_checkbox:
                sexType = 2;
                if (nvbox.isChecked()) {
                    nanbox.setChecked(false);
                } else {
                    nvbox.setChecked(true);
                }
                break;
            default:
        }
    }

    public void getData() {
        for (int i = 18; i < 81; i++) {
            cardItem.add(i);
        }
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void onSucceed(AddFarmItem data) {

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

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void hideDialog() {

    }

    public void setAddressPickerView(final JsonBeanList data) {
        addRessPv = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = data.getOptions1Items().get(options1).getPickerViewText() +
                        data.getOptions2Items().get(options1).get(options2) +
                        data.getOptions3Items().get(options1).get(options2).get(options3);
                mineAddress.setText(tx);

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
