package net.iclassmate.teacherspace.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by xydbj on 2016/5/5.
 */
public class FullGridView extends GridView {
    public FullGridView(Context context) {
        super(context);
    }

    public FullGridView(Context context, AttributeSet attrs) {
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
