package com.qianfeng.laosiji.miaote.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by lenovo11 on 2016/7/19.
 */
public class CustomListview extends ListView {

    private TransparentToolBar mTransparentToolBar;
    private float startY;
    private float distanceY;
    private IDistance iDistance;

    public CustomListview(Context context) {
        super(context);
    }

    public CustomListview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomListview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch(ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                float startX = ev.getX();
               startY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float endX = ev.getX();
                float endY = ev.getY();
                distanceY = Math.abs(endY-startY);
                startY = endY;
                //3接口类调用接口方法
                iDistance.diatanceY(distanceY);
                break;
            case MotionEvent.ACTION_UP:
        }
        return super.onTouchEvent(ev);

    }

    /** 注入ToolBar */
    public void setTitleBar(TransparentToolBar titleBar) {
        mTransparentToolBar = titleBar;
    }

    //1定义接口
    public interface IDistance{
        void diatanceY(float distanceY);
    }
    //2构造监听的方法
    public void setIDistance(IDistance iDistance){
        this.iDistance = iDistance;
    }
}
