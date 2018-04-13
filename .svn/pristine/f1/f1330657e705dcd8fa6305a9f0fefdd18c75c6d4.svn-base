package net.iclassmate.teacherspace.view.weike;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by xydbj on 2016/5/11.
 */
public class CanvasImageView extends ImageView {
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path mPath;
    private Paint mBitmapPaint;// 画布的画笔
    private Paint mPaint;// 真实的画笔
    private float mX, mY;// 临时点坐标
    private static final float TOUCH_TOLERANCE = 4;

    // 保存Path路径的集合,用List集合来模拟栈
    private static List<DrawPath> savePath;
    // 记录Path路径的对象
    private DrawPath dp;

    private int screenWidth, screenHeight;

    private int mColor;
    private float mPaintWidth;
    private int mAlpha;
    private Xfermode mXfermode;

    public CanvasImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasImageView(Context context) {
        super(context, null);
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    private class DrawPath {
        public Path path;// 路径
        public Paint paint;// 画笔
    }

    private void init() {
        mBitmap = Bitmap.createBitmap(getWidth(), getHeight(),
                Bitmap.Config.ARGB_8888);
        // 保存一次一次绘制出来的图形
        mCanvas = new Canvas(mBitmap);

        mBitmapPaint = new Paint(Paint.DITHER_FLAG);
        mPaintWidth = 6f;
        mColor = Color.RED;
        mAlpha = 255;
        mXfermode = null;

        setPaint();
        savePath = new ArrayList<DrawPath>();
    }

    private void setPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);// 设置外边缘
        mPaint.setStrokeCap(Paint.Cap.ROUND);// 形状
        mPaint.setStrokeWidth(mPaintWidth);// 画笔宽度
        mPaint.setColor(mColor);
        mPaint.setAlpha(mAlpha);
        mPaint.setXfermode(mXfermode);
    }

    @Override
    public void onDraw(Canvas canvas) {
        //canvas.drawColor(0xFFAAAAAA);
        // 将前面已经画过得显示出来
        if (mBitmap == null || mCanvas == null) {
            init();
        }
        canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
        if (mPath != null) {
            // 实时的显示
            canvas.drawPath(mPath, mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 每次down下去重新new一个Path
                mPath = new Path();
                setPaint();
                // 每一次记录的路径对象是不一样的
                dp = new DrawPath();
                dp.path = mPath;
                dp.paint = mPaint;
                touch_start(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touch_move(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touch_up();
                invalidate();
                break;
        }
        return true;
    }

    private void touch_start(float x, float y) {
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    private void touch_move(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(mY - y);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            // 从x1,y1到x2,y2画一条贝塞尔曲线，更平滑(直接用mPath.lineTo也是可以的)
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
    }

    private void touch_up() {
        mPath.lineTo(mX, mY);
        mCanvas.drawPath(mPath, mPaint);
        // 将一条完整的路径保存下来(相当于入栈操作)
        savePath.add(dp);
        mPath = null;// 重新置空
        mPaint = null;
    }

    /**
     * 撤销的核心思想就是将画布清空， 将保存下来的Path路径最后一个移除掉， 重新将路径画在画布上面。
     */
    public boolean undo() {
        boolean ret = true;
        if (savePath != null && savePath.size() > 0) {
            savePath.remove(savePath.size() - 1);
            redrawOnBitmap();
        } else {
            ret = false;
        }
        return ret;
    }

    /**
     * 重做
     */
    public void redo() {
        if (savePath != null && savePath.size() > 0) {
            savePath.clear();
            redrawOnBitmap();
        }
    }

    private void redrawOnBitmap() {
        mBitmap = Bitmap.createBitmap(getWidth(), getHeight(),
                Bitmap.Config.ARGB_8888);
        mCanvas.setBitmap(mBitmap);// 重新设置画布，相当于清空画布
        Iterator<DrawPath> iter = savePath.iterator();
        while (iter.hasNext()) {
            DrawPath drawPath = iter.next();
            mCanvas.drawPath(drawPath.path, drawPath.paint);
        }
        invalidate();// 刷新
    }

    public void saveToSDCard() {
        String fileUrl = Environment.getExternalStorageDirectory().toString()
                + "/android/data/test.png";
        try {
            FileOutputStream fos = new FileOutputStream(new File(fileUrl));
            mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getmColor() {
        return mColor;
    }

    public void setmColor(int mColor) {
        this.mColor = mColor;
        mAlpha = 255;
        mXfermode = null;
    }

    public float getmPaintWidth() {
        return mPaintWidth;
    }

    public void setmPaintWidth(float mPaintWidth) {
        this.mPaintWidth = mPaintWidth;
        mAlpha = 255;
        mXfermode = null;
    }

    public Xfermode getmXfermode() {
        return mXfermode;
    }

    public void setmXfermode(Xfermode mXfermode) {
        this.mXfermode = mXfermode;
        mAlpha = 0;
    }
}
