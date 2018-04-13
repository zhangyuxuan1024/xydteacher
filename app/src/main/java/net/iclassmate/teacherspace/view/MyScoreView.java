package net.iclassmate.teacherspace.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import net.iclassmate.teacherspace.R;

/**
 * Created by xydbj on 2016/4/27.
 */
public class MyScoreView extends View {
    private Context mContext;
    private String score;

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public MyScoreView(Context context) {
        super(context);
        mContext = context;
    }

    public MyScoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawScore(mContext, canvas);
    }

    private void drawScore(Context context, Canvas cv) {
        char[] charArray = score.toCharArray();
        float sX = 0, sY = 0;
        for (int j = 0; j < charArray.length; j++) {
            char c = charArray[j];
            switch (c) {
                case '1':
                    Bitmap n1 = BitmapFactory.decodeResource(
                            context.getResources(), R.mipmap.no_1);
                    // Bitmap
                    // n1=ImageBitmapUtil.decodeimagemap.get(Integer.valueOf(R.drawable.no_1));
                    cv.drawBitmap(n1, sX, sY, null);
                    sX += n1.getWidth();
                    n1.recycle();
                    n1 = null;
                    break;
                case '2':
                    Bitmap n2 = BitmapFactory.decodeResource(
                            context.getResources(), R.mipmap.no_2);
                    // Bitmap
                    // n2=ImageBitmapUtil.decodeimagemap.get(Integer.valueOf(R.drawable.no_2));
                    cv.drawBitmap(n2, sX, sY, null);
                    sX += n2.getWidth();
                    n2.recycle();
                    n2 = null;
                    break;
                case '3':
                    Bitmap n3 = BitmapFactory.decodeResource(
                            context.getResources(), R.mipmap.no_3);
                    // Bitmap
                    // n3=ImageBitmapUtil.decodeimagemap.get(Integer.valueOf(R.drawable.no_3));
                    cv.drawBitmap(n3, sX, sY, null);
                    sX += n3.getWidth();
                    n3.recycle();
                    n3 = null;
                    break;
                case '4':
                    Bitmap n4 = BitmapFactory.decodeResource(
                            context.getResources(), R.mipmap.no_4);
                    cv.drawBitmap(n4, sX, sY, null);
                    sX += n4.getWidth();
                    n4.recycle();
                    n4 = null;
                    break;
                case '5':
                    Bitmap n5 = BitmapFactory.decodeResource(
                            context.getResources(), R.mipmap.no_5);
                    cv.drawBitmap(n5, sX, sY, null);
                    sX += n5.getWidth();
                    n5.recycle();
                    n5 = null;
                    break;
                case '6':
                    Bitmap n6 = BitmapFactory.decodeResource(
                            context.getResources(), R.mipmap.no_6);
                    cv.drawBitmap(n6, sX, sY, null);
                    sX += n6.getWidth();
                    n6.recycle();
                    n6 = null;
                    break;
                case '7':
                    Bitmap n7 = BitmapFactory.decodeResource(
                            context.getResources(), R.mipmap.no_7);
                    cv.drawBitmap(n7, sX, sY, null);
                    sX += n7.getWidth();
                    n7.recycle();
                    n7 = null;
                    break;
                case '8':
                    Bitmap n8 = BitmapFactory.decodeResource(
                            context.getResources(), R.mipmap.no_8);
                    // Bitmap
                    // n8=ImageBitmapUtil.decodeimagemap.get(Integer.valueOf(R.drawable.no_8));
                    cv.drawBitmap(n8, sX, sY, null);
                    sX += n8.getWidth();
                    // n8.recycle();
                    n8 = null;
                    break;
                case '9':
                    Bitmap n9 = BitmapFactory.decodeResource(
                            context.getResources(), R.mipmap.no_9);
                    // Bitmap
                    // n9=ImageBitmapUtil.decodeimagemap.get(Integer.valueOf(R.drawable.no_9));
                    cv.drawBitmap(n9, sX, sY, null);
                    sX += n9.getWidth();
                    n9.recycle();
                    n9 = null;
                    break;
                case '0':
                    Bitmap n0 = BitmapFactory.decodeResource(
                            context.getResources(), R.mipmap.no_0);
                    // Bitmap
                    // n0=ImageBitmapUtil.decodeimagemap.get(Integer.valueOf(R.drawable.no_0));
                    cv.drawBitmap(n0, sX, sY, null);
                    sX += n0.getWidth();
                    n0.recycle();
                    n0 = null;
                    break;
                case '.':
                    Bitmap nd = BitmapFactory.decodeResource(
                            context.getResources(), R.mipmap.no_dian);
                    // Bitmap
                    // nd=ImageBitmapUtil.decodeimagemap.get(Integer.valueOf(R.drawable.no_dian));
                    cv.drawBitmap(nd, sX, sY, null);
                    sX += nd.getWidth();
                    nd.recycle();
                    nd = null;
                    break;
                default:
                    break;
            }
        }
        Bitmap deng = BitmapFactory.decodeResource(context.getResources(),
                R.mipmap.no_00);
        cv.drawBitmap(deng, 0, sY + context.getResources().getDimension(R.dimen.view_26), null);
        deng.recycle();
        deng = null;
    }
}
