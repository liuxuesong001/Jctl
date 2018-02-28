package com.colud.jctl.ui.custom.line;

import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.BounceInterpolator;

import com.colud.jctl.utils.ArraysUtils;
import com.db.chart.Tools;
import com.db.chart.animation.Animation;
import com.db.chart.model.LineSet;
import com.db.chart.renderer.AxisRenderer;
import com.db.chart.tooltip.Tooltip;
import com.db.chart.view.LineChartView;
import com.jctl.colud.R;

import java.util.Arrays;
import java.util.List;


public class LineCardOne extends CardController {


    private final LineChartView mChart;


    private final Context mContext;

    //
    //	private final String[] mLabels = {"Jan", "Fev", "Mar", "Apr", "Jun", "May", "Jul", "Aug", "Sep"};
    //	private  String[] mLabels = {"", "", "", "", "", "", "", "", ""};
    private String[] mLabels = new String[31];
    //
    //	private final float[][] mValues = {
    //			{3.5f, 4.7f, 4.3f, 8f, 6.5f, 9.9f, 7f, 8.3f, 7.0f},
    //			{3.5f, 4.7f, 4.3f, 8f, 6.5f, 9.9f, 7f, 8.3f, 7.0f},
    //			{4.5f, 2.5f, 2.5f, 9f, 4.5f, 9.5f, 5f, 8.3f, 1.8f}
    //	};
    //	private Float [] mValues = {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
    private Float[] mValues = new Float[31];

    //	private  String[] mLabels = {};

    //	private  float[][] mValues = {
    //			{},
    //	};

    private Tooltip mTip;

    private Runnable mBaseAction;


    public LineCardOne(CardView card, Context context) {

        super(card);

        mContext = context;
        mChart = (LineChartView) card.findViewById(R.id.chart);
    }

    public LineSet MyLineSet(String[] labels, Float[] values) {

        return new LineSet(labels, Ftof(values));

    }

    //数据没有显示
    public float[] Ftof(Float[] values) {
        float[] myvalues = new float[values.length];
        int i = 0;
        for (Float value : values) {
            myvalues[i] = value;

            i++;
        }
        i = 0;
        return myvalues;

    }

    /**
     * 添加底部日期
     *
     * @param atime
     */
    public void addTime(List<String> atime) {
        this.mLabels = (String[]) atime.toArray(new String[atime.size()]);
    }

    /**
     * 添加数据
     *
     * @param content
     */
    public void addContent(List<Float> content) {
        Float[] desc = new Float[content.size()];
        //		content.toArray();
        //		this.mValues=desc;
        content.toArray(desc);
        this.mValues = desc;
        //		output(desc);
        //		this.mValues = newList.toArray();
        //		this.mValues=content.toArray(new Float[content.size()]);
        //		List<Float> list = new ArrayList<>(Arrays.asList(mValues));


    }

    @Override
    public void show(Runnable action) {

        super.show(action);

        // Tooltip
        mTip = new Tooltip(mContext, R.layout.linechart_three_tooltip, R.id.value);

        //		((TextView) mTip.findViewById(R.id.value)).setTypeface(
        //				Typeface.createFromAsset(mContext.getAssets(), "OpenSans-Semibold.ttf"));

        mTip.setVerticalAlignment(Tooltip.Alignment.BOTTOM_TOP);
        mTip.setDimensions((int) Tools.fromDpToPx(58), (int) Tools.fromDpToPx(25));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {

            mTip.setEnterAnimation(PropertyValuesHolder.ofFloat(View.ALPHA, 1),
                    PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f),
                    PropertyValuesHolder.ofFloat(View.SCALE_X, 1f)).setDuration(200);

            mTip.setExitAnimation(PropertyValuesHolder.ofFloat(View.ALPHA, 0),
                    PropertyValuesHolder.ofFloat(View.SCALE_Y, 0f),
                    PropertyValuesHolder.ofFloat(View.SCALE_X, 0f)).setDuration(200);

            mTip.setPivotX(Tools.fromDpToPx(65) / 2);
            mTip.setPivotY(Tools.fromDpToPx(25));
        }

        mChart.setTooltips(mTip);

        // Data
        List<String> list = Arrays.asList(mLabels);
        if (!TextUtils.isEmpty(list.get(0))) {
            LineSet dataset = MyLineSet(mLabels, mValues);
            dataset.setColor(Color.parseColor("#00FFFF"))
                    .setFill(Color.parseColor("#54708F"))
                    .setDotsColor(Color.parseColor("#00FFFF"))
                    .setThickness(4)
                    //					.setDashed(new float[] {10f, 10f})
                    //					.beginAt(5)
                    .setGradientFill(new int[]{Color.parseColor("#00FFFF"), Color.parseColor("#FF54708F")},
                            null);
            mChart.addData(dataset);

            Paint gridPaint = new Paint();
            gridPaint.setColor(Color.parseColor("#e7e7e7"));
            gridPaint.setStyle(Paint.Style.STROKE);
            gridPaint.setAntiAlias(true);
            gridPaint.setStrokeWidth(Tools.fromDpToPx(.7f));
            // Chart
            int max = ArraysUtils.getMaxValue(Ftof(mValues));
            max = max / 2 + max;
            if (max == 0) {
                max = 20;
            }
            mChart.setBorderSpacing(Tools.fromDpToPx(5))
                    //					.setAxisBorderValues(0, 20)
                    .setAxisBorderValues(0, max)
                    //				.setGrid(0, 10, gridPaint)
                    .setYLabels(AxisRenderer.LabelPosition.OUTSIDE)
                    .setLabelsColor(Color.parseColor("#000000"))
                    .setXAxis(false)
                    .setYAxis(false)
                    .setAxisColor(Color.parseColor("#000000"));


            mBaseAction = action;
            Runnable chartAction = new Runnable() {
                @Override
                public void run() {
                    mBaseAction.run();
                    int max = ArraysUtils.getMax(Ftof(mValues));
                    mTip.prepare(mChart.getEntriesArea(0).get(max), mValues[0]);
                    mChart.showTooltip(mTip, true);
                }
            };

            Animation anim = new Animation().setEasing(new BounceInterpolator()).setEndAction(chartAction);

            mChart.show(anim);
        }
    }

    //		dataset = new LineSet(mLabels, mValues[0]);
    //		dataset.setColor(Color.parseColor("#b3b5bb"))
    //				.setFill(Color.parseColor("#2d374c"))
    //				.setDotsColor(Color.parseColor("#ffc755"))
    //				.setThickness(4)
    //				.endAt(6);
    //		mChart.addData(dataset);


    @Override
    public void update() {

        super.update();

        mChart.dismissAllTooltips();
        //		if (firstStage) {
        //			mChart.updateValues(0, mValues[1]);
        //			mChart.updateValues(1, mValues[1]);
        //		} else {
        //			mChart.updateValues(0, mValues[0]);
        //			mChart.updateValues(1, mValues[0]);
        //		}
        //这里是更新数据的方法
        if (firstStage) {
            mChart.updateValues(0, Ftof(mValues));
        } else {
            mChart.updateValues(0, Ftof(mValues));
        }


        mChart.getChartAnimation().setEndAction(mBaseAction);
        mChart.notifyDataUpdate();
    }


    @Override
    public void dismiss(Runnable action) {

        super.dismiss(action);

        mChart.dismissAllTooltips();
        mChart.dismiss(new Animation().setEasing(new BounceInterpolator()).setEndAction(action));
    }

    /**
     * 清空现有数据
     */
    public void closeDate() {
        mLabels = null;
        mValues = null;
    }
}
