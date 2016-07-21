package com.qianfeng.laosiji.miaote.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.qianfeng.laosiji.miaote.R;

public class PhotoWallActivity extends AppCompatActivity {

    private ViewPager vp;
    private String[] urls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_wall);
        Intent intent = getIntent();
        intent.getIntExtra("position",0);
        urls =intent.getStringArrayExtra("urls");
       vp = (ViewPager) findViewById(R.id.photo_wall_vp);
        initData();
    }

    private void initData() {
        for(int i =0;i<urls.length;i++){

        }
    }
}
