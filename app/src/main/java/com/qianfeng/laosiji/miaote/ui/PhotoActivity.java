package com.qianfeng.laosiji.miaote.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.qianfeng.laosiji.miaote.R;
import com.qianfeng.laosiji.miaote.constant.URLConsatant;
import com.squareup.picasso.Picasso;

public class PhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        Intent intent = getIntent();
        String url =intent.getStringExtra("url");
        ImageView iv_show = (ImageView) findViewById(R.id.photo_iv);
        Picasso.with(this).load(URLConsatant.URL_BASE+url).into(iv_show);
    }
}
