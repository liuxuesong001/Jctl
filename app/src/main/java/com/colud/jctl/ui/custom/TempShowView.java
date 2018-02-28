package com.colud.jctl.ui.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import com.jctl.colud.R;


public class TempShowView extends View {
    /**
     * 组件的宽度和高度
     */
    public int width, height;
    /**
     * 圆心X坐标
     */
    public int centerX;
    /**
     * 圆心Y坐标
     */
    public int centerY;
    /**
     * 外圆的半径
     */
    public int circleRadius;
    /**
     * 渐变线半径
     */
    public int centercircleRadius;

    /**
     * 温度刻度半径
     */

    public int tempCiecleRadius;
    /**
     * 外圆画笔、中间圆画笔、温度圆形画笔、文字画笔
     */

    private Paint paintOut, paintCenter, paintTemp, paintText;
    /**
     * 三只画笔的粗细
     */
    private int paintOutWidth = 3, paintCenterWidth = 3, paintTempWidth = 12;
    /**
     * 最大温度（满刻度时代表的温度值）
     */
    private double MaxTemp = 100.0d;
    /**
     * 每一度温度代表的角度
     */
    private double anglePerTemp;
    /**
     * 当前温度值
     */
    //	private float currentTemp = 0.0f;
    private double currentTemp = 0.0d;
    /**
     * 内容标题
     */
    private static String text = "";

    private float startAngle = -90.0f;

    public TempShowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public TempShowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TempShowView(Context context, String text) {
        super(context);
        this.text = text;
        init(context);
    }

    @SuppressLint("NewApi")
    public TempShowView(Context context, AttributeSet attrs, int defStyleAttr,
                        int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    /**
     * 设置当前温度值
     */

    public void setTemp(final double temp) {
        //				new Thread(){
        //					public void run() {
        //						double cha =  Math.abs(temp-currentTemp);
        //						System.out.println("tempֵΪ"+temp);
        //						if(currentTemp<temp){
        //							for(int i=0;i<=cha;i++){
        //		//						currentTemp +=1;
        //								if(currentTemp<temp){
        //									currentTemp+=1;
        //								}else{
        //									currentTemp=temp;
        //								}
        //								postInvalidate();
        //								try {
        //									this.sleep(2000/cha);
        //								} catch (InterruptedException e) {
        //									e.printStackTrace();
        //								}
        //							}
        //						}else{//再试试
        //							for(int i=0;i<=cha;i++){
        //		//						currentTemp -=1;
        //								if(currentTemp>temp){
        //									currentTemp-=1;
        //								}else{
        //									currentTemp= temp;
        //								}
        //								postInvalidate();
        //								try {
        //									this.sleep(2000/cha);
        //								} catch (InterruptedException e) {
        //									e.printStackTrace();
        //								}
        //							}
        //						}
        //
        //
        //					};
        //				}.start();
        currentTemp = temp;
        invalidate();

    }

    private void init(Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        paintOutWidth = (int) (paintOutWidth * scale + 0.5f);
        paintCenterWidth = (int) (paintCenterWidth * scale + 0.5f);
        paintTempWidth = (int) (paintTempWidth * scale + 0.5f);
        anglePerTemp = 360 / MaxTemp;
        paintOut = new Paint();
        paintOut.setColor(getResources().getColor(R.color.outcolor));
        paintOut.setAntiAlias(true);
        paintOut.setStyle(Style.STROKE);
        paintOut.setStrokeWidth(paintOutWidth);

        paintCenter = new Paint();
        paintCenter.setAntiAlias(true);
        paintCenter.setStyle(Style.STROKE);
        paintCenter.setStrokeWidth(paintCenterWidth);

        paintTemp = new Paint();
        paintTemp.setAntiAlias(true);
        paintTemp.setStyle(Style.STROKE);
        paintTemp.setStrokeWidth(paintTempWidth);
        paintTemp.setStrokeCap(Paint.Cap.ROUND);

        paintText = new Paint();
        paintText.setAntiAlias(true);
        paintText.setStyle(Style.STROKE);
        paintText.setColor(getResources().getColor(R.color.bluesky));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (width == 0 || height == 0) {
            width = getWidth();
            height = getHeight();
            circleRadius = Math.min(width, height) / 2 - paintOutWidth;
            centerX = width / 2;
            centerY = height / 2;
            centercircleRadius = circleRadius * 90 / 100;
            tempCiecleRadius = circleRadius * 75 / 100;
        }

    }

    private Rect textBounds = new Rect();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(centerX, centerY, circleRadius, paintOut);
        int[] SWEEP_GRADIENT_COLORS = new int[]{
                getResources().getColor(R.color.bluesky),
                getResources().getColor(R.color.blueheavey),
                getResources().getColor(R.color.bluesky)};
        SweepGradient Gradient = new SweepGradient(centerX, centerY, SWEEP_GRADIENT_COLORS, null);
        paintCenter.setShader(Gradient);
        canvas.drawCircle(centerX, centerY, centercircleRadius, paintCenter);
        RectF oval = new RectF(centerX - tempCiecleRadius, centerY - tempCiecleRadius,
                centerX + tempCiecleRadius, centerY + tempCiecleRadius);
        int[] SWEEP_GRADIENT_COLORS1 = new int[]{
                getResources().getColor(R.color.bluemid),
                getResources().getColor(R.color.bluese),
                getResources().getColor(R.color.blueheavey),
                getResources().getColor(R.color.bluesky),
                getResources().getColor(R.color.bluemid)};
        float[] positions = new float[]{0.0f, 0.25f, 0.6f, 0.75f, 1.0f};
        SweepGradient Gradient1 = new SweepGradient(centerX, centerY, SWEEP_GRADIENT_COLORS1, positions);
        paintTemp.setShader(Gradient1);
        //		drawTemp(currentTemp*anglePerTemp);
        canvas.drawArc(oval, startAngle, (float) (currentTemp * anglePerTemp), false, paintTemp);

        paintText.setTextSize(tempCiecleRadius >> 2);
        float textlen = paintText.measureText(text);
        paintText.getTextBounds(text, 0, text.length(), textBounds);
        float h1 = textBounds.height();
        float w1 = textBounds.width();
        canvas.drawText(text, centerX - w1 / 2, centerY - h1 / 2 - h1, paintText);
        paintText.setTextSize(tempCiecleRadius >> 1);
        String temp = currentTemp + "℃";
        paintText.getTextBounds(temp, 0, temp.length(), textBounds);
        float h2 = textBounds.height();
        float w2 = textBounds.width();
        canvas.drawText(temp, centerX - w2 / 2, centerY + h2 / 2, paintText);

    }


}
