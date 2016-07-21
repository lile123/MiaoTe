package com.qianfeng.laosiji.miaote.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qianfeng.laosiji.miaote.R;
import com.qianfeng.laosiji.miaote.bean.TaskCenter;

import java.util.List;

/**
 * Created by lenovo11 on 2016/7/17.
 */
public class MyPullToTaskListViewAdapter extends BaseAdapter {
    private List<TaskCenter.DataBean> data;
    private Context mContext;
    private LayoutInflater inflater;
    public MyPullToTaskListViewAdapter(List<TaskCenter.DataBean> data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return data != null ? data.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TsakViewHolder holder = null;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.find_task_list_item,null);
            holder = new TsakViewHolder();
            holder.tv_task_name = (TextView) convertView.findViewById(R.id.find_task_name_tv);
            holder.tv_new = (TextView) convertView.findViewById(R.id.find_task_new_tv);
            convertView.setTag(holder);
        }else{
            holder = (TsakViewHolder) convertView.getTag();
        }
        TaskCenter.DataBean task = data.get(position);
        holder.tv_task_name.setText(task.getTask_name());
        holder.tv_new.setText(task.getStep_name());
        return convertView;
    }
    public static  class TsakViewHolder{

        TextView tv_task_name;
        TextView tv_new;
    }
}
