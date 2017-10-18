package cn.ireader.ti.myapplication;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by zy on 2016/9/22.
 */
public class DoubanView extends View{
    public static final String TAG = "DoubanView";

    private Paint mPaint = new Paint();
    private int mWidth,mHeight;

    private ValueAnimator animator;
    private float animatedValue;
    private long animatorDuration = 5000;
    private TimeInterpolator timeInterpolator = new DecelerateInterpolator();

    public DoubanView(Context context) {
        this(context,null);
    }

    public DoubanView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        initAnimator(animatorDuration);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    private void initPaint(){
        mPaint.setStyle(Paint.Style.FILL);//设置样式
        mPaint.setAntiAlias(true);//设置抗锯齿
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawText(canvas);
//        doubanAnimator(canvas,mPaint);
//       base(canvas);
//        test(canvas);
//        test1(canvas);
//        test2(canvas);
//        test3(canvas);
    }

    private void drawText(Canvas canvas) {
//        canvas.translate(mWidth/2,mHeight/2);
//        Paint paint=new Paint();
//        paint.setColor(Color.RED);  //设置画笔颜色
//
//        paint.setStrokeWidth (5);//设置画笔宽度
//        paint.setAntiAlias(true); //指定是否使用抗锯齿功能，如果使用，会使绘图速度变慢
//        paint.setTextSize(60);//设置文字大小
//        paint.setStyle(Paint.Style.FILL);//绘图样式，设置为填充
//        canvas.drawText("欢迎光临Harvic的博客",10,100, paint);//两个构造函数
//        canvas.drawText("欢迎光临Harvic的博客",10,100 + paint.gette, paint);//两个构造函数

        Paint paint=new Paint();
        paint.setColor(Color.RED);  //设置画笔颜色

        paint.setStrokeWidth (5);//设置画笔宽度
        paint.setAntiAlias(true); //指定是否使用抗锯齿功能，如果使用，会使绘图速度变慢
        paint.setTextSize(80);//设置文字大小
        paint.setStyle(Paint.Style.FILL);//绘图样式，设置为填充

        //变通样式字体
        canvas.drawText("欢迎光临Harvic的博客", 10,100, paint);

        //水平方向拉伸两倍
        paint.setTextScaleX(2);//只会将水平方向拉伸，高度不会变
        canvas.drawText("欢迎光临Harvic的博客", 10,200, paint);

        //写在同一位置,不同颜色,看下高度是否看的不变
        paint.setTextScaleX(1);//先还原拉伸效果
        canvas.drawText("欢迎光临Harvic的博客", 10,300, paint);

        paint.setColor(Color.GREEN);
        paint.setTextScaleX(2);//重新设置拉伸效果
        canvas.drawText("欢迎光临Harvic的博客", 10,400, paint);

    }


    private void doubanAnimator(Canvas canvas, Paint mPaint){
        canvas.translate(mWidth/2,mHeight/2);
        mPaint.setStyle(Paint.Style.STROKE);//描边
        mPaint.setStrokeCap(Paint.Cap.ROUND);//圆角笔触
        mPaint.setColor(Color.rgb(97, 195, 109));
        mPaint.setStrokeWidth(15);
        float point = Math.min(mWidth,mHeight)*0.06f/2;
        float r = point*(float) Math.sqrt(2);
        RectF rectF = new RectF(-r,-r,r,r);
        canvas.save();

        // rotate
        if (animatedValue>=135){
            canvas.rotate(animatedValue-135);
        }

        Log.e(TAG, "doubanAnimator: " + "onDraw执行");
        // draw mouth
        float startAngle=0, sweepAngle=0;
        if (animatedValue<135){
            startAngle = animatedValue +5;
            sweepAngle = 170+animatedValue/3;
        }else if (animatedValue<270){
            startAngle = 135+5;
            sweepAngle = 170+animatedValue/3;
        }else if (animatedValue<630){
            startAngle = 135+5;
            sweepAngle = 260-(animatedValue-270)/5;
        }else if (animatedValue<720){
            startAngle = 135-(animatedValue-630)/2+5;
            sweepAngle = 260-(animatedValue-270)/5;
        }else{
            startAngle = 135-(animatedValue-630)/2-(animatedValue-720)/6+5;
            sweepAngle = 170;
        }
        Log.e(TAG, "doubanAnimator: " + "startAngle : " + startAngle + " ,sweepAngle : " + sweepAngle);
        canvas.drawArc(rectF,startAngle,sweepAngle,false,mPaint);

        // draw eye
        canvas.drawPoints(new float[]{
                -point,-point
                ,point,-point
        },mPaint);

//        canvas.restore();
    }


