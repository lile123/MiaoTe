package com.qianfeng.laosiji.miaote.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qianfeng.laosiji.miaote.R;
import com.qianfeng.laosiji.miaote.bean.FinishBean;
import com.qianfeng.laosiji.miaote.constant.URLConsatant;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/7/23.
 */
public class ListViewFinishAdapter extends BaseAdapter{
    private List<FinishBean.DataBean> list;
    private Context mContext;
    public ListViewFinishAdapter(List<FinishBean.DataBean> list,Context mContext){
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
                    Picasso.with(mContext).load(URLConsatant.URL_BASE + imgList.get(i).getPath()).into(imageView);
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
