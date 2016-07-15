package com.qianfeng.laosiji.miaote.ui;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by lile on 2016/7/13.
 */
public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(this);
    }

    /**
     * LocationClientOption类，该类用来设置定位SDK的定位方式
     */

}