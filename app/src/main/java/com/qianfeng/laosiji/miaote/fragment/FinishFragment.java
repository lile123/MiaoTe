package com.qianfeng.laosiji.miaote.fragment;


import android.content.Context;
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
import com.qianfeng.laosiji.miaote.bean.FinishBean;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FinishFragment extends Fragment {

    public static final String URL_DOMAIN = "http://api.nyato.com";
    public static final String URL_FINISH = "http://api.nyato.com/index.php?app=android&mod=Expo&act=expired_list&&token=36ac12bbc663a58524277e2093718bcd&app_version=3.4&tickets=1&province=420000&p=1";
    private PullToRefreshListView mPullToRefreshListView;
    private List<FinishBean.DataBean> list = new ArrayList<>();
    private Context mContext;

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
        return view;
    }

    private void initListView() {
        OkHttpTool.newInstance().start(URL_FINISH).callback(new IOKCallBack() {
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
        FinishAdapter adapter = new FinishAdapter();
        mPullToRefreshListView.setAdapter(adapter);
        ListView listView = mPullToRefreshListView.getRefreshableView();
        listView.setDivider(null);


    }

    class FinishAdapter extends BaseAdapter {

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
            if (null == convertView) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.finish_item, parent, false);
                viewHolder.tvDate = (TextView) convertView.findViewById(R.id.tv_finish_date);
                viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_finish_name);
                viewHolder.llImage = (LinearLayout) convertView.findViewById(R.id.ll_finish_image_container);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.tvDate.setText(new SimpleDateFormat("MM/dd").format(new Date(Long.parseLong(list.get(position).getStart_time()) * 1000)));
            viewHolder.tvName.setText(list.get(position).getName());
            List<FinishBean.DataBean.StreetImgBean> imgList = list.get(position).getStreet_img();
            if (0 != imgList.size()) {
                viewHolder.llImage.setVisibility(View.VISIBLE);
                for (int i = 0; i < viewHolder.llImage.getChildCount(); i++) {
                    ImageView imageView = (ImageView) viewHolder.llImage.getChildAt(i);
                    if (i < imgList.size()) {
                        Picasso.with(mContext).load(URL_DOMAIN + imgList.get(i).getPath()).into(imageView);
                    } else {
                        imageView.setImageResource(0);
                    }
                }
            }else{
                viewHolder.llImage.setVisibility(View.GONE);
            }
            return convertView;
        }

        class ViewHolder {
            public TextView tvName;
            public TextView tvDate;
            public LinearLayout llImage;
        }

    }

}
