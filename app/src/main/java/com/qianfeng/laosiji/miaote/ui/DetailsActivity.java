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
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidxx.yangjw.httplibrary.IOKCallBack;
import com.androidxx.yangjw.httplibrary.OkHttpTool;
import com.google.gson.Gson;
import com.qianfeng.laosiji.miaote.BaseActivity;
import com.qianfeng.laosiji.miaote.R;
import com.qianfeng.laosiji.miaote.bean.DetailsBean;
import com.qianfeng.laosiji.miaote.constant.URLConsatant;
import com.qianfeng.laosiji.miaote.fragment.DetailsFragment;
import com.qianfeng.laosiji.miaote.fragment.ShoppingFragment;
import com.qianfeng.laosiji.miaote.views.CustomScrollView;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import de.hdodenhof.circleimageview.CircleImageView;

public class DetailsActivity extends BaseActivity {

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
    private Bundle bundle = new Bundle();
    private ImageView ivShare;
    private Button button;
    private String coordinate;
    private String[] locate;
    private ImageView ivBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();
        String eid = intent.getStringExtra("eid");
        map.put("eid", eid);
        map.put("p", "1");
        bundle.putString("eid", eid);
        bundle.putString("p", "1");
        initData();
    }

    private void initData() {
        OkHttpTool.newInstance().start(URLConsatant.URL_Details).post(map).callback(new IOKCallBack() {
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
                coordinate = detailsBean.getInfo().getCoordinate();
                locate =  coordinate.split("\\,");
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
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //跳转百度地图
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(DetailsActivity.this,BaiDuActivity.class);
//                intent.putExtra("latitude",locate[1]);
//                intent.putExtra("longitude",locate[0]);
//                startActivity(intent);
//            }
//        });
        ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareSDK.initSDK(DetailsActivity.this);
                OnekeyShare oks = new OnekeyShare();
                //关闭sso授权
                oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
                //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
                // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
                oks.setTitle("3456789");
                // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
                oks.setTitleUrl("http://sharesdk.cn");
                // text是分享文本，所有平台都需要这个字段
                oks.setText("我是分享文本");
                // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
                //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
                // url仅在微信（包括好友和朋友圈）中使用
                oks.setUrl("http://sharesdk.cn");
                // comment是我对这条分享的评论，仅在人人网和QQ空间使用
                oks.setComment("我是测试评论文本");
                // site是分享此内容的网站名称，仅在QQ空间使用
                oks.setSite(getString(R.string.app_name));
                // siteUrl是分享此内容的网站地址，仅在QQ空间使用
                oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
                oks.show(DetailsActivity.this);
            }

        });
        csvDetails.setOnScrollListener(new CustomScrollView.IOnScroll() {
            @Override
            public void onScroll(int l, int t, int oldl, int oldt) {
                if (t <= 600) {
                    float alpha = (float) t / 600;
                    tbHeader.setAlpha(alpha);
                }
                if (t < 370) {
                    ivBackGround.setVisibility(View.VISIBLE);
                } else {
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
        ivShare = (ImageView) findViewById(R.id.iv_details_name_head);
        button = (Button) findViewById(R.id.btn_tv_details_baidu);
        ivBack = (ImageView)findViewById(R.id.iv_details_back);
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
        Picasso.with(DetailsActivity.this).load(URLConsatant.URL_BASE + cover).into(ivLogo);
        Picasso.with(DetailsActivity.this).load(URLConsatant.URL_BASE + cover).into(ivBackGround);
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
        bundle.putString("url", URLConsatant.URL_Details);
        list.add(DetailsFragment.newInstance(bundle));
        list.add(ShoppingFragment.newInstance(bundle));
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

        Bitmap overlay = Bitmap.createBitmap((view.getMeasuredWidth()),
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

        ((ImageView) view).setImageDrawable(new BitmapDrawable(
                getResources(), overlay));

        rs.destroy();
    }
}
