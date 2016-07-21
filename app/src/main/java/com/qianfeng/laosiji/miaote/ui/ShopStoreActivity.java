package com.qianfeng.laosiji.miaote.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;

import com.androidxx.yangjw.httplibrary.IOKCallBack;
import com.androidxx.yangjw.httplibrary.OkHttpTool;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.qianfeng.laosiji.miaote.R;
import com.qianfeng.laosiji.miaote.adapter.MyPullGridViewAdapter;
import com.qianfeng.laosiji.miaote.bean.FindShopStore;
import com.qianfeng.laosiji.miaote.constant.URLConsatant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopStoreActivity extends AppCompatActivity {
    private Map<String,String> map = new HashMap();
    private List<FindShopStore.DataBean> datas = new ArrayList<>();
    private MyPullGridViewAdapter adapter;
    private PullToRefreshGridView prgView;
    private int a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_store);

        initView();
        map.put("p","1");
        initData();
        adapter = new MyPullGridViewAdapter(datas,ShopStoreActivity.this);
        prgView.setAdapter(adapter);
        prgView.setMode(PullToRefreshBase.Mode.BOTH);
        initListerner();
    }

    /**
     * 下拉刷新
     */
    private void initListerner() {
        prgView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                datas.clear();
                map.put("p","1");
               initData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                a++;

                String value = String.valueOf(a);
                map.put("p",value);
                initData();
            }
        });
    }

    /**
     * 请求数据
     */
    private void initData() {

        OkHttpTool.newInstance().start(URLConsatant.URL_FIND_SHOP).post(map).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                if(result == null){
                    return ;
                }
                Gson gson = new Gson();
                FindShopStore findShop = gson.fromJson(result,FindShopStore.class);
                datas.addAll(findShop.getData());
                adapter.notifyDataSetChanged();
                prgView.onRefreshComplete();
             }
        });
    }

    /**
     * 初始化视图
     */
    private void initView() {
       prgView = (PullToRefreshGridView) findViewById(R.id.find_shop_prg);

    }
    public void onBack(View v) {
        //设置返回功能
        onBackPressed();
    }
}
