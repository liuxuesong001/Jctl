package com.colud.jctl.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.colud.jctl.JctlApplication;
import com.colud.jctl.adapters.GRAdapter;
import com.colud.jctl.api.KeyConstants;
import com.colud.jctl.base.BaseActivity;
import com.colud.jctl.bean.GRItem;
import com.colud.jctl.bean.NodeItem;
import com.colud.jctl.mvp.contract.GrowthContract;
import com.colud.jctl.mvp.presenter.GrowthPresenter;
import com.colud.jctl.ui.custom.ChartView;
import com.colud.jctl.ui.custom.TempCircleView;
import com.colud.jctl.ui.custom.TempShowView;
import com.colud.jctl.ui.custom.TitleBar;
import com.colud.jctl.ui.custom.line.LineCardOne;
import com.colud.jctl.utils.BarStatusHeightUtil;
import com.colud.jctl.utils.ToastUtils;
import com.db.chart.view.LineChartView;
import com.jctl.colud.R;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 数据详情
 * Created by Jcty on 2017/4/6.
 */

public class GrowthRecordsActivity extends BaseActivity<GrowthPresenter> implements GrowthContract.View {


    @BindView(R.id.title_toolbar)
    TitleBar toolbar;

    //	@BindView(R.id.tempView)
    //	TempShowView tempView;

    @BindView(R.id.TempCircleView)
    TempCircleView tempView;

    @BindView(R.id.title_cotent)
    TextView tvContent;
    @BindView(R.id.day)
    Button day;
    @BindView(R.id.month)
    Button month;
    @BindView(R.id.quarter)
    Button quarter;
    @BindView(R.id.gr_rv)
    RecyclerView rv;
    @BindView(R.id.sv)
    ScrollView sv;
    @BindView(R.id.gr_chartview)
    ChartView chartView;
    @BindView(R.id.chart)
    LineChartView mChart;
    @BindView(R.id.card1)
    CardView cardView;


    private List<GRItem> grItems = new ArrayList<>();

    private GRAdapter grAdapter;

    private double value;

    private TempShowView showView;

    private String text;

    private String title;

    private LineCardOne lineCardOne;

    private String dats;

    private String nodeMac;

    private TimePickerView pvCustomTime;

    private Dialog dialog;

