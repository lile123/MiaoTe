package com.handmark.pulltorefresh.library.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.widget.LinearLayout;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.R;

/**
 * Created by Administrator on 2016/7/19.
 */
public class CustomLoadingLayout extends LoadingLayout {

    private AnimationDrawable mAnimationDrawable;

    public CustomLoadingLayout(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.Orientation scrollDirection, TypedArray attrs) {
        super(context, mode, scrollDirection, attrs);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(240,240);
        mHeaderImage.setLayoutParams(params);
        mHeaderImage.setImageResource(R.drawable.drawable_waiting);
        mAnimationDrawable = (AnimationDrawable) mHeaderImage.getDrawable();

    }

    @Override
    protected int getDefaultDrawableResId() {
        return R.drawable.loadcat_01;
    }

    @Override
    protected void onLoadingDrawableSet(Drawable imageDrawable) {
    }

    @Override
    protected void onPullImpl(float scaleOfLayout) {

    }

    @Override
    protected void pullToRefreshImpl() {
        mAnimationDrawable.start();
    }

    @Override
    protected void refreshingImpl() {

    }

    @Override
    protected void releaseToRefreshImpl() {

    }

    @Override
    protected void resetImpl() {

    }
}
