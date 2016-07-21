package com.qianfeng.laosiji.miaote.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidxx.yangjw.httplibrary.IOKCallBack;
import com.androidxx.yangjw.httplibrary.OkHttpTool;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.qianfeng.laosiji.miaote.R;
import com.qianfeng.laosiji.miaote.adapter.MyGridViewAdapter;
import com.qianfeng.laosiji.miaote.adapter.MyPullToStreetInfoListViewAdapter;
import com.qianfeng.laosiji.miaote.bean.Street;
import com.qianfeng.laosiji.miaote.bean.StreetInfo;
import com.qianfeng.laosiji.miaote.constant.URLConsatant;
import com.qianfeng.laosiji.miaote.views.CustomGridView;
import com.qianfeng.laosiji.miaote.views.XCFlowLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class StreetInfoActivity extends AppCompatActivity {
    private Map<String,String> map = new HashMap();
    private PullToRefreshListView ptf_street_info;
    private List<StreetInfo.DataBean> data = new ArrayList<>();
    private MyPullToStreetInfoListViewAdapter adapter;
    private View headView;
    private TextView tv_comment_num_tv;
    private TextView tv_type_time_info;
    private CircleImageView civ_info;
    private TextView tv_name;
    private TextView tv_time;
    private TextView tv_content;
    private ImageView iv_photo;
    private CustomGridView gv_info;
    private XCFlowLayout xcf_info;
    private TextView tv_good;
    private ImageView iv_type_info;
    private TextView tv_type_name_info;
    private Street.DataBean street;
    private List<Street.DataBean.AttachBean> list = new ArrayList<>();
    private LinearLayout ll_manzhan;
    private TextView tv_type_location_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_street_info);
        Intent intent = getIntent();
        String feed_id = intent.getStringExtra("feed_id");
        street = (Street.DataBean) intent.getSerializableExtra("street");
        map.put("feed_id",feed_id);
        map.put("p","1");
        initView();
        initAddHead();
        initData();
        bindHeadView();
    }

    private void bindHeadView() {
        Glide.with(this).load(street.getUavatar()).into(civ_info);
        tv_name.setText(street.getUname());
        String time = new SimpleDateFormat("  HH:mm:ss").format(new Date(Long.parseLong(street.getPublish_time()) * 1000));
        tv_time.setText(time);
        tv_content.setText(street.getFeed_content());
        if (street.getPhoto_count() == 0) {
            iv_photo.setVisibility(View.GONE);
            gv_info.setVisibility(View.GONE);
        } else if (street.getPhoto_count() == 1) {
            iv_photo.setVisibility(View.VISIBLE);
            Glide.with(this).load(URLConsatant.URL_BASE + street.getAttach().get(0).getThumb()).into(iv_photo);
            gv_info.setVisibility(View.GONE);
        } else if (street.getPhoto_count() > 1) {
            iv_photo.setVisibility(View.GONE);
            gv_info.setVisibility(View.VISIBLE);
            list = street.getAttach();
            MyGridViewAdapter adapter = new MyGridViewAdapter(list, this);
            gv_info.setAdapter(adapter);
        }
        tv_good.setText(street.getLove());
        if (street.getCover().length() > 0) {
            ll_manzhan.setVisibility(View.VISIBLE);
            Glide.with(this).load(URLConsatant.URL_BASE + street.getCover()).into(iv_type_info);
            tv_type_name_info.setText(street.getName());
            tv_type_location_info.setText(street.getLocation());
            String time1 = new SimpleDateFormat(" MM/dd").format(new Date(Long.parseLong(street.getStart_time()) * 1000));
            String time2 = new SimpleDateFormat(" MM/dd").format(new Date(Long.parseLong(street.getEnd_time()) * 1000));
            tv_type_time_info.setText(time1 + "-" + time2);
        } else {
            ll_manzhan.setVisibility(View.GONE);
        }


    }
    private void initAddHead() {
        headView = LayoutInflater.from(StreetInfoActivity.this).inflate(R.layout.street_info_head,null);
         civ_info = (CircleImageView)headView.findViewById(R.id.street_info_civ);
       tv_name = (TextView) headView.findViewById(R.id.street_info_name_tv);
        tv_time = (TextView) headView.findViewById(R.id.street_info_time_tv);
         tv_content = (TextView) headView.findViewById(R.id.street_info_content_tv);
        iv_photo = (ImageView) headView.findViewById(R.id.strret_info_photo_iv);
         gv_info = (CustomGridView) headView.findViewById(R.id.street_info_photo_gv);
//         xcf_info = (XCFlowLayout) headView.findViewById(R.id.street_info_xcf_ll);
        tv_good= (TextView) headView.findViewById(R.id.street_info_good_tv);
         iv_type_info = (ImageView) headView.findViewById(R.id.street_info_type_info_iv);
         tv_type_name_info = (TextView) headView.findViewById(R.id.street_info_type_info_name_tv);
         tv_type_time_info = (TextView) headView.findViewById(R.id.street_info_type_info_time_tv);
         tv_comment_num_tv =  (TextView) headView.findViewById(R.id.street_info_comment_num_tv);
         ll_manzhan = (LinearLayout) headView.findViewById(R.id.steet_info_type_info_ll);
        tv_type_location_info =(TextView) headView.findViewById(R.id.street_info_type_info_location_tv);
        ptf_street_info.getRefreshableView().addHeaderView(headView);
    }

    private void initData() {
        OkHttpTool.newInstance().start(URLConsatant.URL_STREET_INFO).post(map).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                 if(result == null){
                     return;
                 }
                Gson gson = new Gson();
                StreetInfo streetInfo = gson.fromJson(result,StreetInfo.class);
                data.addAll(streetInfo.getData());
                tv_comment_num_tv.setText(streetInfo.getComment_count());
                adapter.notifyDataSetChanged();

            }
        });
    }

    private void initView() {
         ptf_street_info = (PullToRefreshListView) findViewById(R.id.street_info_prf);
        adapter = new MyPullToStreetInfoListViewAdapter(data,StreetInfoActivity.this);
        ptf_street_info.setAdapter(adapter);
    }
    public void onBack(View v) {
        //设置返回功能
        onBackPressed();
    }
}
