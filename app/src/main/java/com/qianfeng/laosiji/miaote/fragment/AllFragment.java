package com.qianfeng.laosiji.miaote.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.androidxx.yangjw.httplibrary.IOKCallBack;
import com.androidxx.yangjw.httplibrary.OkHttpTool;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.qianfeng.laosiji.miaote.R;
import com.qianfeng.laosiji.miaote.adapter.ListViewAllAdapter;
import com.qianfeng.laosiji.miaote.bean.CityDataBean;
import com.qianfeng.laosiji.miaote.bean.DataBean;
import com.qianfeng.laosiji.miaote.bean.OtherLocationBean;
import com.qianfeng.laosiji.miaote.ui.DetailsActivity;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllFragment extends Fragment implements View.OnClickListener{
    public static final String URL_DOMAIN = "http://api.nyato.com";
    public static final String URL_CITY_DATA = "http://api.nyato.com/index.php?app=android&mod=Expo&act=ex_list&&token=07a34d80b5457faf160e562266bc4770&app_version=3.4&tickets=1&city=110100&p=1&province=110000";
   // public static final String URL_CITY_DATA = "http://api.nyato.com/index.php?app=android&mod=Expo&act=ex_list&&token=07a34d80b5457faf160e562266bc4770&app_version=3.4&tickets=1&city=0&p=1&province=0";
    public static final String URL_BANNER = "http://api.nyato.com/index.php?app=android&mod=Index&act=getSlide&token=e3e50f189265d749cdce39e1b9491a63&app_version=3.4&tickets=1";

    private PullToRefreshListView mPullToRefreshListView;
    private List<Object> list = new ArrayList<>();
    private List<String> imgList = new ArrayList<>();
    private Context mContext;
    private String eid;
    private ConvenientBanner mBanner;
    private RadioGroup mRadioGroupInVisible;
    private ListView mListView;
    private RadioGroup mRadioGroupHeader;
    private RadioButton mRadioButtonType;
    private RadioButton mRadioButtonTime;
    private RadioButton mRadioButtonState;
    private PopupWindow mPopupWindow;
    private RadioButton mRbType;
    private RadioButton mRbTime;
    private RadioButton mRbState;


    public static AllFragment newInstance() {

        Bundle args = new Bundle();
        AllFragment fragment = new AllFragment();
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
        View view = inflater.inflate(R.layout.fragment_all, container, false);
        mPullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.city_data_ptrlv);
        mListView = mPullToRefreshListView.getRefreshableView();
        mRadioGroupInVisible = (RadioGroup) view.findViewById(R.id.rg_city_data_invisible);
        mRadioButtonType = (RadioButton)view.findViewById(R.id.rb_invisible_type);
        mRadioButtonTime = (RadioButton)view.findViewById(R.id.rb_invisible_time);
        mRadioButtonState = (RadioButton)view.findViewById(R.id.rb_invisible_state);
        initListView();
        initListener();
        return view;
    }




    private void initListener() {

        mPullToRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, DetailsActivity.class);
                if(list.get(position-3) instanceof CityDataBean){
                    CityDataBean bean =  (CityDataBean)list.get(position-3);
                    eid = bean.getEid();
                }else if(list.get(position-3) instanceof DataBean){
                    DataBean bean =  (DataBean)list.get(position-3);
                    eid = bean.getEid();
                }
                intent.putExtra("eid",eid);
                startActivity(intent);
            }
        });
        mRadioGroupInVisible.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_invisible_type:
                        if(mRadioButtonType.isChecked()){
                            View contentView = LayoutInflater.from(mContext).inflate(R.layout.type_option_item, null);
                            initTypeContentView(contentView);
                            initPopupWindow(contentView);
                        }
                        break;
                    case R.id.rb_invisible_time:
                        if(mRadioButtonTime.isChecked()){
                            View contentView = LayoutInflater.from(mContext).inflate(R.layout.time_option_item, null);
                            initTimeContentView(contentView);
                            initPopupWindow(contentView);
                        }
                        break;
                    case R.id.rb_invisible_state:
                        if(mRadioButtonState.isChecked()){
                            View contentView = LayoutInflater.from(mContext).inflate(R.layout.state_option_item, null);
                            initStateContentView(contentView);
                            initPopupWindow(contentView);
                        }
                        break;
                }
            }
        });

        mRadioGroupHeader.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mListView.setSelection(2);
                switch (checkedId){
                    case R.id.rb_header_type:
                        mRadioButtonType.setChecked(true);
                        break;
                    case R.id.rb_header_time:
                        mRadioButtonTime.setChecked(true);
                        break;
                    case R.id.rb_header_state:
                        mRadioButtonState.setChecked(true);
                        break;
                }
            }
        });

        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem >1){
                    if(View.GONE == mRadioGroupInVisible.getVisibility()){
                        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.alpha_in);
                        mRadioGroupInVisible.startAnimation(animation);
                        mRadioGroupInVisible.setVisibility(View.VISIBLE);
                    }
                }else{
                    if(View.VISIBLE == mRadioGroupInVisible.getVisibility()){
                        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.alpha_out);
                        mRadioGroupInVisible.startAnimation(animation);
                        mRadioGroupInVisible.setVisibility(View.GONE);
                    }
                }
            }
        });

    }

    private void initPopupWindow(View contentView) {
        mPopupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setContentView(contentView);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.showAsDropDown(mRadioGroupInVisible);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                mRadioGroupInVisible.clearCheck();
            }
        });
    }

    private void initStateContentView(View contentView) {
        TextView tvFilterAll = (TextView) contentView.findViewById(R.id.tv_filter_all);
        TextView tvFilterTickets = (TextView) contentView.findViewById(R.id.tv_filter_buytickets);
        tvFilterAll.setOnClickListener(this);
        tvFilterTickets.setOnClickListener(this);

    }

    private void initTimeContentView(View contentView) {
        ImageView ivLeft = (ImageView) contentView.findViewById(R.id.iv_trigon_left);
        RecyclerView recyclerView = (RecyclerView) contentView.findViewById(R.id.rv_trigon_month);
        initRecyclerView(recyclerView);
        ImageView ivRight = (ImageView) contentView.findViewById(R.id.iv_trigon_right);

    }

    private void initRecyclerView(RecyclerView recyclerView) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        MyRecyclerAdapter adapter = new MyRecyclerAdapter();
        recyclerView.setAdapter(adapter);
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public RadioGroup group;
        public ViewHolder(View itemView) {
            super(itemView);
            group = (RadioGroup) itemView.findViewById(R.id.rg_month);
        }
    }

    class MyRecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.month_item, null);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

        }


        @Override
        public int getItemCount() {
            return 1;
        }
    }




    private void initTypeContentView(View contentView) {
        TextView tvAllExhibition = (TextView) contentView.findViewById(R.id.tv_popwindow_allexhibition);
        TextView tvActivityExhibition = (TextView) contentView.findViewById(R.id.tv_popwindow_activityexhibition);
        TextView tvAgExhibition = (TextView) contentView.findViewById(R.id.tv_popwindow_agexhibiton);
        TextView tvCosExhibition = (TextView) contentView.findViewById(R.id.tv_popwindow_cosexhibition);
        TextView tvDisplayExhibition = (TextView) contentView.findViewById(R.id.tv_popwindow_displayexhibition);
        TextView tvMeetExhibition = (TextView) contentView.findViewById(R.id.tv_popwindow_meetexhibition);
        TextView tvMusicExhibition = (TextView) contentView.findViewById(R.id.tv_popwindow_musicexhibition);
        TextView tvOtherExhibition = (TextView) contentView.findViewById(R.id.tv_popwindow_otherexhibition);
        TextView tvSingExhibition = (TextView) contentView.findViewById(R.id.tv_popwindow_singexhibiton);
        TextView tvTeaExhibition = (TextView) contentView.findViewById(R.id.tv_popwindow_teaexhibition);
        TextView tvTrExhibition = (TextView) contentView.findViewById(R.id.tv_popwindow_trexhibition);
        tvAllExhibition.setOnClickListener(this);
        tvActivityExhibition.setOnClickListener(this);
        tvAgExhibition.setOnClickListener(this);
        tvCosExhibition.setOnClickListener(this);
        tvDisplayExhibition.setOnClickListener(this);
        tvMeetExhibition.setOnClickListener(this);
        tvMusicExhibition.setOnClickListener(this);
        tvOtherExhibition.setOnClickListener(this);
        tvSingExhibition.setOnClickListener(this);
        tvTeaExhibition.setOnClickListener(this);
        tvTrExhibition.setOnClickListener(this);
    }

    private void initListView() {
        LinearLayout cityDataHeader = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.city_data_header, null);
        LinearLayout cityOptionHeader = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.option_item, null);
        mBanner = (ConvenientBanner) cityDataHeader.findViewById(R.id.city_data_banner);
        mRadioGroupHeader = (RadioGroup)cityOptionHeader.findViewById(R.id.rg_city_data);
        mRbType = (RadioButton)cityOptionHeader.findViewById(R.id.rb_header_type);
        mRbTime = (RadioButton)cityOptionHeader.findViewById(R.id.rb_header_time);
        mRbState = (RadioButton)cityOptionHeader.findViewById(R.id.rb_header_state);
        initBanner();
        mListView.addHeaderView(cityDataHeader);
        mListView.addHeaderView(cityOptionHeader);
        OkHttpTool.newInstance().start(URL_CITY_DATA).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                if (null == result) {
                    return;
                }
                Gson gson = new Gson();
                OtherLocationBean bean = gson.fromJson(result, OtherLocationBean.class);
                List<CityDataBean> cityDataBeanList = bean.getCity_data();
                if (cityDataBeanList.isEmpty()) {
                    list.add("empty");
                    list.add("other");
                } else {
                    list.addAll(cityDataBeanList);
                    list.add("other");
                }
                List<DataBean> dataBeanList = bean.getData();
                list.addAll(dataBeanList);
                bindView();
            }
        });
    }

    private void initBanner() {
        if (imgList.isEmpty()) {
            OkHttpTool.newInstance().start(URL_BANNER).callback(new IOKCallBack() {
                @Override
                public void success(String result) {
                    try {
                        parseBannerData(result);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    mBanner.setPages(new CBViewHolderCreator() {
                        @Override
                        public ImageViewHolder createHolder() {
                            return new ImageViewHolder();
                        }
                    }, imgList).setPageIndicator(new int[]{R.drawable.dot_focus, R.drawable.dot_blur})
                            .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
                }
            });
        } else {
            mBanner.setPages(new CBViewHolderCreator() {
                @Override
                public ImageViewHolder createHolder() {
                    return new ImageViewHolder();
                }
            }, imgList).setPageIndicator(new int[]{R.drawable.dot_focus, R.drawable.dot_blur})
                    .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
        }


    }

    private void parseBannerData(String result) throws JSONException {
        JSONArray jsonArray = new JSONArray(result);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsObj = (JSONObject) jsonArray.get(i);
            String logo = jsObj.getString("logo");
            imgList.add(URL_DOMAIN + logo);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mBanner.startTurning(2000);
    }

    @Override
    public void onStop() {
        super.onStop();
        mBanner.stopTurning();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_popwindow_allexhibition:
                mRadioButtonType.setText("全部漫展");
                mRbType.setText("全部漫展");
                break;
            case R.id.tv_popwindow_trexhibition:
                mRadioButtonType.setText("同人展");
                mRbType.setText("同人展");
                break;
            case R.id.tv_popwindow_agexhibiton:
                mRadioButtonType.setText("动漫/游戏");
                mRbType.setText("动漫/游戏");
                break;
            case R.id.tv_popwindow_cosexhibition:
                mRadioButtonType.setText("Cosplay展");
                mRbType.setText("Cosplay展");
                break;
            case R.id.tv_popwindow_singexhibiton:
                mRadioButtonType.setText("演唱会");
                mRbType.setText("演唱会");
                break;
            case R.id.tv_popwindow_displayexhibition:
                mRadioButtonType.setText("展示会");
                mRbType.setText("展示会");
                break;
            case R.id.tv_popwindow_meetexhibition:
                mRadioButtonType.setText("见面会");
                mRbType.setText("见面会");
                break;
            case R.id.tv_popwindow_teaexhibition:
                mRadioButtonType.setText("茶会");
                mRbType.setText("茶会");
                break;
            case R.id.tv_popwindow_otherexhibition:
                mRadioButtonType.setText("其他会展");
                mRbType.setText("其他会展");
                break;
            case R.id.tv_popwindow_activityexhibition:
                mRadioButtonType.setText("比赛活动");
                mRbType.setText("比赛活动");
                break;
            case R.id.tv_popwindow_musicexhibition:
                mRadioButtonType.setText("音乐会");
                mRbType.setText("音乐会");
                break;
            case R.id.tv_filter_all:
                mRadioButtonState.setText("全部");
                mRbState.setText("全部");
                break;
            case R.id.tv_filter_buytickets:
                mRadioButtonState.setText("已开售");
                mRbState.setText("已开售");
                break;

        }
    }

    public class ImageViewHolder implements Holder<String> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, String data) {
            Picasso.with(mContext).load(data).into(imageView);
        }
    }

    private void bindView() {
        ListViewAllAdapter adapter = new ListViewAllAdapter(list,mContext);
        mPullToRefreshListView.setAdapter(adapter);

    }

}

