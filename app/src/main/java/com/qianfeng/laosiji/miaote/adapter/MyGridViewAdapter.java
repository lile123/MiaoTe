package com.qianfeng.laosiji.miaote.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.qianfeng.laosiji.miaote.R;
import com.qianfeng.laosiji.miaote.bean.Street;
import com.qianfeng.laosiji.miaote.constant.URLConsatant;
import com.qianfeng.laosiji.miaote.ui.PhotoWallActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by lenovo11 on 2016/7/15.
 */
public class MyGridViewAdapter extends BaseAdapter {
    private List<Street.DataBean.AttachBean> data ;
    private Context mContext;
    private LayoutInflater layoutInflater;
    public MyGridViewAdapter (List<Street.DataBean.AttachBean> data,Context mContext){
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder hold = null;
        if(convertView == null){
            hold = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.street_gridview_item,null);
            hold.gv_iv = (ImageView) convertView.findViewById(R.id.street_gv_iv);
            convertView.setTag(hold);
        }else {
            hold = (ViewHolder) convertView.getTag();
        }
        final Street.DataBean.AttachBean attachBean = data.get(position);
        Picasso.with(mContext).load(URLConsatant.URL_BASE+attachBean.getThumb()).into(hold.gv_iv);
        hold.gv_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] urls =new String[data.size()];
                for(int i =0;i<data.size();i++){
                        urls[i] = data.get(i).getThumb();
                }
                Intent intent = new Intent(mContext, PhotoWallActivity.class);
                intent.putExtra("position",position);
                intent.putExtra("urls",urls);

                mContext.startActivity(intent);

            }
        });
        return convertView;
    }
   public static class ViewHolder{
        ImageView gv_iv;
    }
}
