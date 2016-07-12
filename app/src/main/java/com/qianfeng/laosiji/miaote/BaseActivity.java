package com.qianfeng.laosiji.miaote;

import android.support.v7.app.AppCompatActivity;

import com.umeng.analytics.MobclickAgent;

/**
 * Created by Administrator on 2016/7/12.
 */
public class BaseActivity extends AppCompatActivity{
    @Override
    protected void onResume() {
        super.onResume();
        //友盟统计
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
