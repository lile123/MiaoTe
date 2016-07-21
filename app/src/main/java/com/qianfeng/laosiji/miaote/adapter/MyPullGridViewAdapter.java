package com.qianfeng.laosiji.miaote.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qianfeng.laosiji.miaote.R;
import com.qianfeng.laosiji.miaote.bean.FindShopStore;
import com.qianfeng.laosiji.miaote.constant.URLConsatant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by lenovo11 on 2016/7/16.
 */
public class MyPullGridViewAdapter extends BaseAdapter {
    private List<FindShopStore.DataBean> data ;
    private Context mContext;
    private LayoutInflater layoutInflater;
    public MyPullGridViewAdapter (List<FindShopStore.DataBean> data,Context mContext){
        this.data = data;
        this.mContext = mContext;
        layoutInflater = LayoutInflater.from(mContext);


    }
    @Override
    public int getCount() {
        return data == null?0:data.size();
    }

    @Override
    public Object getItem(int position) {
        return data == null?null:data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder hold = null;
        if(convertView == null){
            hold = new MyViewHolder();
            convertView = layoutInflater.inflate(R.layout.find_shop_gridview_item,null);
            hold.iv_shop = (ImageView) convertView.findViewById(R.id.find_shop_iv);
            hold.tv_food = (TextView) convertView.findViewById(R.id.find_shop_food_tv);
            hold.tv_info = (TextView) convertView.findViewById(R.id.find_shop_info_tv);
            hold.tv_time = (TextView) convertView.findViewById(R.id.find_shop_time_tv);
            convertView.setTag(hold);
        }else {
            hold = (MyViewHolder) convertView.getTag();
        }
        FindShopStore.DataBean shopStore = data.get(position);
        String start = new SimpleDateFormat("yyyyMMdd").format(new Date(Long.parseLong(shopStore.getStart_time()) * 1000));

        String end = new SimpleDateFormat("yyyyMMdd").format(new Date(Long.parseLong(shopStore.getEnd_time()) * 1000));

        double dayCount =date(start ,end);
        hold.tv_time.setText(dayCount+"");
        hold.tv_info.setText(shopStore.getName());
        float food =Float.parseFloat(shopStore.getPrice())/1000;
        hold.tv_food.setText(food+"K猫粮");
        Glide.with(mContext).load(URLConsatant.URL_BASE+shopStore.getImg()).into(hold.iv_shop);
        return convertView;
    }

    private double date(String start,String end) {
        Date date1 = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            date1 = simpleDateFormat.parse(start);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date date2 = null;
        try {
            date2 = simpleDateFormat.parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        GregorianCalendar cal1 = new GregorianCalendar();
        GregorianCalendar cal2 = new GregorianCalendar();
        cal1.setTime(date1);
        cal2.setTime(date2);

        double dayCount = (cal2.getTimeInMillis()-cal1.getTimeInMillis())/(1000*3600*24);
        return dayCount;
    }

    public static class MyViewHolder{
        ImageView iv_shop;
        TextView tv_info;
        TextView tv_time;
        TextView tv_food;

    }

}
