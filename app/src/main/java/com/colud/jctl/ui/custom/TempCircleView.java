package com.colud.jctl.ui.custom;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.colud.jctl.utils.DimenUtil;
import com.jctl.colud.R;

import java.text.DecimalFormat;

/**
 * 自定义圆形
 * Created by Jcty on 2017/8/30.
 */

public class TempCircleView extends View {

    /**
     * 圆心X坐标
     */
    public int centerX;
    /**
     * 圆心Y坐标
     */
    public int centerY;


    /**
     * 最外边的圆的画笔
     */
    private Paint outPaint;

    /**
     * 最外环的颜色
     */
    private int outsideColor;

    /**
     * 外圆的半径
     */
    private float outsideRadius;

    /**
     * 最外圆的宽度
     */
    private float outWidth = 10.0f;

    /**
     * 中间的渐变色圆的画笔
     */
    private Paint inPaint;

    /**
     * 中间圆的渐变颜色
     */
    int[] SWEEP_GRADIENT_COLORS = new int[]{

            getResources().getColor(R.color.bluesky),
            getResources().getColor(R.color.blueheavey),
            getResources().getColor(R.color.bluesky)
    };

    /**
     * 中间圆的半径
     */
    private float insideRadius;

    /**
     * 中间圆的宽度
     */
    private float inWidth = 8.0f;


    /**
     * 最里面带动画的圆的画笔
     */
    private Paint tempPaint;

    /**
     * temp 圆的半径
     */
    private float tempRadius;

    /**
     * 圆弧起始角度
     */
    private float startAngle = -90.0f;

    /**
     * 文字B画笔
     */
    private Paint tvBPaint;

    /**
     * 文字颜色
     */
    private int progressTextColor;

    /**
     * 文字A大小
     */
    private float progressTextSizeA;
    /**
     * 文字B大小
     */
    private float progressTextSizeB;

    /**
     * 圆环温度值文字
     */
    private String progressTv;

    /**
     * 圆环Title
     */
    private String progressTitle;

    private Rect rectB;

    /**
     * 文字A画笔
     */
    private Paint tvAPaint;

    private Rect rectA;
    /**
     * 返回字符串的值
     */
    private String p;


    /**
     * temp 的渐变颜色
     */

    int[] SWEEP_GRADIENT_COLORS1 = new int[]{

            getResources().getColor(R.color.bluemid),
            getResources().getColor(R.color.bluese),
            getResources().getColor(R.color.blueheavey),
            getResources().getColor(R.color.bluesky),
            getResources().getColor(R.color.bluemid)
    };

    /**
     * temp 宽度
     */
    private float tempWidth = 30.0f;

    private int maxProgress;    //最大进度

    private float progress;    //当前进度

    private int direction;    //进度从哪里开始(设置了4个值,上左下右)


    /**
     * 动画
     */
    private ValueAnimator animator;


    public TempCircleView(Context context) {
        this(context, null);
    }

    public TempCircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public TempCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //获取X坐标
        centerX = getWidth() / 2;
        //获取y坐标
        centerY = getHeight() / 2;
        //最外层的圆
        outPaint.setColor(outsideColor);
        outPaint.setStyle(Paint.Style.STROKE);
        outPaint.setStrokeWidth(outWidth);
        outPaint.setAntiAlias(true);//消锯齿
        //画圆
        canvas.drawCircle(centerX, centerX, outsideRadius, outPaint);
        //中间的圆
        inPaint.setStyle(Paint.Style.STROKE);
        inPaint.setStrokeWidth(inWidth);
        inPaint.setAntiAlias(true);
        /**
         * 设置渐变圆色
         */
        SweepGradient Gradient = new SweepGradient(centerX, centerY, SWEEP_GRADIENT_COLORS, null);
        inPaint.setShader(Gradient);
        //画圆
        canvas.drawCircle(centerX, centerX, insideRadius, inPaint);

