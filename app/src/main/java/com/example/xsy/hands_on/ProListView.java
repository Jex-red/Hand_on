package com.example.xsy.hands_on;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by XSY on 2017/8/1.
 */

public class ProListView extends ListView {
    public ProListView(Context context) {
        super(context);
    }

    public ProListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
