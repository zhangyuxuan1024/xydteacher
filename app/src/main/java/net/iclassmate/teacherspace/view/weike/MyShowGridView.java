package net.iclassmate.teacherspace.view.weike;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by xydbj on 2016/5/5.
 */
public class MyShowGridView extends GridView{
    private int mHeight;

    public int getmHeight() {
        return mHeight;
    }

    public void setmHeight(int mHeight) {
        this.mHeight = mHeight;
    }

    public MyShowGridView(Context context) {
        super(context);
    }

    public MyShowGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyShowGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(
                getmHeight(),
                MeasureSpec.AT_MOST
        );
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
