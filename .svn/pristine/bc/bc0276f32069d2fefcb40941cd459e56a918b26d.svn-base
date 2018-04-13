package net.iclassmate.teacherspace.view.weike;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import net.iclassmate.teacherspace.R;

/**
 * Created by xydbj on 2016/5/19.
 */
public class PagerView extends View {
    private String mColor;
    private Canvas mCanvas;

    public PagerView(Context context) {
        super(context);
        init();
    }

    public PagerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mColor = "#f1e1b5";
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.parseColor("#00000000"));
        //拖动
        Bitmap bitmap = BitmapFactory.decodeResource(this.getContext().getResources(), R.mipmap.ic_tuodong);

        Bitmap bitmap2 = Bitmap.createBitmap(getWidth() - bitmap.getWidth() / 3, getHeight() - bitmap.getHeight() / 3, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(bitmap2);
        mCanvas.drawColor(Color.parseColor(mColor));
        mCanvas.drawBitmap(bitmap2, 0, 0, new Paint());
        canvas.drawBitmap(bitmap2, bitmap.getWidth() / 3, bitmap.getHeight() / 3, new Paint());
        canvas.drawBitmap(bitmap, 0, 0, new Paint());

        //进入
        Bitmap bitmap3 = BitmapFactory.decodeResource(this.getContext().getResources(), R.mipmap.ic_in);
        canvas.drawBitmap(bitmap3, bitmap.getWidth() / 3, getHeight() - this.getContext().getResources().getDimension(R.dimen.view_135), new Paint());

        //草稿
        bitmap = BitmapFactory.decodeResource(this.getContext().getResources(), R.mipmap.ic_caogao);
        canvas.drawBitmap(bitmap, getWidth() - this.getContext().getResources().getDimension(R.dimen.view_12) - bitmap.getWidth(),
                getHeight() - this.getContext().getResources().getDimension(R.dimen.view_12) - bitmap.getHeight(), new Paint());

        bitmap2 = BitmapFactory.decodeResource(this.getContext().getResources(), R.mipmap.ic_xinzeng2);
        bitmap3 = Bitmap.createBitmap((int) this.getContext().getResources().getDimension(R.dimen.view_35), (int) this.getContext().getResources().getDimension(R.dimen.view_28), Bitmap.Config.RGB_565);
        Canvas mCanvas = new Canvas(bitmap3);
        mCanvas.drawColor(Color.parseColor("#e4ce93"));
        mCanvas.drawBitmap(bitmap2, bitmap3.getWidth() / 2 - bitmap2.getWidth() / 2, bitmap3.getHeight() / 2 - bitmap2.getHeight() / 2, null);
        canvas.drawBitmap(bitmap3, getWidth() - this.getContext().getResources().getDimension(R.dimen.view_12) - bitmap.getWidth() / 2 - bitmap3.getWidth() / 2,
                getHeight() - this.getContext().getResources().getDimension(R.dimen.view_20) - bitmap.getHeight() - bitmap3.getHeight(), new Paint());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int countPoint = event.getPointerCount();
        if (countPoint > 1) {
            mColor = "#55f1e1b5";
            invalidate();
        }
        return true;
    }
}
