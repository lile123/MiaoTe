package com.qianfeng.laosiji.miaote.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.androidxx.yangjw.httplibrary.IOKCallBack;
import com.androidxx.yangjw.httplibrary.OkHttpTool;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.qianfeng.laosiji.miaote.R;
import com.qianfeng.laosiji.miaote.adapter.MyPullToRefreshListAdapter;
import com.qianfeng.laosiji.miaote.bean.Street;
import com.qianfeng.laosiji.miaote.constant.URLConsatant;
import com.qianfeng.laosiji.miaote.views.StretchUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecommendStreetActivity extends AppCompatActivity {
    private Map<String,String> map = new HashMap<>();
    private PullToRefreshListView prf_commend;
    private List<Street.DataBean> datas = new ArrayList<>();
    private MyPullToRefreshListAdapter adapter;
    private View headView;
    private TextView tv_commend;
    private String text;
    private Button btn;
    private int a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_street);
        Intent intent = getIntent();
        String title =intent.getStringExtra("title");
        text = intent.getStringExtra("intro");

        map.put("title",title);
        map.put("p","1");
        initView();
        initData();
        initAddHead();
        initListener();
    }

    private void initListener() {
        prf_commend.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                datas.clear();
                map.put("p","1");
                initData();

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                a++;
                String value = String.valueOf(a);
                map.put("p",value);
                initData();
            }
        });
    }

    /**
     * 添加头部视图
     */
    private void initAddHead() {
        headView = LayoutInflater.from(RecommendStreetActivity.this).inflate(R.layout.street_commend_head,null);
        initHeadView();
        prf_commend.getRefreshableView().addHeaderView(headView);
    }

    /**
     * 初始化头部视图
     */
    private void initHeadView() {
        tv_commend = (TextView) headView.findViewById(R.id.street_commend_text_tv);
         btn = (Button) headView.findViewById(R.id.street_commend_btn);
        tv_commend.setText(text);
        StretchUtil.getInstance(tv_commend,7,btn).initStretch();

    }

    private void initData() {
        OkHttpTool.newInstance().start(URLConsatant.URL_STREET_RECOMMEND).post(map).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                    if(result == null){
                        return;
                    }

                Gson gson = new Gson();
                Street street = gson.fromJson(result,Street.class);
                datas.addAll(street.getData());
                adapter.notifyDataSetChanged();
                prf_commend.onRefreshComplete();
            }
        });
    }

    private void initView() {
        prf_commend = (PullToRefreshListView) findViewById(R.id.street_commend_list_prf);
        adapter = new MyPullToRefreshListAdapter(datas,RecommendStreetActivity.this);
        prf_commend.setAdapter(adapter);
    }
    public void onBack(View v) {
        //设置返回功能
        onBackPressed();
    }
}