        RectF oval = new RectF(centerX - tempRadius, centerY - tempRadius,
                centerX + tempRadius, centerY + tempRadius);
        tempPaint.setStrokeWidth(tempWidth);
        tempPaint.setStyle(Paint.Style.STROKE);
        //让弧线两边是圆滑的
        tempPaint.setStrokeCap(Paint.Cap.ROUND);
        tempPaint.setAntiAlias(true);
        /**
         * 圆弧的头的弧度
         */
        float[] positions = new float[]{0.0f, 0.25f, 0.6f, 0.75f, 1.0f};
        SweepGradient Gradient1 = new SweepGradient(centerX, centerY, SWEEP_GRADIENT_COLORS1, positions);
        tempPaint.setShader(Gradient1);

        /**
         * 根据进度画圆弧
         * oval :指定圆弧的外轮廓矩形区域。
         startAngle: 圆弧起始角度，单位为度。
         sweepAngle: 圆弧扫过的角度，顺时针方向，单位为度,从右中间开始为零度。
         useCenter: 如果为True时，在绘制圆弧时将圆心包括在内，通常用来绘制扇形。
         */
        //		canvas.drawArc(oval, -90.0f,(float) (direction*anglePerTemp), false, tempPaint);
        canvas.drawArc(oval, startAngle, 360 * (progress / maxProgress), false, tempPaint); //根据进度画圆弧
        //第三步:画圆环内百分比文字
        rectB = new Rect();
        tvBPaint.setStrokeWidth(0f);
        tvBPaint.setTextSize(progressTextSizeB);
        tvBPaint.setColor(progressTextColor);
        progressTv = getProgressText();
        /**
         * getTextBounds() 返回在边界(分配)的最小矩形包含所有的字符,以隐含原点(0,0)。
         * text 字符串来衡量并返回它的界限
         * start 索引的第一个字符的字符串来衡量
         * end 1过去的最后一个字符字符串
         * bounds 返回联合边界的所有文本。必须分配给调用者。
         */
        tvBPaint.getTextBounds(progressTv, 0, progressTv.length(), rectB);
        /**
         * 获取文字之间的间距
         */
        Paint.FontMetricsInt fontMetrics = tvBPaint.getFontMetricsInt();
        /**
         * 获得文字的基准线
         */
        int baseline = (getMeasuredHeight() - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
        //画文字
        canvas.drawText(progressTv, getMeasuredHeight() / 2 - rectB.width() / 2, baseline, tvBPaint);
        /**
         * Title
         */
        rectA = new Rect();
        tvAPaint.setStrokeWidth(0f);
        tvAPaint.setColor(progressTextColor);
        tvAPaint.setTextSize(progressTextSizeA);

        /**
         * getTextBounds() 返回在边界(分配)的最小矩形包含所有的字符,以隐含原点(0,0)。
         * text 字符串来衡量并返回它的界限
         * start 索引的第一个字符的字符串来衡量
         * end 1过去的最后一个字符字符串
         * bounds 返回联合边界的所有文本。必须分配给调用者。
         */
        tvAPaint.getTextBounds(getProgressTitle(), 0, getProgressTitle().length(), rectA);
        //画文字
        //		canvas.drawText(progressTitle, centerX - rectA.width() / 2 , centerX / 2 + rectA.height(), tvAPaint);
        canvas.drawText(progressTitle, centerX - rectA.width() / 2, centerX - rectA.height() - 26, tvAPaint);


        super.onDraw(canvas);
    }

