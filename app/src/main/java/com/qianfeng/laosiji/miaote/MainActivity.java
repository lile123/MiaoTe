package com.qianfeng.laosiji.miaote;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.qianfeng.laosiji.miaote.adapter.MyFragmentPagerAdapter;
import com.qianfeng.laosiji.miaote.fragment.ComicFragment;
import com.qianfeng.laosiji.miaote.fragment.FindFragment;
import com.qianfeng.laosiji.miaote.fragment.MyFragment;
import com.qianfeng.laosiji.miaote.fragment.StreetFragment;
import com.qianfeng.laosiji.miaote.ui.BaiDuActivity;
import com.qianfeng.laosiji.miaote.ui.LoginActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements  RadioGroup.OnCheckedChangeListener{
    @BindView(R.id.vp_main)
    ViewPager viewPager;
    @BindView(R.id.rg_buttom)
    RadioGroup radioGroup;
    @BindView(R.id.rb_comic)
    RadioButton radio_comic;
    @BindView(R.id.rb_street)
    RadioButton radio_street;
    @BindView(R.id.rb_find)
    RadioButton radio_find;
    @BindView(R.id.rb_my)
    RadioButton radio_my;
    private List<Fragment> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        bindView();
        radioGroup.setOnCheckedChangeListener(this);

        initListener();
        RadioButton childAt = (RadioButton) radioGroup.getChildAt(0);
        childAt.setChecked(true);
    }

    /**
     * 监听viewPager
     */
    private void initListener() {
     viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
         @Override
         public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
           if(positionOffset>22){

           }
         }

         @Override
         public void onPageSelected(int position) {
             switch (position) {
                 case 0:
                     radio_comic.setChecked(true);
                     break;
                 case 1:
                     radio_street.setChecked(true);
                     break;
                 case 2:
                     radio_find.setChecked(true);
                     Intent intent2 = new Intent(MainActivity.this, BaiDuActivity.class);
                     startActivity(intent2);
                     break;
                 case 3:
                     Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                     startActivity(intent);
                     radio_my.setChecked(true);
                     break;
             }

         }

         @Override
         public void onPageScrollStateChanged(int state) {

         }
     });
    }

    /**
     * 绑定适配器
     */
    private void bindView() {
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),data);
        viewPager.setAdapter(adapter);
    }

    /**
     * 初始化fragment 并添加到list集合
     */
    private void initData() {
        data = new ArrayList<>();
        data.add(ComicFragment.newInstance());
        data.add(StreetFragment.newInstance());
        data.add(FindFragment.newInstance());
        data.add(MyFragment.newInstance());

    }
    /**
     * 监听RadioGroup
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId){
            case R.id.rb_comic:
                viewPager.setCurrentItem(0);
                break;
            case R.id.rb_street:
                viewPager.setCurrentItem(1);
                break;
            case R.id.rb_find:
                viewPager.setCurrentItem(2);
                break;
            case R.id.rb_my:
                viewPager.setCurrentItem(3);
                break;
        }
    }

}