    private void initAnimator(long duration){
        if (animator !=null &&animator.isRunning()){
            animator.cancel();
            animator.start();
        }else {
            animator=ValueAnimator.ofFloat(0,855).setDuration(duration);
            animator.setInterpolator(timeInterpolator);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    Log.e(TAG, "onAnimationUpdate: " + (float) animation.getAnimatedValue());
                    animatedValue = (float) animation.getAnimatedValue();
                    invalidate();
                }
            });
            animator.start();
        }
    }


    private void test3(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);//不设置描边的时候 默认为填充当前扇形 圆 椭圆
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(10);
        float point = Math.min(mWidth,mHeight)*0.2f/2;
        float r = point*(float) Math.sqrt(2);
        RectF rectF = new RectF(-r,-r,r,r);
        canvas.drawArc(rectF,-180,270,false,mPaint);
    }


    private void test2(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);//设置画笔样式为描边，如果已经设置，可以忽略
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(10);
        float point = Math.min(mWidth,mHeight)*0.2f/2;
        float r = point*(float) Math.sqrt(2);
        RectF rectF = new RectF(-r,-r,r,r); //正方形中绘制扇形
        canvas.drawArc(rectF,0,180,false,mPaint);//参数为 矩形参照图形，开始角度，结束角度，是否绘制中心线，画笔
        canvas.drawPoints(new float[]{
                point,-point
                ,-point,-point
        },mPaint); //这两个点都在以r为半径的圆上
    }

    private void test1(Canvas canvas) {
        float point = Math.min(mWidth,mHeight)*0.06f/2;
        float r = point*(float) Math.sqrt(2);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        canvas.save();
        canvas.rotate(90);
        canvas.drawCircle(200,0,r,mPaint);//圆心(200,0)
        canvas.restore();
        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(200,0,r,mPaint);//圆心(200,0)
    }

    private void test(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);//设置画笔类型
        canvas.drawRect(-mWidth/8,-mHeight/8,mWidth/8,mHeight/8,mPaint);//画一个矩形

        canvas.save(); // 平移之前进行保存
        canvas.translate(200,200);//平移
        mPaint.setColor(Color.BLUE);//设置为蓝色
        canvas.drawRect(new RectF(-mWidth/8,-mHeight/8,mWidth/8,mHeight/8),mPaint);//重新绘制矩形
        canvas.restore();// 恢复现场
        //        canvas.translate(-200,-200); //restore到之前的位置
        mPaint.setColor(Color.BLUE);
        canvas.scale(0.5f,0.5f);//缩放
        canvas.drawRect(new RectF(-mWidth/8,-mHeight/8,mWidth/8,mHeight/8),mPaint);

        canvas.rotate(90);//旋转
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(new RectF(-mWidth/8,-mHeight/8,mWidth/8,mHeight/8),mPaint);

        canvas.skew(1,0.5f);//错切
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(new RectF(-mWidth/8,-mHeight/8,mWidth/8,mHeight/8),mPaint);
    }

    private void base(Canvas canvas) {
        canvas.translate(mWidth/2,mHeight/2);//将画布坐标原点移动到屏幕中心位置
        mPaint.setColor(Color.BLACK);//设置画笔颜色
        mPaint.setStrokeWidth(10);//设置画笔宽度
        canvas.drawPoint(0,0,mPaint);//屏幕中心绘制原点
        canvas.drawPoints(new float[]{mWidth/2*0.8f,0,0,mHeight/2*0.8f,-mWidth/2*0.8f,0,0,-mHeight/2*0.8f},mPaint);//绘制坐标轴节点
        mPaint.setStrokeWidth(1);//恢复画笔默认宽度
        canvas.drawLine(-mWidth/2*0.8f,0,mWidth/2*0.8f,0,mPaint); //绘制X轴
        canvas.drawLine(0,-mHeight/2*0.8f,0,mHeight/2*0.8f,mPaint);//绘制Y轴
        mPaint.setStrokeWidth(10);//设置画笔宽度

        //画的顺序是从X,Y开始。先画X轴，后画Y轴
        canvas.drawPoints(new float[]{mWidth/2*0.8f - mWidth/2*0.8f*0.05f,-mWidth/2*0.8f*0.05f,mWidth/2*0.8f - mWidth/2*0.8f*0.05f,mWidth/2*0.8f*0.05f},mPaint);//绘制X坐标轴节点
        canvas.drawPoints(new float[]{mWidth/2*0.8f*0.05f,mHeight/2*0.8f - mWidth/2*0.8f*0.05f,-mWidth/2*0.8f*0.05f,mHeight/2*0.8f - mWidth/2*0.8f*0.05f},mPaint);//绘制Y坐标轴节点
        mPaint.setStrokeWidth(3);
        canvas.drawLines(new float[]{
                        mWidth/2*0.8f,0,mWidth/2*0.8f*0.95f,-mWidth/2*0.8f*0.05f,mWidth/2*0.8f,0,mWidth/2*0.8f*0.95f,mWidth/2*0.8f*0.05f},
                mPaint);//绘制X轴箭头
        //        canvas.scale(1,-1);//翻转Y轴
        canvas.drawLines(new float[]{
                        0,mHeight/2*0.8f,mWidth/2*0.8f*0.05f,mHeight/2*0.8f-mWidth/2*0.8f*0.05f,0,mHeight/2*0.8f,-mWidth/2*0.8f*0.05f,mHeight/2*0.8f-mWidth/2*0.8f*0.05f,}
                ,mPaint);//绘制Y轴箭头
    }

}
