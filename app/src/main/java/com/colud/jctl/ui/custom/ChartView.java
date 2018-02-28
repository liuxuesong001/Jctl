package com.colud.jctl.ui.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.jctl.colud.R;


public class ChartView extends View {
    /**
     * 控件的宽高
     */
    private int width, height;
    /**
     * 横坐标被分为partsX份，纵坐标分为partsY份
     */
    private int partsX, partsY;

    /**
     * 用来画横纵坐标的画笔
     */
    Paint paintXY;
    /**
     * 画网格线的画笔
     */
    Paint paintLine;
    /**
     * 画数值点的画笔
     */
    Paint paintPoint;
    /**
     * 画连接线的画笔
     */
    Paint paintLink;
    /**
     * 坐标文字画笔
     */
    Paint paintText;
    /**
     * 填充阴影面积画笔
     */
    Paint paintYinying;
    /**
     * 显示的竖线条数
     */
    private int lineNum = 5;
    private int padding = 20;
    /**
     * 标注距离坐标轴的距离
     */
    private int gap = 4;

    private String[] xNames = {"0", "1:00", "2:00", "3:00", "4:00", "5:00", "6:00", "7:00", "8:00", "9:00", "10:00", "11:00"};
    private String[] yNames = {"", "10", "20", "30", "40"};
    private float[] points = {30, 24, 20, 18, 35, 25, 15, 10, 26, 28, 14, 19};
    private int gapY;
    private int gapX;


    public ChartView(Context context) {
        super(context);
        init(context);
    }


    @SuppressLint("NewApi")
    public ChartView(Context context, AttributeSet attrs, int defStyleAttr,
                     int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }


