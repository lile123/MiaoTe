package com.qianfeng.laosiji.miaote.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.androidxx.yangjw.httplibrary.IOKCallBack;
import com.androidxx.yangjw.httplibrary.OkHttpTool;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.qianfeng.laosiji.miaote.R;
import com.qianfeng.laosiji.miaote.adapter.ListViewAllAdapter;
import com.qianfeng.laosiji.miaote.adapter.ListViewFinishAdapter;
import com.qianfeng.laosiji.miaote.bean.CityDataBean;
import com.qianfeng.laosiji.miaote.bean.DataBean;
import com.qianfeng.laosiji.miaote.bean.FinishBean;
import com.qianfeng.laosiji.miaote.constant.URLConsatant;
import com.qianfeng.laosiji.miaote.ui.DetailsActivity;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FinishFragment extends Fragment {

    private PullToRefreshListView mPullToRefreshListView;
    private List<FinishBean.DataBean> list = new ArrayList<>();
    private Context mContext;
    private String p ="1";
    private ListViewFinishAdapter adapter;

    public static FinishFragment newInstance() {

        Bundle args = new Bundle();

        FinishFragment fragment = new FinishFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_finish, container, false);
        mPullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.ptrlv_finish);
        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        initListView();
        initListener();
        return view;
    }


    public void refresh(){
        list.clear();
        p = "1";
        String url = ComicFragment.mAssembleUrl.setP(p).getUrl(URLConsatant.URL_FINISH);
        OkHttpTool.newInstance().start(url).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                if (null == result) {
                    return;
                }
                Gson gson = new Gson();
                FinishBean bean = gson.fromJson(result, FinishBean.class);
                list.addAll(bean.getData());
                if(null != adapter){
                    bindView();
                }
            }
        });
    }

    private void initListener() {


        mPullToRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, DetailsActivity.class);
                String eid = list.get(position-1).getEid();
                intent.putExtra("eid", eid);
                startActivity(intent);
            }
        });


        mPullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                list.clear();
                p = "1";
                String url = ComicFragment.mAssembleUrl.setP(p).getUrl(URLConsatant.URL_FINISH);
                OkHttpTool.newInstance().start(url).callback(new IOKCallBack() {
                    @Override
                    public void success(String result) {
                        if (null == result) {
                            return;
                        }
                        Gson gson = new Gson();
                        FinishBean bean = gson.fromJson(result, FinishBean.class);
                        list.addAll(bean.getData());
                        bindView();
                        mPullToRefreshListView.onRefreshComplete();
                    }
                });
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                int index = Integer.parseInt(p);
                index++;
                p = String.valueOf(index);
                String url = ComicFragment.mAssembleUrl.setP(p).getUrl(URLConsatant.URL_FINISH);
                OkHttpTool.newInstance().start(url).callback(new IOKCallBack() {
                    @Override
                    public void success(String result) {
                        if (null == result) {
                            return;
                        }
                        Gson gson = new Gson();
                        FinishBean bean = gson.fromJson(result, FinishBean.class);
                        list.addAll(bean.getData());
                        adapter.notifyDataSetChanged();
                        mPullToRefreshListView.onRefreshComplete();
                    }
                });
            }
        });

    }

    private void initListView() {
        list.clear();
        OkHttpTool.newInstance().start(ComicFragment.mAssembleUrl.getUrl(URLConsatant.URL_FINISH)).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                if (null == result) {
                    return;
                }
                Gson gson = new Gson();
                FinishBean bean = gson.fromJson(result, FinishBean.class);
                list.addAll(bean.getData());
                bindView();
            }
        });
    }

    private void bindView() {
        adapter = new ListViewFinishAdapter(list,mContext);
        mPullToRefreshListView.setAdapter(adapter);
        ListView listView = mPullToRefreshListView.getRefreshableView();
        listView.setDivider(null);
    }

}
