package com.qianfeng.laosiji.miaote.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by Administrator on 2016/7/14.
 */
public class InnerListView extends ListView {

    public InnerListView(Context context) {
        this(context,null);
    }

    public InnerListView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public InnerListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST));
    }
}