    public ChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    public ChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (width == 0 || height == 0) {
            width = getWidth();
            height = getHeight();
            gapX = (width - 2 * padding) / lineNum;
            gapY = (height - 2 * padding) / 4;
        }
    }

    /**
     * 设置一屏幕竖线的条数
     *
     * @param num 显示的条数
     */
    public void setVertiLineNum(int num) {
        this.lineNum = num;
    }

    /**
     * 设置显示内容
     *
     * @param xNames 横坐标标注名称
     * @param points 数值点
     */
    public void setDate(String[] xNames, float[] points) {
        this.xNames = xNames;
        this.points = points;
    }

    ;

    /**
     * 绘制
     */
    public void start() {
        invalidate();
    }

    void init(Context context) {

        gap = changeWidth(context, gap);
        paintXY = new Paint();
        paintXY.setARGB(100, 34, 34, 34);
        paintXY.setAntiAlias(true);
        paintXY.setStyle(Style.FILL);
        padding = changeWidth(context, padding);

        paintLine = new Paint();
        paintLine.setARGB(100, 255, 255, 255);

        paintPoint = new Paint();
        paintPoint.setAntiAlias(true);
        paintPoint.setColor(getResources().getColor(R.color.bluemid));

        paintLink = new Paint();
        paintLink.setAntiAlias(true);
        paintLink.setStyle(Style.STROKE);
        PathEffect pathEffect = new CornerPathEffect(30);

        paintLink.setPathEffect(pathEffect);
        paintLink.setColor(getResources().getColor(R.color.bluesky));

        paintYinying = new Paint();
        paintYinying.setAntiAlias(true);
        paintYinying.setPathEffect(pathEffect);
        paintYinying.setColor(getResources().getColor(R.color.bluesky));

        paintText = new Paint();
        paintText.setAntiAlias(true);
        paintText.setTextSize(changeWidth(context, 14));
        paintText.setARGB(100, 34, 34, 34);

    }

    float moveShow;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect textbox = new Rect();

        //画横纵坐标
        canvas.drawLine(0 + padding, height - padding, width - padding, height - padding, paintXY);
        canvas.drawLine(0 + padding, height - padding, 0 + padding, 0 + padding, paintXY);

        canvas.drawRect(0 + padding, 0 + padding, width - padding, height - padding, paintXY);

        //画横线
        for (int i = 0; i <= 4; i++) {
            canvas.drawLine(padding, (height - padding) - i * gapY, width - padding, (height - padding) - i * gapY, paintLine);
        }

        //画纵坐标标准
        for (int i = 0; i <= 4; i++) {
            paintText.getTextBounds(yNames[i], 0, yNames[i].length(), textbox);
            float h1 = textbox.height();
            float w1 = textbox.width();
            canvas.drawText(yNames[i], padding - w1 - gap, (height - padding) - i * gapY + h1 / 2, paintText);
        }


        Path pathContent = new Path();
        pathContent.addRect(0 + padding, 0 + padding, width - padding, height, Direction.CCW);
        pathContent.addRect(0, height - padding, padding, height, Direction.CCW);
        pathContent.addRect(width - padding, height - padding, width, height, Direction.CCW);

        //画网格线

        canvas.clipPath(pathContent);
        //画竖线
        for (int i = 0; i < xNames.length; i++) {
            if (padding + i * gapX + moveShow >= padding)
                canvas.drawLine(padding + moveShow + i * gapX + moveShow, height - padding, padding + moveShow + i * gapX + moveShow, 0 + padding, paintLine);
        }

        //画横坐标标注
        for (int i = 0; i < xNames.length; i++) {
            paintText.getTextBounds(xNames[i], 0, xNames[i].length(), textbox);
            float h1 = textbox.height();
            float w1 = textbox.width();
            if (padding + i * gapX + moveShow >= padding)
                canvas.drawText(xNames[i], (int) (padding + moveShow + i * gapX - w1 / 2 + moveShow), height - padding + gap + h1, paintText);
        }

        //画点
        for (int i = 0; i < points.length; i++) {
            if ((padding + i * gapX + moveShow) >= padding)
                drawPoint(canvas, (int) (padding + moveShow + i * gapX + moveShow), (int) (height - padding - points[i] * ((height - 2 * padding) / 40)));
        }

        //画线及填充

        Path path = new Path();
        path.moveTo(padding + moveShow, height - padding);
        for (int i = 0; i < points.length; i++) {
            if (padding + i * gapX + moveShow >= padding)
                path.lineTo((int) (padding + moveShow + i * gapX + moveShow), height - padding - points[i] * ((height - 2 * padding) / 40));
        }
        canvas.drawPath(path, paintLink);

        path.lineTo((int) (padding + moveShow + points.length * gapX + moveShow), height - padding);
        path.lineTo((int) (padding + moveShow + moveShow), height - padding);
        path.close();
        LinearGradient gradient = new LinearGradient(padding + 3 * gapX, height - padding - 40 * ((height - 2 * padding) / 40), padding + 3 * gapX, height - padding, getResources().getColor(R.color.blueskystart), getResources().getColor(R.color.blueskyend), TileMode.CLAMP);
        paintYinying.setShader(gradient);
        canvas.drawPath(path, paintYinying);
    }

    /**
     * 用来转换单位适配屏幕
     *
     * @param context
     * @param x
     * @return
     */
    int changeWidth(Context context, int x) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (x * scale + 0.5f);
    }


    void drawPoint(Canvas canvas, int x, int y) {
        canvas.drawCircle(x, y, 4, paintPoint);
        paintPoint.setColor(Color.WHITE);
        paintPoint.setStyle(Style.STROKE);
        paintPoint.setStrokeWidth(2);
        canvas.drawCircle(x, y, 8, paintPoint);
        paintPoint.setColor(getResources().getColor(R.color.bluemid));
        paintPoint.setStyle(Style.FILL);
    }

    ;

    float downX = 0;
    float downY = 0;
    float upX = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                upX = event.getX();
                if (Math.abs(upX - downX) > 5) {
                    moveShow += (upX - downX);
                    if (moveShow > 0) {
                        moveShow = 0;
                    }
                }
                postInvalidate();
                break;
            default:
                break;
        }
        return true;
    }
}
