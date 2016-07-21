package com.qianfeng.laosiji.miaote.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qianfeng.laosiji.miaote.R;
import com.qianfeng.laosiji.miaote.bean.CityDataBean;
import com.qianfeng.laosiji.miaote.bean.DataBean;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/7/17.
 */
public class ListViewAllAdapter extends BaseAdapter {
    public static final String URL_DOMAIN = "http://api.nyato.com";
    private List<Object> list;
    private Context mContext;
    private static final int CITY_EMPTY_TYPE = 0;
    private static final int CITY_DATA_TYPE = 1;
    private static final int OTHER_HEADER_TYPE = 2;
    private static final int OTHER_DATA_TYPE = 3;

    public ListViewAllAdapter(List<Object> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

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
    public int getViewTypeCount() {
        return 4;
    }


    @Override
    public int getItemViewType(int position) {
        if (list.get(position) instanceof String && "empty".equals(list.get(position))) {
            return CITY_EMPTY_TYPE;
        } else if (list.get(position) instanceof CityDataBean) {
            return CITY_DATA_TYPE;
        } else if (list.get(position) instanceof String && "other".equals(list.get(position))) {
            return OTHER_HEADER_TYPE;
        } else if (list.get(position) instanceof DataBean) {
            return OTHER_DATA_TYPE;
        }
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (CITY_EMPTY_TYPE == getItemViewType(position)) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.city_data_empty_item, parent, false);
        } else if (CITY_DATA_TYPE == getItemViewType(position)) {
            if (null == convertView) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.other_location_item, parent, false);
                initItemView(viewHolder, convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            initCityItemData(viewHolder, position);
        } else if (OTHER_HEADER_TYPE == getItemViewType(position)) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.other_location_header, parent, false);
        } else if (OTHER_DATA_TYPE == getItemViewType(position)) {
            if (null == convertView) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.other_location_item, parent, false);
                initItemView(viewHolder, convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            initOtherItemData(viewHolder, position);
        }
        return convertView;
    }

    private void initItemView(ViewHolder viewHolder, View convertView) {
        viewHolder.ivCover = (ImageView) convertView.findViewById(R.id.other_location_cover_iv);
        viewHolder.tvName = (TextView) convertView.findViewById(R.id.other_location_name_tv);
        viewHolder.ivHot = (ImageView) convertView.findViewById(R.id.other_location_is_hot_iv);
        viewHolder.tvTime = (TextView) convertView.findViewById(R.id.other_location_time_tv);
        viewHolder.tvLocation = (TextView) convertView.findViewById(R.id.other_location_location_tv);
        viewHolder.tvLove = (TextView) convertView.findViewById(R.id.other_location_love_tv);
        viewHolder.tvSection = (TextView) convertView.findViewById(R.id.other_location_section_name_tv);
        viewHolder.ivTicket = (ImageView) convertView.findViewById(R.id.other_location_is_ticket_iv);
        viewHolder.tvNewsSection = (TextView) convertView.findViewById(R.id.other_location_news_section_tv);
        viewHolder.tvNewsTitle = (TextView) convertView.findViewById(R.id.other_location_news_title_tv);
        viewHolder.tvGuestSection = (TextView) convertView.findViewById(R.id.other_location_guest_section_tv);
        viewHolder.tvGuestTitle = (TextView) convertView.findViewById(R.id.other_location_guest_title_tv);
        viewHolder.dividerOne = convertView.findViewById(R.id.other_location_divider_one);
        viewHolder.dividerTwo = convertView.findViewById(R.id.other_location_divider_two);
    }


    private void initOtherItemData(ViewHolder viewHolder, int position) {
        DataBean bean = (DataBean) list.get(position);
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

    private void initCityItemData(ListViewAllAdapter.ViewHolder viewHolder, int position) {
        CityDataBean bean = (CityDataBean) list.get(position);
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


    class ViewHolder {
        public ImageView ivCover;
        public TextView tvName;
        public ImageView ivHot;
        public TextView tvTime;
        public TextView tvLocation;
        public TextView tvLove;
        public TextView tvSection;
        public ImageView ivTicket;
        public TextView tvNewsSection;
        public TextView tvNewsTitle;
        public View dividerOne;
        public TextView tvGuestSection;
        public TextView tvGuestTitle;
        public View dividerTwo;
    }
}