    /**
     * 获取自定义属性
     *
     * @param context
     * @param attrs
     */
    private void initAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
        //		float scale = context.getResources().getDisplayMetrics().density;
        TypedArray atr = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TempCircleView, defStyleAttr, 0);
        //圆环属性
        outsideColor = atr.getColor(R.styleable.TempCircleView_outside_color, ContextCompat.getColor(context, R.color.outcolor));
        outsideRadius = atr.getDimension(R.styleable.TempCircleView_outside_radius, DimenUtil.dp2px(context, 70.0f));
        insideRadius = atr.getDimension(R.styleable.TempCircleView_inside_radius, DimenUtil.dp2px(context, 70.f));
        maxProgress = atr.getInt(R.styleable.TempCircleView_max_progress, 100);
        progress = atr.getFloat(R.styleable.TempCircleView_progress, 0.0f);
        direction = atr.getInt(R.styleable.TempCircleView_direction, 3);
        //文字属性
        progressTextColor = atr.getColor(R.styleable.TempCircleView_progress_text_color, ContextCompat.getColor(context, R.color.color_406081));
        progressTextSizeA = atr.getDimension(R.styleable.TempCircleView_progress_text_size_a, DimenUtil.dp2px(context, 10.0f));
        progressTextSizeB = atr.getDimension(R.styleable.TempCircleView_progress_text_size_b, DimenUtil.dp2px(context, 24.0f));

        atr.recycle();


        outPaint = new Paint();
        inPaint = new Paint();
        tempPaint = new Paint();
        tvBPaint = new Paint();
        tvAPaint = new Paint();

    }

    /**
     * 指定大小
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width, height;

        int size = MeasureSpec.getSize(widthMeasureSpec);
        int mode = MeasureSpec.getSize(widthMeasureSpec);

        if (mode == MeasureSpec.EXACTLY) {
            width = size;
        } else {

            width = (int) ((2 * outsideRadius) + outWidth);

            //			centerX=width/2;
        }

        size = MeasureSpec.getSize(heightMeasureSpec);
        mode = MeasureSpec.getSize(heightMeasureSpec);

        if (mode == MeasureSpec.EXACTLY) {
            height = size;
        } else {

            height = (int) ((2 * outsideRadius) + outWidth);

            //			centerY= height / 2;
        }

        /**
         * 中间圆的半径大小
         */
        insideRadius = outsideRadius * 90 / 100;

        /**
         * 最内测圆的半径大小
         */
        tempRadius = insideRadius * 84 / 100;

        setMeasuredDimension(width, height);
    }

    /**
     * 获取温度值
     *
     * @return
     */
    //中间的进度百分比
    //	private String getProgressText() {
    //		if (progressTitle.equals("二氧化碳")){
    //			return (int) ((progress / maxProgress) * 1000) + "%";
    //		}else {
    //			return (int)((progress / maxProgress) * 100) + "℃";
    //		}
    //	}

    //中间的进度百分比
    private String getProgressText() {
        DecimalFormat decimalFormat = new DecimalFormat(".00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        if (progressTitle.equals("二氧化碳")) {
            return (int) ((progress / maxProgress) * 1000) + "%";
        } else {
            //            return (int) ((progress / maxProgress) * 100) + "℃";
            p = decimalFormat.format((progress / maxProgress) * 100) + "℃";
            ;//format 返回的是字符串
        }
        return p;
    }


    /**
     * 获取Title
     *
     * @return
     */
    public String getProgressTitle() {

        return progressTitle;
    }

    public void setProgressTitle(String title) {

        this.progressTitle = title;
    }

    public synchronized int getMaxProgress() {

        return maxProgress;
    }

    public synchronized void setMaxProgress(int maxProgress) {
        if (maxProgress < 0) {
            //此为传递非法参数异常
            throw new IllegalArgumentException("maxProgress should not be less than 0");
        }
        this.maxProgress = maxProgress;
    }


    //    private double maxProgressA = 100.0d;    //最大进度


    //加锁保证线程安全,能在线程中使用
    public synchronized void setProgress(double progress) {
        if (progress < 0.0d) {
            throw new IllegalArgumentException("progress should not be less than 0");
        }
        if (progress > maxProgress) {
            progress = maxProgress;
        }
        startAnim((float) progress);
    }


    public synchronized float getProgress() {

        return progress;
    }
    //加锁保证线程安全,能在线程中使用
    //    public synchronized void setProgress(int progress) {
    //        if (progress < 0) {
    //            throw new IllegalArgumentException("progress should not be less than 0");
    //        }
    //        if (progress > maxProgress) {
    //            progress = maxProgress;
    //        }
    //        startAnim(progress);
    //    }

    private void startAnim(float startProgress) {
        animator = ObjectAnimator.ofFloat(0, startProgress);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                TempCircleView.this.progress = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.setStartDelay(500);
        animator.setDuration(2000);
        animator.setInterpolator(new LinearInterpolator());
        animator.start();
    }

}
