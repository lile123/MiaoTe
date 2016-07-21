package com.qianfeng.laosiji.miaote.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.androidxx.yangjw.httplibrary.IOKCallBack;
import com.androidxx.yangjw.httplibrary.OkHttpTool;
import com.google.gson.Gson;
import com.qianfeng.laosiji.miaote.R;
import com.qianfeng.laosiji.miaote.adapter.MyRecyclerViewAdapter;
import com.qianfeng.laosiji.miaote.bean.Find;
import com.qianfeng.laosiji.miaote.constant.URLConsatant;
import com.qianfeng.laosiji.miaote.ui.ReportInfoActivity;
import com.qianfeng.laosiji.miaote.ui.ShopStoreActivity;
import com.qianfeng.laosiji.miaote.ui.TaskActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindFragment extends Fragment {


    private View view;
    private ImageView iv_miao;
    private ImageView iv_center;
    private RecyclerView rlv;
    private MyRecyclerViewAdapter mRecyclerAdapter;
    private List<Find.GiftDataBean> datas = new ArrayList<>();
    private LinearLayout ll_shop;
    private LinearLayout ll_center;
    private LinearLayout ll_miaoji;
    private ImageView iv_miaoji;

    public FindFragment() {
        // Required empty public constructor
    }

    public static FindFragment newInstance() {
        
        Bundle args = new Bundle();
        
        FindFragment fragment = new FindFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_find, container, false);
        initView();
        initData();
        initListerner();
        return view;
    }

    /**
     *  点击监听
     */
    private void initListerner() {
        ll_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ShopStoreActivity.class);
                startActivity(intent);
            }
        });
        ll_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TaskActivity.class);
                startActivity(intent);
            }
        });
        iv_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TaskActivity.class);
                startActivity(intent);
            }
        });
        ll_miaoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    /**
     *  初始化视图
     */
    private void initView() {
         iv_miao = (ImageView) view.findViewById(R.id.find_miaoji_iv);
         rlv = (RecyclerView) view.findViewById(R.id.find_rlv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        rlv.setLayoutManager(linearLayoutManager);
        iv_center = (ImageView) view.findViewById(R.id.find_center_iv);
        mRecyclerAdapter = new MyRecyclerViewAdapter(datas,getContext());
        //关联适配器视图
        rlv.setAdapter(mRecyclerAdapter);
         ll_shop = (LinearLayout) view.findViewById(R.id.find_shop_ll);
        ll_center = (LinearLayout)view.findViewById(R.id.find_center_ll);
        ll_miaoji =(LinearLayout) view.findViewById(R.id.find_miaoji_ll);

//

    }


    /**
     * 加载视图数据
     */
    private void initData() {
        OkHttpTool.newInstance().start(URLConsatant.URL_FIND).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                if(result == null){
                    return;
                }
                Gson gson = new Gson();
                final Find find = gson.fromJson(result,Find.class);
                Picasso.with(getContext()).load(URLConsatant.URL_BASE+find.getData().getTask_logo()).into(iv_center);
                Picasso.with(getContext()).load(URLConsatant.URL_BASE+find.getData().getReport_logo()).into(iv_miao);
                iv_miao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), ReportInfoActivity.class);
                         intent.putExtra("id", find.getData().getTo_id());
                        startActivity(intent);
                    }
                });
                datas.addAll(find.getGift_data());
                mRecyclerAdapter.notifyDataSetChanged();

            }
        });
    }

}
