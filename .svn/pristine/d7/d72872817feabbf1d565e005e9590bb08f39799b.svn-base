package net.iclassmate.teacherspace.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 石中玉
 * 自定义的ListView
 * Created by xydbj on 2016/1/29.
 */
public class FullListView extends ListView{
    public FullListView(Context context) {
        super(context);
    }

    public FullListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(
                50000,
                MeasureSpec.AT_MOST
        );
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
