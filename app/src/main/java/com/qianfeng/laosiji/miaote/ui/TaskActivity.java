package com.qianfeng.laosiji.miaote.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.androidxx.yangjw.httplibrary.IOKCallBack;
import com.androidxx.yangjw.httplibrary.OkHttpTool;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.qianfeng.laosiji.miaote.R;
import com.qianfeng.laosiji.miaote.adapter.MyPullToTaskListViewAdapter;
import com.qianfeng.laosiji.miaote.bean.TaskCenter;
import com.qianfeng.laosiji.miaote.constant.URLConsatant;

import java.util.ArrayList;
import java.util.List;

public class TaskActivity extends AppCompatActivity {

    private PullToRefreshListView prf_list;
    private List<TaskCenter.DataBean> data = new ArrayList<>();
    private MyPullToTaskListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        initview();
        initData();
    }

    /**
     * 加载数据
     */
    private void initData() {
        OkHttpTool.newInstance().start(URLConsatant.URL_FIND_TASK).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                if(result ==null){
                    return;
                }
                Gson gson = new Gson();
                TaskCenter taskCenter = gson.fromJson(result,TaskCenter.class);
                data.addAll(taskCenter.getData());
                adapter.notifyDataSetChanged();

            }
        });
    }

    /**
     * 初始化视图
     */
    private void initview() {
         prf_list = (PullToRefreshListView) findViewById(R.id.find_task_prf_list);
        adapter = new MyPullToTaskListViewAdapter(data,TaskActivity.this);
        prf_list.setAdapter(adapter);
    }
}
