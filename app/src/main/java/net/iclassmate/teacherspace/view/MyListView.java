package net.iclassmate.teacherspace.view;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

import net.iclassmate.teacherspace.R;
/**
 *
 * Created by xyd on 2016/1/29.
 */
public class MyListView extends ListView implements Handler.Callback {

	private Paint mPaint;
	private RectF mRectF;
	private Bitmap mPullToTopBmp;

	private int mPosition = 0, mTotalCount = 0;
	private int mCountPerScreen = 1;
	private boolean isScrolling = false;

	private int screenWidth, iconWidth;
	private float margin;

	private static final float radius = 10f;

	private Handler mHandler;

	public MyListView(Context context) {
		super(context);
		init(context);
	}

	public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public MyListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	private void init(Context context) {
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mRectF = new RectF();

		mPullToTopBmp = BitmapFactory.decodeResource(context.getResources(),
				R.mipmap.pulltotop);

		screenWidth = context.getResources().getDisplayMetrics().widthPixels;
		iconWidth = screenWidth / 10;
		margin = screenWidth / 10;
		mHandler = new Handler(this);
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
		if (isScrolling) {

			int fontSize = getResources().getDimensionPixelSize(
					R.dimen.tab_font_size);
			mPaint.setTextSize(fontSize);

			mRectF.left = margin / 4;
			mRectF.top = margin / 4;
			mRectF.right = mRectF.left
					+ mPaint.measureText(mPosition + "/" + mTotalCount)
					+ radius * 2;
			mRectF.bottom = mRectF.top + fontSize + radius * 2;
			// mRectF.set(margin / 4, margin / 4, margin / 4 + screenWidth / 6,
			// margin / 4 + screenWidth / 12);

			mPaint.setColor(Color.rgb(58,190,192));// 设置颜色
			canvas.drawRoundRect(mRectF, radius, radius, mPaint);

			mPaint.setColor(Color.WHITE);
			canvas.drawText(
					mPosition + "/" + mTotalCount,
					mRectF.left + radius,
					// mRectF.left + (mRectF.width() -
					// mPaint.measureText(mPosition + "/" + mTotalCount)) / 2,
					mRectF.top + mRectF.height() / 2
							+ (fontSize - mPaint.getFontMetrics().bottom) / 2,
					mPaint);
		}

		if (mPosition > mCountPerScreen) {
			mRectF.set(getWidth() - iconWidth - margin, getHeight() - iconWidth
					- margin, getWidth() - margin, getHeight() - margin);
			canvas.drawBitmap(mPullToTopBmp, null, mRectF, null);
		}
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_UP:
			if (mPosition > mCountPerScreen) {
				float tux = ev.getX();
				float tuy = ev.getY();

				if (tuy > getHeight() - iconWidth - margin
						&& tuy < getHeight() - margin
						&& tux > getWidth() - iconWidth - margin
						&& tux < getWidth() - margin) {
					setSelection(0);
					mHandler.sendEmptyMessageDelayed(0, 200);
					return true;
				}
			}
			break;
		}
		return super.dispatchTouchEvent(ev);
	}

	// public void setFistVisibleItem(int pos) {
	// mPosition = pos + 1;
	// }
	//
	// public void scrollIdle() {
	// isScrolling = false;
	// postInvalidate();
	// }
	//
	// public void scrolling() {
	// isScrolling = true;
	// postInvalidate();
	// }

	@Override
	public boolean handleMessage(Message msg) {
		switch (msg.what) {
		case 0:
			if (isScrolling) {
				isScrolling = false;
				postInvalidate();
			}
			break;
		}
		return true;
	}

	// public abstract void setFistVisibleItem(int pos);
	// public abstract void scrolling(boolean scrolled);
	public void setFistVisibleItem(int pos, int count, int perScreen) {
		mPosition = pos;
		mTotalCount = count;
		if (mTotalCount <= 0)
			mPosition = mTotalCount;
		mCountPerScreen = perScreen;
		postInvalidate();
	}

	public void scrolling(boolean scrolled) {
		isScrolling = scrolled;
		postInvalidate();
	}
}