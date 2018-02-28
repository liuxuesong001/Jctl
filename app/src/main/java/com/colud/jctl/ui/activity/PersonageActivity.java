package com.colud.jctl.ui.activity;

import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.colud.jctl.JctlApplication;
import com.colud.jctl.base.BaseActivity;
import com.colud.jctl.base.BasePesenter;
import com.colud.jctl.ui.custom.TitleBar;
import com.colud.jctl.utils.BarStatusHeightUtil;
import com.jctl.colud.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by Jcty on 2017/3/23.
 */

public class PersonageActivity extends BaseActivity {

    @BindView(R.id.title_cotent)
    TextView collectGl;

    @BindView(R.id.personage_user)
    TextView personageUser;
    @BindView(R.id.personage_userzh)
    TextView personageUserzh;
    @BindView(R.id.personage_usertype)
    TextView personageUsertype;
    @BindView(R.id.personage_type_img)
    ImageView personageTypeImg;
    @BindView(R.id.personage_type_layout)
    LinearLayout personageTypeLayout;
    @BindView(R.id.personage_sex)
    TextView personageSex;
    @BindView(R.id.personage_sex_img)
    ImageView personageSexImg;
    @BindView(R.id.personage_sex_layout)
    LinearLayout personageSexLayout;
    @BindView(R.id.personage_age)
    TextView personageAge;
    @BindView(R.id.personage_age_img)
    ImageView personageAgeImg;
    @BindView(R.id.personage_age_layout)
    LinearLayout personageAgeLayout;
    @BindView(R.id.personage_nestpad)
    TextView personageNestpad;
    @BindView(R.id.personage_nhsl)
    TextView personageNhsl;

    protected static boolean isState = false;//是否点击

    private ArrayList<String> typeItem = new ArrayList<>();
    private ArrayList<Integer> ageItem = new ArrayList<>();
    private ArrayList<String> sexItem = new ArrayList<>();

    private OptionsPickerView ageCustomOptions;

    protected static int INT_TYPE = 0;

    @BindView(R.id.login_toolbar)
    TitleBar toolbar;


    @Override
    protected BasePesenter onCreatePresenter() {
        return new BasePesenter();
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_personage;
    }

    @Override
    public void initViews() {
        getData();
        setTitle();
        //		mAdapter=new PersonageAdapter(this,null,true);
        //		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //		rv.setLayoutManager(layoutManager);
        //		rv.setAdapter(mAdapter);
        //		mAdapter.setNewData(itemList);
    }

    @Override
    public void addListener() {
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.personage_type_layout, R.id.personage_age_layout, R.id.personage_sex_layout, R.id.title_cotent})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_cotent:
                if ("编辑".equals(collectGl.getText().toString())) {
                    isState = true;
                    collectGl.setText("完成");
                    personageAgeImg.setVisibility(View.VISIBLE);
                    personageTypeImg.setVisibility(View.VISIBLE);
                    personageSexImg.setVisibility(View.VISIBLE);
                } else if ("完成".equals(collectGl.getText().toString())) {
                    isState = false;
                    collectGl.setText("编辑");
                    personageAgeImg.setVisibility(View.GONE);
                    personageTypeImg.setVisibility(View.GONE);
                    personageSexImg.setVisibility(View.GONE);
                }
                break;
            case R.id.personage_type_layout:
                if (isState) {
                    INT_TYPE = 0;
                    initCustomOptionPickerType();
                }
                break;
            case R.id.personage_sex_layout:
                if (isState) {
                    INT_TYPE = 1;
                    initCustomOptionPickerType();
                }
                break;
            case R.id.personage_age_layout:
                if (isState) {
                    INT_TYPE = 2;
                    initCustomOptionPickerType();
                }
                break;
        }
    }

    /**
     *
     */
    private void setTitle() {
        collectGl.setVisibility(View.VISIBLE);
        collectGl.setText(R.string.mine_bj);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//不显示自带标题
        toolbar.setCenterTitle(JctlApplication.getAppResources().getString(R.string.mine_grzl));
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

    private void initCustomOptionPickerType() {//条件选择器初始化，自定义布局

        // 注意：自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针
        // 具体可参考demo 里面的两个自定义布局
        //		ageCustomOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
        //			@Override
        //			public void onOptionsSelect(int options1, int option2, int options3, View v) {
        //				//返回的分别是三个级别的选中位置
        //				if(INT_TYPE ==0){
        //					String type= typeItem.get(options1).toString();
        //					personageUsertype.setText(type);
        //				}else if(INT_TYPE ==1){
        //					String sex = sexItem.get(options1).toString();
        //					personageSex.setText(sex);
        //				}else if(INT_TYPE ==2){
        //					int age = ageItem.get(options1);
        //					personageAge.setText(String.valueOf(age));
        //				}
        //
        //			}
        //		})
        //				.setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
        //					@Override
        //					public void customLayout(View v) {
        //						final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
        //						TextView prickerviewTitle=(TextView)v.findViewById(R.id.prickerview_title);
        //						if(INT_TYPE ==0){
        //							prickerviewTitle.setText("类型");
        //						}else if(INT_TYPE ==1){
        //							prickerviewTitle.setText("性别");
        //						}else if(INT_TYPE ==2){
        //							prickerviewTitle.setText("年龄");
        //						}
        //						tvSubmit.setOnClickListener(new View.OnClickListener() {
        //							@Override
        //							public void onClick(View v) {
        //								ageCustomOptions.returnData(tvSubmit);
        //							}
        //						});
        //						//						ivCancel.setOnClickListener(new View.OnClickListener() {
        //						//							@Override
        //						//							public void onClick(View v) {
        //						//								ageCustomOptions.dismiss();
        //						//							}
        //						//						});
        //						//						tvAdd.setOnClickListener(new View.OnClickListener() {
        //						//							@Override
        //						//							public void onClick(View v) {
        //						//								getData();
        //						//								ageCustomOptions.setPicker(cardItem);
        //						//							}
        //						//						});
        //
        //					}
        //				})
        //				.setDividerColor(getResources().getColor(R.color.color_CECECE))
        //				.setTextColorCenter(getResources().getColor(R.color.color_3AB07D)) //设置选中项文字颜色
        //				.setContentTextSize(18)
        //				.isDialog(true)
        //				.build();
        //		if(INT_TYPE ==0){
        //			ageCustomOptions.setPicker(typeItem);//添加数据
        //		}else if(INT_TYPE ==1){
        //			ageCustomOptions.setPicker(sexItem);//添加数据
        //		}else if(INT_TYPE ==2){
        //			ageCustomOptions.setPicker(ageItem);//添加数据
        //		}
        //		ageCustomOptions.show();
    }

    public void getData() {
        typeItem.add("农场主");
        typeItem.add("农户");
        typeItem.add("管家");

        for (int i = 10; i < 81; i++) {
            ageItem.add(i);
        }

        sexItem.add("男");
        sexItem.add("女");
    }
}
