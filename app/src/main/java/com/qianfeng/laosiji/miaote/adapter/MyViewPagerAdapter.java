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
    public MyViewPagerAdapter(List<ImageView> data) {
        this.data = data;
    }
    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }

    /**
     * 返回 当前页面对要进入的页面是否为同一个对象
     * @param view
     * @param object
     * @return
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;//固定的写法
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
