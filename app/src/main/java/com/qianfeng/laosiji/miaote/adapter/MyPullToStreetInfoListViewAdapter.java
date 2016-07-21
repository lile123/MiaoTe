package com.qianfeng.laosiji.miaote.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qianfeng.laosiji.miaote.R;
import com.qianfeng.laosiji.miaote.bean.StreetInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by lenovo11 on 2016/7/20.
 */
public class MyPullToStreetInfoListViewAdapter extends BaseAdapter {
    private List<StreetInfo.DataBean> data ;
    private Context mContext;
    private LayoutInflater layoutInflater;
    public MyPullToStreetInfoListViewAdapter (List<StreetInfo.DataBean> data,Context mContext){
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
            convertView = layoutInflater.inflate(R.layout.report_info_comment_item,null);
            hold.iv_icon = (CircleImageView) convertView.findViewById(R.id.report_comment_iv);
            hold.tv_name = (TextView) convertView.findViewById(R.id.report_comment_name_tv);
            hold.tv_time = (TextView) convertView.findViewById(R.id.report_comment_time_tv);
            hold.tv_comment = (TextView) convertView.findViewById(R.id.report_comment_comment_tv);
            convertView.setTag(hold);
        }else {
            hold = (MyViewHolder) convertView.getTag();
        }
        StreetInfo.DataBean streetInfo = data.get(position);
        hold.tv_comment.setText(streetInfo.getContent());

        hold.tv_name.setText(streetInfo.getUname());
        String time = new SimpleDateFormat(" HH:mm:ss").format(new Date(Long.parseLong(streetInfo.getCtime()) * 1000));
        hold.tv_time.setText(time);
        Glide.with(mContext).load(streetInfo.getUavatar()).into( hold.iv_icon);
        return convertView;
    }



    public static class MyViewHolder{
        CircleImageView iv_icon;
        TextView tv_name;
        TextView tv_comment;
        TextView tv_time;

    }
}
