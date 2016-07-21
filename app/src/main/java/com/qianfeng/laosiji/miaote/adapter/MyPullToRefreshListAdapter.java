package com.qianfeng.laosiji.miaote.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qianfeng.laosiji.miaote.R;
import com.qianfeng.laosiji.miaote.bean.Street;
import com.qianfeng.laosiji.miaote.constant.URLConsatant;
import com.qianfeng.laosiji.miaote.ui.PhotoActivity;
import com.qianfeng.laosiji.miaote.ui.StreetInfoActivity;
import com.qianfeng.laosiji.miaote.views.CustomGridView;
import com.qianfeng.laosiji.miaote.views.XCFlowLayout;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by lenovo11 on 2016/7/14.
 */
public class MyPullToRefreshListAdapter extends BaseAdapter{
    private List<Street.DataBean> data;
    private Context context;
    private LayoutInflater inflater;
    private static final int TYPE_DATE = 0;
    private static final int TYPE_CHANNELS_CONTENT = 1;
    private static final int TYPE_CHANNELS =2;
    public List<Street.DataBean> getData() {
        return data;
    }

    public MyPullToRefreshListAdapter(List<Street.DataBean> data, Context context) {
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
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        int type=0;
        int num=data.get(position).getPhoto_count();
        if(num == 0){
            type = TYPE_DATE;
        }else if(num == 1){
            type = TYPE_CHANNELS_CONTENT;
        }else {
            type = TYPE_CHANNELS;
        }
        return type;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);

