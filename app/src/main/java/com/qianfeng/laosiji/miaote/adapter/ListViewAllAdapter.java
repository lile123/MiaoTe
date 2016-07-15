package com.qianfeng.laosiji.miaote.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qianfeng.laosiji.miaote.R;

import java.util.List;

/**
 * Created by Administrator on 2016/7/14.
 */
public abstract class ListViewAllAdapter<T> extends BaseAdapter {
    private List<T> list;
    private Context mContext;
    public ListViewAllAdapter(List<T> list,Context mContext){
        this.list = list;
        this.mContext = mContext;
    }
    @Override
    public int getCount() {
        if(0==list.size()){
            return 1;
        }
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
        if(0 == list.size()){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.city_data_empty_item,parent,false);
            return convertView;
        }
        ViewHolder viewHolder;
        if(null == convertView){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.other_location_item,parent,false);
            initItemView(viewHolder,convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        initItemData(viewHolder,position);
        return convertView;
    }

    public abstract void initItemData(ViewHolder viewHolder,int position);

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

    public class ViewHolder{
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