    private Map<String, String> map = new ArrayMap<>();


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //			tempView.setTemp((double) msg.obj);
        }
    };


    @Override
    public int getContentViewId() {
        return R.layout.activity_growth_records;
    }

    @Override
    public void initViews() {
        //		tvContent.setVisibility(View.VISIBLE);
        //		tvContent.setText("选择日期");
        showView = new TempShowView(this, text);
        tempView = new TempCircleView(this);
        //		tvTitle.setText(JctlApplication.getAppResources().getString(R.string.tv_szjl));
        //		tempView.setTemp(10.5f);
        initToolbar();
        startShow();
        initAdapter();
        initCharts();
        //		float temps[] = {30,27,17,35};
        //		String xNames[] = {"一季度","二季度","三季度","四季度"};
        //		chartView.setVertiLineNum(3);//设置报表有几列
        //		chartView.setDate(xNames, temps);//设置报表横坐标名称和对应的数值
        //		chartView.start();//让报表重新绘制一次，展示所设置的值。
    }

    private void initToolbar() {
        //		tvContent.setVisibility(View.VISIBLE);
        tvContent.setText(JctlApplication.getAppResources().getString(R.string.tv_xzrq));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//不显示自带标题
        toolbar.setCenterTitle(JctlApplication.getAppResources().getString(R.string.tv_szjl));
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

    /**
     * 设置initCharts
     */
    protected void initCharts() {
        lineCardOne = new LineCardOne(cardView, this);
        //		lineCardOne.init();
    }

    @Override
    public void addListener() {

    }

    protected void initAdapter() {
        //		FullyLinearLayoutManager manager=new FullyLinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        //		rv.setNestedScrollingEnabled(false);
        //		rv.setLayoutManager(manager);
        //		sv.smoothScrollTo(0,0);
        //		grAdapter=new GRAdapter(getApplicationContext(),grItems,true);
        //		rv.setAdapter(grAdapter);

    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        NodeItem.InfoBean info = (NodeItem.InfoBean) intent.getSerializableExtra("data");
        String type = intent.getStringExtra("type");
        if (info != null) {
            if (info.getAirTemperature() != 0.0d && type.equals("a")) {
                title = "空气温度";
                tempView.setProgressTitle(title);
                tempView.setProgress(info.getAirTemperature());
            } else if (info.getAirHumidity() != 0.0d && type.equals("b")) {
                title = "空气湿度";
                tempView.setProgressTitle(title);
                tempView.setProgress(info.getAirHumidity());
            } else if (info.getSoilTemperature1() != 0.0d && type.equals("c")) {
                title = "1号采集温度";
                tempView.setProgressTitle(title);
                tempView.setProgress(info.getSoilTemperature1());
            } else if (info.getSoilHumidity1() != 0.0d && type.equals("d")) {
                title = "1号采集湿度";
                tempView.setProgressTitle(title);
                tempView.setProgress(info.getSoilHumidity1());

            } else if (info.getSoilTemperature2() != 0.0d && type.equals("e")) {
                title = "2号采集湿度";
                tempView.setProgressTitle(title);
                tempView.setProgress(info.getSoilTemperature2());

            } else if (info.getSoilHumidity2() != 0.0d && type.equals("f")) {
                title = "2号采集湿度";
                tempView.setProgressTitle(title);
                tempView.setProgress(info.getSoilHumidity2());

            } else if (info.getSoilTemperature3() != 0.0d && type.equals("g")) {
                title = "3号采集温度";
                tempView.setProgressTitle(title);
                tempView.setProgress(info.getSoilTemperature3());


            } else if (info.getSoilHumidity3() != 0.0d && type.equals("h")) {
                title = "3号采集湿度";
                tempView.setProgressTitle(title);
                tempView.setProgress(info.getSoilHumidity3());
            } else if (info.getCo2() != 0 && type.equals("y")) {
                title = "二氧化碳";
                tempView.setProgressTitle(title);
                tempView.setMaxProgress(1000);
                tempView.setProgress(info.getCo2());
            }

            if (info.getNodeMac() != null && !info.getNodeMac().equals("")) {
                nodeMac = info.getNodeMac();
                map.clear();
                map.put("singleId", KeyConstants.USER_SINGLEID);
                map.put("nodeNum", nodeMac);
                map.put("type", title);
                mPresenter.setGR(map);
            }
        }
        //		String a = get.getStringExtra("value");
        //		text = get.getStringExtra("text");
        //		content = get.getStringExtra("content");
        //		if (!TextUtils.isEmpty(a)) {
        //			value = Double.valueOf(a);
        //			tempView.setProgress(80);
        //		}
        //		num=get.getStringExtra("num");
        //		if (nodeMac!=null&&!"".equals(num)&&content!=null&&!"".equals(content)){
        //			GRItem item=new GRItem();
        //			item.setNum(num);
        //			item.setName(content);
        //			mPresenter.setGR(item);

        //            @Query("nodeNum")String nodeMac,
        //            @Query("type")String type,
        //            @Query("data")String data
        //			Map<String,String>map=new ArrayMap<>();
        //			map.put("singleId", KeyConstants.KAY_USERID);
        //			map.put("nodeNum",num);
        //			map.put("type",content);
        //			mPresenter.setGR(map);
        //
        //		}


    }

    private void startShow() {
        new Thread() {
            public void run() {
                Message message = new Message();
                message.obj = value;
                handler.sendMessage(message);
                //				while (true) {
                //					Message message = new Message();
                //					Random random = new Random();
                //					int a = random.nextInt(60);
                //					message.obj = a;
                //					handler.sendMessageDelayed(message, 2000);
                //					try {
                //						this.sleep(2000);
                //					} catch (InterruptedException e) {
                //						e.printStackTrace();
                //					}
                //				}
            }
        }.start();
    }

    @OnClick({R.id.title_cotent, R.id.day, R.id.month, R.id.quarter})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_cotent:
                if (pvCustomTime != null) {
                    pvCustomTime.show();
                }
                break;
            case R.id.day:
                day.setTextColor(JctlApplication.getAppResources().getColor(R.color.white));
                day.setBackgroundColor(JctlApplication.getAppResources().getColor(R.color.color_3AB07D));
                month.setTextColor(JctlApplication.getAppResources().getColor(R.color.black));
                month.setBackgroundColor(JctlApplication.getAppResources().getColor(R.color.white));
                quarter.setTextColor(JctlApplication.getAppResources().getColor(R.color.black));
                quarter.setBackgroundColor(JctlApplication.getAppResources().getColor(R.color.white));
                break;
            case R.id.month:
                month.setTextColor(JctlApplication.getAppResources().getColor(R.color.white));
                month.setBackgroundColor(JctlApplication.getAppResources().getColor(R.color.color_3AB07D));
                day.setTextColor(JctlApplication.getAppResources().getColor(R.color.black));
                day.setBackgroundColor(JctlApplication.getAppResources().getColor(R.color.white));
                quarter.setTextColor(JctlApplication.getAppResources().getColor(R.color.black));
                quarter.setBackgroundColor(JctlApplication.getAppResources().getColor(R.color.white));
                break;
            case R.id.quarter:
                //				quarter.setTextColor(JctlApplication.getAppResources().getColor(R.color.white));
                //				quarter.setBackgroundColor(JctlApplication.getAppResources().getColor(R.color.color_3AB07D));
                //				month.setTextColor(JctlApplication.getAppResources().getColor(R.color.black));
                //				month.setBackgroundColor(JctlApplication.getAppResources().getColor(R.color.white));
                //				day.setTextColor(JctlApplication.getAppResources().getColor(R.color.black));
                //				day.setBackgroundColor(JctlApplication.getAppResources().getColor(R.color.white));
                break;
        }
    }

    @Override
    protected GrowthPresenter onCreatePresenter() {
        return new GrowthPresenter(this);
    }

    @Override
    public void showDialog() {
        //		JctlApplication.getGifLoadingView().show(getFragmentManager(),"");
        if (dialog == null) {
            dialog = new Dialog(GrowthRecordsActivity.this, R.style.progress_dialog);
            dialog.setContentView(R.layout.dialog);
            dialog.setCancelable(true);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            TextView msg = (TextView) dialog.findViewById(R.id.id_tv_loadingmsg);
            msg.setText("请稍后...");
            dialog.show();
        }
    }

    @Override
    public void onSucceed(GRItem data) {
        if (!TextUtils.isEmpty(data.getMsg())) {
            ToastUtils.showLong(data.getMsg());
        } else {
            lineCardOne.addTime(data.getAddTime());
            lineCardOne.addContent(data.getContent());
            lineCardOne.init();
        }

    }

    @Override
    public void onFailure(String err, Throwable e) {
        ToastUtils.showLong(err);
    }

    @Override
    public void onFail(String err) {
        KLog.d(err);
    }

    @Override
    public void hideDialog() {
        //		JctlApplication.getGifLoadingView().dismiss();
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }
    //	private void initCustomTimePicker() {
    //		// 注意：自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针
    //		// 具体可参考demo 里面的两个自定义布局
    //		//因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
    //		//控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
    //		Calendar selectedDate = Calendar.getInstance();//系统当前时间
    //		Calendar startDate = Calendar.getInstance();
    //		startDate.set(2017,1,1);
    //		Calendar endDate = Calendar.getInstance();
    //		endDate.set(2027,2,28);
    //		//时间选择器 ，自定义布局
    //		pvCustomTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
    //			@Override
    //			public void onTimeSelect(Date date, View v) {//选中事件回调
    //				dats=getTime(date);
    //				if (!TextUtils.isEmpty(dats)){
    //					tvContent.setText(dats);
    //					if (!TextUtils.isEmpty(num)&&!TextUtils.isEmpty(content)){
    //						GRItem item=new GRItem();
    //						item.setNum(num);
    //						item.setName(content);
    //						item.setDate(dats);
    //						mPresenter.setGR(item);
    //					}
    //				}
    //				//				btn_CustomTime.setText(getTime(date));
    //			}
    //		})
    //				//				.setDate(selectedDate)
    //				//				.setRangDate(startDate,endDate)
    //				.setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {
    //
    //					@Override
    //					public void customLayout(View v) {
    //						final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
    //						//						ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
    //						tvSubmit.setOnClickListener(new View.OnClickListener() {
    //							@Override
    //							public void onClick(View v) {
    //								pvCustomTime.returnData();
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
    //				.setDividerColor(JctlApplication.getAppResources().getColor(R.color.color_CECECE))
    //				.setTextColorCenter(JctlApplication.getAppResources().getColor(R.color.color_3AB07D)) //设置选中项文字颜色
    //				.setContentSize(15)
    //				.isDialog(true)
    //				.build();
    //
    //	}
    //	private String getTime(Date date) {//可根据需要自行截取数据显示
    //		//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    //		return format.format(date);
    //	}
}