        if(TYPE_DATE == type){
            ViewHolder3 viewHolder3= null;

            if(convertView == null){
                convertView = inflater.inflate(R.layout.street_prf_list_item3,null);
                viewHolder3 = new ViewHolder3(convertView);
                viewHolder3.linearLayout3 = (LinearLayout) convertView.findViewById(R.id.street_item3_ll);
            }else{
                viewHolder3 = (ViewHolder3) convertView.getTag();
            }

            final Street.DataBean street = data.get(position);
            Picasso.with(context).load(street.getUavatar()).into(viewHolder3.circleImageView);
            viewHolder3.tv_name.setText(street.getUname());
            String time = new SimpleDateFormat("  HH:mm:ss").format(new Date(Long.parseLong(street.getPublish_time()) * 1000));
            if(street.getName().length()>5) {
                viewHolder3.tv_time.setText(time + "来自：" + street.getName());
            }else{
                viewHolder3.tv_time.setText(time);
            }
            viewHolder3.tv_content.setText(street.getFeed_content());
            viewHolder3.tv_good.setText(street.getDigg_count());
            viewHolder3.tv_comment.setText(street.getComment_all_count());
            String tag = street.getFeed_tags();
            String a[] = tag.split(",");
            ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.leftMargin = 5;
            lp.rightMargin = 5;
            lp.topMargin = 5;
            lp.bottomMargin = 5;
            /**
             * 根据子控件的个数  排除重复利用Item导致的重复加载子控件
             */
            if(viewHolder3.xcFlowLayout.getChildCount() == 0){
                for(int i = 0; i < a.length; i ++){
                    TextView view = new TextView(context);
                    if(a[i].length()>0){
                        view.setText(a[i]);
                        view.setTextSize(10);
                        view.setGravity(Gravity.CENTER);
                        view.setTextColor(Color.BLACK);
                        view.setBackgroundResource(R.drawable.input_focused);
                        viewHolder3.xcFlowLayout.addView(view,lp);

                    }
                }
            }
            viewHolder3.linearLayout3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,StreetInfoActivity.class);
                    intent.putExtra("feed_id",street.getFeed_id());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("street",  street);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });

        }else if(TYPE_CHANNELS_CONTENT == type){
            ViewHolder viewHolder= null;

            if(convertView == null){
                convertView = inflater.inflate(R.layout.street_prf_list_item2,null);
                viewHolder = new ViewHolder(convertView);
                viewHolder.linearLayout = (LinearLayout) convertView.findViewById(R.id.street_item2_ll);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();

            }

            final Street.DataBean street = data.get(position);
            Picasso.with(context).load(street.getUavatar()).into(viewHolder.circleImageView);
            viewHolder.tv_name.setText(street.getUname());
            String time = new SimpleDateFormat("  HH:mm:ss").format(new Date(Long.parseLong(street.getPublish_time()) * 1000));
            if(street.getName().length()>5) {
                viewHolder.tv_time.setText(time + "来自：" + street.getName());
            }else{
                viewHolder.tv_time.setText(time);
            }
            viewHolder.tv_content.setText(street.getFeed_content());
            viewHolder.tv_good.setText(street.getLove());
            viewHolder.tv_comment.setText(street.getComment_all_count());
         Picasso.with(context).load(URLConsatant.URL_BASE+street.getAttach().get(0).getThumb()).into(viewHolder.imageView);
            viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   Intent intent = new Intent(context,PhotoActivity.class);
                    intent.putExtra("url",street.getAttach().get(0).getThumb());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("street",  street);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
            String tag = street.getFeed_tags();
            String a[] = tag.split(",");
            ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.leftMargin = 5;
            lp.rightMargin = 5;
            lp.topMargin = 5;
            lp.bottomMargin = 5;
            if(viewHolder.xcFlowLayout.getChildCount() ==0){
                for(int i = 0; i < a.length; i ++){
                    TextView view = new TextView(context);

                    if(a[i].length()>0){
                        view.setText(a[i]);
                        view.setTextSize(10);
                        view.setTextColor(Color.BLACK);
                        view.setGravity(Gravity.CENTER);
                        view.setBackgroundResource(R.drawable.input_focused);
                        viewHolder.xcFlowLayout.addView(view,lp);
                    }
            }
            }
            viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,StreetInfoActivity.class);
                    intent.putExtra("feed_id",street.getFeed_id());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("street", street);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }else if(TYPE_CHANNELS == type){

            ViewHolder2 viewHolder2= null;
            if(convertView == null){
                convertView = inflater.inflate(R.layout.street_prf_list_item,null);
                viewHolder2 = new ViewHolder2(convertView);
                viewHolder2.linearLayout2 = (LinearLayout) convertView.findViewById(R.id.street_item_ll);
            }else{
                viewHolder2 = (ViewHolder2) convertView.getTag();
            }
            final Street.DataBean street = data.get(position);
            Picasso.with(context).load(street.getUavatar()).into(viewHolder2.circleImageView);
            viewHolder2.tv_name.setText(street.getUname());
            String time = new SimpleDateFormat("  HH:mm:ss").format(new Date(Long.parseLong(street.getPublish_time()) * 1000));
            if(street.getName().length()>5) {
                viewHolder2.tv_time.setText(time + "来自：" + street.getName());
            }else{
                viewHolder2.tv_time.setText(time);
            }

            viewHolder2.tv_content.setText(street.getFeed_content());
            viewHolder2.tv_good.setText(street.getDigg_count());
            viewHolder2.tv_comment.setText(street.getComment_all_count());
            MyGridViewAdapter adapter = new MyGridViewAdapter(street.getAttach(),context);
            viewHolder2.gridView.setAdapter(adapter);
            String tag = street.getFeed_tags();
            String a[] = tag.split(",");
            ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            if(viewHolder2.xcFlowLayout.getChildCount() == 0){
                for(int i = 0; i < a.length; i ++){
                    TextView view = new TextView(context);

                    if(a[i].length()>0){
                        view.setTextSize(10);
                        view.setText(a[i]);
                        view.setTextColor(Color.BLACK);
                        view.setGravity(Gravity.CENTER);
                        view.setBackgroundResource(R.drawable.input_focused);
                        viewHolder2.xcFlowLayout.addView(view,lp);
                    }
                }
            }
            viewHolder2.linearLayout2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,StreetInfoActivity.class);
                    intent.putExtra("feed_id",street.getFeed_id());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("street", street);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
            }

        return convertView;
    }
    public static class ViewHolder{
        @BindView(R.id.street_civ)
        CircleImageView circleImageView;
        @BindView(R.id.street_name_tv)
        TextView tv_name;
        @BindView(R.id.street_time)
        TextView tv_time;
        @BindView(R.id.street_content_tv)
        TextView tv_content;
        @BindView(R.id.street_pic_iv)
        ImageView imageView;
        @BindView(R.id.street_xcf_ll)
        XCFlowLayout xcFlowLayout;
        @BindView(R.id.street_good_tv)
        TextView tv_good;
        @BindView(R.id.street_comment_tv)
        TextView tv_comment;

        LinearLayout linearLayout;

        public ViewHolder(View view) {
            view.setTag(this);
            ButterKnife.bind(this,view);
        }
    }
    public static class ViewHolder2{
        @BindView(R.id.street_civ)
        CircleImageView circleImageView;
        @BindView(R.id.street_name_tv)
        TextView tv_name;
        @BindView(R.id.street_time)
        TextView tv_time;
        @BindView(R.id.street_content_tv)
        TextView tv_content;
        @BindView(R.id.street_gv)
        CustomGridView gridView;
        @BindView(R.id.street_xcf_ll)
        XCFlowLayout xcFlowLayout;
        @BindView(R.id.street_good_tv)
        TextView tv_good;
        @BindView(R.id.street_comment_tv)
        TextView tv_comment;

        LinearLayout linearLayout2;
        public ViewHolder2(View view) {
            view.setTag(this);
            ButterKnife.bind(this,view);
        }
    }
    public static class ViewHolder3{
        @BindView(R.id.street_civ)
        CircleImageView circleImageView;
        @BindView(R.id.street_name_tv)
        TextView tv_name;
        @BindView(R.id.street_time)
        TextView tv_time;
        @BindView(R.id.street_content_tv)
        TextView tv_content;
        @BindView(R.id.street_xcf_ll)
        XCFlowLayout xcFlowLayout;
        @BindView(R.id.street_good_tv)
        TextView tv_good;
        @BindView(R.id.street_comment_tv)
        TextView tv_comment;

        LinearLayout linearLayout3;
        public ViewHolder3(View view) {
            view.setTag(this);
            ButterKnife.bind(this,view);
        }
    }
}
