package net.iclassmate.teacherspace.bean.weike;

import android.graphics.Xfermode;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xydbj on 2016/5/12.
 */
public class Path2 implements Serializable {
    public List<DrawPath2> dp;
    public int mPaintColr;//画笔颜色
    public float mPaintWidth;//画笔宽度
    public Xfermode mXfermode;//画笔样式
    public int mAlpha;//画笔透明度
    public long sleeptime;

    public List<DrawPath2> getDp() {
        return dp;
    }

    public void setDp(List<DrawPath2> dp) {
        this.dp = dp;
    }

    public int getmPaintColr() {
        return mPaintColr;
    }

    public void setmPaintColr(int mPaintColr) {
        this.mPaintColr = mPaintColr;
    }

    public float getmPaintWidth() {
        return mPaintWidth;
    }

    public void setmPaintWidth(float mPaintWidth) {
        this.mPaintWidth = mPaintWidth;
    }

    public Xfermode getmXfermode() {
        return mXfermode;
    }

    public void setmXfermode(Xfermode mXfermode) {
        this.mXfermode = mXfermode;
    }

    public int getmAlpha() {
        return mAlpha;
    }

    public void setmAlpha(int mAlpha) {
        this.mAlpha = mAlpha;
    }
}
