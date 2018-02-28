package com.colud.jctl.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.util.ArrayMap;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.colud.jctl.JctlApplication;
import com.colud.jctl.base.BaseActivity;
import com.colud.jctl.bean.CycleWeekBean;
import com.colud.jctl.bean.NodeCollectionCycle;
import com.colud.jctl.mvp.contract.CycleWeekContract;
import com.colud.jctl.mvp.presenter.CycleWeekPresenter;
import com.colud.jctl.base.AppManager;
import com.colud.jctl.utils.ToastUtils;
import com.jctl.colud.R;
import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.colud.jctl.api.KeyConstants.INTENT_RESULT;


/**
 * Created by Jcty on 2017/4/6.
 */

public class PeriodActivity extends BaseActivity<CycleWeekPresenter> implements CycleWeekContract.View {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.btn_back)
    ImageButton btnBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_color_off)
    TextView tvColorOff;
    @BindView(R.id.tv_zhouqi)
    TextView tvZhouqi;
    @BindView(R.id.ll_chongfu)
    LinearLayout llChongfu;
    @BindView(R.id.hour_wheelview)
    WheelView hourWheelView;
    @BindView(R.id.minute_wheelview)
    WheelView minuteWheelView;
    @BindView(R.id.second_wheelview)
    WheelView secondWheelView;

    private String hour, min, second;
    private String cycleTime;

    private ArrayList<String> sList;

    private ArrayList<Integer> iList = new ArrayList<>();


    private NodeCollectionCycle item;

    private String nodeId, cycle;


    @OnClick({R.id.btn_back, R.id.ll_back, R.id.tv_content, R.id.ll_chongfu})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                AppManager.newInstance().finishCurrentActivity();
                break;
            case R.id.btn_back:
                AppManager.newInstance().finishCurrentActivity();
                break;
            case R.id.tv_content:
                if (iList.size() > 0) {
                    cycleTime = hourWheelView.getSelectionItem() + ":" + minuteWheelView.getSelectionItem();
                    List<String> alist = listIntegerToListString(iList);
                    @SuppressLint({"NewApi", "LocalSuppress"})
                    Map<String, String> date = new ArrayMap<>();
                    //					String GuideActivity=listToString3(iList,'1');
                    if (nodeId != null && cycleTime != null) {
                        date.put("nodeId", nodeId);
                        if (cycle.equals("on")) {
                            date.put("cycleOn", cycleTime);
                        } else {
                            date.put("cycleOff", cycleTime);
                        }
                    }
                    String[] week = listStringToStringArray(alist);
                    mPresenter.setCycleWeekData(cycle, date, week);
                } else {
                    ToastUtils.showLong("请设置周期");
                }
                break;
            case R.id.ll_chongfu:
                Intent intent = new Intent(getApplicationContext(), RepetitionActivity.class);
                intent.putExtra("sList", iList);
                startActivityForResult(intent, INTENT_RESULT);
                break;
        }
    }


    @Override
    public int getContentViewId() {
        return R.layout.activity_period;
    }

    @Override
    public void initViews() {
        tvTitle.setText(JctlApplication.getAppResources().getString(R.string.tv_zqsx) + "-开");
        tvContent.setVisibility(View.VISIBLE);
        tvContent.setText(JctlApplication.getAppResources().getString(R.string.mine_wc));

    }

    @Override
    public void addListener() {

        hourWheelView.setOnWheelItemSelectedListener(new WheelView.OnWheelItemSelectedListener() {
            @Override
            public void onItemSelected(int position, Object o) {
                hour = createHours().get(position);
            }
        });

        minuteWheelView.setOnWheelItemSelectedListener(new WheelView.OnWheelItemSelectedListener() {
            @Override
            public void onItemSelected(int position, Object o) {
                min = createMinutes().get(position);
            }
        });
        secondWheelView.setOnWheelItemSelectedListener(new WheelView.OnWheelItemSelectedListener() {
            @Override
            public void onItemSelected(int position, Object o) {
                second = createMinutes().get(position);
            }
        });
    }

    @Override
    public void initData() {
        hourWheelView.setWheelAdapter(new ArrayWheelAdapter(this));
        hourWheelView.setSkin(WheelView.Skin.Holo);
        hourWheelView.setWheelData(createHours());
        WheelView.WheelViewStyle style = new WheelView.WheelViewStyle();
        //		style.selectedTextColor = Color.parseColor("#FF0000");
        style.selectedTextColor = Color.BLACK;
        style.textColor = Color.GRAY;
        style.selectedTextSize = 13;
        //		style.selectedTextZoom=2f;
        hourWheelView.setStyle(style);
        //		hourWheelView.setExtraText("时", Color.parseColor("#FF0000"), 40, 70);
        hourWheelView.setExtraText("", Color.BLACK, 40, 70);

        minuteWheelView.setWheelAdapter(new ArrayWheelAdapter(this));
        minuteWheelView.setSkin(WheelView.Skin.Holo);
        minuteWheelView.setWheelData(createMinutes());
        minuteWheelView.setStyle(style);
        //		minuteWheelView.setExtraText("分", Color.parseColor("#FF0000"), 40, 70);
        minuteWheelView.setExtraText("", Color.BLACK, 40, 70);

        //		secondWheelView.setWheelAdapter(new ArrayWheelAdapter(this));
        //		secondWheelView.setSkin(WheelView.Skin.Holo);
        //		secondWheelView.setWheelData(createMinutes());
        //		secondWheelView.setStyle(style);
        //		//		secondWheelView.setExtraText("秒", Color.parseColor("#0288ce"), 40, 70);
        //		secondWheelView.setExtraText("", Color.BLACK, 40, 70);
        Intent intent = getIntent();
        item = (NodeCollectionCycle) intent.getSerializableExtra("cycleItem");
        cycle = intent.getStringExtra("cycle");
        if (item != null && cycle.equals("on")) {
            nodeId = item.getNodeId();
            tvColorOff.setText("on");
            if (item.getCycleOn() != null) {
                setTime(item.getCycleOn());
            }
            if (item.getOnWeeks() != null) {
                if (item.getOnWeeks().size() > 0) {
                    tvZhouqi.setText("");
                    for (String week : item.getOnWeeks()) {
                        if (week.equals("1")) {
                            week = "日";
                            iList.add(1);
                        } else if (week.equals("2")) {
                            week = "一";
                            iList.add(2);
                        } else if (week.equals("3")) {
                            week = "二";
                            iList.add(3);
                        } else if (week.equals("4")) {
                            week = "三";
                            iList.add(4);
                        } else if (week.equals("5")) {
                            week = "四";
                            iList.add(5);
                        } else if (week.equals("6")) {
                            week = "五";
                            iList.add(6);
                        } else if (week.equals("7")) {
                            week = "六";
                            iList.add(7);
                        }
                        tvZhouqi.append("周" + week + "\t");
                    }
                }
            }

        } else if (item != null && cycle.equals("off")) {
            tvColorOff.setText("off");
            tvColorOff.setTextColor(JctlApplication.getAppResources().getColor(R.color.red));
            nodeId = item.getNodeId();
            if (item.getCycleOff() != null) {
                setTime(item.getCycleOff());
            }
            if (item.getOffWeeks() != null) {
                if (item.getOffWeeks().size() > 0) {
                    tvZhouqi.setText("");
                    for (String week : item.getOffWeeks()) {
                        if (week.equals("1")) {
                            week = "日";
                            iList.add(1);
                        } else if (week.equals("2")) {
                            week = "一";
                            iList.add(2);
                        } else if (week.equals("3")) {
                            week = "二";
                            iList.add(3);
                        } else if (week.equals("4")) {
                            week = "三";
                            iList.add(4);
                        } else if (week.equals("5")) {
                            week = "四";
                            iList.add(5);
                        } else if (week.equals("6")) {
                            week = "五";
                            iList.add(6);
                        } else if (week.equals("7")) {
                            week = "六";
                            iList.add(7);
                        }
                        tvZhouqi.append("周" + week + "\t");
                    }
                }
            }
        }
    }


    public void setTime(String time) {
        String[] strs = time.split(":");
        hourWheelView.setSelection(Integer.parseInt(strs[0]));
        minuteWheelView.setSelection(Integer.parseInt(strs[1]));
        //		secondWheelView.setSelection(Integer.parseInt(strs[2]));
    }


    private ArrayList<String> createHours() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            if (i < 10) {
                list.add("0" + i);
            } else {
                list.add("" + i);
            }
        }
        return list;
    }

    private ArrayList<String> createMinutes() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            if (i < 10) {
                list.add("0" + i);
            } else {
                list.add("" + i);
            }
        }
        return list;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //		String change01 = data.getStringExtra("conList");
        // 根据上面发送过去的请求吗来区别
        if (data != null) {
            switch (requestCode) {
                case 1004:
                    tvZhouqi.setText("");
                    iList.clear();
                    sList = (ArrayList<String>) data.getSerializableExtra("conList");
                    //					Collections.sort(sList);
                    for (String week : sList) {
                        if (week.equals("星期日")) {
                            week = "日";
                            iList.add(1);
                        } else if (week.equals("星期一")) {
                            week = "一";
                            iList.add(2);
                        } else if (week.equals("星期二")) {
                            week = "二";
                            iList.add(3);
                        } else if (week.equals("星期三")) {
                            week = "三";
                            iList.add(4);
                        } else if (week.equals("星期四")) {
                            week = "四";
                            iList.add(5);
                        } else if (week.equals("星期五")) {
                            week = "五";
                            iList.add(6);
                        } else if (week.equals("星期六")) {
                            week = "六";
                            iList.add(7);
                        }
                        tvZhouqi.append("周" + week + "\t");
                    }

                    break;
                default:
                    break;
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected CycleWeekPresenter onCreatePresenter() {
        return new CycleWeekPresenter(this);
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void onSucceed(CycleWeekBean data) {
        Toast.makeText(getApplicationContext(), data.getMsg(), Toast.LENGTH_LONG).show();
        //		if (data.getCyclOnTime()!=null){
        //			setTime(data.getCyclOnTime());
        //		}
        //		if (data.getCyclOnWeek()!=null&&data.getCyclOnWeek().size()>0){
        //			tvZhouqi.setText("");
        //			List<String>mList=new ArrayList<>();
        //			for (int i = 0; i < data.getCyclOnWeek().size(); i++) {
        //				mList.add(data.getCyclOnWeek().get(i)+"");
        //			}
        //			//			Collections.sort(mList);
        //			for (String week : mList) {
        //				if (week.equals("1")){
        //					week="日";
        //				}else if (week.equals("2")){
        //					week="一";
        //				}
        //				else if (week.equals("3")){
        //					week="二";
        //				}
        //				else if (week.equals("4")){
        //					week="三";
        //				}
        //				else if (week.equals("5")){
        //					week="四";
        //				}
        //				else if (week.equals("6")){
        //					week="五";
        //				}
        //				else if (week.equals("7")){
        //					week="六";
        //				}
        //				tvZhouqi.append("周"+week+"\t");
        //			}
        //		}


    }

    @Override
    public void onFailure(String err, Throwable e) {
        Toast.makeText(getApplicationContext(), err, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFail(String err) {

    }

    @Override
    public void hideDialog() {

    }

    //List<String>对象转换为String型数组
    static String[] listStringToStringArray(List<String> stringList) {
        String[] strings = stringList.toArray(new String[0]);
        return strings;
    }

    //	List<Integer>对象转换为List<String>对象
    static ArrayList<String> listIntegerToListString(ArrayList<Integer> integerList) {
        ArrayList<String> stringList = new ArrayList<>(integerList.size());
        for (Integer anInt : integerList) {
            stringList.add(String.valueOf(anInt));
        }
        return stringList;
    }

    // 方法三：
    public String listToString3(List list, char separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }
    //List<String>对象转换为String型数组

}
