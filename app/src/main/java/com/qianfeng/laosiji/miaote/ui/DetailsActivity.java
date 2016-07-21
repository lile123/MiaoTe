package com.qianfeng.laosiji.miaote.ui;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidxx.yangjw.httplibrary.IOKCallBack;
import com.androidxx.yangjw.httplibrary.OkHttpTool;
import com.google.gson.Gson;
import com.qianfeng.laosiji.miaote.BaseActivity;
import com.qianfeng.laosiji.miaote.R;
import com.qianfeng.laosiji.miaote.bean.DetailsBean;
import com.qianfeng.laosiji.miaote.fragment.DetailsFragment;
import com.qianfeng.laosiji.miaote.fragment.ShoppingFragment;
import com.qianfeng.laosiji.miaote.views.CustomScrollView;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailsActivity extends BaseActivity {
    public static final String URL_DOMAIN = "http://api.nyato.com";
    public static final String URL_Details = "http://api.nyato.com/index.php?app=android&mod=Expo&act=ex_detail&&token=b9297bc832265b95d68f24c9b65430bc&app_version=3.4&tickets=1";

    private ViewPager vpDetails;
    private List<Fragment> list;
    private TextView tvDetails;
    private TextView tvShopping;
    private ImageView ivDetails;
    private ImageView ivShopping;
    private TextView tvDetailsInvisible;
    private TextView tvShoppingInvisible;
    private ImageView ivDetailsInvisible;
    private ImageView ivShoppingInvisible;
    private CustomScrollView csvDetails;
    private LinearLayout llInvisible;
    private Toolbar tbHeader;
    private TextView tvName;
    private TextView tvHeadName;
    private TextView tvPresalePrice;
    private TextView tvScenePrice;
    private TextView tvStartTime;
    private TextView tvLocation;
    private ImageView ivLogo;
    private HashMap<String, String> map = new HashMap<>();
    private CircleImageView civUserOne;
    private CircleImageView civUserTwo;
    private CircleImageView civUserThree;
    private CircleImageView civUserFour;
    private CircleImageView civUserFive;
    private ImageView ivBackGround;
    private String cover;
    private String name;
    private String presalePrice;
    private String scenePrice;
    private String startTime;
    private String endTime;
    private String location;
    private String userOne;
    private String userTwo;
    private String userThree;
    private String userFour;
    private String userFive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();
        String eid = intent.getStringExtra("eid");
        map.put("eid", eid);
        map.put("p", "1");
        initData();
    }

    private void initData() {

        OkHttpTool.newInstance().start(URL_Details).post(map).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                if (result.length() < 145) {
                    return;
                }
                Gson gson = new Gson();
                DetailsBean detailsBean = gson.fromJson(result, DetailsBean.class);
                cover = detailsBean.getInfo().getCover();
                name = detailsBean.getInfo().getName();
                presalePrice = detailsBean.getInfo().getPresale_price();
                scenePrice = detailsBean.getInfo().getScene_price();
                startTime = detailsBean.getInfo().getStart_time();
                endTime = detailsBean.getInfo().getEnd_time();
                location = detailsBean.getInfo().getLocation();
                userOne = detailsBean.getApply_user().get(0).getUavatar();
                userTwo = detailsBean.getApply_user().get(1).getUavatar();
                userThree = detailsBean.getApply_user().get(2).getUavatar();
                userFour = detailsBean.getApply_user().get(3).getUavatar();
                userFive = detailsBean.getApply_user().get(4).getUavatar();
                initView();
                initAdapter();
                initListener();
            }
        });
    }

    private void initListener() {
        csvDetails.setOnScrollListener(new CustomScrollView.IOnScroll() {
            @Override
            public void onScroll(int l, int t, int oldl, int oldt) {
                if (t <= 600) {
                    float alpha = (float) t / 600;
                    tbHeader.setAlpha(alpha);
                }
                if(t<370){
                    ivBackGround.setVisibility(View.VISIBLE);
                }else{
                    ivBackGround.setVisibility(View.GONE);
                }
                if (t > 910) {
                    llInvisible.setVisibility(View.VISIBLE);
                } else {
                    llInvisible.setVisibility(View.GONE);
                }
            }
        });
        vpDetails.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setSelectedFragment(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tvShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelectedFragment(1);
            }
        });
        tvShoppingInvisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelectedFragment(1);
            }
        });
        tvDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelectedFragment(0);
            }
        });
        tvDetailsInvisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelectedFragment(0);
            }
        });
    }

    private void setSelectedFragment(int position) {
        if (0 == position) {
            ivShopping.setVisibility(View.INVISIBLE);
            ivDetails.setVisibility(View.VISIBLE);
            ivShoppingInvisible.setVisibility(View.INVISIBLE);
            ivDetailsInvisible.setVisibility(View.VISIBLE);
        } else {
            ivShopping.setVisibility(View.VISIBLE);
            ivDetails.setVisibility(View.INVISIBLE);
            ivShoppingInvisible.setVisibility(View.VISIBLE);
            ivDetailsInvisible.setVisibility(View.INVISIBLE);
        }
        vpDetails.setCurrentItem(position);

    }

    private void initAdapter() {
        DetailsAdapter adapter = new DetailsAdapter(getSupportFragmentManager());
        vpDetails.setAdapter(adapter);
    }

    private void initView() {
        initFragment();
        llInvisible = (LinearLayout) findViewById(R.id.ll_details_invisible);
        csvDetails = (CustomScrollView) findViewById(R.id.csv_details);
        ivLogo = (ImageView) findViewById(R.id.iv_details_logo);
        ivBackGround = (ImageView) findViewById(R.id.iv_details_blur);
        tvHeadName = (TextView) findViewById(R.id.tv_details_name_head);
        tvName = (TextView) findViewById(R.id.tv_details_name);
        tvPresalePrice = (TextView) findViewById(R.id.tv_details_presale_price);
        tvScenePrice = (TextView) findViewById(R.id.tv_details_scene_price);
        tvStartTime = (TextView) findViewById(R.id.tv_details_start_time);
        tvLocation = (TextView) findViewById(R.id.tv_details_location);
        civUserOne = (CircleImageView) findViewById(R.id.civ_user_one);
        civUserTwo = (CircleImageView) findViewById(R.id.civ_user_two);
        civUserThree = (CircleImageView) findViewById(R.id.civ_user_three);
        civUserFour = (CircleImageView) findViewById(R.id.civ_user_four);
        civUserFive = (CircleImageView) findViewById(R.id.civ_user_five);
        vpDetails = (ViewPager) findViewById(R.id.vp_details);
        tvDetails = (TextView) findViewById(R.id.tv_details_detail);
        tvShopping = (TextView) findViewById(R.id.tv_details_shopping);
        ivDetails = (ImageView) findViewById(R.id.iv_details_detail_heng);
        ivShopping = (ImageView) findViewById(R.id.iv_details_shopping_heng);
        tbHeader = (Toolbar) findViewById(R.id.tb_details_head);
        tbHeader.setAlpha(0);
        tvDetailsInvisible = (TextView) findViewById(R.id.tv_details_detail_invisible);
        tvShoppingInvisible = (TextView) findViewById(R.id.tv_details_shopping_invisible);
        ivDetailsInvisible = (ImageView) findViewById(R.id.iv_details_detail_heng_invisible);
        ivShoppingInvisible = (ImageView) findViewById(R.id.iv_details_shopping_heng_invisible);
        setSelectedFragment(0);
        Picasso.with(DetailsActivity.this).load(URL_DOMAIN + cover).into(ivLogo);
        Picasso.with(DetailsActivity.this).load(URL_DOMAIN + cover).into(ivBackGround);
        applyBlur();
        tvName.setText(name);
        tvHeadName.setText(name);
        tvPresalePrice.setText("￥ " + presalePrice);
        tvScenePrice.setText("现场" + scenePrice);
        tvStartTime.setText(new SimpleDateFormat("MM-dd HH:mm").format(new Date(Long.parseLong(startTime) * 1000)) + " - " + new SimpleDateFormat("MM-dd HH:mm").format(new Date(Long.parseLong(endTime) * 1000)));
        tvLocation.setText(location);
        Picasso.with(DetailsActivity.this).load(userOne).into(civUserOne);
        Picasso.with(DetailsActivity.this).load(userTwo).into(civUserTwo);
        Picasso.with(DetailsActivity.this).load(userThree).into(civUserThree);
        Picasso.with(DetailsActivity.this).load(userFour).into(civUserFour);
        Picasso.with(DetailsActivity.this).load(userFive).into(civUserFive);
    }

    private void initFragment() {
        list = new ArrayList<>();
        list.add(DetailsFragment.newInstance());
        list.add(ShoppingFragment.newInstance());
    }


    class DetailsAdapter extends FragmentPagerAdapter {

        public DetailsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list == null ? 0 : list.size();
        }
    }


    private void applyBlur() {
        ivBackGround.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                ivBackGround.getViewTreeObserver().removeOnPreDrawListener(this);
                ivBackGround.buildDrawingCache();
                Bitmap bmp = ivBackGround.getDrawingCache();
                blur(bmp, ivBackGround);
                return true;
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void blur(Bitmap bkg, View view) {

        float radius = 25;

        Bitmap overlay = Bitmap.createBitmap( (view.getMeasuredWidth()),
                (view.getMeasuredHeight()), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(overlay);

        canvas.translate(-view.getLeft(), -view.getTop());
        canvas.drawBitmap(bkg, 0, 0, null);

        RenderScript rs = RenderScript.create(DetailsActivity.this);

        Allocation overlayAlloc = Allocation.createFromBitmap(
                rs, overlay);

        ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(
                rs, overlayAlloc.getElement());

        blur.setInput(overlayAlloc);

        blur.setRadius(radius);

        blur.forEach(overlayAlloc);

        overlayAlloc.copyTo(overlay);

        ((ImageView)view).setImageDrawable(new BitmapDrawable(
                getResources(), overlay));

        rs.destroy();
    }
}
