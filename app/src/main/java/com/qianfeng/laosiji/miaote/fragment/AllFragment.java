package com.qianfeng.laosiji.miaote.fragment;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.androidxx.yangjw.httplibrary.IOKCallBack;
import com.androidxx.yangjw.httplibrary.OkHttpTool;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.google.gson.Gson;
import com.qianfeng.laosiji.miaote.R;
import com.qianfeng.laosiji.miaote.adapter.ListViewAllAdapter;
import com.qianfeng.laosiji.miaote.bean.CityDataBean;
import com.qianfeng.laosiji.miaote.bean.DataBean;
import com.qianfeng.laosiji.miaote.bean.OtherLocationBean;
import com.qianfeng.laosiji.miaote.views.CustomListView;
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
public class AllFragment extends Fragment {
    public static final String URL_DOMAIN = "http://api.nyato.com";
    //public static final String URL_CITY_OTHER = "http://api.nyato.com/index.php?app=android&mod=Expo&act=ex_list&&token=07a34d80b5457faf160e562266bc4770&app_version=3.4&tickets=1&city=110100&p=1&province=110000";
    public static final String URL_CITY_OTHER = "http://api.nyato.com/index.php?app=android&mod=Expo&act=ex_list&&token=07a34d80b5457faf160e562266bc4770&app_version=3.4&tickets=1&city=0&p=1&province=0";
    public static final String URL_BANNER = "http://api.nyato.com/index.php?app=android&mod=Index&act=getSlide&token=e3e50f189265d749cdce39e1b9491a63&app_version=3.4&tickets=1";

