package com.qianfeng.laosiji.miaote.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qianfeng.laosiji.miaote.R;
import com.qianfeng.laosiji.miaote.bean.ReportInfo;
import com.qianfeng.laosiji.miaote.constant.URLConsatant;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by lenovo11 on 2016/7/18.
 */
public class MyReportInfoListViewAdapter extends BaseAdapter{
    private List<ReportInfo.DataBean.DescBean> data;
    private Context context;
    private LayoutInflater inflater;
    private static final int TYPE_DATE = 0;
    private static final int TYPE_CHANNELS_CONTENT = 1;
    public List<ReportInfo.DataBean.DescBean> getData() {
        return data;
    }

    public MyReportInfoListViewAdapter(List<ReportInfo.DataBean.DescBean> data, Context context) {
        this.data = data;
        this.context = context;
        inflater = LayoutInflater.from(context);
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
    public int getViewTypeCount() {
        return 2;
    }
    @Override
    public int getItemViewType(int position) {
        int type = 0;

        if(data.get(position).getImg().length()>0){
            type = TYPE_DATE;
        }else if(data.get(position).getTxt().length()>0){
            type = TYPE_CHANNELS_CONTENT;
        }
        return type;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);

          if (type == TYPE_DATE ) {
                ReportViewHoler viewHoler = null;
                if (convertView == null) {
                    viewHoler = new ReportViewHoler();
                    convertView = inflater.inflate(R.layout.report_info_list_item2, null);
                    viewHoler.iv_report = (ImageView) convertView.findViewById(R.id.report_info_list_iv);
                    convertView.setTag(viewHoler);
                } else {
                    viewHoler = (ReportViewHoler) convertView.getTag();
                }
                ReportInfo.DataBean.DescBean reportInfo = data.get(position);
//                Glide.with(context).load(URLConsatant.URL_BASE + reportInfo.getImg()).into(viewHoler.iv_report);
              Picasso.with(context).load(URLConsatant.URL_BASE + reportInfo.getImg()).config(Bitmap.Config.RGB_565).placeholder(R.mipmap.ic_launcher).into(viewHoler.iv_report);

            } else if (type == TYPE_CHANNELS_CONTENT) {
                ReportViewHoler2 viewHoler2 = null;
                if (convertView == null) {
                    viewHoler2 = new ReportViewHoler2();
                    convertView = inflater.inflate(R.layout.report_info_list_item, null);
                    viewHoler2.tv_report = (TextView) convertView.findViewById(R.id.report_info_list_tv);
                    convertView.setTag(viewHoler2);
                } else {
                    viewHoler2 = (ReportViewHoler2) convertView.getTag();
                }
                ReportInfo.DataBean.DescBean reportInfo = data.get(position);
                viewHoler2.tv_report.setText(reportInfo.getTxt());
            }

        return convertView;
    }
    static class ReportViewHoler{
        ImageView iv_report;
    }
    static class ReportViewHoler2{
        TextView tv_report;
    }

}
