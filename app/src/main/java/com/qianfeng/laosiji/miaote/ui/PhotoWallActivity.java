package com.qianfeng.laosiji.miaote.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.qianfeng.laosiji.miaote.R;
import com.qianfeng.laosiji.miaote.adapter.MyViewPagerAdapter;
import com.qianfeng.laosiji.miaote.constant.URLConsatant;

import java.util.ArrayList;
import java.util.List;

public class PhotoWallActivity extends AppCompatActivity {

    private ViewPager vp;
    private String[] urls;
    private List<ImageView> data = new ArrayList<>();
    private MyViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_wall);
        Intent intent = getIntent();
        int position =intent.getIntExtra("position",0);
        urls =intent.getStringArrayExtra("urls");
       vp = (ViewPager) findViewById(R.id.photo_wall_vp);
        initData();
        adapter = new MyViewPagerAdapter(data);
        vp.setAdapter(adapter);
        vp.setCurrentItem(position);
    }

    private void initData() {
        for(int i =0;i<urls.length;i++){
            ImageView imageView=new ImageView(this);
            ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            imageView.setLayoutParams(params);
            //设置图片铺满屏幕
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(PhotoWallActivity.this).load(URLConsatant.URL_BASE + urls[i]).into(imageView);
            data.add(imageView);
        }
    }
}