    private CustomListView mCityCustomListView;
    private CustomListView mOtherCustomListView;
    private List<CityDataBean> cityDatas = new ArrayList<>();
    private List<DataBean> otherDatas = new ArrayList<>();
    private List<String> imgList = new ArrayList<>();
    private Context mContext;
    private String eid;
    private CityAdapter cityAdapter;
    private OtherAdapter otherAdapter;
    private ConvenientBanner mBanner;



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
        View view =  inflater.inflate(R.layout.fragment_all, container, false);
        mOtherCustomListView = (CustomListView) view.findViewById(R.id.all_other_lv);
        mCityCustomListView = (CustomListView) view.findViewById(R.id.city_data_clv);
        mBanner = (ConvenientBanner) view.findViewById(R.id.city_data_banner);
        initListView();
        initListener();
        return view;
    }

    private void initListener() {



    }

    private void initListView() {
        initBanner();
        LinearLayout otherHeader = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.other_location_header, null);
        mOtherCustomListView.addHeaderView(otherHeader);
        OkHttpTool.newInstance().start(URL_CITY_OTHER).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                if (null == result) {
                    return;
                }
                Gson gson = new Gson();
                OtherLocationBean bean = gson.fromJson(result, OtherLocationBean.class);
                List<CityDataBean> cityDataBeanList = bean.getCity_data();
                cityDatas.addAll(cityDataBeanList);
                List<DataBean> dataBeanList = bean.getData();
                otherDatas.addAll(dataBeanList);
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
        for(int i=0;i<jsonArray.length();i++){
            JSONObject jsObj = (JSONObject) jsonArray.get(i);
            String logo = jsObj.getString("logo");
            imgList.add(URL_DOMAIN+logo);
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
        cityAdapter = new CityAdapter(cityDatas, mContext);
        mCityCustomListView.setAdapter(cityAdapter);
        otherAdapter = new OtherAdapter(otherDatas, mContext);
        mOtherCustomListView.setAdapter(otherAdapter);
    }


    class CityAdapter extends ListViewAllAdapter {
        private List<CityDataBean> list;

        public CityAdapter(List list, Context mContext) {
            super(list, mContext);
            this.list = list;
        }

        @Override
        public void initItemData(ListViewAllAdapter.ViewHolder viewHolder, int position) {
            CityDataBean bean = list.get(position);
            eid = bean.getEid();
            viewHolder.tvName.setText(bean.getName());
            Picasso.with(mContext).load(URL_DOMAIN + bean.getCover()).into(viewHolder.ivCover);
            viewHolder.tvTime.setText(new SimpleDateFormat("yyyy年MM月dd日").format(new Date(Long.parseLong(bean.getStart_time()) * 1000)) + "-" + new SimpleDateFormat("MM月dd日").format(new Date(Long.parseLong(bean.getEnd_time()) * 1000)));
            if (!"0".equals(bean.getIs_hot())) {
                viewHolder.ivHot.setImageResource(R.drawable.is_hot);
            } else {
                viewHolder.ivHot.setImageResource(0);
            }
            viewHolder.tvLocation.setText(bean.getLocation() + bean.getAddr());
            viewHolder.tvLove.setText(bean.getLove());
            viewHolder.tvSection.setText(bean.getSection_name());
            if (!"0".equals(bean.getIs_ticket())) {
                viewHolder.ivTicket.setImageResource(R.drawable.buytickets);
            } else {
                viewHolder.ivTicket.setImageResource(0);
            }
            if (!(0 == bean.getNews_status())) {
                viewHolder.dividerOne.setVisibility(View.VISIBLE);
                viewHolder.tvNewsTitle.setVisibility(View.VISIBLE);
                viewHolder.tvNewsSection.setVisibility(View.VISIBLE);
                viewHolder.tvNewsTitle.setText(bean.getNews_data().getTitle());
                viewHolder.tvNewsSection.setText(bean.getNews_data().getSection_name());
            } else {
                viewHolder.dividerOne.setVisibility(View.GONE);
                viewHolder.tvNewsTitle.setVisibility(View.GONE);
                viewHolder.tvNewsSection.setVisibility(View.GONE);
            }
            if (!(0 == bean.getGuest_status())) {
                viewHolder.dividerTwo.setVisibility(View.VISIBLE);
                viewHolder.tvGuestTitle.setVisibility(View.VISIBLE);
                viewHolder.tvGuestSection.setVisibility(View.VISIBLE);
                viewHolder.tvGuestTitle.setText(bean.getGuset_data().getTitle());
                viewHolder.tvGuestSection.setText(bean.getGuset_data().getSection_name());
            } else {
                viewHolder.dividerTwo.setVisibility(View.GONE);
                viewHolder.tvGuestTitle.setVisibility(View.GONE);
                viewHolder.tvGuestSection.setVisibility(View.GONE);
            }
        }
    }

    class OtherAdapter extends ListViewAllAdapter {
        private List<DataBean> list;

        public OtherAdapter(List list, Context mContext) {
            super(list, mContext);
            this.list = list;
        }

        @Override
        public void initItemData(ViewHolder viewHolder, int position) {
            DataBean bean = list.get(position);
            eid = bean.getEid();
            viewHolder.tvName.setText(bean.getName());
            Picasso.with(mContext).load(URL_DOMAIN + bean.getCover()).into(viewHolder.ivCover);
            viewHolder.tvTime.setText(new SimpleDateFormat("yyyy年MM月dd日").format(new Date(Long.parseLong(bean.getStart_time()) * 1000)) + "-" + new SimpleDateFormat("MM月dd日").format(new Date(Long.parseLong(bean.getEnd_time()) * 1000)));
            if (!"0".equals(bean.getIs_hot())) {
                viewHolder.ivHot.setImageResource(R.drawable.is_hot);
            } else {
                viewHolder.ivHot.setImageResource(0);
            }
            viewHolder.tvLocation.setText(bean.getLocation() + bean.getAddr());
            viewHolder.tvLove.setText(bean.getLove() + "人参加漫展");
            viewHolder.tvSection.setText(bean.getSection_name());
            if (!"0".equals(bean.getIs_ticket())) {
                viewHolder.ivTicket.setImageResource(R.drawable.buytickets);
            } else {
                viewHolder.ivTicket.setImageResource(0);
            }
            if (!(0 == bean.getNews_status())) {
                viewHolder.dividerOne.setVisibility(View.VISIBLE);
                viewHolder.tvNewsTitle.setVisibility(View.VISIBLE);
                viewHolder.tvNewsSection.setVisibility(View.VISIBLE);
                viewHolder.tvNewsTitle.setText(bean.getNews_data().getTitle());
                viewHolder.tvNewsSection.setText(bean.getNews_data().getSection_name());
            } else {
                viewHolder.dividerOne.setVisibility(View.GONE);
                viewHolder.tvNewsTitle.setVisibility(View.GONE);
                viewHolder.tvNewsSection.setVisibility(View.GONE);
            }
            if (!(0 == bean.getGuest_status())) {
                viewHolder.dividerTwo.setVisibility(View.VISIBLE);
                viewHolder.tvGuestTitle.setVisibility(View.VISIBLE);
                viewHolder.tvGuestSection.setVisibility(View.VISIBLE);
                viewHolder.tvGuestTitle.setText(bean.getGuset_data().getTitle());
                viewHolder.tvGuestSection.setText(bean.getGuset_data().getSection_name());
            } else {
                viewHolder.dividerTwo.setVisibility(View.GONE);
                viewHolder.tvGuestTitle.setVisibility(View.GONE);
                viewHolder.tvGuestSection.setVisibility(View.GONE);
            }
        }
    }
}

