package com.qianfeng.laosiji.miaote.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by lenovo11 on 2016/7/21.
 */
public class MyViewPagerAdapter extends PagerAdapter{
    private List<ImageView> data ;
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
    /**
     * 把页面加入到ViewPager
     * @param container  ViewPager
     * @param position   下标
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(data.get(position));
        return data.get(position);
    }

    /**
     * 删除已经滑出去的页面
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(data.get(position));
    }

}
