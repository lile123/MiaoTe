package com.qianfeng.laosiji.miaote.fragment;


import android.media.TimedText;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidxx.yangjw.httplibrary.IOKCallBack;
import com.androidxx.yangjw.httplibrary.OkHttpTool;
import com.google.gson.Gson;
import com.qianfeng.laosiji.miaote.BaseActivity;
import com.qianfeng.laosiji.miaote.R;
import com.qianfeng.laosiji.miaote.bean.DetailsBean;
import com.qianfeng.laosiji.miaote.views.MyListView;
import com.qianfeng.laosiji.miaote.views.StretchUtil;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {

    public static final String URL_DOMAIN = "http://api.nyato.com";

    private TextView tvAddr;
    private TextView tvDescription;
    private Button button;
    private String url;
    private String eid;
    private String p;
    private MyListView mlvIntelligence;
    private RelativeLayout rlHeader;
    private TextView tvCount;
    private LinearLayout llFooter;
    private List<DetailsBean.NewsBean> list;

    public static DetailsFragment newInstance(Bundle bundle) {
        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        tvAddr = (TextView) view.findViewById(R.id.tv_details_detail_addr);
        tvDescription = (TextView) view.findViewById(R.id.tv_details_description);
        button = (Button) view.findViewById(R.id.btn_details_show_more);
        mlvIntelligence = (MyListView) view.findViewById(R.id.mlv_intelligence);
        rlHeader = (RelativeLayout) inflater.inflate(R.layout.intelligence_header,null);
        tvCount = (TextView) rlHeader.findViewById(R.id.tv_details_intelligence_count);
        llFooter = (LinearLayout) inflater.inflate(R.layout.intelligence_footer,null);
        setView();
        return view;
    }

    private void setView() {
        HashMap<String, String> map = new HashMap<>();
        map.put("eid",eid);
        map.put("p",p);
        OkHttpTool.newInstance().start(url).post(map).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                if (result.length() < 145) {
                    return;
                }
                Gson gson = new Gson();
                DetailsBean detailsBean = gson.fromJson(result, DetailsBean.class);
                String addr = detailsBean.getInfo().getAddr();
                tvAddr.setText(addr);
                String description = detailsBean.getInfo().getDescription();
                tvDescription.setText(description);
                StretchUtil.getInstance(tvDescription, 7, button).initStretch();
                list = detailsBean.getNews();
                setListView();
            }
        });
    }

    private void setListView() {
        tvCount.setText("共"+list.size()+"条公告");
        mlvIntelligence.addHeaderView(rlHeader);
        mlvIntelligence.addFooterView(llFooter);
        MyAdapter adapter = new MyAdapter();
        mlvIntelligence.setAdapter(adapter);
        tvAddr.setFocusable(true);
        tvAddr.setFocusableInTouchMode(true);
        tvAddr.requestFocus();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        url = bundle.getString("url");
        eid = bundle.getString("eid");
        p = bundle.getString("p");
    }


    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list == null ? 0 : list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if(null == convertView){
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.intelligence_item,null);
                viewHolder.ivIntelligence = (ImageView) convertView.findViewById(R.id.iv_details_intelligence);
                viewHolder.tvAtitle = (TextView) convertView.findViewById(R.id.tv_intelligence_atitle);
                viewHolder.tvCTime = (TextView) convertView.findViewById(R.id.tv_intelligence_ctime);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }
            String imgUrl = list.get(position).getNews_cover();
            Picasso.with(getContext()).load(URL_DOMAIN+imgUrl).into(viewHolder.ivIntelligence);
            String atitle = list.get(position).getAtitle();
            viewHolder.tvAtitle.setText(atitle);
            String cTime = list.get(position).getCTime();
            viewHolder.tvCTime.setText(new SimpleDateFormat("MM-dd HH:mm").format(new Date(Long.parseLong(cTime) * 1000)));
            return convertView;
        }


        class ViewHolder{
            ImageView ivIntelligence;
            TextView tvAtitle;
            TextView tvCTime;
        }
    }
}
