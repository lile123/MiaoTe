package com.qianfeng.laosiji.miaote.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.qianfeng.laosiji.miaote.R;
import com.qianfeng.laosiji.miaote.bean.Find;
import com.qianfeng.laosiji.miaote.constant.URLConsatant;
import com.qianfeng.laosiji.miaote.ui.ShopStoreActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by lenovo11 on 2016/7/16.
 */
public  class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder>{
    private List<Find.GiftDataBean> datas;
    public Context context;

    public MyRecyclerViewAdapter(List datas, Context context) {
        this.datas = datas;
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.find_recyclerview_item, null);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mImageView.setTag(position);
        holder. mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(v.getTag().toString());
                    if(position == datas.size()){
                      Intent intent = new Intent(context, ShopStoreActivity.class);
                        context.startActivity(intent);
                    }
            }
        });
        if(position == datas.size()){
            holder.mImageView.setImageResource(R.drawable.seemore);
        }else {
            Picasso.with(context).load(URLConsatant.URL_BASE + datas.get(position).getImg()).into(holder.mImageView);
        }
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size()+1;
    }



}
    class  MyViewHolder extends RecyclerView.ViewHolder {
    public ImageView mImageView;

    public MyViewHolder(View itemView) {
        super(itemView);
        mImageView = (ImageView) itemView.findViewById(R.id.find_recyclerview_iv);

    }

    }
