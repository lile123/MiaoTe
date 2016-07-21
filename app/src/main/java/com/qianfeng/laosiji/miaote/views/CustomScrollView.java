package com.qianfeng.laosiji.miaote.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2016/7/20.
 */
public class CustomScrollView extends ScrollView {

    private float startX;
    private float startY;
    private float distanceX;
    private float distanceY;
    private IOnScroll onScrollListener;

    public CustomScrollView(Context context) {
        this(context ,null);
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                distanceX = 0;
                distanceY = 0;
                startX = ev.getX();
                startY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float curX = ev.getX();
                float curY = ev.getY();
                distanceX = Math.abs(curX-startX);
                distanceY = Math.abs(curY-startY);
                startX = curX;
                startY = curY;
                if(distanceX > distanceY){
                    return false;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    public interface IOnScroll{
        void onScroll(int l, int t, int oldl, int oldt);
    }

    public void setOnScrollListener(IOnScroll onScrollListener){
        this.onScrollListener = onScrollListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        onScrollListener.onScroll(l, t, oldl, oldt);
    }
}
