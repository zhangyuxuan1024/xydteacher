package net.iclassmate.teacherspace.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import net.iclassmate.teacherspace.R;
import net.iclassmate.teacherspace.utils.DensityUtil;

import java.util.List;

/**
 * Created by xydbj on 2016/3/8.
 */
public class StateView extends View {
    private Paint mPaint;
    private List<Double> mPointX;
    private List<Double> mPointY;

    private int mWidth;
    private int mHeight;
    private boolean flag;

    public StateView(Context context) {
        super(context);
        init(context, null);
    }

    public StateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mPaint = new Paint();
        flag = false;
        mHeight = DensityUtil.dip2px(context, 49 * 4);
        mWidth = getmWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        mWidth = canvas.getWidth();
//        mHeight = canvas.getHeight();zcl
        //Log.i("info", "s宽度=" + mWidth + "，s高度=" + mHeight);
        float x = 0, y = 0;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.img_xiaohuangquan);
        if (getmPointX() != null && getmPointX().size() > 0 && getmPointY() != null && getmPointY().size() > 0) {
            int len = mPointX.size();
            for (int i = 0; i < len; i++) {
                x = (float) (mWidth * mPointX.get(i));
                y = (float) (mHeight * (1 - mPointY.get(i)));
                //canvas.drawBitmap(bitmap, 5 + 3 * i, i * 6 + 5, mPaint);
                canvas.drawBitmap(bitmap, x, y, mPaint);
                //Log.i("info", "第" + i + "点x=" + x + ",y=" + y);
            }
            flag = true;
        }
        if (!flag) {
            invalidate();
        }
        super.onDraw(canvas);
    }

    public List<Double> getmPointX() {
        return mPointX;
    }

    public void setmPointX(List<Double> mPointX) {
        this.mPointX = mPointX;
    }

    public List<Double> getmPointY() {
        return mPointY;
    }

    public void setmPointY(List<Double> mPointY) {
        this.mPointY = mPointY;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getmWidth() {
        return mWidth;
    }

    public void setmWidth(int mWidth) {
        this.mWidth = mWidth;
    }

    public int getmHeight() {
        return mHeight;
    }

    public void setmHeight(int mHeight) {
        this.mHeight = mHeight;
    }
}
