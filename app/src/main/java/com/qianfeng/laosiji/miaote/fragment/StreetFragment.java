package com.qianfeng.laosiji.miaote.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.androidxx.yangjw.httplibrary.IOKCallBack;
import com.androidxx.yangjw.httplibrary.OkHttpTool;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.qianfeng.laosiji.miaote.R;
import com.qianfeng.laosiji.miaote.adapter.MyPullToRefreshListAdapter;
import com.qianfeng.laosiji.miaote.bean.Street;
import com.qianfeng.laosiji.miaote.bean.StreetHead;
import com.qianfeng.laosiji.miaote.constant.URLConsatant;
import com.qianfeng.laosiji.miaote.ui.RecommendStreetActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class StreetFragment extends Fragment {
    @BindView(R.id.street_prf_list)
    PullToRefreshListView pullToRefreshListView;
    private List<Street.DataBean> datas = new ArrayList<>();
    private Map<String,String> map = new HashMap();
    private MyPullToRefreshListAdapter adapter;
    private ListView refreshableView;
    private ImageView iv_head;
    private int i;
    public StreetFragment() {
        // Required empty public constructor
    }

    public static StreetFragment newInstance() {
        
        Bundle args = new Bundle();
        
        StreetFragment fragment = new StreetFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        Intent intent = new Intent(getActivity(), BaiDuActivity.class);
//        startActivity(intent);
        View view = inflater.inflate(R.layout.fragment_street, container, false);
        ButterKnife.bind(this,view);
        map.put("city","");
        map.put("eid","");
        map.put("p","1");
        map.put("province","");
        initData();

        adapter = new MyPullToRefreshListAdapter(datas,getContext());
        pullToRefreshListView.setAdapter(adapter);
        addHeadView();
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        return view;

    }

    /**
     * 添加头部视图
     */
    private void addHeadView() {
        View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.street_list_head,null);
        iv_head = (ImageView) headerView.findViewById(R.id.street_head_iv);
        initAddView();
        refreshableView = pullToRefreshListView.getRefreshableView();
        refreshableView.addHeaderView(headerView);
        initListener();
    }

    /**
     * 下拉刷新
     */
    private void initListener() {
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                map.put("p","1");
                datas.clear();
                initData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                i=1;
                i++;
                String value = String.valueOf(i);
                map.put("p",value);
                initData();
            }
        });
//        pullToRefreshListView.getRefreshableView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(getActivity(), StreetInfoActivity.class);
//
//                    startActivity(intent);
//            }
//        });
    }

    /**
     * 加载头部视图数据
     */
    private void initAddView() {
        OkHttpTool.newInstance().start(URLConsatant.URL_STREET_HEAD).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                if (result == null) {
                    return;
                }
                Gson gson = new Gson();
                final StreetHead streetHead = gson.fromJson(result,StreetHead.class);
                Picasso.with(getContext()).load(URLConsatant.URL_BASE+streetHead.getLogo()).into(iv_head);
                iv_head.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), RecommendStreetActivity.class);
                        intent.putExtra("title",streetHead.getTitle());
                        intent.putExtra("intro",streetHead.getIntro());
                        startActivity(intent);
                    }
                });
            }
        });
    }

    private void initData() {

        OkHttpTool.newInstance().start(URLConsatant.URL_STREET).post(map).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                if (result == null) {
                    return;
                }
                Gson gson = new Gson();
                Street street = gson.fromJson(result,Street.class);
                datas.addAll(street.getData());
                adapter.notifyDataSetChanged();
                pullToRefreshListView.onRefreshComplete();
            }
        });
    }

}
