package com.qianfeng.laosiji.miaote.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidxx.yangjw.httplibrary.IOKCallBack;
import com.androidxx.yangjw.httplibrary.OkHttpTool;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.qianfeng.laosiji.miaote.R;
import com.qianfeng.laosiji.miaote.adapter.MyReportInfoCommentListViewAdapter;
import com.qianfeng.laosiji.miaote.adapter.MyReportInfoListViewAdapter;
import com.qianfeng.laosiji.miaote.bean.ReportInfo;
import com.qianfeng.laosiji.miaote.constant.URLConsatant;
import com.qianfeng.laosiji.miaote.views.CustomListview;
import com.qianfeng.laosiji.miaote.views.MyListView;
import com.qianfeng.laosiji.miaote.views.TransparentToolBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReportInfoActivity extends AppCompatActivity {
    private Map<String,String> map = new HashMap();
    private CustomListview lv_report_info;
    private List<ReportInfo.DataBean.DescBean> datas = new ArrayList<>();
    private List<ReportInfo.CommentDataBean> list = new ArrayList<>();
    private MyReportInfoListViewAdapter adapter;
    private LinearLayout ll_type_info;
    private ImageView iv_type_info;
    private TextView tv_type_time;
    private TextView tv_type_name;
    private MyListView lv_report_comment;
    private MyReportInfoCommentListViewAdapter adapter2;
    private View footView;
    private View headView;
    private TextView tv_comment_num;
    private CircleImageView civ;
    private TextView tv_head_name;
    private TextView tv_head_tag;
    private TextView tv_head_num;
    private TextView tv_head_title;
    private RelativeLayout rl_head;
    private TransparentToolBar mTransparentToolBar;
    private LinearLayout ll_share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_info);
        Intent intent = getIntent();
        String id =intent.getStringExtra("id");
        initView();
        initAddFoot();
        initAddHead();
        map.put("id",id);
        initData();
        initAdapter();
        initBind();


    }






    /**
     * 添加头部视图
     */
    private void initAddHead() {
        headView = LayoutInflater.from(ReportInfoActivity.this).inflate(R.layout.report_info_list_head,null);
        initHeadView();
        lv_report_info.addHeaderView(headView);
    }

    /**
     * 初始化头部视图
     */
    private void initHeadView() {
         civ = (CircleImageView) headView.findViewById(R.id.report_info_head_icon_civ);
         tv_head_name = (TextView) headView.findViewById(R.id.report_info_head_name_tv);
         tv_head_tag = (TextView) headView.findViewById(R.id.report_info_head_tag_tv);
         tv_head_num =(TextView) headView.findViewById(R.id.report_info_head_num_tv);
        tv_head_title =(TextView) headView.findViewById(R.id.report_info_head_title_tv);
         rl_head = (RelativeLayout) headView.findViewById(R.id.report_info_head_rl);
    }

    //添加底部视图
    private void initAddFoot() {
         footView = LayoutInflater.from(ReportInfoActivity.this).inflate(R.layout.report_info_list_foot,null);
        initFootView();
        lv_report_info.addFooterView(footView);
    }

    /**
     * 初始化底部视图
     */
    private void initFootView() {
         ll_type_info = (LinearLayout)footView.findViewById(R.id.report_info_type_info_ll);
        iv_type_info = (ImageView) footView.findViewById(R.id.report_info_type_info_iv);
        tv_type_name = (TextView) footView.findViewById(R.id.report_info_type_info_name_tv);
        tv_type_time = (TextView) footView.findViewById(R.id.report_info_type_info_time_tv);
        lv_report_comment = (MyListView) footView.findViewById(R.id.report_info_comment_lv);
        tv_comment_num =(TextView) footView.findViewById(R.id.report_comment_num_tv);
    }

    /**
     * 绑定适配器
     */
    private void initBind() {
        lv_report_info.setAdapter(adapter);
       lv_report_comment.setAdapter(adapter2);
    }

    /**
     * 创建适配器
     */
    private void initAdapter() {
        adapter = new MyReportInfoListViewAdapter(datas,ReportInfoActivity.this);
        adapter2 = new MyReportInfoCommentListViewAdapter(list,ReportInfoActivity.this);
    }

    /**
     * 加载数据
     */
    private void initData() {
        OkHttpTool.newInstance().start(URLConsatant.URL_REPORT_INFO).post(map).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                if(result == null){
                    return;
                }
                Gson gson = new Gson();
                ReportInfo reportInfo = gson.fromJson(result,ReportInfo.class);
                datas.addAll(reportInfo.getData().getDesc());

                if("0".equals(reportInfo.getData().getType_info().getId())){
                    ll_type_info.setVisibility(View.GONE);
                }else{
                    ll_type_info.setVisibility(View.VISIBLE);
                    Glide.with(ReportInfoActivity.this).load(URLConsatant.URL_BASE + reportInfo.getData().getType_info().getCover()).into(iv_type_info);
                    tv_type_name.setText(reportInfo.getData().getType_info().getName());
                    tv_type_time.setText(reportInfo.getData().getType_info().getLocation());
                }

                if("0".equals(reportInfo.getData().getCommentCount())){
                    lv_report_comment.setVisibility(View.INVISIBLE);
                }else{
                    lv_report_comment.setVisibility(View.VISIBLE);
                    list.addAll(reportInfo.getComment_data());

                   adapter2.notifyDataSetChanged();

                }
                tv_comment_num.setText(reportInfo.getData().getCommentCount());
                adapter.notifyDataSetChanged();
                tv_head_name.setText(reportInfo.getData().getUname());
                Glide.with(ReportInfoActivity.this).load(URLConsatant.URL_BASE + reportInfo.getData().getCover()).into(civ);
                tv_head_num.setText(reportInfo.getData().getReadCount()+"人气");
                tv_head_title.setText(reportInfo.getData().getTitle());
                
            }
        });
    }

    /**
     * 初始化视图
     */

    private void initView() {
        mTransparentToolBar = (TransparentToolBar) findViewById(R.id.report_info_tb);
        lv_report_info = (CustomListview) findViewById(R.id.report_info_lv);
        ll_share =(LinearLayout) findViewById(R.id.report_info_share_ll);
        lv_report_info.setTitleBar(mTransparentToolBar);
        //取消item线
        lv_report_info.setDivider(null);
        lv_report_info.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem >= 1){
                    mTransparentToolBar.setBackgroundColor(getResources().getColor(R.color.yanse));
                }else{
                    mTransparentToolBar.setBackgroundColor(getResources().getColor(R.color.yansetouming));
                }
            }
        });
        //拿到回调过来的数据
        lv_report_info.setIDistance(new CustomListview.IDistance() {
            @Override
            public void diatanceY(float distanceY) {
            if(distanceY>50){
                ll_share.setVisibility(View.GONE);
            }else{
                ll_share.setVisibility(View.VISIBLE);
            }
            }
        });
    }
    public void onBack(View v) {
        //设置返回功能
        onBackPressed();
    }
}
