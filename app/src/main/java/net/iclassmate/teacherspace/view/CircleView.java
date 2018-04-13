package net.iclassmate.teacherspace.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import net.iclassmate.teacherspace.R;

public class CircleView extends View {
    private static int r;
    // 画实心圆的画笔
    private Paint mCirclePaint;
    // 画圆环的画笔
    private Paint mRingPaint;
    //画整体圆环的画笔
    private Paint mPaint;

    // 画字体的画笔
    private Paint mTextPaint;
    // 圆形颜色
    private int mCircleColor;
    // 圆环颜色
    private int mRingColor;
    private int mRingColorDefault;

    // 半径
    private float mRadius;
    // 圆环半径
    private float mRingRadius;
    //实体圆环半径
    private float mEntityRadius;

    // 圆环宽度
    private float mStrokeWidth;
    // 圆心x坐标
    private int mXCenter;
    // 圆心y坐标
    private int mYCenter;
    // 字的长度
    private float mTxtWidth;
    // 字的高度
    private float mTxtHeight;
    // 总进度
    private int mTotalProgress = 100;
    // 当前进度
    private double mProgress;
    //题难度
    private String energyName;
    //丢失分数
    private String mLostScore;

    //目标摄入元素量
    private String totalFood;
    //目前元素摄入量
    private double curFood = 0;
    //圆环与圆的间隔
    private int spacing = 30;

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 获取自定义的属性
        initAttrs(context, attrs);
        initVariable();
    }

    public void setTitle(String energyName) {
        this.energyName = energyName;
    }

    public void setCurFood(double curFood) {
        this.curFood = curFood;
    }

    public String getmLostScore() {
        return mLostScore;
    }

    public void setmLostScore(String mLostScore) {
        this.mLostScore = mLostScore;
    }

    public double getmProgress() {
        return mProgress;
    }

    public void setmProgress(int mProgress) {
        this.mProgress = mProgress;
    }

    public void setmTotalProgress(int mTotalProgress) {
        this.mTotalProgress = mTotalProgress;
        totalFood = mTotalProgress + "";
        new Thread(new ProgressRunalbe()).start();
    }

    public static void setR(int r2) {
        r = r2;
    }

    /**
     * 初始化
     *
     * @param context
     * @param attrs   控件参数
     */
    public void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typeArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CircleView, 0, 0);
        mRadius = typeArray.getDimension(R.styleable.CircleView_radius_circle, r);
        mStrokeWidth = typeArray.getDimension(R.styleable.CircleView_strokeWidth_circle, getResources().getDimension(R.dimen.view_3));

        mCircleColor = typeArray.getColor(R.styleable.CircleView_circleColor, 0xFFFFFFFF);
        mRingColor = typeArray.getColor(R.styleable.CircleView_ringColor, 0xFFFFFFFF);
        mRingColorDefault = typeArray.getColor(R.styleable.CircleView_ringColorDefault, 0xFFFFFFFF);
        mRingRadius = mRadius + mStrokeWidth + getResources().getDimension(R.dimen.view_4);
        //mEntityRadius = mRingRadius - mStrokeWidth;
        mEntityRadius = mRadius + getResources().getDimension(R.dimen.view_4);
    }

    /**
     * 初始化画笔。
     */
    private void initVariable() {
        //画圆
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(mCircleColor);
        mCirclePaint.setStyle(Paint.Style.FILL);

        //画最外层的圆圈的
        mRingPaint = new Paint();
        mRingPaint.setAntiAlias(true);
        mRingPaint.setColor(mRingColorDefault);
        mRingPaint.setStyle(Paint.Style.STROKE);
        mRingPaint.setStrokeWidth(mStrokeWidth);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#c6cad1"));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(getResources().getDimension(R.dimen.view_2));

        //写字的
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.FILL);
        //mTextPaint.setARGB(255, 0, 0, 0);
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(mRadius / 4);

        Paint.FontMetrics fm = mTextPaint.getFontMetrics();
        mTxtHeight = (int) Math.ceil(fm.descent - fm.ascent);
    }

    public int getmXCenter() {
        return mXCenter;
    }

    public void setmXCenter(int mXCenter) {
        this.mXCenter = mXCenter;
    }

    public int getmYCenter() {
        return mYCenter;
    }

    public void setmYCenter(int mYCenter) {
        this.mYCenter = mYCenter;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mXCenter = getmXCenter();
        mYCenter = getmYCenter();
        mRingPaint.setColor(mRingColorDefault);
        canvas.drawCircle(mXCenter, mYCenter, mRadius, mCirclePaint);
        canvas.drawCircle(mXCenter, mYCenter, mRingRadius, mRingPaint);
        canvas.drawCircle(mXCenter, mYCenter, mEntityRadius, mPaint);

        if (getmProgress() > 0) {
            RectF oval = new RectF();
            oval.left = (mXCenter - mRingRadius);
            oval.top = (mYCenter - mRingRadius);
            oval.right = mRingRadius * 2 + (mXCenter - mRingRadius);
            oval.bottom = mRingRadius * 2 + (mYCenter - mRingRadius);
            mRingPaint.setColor(mRingColor);
            canvas.drawArc(oval, -90, ((float) getmProgress() / mTotalProgress) * 360, false, mRingPaint);

            //设置第一行文字为位置
            mTextPaint.setTextSize(getResources().getDimension(R.dimen.tv_15));
            mTextPaint.setARGB(255, 255, 255, 255);
            mTxtWidth = mTextPaint.measureText(energyName, 0, energyName.length());
            //canvas.drawText(energyName, mXCenter - mTxtWidth / 2, mYCenter - mRadius / 4, mTextPaint);
            canvas.drawText(energyName, mXCenter - mTxtWidth / 2, mYCenter - getResources().getDimension(R.dimen.view_3), mTextPaint);

            String curEn = "共" + totalFood + "分";
            mTextPaint.setTextSize(getResources().getDimension(R.dimen.tv_12));
            mTextPaint.setARGB(255, 255, 255, 255);
            mTxtWidth = mTextPaint.measureText(curEn, 0, curEn.length());
            canvas.drawText(curEn, mXCenter - mTxtWidth / 2, (float) (mYCenter + mTxtHeight), mTextPaint);

            Bitmap bm = BitmapFactory.decodeResource(getResources(), R.mipmap.img_diufen);
            canvas.drawBitmap(bm, mXCenter, (float) (mYCenter - mRadius - getResources().getDimension(R.dimen.view_31)), new Paint());

            mTextPaint.setTextSize(getResources().getDimension(R.dimen.tv_10));
            mTextPaint.setColor(Color.WHITE);
            mTxtWidth = mTextPaint.measureText(mLostScore, 0, mLostScore.length());
            canvas.drawText(mLostScore, mXCenter + 10, (float) (mYCenter - mRadius - getResources().getDimension(R.dimen.view_20)), mTextPaint);
        }
    }

    public void setProgress() {
        postInvalidate();
    }

    /**
     * 刷新进度
     */
    class ProgressRunalbe implements Runnable {
        @Override
        public void run() {
            while (getmProgress() <= curFood) {
                if (getmProgress() < curFood) {
                    mProgress += 0.05;
                } else {
                    mProgress += 0.001;
                }
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setProgress();
            }
        }
    }
}