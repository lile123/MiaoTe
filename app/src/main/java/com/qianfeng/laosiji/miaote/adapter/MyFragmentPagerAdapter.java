package com.qianfeng.laosiji.miaote.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by lenovo11 on 2016/7/12.
 */
public class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> data;

    public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> data) {
        super(fm);
        this.data=data;
    }

    @Override
    public Fragment getItem(int position) {
        return data!=null?data.get(position):null;
    }

    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }
}
