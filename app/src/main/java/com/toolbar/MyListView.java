package com.toolbar;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class MyListView extends ListView {
    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int a=MeasureSpec.makeMeasureSpec(MeasureSpec.EXACTLY>>2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, a);
    }
}
